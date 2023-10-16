<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page import ="com.tcs.sgv.eis.valueobject.HrEisEmpMst, 
				  java.text.SimpleDateFormat" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>


<script type="text/javascript" src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
	

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" 	var="commonLables" scope="page"/>
<fmt:setBundle basename="resources.Payroll" 				var="constantVariables" scope="request"/>


<fmt:message var="contractEmpType" 	key="contract" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="fixedEmpType" 	key="fixed" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="probationEmpType" key="probation" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="pageSize" 		key="pageSize" 	bundle="${constantVariables}" scope="request"> </fmt:message>


<%
long empId=0;	
long otherId=0;

	//System.out.println("the test emp id from jsp is==>"+request.getParameter("empId")+" manoj");

	if(request.getParameter("empId")!=null && !request.getParameter("empId").equals("")&& !request.getParameter("empId").equals(" "))
		empId = Long.valueOf(request.getParameter("empId").toString());

	//System.out.println("the test other id  from jsp is==>"+request.getParameter("otherId")+" manoj");
	
	if(request.getParameter("otherId")!=null && !request.getParameter("otherId").equals("") && !request.getParameter("otherId").equals(" "))
		otherId = Long.valueOf(request.getParameter("otherId").toString());

	String empAllRec="false";
  	if(request.getParameter("empAllRec")!=null && !request.getParameter("empAllRec").equals("") && !request.getParameter("empAllRec").equals(""))
		empAllRec=request.getParameter("empAllRec").toString();

  
  pageContext.setAttribute("empId",empId);
  pageContext.setAttribute("otherId",otherId);
  pageContext.setAttribute("empAllRec",empAllRec);
  
  //System.out.println("the empall rec from jsp is==>"+empAllRec);
%>

<c:set var="resultObj" 			value="${result}" > </c:set>
<c:set var="resValue" 			value="${resultObj.resultValue}" > </c:set>	
<c:set var="allowList" 			value="${resValue.allowList}" ></c:set>
<c:set var="allowNamesFromMpg" 	value="${resValue.allowNamesFromMpg}" ></c:set>
<c:set var="empList" 			value="${resValue.empList}" ></c:set>
<c:set var="allowMpgSize" 		value="${resValue.allowMpgSize}" > </c:set>
<c:set var="hrEisMst" 			value="${resValue.hrEisMst}" > </c:set>
<c:set var="allowNamesFromExpr" value="${resValue.allowNamesFromExpr}" > </c:set>
<c:set var="hrEisOtherDtls" 	value="${resValue.hrEisOtherDtls}" > </c:set>
<c:set var="msg" 				value="${resValue.msg}" ></c:set>
<c:set var="otherList" 			value="${resValue.dataList}" ></c:set>
<c:set var="gpfAccoungFlag" 	value="${resValue.gpfAccoungFlag}" ></c:set>
<c:set var="gpfFlagForZeroCheck" value="${resValue.gpfFlagForZeroCheck}" ></c:set>
<c:if test="${otherId eq 0}">
	<c:set var="otherId" 		value="${resValue.otherId}" ></c:set>
</c:if>
<c:set var="deducActionList" 	value="${resValue.deducActionList}" ></c:set>  
<c:set var="otherDtlsLoan" 		value="${resValue.otherDtlsLoan}"> </c:set>
<c:set var="loanList" 			value="${resValue.loanList}" ></c:set> 
<c:set var="deducNamesFromMpg" 	value="${resValue.deducNamesFromMpg}" ></c:set>
<!-- <c:set var="empList" 		value="${resValue.empList}" ></c:set>  -->
<c:set var="deducMpgSize" 		value="${resValue.deducMpgSize}" > </c:set>
<c:set var="deducNamesFromExpr" value="${resValue.deducNamesFromExpr}" > </c:set>
<!-- <c:set var="msg" 			value="${resValue.msg}" ></c:set>  -->
<!--  <c:set var="hrEisMst" 	value="${resValue.hrEisMst}" > </c:set> -->
<!-- <c:set var="otherList" 	value="${resValue.dataList}" > </c:set>  -->
<c:set var="incometax" 			value="${resValue.incometax}" > </c:set>
<c:set var="miscRecov" 			value="${resValue.miscRecov}" > </c:set>
<c:set var="pt" 				value="${resValue.pt}" > </c:set>
<c:set var="vpfAmt" 			value="${resValue.vpfAmt}" > </c:set>


<!-- Added for disable deduction -->
<fmt:setBundle basename="resources.Payroll" var="constantVariables" scope="request"/>
<fmt:message var="ptId" 	key="ptId" 		bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="vpfId" 	key="vpfId" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="cpfId" 	key="cpfId" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="surItId" 	key="surItId" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="GpfId" 	key="GpfId" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="pliId" 	key="pliId" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="itId" 	key="itId" 		bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="GpfIdIV" 	key="GpfIdIV" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="PF" 		key="PF" 		bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="JeepRent" key="JeepRent" 	bundle="${constantVariables}" scope="request"> </fmt:message>


<fmt:message var="sisSfId" key="sisSfId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="sisIfId" key="sisIfId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="aisSfId" key="aisSfId" bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="aisIfId" key="aisIfId" bundle="${constantVariables}" scope="request"> </fmt:message>

<fmt:message var="contract" key="contract" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<fmt:message var="fixed" 	key="fixed" 	bundle="${constantVariables}" scope="request"> </fmt:message>
<!-- End -->

<script><!--

