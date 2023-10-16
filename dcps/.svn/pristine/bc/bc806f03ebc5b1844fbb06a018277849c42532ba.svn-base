
<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page session="true"%>
<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>

<script type="text/javascript" src="common/script/commonfunctions.js">
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

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="empName" value="${resValue.empList}">
</c:set>
<c:set var="loanList" value="${resValue.loanList}">
</c:set>
<c:set var="loanList1" value="${resValue.loanList}">
</c:set>
<c:set var="prinLoanTypeId" value="${resValue.prinLoanTypeId}">
</c:set>
<c:set var="intLoanTypeId" value="${resValue.intLoanTypeId}">
</c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="empName" value="${resValue.empName}"></c:set>
<c:set var="empId" value="${resValue.empId}"></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}"></c:set>

<!-- added by ravysh -->
<c:set var="FromBasicDtlsNew" value="${resValue.FromBasicDtlsNew}"></c:set>
<c:set var="otherId" value="${resValue.otherId}"></c:set>


<fmt:setBundle basename="resources.Payroll" var="commonLable"
	scope="request" />
<fmt:message var="contractEmpType" key="contract"
	bundle="${commonLable}" scope="request">
</fmt:message>
<fmt:message var="fixedEmpType" key="fixed" bundle="${commonLable}"
	scope="request">
</fmt:message>
<fmt:message var="directRecruit" key="directRecruit"
	bundle="${commonLable}" scope="request">
</fmt:message>
<fmt:message var="probationEmpType" key="probation"
	bundle="${commonLable}" scope="request">
</fmt:message>
<fmt:message var="festival" key="festival" bundle="${commonLable}"
	scope="request">
</fmt:message>
<fmt:message var="foodGrain" key="foodGrain" bundle="${commonLable}"
	scope="request">
</fmt:message>
<script language="javascript"><!--
//alert("newEmpLoadDtls......");
var empId;
var empname;
var count=0;

function checkOddAmount()
{
	alert('insode heaven');
	var amt=document.empLoan.principalAmt.value;
	if(amt=="")
	{
		amt=document.empLoan.interestAmt.value;
	}
	alert('anmount is '+amt);
	var instNo=document.empLoan.principalInstNo.value;
	if(instNo=="")
	{
		instNo=document.empLoan.interestInstNo.value;
	}
	//var emiAmount=amt/instNo;
	alert('instNo is '+instNo);
	var emiAmount= document.empLoan.loanPrinEmiAmt.value;
	if(emiAmount=="")
	{
		emiAmount= document.empLoan.loanIntEmiAmt.value;
	}
	alert('emiAmount is '+emiAmount);

	if((emiAmount*instNo)<amt)
	{
		alert('Please Enter appropriate value of Installment No');
		return false;
	}

	
	
	if((emiAmount*instNo)>amt)
	{
		var oddInsAmt=amt-(emiAmount*(instNo-1));
		var oddInsNo=instNo;
	
	alert('oddInsAmt=  '+oddInsAmt +' oddInsNo= '+oddInsNo );
	var orginalOddInstNo=  document.getElementById("loanOddinstno").value;
	alert(''+orginalOddInstNo);
	var orignialOddInstAmt=document.getElementById("loanOddinstAmt").value;
	alert(''+orignialOddInstAmt);
	var box="";
	// 03 JAN 2012 change for odd installment
/*	if(orginalOddInstNo != oddInsNo)
	{
		alert('Please enter appropriate value of Odd Installment No. i.e.'+oddInsNo);
		box=doucment.getElementById("loanOddinstno");
		box.focus();
		return false;
	}
	if(orignialOddInstAmt!= oddInsAmt)
	{
		alert('Please enter appropriate value of Odd Installment Amount i.e.'+oddInsAmt);
		box=doucment.getElementById("loanOddinstAmt");
		box.focus();
		return false;
	}*/
	
	}
	return true;
}

function chknumber(numfield){

	var resultCode=true;	
	var strValidChars = "0123456789";
	var amt=numfield.value;	

	for (i = 0; i < amt.length && resultCode == true; i++)
      {
      strChar = amt.charAt(i);
      if (strValidChars.indexOf(strChar) == -1)
         {
         resultCode = false;
        
         }
      }
      
    if(resultCode != true){  
    	alert("Enter Number Only");
    	numfield.value='';
    	numfield.focus();
    	return false;
    }
   
}

function chknumberforNegative(recoveredValue)
{
	var recoveredAmt = recoveredValue.value;
	
	if(recoveredAmt!="" && eval(recoveredAmt)<=0)
    	{
    		alert("Value can not be negative or zero");
    		recoveredValue.value="";
    		recoveredValue.focus();
    		return false;
    	}
}

function resetFormm() {
	 if(confirm("All values will be cleared please confirm !") == true){
	 	resetFields();
     }
}			  	

