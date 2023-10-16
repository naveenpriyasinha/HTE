<%
try{
%>

<%@ include file="../../core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>

<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<fmt:setBundle basename="resources.eis.eis_common_lables"   var="enLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}" scope="request"> </fmt:message>

<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>

<script type="text/javascript"	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/leave/DateDifference.js"></script>
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/leavevalidation.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/hrms/ess/leave/DateVal.js"/>"></script>
<script type="text/javascript" src="script/hrms/ess/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/hrms/ess/leave/DateVal.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>


<script type="text/javascript"  src="common/script/tagLibValidation.js"></script>
<script type="text/javascript"  src="common/script/commonfunctions.js"></script>
<script type="text/javascript"	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript"  src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  src="common/script/tagLibValidation.js"></script>
<script type="text/javascript"	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"  src="script/hod/ps/common.js"></script>
<script type="text/javascript"  src="script/common/person.js"></script>
<script type="text/javascript"	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript"	src="<c:url value="/script/hrms/eis/commonUtils.js"/>"></script>



<c:set var="resultObj" value="${result}" > </c:set>	
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	  
<c:set var="lstGrades" value="${resValue.lstGrades}" ></c:set>
<c:set var="lstBills" value="${resValue.lstBills}" ></c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>
<!--  <c:set var="lstEmployees" value="${resValue.lstEmployees}" ></c:set> -->



	
<script type="text/javascript" ><!--
// This Method will fetch all desigantion on the selection of the Grade

var count=0; // Gole
function getDesignations()
{
	
	var v=document.getElementById("desig").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("desig").options.length -1;
			document.getElementById("desig").options[lgth] = null;
	}		
		  xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  url= uri+'&gradeID='+ document.frmMulitpleLoans.empClass.value;
		  var actionf="GetDesignations";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url; 
		  xmlHttp.onreadystatechange=gradeChangedDesigs;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}


function getEmpbyBillNo()
{



var v=document.getElementById("srcEmpId").length;
	for(i=0;i<v;i++)
	{
			lgth = document.getElementById("srcEmpId").options.length -1;
			document.getElementById("srcEmpId").options[lgth] = null;
	}	
var w=document.getElementById("destEmpId").length;
	for(i=0;i<w;i++)
	{
			lgth = document.getElementById("destEmpId").options.length -1;
			document.getElementById("destEmpId").options[lgth] = null;
	}	
	
	
	xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  url= uri+'&billNo='+ document.frmMulitpleLoans.billNo.value+'&loanAdvId='+document.frmMulitpleLoans.loanName.value;
		  var actionf="getEmployeeByBillNo";
		  uri='./hrms.htm?actionFlag='+actionf;
		  url=uri+url;
		  //url= uri+'&gradeID='+ document.frmMulitpleLoans.empClass.value;
		  //var actionf="GetDesignations";
		  //uri='./hrms.htm?actionFlag='+actionf;
		  //url=uri+url;  
		  xmlHttp.onreadystatechange=gradeChangedEmps;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);	
}


function gradeChangedEmps()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var Employees = document.getElementById("srcEmpId");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('Employee-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text = entries[i].childNodes[1].text + ' ' + entries[i].childNodes[2].text + ' ' + entries[i].childNodes[3].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        try
   				        {      				    					
                            Employees.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       Employees.add(y); 
   			   	        }	
	                
	                 }
  }
}


function gradeChangedDesigs()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var Designations = document.getElementById("desig");
			
					var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('Designations-mapping');
					var val=0;
					var text='';
					 for ( var i = 0 ; i < entries.length ; i++ )
	     			 {
     				    val=entries[i].childNodes[0].text;    
     				    text = entries[i].childNodes[1].text; 
     				    var y = document.createElement('option')   
     			        y.value=val;
     			        y.text=text;	
     			        try
   				        {      				    					
                            Designations.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       Designations.add(y); 
   			   	        }	
	                
	                 }
  }
}

// Checking the Recovered Amount