var status="";
var size=0;
function showEmpName()
{
 document.getElementById('allowance').style.display='none';
 //resetMainFormCombo();
 childWindow = window.open("hrms.htm?actionFlag=getEmpData&EmpMpg=Y","EmployeeNames","toolbar=no, location=no, directories=no,status=no, menubar=no, scrollbars=yes, resizable=yes, resize = no, width = 800, height = 550, copyhistory=no, top=80,left=80");
 if (childWindow.opener == null) childWindow.opener = self;
}

 function onclosefn()
  {
		
	if('${empAllRec}'=='true')
		window.location="./hrms.htm?actionFlag=getOtherData&Employee_ID_EmpOtherSearch=${empId}&empAllRec=true";
	else
		window.location="./hrms.htm?actionFlag=getOtherData";
	//document.forms[0].action="./hrms.htm?actionFlag=getOtherData";
	//document.forms[0].submit();
	//history.go(-1);return false;
  }

function insertEmpAllowMpg1()
{
  //alert('function called');
  //document.forms[0].btnsubmit.disabled=true;
  status=document.forms[0].status.value;
   	  	
  if(status== 'Add')
  {
   // var empid = document.insEmpMpg.Employee_ID_EmpSearch.value;
    url = "hrms.htm?actionFlag=insertEmpAllowMpg&batchupdate=true&edit=N&size="+size+"&txtEmpId=" + ${empId} + "&otherId=" + ${otherId};
  }
  else //if(status== 'update')
  {
	    //var empid = document.getElementById("Employee_ID_EmpSearch").value;
		
	if('${empAllRec}'=='true' || '${empAllRec}'=='Y' ){
	   if(confirm("Are you sure to update records"))
           url = "hrms.htm?actionFlag=insertEmpAllowMpg&batchupdate=true&edit=Y&size="+size+"&txtEmpId=" + ${empId} + "&otherId=" + ${otherId}+'&empAllRec=Y';
	   else
		   return false;
	}   
    else {
    	if(document.forms[0].updatePaybillFlg.value!=null&&document.forms[0].updatePaybillFlg.value=='y') 
         var r=confirm("Are you sure to update records and update paybill as well?");
    	else
    	 var r=confirm("Are you sure to update records?");
       var updatePaybillFlg=document.forms[0].updatePaybillFlg.value;
       var paybillMonth=document.forms[0].paybillMonth.value;
       var paybillYear=document.forms[0].paybillYear.value;
       if (r==true){
         url = "hrms.htm?actionFlag=insertEmpAllowMpg&batchupdate=true&edit=Y&size="+size+"&updatePaybillFlg="+updatePaybillFlg+"&paybillMonth="+paybillMonth+"&paybillYear="+paybillYear+"&empId="+${empId}+"&txtEmpId=" + ${empId} + "&otherId=" + ${otherId}+'&empAllRec=N';
       }
       else
	     return false;
    }     
 }
 	document.forms[0].action = url;
 	document.forms[0].submit();
 	  
}

function showDeductions()
{ 
 
//    var empid = document.getElementById("Employee_ID_EmpSearch").value;
    /*if(document.insEmpMpg.cstrStatus.value=="2")
    {*/
    url = "hrms.htm?actionFlag=getDeducType&EmpDeducDtls=Y&EmpId=" + ${empId};
  	document.insEmpMpg.action = url;
    document.insEmpMpg.submit();
    //}
   
} 
var grossAmount = 0;
var totalDeduc = 0;
//alert('1.totalDeduc = '+totalDeduc);
//function which shows values after insert.
function checkComboStates(allow_code,val,amount)
{
//alert('in checkstate funct..' + val + 'amount ' + amount + ' ' + document.forms[0].tax_name.length);

 for(i=0;i<document.forms[0].tax_name.length;i++)
  if(document.forms[0].tax_name[i].value == val)
   {
     //document.forms[0].tax_name[i].checked = true;     
     //document.forms[0].tax_name[i].disabled = true;
      document.forms[0].tax_name[i].style.display = 'none';
      var txtname = 'txt' + val; 
      var txthidden = 'txthidden' + val; 
      var txtIsChecked = 'txtIsChecked' + val;
      var allowcodeId = 'txtAllowCode' + val;
//      alert('textbox name ' + txtname);
//      alert('value of textbox is ' + document.getElementById(txtname).value);
     document.getElementById(txtname).value = amount;
     document.getElementById(txthidden).value = amount;
     document.getElementById(txtIsChecked).value="1";
     document.getElementById(txtname).style.display = '';
      //document.getElementById(txtname).style.backgroundColor= '#E5F0F2';
     //document.getElementById(txtname).disabled=true;
/*     var btnAdd = 'btn_add' + val; 
     document.getElementById(btnAdd).style.display = 'none';
     document.getElementById(btnAdd).value='Edit';*/
     document.getElementById(allowcodeId).value=allow_code;

grossAmount = eval(grossAmount)+eval(amount);

   }
   grossAmount=eval(grossAmount);
  
}

function checkComboStatesDefault(allow_code,val,amount)
{
//alert('in checkComboStatesDefault main page funct..' + val + 'amount ' + amount);


 for(i=0;i<document.forms[0].tax_name.length;i++)
  if(document.forms[0].tax_name[i].value == val)
   {
     //document.forms[0].tax_name[i].checked = true;     
     //document.forms[0].tax_name[i].disabled = true;
      document.forms[0].tax_name[i].style.display = 'none';
      var txtname = 'txt' + val; 
      var txthidden = 'txthidden' + val; 
      var txtIsChecked = 'txtIsChecked' + val;
      var allowcodeId = 'txtAllowCode' + val;
  //          alert('textbox name ' + txtname);
//      alert('value of textbox is ' + document.getElementById(txtname).value);

     document.getElementById(txtname).value = amount;
     document.getElementById(txthidden).value = '-1';
     
     document.getElementById(txtIsChecked).value="1";
     document.getElementById(txtIsChecked).style.display = 'none';

     document.getElementById(txtname).style.display = '';
     //document.getElementById(txtname).disabled=true;
     	document.getElementById(txtname).readOnly=true;
document.getElementById(txtname).style.backgroundColor = "#E5F0F2";
  /*   var btnAdd = 'btn_add' + val; 
     document.getElementById(btnAdd).style.display = 'none';
     document.getElementById(btnAdd).value='Edit';*/
     document.getElementById(allowcodeId).value=allow_code;
   }

}