function chkRecovedAmt()
{
	
	var loanPrinRecovAmt=document.empLoan.loanPrinRecovAmt.value;	
	var loanPrinRecovInt=document.empLoan.loanPrinRecovInt.value;
	var loanIntRecovAmt=document.empLoan.loanIntRecovAmt.value;
	var loanIntRecovInt=document.empLoan.loanIntRecovInt.value;
	
	var principalAmt=document.empLoan.principalAmt.value;
	var principalInstNo=document.empLoan.principalInstNo.value;
	var interestAmt=document.empLoan.interestAmt.value;
	var interestInstNo=document.empLoan.interestInstNo.value;
	
	var loanPrinEmiAmt=document.empLoan.loanPrinEmiAmt.value;
	var loanIntEmiAmt=document.empLoan.loanIntEmiAmt.value;

	
	if(principalAmt!= "")
	{
		if(loanPrinRecovAmt != "" && eval(loanPrinRecovAmt)>eval(principalAmt) )
		{
		    alert("Loan PrinciPal Recovered Amount can not be greater than Loan Principal Amount.");
		    document.empLoan.loanPrinRecovAmt.focus();
		    document.empLoan.loanPrinRecovAmt.value="";
	    	return false;
	    }
	}
	else
	{
		if(loanPrinRecovAmt != "")
		{
		    alert("Please Enter the Loan Principal Amount.");
		    document.empLoan.principalAmt.focus();
		    document.empLoan.loanPrinRecovAmt.value="";
	    	return false;
	    }
	}
    if(principalInstNo!="")
    {
	    if(loanPrinRecovInt != "" && eval(loanPrinRecovInt)>eval(principalInstNo) )
	    {
		    alert("Loan PrinciPal Recovered Installsment can not be greater than Loan Principal Installments.");
		    document.empLoan.loanPrinRecovInt.focus();
		    document.empLoan.loanPrinRecovInt.value="";
	    	return false;
	    }
	}
	else
	{
		if(loanPrinRecovInt != "")
		{
		    alert("Please Enter the Loan Principal Installments.");
		    document.empLoan.principalInstNo.focus();
		    document.empLoan.loanPrinRecovInt.value="";
	    	return false;
	    }
	}
	if(interestAmt!="")
	{
	    if(loanIntRecovAmt != "" && eval(loanIntRecovAmt)>eval(interestAmt) )
	    {
		    alert("Loan Interest Recovered Amount can not be greater than Loan Interest Amount.");
		    document.empLoan.loanIntRecovAmt.focus();
		    document.empLoan.loanIntRecovAmt.value="";
	    	return false;
	    }
	}
	else
	{
		if(loanIntRecovAmt != "")
		{
		    alert("Please Enter the Loan Interest Amount.");
		    document.empLoan.interestAmt.focus();
		    document.empLoan.loanIntRecovAmt.value="";
	    	return false;
	    }
	}
	if(interestInstNo!="")
	{
	    if(loanIntRecovInt != "" && eval(loanIntRecovInt)>eval(interestInstNo) )
	    {
		    alert("Loan Interest Recovered Installments can not be greater than Loan Interest Installments.");
		    document.empLoan.loanIntRecovInt.focus();
		    document.empLoan.loanIntRecovInt.value="";
	    	return false;
	    }
	}
	else
	{
		if(loanIntRecovInt != "")
		{
		    alert("Please Enter the Loan Interest Amount.");
		    document.empLoan.interestInstNo.focus();
		    document.empLoan.loanIntRecovInt.value="";
	    	return false;
	    }
	}
	
	return true;
    
}

function disableLeftComponent()
{
	/*
	document.empLoan.loanIntEmiAmt.readOnly=true;
	document.empLoan.interestAmt.readOnly=true;
	document.empLoan.interestInstNo.readOnly=true;
	var principalAmt=document.empLoan.principalAmt.value;
	var principalInstNo=document.empLoan.principalInstNo.value;
	var loanPrinEmiAmt=document.empLoan.loanPrinEmiAmt.value;
	var loanIntEmiAmt=document.empLoan.loanIntEmiAmt.value;
	var interestAmt=document.empLoan.interestAmt.value;
	var interestInstNo=document.empLoan.interestInstNo.value;
	
	if(principalAmt!='' || principalInstNo !='' || loanPrinEmiAmt !='')
	{
	document.empLoan.loanIntEmiAmt.readOnly=true;
	document.empLoan.interestAmt.readOnly=true;
	document.empLoan.interestInstNo.readOnly=true;
	}
	else
	{

	document.empLoan.loanIntEmiAmt.readOnly=false;
	document.empLoan.interestAmt.readOnly=false;
	document.empLoan.interestInstNo.readOnly=false;
	}
	*/
}

function disableRightComponent()
{
	/*
	document.empLoan.principalAmt.readOnly=true;
	document.empLoan.loanPrinEmiAmt.readOnly=true;
	document.empLoan.principalInstNo.readOnly=true;

	var principalAmt=document.empLoan.principalAmt.value;
	var principalInstNo=document.empLoan.principalInstNo.value;
	var loanPrinEmiAmt=document.empLoan.loanPrinEmiAmt.value;
	var loanIntEmiAmt=document.empLoan.loanIntEmiAmt.value;
	var interestAmt=document.empLoan.interestAmt.value;
	var interestInstNo=document.empLoan.interestInstNo.value;

	if(loanIntEmiAmt!='' || interestAmt !='' || interestInstNo !='')
	{
		document.empLoan.principalAmt.readOnly=true;
		document.empLoan.loanPrinEmiAmt.readOnly=true;
		document.empLoan.principalInstNo.readOnly=true;
	}
	else
	{	
		document.empLoan.principalAmt.readOnly=false;
		document.empLoan.loanPrinEmiAmt.readOnly=false;
		document.empLoan.principalInstNo.readOnly=false;
	}
	*/
}