function chkRecovedAmt()
{
	
	var loanPrinRecovAmt=document.frmMulitpleLoans.loanPrinRecovAmt.value;	
	var loanPrinRecovInt=document.frmMulitpleLoans.loanPrinRecovInt.value;
	
	var principalAmt=document.frmMulitpleLoans.principalAmt.value;
	var principalInstNo=document.frmMulitpleLoans.principalInstNo.value;
	
	var loanPrinEmiAmt=document.frmMulitpleLoans.loanPrinEmiAmt.value;
	
	if(principalAmt!= "")
	{
		if(loanPrinRecovAmt != "" && eval(loanPrinRecovAmt)>eval(principalAmt) )
		{
		    alert("Loan PrinciPal Recovered Amount can not be greater than Loan Principal Amount.");
		    document.frmMulitpleLoans.loanPrinRecovAmt.focus();
		    document.frmMulitpleLoans.loanPrinRecovAmt.value="";
	    	return false;
	    }
	}
	else
	{
		if(loanPrinRecovAmt != "")
		{
		    alert("Please Enter the Loan Principal Amount.");
		    document.frmMulitpleLoans.principalAmt.focus();
		    document.frmMulitpleLoans.loanPrinRecovAmt.value="";
	    	return false;
	    }
	}
    if(principalInstNo!="")
    {
	    if(loanPrinRecovInt != "" && eval(loanPrinRecovInt)>eval(principalInstNo) )
	    {
		    alert("Loan PrinciPal Recovered Installsment can not be greater than Loan Principal Installments.");
		    document.frmMulitpleLoans.loanPrinRecovInt.focus();
		    document.frmMulitpleLoans.loanPrinRecovInt.value="";
	    	return false;
	    }
	}
	else
	{
		if(loanPrinRecovInt != "")
		{
		    alert("Please Enter the Loan Principal Installments.");
		    document.frmMulitpleLoans.principalInstNo.focus();
		    document.frmMulitpleLoans.loanPrinRecovInt.value="";
	    	return false;
	    }
	}
	return true;
    
}


// Check for the Negative Number

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

// Added By Urvin Shah.

function deleteAllFromDest(){
	var destObj = document.getElementById('destEmpId');
	var destEmpLgth = document.getElementById('destEmpId').length;
	for(i=destEmpLgth-1;i>=0;i--) {
		destObj.options[i]=null;
	}
	
	
	
	return true;
}


// Function:- This method add selected items from Source Post List to Destination List and remove the Selected Items from the Source Post List.

function moveFromSrcToDest(){
	var destObj = document.getElementById('destEmpId');
	var srcObj = document.getElementById('srcEmpId');
	var srcEmpLgth = document.getElementById('srcEmpId').length;
	var destEmpLgth = document.getElementById('destEmpId').length;
	

	for( i=srcEmpLgth-1; i>=0; i-- ) {
		if(srcObj.options[i].selected) {
			var optDest = document.createElement('option');

		    optDest.value=srcObj.options[i].value;
		    optDest.text=srcObj.options[i].text;
		    //alert('text and value is ' + srcObj.options[i].value + ' ' + srcObj.options[i].text);
		 
		    try {      				    					
                destObj.add(optDest,null);
                
           	}
 			catch(ex) {
   			 	 destObj.add(optDest); 
   			}
   			srcObj.options[i]=null;
		}	
	}
}

// Function:- This method add all items from Source Post List and add them in to Destination List and remove all items from the Source Post List.

function moveAllFromSrcToDest(){
	var destObj = document.getElementById('destEmpId');
	var srcObj = document.getElementById('srcEmpId');
	var srcEmpLgth = document.getElementById('srcEmpId').length;
	var destEmpLgth = document.getElementById('destEmpId').length;
	for(i=0;i<srcEmpLgth;i++) {
		var optDest = document.createElement('option');
	    optDest.value=srcObj.options[i].value;
	    optDest.text=srcObj.options[i].text;
	    
	    
	    try {      				    					
               destObj.add(optDest,null);
          	}
		catch(ex) {
 			destObj.add(optDest); 
 		}
	}
	for(i=srcEmpLgth-1;i>=0;i--) {
		srcObj.options[i]=null;
	}
}

// Function:- This method remove selected items from Destination Post List and add to Source List and add to the Source Post List.

function moveFromDestToSrc(){
	var destObj = document.getElementById('destEmpId');
	var srcObj = document.getElementById('srcEmpId');
	var srcEmpLgth = document.getElementById('srcEmpId').length;
	var destEmpLgth = document.getElementById('destEmpId').length;
	for(i=destEmpLgth-1;i>=0;i--) {
		if(destObj.options[i].selected) {
			var optSrc = document.createElement('option');
		    optSrc.value=destObj.options[i].value;
		    optSrc.text=destObj.options[i].text;
		    try {      				    					
                srcObj.add(optSrc,null);
           	}
 			catch(ex) {
   			 	 srcObj.add(optSrc); 
   			}
   			destObj.options[i]=null;
		}	
	}
}