function chkValue()
{
	
	var empId=${empId};
	if(empId=="")
	{
		alert("Please search the employee first");
	}
	else
	{

//		document.getElementById("Employee_ID_EmpSearch").value=empId;
		document.getElementById("Emp_allow").value='y';
		
		addOrUpdateRecord('ChkEmp', 'chkEmpDetail', new Array('Employee_ID_EmpSearch','Emp_allow'));
		
		//document.getElementById("Employee_ID_EmpSearch").value='';
		document.getElementById("Emp_allow").value='';
	}	
}

function ChkEmp()
{
	var empId=${empId};
	if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {			
				
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
               
				var empMapping = XMLDocForAjax.getElementsByTagName('emp-mapping');	
					
					if(empMapping.length != 0) {	
							var emp = empMapping[0].childNodes[0].text;		
							//added by samir for contractual and fixe scale employee
							var emptype = empMapping[0].childNodes[1].text;		
//							if(emptype==300225 || emptype==300018)
							if(emptype=="{fixedEmpType}" || emptype=="{contractEmpType}")
							{
							//clearEmployee("EmpAllowSearch");
							alert("Not Accessible For Fixed and Contractual Employee!!");
							return;
							}
							//end
								if(emp<0)
								{
									alert("The Employee information is not entered in the system.");
									return false;
								}
								if(emp=="n")
								{
									alert("The Employee other Detail is not entered in the System.");
									return false;
								}
								
								if(emp=="y")
								{
									url = "hrms.htm?actionFlag=getAllwType&EmpAllowMpg=Y&EmpId=" + empId;
									window.location=url;
								}
							}
								
			}
		}
}
function chknumber1(number1)
{
        name1='txt'+number1;
		var field_value=document.getElementById(name1).value;
        var periodpos="";
		var atpos="";
        var rule_num="0123456789";
		if(field_value!="")
		{
		
		for(var i=0;i<field_value.length;i++)
		{
        var cchar=field_value.charAt(i);
        if(rule_num.indexOf(cchar)==-1)
        {
          alert("Enter Valid amount");
          document.getElementById(name1).value="";
          document.getElementById(name1).focus();
          return false;
        }
        }
        } 
}

//Added by Mrugesh
var cpfamt=0;
//function which shows values after insert.
function checkComboStates1(allow_code,val,amount,status,vpfAmt)
{
//alert('ankit...length is' + document.forms[0].tax_name1.length);
 for(i=0;i<document.forms[0].tax_name1.length;i++)
 {

	if(document.forms[0].tax_name1[i].value == val)
   	{
    	//document.forms[0].tax_name[i].checked = true;     
     	//document.forms[0].tax_name[i].disabled = true;
     
      	var txtname1 = 'txtt' + val; 
      	var txtIsChecked1 = 'txtIsChecked' + val;
      	var allowcodeId1 = 'txtDeducCode' + val;
		//alert('textbox name ' + txtname);
		//alert('value of textbox is ' + opener.document.getElementById(txtname).value);

		if(status==0)
	 	{
	 		var temp;
		 	//alert(amount);
		 	//alert(vpfAmt);
		 	/*if(amount>vpfAmt)
		 		temp=amount-vpfAmt;
		 	else
		 		temp=vpfAmt-amount;
		 	totalDeduc=totalDeduc+temp;*/
		 	
	 		if(val=="${vpfId}")
			{
		 		document.getElementById("gpfAmt").value=amount;
				amount=vpfAmt;
				document.getElementById(txtname1).readOnly=false;
				document.getElementById(txtname1).style.backgroundColor="#ffffff";
				document.getElementById("pfType").value="VPF";
				document.getElementById("pfId").value="${vpfId}";
	 		}
	
			document.getElementById("gpfAmt").value=amount;
			amount=vpfAmt;
	 
			document.getElementById(txtname1).readOnly=false;
			document.getElementById(txtname1).style.backgroundColor="#ffffff";
			document.getElementById("pfType").value="VPF";
			document.getElementById("pfId").value="${vpfId}";
	  
		}
	 	else
	 	{
	 		if(val=="${PF}")
	 		{
	 		
	 		 
				document.getElementById("pfType").value="GPF";
				document.getElementById("span${PF}").innerHTML="<b>(GPF)</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				document.getElementById("pfId").value=val;
	 			document.getElementById("gpfAmt").value=amount;
	 				amount=eval(amount)+eval(cpfamt);
	 			document.getElementById(txtname1).disabled=false;
	 			document.getElementById(txtname1).style.backgroundColor="#ffffff";
	  		}
	 	}
 
 		if(val=="${ptId}")
 			amount=0;
 	
		if(val!="${itId}")
			totalDeduc=totalDeduc+amount;
      
			//alert('2.totalDeduc = '+totalDeduc);
	 
	 
			document.getElementById(txtname1).value = amount;
	     	//document.getElementById(txtIsChecked1).value="1";
	     	document.getElementById(txtname1).style.display = '';
	     
	      	document.getElementById(txtname1).readOnly=true;
	    	//Modified by Mrugesh
	     	/*var btnAdd = 'btn_add' + val; 
			document.getElementById(btnAdd).style.display = 'none';
			document.getElementById(btnAdd).value='Edit';*/
			document.getElementById(allowcodeId).value=allow_code;
			//Ended by Mrugesh
   }
   
   if(val=="${cpfId}" && amount!=0)
 	{
 		document.getElementById("gpfAmt").value=amount;
		 cpfamt=amount;
		 document.getElementById("txtt${PF}").value=amount;
		 document.getElementById("pfType").value="GPF";
		 document.getElementById("pfId").value="${cpfId}";
	}
    
}

}