function addLoanDataToTable(){
	
	var amt=document.empLoan.principalAmt.value;
	if(amt=="")
	{
		amt=document.empLoan.interestAmt.value;
	}
	//alert('anmount is '+amt);
	var instNo=document.empLoan.principalInstNo.value;
	if(instNo=="")
	{
		instNo=document.empLoan.interestInstNo.value;
	}
	//var emiAmount=amt/instNo;
	//alert('instNo is '+instNo);
	var emiAmount= document.empLoan.loanPrinEmiAmt.value;
	if(emiAmount=="")
	{
		emiAmount= document.empLoan.loanIntEmiAmt.value;
	}
	//alert('emiAmount is '+emiAmount);
	var reqAmt="";
	if(amt % emiAmount != 0)
	{
		reqInstal = ((amt-(amt%emiAmount))/emiAmount)+1;
	}
	else
	{
		reqInstal=amt/emiAmount;
	}

	
	if(instNo != reqInstal)
	{
		//alert('Please Enter appropriate value of Installment No i.e.'+reqInstal);
		//return false;
	}
	
	if((emiAmount*instNo)>amt)
	{
		var oddInsAmt=amt-(emiAmount*(instNo-1));
		var oddInsNo=instNo;
	
	//alert('oddInsAmt=  '+oddInsAmt +' oddInsNo= '+oddInsNo );
	var orginalOddInstNo=  document.getElementById("loanOddinstno").value;
	//alert(''+orginalOddInstNo);
	var orignialOddInstAmt=document.getElementById("loanOddinstAmt").value;
	//alert(''+orignialOddInstAmt);
	var box="";
	/*03 JAN 2012 change for odd installment
	if(orginalOddInstNo != oddInsNo)
	{
		alert('Please enter appropriate value of Odd Installment No. i.e.'+oddInsNo);
		box=doucment.getElementById("loanOddinstno");
		box.focus();
		return false;
	}
	if(orignialOddInstAmt!= oddInsAmt)
	{
		alert('Please enter appropriate value of Odd Installment Amount i.e.'+oddInsAmt);
		box=doucment.getElementById("loanOddinstAmt");
		box.focus();
		return false;
	}*/
	}
	//alert(''+document.empLoan.loanVoucherNo.value);
	if(document.empLoan.loanVoucherNo.value == "")
	{
		alert('Please Enter  Voucher No');
		box=document.empLoan.loanVoucherNo.value;
		box.focus();
		return false;
	}
	//alert(''+document.empLoan.loanVoucherDate);
	if(document.empLoan.loanVoucherDate.value == "")
	{
		alert('Please Enter  Voucher Date');
		box=document.empLoan.loanVoucherDate;
		box.focus();
		return false;
	}
	if(document.empLoan.loanSancOrderDate.value == "")
	{
		alert('Please Enter loan sanction order Date');
		box=document.empLoan.loanSancOrderDate;
		box.focus();
		return false;
	}
	
	if(document.getElementById("Employee_ID_EmpSearch").value != ""){

		var loanDataForVOGEN = new Array('Employee_ID_EmpSearch','loanName','principalAmt','principalInstNo','loanPrinEmiAmt','interestAmt','interestInstNo','loanIntEmiAmt','loanACNo','loanSancOrderNo','loanSancOrderDate','loanDate','loanPrinRecovAmt','loanPrinRecovInt','loanIntRecovAmt','loanIntRecovInt','loanOddinstno','loanOddinstAmt','loanVoucherNo','loanVoucherDate');
		addOrUpdateRecord('addRecord', 'multipleLoanData', loanDataForVOGEN);

	}
	else{
		alert("Please search employee first");
	}
	return true;
	
}//end function: addLoanDataToTable()