// Function:- This method remove all items from Destination Post List and add them in to the Source Post List.

function moveAllFromDestToSrc(){
	var destObj = document.getElementById('destEmpId');
	var srcObj = document.getElementById('srcEmpId');
	var srcEmpLgth = document.getElementById('srcEmpId').length;
	var destEmpLgth = document.getElementById('destEmpId').length;
	for(i=0;i<destEmpLgth;i++) {
		var optSrc = document.createElement('option');
	    optSrc.value=destObj.options[i].value;
	    optSrc.text=destObj.options[i].text;
	    try {      				    					
               srcObj.add(optSrc,null);
          	}
		catch(ex) {
 			srcObj.add(optSrc); 
 		}
	}
	for(i=destEmpLgth-1;i>=0;i--) {
		destObj.options[i]=null;
	}
}

// Add  Multiple Records
function addLoanDataToTable(){

	
	var destObj = document.getElementById('destEmpId');
	var destEmpLgth = document.getElementById('destEmpId').length;
	var empId=0;
	var empName="";
	if(document.getElementById("loanName").value == ""){
		    alert("Select the Loan Type");
		    document.frmMulitpleLoans.loanName.focus();
	    	return false;
	    }
	    if(document.frmMulitpleLoans.loanDate.value == ''){
	    	alert("Enter the Loan Date");
	    	document.frmMulitpleLoans.loanDate.focus();
	    	return false;
	}
	if(document.frmMulitpleLoans.principalAmt.value == ''){
	    	alert("Enter the Principal Amount");
	    	document.frmMulitpleLoans.principalAmt.focus();
	    	return false;
	}
	if(document.frmMulitpleLoans.principalInstNo.value == ''){
	    	alert("Enter the Principal Installment No");
	    	document.frmMulitpleLoans.principalInstNo.focus();
	    	return false;
	}
	if(document.frmMulitpleLoans.loanPrinEmiAmt.value == ''){
	    	alert("Enter the Principal EMI Amount");
	    	document.frmMulitpleLoans.loanPrinEmiAmt.focus();
	    	return false;
	}


	
	if(document.frmMulitpleLoans.loanSancOrderDate.value == ''){
    	alert("Enter the Loan Sanction Order Date");
    	document.frmMulitpleLoans.loanSancOrderDate.focus();
    	return false;
	}
	if(document.frmMulitpleLoans.loanVoucherNo.value == ''){
    	alert("Enter the Loan Voucher No");
    	document.frmMulitpleLoans.loanVoucherNo.focus();
    	return false;
	}
	if(document.frmMulitpleLoans.loanVoucherDate.value == ''){
    	alert("Enter the Loan Voucher Date");
    	document.frmMulitpleLoans.loanVoucherDate.focus();
    	return false;
	}

	//Block to validate installments 
		var prinAmt = document.frmMulitpleLoans.principalAmt.value;
		var emiAmt = document.frmMulitpleLoans.loanPrinEmiAmt.value;
		var reqInstal;
		var instNo = document.frmMulitpleLoans.principalInstNo.value;

		if(prinAmt % emiAmt != 0)
		{
			reqInstal = ((prinAmt-(prinAmt%emiAmt))/emiAmt)+1;
		}
		else
		{
			reqInstal=prinAmt/emiAmt;
		}
		if(instNo != reqInstal)
		{
			alert('Please Enter appropriate value of Installment No i.e.'+reqInstal);
			document.frmMulitpleLoans.principalInstNo.focus();
			return false;
		}

		if((emiAmt*instNo)>prinAmt)
		{
			var oddInsAmt=prinAmt-(emiAmt*(instNo-1));
			var oddInsNo=instNo;
		
		//alert('oddInsAmt=  '+oddInsAmt +' oddInsNo= '+oddInsNo );
			var orginalOddInstNo=  document.getElementById("loanOddinstno").value;
		//alert(''+orginalOddInstNo);
			var orignialOddInstAmt=document.getElementById("loanOddinstAmt").value;
		//alert(''+orignialOddInstAmt);
			var box="";
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
			}
		}
		
	//validation completed
	
	
		  
	// Fetching the Common values
	
	var principalAmt=document.frmMulitpleLoans.principalAmt.value;
	var principalInstNo=document.frmMulitpleLoans.principalInstNo.value;
	var loanPrinEmiAmt=document.frmMulitpleLoans.loanPrinEmiAmt.value;
	
	
	if(chkRecovedAmt()==false)
	{
		return false;
	}

    //count = parseInt(document.empLoan.recCounter.value); // Counter for Controls.
    
	

	var loanName=document.frmMulitpleLoans.loanName.value;
	var loanDisplayName=document.frmMulitpleLoans.loanName.options(frmMulitpleLoans.loanName.selectedIndex).text;
	var loanSancOrderNo=document.frmMulitpleLoans.loanSancOrderNo.value;	
	var loanDate=document.frmMulitpleLoans.loanDate.value;
	var loanPrinRecovAmt=document.frmMulitpleLoans.loanPrinRecovAmt.value;	
	var loanPrinRecovInt=document.frmMulitpleLoans.loanPrinRecovInt.value;

	
	var loanACNo = document.frmMulitpleLoans.loanACNo.value;
	var loanVoucherNo = document.frmMulitpleLoans.loanVoucherNo.value;
	var loanVoucherDate  = document.frmMulitpleLoans.loanVoucherDate.value;
	var loanSancOrderDate = document.frmMulitpleLoans.loanSancOrderDate.value;

	var loanOddinstno = document.frmMulitpleLoans.loanOddinstno.value;
	var loanOddinstAmt = document.frmMulitpleLoans.loanOddinstAmt.value;

	
	
	// Inserting Cells
	
	
	
	for(i=0;i<destEmpLgth;i++) {
		var optSrc = document.createElement('option');
	if(optSrc!=null) {		
	    //alert(destObj.options[i].value);
		//alert(destObj.options[i].text);
		empId=destObj.options[i].value;
		empName=destObj.options[i].text;
		
		
		var trow=document.getElementById('tblempLoan').insertRow();
		trow.align="center";
	
	
		
		
		var tdEmployeeName=trow.insertCell(0);
		var tdloanName=trow.insertCell(1);
		var tdprincipalAmt=trow.insertCell(2);
		var tdprincipalInstNo=trow.insertCell(3);
		var tdloanPrinEmiAmt=trow.insertCell(4);
		var tdloanSancOrderNo=trow.insertCell(5);
		var tdloanDate=trow.insertCell(6);
		var tdloanPrinRecovAmt=trow.insertCell(7);
		var tdloanPrinRecovInt=trow.insertCell(8);

		var tdloanACNo=trow.insertCell(9);
		var tdloanSancOrderDate=trow.insertCell(10);
		var tdloanVoucherNo=trow.insertCell(11);
		var tdloanVoucherDate=trow.insertCell(12);

		var tdloanOddinstno=trow.insertCell(13);
		var tdloanOddinstAmt=trow.insertCell(14);

		
		var isDelete=trow.insertCell(15);
		
		
		tdEmployeeName.innerHTML='<input type="text" style="width:100%" name="empName'+count+'" value="'+empName+'" readonly="true"><input type="hidden" name="empId'+count+'" value="'+empId+'">';
		tdloanName.innerHTML='<input type="text" style="width:100%" name="loanDisplayName'+count+'" value="'+loanDisplayName+'" readonly="true"><input type="hidden" name="loanName'+count+'" value="'+loanName+'">';
		tdprincipalAmt.innerHTML='<input type="text" style="width:100%" name="principalAmt'+count+'" value="'+principalAmt+'" readonly="false">';
		tdprincipalInstNo.innerHTML='<input type="text" style="width:100%" name="principalInstNo'+count+'" value="'+principalInstNo+'" readonly="false">';
		tdloanPrinEmiAmt.innerHTML='<input type="text" style="width:100%" name="loanPrinEmiAmt'+count+'" value="'+loanPrinEmiAmt+'" readonly="false">';
		tdloanSancOrderNo.innerHTML='<input type="text" style="width:100%" name="loanSancOrderNo'+count+'" value="'+loanSancOrderNo+'" readonly="false">';
		tdloanDate.innerHTML='<input type="text" style="width:100%" name="loanDate'+count+'" value="'+loanDate+'" readonly="false">';
		tdloanPrinRecovAmt.innerHTML='<input type="text" style="width:100%" name="loanPrinRecovAmt'+count+'" value="'+loanPrinRecovAmt+'" readonly="false">';
		tdloanPrinRecovInt.innerHTML='<input type="text" style="width:100%" name="loanPrinRecovInt'+count+'" value="'+loanPrinRecovInt+'" readonly="false">';

		tdloanACNo.innerHTML='<input type="text" style="width:100%" name="loanACNo'+count+'" value="'+loanACNo+'" readonly="false">';
		tdloanSancOrderDate.innerHTML='<input type="text" style="width:100%" name="loanSancOrderDate'+count+'" value="'+loanSancOrderDate+'" readonly="false">';
		tdloanVoucherNo.innerHTML='<input type="text" style="width:100%" name="loanVoucherNo'+count+'" value="'+loanVoucherNo+'" readonly="false">';
		tdloanVoucherDate.innerHTML='<input type="text" style="width:100%" name="loanVoucherDate'+count+'" value="'+loanVoucherDate+'" readonly="false">';

		tdloanOddinstno.innerHTML='<input type="text" style="width:100%" name="loanOddinstno'+count+'" value="'+loanOddinstno+'" readonly="false">';
		tdloanOddinstAmt.innerHTML='<input type="text" style="width:100%" name="loanOddinstAmt'+count+'" value="'+loanOddinstAmt+'" readonly="false">';
		
	   	isDelete.innerHTML='<input type="button" name="delete'+count+'" style="width:100%" value="Delete" onclick="return deleteRecord(this)">';
	   	count=count+1;
	    //alert("The value of the count is the :-"+count);
		}	
	}
	    
	//document.empLoan.recCounter.value=parseInt(document.empLoan.recCounter.value)+1;
	document.frmMulitpleLoans.recCounter.value=count;
	resetFields();
	return true;
}