function checkComboStatesDefault1(allow_code1,val,amount,status,vpfAmt)
{
//alert('in checkComboStatesDefault main page funct..' + val + 'amount ' + amount);

if(amount==null || amount=='' || amount==" ")
amount=0;

 for(i=0;i<document.forms[0].tax_name1.length;i++)
  if(document.forms[0].tax_name1[i].value == val)
   {
     //document.forms[0].tax_name[i].checked = true;     
     //document.forms[0].tax_name[i].disabled = true;
     
      var txtname1 = 'txtt' + val; 
      var txtIsChecked1 = 'txtIsChecked' + val;
      var allowcodeId1 = 'txtDeducCode' + val;
  //          alert('textbox name ' + txtname);
//      alert('value of textbox is ' + opener.document.getElementById(txtname).value);

 if(status==0)
 {
 amount=vpfAmt;
}
  if(val=="${ptId}")
 	 amount=0;
     document.getElementById(txtname1).value = amount;
     document.getElementById(txtIsChecked1).value="1";
     document.getElementById(txtname1).style.display = '';
    
     document.getElementById(txtname1).readOnly=true;
     /*document.getElementById(txtname).disabled=true;
     var btnAdd = 'btn_add' + val; 
     document.getElementById(btnAdd).style.display = 'none';
     
     document.getElementById(btnAdd).value='Edit';*/
     document.getElementById(allowcodeId1).value=allow_code1;
     
   }
}

function chknumber11(number1)
{
//        alert('chknumber11');
        name11='txtt'+number1;
		var field_value=document.getElementById(name11).value;
        var periodpos="";
		var atpos="";
        var rule_num="0123456789.";
		if(field_value!="")
		{
		
		for(var i=0;i<field_value.length;i++)
		{
        var cchar=field_value.charAt(i);
        if(rule_num.indexOf(cchar)==-1)
        {
          alert("Enter Valid amount");
          document.getElementById(name11).value="";
          document.getElementById(name11).focus();
          return false;
        }
        }
        } 
}
//Ended



	function chkNumberForJeepRent()
	{
		if((event.keyCode<48 || event.keyCode>57 ) && event.keyCode!=13)
		{
			alert("Enter Number only.");
			document.getElementById("txtt${JeepRent}").value="";
			document.getElementById("txtt${JeepRent}").focus();
			return false;
		}
		
		
	}
	
	
	function chkKey()
	{
		if((event.keyCode<48 || event.keyCode>57 ) && event.keyCode!=13)
		{
			alert("Enter Number only.");
			document.getElementById("txtt${PF}").value="";
			document.getElementById("txtt${PF}").focus();
			return false;
		}
		
		
	}
	function chkPF()
	{
		
		var gpfAmt = document.getElementById("gpfAmt").value;
		var newGpfAmt = document.getElementById("txtt${PF}").value;
		var basic = document.getElementById("basicPay").value;
		
		
		if(eval(newGpfAmt) < eval(gpfAmt))
		{
			alert("The entered Amount is less than Gpf lower limit. You Can not enter this Amount.");
			//document.getElementById("txtt${PF}").value="";
			//document.getElementById("txtt${PF}").focus();
			return false;
			
		}
		
		
		
	}





	
function checkAvailability()
{
	//alert('checking vpf availablity for empID: ' + ${hrEisMst.empId});
	
	
	var vpfAmount=document.getElementById("txtt${PF}").value;	
	var vpfAmt=document.getElementById("txtt${PF}").value;	
	if(vpfAmount!="")
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
			        return false;        
			    }
			}
		}
		var url = "hrms.htm?actionFlag=getVpfDtlsByEmpId&empId=${hrEisMst.orgEmpMst.empId}&check=2";  
		xmlHttp.onreadystatechange = function() {	
    	if (xmlHttp.readyState == 4) {     			
			if (xmlHttp.status == 200) {	
if( document.getElementById("txtt${PF}").readOnly==false)
		{
			   // alert("this is going to chk");
			    var initBasic;
			    var emptype;
			    var pfAmount;
			    var isEmpAvail;
			    var isGpfAcc;
				var XMLDocForAjax=xmlHttp.responseXML.documentElement;
				var vpfDetailsMapping = XMLDocForAjax.getElementsByTagName('vpfDetailsMapping');	
				var flag="true";					
				if(vpfDetailsMapping.length != 0) {	
					isEmpAvail = vpfDetailsMapping[0].childNodes[1].text;					
										
					isGpfAcc = vpfDetailsMapping[0].childNodes[2].text;
					//alert(isGpfAcc);
					if(isGpfAcc==0){
						alert("The employee has not opened the GPF Account yet.");
						 document.getElementById("txtt${PF}").value=0;
					    //window.location.reload();	
		                //document.getElementById("txtt${PF}").value='';
						return false;		                		                
					}
					initBasic=vpfDetailsMapping[0].childNodes[3].text;
					pfAmount = vpfDetailsMapping[0].childNodes[4].text;
					var maxAmount = parseFloat(initBasic);
					var pfAmt = parseFloat(pfAmount);

					/*alert("The Initial Basic is:-"+initialBasic);
					alert("The Value of PF Amount is :-"+pfAmt);										
					alert("The Value of VPF Amount is :-"+vpfAmount);					*/
						if(vpfAmount < pfAmt){
							alert("VPF Amount must be greater then "+pfAmt);
							document.getElementById("txtt${PF}").value=pfAmt;
							//document.getElementById("txtt${PF}").focus();
							return false;
						}
						else if(vpfAmount > maxAmount){
								 alert("VPF Amount must be Less then "+maxAmount);
								document.getElementById("txtt${PF}").value=pfAmt;
							     document.getElementById("txtt${PF}").focus();
							     return false;
							}
							else if (vpfAmount%10!=0)
							{
								if (confirm("Do You Want to Round Off Your VPF account?"))
								{
									if((vpfAmount%10)>0)
										vpfAmount=Math.abs(vpfAmount)+Math.abs(10-(vpfAmount%10));			            	
									
									if(	vpfAmount >	maxAmount)
									{
										vpfAmt=Math.abs(vpfAmt)-Math.abs(vpfAmt%10);
										document.getElementById("txtt${PF}").value=vpfAmt;
									}	
									else
									{            	
										document.getElementById("txtt${PF}").value=vpfAmount;
									}
									return true;
								}
								else
								{
									return false;
								}
							}
							else
							{
								//alert("returnign false");
								return true;
							}
				}
				
			}
		}
		}
		
	}
	
	xmlHttp.open("POST", encodeURI(url) , false);    
	xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.send(encodeURIComponent(null));	
	return true;
	}
	else
	{
		alert("VPF Amount can not be blank ");
		document.getElementById("txtt${PF}").focus();
		return false;
	}
	
	
}