function addRecord(){

	//alert("addRecord() called: ");	

    if(document.getElementById("Employee_ID_EmpSearch").value == ""){
	    alert("Select the Employee");
    	return false;
    }
    if(document.getElementById("loanName").value == ""){
	    alert("Select the Loan Type");
	    document.empLoan.loanName.focus();
    	return false;
    }

    if(document.empLoan.loanDate.value == ''){
    	alert("Enter the Loan Start Date");
    	document.empLoan.loanDate.focus();
    	return false;
    }

	var principalAmt=document.empLoan.principalAmt.value;
	var principalInstNo=document.empLoan.principalInstNo.value;
	var loanPrinEmiAmt=document.empLoan.loanPrinEmiAmt.value;
	var loanIntEmiAmt=document.empLoan.loanIntEmiAmt.value;
	var interestAmt=document.empLoan.interestAmt.value;
	var interestInstNo=document.empLoan.interestInstNo.value;
	if(principalAmt!='' || principalInstNo !='' || loanPrinEmiAmt !='')
	{
		document.empLoan.loanIntEmiAmt.readOnly;
		document.empLoan.interestAmt.readOnly;
		document.empLoan.interestInstNo.readOnly;
	}
	
	
	if((principalAmt=='' || principalInstNo =='' || loanPrinEmiAmt =='') && (interestAmt =='' || interestInstNo=='' || loanIntEmiAmt ==''))
	{
	 	alert('Please Enter Proper Loan Value');
	 return false;
	}
	else if(((principalAmt!='' && principalInstNo !='' && loanPrinEmiAmt !='') && (interestAmt !='' && interestInstNo!='' && loanIntEmiAmt !='')))
	{
	 	alert('Please Enter Principal Or Interest not Both Value');
	 return false;
	}
	else if(eval(loanIntEmiAmt)>eval(interestAmt) || eval(loanPrinEmiAmt)>eval(principalAmt))
	{
		alert('Emi-Amount should Not be Greater than Amount');
	 return false
	}
	
	if(chkRecovedAmt()==false)
	{
		return false;
	}


	//varun
 	if (xmlHttp.readyState == 4)
	{ 	
 		
		var loanDataForTableDisplay = new Array('loanName','principalAmt','principalInstNo','loanPrinEmiAmt','interestAmt','interestInstNo','loanIntEmiAmt','loanACNo','loanSancOrderNo','loanSancOrderDate','loanDate','loanPrinRecovAmt','loanPrinRecovInt','loanIntRecovAmt','loanIntRecovInt','loanOddinstno','loanOddinstAmt','loanVoucherNo','loanVoucherDate');

	
		var rowId = addDataInTable('tblempLoan','encXML',loanDataForTableDisplay,'','deleteRecord');

		
		resetFields();
	return true;
	}

}//end method: addRecord()

function resetFields(){
    
	document.empLoan.loanName.value='';
	document.empLoan.principalAmt.value='';
	document.empLoan.principalInstNo.value='';
	document.empLoan.loanPrinEmiAmt.value='';
	document.empLoan.interestAmt.value='';
	document.empLoan.interestInstNo.value='';
	document.empLoan.loanIntEmiAmt.value='';
	document.empLoan.loanACNo.value='';
	document.empLoan.loanSancOrderNo.value='';
	document.empLoan.loanSancOrderDate.value='';
	document.empLoan.loanDate.value='';
	document.empLoan.loanVoucherNo.value='';
	document.empLoan.loanVoucherDate.value='';
	document.empLoan.loanPrinRecovAmt.value='';
	document.empLoan.loanPrinRecovInt.value='';
	document.empLoan.loanIntRecovAmt.value='';
	document.empLoan.loanIntRecovInt.value='';
	document.empLoan.loanOddinstno.value='';
	document.empLoan.loanOddinstAmt.value='';
}