// Resetting the Loan records.

function resetFields(){
    
	//document.frmMulitpleLoans.loanName.value='';
	document.frmMulitpleLoans.principalAmt.value='';
	document.frmMulitpleLoans.principalInstNo.value='';
	document.frmMulitpleLoans.loanPrinEmiAmt.value='';
	document.frmMulitpleLoans.loanSancOrderNo.value='';
	document.frmMulitpleLoans.loanDate.value='';
	document.frmMulitpleLoans.loanPrinRecovAmt.value='';
	document.frmMulitpleLoans.loanPrinRecovInt.value='';
	document.frmMulitpleLoans.loanACNo.value='';
	document.frmMulitpleLoans.loanVoucherNo.value='';
	document.frmMulitpleLoans.loanVoucherDate.value='';
	document.frmMulitpleLoans.loanSancOrderDate.value='';
	document.frmMulitpleLoans.loanOddinstno.value='';
	document.frmMulitpleLoans.loanOddinstAmt.value='';
	document.frmMulitpleLoans.loanACNo.value = '';
}



// Delete Records from the Multiple Records

function deleteRecord(currentRecord){

    count=count-1;
    //var recCount=parseInt(document.frmMulitpleLoans.recCounter.value);
    document.getElementById('tblempLoan').deleteRow(currentRecord.parentNode.parentNode.rowIndex);
    //recCount=recCount-1;
    //document.frmMulitpleLoans.recCounter.value = recCount;
    document.frmMulitpleLoans.recCounter.value = count;
     for(var i=1; i<=count; i++)
          {
            var cell1 = document.getElementById('tblempLoan').cells(((i*15)+0));
            var cell2 = document.getElementById('tblempLoan').cells(((i*15)+1));
            var cell3 = document.getElementById('tblempLoan').cells(((i*15)+2));
            var cell4 = document.getElementById('tblempLoan').cells(((i*15)+3));
            var cell5 = document.getElementById('tblempLoan').cells(((i*15)+4));
            var cell6 = document.getElementById('tblempLoan').cells(((i*15)+5));
            var cell7 = document.getElementById('tblempLoan').cells(((i*15)+6));
            var cell8 = document.getElementById('tblempLoan').cells(((i*15)+7));
            var cell9 = document.getElementById('tblempLoan').cells(((i*15)+8));

            var cell10 = document.getElementById('tblempLoan').cells(((i*15)+9));
            var cell11 = document.getElementById('tblempLoan').cells(((i*15)+10));
            var cell12 = document.getElementById('tblempLoan').cells(((i*15)+11));
            var cell13 = document.getElementById('tblempLoan').cells(((i*15)+12));

            var cell14 = document.getElementById('tblempLoan').cells(((i*15)+13));
            var cell15 = document.getElementById('tblempLoan').cells(((i*15)+14));
           
            var cell16 = document.getElementById('tblempLoan').cells(((i*15)+15));
          
            
            cell1.children[0].name = "empName"+(i-1);
            cell1.children[1].name = "empId"+(i-1);
            cell2.children[0].name = "loanDisplayName"+(i-1);
            cell2.children[1].name = "loanName"+(i-1);
            cell3.children[0].name = "principalAmt"+(i-1);
            cell4.children[0].name = "principalInstNo"+(i-1);
            cell5.children[0].name = "loanPrinEmiAmt"+(i-1);          
    		cell6.children[0].name = "loanSancOrderNo"+(i-1);
            cell7.children[0].name = "loanDate"+(i-1);
            cell8.children[0].name = "loanPrinRecovAmt"+(i-1);
            cell9.children[0].name = "loanPrinRecovInt"+(i-1);

            cell10.children[0].name = "loanACNo"+(i-1);
            cell11.children[0].name = "loanSancOrderDate"+(i-1);
            cell12.children[0].name = "loanVoucherNo"+(i-1);
            cell13.children[0].name = "loanVoucherDate"+(i-1);

            cell14.children[0].name = "loanOddinstno"+(i-1);
            cell15.children[0].name = "loanOddinstAmt"+(i-1);
            
            cell16.children[0].name = "delete"+(i-1);
          }
}
function callActionMultiAddFood()
{
		//	var empName = document.getElementById("Employee_ID_EmpSearch").value;
	//	document.frmMulitpleLoans.empName.value=empName;
		//alert('Inside Submit Emp Name');
		document.frmMulitpleLoans.action="./hrms.htm?actionFlag=insertMultipleLoanFood&edit=N";
		document.frmMulitpleLoans.submit();
}