function openNewWindow()
{	
	window.showModalDialog("./hrms.htm?actionFlag=viewTemporaryPayBill&empId=${empId}&otherId=${otherId}","PreviewPayBill","dialogWidth:900px; dialogHeight:650px; center:yes; status:yes;menubar=yes");
}			



--></script>




   

				  	
		        
<table border=1 width="100%">

<tr>

	<c:choose>
	<c:when test="${empAllRec eq 'true' or empAllRec eq 'y'}">
			<td colspan="3" align="center"><font size="3"><b><u><a href="./hrms.htm?actionFlag=viewTemporaryPayBill&empId=${empId}&empAllRec=true&otherId=${otherId}">Preview Bill</a></u></b></font></td>

	</c:when>
		<c:otherwise>
				<td colspan = 3 align="center"><font size="3"><b><u><a href="javascript:openNewWindow()" onmouseover="window.status='PreviewBill';return true;" onmouseout="window.status='';"> Preview Bill </a>
	</u></b></font></td>
		</c:otherwise>

	</c:choose>

</tr>

<tr>
  <td width="33%" valign="top">
<c:set var="allowcode" value="${0}"/>
<c:choose>
<c:when test="${allowMpgSize eq 0}">
<table width="100%" border=0 align="left" id="allowance" style="display:none">
</c:when>
<c:otherwise>
<table  width="100%" align="left"  border=0 id="allowance">
	<tr>
    	<td align="right"><font color="red" > <b> Allowances (A)</b></font></td><td>&nbsp;</td>
	</tr>
</c:otherwise>
</c:choose> 
	<tr>
		<!-- <td><b>Options</b></td>
		<td>
			<hdiits:select onchange="showDeductions()" default ="1" name="cstrStatus" size ="1" captionid="drop_down" >         
			<hdiits:option  value="1">Allowances </hdiits:option>
			<hdiits:option value="2">Deductions </hdiits:option>
			</hdiits:select>
		</td>
        <td></td>  -->
        <td colspan="2"><hdiits:hidden default="" name="status" /></td>	
	</tr>
	<tr>
       	<td align="right"><b><c:out value="Basic Pay"/></b></td>
	    <td align="right" style="padding-right: 5px"><hdiits:text name="basicPay" readonly="true" style="background: #E5F0F2; text-align:right;	font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" size="5" caption="basicPay" id="basicPay" default="${hrEisOtherDtls.otherCurrentBasic}"  />
		 	<script>
            	grossAmount="${hrEisOtherDtls.otherCurrentBasic}";
			</script>
		</td>
	</tr>
	<c:forEach var="allowType" items="${allowList}">
		<script>size++;</script>
		
	
		
	<TR>
		<TD width="65%" align="right"> 
			<hdiits:hidden default="${allowType[0]}" name="tax_name" id="tax_name" />
			<b><c:out value="${allowType[1]}"/></b>
		</TD>
		<td width="35%"  align="right"  style="padding-right: 5px">
			<c:set var="allowcode" value="${allowcode+1}"/>
	        <hdiits:text  name="txt${allowType[0]}" size="5"  caption="Allowance" captionid = "Allowance" id="txt${allowType[0]}" maxlength="5" onblur="chknumber1(${allowType[0]});document.getElementById('txthidden${allowType[0]}').value=document.getElementById('txt${allowType[0]}').value" style="background-color: ffffff; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;"/>
            <hdiits:button name="btn_add${allowType[0]}" type="button" style="display:none" 
            value="Add" onclick="insertEmpAllowMpg(${allowType[0]})"/>		                
            <hdiits:hidden name="txthidden${allowType[0]}"  id="txthidden${allowType[0]}"  />
            <hdiits:hidden name="txtIsChecked${allowType[0]}" caption="Check Status" captionid="CheckStatus" />
            <hdiits:hidden name="txtAllowCode${allowType[0]}"  />
            <hdiits:hidden default="${allowType[0]}" name="Allowcode${allowcode}" />
		</td>
	</TR> 
	</c:forEach>