function chkValue1()
{
	
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	if(empId=="")
	{
		alert("Please search the employee first");
		document.empLoan.loanName.value = '';
	}
	else
	{

		document.getElementById("Employee_ID_EmpSearch").value=empId;
		document.getElementById("Emp_allow").value='y';
		
		addOrUpdateRecord('ChkEmp', 'chkEmpDetail', new Array('Employee_ID_EmpSearch','Emp_allow'));
		
		
		return true;
	}	
}
var emptype=300026;
function ChkEmp()
{
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
				var empMapping = XMLDocForAjax.getElementsByTagName('emp-mapping');	
					
					if(empMapping.length != 0) {	
							var emp = empMapping[0].childNodes[0].text;		
							//added by samir for contractual and fixe scale employee
							 emptype = empMapping[0].childNodes[1].text;		
							
					/*		if(emptype=="${fixedEmpType}" || emptype=="${contractEmpType}" || emptype=="${probationEmpType}")
							{
							alert("Not Accessible For Fixed and Contractual Employee and Probession employee!!");
							if( "${empAllRec}"!=null && "${empAllRec}"=='true')
							{
							window.location="./hrms.htm?actionFlag=getLoanValue&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y";
							return;
							}
							else
							{
							clearEmployee("EmpSearch");
							document.empLoan.loanName.value = '';
							return;
							}
							}*/
							//end
								if(emp<0)
								{
									var res = confirm("The Employee information is not entered in the system.\n Want to Enter the Information?");
									resetFields();
									document.empLoan.reset();
									retValue=false;
										if(res){
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=newEmpData&empId=${empId}&empAllRec=Y&newEntryEmpId=${empId}"
										else
										//alert('in else'+ empId);
										var url = "./hrms.htm?actionFlag=newEmpData";
									document.empLoan.action=url;
									document.empLoan.submit();
									}	

								}
								if(emp=="n")
								{
									var res = confirm("The Employee Basic Detail is not entered in the System.\n Want to Enter the Information?");
									resetFields();
									retValue=false;
										document.empLoan.reset();
										if(res){
										//alert("${empAllRec}");
											if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
										var url ="./hrms.htm?actionFlag=fillCombo&empId="+empId+"&empAllRec=true&newEntryEmpId="+empId;
										else
										//alert('in else'+ empId);
									var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&empId="+empId+"&newEntryEmpId="+empId;
										document.empLoan.action=url;
										document.empLoan.submit();
										}
										
								}
								
							}							
			}
		}
}
function isNegative(interestAmt)
{
	var loanId=document.empLoan.loanName.value;
	if(loanId=="")
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
	if(loanId=="")
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
	if(loanId=="")
	{
		alert("Please select the loan type first");
		document.empLoan.loanName.focus();
		retValue=false;
	}
	else
	{
		if(loanInstallment!="" && loanInstallment>0)
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
	
	var empId=document.getElementById("Employee_ID_EmpSearch").value;
	var newLoanType=document.getElementById("loanName");
	var loanId=newLoanType.value;
	var retValue=true;
	if(empId=="" || loanId=="")
	{
		if(empId=="")
		{
			alert("Please search the employee first");
			retValue=false;
		}
		if(loanId=="")
		{
			alert("Please select the loan type first");
			newLoanType.options[0].selected="selected";
			newLoanType.focus();
			document.getElementById('loanTypeCaptionTD').style.display = 'none';
			document.getElementById('loanTypeFieldTD').style.display = 'none';
			retValue=false;
		}
	}

	else
	{
		var prinLoanTypeId = '${prinLoanTypeId}';
		var intLoanTypeId = '${intLoanTypeId}';
		
		if(prinLoanTypeId.indexOf(loanId) != -1){
			for(var itr=1;itr<=5;itr++){
				document.getElementById('interestTD'+itr).style.display = 'none';
				document.getElementById('interestTD'+itr+"."+itr).style.display = 'none';
				document.getElementById('principalTD'+itr).style.display = '';
				document.getElementById('principalTD'+itr+"."+itr).style.display = '';
			}
			document.getElementById('loanTypeCaptionTD').style.display = '';
			document.getElementById('loanTypeFieldTD').style.display = '';
			document.getElementById('LoanTypeDesc').value="Principal Loan";
			
		}else if(intLoanTypeId.indexOf(loanId) != -1){
			for(var itr=1;itr<=5;itr++){
				document.getElementById('principalTD'+itr).style.display = 'none';
				document.getElementById('principalTD'+itr+"."+itr).style.display = 'none';
				document.getElementById('interestTD'+itr).style.display = '';
				document.getElementById('interestTD'+itr+"."+itr).style.display = '';
			}
			document.getElementById('loanTypeCaptionTD').style.display = '';
			document.getElementById('loanTypeFieldTD').style.display = '';
			document.getElementById('LoanTypeDesc').value="Interest Loan";
		}
		if( emptype=="${directRecruit}" && (loanId!="${foodGrain}" && loanId !="${festival}"))	
		{
			alert("Not Accessible Direct Recurit employee!!");
			newLoanType.options[0].selected="selected";
			newLoanType.focus();
			retValue=false;
		}
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
				    retValue=false;     
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
						if(LoanNo>0){
							alert("Loan is already sanctioned to the Employee selected");
							newLoanType.options[0].selected="selected";
							newLoanType.focus();
							retValue=false;
						}
						if(LoanNo==-1){
									var res = confirm("The Employee information is not entered in the system.\n Want to Enter the Information?");
									resetFields();
									document.empLoan.reset();
									retValue=false;
										if(res){
										if( "${empAllRec}"!=null && "${empAllRec}"=='true')
										//alert('in if'+ empId);
												var url ="./hrms.htm?actionFlag=fillCombo&empId="+empId+"&empAllRec=true&newEntryEmpId="+empId;
										else
										//alert('in else'+ empId);
										var url = "./hrms.htm?actionFlag=fillCombo&empAllRec=false&empId="+empId+"&newEntryEmpId="+empId;
									document.empLoan.action=url;
									document.empLoan.submit();
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


function chkPrinEmiAmt() {
	
	var loanPrinEmiAmt = document.empLoan.loanPrinEmiAmt.value;
	//alert("The Value of Principal EMI Amount is:-"+loanPrinEmiAmt);
	var loanPrinAmt = document.empLoan.principalAmt.value;
	//alert("The Value of Principal Amount is:-"+loanPrinAmt);	
	var loanPrinInstNo = document.empLoan.principalInstNo.value;
	//alert("The Value of Principal Installment No is:-"+loanPrinInstNo);	
	
	if(loanPrinAmt <= 0 || loanPrinAmt==null){
		alert("Enter Positive Principal Amount");		
		document.empLoan.loanPrinEmiAmt.value='';
		document.empLoan.principalAmt.value='';
		document.empLoan.principalAmt.focus();
		
		return false;
	}
	if(loanPrinInstNo <= 0 || loanPrinInstNo==null){
		alert("Enter Positive Principal Installment Number");
		document.empLoan.loanPrinEmiAmt.value='';
		document.empLoan.principalInstNo.focus();
		return false;
	}
	var newLoanPrinAmt = loanPrinEmiAmt * loanPrinInstNo;		
	if(newLoanPrinAmt != loanPrinAmt) {
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
	if(loanIntAmt <= 0 || loanIntAmt==null){
		alert("Enter Positive Interest Amount");
		document.empLoan.loanIntEmiAmt.value='';
		document.empLoan.interestAmt.focus();
		return false;
	}
	if(loanIntInstNo <= 0 || loanIntInstNo==null){
		alert("Enter Positive Interest Installment Number");
		document.empLoan.loanIntEmiAmt.value='';
		document.empLoan.interestInstNo.focus();
		return false;
	}	
	var newLoanIntAmt = loanIntEmiAmt * loanIntInstNo;	
	if(newLoanIntAmt != loanIntAmt) {
		alert('Enter Interest EMI Amount compatible with the Interest Amount & Interest Installment Number');
		document.empLoan.loanIntEmiAmt.value='';
		document.empLoan.loanIntEmiAmt.focus();
		return false;
	}
}


function validateForm()
{	

	return true;
}

function beforeSubmit()
{
	if(!confirm("Are you sure,you want to save  Loan Details ?"))
	{
		hideProgressbar();   
		document.empLoan.formSubmitButton.disabled = false;
		return;
	}
	if("${empAllRec}"=='true'){
		document.empLoan.action="./hrms.htm?actionFlag=multipleAddLoan&edit=N&empId=${empId}&empAllRec=y";
	}
	else{
		//alert("inside empAllRec=n " ); 
		if("${FromBasicDtlsNew}"!='YES')
		document.empLoan.action="./hrms.htm?actionFlag=multipleAddLoan&edit=N&empAllRec=n";
		else
			document.empLoan.action="./hrms.htm?actionFlag=multipleAddLoan&edit=N&empAllRec=n&FromBasicDtlsNew=YES&otherId=${otherId}";	
	}
	document.empLoan.submit();
	
	
}
--></script>
<body>
<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />


<hdiits:form name="empLoan" validate="true" method="POST"
	action="javascript:beforeSubmit()" encType="text/form-data">
	<%-- <hdiits:form name="empLoan" validate="true" method="POST"
	action="./hrms.htm?actionFlag=insertEmpLoanDtls&edit=N" encType="text/form-data">	--%>


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs" >
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="EL.loanMaster"
			bundle="${commonLables}" /></b></a></li>
	</ul>
	</div>
	
	<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin"><c:choose>
		<c:when test="${empAllRec!='true'}">
			<c:set value="display:show" var="displayStyle" />
		</c:when>
		<c:otherwise>
			<c:set value="display:none" var="displayStyle" />
			<hdiits:hidden name="Employee_ID_EmpSearch" default="${empId}" />

		</c:otherwise>
	</c:choose>
	<br/>
	<table width="85%" align="center" style="${displayStyle}" border="1">
		<tr >
			<TD >
			<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
				<jsp:param name="searchEmployeeTitle" value="Search Employee" />
				<jsp:param name="SearchEmployee" value="EmpSearch" />
				<jsp:param name="formName" value="empLoan" />
				<jsp:param name="functionName" value="chkValue1" />
			</jsp:include></td>
		</tr>
	</table>

	<table width="80%" align="center">
		<br>
		<hdiits:hidden id="empId" name="empId" />
		<hdiits:hidden name="Emp_allow"></hdiits:hidden>

		<tr></tr>
		<tr style="color: red">
			Note: Only those loans and advances which are mapped with Organization/Institution
			will come in
			<b>Loan Name</b>
			combo given below.
		</tr>
		<tr>
			<TD class="fieldLabel"><hdiits:caption captionid="EL.loanName"
				bundle="${commonLables}"></hdiits:caption></TD>
			<td><hdiits:select name="loanName" size="1" id="loanName"
				captionid="EL.loanName" mandatory="true" bundle="${commonLables}"
				onchange="chkFunc();chkValue1()" tabindex="1">
				<hdiits:option value="">--Select--</hdiits:option>

				<c:forEach var="loanList" items="${loanList}">
					<hdiits:option value="${loanList.typeId}">
						<c:out  value="${loanList.rltBillTypeEdp.edpShortName}" >
						</c:out>
					</hdiits:option>
				</c:forEach>
			</hdiits:select>
			</td>
			<td id="principalTD1.1" name="principalTD1.1" ><b><hdiits:caption captionid="EL.lpAmt"
				bundle="${commonLables}" /></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td id="principalTD1" name="principalTD1" ><hdiits:number name="principalAmt" tabindex="8" default=""
				style="text-align:right" caption="principalAmt"
				onblur="disableLeftComponent();return chknumberforNegative(this);"
				validation="txt.isnumber" maxlength="10" size="20" /></td>
				
			<td id="interestTD1.1" name="interestTD1.1" ><b><hdiits:caption captionid="EL.liAmt"
				bundle="${commonLables}" /></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td id="interestTD1" name="interestTD1"  ><hdiits:number name="interestAmt" tabindex="8" default=""
				style="text-align:right" caption="interestAmt"
				validation="txt.isnumber"
				onblur="disableRightComponent();return chknumberforNegative(this)"
				maxlength="10" size="20" /></td>
			
			<td id="loanTypeCaptionTD" name="loanTypeCaptionTD" style="display:none"><hdiits:caption captionid="VN.LoanType"
				bundle="${commonLables}" /></td>
			<td id="loanTypeFieldTD" name="loanTypeFieldTD" style="display:none"><hdiits:text name="LoanTypeDesc" default="Select Loan Name" id="LoanTypeDesc"
				caption="LoanType" readonly="true" /></TD>	
			
		

		</tr>
		<tr>
		
		
			<td><hdiits:caption captionid="EL.loanStartDate"
				bundle="${commonLables}" /></td>
			<td><hdiits:dateTime captionid="EL.loanStartDate" caption="loanDate"
				 name="loanDate" mandatory="true" validation="txt.isdt"
				bundle="${commonLables}" minvalue="" tabindex="2"/></TD>
				
				<td id="principalTD2.2" name="principalTD2.2"><b><hdiits:caption captionid="EL.lpiNo"
				bundle="${commonLables}" /></td>
			<td id="principalTD2" name="principalTD2" ><hdiits:number name="principalInstNo" tabindex="9"
				default="" caption="principalInstNo" style="text-align:right"
				onblur="disableLeftComponent();return chknumberforNegative(this);"
				validation="txt.isnumber" maxlength="3" size="20" /></td>
				
				<td id="interestTD2.2" name="interestTD2.2"><b><hdiits:caption captionid="EL.liiNo"
				bundle="${commonLables}" /></td>
			<td id="interestTD2" name="interestTD2" ><hdiits:number name="interestInstNo" tabindex="9" style="text-align:right"
				default="" caption="interestInstNo" validation="txt.isnumber"
				onblur="disableRightComponent();return chknumberforNegative(this)"
				maxlength="3" size="20" /></td>
				
			
		</tr>



		<tr>
		
			<td><hdiits:caption captionid="EL.loanACNo"
				bundle="${commonLables}" /></td>
			<td><hdiits:text  name="loanACNo" default=""
				caption="loanACNo" maxlength="16" size="20" tabindex="3"/></td>
				
				<td id="principalTD3.3" name="principalTD3.3"><b><hdiits:caption captionid="EL.loanPrincipalEmiAmt"
				bundle="${commonLables}" /></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td id="principalTD3" name="principalTD3"><hdiits:number name="loanPrinEmiAmt" tabindex="10" default=""
				style="text-align:right" caption="loanPrinEmiAmt"
				validation="txt.isnumber"
				onblur="disableLeftComponent();return chknumberforNegative(this);"
				maxlength="8" size="20" /></td>
			<td id="interestTD3.3" name="interestTD3.3"><b><hdiits:caption captionid="EL.loanInterestEmiAmt"
				bundle="${commonLables}" /></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td id="interestTD3" name="interestTD3"><hdiits:number name="loanIntEmiAmt" tabindex="10"
				default="" style="text-align:right" caption="loanIntEmiAmt"
				validation="txt.isnumber"
				onblur="disableRightComponent();return chknumberforNegative(this)"
				maxlength="8" size="20" /></td>
			
			
		</tr>

		<tr>
		
			<td><hdiits:caption captionid="EL.loanSancOrderNo"
				bundle="${commonLables}" /></td>
			<td><hdiits:text  name="loanSancOrderNo" default=""
				caption="Loan Sanction Order No" size="20" maxlength="25" tabindex="4"/></td>
				
				<td id="principalTD4.4" name="principalTD4.4"><hdiits:caption captionid="EL.loanPrinRecovAmt"
				bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td id="principalTD4" name="principalTD4"><hdiits:number name="loanPrinRecovAmt" tabindex="11"
				default="" style="text-align:right" caption="loanPrinRecovAmt"
				validation="txt.isnumber" onblur="return chkRecovedAmt()"
				maxlength="8" size="20" /></td>
				
				<td id="interestTD4.4" name="interestTD4.4"><hdiits:caption captionid="EL.loanIntRecovAmt"
				bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td id="interestTD4" name="interestTD4" ><hdiits:number name="loanIntRecovAmt" tabindex="11"
				default="" style="text-align:right" caption="loanIntRecovAmt"
				validation="txt.isnumber" onblur="return chkRecovedAmt()"
				maxlength="8" size="20" /></td>
			
			
		</tr>

		<tr>
		
			<td><hdiits:caption captionid="EL.loanSancOrderDate"
				bundle="${commonLables}" /></td>
			<td><hdiits:dateTime captionid="EL.loanSancOrderDate"
				caption="loanSancOrderDate"  name="loanSancOrderDate"  mandatory="true"
				validation="txt.isdt" bundle="${commonLables}" minvalue="" tabindex="5"/></td>
				
				<td id="principalTD5.5" name="principalTD5.5"><hdiits:caption captionid="EL.loanPrinRecovInt"
				bundle="${commonLables}" /></td>
			<td id="principalTD5" name="principalTD5"><hdiits:number name="loanPrinRecovInt" tabindex="12"
				default="" caption="loanPrinRecovInt" validation="txt.isnumber" style="text-align:right"
				onblur="return chkRecovedAmt()" maxlength="8" size="20" /></td>
			<td id="interestTD5.5" name="interestTD5.5"><hdiits:caption captionid="EL.loanIntRecovInt"
				bundle="${commonLables}" /></td>
			<td id="interestTD5" name="interestTD5" ><hdiits:number name="loanIntRecovInt" tabindex="12" style="text-align:right"
				default="" caption="loanIntRecovInt" validation="txt.isnumber"
				onblur="return chkRecovedAmt()" maxlength="8" size="20" /></td>
			
		</tr>
		<tr>
		
			<td><hdiits:caption captionid="VN.VoucherNo"
				bundle="${commonLables}" /></td>
			<td><hdiits:text name="loanVoucherNo" tabindex="6" default=""
				caption="loanVoucherNo" mandatory="true" size="20" /></td>
				
				<td><hdiits:caption captionid="EL.addintno"
				bundle="${commonLables}" /></td>
			<td><hdiits:number name="loanOddinstno" tabindex="13" default="" style="text-align:right"
				caption="loanOddinstno" validation="txt.isnumber" maxlength="8"
				size="20" /></td>
			<td colspan="2"></td>
			
			
		</tr>
		<tr>
			<td><hdiits:caption captionid="VN.VoucherDate"
				bundle="${commonLables}" /></td>
			<td><hdiits:dateTime captionid="VN.VoucherDate"
				caption="voucherDate" tabindex="7" name="loanVoucherDate"
				mandatory="true" validation="txt.isdt" bundle="${commonLables}"
				minvalue="" /></TD>	
			<td><hdiits:caption captionid="EL.addintamt"
				bundle="${commonLables}" /><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td><hdiits:number name="loanOddinstAmt" tabindex="13" style="text-align:right"
				default="" caption="loanOddinstAmt" validation="txt.isnumber"
				maxlength="8" size="20" /></td>
			<td colspan="2"></td>
			
		</tr>
		<%-- <tr>
			<td><hdiits:caption captionid="VN.LoanType"
				bundle="${commonLables}" /></td>
			<td><hdiits:text name="LoanTypeDesc" default="Select Loan Name" id="LoanTypeDesc"
				caption="LoanType" readonly="true" /></TD>	
			
		</tr>
		
		 ended --%>
	</table>


	<br/><br/>

	<table id="btnAdd" style="" align="center">
		<tr>
			<td class="fieldLabel" align="center" colspan="4"><hdiits:button
				name="add" type="button" caption="Add"
				onclick="addLoanDataToTable();"></hdiits:button></td>
		</tr>
	</table>
	<br/><br/>

	<table id="tblempLoan" border="1" bgcolor="ffffff" bordercolor="aaaaaa"
		align="center">
		<tr>

			<td align="center">Loan Name</td>
			<td align="center">Loan Amt <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td align="center">Loan Inst. No</td>
			<td align="center">Loan EMI Amt <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td align="center">Int. Amt <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td align="center">Int. Inst. No</td>
			<td align="center">Int. EMI Amt <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td align="center">Account No</td>
			<td align="center">Sanction Order No</td>
			<td align="center">Sanction Order Date</td>
			<td align="center">Loan Start Date</td>
			<td align="center">Principal Rcvrd <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td align="center">Principal Rcvrd Inst.</td>
			<td align="center">Interest Rcvrd <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td align="center">Interest Rcvrd Inst.</td>
			<td align="center">Odd Installment No.</td>
			<td align="center">Odd Installment Amount <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
			<td align="center">Loan Voucher No.</td>
			<td align="center">Loan Voucher Date</td>
		</tr>
	</table>

	</font> <br>
	<c:choose>
		<c:when test="${empAllRec eq true }">
			<hdiits:hidden
				default="getLoanValue&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y"
				name="givenurl" />
		</c:when>
		<c:otherwise>
			<hdiits:hidden default="getLoanValue" name="givenurl" />
		</c:otherwise>
	</c:choose> <hdiits:jsField name="validate" jsFunction="validateForm()" /> <fmt:setBundle
		basename="resources.eis.eis_common_lables" var="Lables" scope="page" />
	<jsp:include page="../../core/PayTabnavigation.jsp" /></div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
    //    alert('emp ID is ' + ${empId});
		if('${empId}'!=null && '${empId}'!=0  && "${empAllRec}"!=null && "${empAllRec}"=='true')
   	    {
  // 	      alert("inside if of Loan");
   	      document.getElementById('searchEmpDiv').style.visibility = 'hidden';
		  document.getElementById('Employee_ID_EmpSearch').value = '${empId}';
		//  alert(' ' + document.getElementById("Employee_ID_EmpOtherDtls").value);
//	      alert('emp ID after setting value is ' + ${empId});
	
		}
		else
		{
		
		if("${msg}"!=null&&"${msg}"!='')
		{
					alert("${msg}");
					if("${empId}"!=null && "${empId}"!='' && "${empAllRec}"=='added')
					{
					var url="./hrms.htm?actionFlag=getLoanValue&Employee_ID_EmpLoanSearch=${empId}&empAllRec=Y";
					}
					else if("${FromBasicDtlsNew}"!='YES'){
					var url="./hrms.htm?actionFlag=getLoanValue";
					document.empLoan.action=url;
					document.empLoan.submit();
					}
					else{
						window.opener.location.href="./hrms.htm?actionFlag=getOtherDataMerged&otherId=${otherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES&elementId=9000195";
						 window.close();
					}
		}


		}
		if('${empName}'!= null && '${empName}'!= "")
		{	
				document.empLoan.Employee_srchNameText_EmpSearch.value='${empName}';
				GoToNewPageEmpSearch();
		}

		
		
	</script> <hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