function validateForm()
{	
	return true;
}




--></script>
<body>

 <hdiits:form name="frmMulitpleLoans" action="javascript:callActionMultiAddFood()" validate="true" method="POST"	encType="text/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="EL.loanView" bundle="${enLables}"/></b></a></li>
		</ul>
	</div>
	<div style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin"  id="tcontent1">
			<hdiits:jsField  name="validate" jsFunction="validateForm()" /> 
				<table width="90%" align="center">
					<tr >
						<td width="10%" align="left">
							<b> <fmt:message key="BHM.billNo" bundle="${enLables}"/></b>
						</td>
						<td width="25%" >
							<hdiits:select name="billNo" id="billNo"  sort="false" size="" onchange="getEmpbyBillNo()" style="width:200px" tabindex="0"> 
								<option value="-1">--------Select----------</option>
									<c:forEach var="lstBills" items="${lstBills}">
										<option value='<c:out value="${lstBills.billHeadId}"/>' title="${lstBills.billId}"  >
											<c:out value="${lstBills.billId}"/></option>
									</c:forEach>
							</hdiits:select>
		 				</td>
						<td width="20%"></td>
						<TD align="left" width="10%" >
		  					<b> <fmt:message key="EL.loanName" bundle="${enLables}"/></b>
						</TD>		
						<TD align="left" width="25%"><b>
							<hdiits:select style="" captionid="EL.loanName" bundle="${enLables}" id="loanName" mandatory="true" name="loanName" size ="1" onchange="getEmpbyBillNo()">
								<!-- 	<option value="30" >FoodGrain Advance</option>	 -->
								<option value="59" >Festival Advance</option>
							</hdiits:select></b>
						</TD>
					</tr>
					<TR>
						<TD width="10%" align="left">
							<b>   <fmt:message key="employeeMaster" bundle="${enLables}" 	/></b>
						</TD>			
		
						<TD width="25%">
							<hdiits:select style="width:100%" captionid="employeeMaster" bundle="${enLables}" id="srcEmpId" name="srcEmpId"  multiple="true" size ="5">
								<!--  	<c:forEach var="lstEmployees" items="${lstEmployees}">
								<option value='<c:out value="${lstEmployees.empId}"/>' >
								${lstEmployees.orgEmpMst.empFname} ${lstEmployees.orgEmpMst.empMname} ${lstEmployees.orgEmpMst.empLname}
								</option>
								</c:forEach> -->
							</hdiits:select>

				
						</TD>
						<td width="20%"> 			
							<table align="center">
								<tr>
									<td>
										<hdiits:button name="addFromSrvToDest" type="button" captionid="eis.addFromSrcToDest" bundle="${enLables}" onclick="moveFromSrcToDest()"/>
									</td>
								</tr>
								<tr>
									<td>
										<hdiits:button name="addMultiFromSrcToDest" type="button" captionid="eis.addMultiFromSrcToDest" bundle="${enLables}" onclick="moveAllFromSrcToDest()"/>
									</td>
								</tr>
								<tr>
									<td>
										<hdiits:button name="addFromDestToSrc" type="button" captionid="eis.addFromDestToSrc" bundle="${enLables}" onclick="moveFromDestToSrc()"/>
									</td>
								</tr>
								<tr>
									<td>
										<hdiits:button name="addMultiFromDestToSrc" type="button" captionid="eis.addMultiFromDestToSrc" bundle="${enLables}" onclick="moveAllFromDestToSrc()"/>
									</td>	
								</tr>
							</table>
						</td>
						<td width="10%"></td>
						<TD width="25%">
							<select style="width:100%" id="destEmpId" mandatory="true" name="destEmpId" multiple="true" size ="5">
							</select>
		  				</TD>			
					</tr>
	
					<tr>
						<td width="10%">
							<b> <fmt:message key="EL.loanStartDate" bundle="${enLables}"/></b>
						</td>
						<td width="25%">
							<hdiits:dateTime captionid="EL.loanStartDate" caption="loanDate" tabindex="1" name="loanDate" mandatory="true" validation="txt.isdt" bundle="${enLables}" />
						</td>
						<td width="20%"> </td>
						<td width="10%">				</td>
						<td width="25%">		</td>
					</tr>
	
				<tr>
					<td width="15%"> <b><hdiits:caption captionid="EL.loanACNo" bundle="${enLables}"/></b></td>
					<td width="25%"><hdiits:text tabindex="2" name="loanACNo" default="" caption="loanACNo"    size="20" maxlength="25"/> </td>
					<td width="20%"> </td>
					<td width="15%"><b><hdiits:caption captionid="EL.lpAmt" bundle="${enLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
    				<td width="25%"><hdiits:number name="principalAmt" tabindex="3" default="" style="text-align:right" caption="principalAmt" mandatory="true" onblur="return chknumberforNegative(this);" validation="txt.isnumber" maxlength="10"   size="20"  /> </td>
				</tr>
				<tr>
					<td width="15%"><b><hdiits:caption captionid="EL.lpiNo" bundle="${enLables}"/></b></td>
					<td width="25%"><hdiits:number name="principalInstNo" tabindex="4" default="" caption="principalInstNo"  mandatory="true" onblur="return chknumberforNegative(this);"  validation="txt.isnumber" maxlength="3"   size="20" /> </td>
					<td width="20%"> </td>
					<td width="15%"><b><hdiits:caption captionid="EL.loanPrincipalEmiAmt" bundle="${enLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
					<td width="25%"><hdiits:number name="loanPrinEmiAmt" tabindex="5" default=""  style="text-align:right" caption="loanPrinEmiAmt" mandatory="true" validation="txt.isnumber"  onblur="return chknumberforNegative(this);"  maxlength="8" size="20" /> </td>
				</tr>
				<tr>
					<td width="15%"><b><hdiits:caption captionid="EL.loanPrinRecovAmt"  bundle="${enLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
					<td width="25%"><hdiits:number name="loanPrinRecovAmt" tabindex="6" default=""  style="text-align:right" caption="loanPrinRecovAmt"  validation="txt.isnumber"  onblur="return chkRecovedAmt()" maxlength="8" size="20" /> </td>
					<td width="20%"> </td>
					<td width="15%"><b><hdiits:caption captionid="EL.loanPrinRecovInt"  bundle="${enLables}"/></b></td>
					<td width="25%"><hdiits:number name="loanPrinRecovInt" tabindex="7" default=""  caption="loanPrinRecovInt"  validation="txt.isnumber" onblur="return chkRecovedAmt()"  maxlength="8" size="20" /> </td>
				</tr>
				<tr>
					<td width="15%"> <b><hdiits:caption captionid="EL.loanSancOrderNo" bundle="${enLables}"/></b></td>
					<td width="25%"><hdiits:text tabindex="8" name="loanSancOrderNo" default="" caption="Loan Sanction Order No"    size="20" maxlength="25"/> </td>
					<td width="20%"> </td>
					<td width="15%"><b><hdiits:caption captionid="EL.loanSancOrderDate"  bundle="${enLables}"/></b></td>
					<td width="25%"><hdiits:dateTime captionid="EL.loanSancOrderDate" caption="loanSancOrderDate"  name="loanSancOrderDate"  mandatory="true" default=""
									validation="txt.isdt" bundle="${enLables}" minvalue="" tabindex="9"/></td>
				</tr>
				<tr>
					<td width="15%"> <b><hdiits:caption captionid="VN.VoucherNo" bundle="${enLables}"/></b></td>
					<td width="25%"><hdiits:text tabindex="10" name="loanVoucherNo" default="" caption="loanVoucherNo"  size="20" maxlength="25" mandatory="true" /> </td>
					<td width="20%"> </td>
					<td width="15%"><b><hdiits:caption captionid="VN.VoucherDate"  bundle="${enLables}"/></b></td>
					<td width="25%"><hdiits:dateTime captionid="VN.VoucherDate" caption="voucherDate"  name="loanVoucherDate"  mandatory="true" default="" 
									validation="txt.isdt" bundle="${enLables}" minvalue="" tabindex="11"/></td>
				</tr>
				<tr>
					<td width="15%"><b><hdiits:caption captionid="EL.addintno"  bundle="${enLables}"/></b></td>
					<td width="25%"><hdiits:number name="loanOddinstno" tabindex="12" default=""  style="text-align:right" caption="loanOddinstno"  validation="txt.isnumber"   maxlength="8" size="20" /> </td>
					<td width="20%"> </td>
					<td width="15%"><b><hdiits:caption captionid="EL.addintamt"  bundle="${enLables}"/></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
					<td width="25%"><hdiits:number name="loanOddinstAmt" tabindex="13" default=""  caption="loanOddinstAmt"  validation="txt.isnumber"  maxlength="8" size="20" /> </td>
				</tr>
				
			</table>
			
			
			
				<br><br><hdiits:hidden name="recCounter" id="recCounter" default="0"/>
				<hdiits:hidden name="loanActivateFlag" default="1"/>
			<table id="btnAdd" style="" align="center">
				<tr>
					<td class="fieldLabel" align="center" colspan="4">
						<hdiits:button  name="add"  type="button"  caption="Add" onclick="addLoanDataToTable()" tabindex="14"></hdiits:button>
					</td>
				</tr>
			</table>
			<br><br>
				  <table id="tblempLoan" border="1" bgcolor="ffffff" bordercolor="aaaaaa" align="center">
 					<tr>
 						<td align="center" width="">Employee Name</td>
 						<td align="center" width="">Loan Name</td>
 						<td align="center" width="">Loan Amt <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
 						<td align="center" width="">Loan Inst. No</td>
 						<td align="center" width="">Loan EMI Amt <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
 						<td align="center" width="">Sanction Order No</td> 				
 						<td align="center" width="">Loan Date</td> 	
 						<td align="center" width="">Principal Rcvrd <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td> 	
 						<td align="center" width="">Principal Rcvrd Inst.</td> 	
 						<td align="center" width="">Loan Acct. No</td>
 						<td align="center" width="">Sanction Order Date</td> 	
 						<td align="center" width="">Voucher No</td> 	
 						<td align="center" width="">Voucher Date</td>
 						<td align="center" width="">Odd Inst. No</td>
 						<td align="center" width="">Odd Inst. Amount <Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</Font></td>
 					</tr>
 				</table>
 		
 				<jsp:include page="../../core/PayTabnavigation.jsp" /> 	
 		
 	</div>
 		<script type="text/javascript">
			deleteAllFromDest();
			
			//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
			initializetabcontent("maintab");
		
		</script>
 		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>
</hdiits:form>
  
  
	
		

 </body> 
  	  <%
}
  	  catch(Exception e)
  	  {
  		  //System.out.println("There is some error:-");
  		  e.printStackTrace();
  	  }
  	  %>
  	  