</table>

		<c:if test="${allowMpgSize ne 0}">
			<script>
   				document.getElementById("jsptext").style.display='';
   				document.getElementById("jsptable").style.display='none';
				//document.getElementById("empTable").style.display='none';
   				var value="${hrEisMst.orgEmpMst.empFname} ${hrEisMst.orgEmpMst.empMname} ${hrEisMst.orgEmpMst.empLname}";
				//document.getElementById("EmpName").value=value;
   				//document.getElementById("Employee_ID_EmpSearch").value="${hrEisMst.orgEmpMst.empId}";
   	   		</script>
     		<c:forEach  var="list" items="${allowNamesFromMpg}"> 
				<script>checkComboStates(${list[0]}, ${list[1].allowCode}, ${list[2]});</script>
		    </c:forEach>
		</c:if>
    
		<c:forEach  var="exprList" items="${allowNamesFromExpr}"> 
			<c:forEach  var="list" items="${allowNamesFromMpg}">
				<c:choose>
					<c:when test="${list[1].allowCode==exprList.hrPayAllowTypeMst.allowCode}">
						<script>checkComboStatesDefault(${list[0]}, ${list[1].allowCode}, ${list[2]});</script>
					</c:when>
				</c:choose>
			</c:forEach>
		</c:forEach>
</td>
<td width="33%" valign="top">
  <!-- Added by Mrugesh -->
	  <c:set var="deduccode" value="${0}"/>
		<c:choose>
			<c:when test="${deducMpgSize eq 0}">
				<table  width="100%" align="right" border=0 id="deduction" style="display:none">
			</c:when>
			<c:otherwise>
				<table width="100%" align="right" border=0   id="deduction">
					<tr>
						<td align="right"><b><font color="red" >Deductions (B)</font></b></td>
						<td><hdiits:hidden name="pfType" /><br><hdiits:hidden name="gpfAmt" /><br><hdiits:hidden name="pfId"/></td>
					</tr>
			</c:otherwise>
		</c:choose>	
		<c:forEach var="deducType" items="${deducActionList}">
			<c:if test="${deducType[0] ne vpfId }">
				<c:if test="${deducType[0] ne cpfId }">	
					<c:if test="${deducType[0] ne surItId }">
						<c:if test="${deducType[0] ne GpfId }">
							<c:if test="${deducType[0] ne pliId }">
								<c:if test="${deducType[0] ne GpfIdIV }">
									<TR>
										<TD width="65%" align="right">
											<hdiits:hidden default="${deducType[0]}" name="tax_name1" id="tax_name1" />
											<b><c:out value="${deducType[1]}"/></b>
										</TD>
										<td width="35%" align="right" style="padding-right: 5px">
											<c:set var="deduccode" value="${deduccode+1}"/>
											<span id="span${deducType[0]}"></span><hdiits:text size="5" onblur="chknumber11(${deducType[0]})" readonly="true"  name="txtt${deducType[0]}" caption="Allowance1" captionid = "Allowance1" default="0" id="txtt${deducType[0]}" maxlength="8" style="background: #E5F0F2;	text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;"/>
											</td>
									</TR>
								</c:if> 
							</c:if>
						</c:if>
					</c:if>
				</c:if>
			</c:if>
		</c:forEach>
		
<script>
document.getElementById("txtt${PF}").onkeypress=chkKey;
document.getElementById("txtt${PF}").onblur=checkAvailability;
	//document.getElementById("txtt${PF}").onblur=testCurrency;
</script>
		
		<!--  for misc recovery -->
								<TR>
									<TD width="65%" align="right">
										<hdiits:hidden default="${deducType[0]}" name="tax_name1" id="tax_name1" />
										<b><c:out value="Misc. Recovery "/></b>
									</TD>
									<td width="35%" align="right" style="padding-right: 5px">
										<hdiits:text name="txtt${deducType[0]}"  caption="Allowance1" captionid = "Allowance1" size="5" readonly="true" default="${miscRecov}" id="txtt${deducType[0]}" style="background: #E5F0F2;	text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;"/>
									<script>
											totalDeduc=totalDeduc+eval("${miscRecov}");
											//alert('3.totalDeduc = '+totalDeduc+' miscRecov: '+${miscRecov});
									</script>
									</td>
								</TR> 
		
		<tr>
			<td align="right" colspan="2">
					<%--jsp:include page="../../core/PayTabnavigation.jsp" --%>
					<c:if test="${deducMpgSize ne 0}">
						<script>
   							/*var value="${hrEisMst.orgEmpMst.empFname} ${hrEisMst.orgEmpMst.empMname} ${hrEisMst.orgEmpMst.empLname}";*/
							document.getElementById("txtt"+${itId}).value="${incometax}";
							//alert(' income tax is' + ${incometax});
							//totalDeduc=totalDeduc+eval("${incometax}");
						</script>
						<c:forEach  var="list" items="${deducNamesFromMpg}">  
							<script>checkComboStates1(${list[0]}, ${list[1].deducCode}, ${list[2]},${list[3]},parseFloat("${vpfAmt}"));</script>
						</c:forEach>
						
					</c:if>
  			</td>
		</tr>
		<c:forEach  var="exprList" items="${deducNamesFromExpr}"> 
        	<c:forEach  var="list" items="${deducNamesFromMpg}">
				<c:choose>
					<c:when test="${list[1].deducCode==exprList.hrPayDeducTypeMst.deducCode}">
						<script>checkComboStatesDefault1(${list[0]}, ${list[1].deducCode}, ${list[2]},${list[3]},parseFloat("${vpfAmt}"));</script>
					</c:when>
				</c:choose>
			</c:forEach>
		</c:forEach>
	    		<script>
		document.getElementById("txtt${ptId}").value="${pt}";
		totalDeduc=eval(totalDeduc)+eval("${pt}");
		//alert('4.totalDeduc = '+totalDeduc);
		
	if("${hrEisOtherDtls.isSis}"==1)
		{
			var aisSf=eval(document.getElementById("txtt${aisSfId}").value);
			var aisIf =eval(document.getElementById("txtt${aisIfId}").value);
			
			totalDeduc=totalDeduc-(aisSf+aisIf);
			document.getElementById("txtt${aisSfId}").value=0;
			document.getElementById("txtt${aisIfId}").value=0;	
			//document.getElementById("txtt${sisSfId}").value=0;
			//document.getElementById("txtt${sisIfId}").value=0;
			//alert('5.totalDeduc = '+totalDeduc+' sis');
		}

		document.getElementById("txtt${JeepRent}").onkeypress=chkNumberForJeepRent;
		document.getElementById("txtt${JeepRent}").readOnly=false;
		document.getElementById("txtt${PF}").readOnly=false;
 		document.getElementById("txtt${JeepRent}").style.backgroundColor="#ffffff";

 		//alert('1.vpf is: ' +parseFloat(${vpfAmt}));
 		//alert('gpf amnt is: ' +parseFloat(document.getElementById("gpfAmt").value) );

		if(document.getElementById("pfId").value=="${cpfId}" && parseFloat("${vpfAmt}")==0)
	 	{
	 		document.getElementById("span${PF}").innerHTML="<b>(CPF)</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	 		document.getElementById("txtt${PF}").value=document.getElementById("gpfAmt").value;

	 		totalDeduc=eval(totalDeduc)+eval(document.getElementById("txtt${PF}").value);
	 		
	 		//alert('6.totalDeduc = '+totalDeduc+'in pfId=cpfId && vpf==0');
	 	}
	 	else if(parseFloat("${vpfAmt}")!=0)
	 	{
		 	var gpfAmount=parseFloat(document.getElementById("gpfAmt").value);
		 
		 	if(gpfAmount>parseFloat("${vpfAmt}"))
		 	{
		 	 	var temp=gpfAmount-parseFloat("${vpfAmt}");
			 	document.getElementById("txtt${PF}").value=parseFloat(document.getElementById("gpfAmt").value);
			 	totalDeduc=eval(totalDeduc)+temp;
			 	//alert('7.totalDeduc = '+totalDeduc+' gpfAmt>vpf');
		 	}
		 	else
		 	{
			 	document.getElementById("txtt${PF}").value=parseFloat("${vpfAmt}");
			 	//alert('8.totalDeduc = '+totalDeduc+' else');
		 	}

	 	document.getElementById("span${PF}").innerHTML="<b>(VPF)</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	 	document.getElementById("gpfAmt").value=parseFloat("${vpfAmt}");

		document.getElementById("pfType").value="VPF";
		document.getElementById("pfId").value="${vpfId}";
		
	 }
 		
		</script>
		
<c:if test="${hrEisOtherDtls.hrEisEmpMst.empType eq contract or hrEisOtherDtls.hrEisEmpMst.empType eq fixed}">
<script>
document.getElementById("txtt${PF}").readOnly=true;
document.getElementById("txtt${PF}").style.backgroundColor="#E5F0F2";
</script>

</c:if>

 
<c:if test="${gpfFlagForZeroCheck eq 0}">
<script>
document.getElementById("txtt${PF}").readOnly=true;
totalDeduc=totalDeduc-document.getElementById("txtt${PF}").value;
document.getElementById("txtt${PF}").value=0;
document.getElementById("txtt${PF}").style.backgroundColor="#E5F0F2";
document.getElementById("txtt${PF}").disabled=true;

</script>

</c:if>		

<!-- Modified By Mrugesh For removing readonly Income-Tax textbox -->
<script>
		document.getElementById("txtt${itId}").readOnly=false;
		document.getElementById("txtt${itId}").style.backgroundColor="FFFFFF";
		 //alert("The IT is---->>>"+document.getElementById("txtt${itId}").value);
		document.frmOtherMst.incometax.value = document.getElementById("txtt${itId}").value;//Added for basic detail screen's income-tax value
		totalDeduc=totalDeduc+eval(document.getElementById("txtt${itId}").value);
</script>
<!-- Ended by Mrugesh -->
	</table>
</td>



<!-- ADDED BY VARUN SHARMA FOR LOAN -->

	<td width="33%" valign="top">
		<table width="100%" align="center" border="0">
			<tr>
				<!-- SET LOAN HEADER  -->
				
				<td width="25%"></td>
				<td width="25%" align="center" >
					<b><font color="red"> Loan (C) </font></b>
				</td>
				<td width="25%"></td>
				
			</tr>

				<!-- SET LOAN INFORMATION -->

				<c:set var="size" value="1" ></c:set>
				<%! int i=1; %>
				<%
					i=(i-i)+1;
					pageContext.setAttribute("size",i);
				%>

				<c:forEach var="loanList" items="${otherDtlsLoan}" >
					<c:choose>

						<c:when test="${loanList.outstandingPrincipleAmt ne 0}">
							<c:set var="empLoanId" 			value="${loanList.empLoanId}" ></c:set>
							<c:set var="loanName" 			value="${loanList.loanName}" ></c:set>
							<c:set var="emiAmt"				value="${loanList.loanPrinEmiAmt}" ></c:set>
							<c:set var="amtType" 			value="Principle"></c:set>
							<c:set var="outstandingPrinAmt" value="${loanList.outstandingPrincipleAmt}" ></c:set>

							<tr >

								<td width="30%" ><b><a href="javascript:openWindow(${empLoanId})" onmouseover="window.status='loan';return true;" onmouseout="window.status='';" ><c:out value="${loanName}"/></a></b></td>
								<td width="40%">
									<hdiits:number name="loanTxt${size}"  id="loanTxt"  maxlength="5" default="${emiAmt}" size="5" style=" font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />
										&nbsp;<b><c:out value="${amtType}"></c:out></b></td>
								<td align="right">
									<font color=red ><b><c:out value="${outstandingPrinAmt} remaining"></c:out></b></font></td>
									<hdiits:hidden name="loanAmtType${size}"  id="loanAmtType" default="${amtType}" />		
									<hdiits:hidden 	name="loanTxtId${size}"  id="loanTxtId" default="${loanList.empLoanId}" />
							</tr>
							
							<script>
								//alert(" totalDeduc in princ is: " +totalDeduc);
								//alert(" emiAmt is: ${emiAmt}");
								
								//SET TOTAL DEDUC AMOUNT
								totalDeduc=totalDeduc+eval("${emiAmt}");
							</script>
						</c:when>

						<c:otherwise>
							<c:set var="empLoanId" 			value="${loanList.empLoanId}" ></c:set>
							<c:set var="loanName" 			value="${loanList.loanName}" ></c:set>
							<c:set var="emiAmt"				value="${loanList.loanIntEmiAmt}" ></c:set>
							<c:set var="amtType" 			value="Interest"></c:set>
							<c:set var="outstandingIntAmt" 	value="${loanList.outstandingInterestAmt}" ></c:set>

							<tr>
								<td style="width:25%" align="right" ><b><a href="javascript:openWindow(${empLoanId})" onmouseover="window.status='loan';return true;" onmouseout="window.status='';" ><c:out value="${loanName}"/></a></b></td>
								<td width="40%">
									<hdiits:number name="loanTxt${size}"  id="loanTxt"  maxlength="5" default="${emiAmt}" size="5" style=" font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />
										&nbsp;<b><c:out value="${amtType}"></c:out></b></td>
								<td width="25%" align="right" >
									<font color=red ><b><c:out value="${outstandingIntAmt} remaining"></c:out></b></font></td>
                                <hdiits:hidden name="loanAmtType${size}"  id="loanAmtType" default="${amtType}" />
								<hdiits:hidden name="loanTxtId${size}"  id="loanTxtId" default="${loanList.empLoanId}" />
							</tr>
							
							<script>
								//alert(" totalDeduc in princ is: " +totalDeduc);
								//alert(" emiAmt is: " +emiAmt);
								
								//SET TOTAL DEDUCT AMOUNT
								totalDeduc=totalDeduc+eval("${emiAmt}");
							</script>
						</c:otherwise>

					</c:choose>
					<%
						 i=i+1;
						 pageContext.setAttribute("size",i);
					%>
				</c:forEach>
				<hdiits:hidden name="loanSize"  id="loanSize" default="${size-1}" />
		</table>
	</td>





</tr>
<tr>
    <td align="left" style="padding-left: 120px;">
		<b> Total Sum Of A <font color="red">(D)</font> : </b>  
		<hdiits:text id="grossAmount" name="grossAmount" style="border: 0px; text-align:center; font-family: Verdana; font-size: 11px; font-weight: bold; color :Black;" readonly="true"/>
	</td>
	
	<td align="left" style="padding-left: 120px;">
		<b>	Deduction [B + C] <font color="red"> ( E ) </font>  : </b>	
		<hdiits:text id="totalDeduc" name="totalDeduc"  style="border: 0px; text-align:center; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" readonly="true"/>
	</td>				
	
	<td align="left" style="padding-left: 120px;">
		<b>	Net Amount [D - E] : </b>
		<hdiits:text id="netAmount" name="netAmount"  style="border: 0px; text-align:center; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" readonly="true"/>
	</td>
</tr>

<!-- ENDED BY VARUN SHARMA -->

<tr>
	<td colspan="3" align="center" >
		<hdiits:button name="btnsubmit" id="btnsubmit" type="button" onclick="insertEmpAllowMpg1()" value="Submit"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<hdiits:button name="btnClose" type="button" captionid="eis.close" bundle="${Lables}" onclick="onclosefn();" />
	</td>
</tr>

</table>
</td>
</tr>


</table>


<!-- Ended by Mrugesh -->




<script type="text/javascript">
	
		document.getElementById("grossAmount").value=grossAmount;

		document.getElementById("totalDeduc").value=totalDeduc;

		document.getElementById("netAmount").value=grossAmount-totalDeduc;
   
	
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
	initializetabcontent("maintab")
	//alert('hi');
	//window.location="./hrms.htm?actionFlag=viewTemporaryPayBill&empId=${empId}&otherId=${otherId}";
	
	if("${msg}"!=null&&"${msg}"!='')
	{
		alert("${msg}");
		if("${msg}"!=null&&"${msg}"!=''&& ("${empAllRec}"=='Y' || "${empAllRec}"=='true' || "${empAllRec}"=='TRUE') )
		{
			var url="./hrms.htm?actionFlag=getOtherData&empId=${empId}&empAllRec=true&otherId=${otherId}&edit=Y";
		}
		else
		{
			var url="./hrms.htm?actionFlag=getOtherData&otherId=${otherId}&edit=Y&empAllRec=false";
		}

		document.forms[0].action=url;
		document.forms[0].submit();
	}


	function openWindow(empLoanId)
	{

		var url="./hrms.htm?actionFlag=getLoanValue&edit=Y&empLoanId="+empLoanId;
		window.open(url,'','status=0');
	}
	
</script>
 
 
	
	

</body>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>


