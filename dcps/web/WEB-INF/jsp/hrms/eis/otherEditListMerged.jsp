
<%
try {
	//System.out.println("Hiii...");

%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>">
</script>
<script type="text/javascript" 
	src="script/hod/ps/common.js">
</script>
<script type="text/javascript" 
	src="script/common/person.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>">
</script>





<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="empName" value="${resValue.empName}"> </c:set>
<c:set var="isPunished" value="${resValue.isPunished}"> </c:set>

<c:set var="grdPay" value="${resValue.gradePay}"> </c:set>

<c:set var="resultObj1" value="${result}" > </c:set>

<c:set var="resValue1" value="${resultObj1.resultValue}" > </c:set>	
<c:set var="gpfAccNo" value="${resValue.gpf}"> </c:set>


<c:set var="gradeName" value="${resValue1.gradeName}"> </c:set>
<c:set var="postName" value="${resValue1.postName}"> </c:set>
<c:set var="desigName" value="${resValue1.desigName}"> </c:set>
<c:set var="scaleId" value="${resValue.scaleId}"> </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set> 
<c:set var="empType" value="${resValue.empType}" ></c:set>
<c:set var="empId" value="${resValue.empId}" ></c:set>
<c:set var="otherId" value="${resValue.otherId}" ></c:set>
<c:set var="sevarthId" value="${resValue.sevarthId}" ></c:set>
<c:set var="empAllRecOther" value="${resValue.empAllRecOther}" ></c:set>
<c:set var="empAllRec" value="${resValue.empAllRec}" ></c:set>
<c:set var="updatePaybillFlg" value="${resValue.updatePaybillFlg}" ></c:set>
<c:set var="paybillMonth" value="${resValue.paybillMonth}" ></c:set>
<c:set var="paybillYear" value="${resValue.paybillYear}" ></c:set>

<c:set var="officeName" value="${resValue.officeName}" ></c:set>
<c:set var="CurrPsrNo" value="${resValue.CurrPsrNo}" ></c:set>
<c:set var="CurrBillNo" value="${resValue.CurrBillNo}" ></c:set>
<c:set var="NextPsrNo" value="${resValue.NextPsrNo}" ></c:set>
<c:set var="NextOtherId" value="${resValue.NextOtherId}" ></c:set>
<c:set var="NextBillNo" value="${resValue.NextBillNo}" ></c:set>

<c:set var="PreviousPsrNo" value="${resValue.PreviousPsrNo}" ></c:set>
<c:set var="PreviousOtherId" value="${resValue.PreviousOtherId}" ></c:set>
<c:set var="PreviousBillNo" value="${resValue.PreviousBillNo}" ></c:set>

<c:set var="locationCode" value="${resValue.locationCode}" ></c:set>
<c:set var="payCommissionList" value="${resValue2.payCommissionList}" ></c:set>




<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:message var="contractEmpType" key="contract" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="fixedEmpType" key="fixed" bundle="${commonLable}" scope="request"> </fmt:message>
<fmt:message var="probationEmpType" key="probation" bundle="${commonLable}" scope="request"> </fmt:message>

<c:set var="empAllRecFlag" value="${resValue.empAllRecFlag}" ></c:set>
<c:set var="empId" value="${resValue.empId}" ></c:set>
<!--<c:set var="deptName" value="${resValue1.deptList}"> </c:set>
  <c:out value="${deptName}"> </c:out> -->



<!--  <c:set var="resultObj3" value="${result}" > </c:set>
<c:set var="resValue3" value="${resultObj3.resultValue}" > </c:set>	
<c:set var="otherList" value="${resValue3.otherList}"> </c:set> -->

<c:set var="otherList" value="${resValue1.otherList}"> </c:set>
<c:set var="cityList" value="${resValue1.cityList}"> </c:set>
<c:set var="quaterTypeList" value="${resValue1.quaterTypeList}"> </c:set> 
<c:set var="resultObj2" value="${result}" > </c:set>
<c:set var="resValue2" value="${resultObj2.resultValue}" > </c:set>	
<%--<c:set var="gradeName" value="${resValue2.gradeList}"> </c:set>
<c:set var="hrScaleMst" value="${resValue2.hrScaleMst}"> </c:set>
<c:set var="orgGradeMst" value="${resValue2.orgGradeMst}"> </c:set>
<c:set var="gdMapId" value="${resValue2.gdMapId}"> </c:set>--%>
<c:set var="sgdMapId" value="${resValue2.sgdMapId}"> </c:set>
<c:set var="cityId" value="${resValue2.cityId}"> </c:set>
<!--  for Fetching all scales for a Designation. -->
<c:set var="scaleList" value="${resValue2.scaleList}"> </c:set>
<c:set var="scaleListForFifthPay" value="${resValue2.scaleListForFifthPay}"> </c:set>
<!--  by manoj for hrEmpTraMpg -->
<c:set var="hrEmpTraMpg" value="${resValue2.hrEmpTraMpg}"> </c:set>
<!--end  by manoj for hrEmpTraMpg -->
<!--  Fetching all Designation of a perticlular Grade -->
<c:set var="desgList" value="${resValue2.desgList}"> </c:set>
<c:set var="gradeId" value="${resValue2.gradeId}"> </c:set>
<c:set var="payCommissionId" value="${resValue2.payCommissionId}"> </c:set>
<c:set var="payCommissionName" value="${resValue2.payCommissionName}"> </c:set>

 
<c:set var="reasonList" value="${resValue.reason}"> </c:set>
 


<fmt:setBundle basename="resources.eis.eis_Constants" var="enConstants" scope="request"/>
<fmt:message var="p1" key="AISGradeCode" bundle="${enConstants}" scope="request" />


<script type="text/javascript" language="JavaScript"><!--


toggleOnLoad();

function chkKey(e)
{
	if(window.event) // IE
	{
		if(e.keyCode=="13")
			{
				return false;
			}
		else
			return true;
	}
}

//added Mrugesh
// Start By Urvin shah To Clear the Combos 

function clearCombo(cmbName)
{	
	
	var v=document.getElementById(cmbName).length;
	
	for(i=0;i<v-1;i++)
	{	
			
			lgth = document.getElementById(cmbName).options.length -1;
			document.getElementById(cmbName).options[lgth] = null;
	}		
}

// Ended By Urvin shah.
var flag=0;
function disableFields(empTypeId) {

	
//if(empTypeId==300018 || empTypeId==300225)
 if(empTypeId=="${contractEmpType}" || empTypeId=="${fixedEmpType}")
    {
    	
    	
    	var cmbName="scale";
    	//alert('alert if');
    	clearCombo(cmbName);
    	/*if(empTypeId==300018)
	    	document.getElementById("incometax").disabled=true;*/
    	//document.getElementById("is_handicapped").disabled=true;
    	//document.getElementById("quaterType").disabled=true;
    	document.getElementById("medAllowance").disabled=true;
    	document.getElementById("uniformAvailed").disabled=true;
    	///document.getElementById("sis1979Flag").disabled=true;
    	document.getElementById("FamilyPlannig").disabled=true;
    	//document.frmOtherMst.scale(document.frmOtherMst.scale.selectedIndex).text="0-0-0";
    	//document.frmOtherMst.scale(document.frmOtherMst.scale.selectedIndex).value="0";
    	document.getElementById("scale").value="0";
    	document.getElementById("scale").disabled=true;
    	
    	//document.getElementById("empCity").value="0";
    	//document.getElementById("empCity").disabled=true;
    	flag=1;
    	//by manoj for tra mapping
    	document.getElementById("traTable").style.display='none';
    	document.getElementById("taAvailed").checked=false;
    	//end by manoj for tra mapping
    }
    else
    {
    	//alert('alert else'); 
    	   	
    	//document.getElementById("incometax").disabled=false;
    	//document.getElementById("is_handicapped").disabled=false;
    	//document.getElementById("quaterType").disabled=false;
    	document.getElementById("medAllowance").disabled=false;
    	document.getElementById("uniformAvailed").disabled=false;
    	//document.getElementById("sis1979Flag").disabled=false;
    	document.getElementById("FamilyPlannig").disabled=false;
    	document.getElementById("scale").disabled=false;
    	document.frmOtherMst.scale(document.frmOtherMst.scale.oprions).value="";
    	document.frmOtherMst.scale(document.frmOtherMst.scale.oprions).text="--------------Select--------------";
		
		if(eval(flag)==1)
		{			
			clearCombo("scale");
			//getgradedesgpost();
			flag=0;
    	}
    	//by manoj for tra mapping
    	document.getElementById("taAvailed").checked=true;
    	if(document.getElementById("taAvailed").checked==true)
    	{
    		document.getElementById("traTable").style.display='';
    	}
    	//end by manoj for tra mapping
    	//document.frmOtherMst.initialBasic.value='';
    	//document.getElementById("empCity").value="";
    	//document.getElementById("empCity").disabled=false;
    }
    /*if(${p1}==${gradeId})
    {
     //alert('grade match');
     document.getElementById("sis1979Flag").disabled=true;
     document.getElementById("isSis").style.visibility ='visible';
     document.getElementById("sisLabel").style.visibility ='visible';
     
     }else{
      //alert('in else');
     document.getElementById("isSis").style.visibility='hidden';
     document.getElementById("sisLabel").style.visibility ='hidden';
     }*/
    

}


//ended by Mrugesh
//added by manish
function calculateGradePay()
{

	//calculatePB()
	//alert("calculate grade pay");
	//alert('inside grade pay');
	var gradePayArr = document.getElementById("scale").options[document.getElementById("scale").selectedIndex].text.split("(");
	if(gradePayArr!=null) {
	if(gradePayArr.length<=1)
		document.getElementById('GradePay').value = 0;
	else {
	var gradePay = gradePayArr[gradePayArr.length-1].split(")");
	if(gradePay!=null)	
	 document.getElementById('GradePay').value = gradePay[0]; 
	}
	}	

	//alert('manish here');
	//var pb=document.getElementById("PayinPayband").value ;
	//var gp = gradePay[0];
	//var basic= eval(pb)+eval(gp) ;
	//alert("basic is "+basic);
	
	//document.getElementById('initialBasic').value = basic; 
	//document.getElementById('PayinPayband').value = document.getElementById('initialBasic').value-eval(gp); 
	
}

function clearPayBand()
{
	//alert("function called");
	document.getElementById("PayinPayband").value="";
}
function calculatePB()
{

	
	//alert('calculatePB');
	var gradePayArr = document.getElementById("scale").options[document.getElementById("scale").selectedIndex].text.split("(");
	if(gradePayArr!=null) {
	if(gradePayArr.length<=1)
		document.getElementById('GradePay').value = 0;
	else {
	var gradePay = gradePayArr[gradePayArr.length-1].split(")");
	if(gradePay!=null)	
	 document.getElementById('GradePay').value = gradePay[0]; 
	}
	}

	
	
	var gp = gradePay[0];
	var basic= document.getElementById('initialBasic').value;
	var pb=eval(basic)-eval(gp);
	document.getElementById('PayinPayband').value = pb; 
	 
}
function calculateBasic()
{

	
	//alert('calculate basic');
	var gradePayArr = document.getElementById("scale").options[document.getElementById("scale").selectedIndex].text.split("(");
	if(gradePayArr!=null) {
	if(document.getElementById("scale").value=="")
		alert('please select the scale');
	if(gradePayArr.length<=1)
		document.getElementById('GradePay').value = 0;
	else {
	var gradePay = gradePayArr[gradePayArr.length-1].split(")");
	if(gradePay!=null)	
	 document.getElementById('GradePay').value = gradePay[0]; 
	}
	}

	
	//if(document.getElementById("PayinPayband") != null && document.getElementById("PayinPayband")!="" )
//	{
	var pb=document.getElementById("PayinPayband").value ;

	//alert(pb);
//	}
	//else 
	//{
			if(document.getElementById("PayinPayband").value  =="")
							pb=0;
	//}
		//	alert(pb);
	var gp = gradePay[0];
	

	var basic=eval(pb)+eval(gp);
	//alert('eval(pb) '+eval(pb));	
	//alert('basic is'+basic);
	document.getElementById('initialBasic').value = basic; 
	//document.getElementById('PayinPayband').value = document.getElementById('initialBasic').value-eval(gp); 
}
function chkval()
{

	if(document.getElementById("orderDataFlag")!=null)
	{
	
	var chkOrderDet = document.getElementById("orderDataFlag").value;
	if(chkOrderDet=="Y")
	{
		//japen
		//alert("::"+document.getElementById("orderNo").value+"::");
		var orderNo = document.forms[0].orderNo.value;
		var orderDate = document.forms[0].orderDate.value ;
		var reason = document.forms[0].Reason.value ;
		var remarks = document.forms[0].Remarks.value ;

		if(orderNo=='')
		{
			alert('Please Enter Valid Order Number');
			return false;
		}
		
		if(orderDate== '')
		{
			alert('Please Enter Valid Order Date');
			return false;
		}
		if(reason== -1)
		{
			alert('Please Enter Valid Reason');
			return false;
		}
		if(remarks== '')
		{
			alert('Please Enter Remarks');
			return false;
		}
	}
	}

	
	if(document.getElementById("payCommissionCombo").value == 6)
	{
		
	if(document.frmOtherMst.sgdMapId1.value== '${sgdMapId}' && document.frmOtherMst.initialBasic.value==''){
		return true;
	}
	if(document.frmOtherMst.initialBasic.value!='')
	{
		if( document.frmOtherMst.initialBasic.value < 1)
		{
			alert("Please Enter Valid Value of Current basic");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		var intialBasic=parseFloat(document.frmOtherMst.initialBasic.value);
		if(!document.forms[0].sgdMapId1.disabled)//start if only if not fixed pay 
		{		
		var scaleValue=document.forms[0].sgdMapId1.options[document.forms[0].sgdMapId1.selectedIndex].text;
		var initialValue=parseFloat(scaleValue.substring(0,scaleValue.indexOf("-")));
		var temp=scaleValue.substring(scaleValue.indexOf("-")+1);
		var incrementValue = parseFloat(temp.substring(0,temp.indexOf("-")));
		var endValue= parseFloat(temp.substring(temp.indexOf("-")+1));
		var sixPayScale=temp.substring(0,temp.indexOf("-"));
		var gpString=temp.substring(temp.indexOf("(")+1);
		gpString=gpString.substring(0,gpString.indexOf(")"))
		//alert('sixPayScale'+sixPayScale);
		//alert(gpString);+eval(gpString)
		var maxBasicSixPay=eval(sixPayScale);
		//alert(maxBasicSixPay);		
		var minBasicSixPay=initialValue;
		//alert(minBasicSixPay);
		if(sixPayScale>initialValue)
		{
			if(intialBasic < minBasicSixPay){				
				alert("Please Enter Higher value of Current basic then scale's Initial Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;
			}
			if(intialBasic > maxBasicSixPay){
				alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;
			}
				
			return true;
		}
		temp=temp.substring(temp.indexOf("-")+1);
		

		if(temp.indexOf("-")>0) // this if loop is for higher scale  
		{
		var higherIncVal=temp.substring(temp.indexOf("-")+1);
		higherIncVal=higherIncVal.substring(0,higherIncVal.indexOf("-"));
		temp=temp.substring(temp.indexOf("-")+1);
		var higherEndVal=temp.substring(temp.indexOf("-")+1);
		
		// Amount is straight away equals start ,middle or last values return
		
        if(intialBasic == initialValue||intialBasic == endValue||intialBasic == higherEndVal ){
			return true;
		}
		
		if(intialBasic < initialValue){
			alert("Please Enter Higher value of Current basic then scale's Initial Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		if(intialBasic > higherEndVal){
			alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		
		// this code is same as normal scale as values is less than higherscale range
		if(intialBasic<=endValue)
		{
		var tempValue = intialBasic - initialValue;
		if(incrementValue!=0 && tempValue%incrementValue == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(incrementValue!=0 && tempValue%incrementValue != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
				}
			}
		}			
		else // validation if scale is lying in higher scale range
		{
		var tempValue = intialBasic - endValue;
		if(higherIncVal!=0 && tempValue%higherIncVal == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(higherIncVal!=0 && tempValue%higherIncVal != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
				}
			}
		}
		}//  end if loop is for higher scale 
		else  // this else loop is for normal scale retained as it is  
		{
			
		if(intialBasic == initialValue){
			return true;
		}
		if(intialBasic == endValue){
			return true;
		}
		if(intialBasic < initialValue){
			alert('initial basic is '+intialBasic);
			alert("Please Enter Higher value of Current basic then scale's Initial Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		if(intialBasic > endValue){
			alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
			document.frmOtherMst.initialBasic.value="";
			document.frmOtherMst.initialBasic.focus();
			return false;
		}
		var tempValue = intialBasic - initialValue;
		if(incrementValue!=0 && tempValue%incrementValue == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(incrementValue!=0 && tempValue%incrementValue != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.initialBasic.value="";
				document.frmOtherMst.initialBasic.focus();
				return false;	
				}
			}
	    }		
	}//end start if only if not fixed pay 
	}

	
	}
	else
	{
		
		var abc=chkvalForFifthPay();
		//alert('jay shree ram');
		//alert(''+document.getElementById("BasicPay").value);
		//alert(''+document.getElementById("scale_fifth").value);
		//alert(''+document.getElementByName("sgdMapId_fifth").value);
		
		//document.getElementByID("initialBasic").value=document.getElementByID("BasicPay").value;
		//document.getElementByID("scale").value=document.getElementByID("scale_fifth").value;
		//document.frmOtherMst.sgdMapId.value=document.getElementByID("scale_fifth").value;
		//document.getElementByID("GradePay").value=0;
		//alert('abc is '+abc);
		return abc;
	}
	
}
	


function chkvalForFifthPay()
{	
	if(document.frmOtherMst.sgdMapId_fifth.value== '${sgdMapId_fifth}' && document.frmOtherMst.BasicPay.value==''){
		return true;
	}
	if(document.frmOtherMst.BasicPay.value!='')
	{
		if( document.frmOtherMst.BasicPay.value < 1)
		{
			alert("Please Enter Valid Value of Basic Pay");
			document.frmOtherMst.BasicPay.value="";
			document.frmOtherMst.BasicPay.focus();
			return false;
		}
		var BasicPay=parseFloat(document.frmOtherMst.BasicPay.value);
		if(!document.forms[0].sgdMapId_fifth.disabled)//start if only if not fixed pay 
		{		
		var scaleValue=document.forms[0].sgdMapId_fifth.options[document.forms[0].sgdMapId_fifth.selectedIndex].text;
		var initialValue=parseFloat(scaleValue.substring(0,scaleValue.indexOf("-")));
		var temp=scaleValue.substring(scaleValue.indexOf("-")+1);
		var incrementValue = parseFloat(temp.substring(0,temp.indexOf("-")));
		var endValue= parseFloat(temp.substring(temp.indexOf("-")+1));
		var sixPayScale=temp.substring(0,temp.indexOf("-"));
		var gpString=temp.substring(temp.indexOf("(")+1);
		gpString=gpString.substring(0,gpString.indexOf(")"))
		//alert(sixPayScale);
		//alert(gpString);
		var maxBasicSixPay=eval(sixPayScale)+eval(gpString);
		//alert(maxBasicSixPay);
		var minBasicSixPay=initialValue+eval(gpString);
		//alert(minBasicSixPay);
		if(sixPayScale>initialValue)
		{
			if(BasicPay < minBasicSixPay){
				alert("Please Enter Higher value of Basic Pay then scale's Initial Value");
				document.frmOtherMst.BasicPay.value="";
				document.frmOtherMst.BasicPay.focus();
				return false;
			}
			if(BasicPay > maxBasicSixPay){
				alert("Please Enter Lower value of Basic Pay then scale's End Scale's Value");
				document.frmOtherMst.BasicPay.value="";
				document.frmOtherMst.BasicPay.focus();
				return false;
			}
				
			return true;
		}
		temp=temp.substring(temp.indexOf("-")+1);
		

		if(temp.indexOf("-")>0) // this if loop is for higher scale  
		{
		var higherIncVal=temp.substring(temp.indexOf("-")+1);
		higherIncVal=higherIncVal.substring(0,higherIncVal.indexOf("-"));
		temp=temp.substring(temp.indexOf("-")+1);
		var higherEndVal=temp.substring(temp.indexOf("-")+1);
		
		// Amount is straight away equals start ,middle or last values return
		
        if(BasicPay == initialValue||BasicPay == endValue||BasicPay == higherEndVal ){
			return true;
		}
		
		if(BasicPay < initialValue){
			alert("Please Enter Higher value of Current basic then scale's Initial Value");
			document.frmOtherMst.BasicPay.value="";
			document.frmOtherMst.BasicPay.focus();
			return false;
		}
		if(BasicPay > higherEndVal){
			alert("Please Enter Lower value of Current basic then scale's End Scale's Value");
			document.frmOtherMst.BasicPay.value="";
			document.frmOtherMst.BasicPay.focus();
			return false;
		}
		
		// this code is same as normal scale as values is less than higherscale range
		if(BasicPay<=endValue)
		{
		var tempValue = BasicPay - initialValue;
		if(incrementValue!=0 && tempValue%incrementValue == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(incrementValue!=0 && tempValue%incrementValue != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.BasicPay.value="";
				document.frmOtherMst.BasicPay.focus();
				return false;	
				}
			}
		}			
		else // validation if scale is lying in higher scale range
		{
		var tempValue = BasicPay - endValue;
		if(higherIncVal!=0 && tempValue%higherIncVal == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(higherIncVal!=0 && tempValue%higherIncVal != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.BasicPay.value="";
				document.frmOtherMst.BasicPay.focus();
				return false;	
				}
			}
		}
		}//  end if loop is for higher scale 
		else  // this else loop is for normal scale retained as it is  
		{
		if(BasicPay == initialValue){
			return true;
		}
		if(BasicPay == endValue){
			return true;
		}
		if(BasicPay < initialValue){
			alert("Please Enter Higher value of Basic Pay then scale's Initial Value");
			document.frmOtherMst.BasicPay.value="";
			document.frmOtherMst.BasicPay.focus();
			return false;
		}
		if(BasicPay > endValue){
			alert("Please Enter Lower value of Basic Pay then scale's End Scale's Value");
			document.frmOtherMst.BasicPay.value="";
			document.frmOtherMst.BasicPay.focus();
			return false;
		}
		var tempValue = BasicPay - initialValue;
		if(incrementValue!=0 && tempValue%incrementValue == 0){
				return true;		
			}
			if(!${isPunished}) {
			if(incrementValue!=0 && tempValue%incrementValue != 0){
				alert("Please Enter value By Considering the Increment Value");
				document.frmOtherMst.BasicPay.value="";
				document.frmOtherMst.BasicPay.focus();
				return false;	
				}
			}
	    }		
	}//end start if only if not fixed pay 
	}
}


function GetDesignations()
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
		  
		  url= uri+'&gradeID='+ document.frmOtherMst.empGrade.value;
		  
		  var actionf="GetDesignations";
		  
		  uri='./ifms.htm?actionFlag='+actionf;
		  
		  url=uri+url; 
		  xmlHttp.onreadystatechange=gradechangeddesigs;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function gradechangeddesigs()
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

function GetScales()
{

	var v=document.getElementById("scale").length;
	for(i=1;i<v;i++)
	{
			lgth = document.getElementById("scale").options.length -1;
			document.getElementById("scale").options[lgth] = null;
	}	
		  xmlHttp=GetXmlHttpObject();
		  
		  if (xmlHttp==null)
		 
		  {
			  alert ("Your browser does not support AJAX!");
			  return;
		  } 
		  
		  var url=''; 
		  var uri='';
		  
		  url= uri+'&gdMapId='+ document.frmOtherMst.empDesg.value;
		  
		  var actionf="GetScales";
		  
		  uri='./ifms.htm?actionFlag='+actionf;
		  
		  url=uri+url; 
            
		  xmlHttp.onreadystatechange=gdChangeScale;
		  xmlHttp.open("POST",encodeURI(url),true);
		  xmlHttp.send(null);		  	
}

function gdChangeScale()
	{	
		if (xmlHttp.readyState==complete_state)
		{ 
			var scales = document.getElementById("scale");
			
								var XMLDoc=xmlHttp.responseXML.documentElement;
				    var entries = XMLDoc.getElementsByTagName('Scale-mapping');
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
                            scales.add(y,null);
           			    }
 				        catch(ex)
   				        {
   			 		       scales.add(y); 
   			   	        }	
	                
	                 }
  }
}


//by manoj for tra mapping
function taAvailed()
{
	return document.getElementById("taAvailed").checked;
}
function vehiAvailed()
{
	return document.getElementById("chkVehicalAvailed").checked;
}

/*
Made By:- Urvin Shah
Purpose:- This method will fetch the other details for the searched employee.
*/

function submitFormAuto()
{
	
	//var empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	//var url="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES&empId="+empId;
	//document.frmOtherMst.action=url;
	//document.frmOtherMst.submit();

	var empId="";
	if(document.getElementById("Employee_ID_EmpOtherSearch").value==document.getElementById("Employee_Name_EmpOtherSearch").value)
	{
	
	 empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	}
	else
	{
				
		empId=document.getElementById("Employee_Name_EmpOtherSearch").value;
	}

	document.getElementById("Employee_ID_EmpOtherSearch").value=empId;
	   
	
	empId=document.getElementById("Employee_ID_EmpOtherSearch").value;

	
	var url="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES&empId="+empId;
	document.frmOtherMst.action=url;
	document.frmOtherMst.submit();
	return true;
		
}
function onclosefn()
{
	
	if('${empAllRec}'=='true')
		window.location="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES&Employee_ID_EmpOtherSearch=${empId}&empAllRec=true";
	else
		window.location="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES";
	
}

function submitForm()
{

//var fag=chkval();
//alert('manish '+flag);
//if(flag)
//{
	//var flag=true;
	
	//alert('in right jsp...');
				
				document.getElementById("Update").disabled=true;
				

				
  var result = chkval();
  isAvailQuarter();
  if(result==false)
  {
	  hideProgressbar();
	   document.getElementById("Update").disabled=false;
	   flag=fasle;
  }
	/*var resultForFifth=chkValForFifthPay();
	if(resultForFifth==false)
	  {
		 hideProgressbar();
		   document.getElementById("Update").disabled=false;
		   flag=fasle;
	  }*/
  
  else {
  //if(flag==true)
  var uri = "./ifms.htm?actionFlag=";
  if('${empAllRec}'=='true'){
	  if(confirm("Are you sure to Calculate Componenet Values?"))
	   {
		  	   
		    var url = uri + "insertOtherData&edit=Y&MergedScreen=YES&otherId=${otherList.otherId}&empAllRec=true";
		    document.frmOtherMst.action = url;
		 	document.frmOtherMst.submit();
		 	showProgressbar("Please wait<br>While Calculate the Data...");
	   }
	  else
	   {
			hideProgressbar();
			document.getElementById("Update").disabled=false;
	   }
   
  }
   else
   {
    var confirmalert='Are you sure to Calculate Componenet Values?';
   	if(document.forms[0].updatePaybillFlg.value!=null&&document.forms[0].updatePaybillFlg.value=='y') 
   		confirmalert='Are you sure to update records and paybill as well?';
	   
   if(confirm(confirmalert))
   {

	   
       var url = uri + "insertOtherData&edit=Y&MergedScreen=YES&PreviewBill=YES&otherId=${otherList.otherId}&empAllRec=false";
	   document.frmOtherMst.action = url;
	   document.frmOtherMst.submit();
	   showProgressbar("Please wait<br>While Calculate the Data...");
   }
   else
   {
	   hideProgressbar();
	   document.getElementById("Update").disabled=false;
	   return false;
	 }
   }  
  }
  
}
//end by manoj for tra mapping

//Added By varun$harm@ for Family Planning
function enableFamilyPln()
{
	if ( document.frmOtherMst.FamilyPlannig.checked==true )
	{
		var currentFmlyPlnAmt = document.frmOtherMst.FamilyPlnAmt.value;
		var scaleAmt = document.frmOtherMst.sgdMapId1.options[document.frmOtherMst.sgdMapId1.selectedIndex].text;
			scaleAmt = scaleAmt.split('-');
		if(parseFloat(scaleAmt[1])>parseFloat(scaleAmt[0]))
		{
			document.frmOtherMst.FamilyPlnAmt.value = 0;
			document.getElementById("FamilyPlnAmt").style.display='';
			return true;
		}
		if ( currentFmlyPlnAmt == null || currentFmlyPlnAmt.trim() =='' || currentFmlyPlnAmt == 0) //i.e. if a fresh case
		{
			//make it visible
			document.getElementById("FamilyPlnAmt").style.display='';
			//set fmlyAmt as per the scale
			document.frmOtherMst.FamilyPlnAmt.value = scaleAmt[1];
		}
		//else: i.e. if not  a fresh case den let the amt as it is.
	}
	else
	{
		//if checkbox has been deselected set the value to zero.
		document.frmOtherMst.FamilyPlnAmt.value = 0;
		document.getElementById("FamilyPlnAmt").style.display='none';
	}
}
//Ended by varun$harm@

function toggleIsAvailedHRA()
{
	if( document.frmOtherMst.chkBxIsAvailedHRA.checked==true)
	{
		document.frmOtherMst.chkBxIsAvailedHRA.value=1;
		
	}
	else
	{
		document.frmOtherMst.chkBxIsAvailedHRA.value=0;
		
	}

	//isAvailQuarter();
	
}

function isAvailQuarter()
{
	//alert('Inside isAvailQuarter ');
	var empId="";
	if(document.getElementById("Employee_ID_EmpOtherSearch").value==document.getElementById("Employee_Name_EmpOtherSearch").value)
	{
	
	 empId=document.getElementById("Employee_ID_EmpOtherSearch").value;
	}
	else
	{
				
		empId=document.getElementById("Employee_Name_EmpOtherSearch").value;
	}

	empId='${otherList.hrEisEmpMst.empId}';

	//alert('cecking id '+'${empId}');
	//alert('EmpId'+empId);

	 	if(empId == "")
		{
			alert('Employee Not Found');
			return true;
					
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
					    retValue=false;     
					}
				}
			}
			
			var url = "hrms.htm?actionFlag=isAvailQuarterFromSalaryConfig&empId="+empId;
	  	
			xmlHttp.onreadystatechange = function(){
				if(xmlHttp.readyState == 4){     			
					if (xmlHttp.status == 200){	
						var XMLDocForAjax=xmlHttp.responseXML.documentElement;
						var qtrExistFlag = XMLDocForAjax.getElementsByTagName('empQtrMapping');	

						var qtrFlag1 = qtrExistFlag[0].childNodes[0].text;
						//alert('Ajax state change');
						if(qtrFlag1=="true" && document.frmOtherMst.chkBxIsAvailedHRA.checked==true)
						{
							alert('Employee is alloted quarter hence HRA cannot be availed.');
							document.frmOtherMst.chkBxIsAvailedHRA.checked=false;
							document.frmOtherMst.chkBxIsAvailedHRA.value=0;
						  						
						}
						
					
					
					}
				}
			}			
				

			xmlHttp.open("POST", encodeURI(url) , false);    
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));	
			
		}
	 	
}






function getNextPsrNoData()
{
	document.frmOtherMst.action="./ifms.htm?actionFlag=getOtherDataMerged&otherId=${NextOtherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES";
	document.frmOtherMst.method="POST";
	document.frmOtherMst.submit();
}
function getPreviousPsrNoData()
{
	document.frmOtherMst.action="./ifms.htm?actionFlag=getOtherDataMerged&otherId=${PreviousOtherId}&edit=Y&empAllRec=false&MergedScreen=YES&PreviewBill=YES";
	document.frmOtherMst.method="POST";
	document.frmOtherMst.submit();
}





function toggleIsAvailedATS50()
{
	if( document.frmOtherMst.ATS50chk.checked==true)
	{
		document.frmOtherMst.ATS50.value=1;
		document.frmOtherMst.ATS30chk.checked=false;
		document.frmOtherMst.ATS30.value=0;
	}
	else
	{
		document.frmOtherMst.ATS50.value=0;
		
	}
}

function toggleIsAvailedATS30()
{
	if( document.frmOtherMst.ATS30chk.checked==true)
	{
		document.frmOtherMst.ATS30.value=1;
		document.frmOtherMst.ATS50chk.checked=false;
		document.frmOtherMst.ATS50.value=0;
		
	}
	else
	{
		document.frmOtherMst.ATS30.value=0;
		
	}
}

function toggleIsAvailedForce25()
{
	if( document.frmOtherMst.force25chk.checked==true)
	{
		document.frmOtherMst.force25.value=1;
		document.frmOtherMst.force100chk.checked = false
		document.frmOtherMst.force100.value=0;
	}
	else
	{
		document.frmOtherMst.force25.value=0;
		
	}
}

function showHide()
{
	alert('inside function');
	if(document.getElementById("payCommissionCombo").value == 5)
	  {alert('pay commission '+document.getElementById("payCommissionCombo").value);
	//  document.getElementById("fifthPay")).style.visibility = 'hidden'; 
	  document.getElementById("sixthPay").style.visibility = "hidden";
	 // document.getElementById("fifth").style.display="none";

	  }
	else
	{
		alert('pay commission '+document.getElementById("payCommissionCombo").value);
		//document.getElementById("sixthPay").
		 document.getElementById("fifthPay").style.visibility = "hidden";
		 document.getElementById("fifth").style.visibility = "hidden";
	}

		
}
function toggle(){
	
	if(document.getElementById("payCommissionCombo").value == 6)
	{
	
	//alert('inside toggle function');
		document.getElementById("fifth").style.display="none";
		document.getElementById("scale_fifth").mandatory="false";
		document.getElementById("BasicPay").mandatory="false";
		document.getElementById("scale").mandatory="true";
		document.getElementById("initialBasic").mandatory="true";
		document.getElementById("PayinPayband").mandatory="true"; 
		document.getElementById("GradePay").mandatory="true"; 
		
		document.getElementById("sixthOne").style.display="block";
		document.getElementById("sixthTwo").style.display="block";
		
	}
	else
	{
		document.getElementById("sixthOne").style.display="none";
		document.getElementById("sixthTwo").style.display="none";


		document.getElementById("scale").mandatory="false";
		document.getElementById("initialBasic").mandatory="false";
		document.getElementById("PayinPayband").mandatory="false"; 
		document.getElementById("GradePay").mandatory="false"; 
		document.getElementById("scale_fifth").mandatory="true";
		document.getElementById("BasicPay").mandatory="true";

		
		document.getElementById("fifth").style.display="block";
	} 

}
/*function validateFields()
{

	if(document.getElementById("payCommissionCombo").value == 6)
	{

		if(document.getElementById("PayinPayband")!= null && document.getElementById("PayinPayband").value =="")
		{
			
			alert("Plase Enter the Pay In PayBand ");
			document.getElementById("PayinPayband").focus();
			return false;
		} 
		if(document.getElementById("initialBasic") != null && document.getElementById("initialBasic").value=="" 
		{
			alert("please Enter  the Basic's Value");
			document.getElementById("initialBasic").focus();
			return false;
		}
		if(document.getElementById("scale") != null && document.getElementById("scale").value=="" 
		{
			alert("please select the Scale");
			document.getElementById("scale").focus();
			return false;
		}

		//if(document.getElementById("PayinPayband")!= null && isNumber(document.getElementById("PayinPayband").value ))
		//{
			
		//	alert("Plase Enter the valid amont for  Pay In PayBand ");
		//	document.getElementById("PayinPayband").focus();
		//}
		//turn isNumber(document.getElementById("PayinPayband").value,"Plase Enter the valid amont for  Pay In PayBand ");

		return true;
		
	}
	else
	{

		
	}
	
}
function isNumber(field,alrtString)
{
  var str=field.value;
  if (!str || trim(str)=="") { return  true; }
  re1 = /[^a-z^0-9\/\." "]/gi;
  if(str.search(re1) < 0)
  {
  	return true;
  }
  else
  {
  	alert(alrtString);
  	field.value='';
  	field.focus();
  	return false;
  }
  //return (str.search(re1) < 0 ? true : false);
} */

/*function toggleOnLoad(){

	var payCommissionId='${resValue2.payCommissionId}';
	alert('Pay Commission '+payCommissionId);
	if(payCommissionId== 6)
	{		 
		document.getElementById("fifth").style.display="none";
		document.getElementById("scale_fifth").mandatory="false";
		document.getElementById("BasicPay").mandatory="false";
		document.getElementById("scale").mandatory="true";
		document.getElementById("initialBasic").mandatory="true";
		document.getElementById("PayinPayband").mandatory="true"; 
		document.getElementById("GradePay").mandatory="true"; 
		
		document.getElementById("sixthOne").style.display="block";
		document.getElementById("sixthTwo").style.display="block";
		
	}
	else
	{		
		document.getElementById("sixthOne").style.display="none";
		document.getElementById("sixthTwo").style.display="none";
		
		document.getElementById("scale").mandatory="false";		
		document.getElementById("initialBasic").mandatory="false";
		document.getElementById("PayinPayband").mandatory="false"; 
		document.getElementById("GradePay").mandatory="false"; 
		document.getElementById("scale_fifth").mandatory="true";
		document.getElementById("BasicPay").mandatory="true";
		
		document.getElementById("fifth").style.display="block";		
	}

}*/
function displayRow(){   
	var row = document.getElementById("captionRow");
		if (row.style.display == '') 
			 row.style.display = 'none';

		else row.style.display = '';    }
function toggleOnLoad(){

	//alert('function called');
	//alert(${resValue2.payCommissionId});
	if( ${resValue2.payCommissionId}== 6)
	{		 
		//document.getElementById("fifth").style.display="none";
		//document.getElementById("scale_fifth").mandatory="false";
		//document.getElementById("BasicPay").mandatory="false";
		//document.getElementById("scale").mandatory="true";
		//document.getElementById("initialBasic").mandatory="true";
		//document.getElementById("PayinPayband").mandatory="true"; 
		//document.getElementById("GradePay").mandatory="true"; 
		//document.getElementById("sixthOne").style.display="block";
		//document.getElementById("sixthTwo").style.display="block";
		//alert('Hummmmmmmmm');
		//var row = document.getElementById('hideRow');
		//alert(''+row.style.display );
		//row.style.display = "block";	
		
	}
	else
	{		
		//document.getElementById("sixthOne").style.display="none";
		//document.getElementById("sixthTwo").style.display="none";
		
		//document.getElementById("scale").mandatory="false";		
		//document.getElementById("initialBasic").mandatory="false";
		//document.getElementById("PayinPayband").mandatory="false"; 
		//document.getElementById("GradePay").mandatory="false"; 
		//document.getElementById("scale_fifth").mandatory="true";
		//document.getElementById("BasicPay").mandatory="true";
		
		//document.getElementById("fifth").style.display="block";
		
		//document.getElementById("hideGradePayCaption").style.display="none"
		//var row = document.getElementById("hideRow");
		
		//row.style.display = 'none';	
		//alert('Hummmmmmmmmmmmmm');
		//document.getElementById("hideRow").style.display="none";
	}

}
function toggleVisibility(){

	 
	if (document.getElementById("sixthPay").style.visibility=="hidden"){
		document.getElementById("sixthPay").style.visibility="visible";
		}
	else {
		document.getElementById("sixthPay").style.visibility="hidden";
		}
	}

function toggleIsAvailedForce100()
{
	if( document.frmOtherMst.force100chk.checked==true)
	{
		document.frmOtherMst.force100.value=1;
		document.frmOtherMst.force25chk.checked = false;
		document.frmOtherMst.force25.value=0;
	}
	else
	{
		document.frmOtherMst.force100.value=0;
		
	}
}

function displayOrderDetails()
{

	  
	var selScale;
	
	var prevScale = document.getElementById("sgdPrev").value;
	//alert('prev scale before '+prevScale);
	//alert(document.getElementById("scale").value);

	
	if(document.getElementById("scale_fifth")!=null && ${resValue2.payCommissionId}==5)
	{
		selScale = document.getElementById("scale_fifth").value;
	}
	else if(document.getElementById("scale")!=null && ${resValue2.payCommissionId}==6)
	{
		selScale = document.getElementById("scale").value;
	}
	else
	{
		selScale=prevScale;
	}

	
	if(selScale!=prevScale)
	{
		 
		document.getElementById("orderDataFlag").value="Y";
		document.getElementById("orderDetails").style.display="block";
		document.getElementById("orderDetails1").style.display="block";
		document.getElementById("orderDetails2").style.display="block";
		 
	}
	else
	{
		document.getElementById("orderDataFlag").value="N";
		document.getElementById("orderDetails").style.display="none";
		document.getElementById("orderDetails1").style.display="none";
		document.getElementById("orderDetails2").style.display="none";
		 
	}
	
}

--></script>
<body onload="calculateGradePay();calculatePB();calculateBasic();">
<hdiits:form name="frmOtherMst"  validate="true" method="POST" 
             action="javascript:submitForm();"  >

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	    <c:choose>
	    <c:when test="${updatePaybillFlg eq 'y'}" >
		<li class="selected"><a href="#" rel="tcontent1"><b>VIEW DRAFT SALARY</b></a></li>
		</c:when>
		<c:otherwise>
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="OT.otherDetailMaster" bundle="${commonLables}" /></b></a></li>
        </c:otherwise>
        </c:choose>
    </ul>
</div>

<div id="tcontent1" style="background-color: #E8E3E3;border-style: inset;border-color:#B24700 ;border-width: thin">
<table><tr><td><hdiits:hidden name="updatePaybillFlg" default="${updatePaybillFlg}" />
			     <hdiits:hidden name="paybillMonth" default="${paybillMonth}" />
			     <hdiits:hidden name="paybillYear" default="${paybillYear}" />
			     <hdiits:hidden name="sgdMapId" default="${sgdMapId}" />
			     
			     
</td></tr></table>	
	<c:if test="${empAllRec eq 'false'}">
	<table  width="85%" align="center">
	    <tr>
			<td>
			     <c:if test="${updatePaybillFlg eq 'y'}" >
			     <a href="./ifms.htm?actionFlag=fillPaybillData&paybillYear=${paybillYear}&paybillMonth=${paybillMonth}&updatePaybillEmpId=${otherList.hrEisEmpMst.orgEmpMst.empId}&searchData=y">Back</a>
			     </c:if>
			     
		</tr>
		
		<tr style="display: none;">
		<td align="right">
		<c:if test="${PreviousPsrNo ne '' and PreviousPsrNo ne null}">
		<a href="#" onclick="getPreviousPsrNoData();"><img src="images\previous1.png" alt="Previous PSR NO-${PreviousPsrNo} BILL-${PreviousBillNo}"></a>
		</c:if>
		<hdiits:caption captionid="Bill No" caption= "Bill Id"></hdiits:caption>
		&nbsp;&nbsp;<hdiits:text readonly="true" default ="${CurrBillNo}" name="BillNo" maxlength="10" size="3" style="  text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--<hdiits:caption captionid="PSR No" caption= "PSR No"></hdiits:caption>
		&nbsp;&nbsp;<hdiits:text readonly="true" default ="" name="PSRNo" maxlength="10" size="3" style=" text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;"/>
		&nbsp;&nbsp;-->
		<c:if test="${NextPsrNo ne '' and NextPsrNo ne null}">
		<a href="#" onclick="getNextPsrNoData();"><img src="images\next1.png" alt="Next PSR NO-${NextPsrNo} BILL-${NextBillNo}"></a>
		</c:if>
		</td>
		</tr>
		
		<c:if test="${listSize ge 2}">
		<c:set value="display:none" var="displayStyle"/>
		</c:if>
		<tr style="${displayStyle}">
			<td align="center">
				<fieldset style="background: #eeeeee;">
				<!--<hdiits:submitbutton type="button" captionid="populateDetail" bundle="${commonLables}"  name="details" onclick="return chkValue()"/>	
				-->
				</fieldset>
			</td>
		</tr>	
	</table>
	<table border="1" align="center" width="85%">
	<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpOtherSearch"/>
						<jsp:param name="formName" value="frmOtherMst"/>
						<jsp:param name="functionName" value="submitFormAuto"/>
					</jsp:include>
			</td>
		</tr>
		</table>
		</c:if>
	
<table align="center" width="85%" border="0">
	
	<tr>
		<!-- <td><hdiits:caption captionid="OT.otherId" bundle="${commonLables}"/></td>
		<td><hdiits:text name="otherId" default="" caption="otherId"  maxlength="10" readonly="true" style="background:gray;color:white" size="20"/> 
		</td> -->
	</tr>
	<tr> </tr> <tr> </tr>
	<tr>
		<hdiits:hidden name="empName" default="${otherList.hrEisEmpMst.empId}" />
		<TD width="12%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empName" bundle="${commonLables}"></hdiits:caption></b>
		</TD><td width="30%" align="left" ><hdiits:text name="empName" default="${empName}" caption="empName" readonly="true" size="25"/>
			</td>
		
		<!--<td width="12%" align="center"  class="fieldLabel" >
-->
<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<TD  width="16%" align="left"  class="fieldLabel" style="display:none">
<b><hdiits:caption captionid="OT.empId" bundle="${commonLables}"></hdiits:caption></b> </td>
		<td width="30%" align="left" style="display:none"><hdiits:text name="empId" default="${otherList.hrEisEmpMst.empId}"  caption="empId" readonly="true" size="25"/></td>
		</td>
			<TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.sevarthId" bundle="${commonLables}"></hdiits:caption></b> </td>
		<td width="30%" align="left"><hdiits:text name="sevarthId" default="${otherList.hrEisEmpMst.sevarthEmpCode}"  caption="sevarthId" readonly="true" size="25"/></td>
		
		</tr><tr>
		<!--<td width="4%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			--><TD  width="12%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empDesg"  bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		
		<td width="30%" align="left" ><hdiits:text name="empDesg" default="${desigName}" caption="empDesg" readonly="true" size="25"/>
		</td>
		
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		
		<TD width="16%" align="left"  class="fieldLabel" > 
<b><hdiits:caption captionid="OT.office" bundle="${commonLables}"></hdiits:caption></b></td>
<td colspan="2" width="40%" align="left"><hdiits:text id="office" name = "office" readonly="true" default="${officeName}" title="${officeName}" caption = "office" size="40"></hdiits:text></td>
		
		
     <%-- <td><hdiits:select name="empNameSel" size="1" readonly="true" 
                       captionid="OT.empName"   
                       validation="sel.isrequired"
                       bundle="${commonLables}" 
                       sort="false">
        <hdiits:option value="">---------------Select---------------</hdiits:option>
        
		 
         <c:forEach items ="${resValue.empList}" var="list">
			
			<c:choose>

			<c:when test="${list.empId == otherList.hrEisEmpMst.empId}">
			  
 			    <hdiits:option  value="${list.empId}" selected="true"> ${list.orgEmpMst.empFname} ${list.orgEmpMst.empMname} ${list.orgEmpMst.empLname}</hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.empId}"> ${list.orgEmpMst.empFname} ${list.orgEmpMst.empMname} ${list.orgEmpMst.empLname} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			</hdiits:select>
			 --%>
     <!--    </td><td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
        <TD width="16%" align="left"   class="fieldLabel" >
		<b><hdiits:caption captionid="OT.empGrade" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td  width="30%" align="left" ><hdiits:text name="empGrade" default="${gradeName}" caption="empGrade" readonly="true"  size="25"/> -->
	<%--	<hdiits:select name="empGrade" size="1"
                       captionid="OT.empGrade"  
                       sort="false"
                       mandatory="true" 
                       validation="sel.isrequired"
                       bundle="${commonLables}" 
                       onchange="GetDesignations()" >
                       
        <hdiits:option value="">---------------Select---------------</hdiits:option>
        
        
        <c:forEach items ="${resValue2.gradeList}" var="list">
			
			<c:choose>

			
			<c:when test="${list.gradeId == otherList.hrEisEmpMst.orgEmpMst.orgGradeMst.gradeId}">
			 
 			    <hdiits:option  value="${list.gradeId}" selected="true"> ${list.gradeName} </hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.gradeId}"> ${list.gradeName} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
        
        </hdiits:select > --%>
	
	</tr>
<tr>
	



</tr>
<tr>
<td  width="16%" align="left"  class="fieldLabel" >
<b><hdiits:caption captionid="OT.gpf" bundle="${commonLables}"></hdiits:caption></b></td>
<td width="30%" align="left" >
<!--<td  width="10%" align="center">
--><hdiits:text id="gpf" name = "gpf" readonly="true" default="${gpfAccNo}" caption = "gpf" size="25"></hdiits:text></td>
<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>

<hdiits:hidden name="payCommissionCombo"  default="${payCommissionId}" />
<TD width="16%" align="left"   class="fieldLabel" >
		<b><hdiits:caption captionid="OT.payCommission" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left">
		<hdiits:text name="payCommissionNameText"  default="${payCommissionName}" readonly="true" size="25"/>
</td>


 </tr>

 <tr> </tr>	
	
	
	<tr >
	
		<!--<b><hdiits:caption captionid="hr.other.post"  bundle="${commonLables}"></hdiits:caption></b>-->
		 	<%--<hdiits:text name="empPost" default="${postName}" caption="empPost" readonly="true" size="25"/>
	<hdiits:select id="desig" name="empDesg" size="1"
                       captionid="OT.empDesg"   
                       validation="sel.isrequired"
                       mandatory="true" 
                       sort="false"
                       bundle="${commonLables}"  onchange="GetScales()">
        <hdiits:option value="">---------------Select---------------</hdiits:option>
        <!--<hdiits:option value="1">Class 1</hdiits:option>-->
         <c:forEach items ="${resValue2.desgList}" var="list">
			
			 <c:choose>
			<c:when test="${list.gdMapId == gdMapId}">
			  
 			    <hdiits:option  value="${list.gdMapId}" selected="true"> ${list.orgDesignationMst.dsgnName} </hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.gdMapId}"> ${list.orgDesignationMst.dsgnName} </hdiits:option>
			</c:otherwise>
			</c:choose>
			</c:forEach>      
        
        
        </hdiits:select > --%>
		
	</tr>

<tr>
 


<!--<td  width="16%" align="left"><hdiits:caption captionid="OT.gradePay" bundle="${commonLables}"></hdiits:caption></TD>
<td width="30%" align="left" ><hdiits:text name="gradePay" default="0" caption="gradePay" readonly="true" size="25"/></td> 
-->


<TD width="16%" align="left"   class="fieldLabel"  >
		<b><hdiits:caption captionid="OT.payScale" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" >
		<hdiits:select id="scale" name="sgdMapId1" size="1" 
                       captionid="OT.empScale"                          
                    	readonly="true"
                       sort="false"
                       bundle="${commonLables}" onchange="document.frmOtherMst.initialBasic.value='';document.frmOtherMst.PayinPayband.value='';calculateGradePay();clearPayBand();calculateBasic();displayOrderDetails();" >
                       
        <hdiits:option value="">---------------Select---------------</hdiits:option>      
        <c:forEach items ="${resValue2.scaleList}" var="list">
			
			 <c:choose>
			<c:when test="${list.hrEisScaleMst.scaleId == scaleId}">
			            <c:choose>
						  <c:when test="${list.hrEisScaleMst.scaleHigherIncrAmt == 0}">
						  	<c:choose>
						  		<c:when test="${list.hrEisScaleMst.scaleGradePay == 0}" >
						  			<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt} </hdiits:option>																								  		
						  		</c:when>
						  		<c:otherwise>
						  			<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleEndAmt}-(${list.hrEisScaleMst.scaleGradePay}) </hdiits:option>
						  									  			
						  		</c:otherwise>
						  	</c:choose>
						  </c:when>
						  <c:otherwise>
						  		<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt}-${list.hrEisScaleMst.scaleHigherIncrAmt}-${list.hrEisScaleMst.scaleHigherEndAmt} </hdiits:option>
						  </c:otherwise>
			 			</c:choose>
 			    
		    
			  </c:when>
			  <c:otherwise>
			            <c:choose>
						  <c:when test="${list.hrEisScaleMst.scaleHigherIncrAmt == 0}">
						    <c:choose>
						  		<c:when test="${list.hrEisScaleMst.scaleGradePay == 0}" >
						  		  <hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt} </hdiits:option>
						  		</c:when>
						  		<c:otherwise>
							  		<hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleEndAmt}-(${list.hrEisScaleMst.scaleGradePay})</hdiits:option>
					  		   </c:otherwise>
						  	</c:choose>
						  </c:when>
						  <c:otherwise>
						  		<hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt}-${list.hrEisScaleMst.scaleHigherIncrAmt}-${list.hrEisScaleMst.scaleHigherEndAmt} </hdiits:option>
						  </c:otherwise>
			 			</c:choose>
			
			</c:otherwise>
			</c:choose>
			</c:forEach>    
        </hdiits:select >
        
        
        <c:forEach items ="${resValue2.scaleList}" var="list">
        <script>
        
        </script>
         	<c:if test="${list.hrEisScaleMst.scaleId == scaleId}">
        		<hdiits:hidden name="sgdPrev" id="sgdPrev" default="${list.sgdMapId}" />
        		 
       		 </c:if>
        </c:forEach>
        
	</td>
	<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	<td  width="16%" align="left"  class="fieldLabel" >
<b><hdiits:caption captionid="OT.PayInPayBand" bundle="${commonLables}"></hdiits:caption></b><Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)</td>
<td width="30%" align="left" >
<hdiits:text id="PayInPayBand" name = "PayInPayBand" readonly="true" default="${otherList.otherCurrentBasic-grdPay}" caption = "PayInPayBand" size="25"></hdiits:text></td>
	

</tr>



<div id="divFifth" name="divFifth" >
	<tr> </tr>
	 <tr> </tr>	
	<tr id="fifth" style="display:none" ><TD width="16%" align="left"   class="fieldLabel">
		<b><hdiits:caption captionid="OT.payBand" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" >
		<hdiits:select id="scale_fifth" name="sgdMapId_fifth" size="1" readonly="true"
                       captionid="OT.empScale"                                            
                       sort="false"
                       bundle="${commonLables}" onchange="document.frmOtherMst.BasicPay.value='';calculateGradePay();displayOrderDetails();" >
                       
        <hdiits:option value="">---------------Select---------------</hdiits:option>      
        <c:forEach items ="${resValue2.scaleListForFifthPay}" var="list">
			
			 <c:choose>
			<c:when test="${list.hrEisScaleMst.scaleId == scaleId}">
			            <c:choose>
						  <c:when test="${list.hrEisScaleMst.scaleHigherIncrAmt == 0}">
						  	<c:choose>
						  		<c:when test="${list.hrEisScaleMst.scaleGradePay == 0}" >
						  			<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt} </hdiits:option>																								  		
						  		</c:when>
						  		<c:otherwise>
						  			<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleEndAmt}-(${list.hrEisScaleMst.scaleGradePay}) </hdiits:option>
						  									  			
						  		</c:otherwise>
						  	</c:choose>
						  </c:when>
						  <c:otherwise>
						  		<hdiits:option  value="${list.sgdMapId}" selected="true"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt}-${list.hrEisScaleMst.scaleHigherIncrAmt}-${list.hrEisScaleMst.scaleHigherEndAmt} </hdiits:option>
						  </c:otherwise>
			 			</c:choose>
 			    
		    
			  </c:when>
			  <c:otherwise>
			            <c:choose>
						  <c:when test="${list.hrEisScaleMst.scaleHigherIncrAmt == 0}">
						    <c:choose>
						  		<c:when test="${list.hrEisScaleMst.scaleGradePay == 0}" >
						  		  <hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt} </hdiits:option>
						  		</c:when>
						  		<c:otherwise>
							  		<hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleEndAmt}-(${list.hrEisScaleMst.scaleGradePay})</hdiits:option>
					  		   </c:otherwise>
						  	</c:choose>
						  </c:when>
						  <c:otherwise>
						  		<hdiits:option value="${list.sgdMapId}"> ${list.hrEisScaleMst.scaleStartAmt}-${list.hrEisScaleMst.scaleIncrAmt}-${list.hrEisScaleMst.scaleEndAmt}-${list.hrEisScaleMst.scaleHigherIncrAmt}-${list.hrEisScaleMst.scaleHigherEndAmt} </hdiits:option>
						  </c:otherwise>
			 			</c:choose>
			
			</c:otherwise>
			</c:choose>
			</c:forEach>    
        </hdiits:select >
        
         <c:forEach items ="${resValue2.scaleListForFifthPay}" var="list">
         	<c:if test="${list.hrEisScaleMst.scaleId == scaleId}">
        		<hdiits:hidden name="sgdPrev" id="sgdPrev" default="${list.sgdMapId}" />
       		 </c:if>
        </c:forEach>
		</td>
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<TD  width="16%" align="left"  class="fieldLabel" >
		
		<b><hdiits:caption captionid="OT.basicPay" bundle="${commonLables}"/></b>
		</TD>
		<td ><hdiits:number name="BasicPay" id="BasicPay" readonly="true" default="${otherList.otherCurrentBasic}" caption="Basic Pay"    validation="txt.isrequired,txt.isnumber"   maxlength="10"   size="25" />	</td>
	</tr>
</div>

<div id="divSixth"> 
	
	<tr id="hideRow" style="display:true" >
	<c:if test="${resValue2.payCommissionId  eq 6}">
		<td >	<hdiits:caption captionid="OT.GradePay" bundle="${commonLables}"></hdiits:caption>
		<Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)	</td>
		<td >	<hdiits:number name="GradePay" id="GradePay"  readonly="true"  maxlength="10"  default="${grdPay}"  size="23"  />	</td>
		
	</c:if>
	<c:if test="${resValue2.payCommissionId  eq 5}">
	<td></td>
	<td></td>
	</c:if>
		
		<td width="16%" align="left"  style="display:none" ><b><hdiits:caption captionid="OT.PayInPayBand" bundle="${commonLables}" ></hdiits:caption></b></td>
		<td width="30%" align="left" style="display:none" ><hdiits:number name="PayinPayband" id="PayinPayband" readonly="false"   caption="Pay In Payband"  maxlength="10"   size="25"    onblur="calculateBasic()"     />	</td>
		
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="currentBasic" bundle="${commonLables}"/></b>
		<Font size="3" face='Rupee Foradian'> (<Font size="3" face='Rupee Foradian'>`</Font>)
		<BR/><font size="1">(PayInPB + GPay)</font>
		</TD>
		<td ><hdiits:number name="initialBasic" id="initialBasic"   readonly="true"   default="${otherList.otherCurrentBasic}" caption="Current basic"      maxlength="10"   size="25"  />	</td>
		 
	</tr>
		
	
	
<tr>
	
		
	 </tr>
</div>
	 
	 
	<tr id="orderDetails" style="display:none" >
	<td><B><U>Order Details </U></B></td></tr>
	<tr id="orderDetails1" style="display: none" >
	<td>Order Number: <font style="color: red;">*</font> &nbsp;&nbsp;&nbsp;</td>
	<td><input type="text"  name="orderNo" id="orderNo" /></td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td  >Dated: <font style="color: red;">*</font></td>
	<td><hdiits:dateTime name="orderDate" caption="Date"   validation="txt.isdt" /></td>
	</tr>
	<tr id="orderDetails2" style="display: none" >
	<td>Reason: <font style="color: red;">*</font> &nbsp;&nbsp;&nbsp;</td>
	<td>
<hdiits:select name="Reason" id="Reason">

<hdiits:option selected="true" value="-1">--------select--------</hdiits:option>
<c:forEach items="${reasonList}" var="rsn">

	<hdiits:option  value="${rsn.lookupId}">${rsn.lookupName}</hdiits:option>
</c:forEach>
</hdiits:select>
	
	</td>
	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td><td  >Remarks: <font style="color: red;">*</font> </td>
	<td   ><input type="text"  name="Remarks" id="Remarks" />
	<hdiits:hidden name="orderDataFlag" id="orderDataFlag" default="N"/>
	</td>
	</tr>
	
	
	
	
	
	
	<tr>
	
	    <!--<TD width="16%" align="left"   class="fieldLabel" >
		<b><hdiits:caption captionid="OT.city" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" ><hdiits:select name="empCity" size="1"
                       captionid="OT.city"   
                       validation="sel.isrequired"                       
                       sort="false"
                       bundle="${commonLables}" > <c:out value="cityList"/>
        <hdiits:option value="">---------------Select---------------</hdiits:option>
       
        <c:forEach var="cityList" items="${cityList}">
        
        <c:choose>
			<c:when test="${cityList.cityId == otherList.city}">
			  
 			    <hdiits:option  value="${cityList.cityId}" selected="true"> ${cityList.cityName} </hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${cityList.cityId}"> ${cityList.cityName}</hdiits:option>
			</c:otherwise>
			</c:choose>
        </c:forEach>
        </hdiits:select >
		</td>
		
		
		--><TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.Phy_handi"    bundle="${commonLables}" ></hdiits:caption></b>
		</TD>
		
		<td width="30%" align="left" >
		<hdiits:hidden name="is_handicapped"  default="${otherList.phyChallenged}"/>
		<c:set var="flag" value="TRUE" ></c:set>
		<c:choose>
		<c:when test="${otherList.phyChallenged=='TRUE'}" >
			<hdiits:radio readonly="true" captionid="OT.YES"  caption="YES" bundle="${commonLables}" value="TRUE" default="TRUE" name="is_handicapped" id="is_handicapped" />
			<hdiits:radio readonly="true"  captionid="OT.NO" caption="NO" bundle="${commonLables}" value="FALSE"  name="is_handicapped" id="is_handicapped"/>
		</c:when> 
		<c:otherwise>
			<hdiits:radio readonly="true"  captionid="OT.YES"  caption="YES" bundle="${commonLables}" value="TRUE"  name="is_handicapped"  />
			<hdiits:radio readonly="true" captionid="OT.NO" caption="NO" bundle="${commonLables}" value="FALSE" default="FALSE" name="is_handicapped" />
		</c:otherwise>
     	</c:choose>
        </td>
		
		
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		
		<td style="display:none">
			<c:choose>
			<c:when test="${otherList.isAvailedHRA eq '1'}">
				<input type="checkbox" id="isAvailedHRA" name="chkBxIsAvailedHRA" value="1" checked onclick="javascript:toggleIsAvailedHRA()">
				<b><hdiits:caption captionid="OT.IsAvailedHRA" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
				
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="isAvailedHRA" name="chkBxIsAvailedHRA" value="0" onclick="javascript:toggleIsAvailedHRA()">
				<b><hdiits:caption captionid="OT.IsAvailedHRA" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
			</c:otherwise>
			</c:choose>
		</td>
		
	</tr><tr> </tr>
	<tr style="display:none">
	<!-- Modified By Mrugesh for hiding income-tax text box -->
		<!-- <td width="16%" align="left"  ><b><hdiits:caption captionid="OT.incomeTax" bundle="${commonLables}"/></b></td>
		<td width="30%" align="left" ><hdiits:number id="incometax" name="incometax" default="${otherList.incomeTax}" captionid="OT.incomeTax" bundle="${commonLables}"  validation="txt.isnumber" maxlength="10"   size="25"/> </td> -->
		

    <!-- <td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>  -->
    <hdiits:hidden id="incometax" name="incometax"></hdiits:hidden>
    <!-- Ended by Mrugesh -->
		<td width="16%" align="left" colspan="1"><b><hdiits:caption  captionid="incrementDate" bundle="${commonLables}"/></b></td>
		<td  width="30%" align="left" colspan="2"><hdiits:dateTime captionid="incrementDate" caption="incrementDate"  name="IncrementDate" default="${otherList.incrementDate}"  bundle="${commonLables}" validation="txt.isdt" /> </td>
					<td width="16%" align="left"   colspan="1"><b><hdiits:caption id="effctivedate" captionid="effctivedate" bundle="${commonLables}"/></b></td>
		<td  width="30%" align="left" colspan="2"><hdiits:dateTime caption="effctivedate"  name="effctivedate" default="${otherList.commissionAcceptanceDate}"  captionid="effctivedate" bundle="${commonLables}"  validation="txt.isdt"/> </td>
	</tr>
	
	
	<!-- <tr id="cmbQuaterType" style="">
	
		<TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="OT.quaterType" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" ><hdiits:select id="quaterType" name="quaterType" size="1"
                       captionid="OT.quaterType"   
                        condition="chkquaterAvail()"
                       sort="false"
                       bundle="${commonLables}">
                       
        <hdiits:option value="" >---------------Select---------------</hdiits:option>
        <c:forEach var="list" items="${quaterTypeList}">
         
         <c:choose>
			<c:when test="${list.quaId == otherList.hrQuaterTypeMst.quaId}">
			 
 			    <hdiits:option  value="${list.quaId}" selected="true"> ${list.quaType} </hdiits:option>
		
			  </c:when>
			  <c:otherwise>
			  
			<hdiits:option value="${list.quaId}"> ${list.quaType}</hdiits:option>
			</c:otherwise>
			</c:choose>
         </c:forEach>
        </hdiits:select >
		</td>
	</tr><tr> </tr>  -->
	<tr style="display:none">
	 
		<td align="left" colspan="2" style="display:none">
			<%-- <hdiits:checkbox name="medAllowance" value="1" />  --%>
			<c:choose>
			<c:when test="${otherList.medAllowance eq 1}">
				<input type="checkbox" id="medAllowance" name="medAllowance" value="1" checked>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="medAllowance" name="medAllowance" value="1">
			</c:otherwise>
			</c:choose>
			<b><hdiits:caption captionid="OT.Medical" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
			<TD>&nbsp;</TD>
		<td align="left" colspan="2" style="display:none">
			<%-- <hdiits:checkbox name="uniformAvailed" value="3"/> --%>
			<c:choose>
			<c:when test="${otherList.uniformAvailed eq 1}">
				<input type="checkbox" id="uniformAvailed" name="uniformAvailed" value="1" checked>
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="uniformAvailed" name="uniformAvailed" value="1">
			</c:otherwise>
			</c:choose>
		
			<b><hdiits:caption captionid="OT.Uniform" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		
	</tr>
	<%-- Ended By Paurav --%>
	

	<!--<tr>
		<td>
			<c:choose>
			<c:when test="${otherList.isAvailedHRA eq '1'}">
				<input type="checkbox" id="isAvailedHRA" name="chkBxIsAvailedHRA" value="1" checked onclick="javascript:toggleIsAvailedHRA()">
				<b><hdiits:caption captionid="OT.IsAvailedHRA" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
				
			</c:when>
			<c:otherwise>
				<input type="checkbox" id="isAvailedHRA" name="chkBxIsAvailedHRA" value="0" onclick="javascript:toggleIsAvailedHRA()">
				<b><hdiits:caption captionid="OT.IsAvailedHRA" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	

	--><!-- added by manoj for tra mapping -->
	<tr  style="display:none">
			<td colspan="5" >
			<c:choose>
			
			<c:when test="${hrEmpTraMpg.id ne 0}">
				<input type="checkbox" name="taAvailed" id="taAvailed" value="<c:out value="${hrEmpTraMpg.id}"/>" checked>
			</c:when>
			<c:otherwise>
				<input type="checkbox" name="taAvailed" id="taAvailed" value="0">
			</c:otherwise>
			</c:choose>
				
				
			</td>
	</tr>
	<c:if test="${otherList.hrEisSgdMpg.hrEisScaleMst.hrPayCommissionMst.id eq 2500341}">
	<tr  style="display:none">
	<td align="left" colspan="3">
	 
		 
		
		 
		
		<input type="checkbox" name="ATS30chk" id="ATS30chk" onclick="javascript:toggleIsAvailedATS30()"   />
		
		<b><hdiits:caption captionid="OT.ATS30" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
		<hdiits:text caption="ATS30" validation="txt.isnumber" style="display:none" id="ATS30" name="ATS30"></hdiits:text>
	</td>
	<td colspan="2">
		<input type="checkbox" name="ATS50chk" id="ATS50chk" onclick="javascript:toggleIsAvailedATS50()" ><b><hdiits:caption captionid="OT.ATS50" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
		<hdiits:text caption="ATS50" validation="txt.isnumber" style="display:none" id="ATS50" name="ATS50"></hdiits:text>
	</td>
	
	
	
	</tr >
	
	<tr style="display:none">
	<td align="left" colspan="3">
		<input type="checkbox" name="force100chk" id="force100chk" onclick="javascript:toggleIsAvailedForce100()" ><b><hdiits:caption captionid="OT.Force100" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
		<hdiits:text caption="Force100" validation="txt.isnumber" style="display:none" id="force100" name="force100"></hdiits:text>
	</td>
	<td colspan="2"> 
		<input type="checkbox" name="force25chk" id="force25chk" onclick="javascript:toggleIsAvailedForce25()" ><b><hdiits:caption captionid="OT.Force25" bundle="${commonLables}"></hdiits:caption></b>&nbsp;
		<hdiits:text caption="Force25" validation="txt.isnumber" style="display:none" id="force25" name="force25"></hdiits:text>
	</td>
	
	
	
	</tr>
	</c:if>
	<script>
	
	</script>
	
	<tr> <td> </td> </tr>
	<tr id="traTable" style="display:none">
	<td colspan="4">
		<table  width="100%">
		<tr>
			<td>
				<jsp:include page="/WEB-INF/jsp/hrms/eis/HrEmpTraMpgMaster.jsp">
					<jsp:param name="distance" value="${hrEmpTraMpg.distance}"/>
					<jsp:param name="vehicalAvailed" value="${hrEmpTraMpg.vehicalAvailed}"/>
					<jsp:param name="vehiNo" value="${hrEmpTraMpg.vehicalNo}"/>
					<jsp:param name="vehicalAvailDate" value="${hrEmpTraMpg.vehicalAvailDate}"/>
					<jsp:param name="vehicalLeaveDate" value="${hrEmpTraMpg.vehicalLeaveDate}"/>
				</jsp:include>
			</td>
		</tr>
		</table>	
	</td>
	</tr>
	<%-- Ended By manoj for tra mapping --%>
	
	</table>
	<fmt:setBundle basename="resources.payroll.payrollLables" var="Lables" scope="request"/>
	<div align="center">
    <%--<hdiits:formSubmitButton name="Update" id="Update" type="button" caption="Update" onclick="return submitForm();"/>
     --%>
     <hdiits:submitbutton  name = "Update" id ="Update" captionid="OT.update" bundle="${commonLables}" onclick="return submitForm();" title="Clicking this will Calculate Components shown below"/>
   <%--  <hdiits:button name="btnClose1" type="button" captionid="eis.close" bundle="${Lables}" onclick="onclosefn()" />         --%>
    </div>
	<hr color="#BD6C03" size="4">
	<span style="background-color: #ffffee">
		<jsp:include page="/WEB-INF/jsp/hrms/eis/empAllowMapViewMerged.jsp">
	  		<jsp:param name="empId" value="${otherList.hrEisEmpMst.orgEmpMst.empId}" />
	    	<jsp:param name="otherId" value="${otherList.otherId}" />
	    	<jsp:param name="updatePaybillFlg" value="${updatePaybillFlg}" />
	    	<jsp:param name="paybillMonth" value="${paybillMonth}" />
	    	<jsp:param name="paybillYear" value="${paybillYear}" />
	</jsp:include>
	</span>
	</div>
	<div>
	<c:if test="${empAllRec eq 'false'}">
 	<table  width="85%" align="center" style="display: none;">
		<tr>
		<td align="right">
		<c:if test="${PreviousPsrNo ne '' and PreviousPsrNo ne null}">
		<a href="#" onclick="getPreviousPsrNoData();"><img src="images\previous1.png" alt="Previous PSR NO-${PreviousPsrNo} BILL-${PreviousBillNo}"></a>
		</c:if>
		<hdiits:caption captionid="Bill No" caption= "Bill Id"></hdiits:caption>
		&nbsp;&nbsp;<hdiits:text readonly="true" default ="${CurrBillNo}" name="billNo" maxlength="10" size="3" style="  text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
		
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--<hdiits:caption captionid="PSR No" caption= "PSR No"></hdiits:caption>
		&nbsp;&nbsp;<hdiits:text readonly="true" default ="${CurrPsrNo}" name="psrNo" maxlength="10" size="3" style=" text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;"/>
		&nbsp;&nbsp;-->
		<c:if test="${NextPsrNo ne '' and NextPsrNo ne null}">
		
		<a href="#" onclick="getNextPsrNoData();"><img src="images\next1.png" alt="Next PSR NO-${NextPsrNo} BILL-${NextBillNo}"></a>
		</c:if> 
		
		</td>
		</tr>
	</table>
	</c:if>

	</div>
	<script type="text/javascript"><!--
		


	 
	
	  
    
		initializetabcontent("maintab")
		
		if("${locationCode}"=='300024')
		{
			
			var cash2PsrNo="${CurrPsrNo}";
			var PsrNew=cash2PsrNo.substring(2);
		    document.frmOtherMst.PSRNo.value=PsrNew;
		}
		else
			 document.frmOtherMst.PSRNo.value="${CurrPsrNo}";
			
		
		if("${msg}"!=null&&"${msg}"!='')
		{
		
			alert("${msg}");
			var url="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES&PreviewBill=YES&otherId=${otherId}&edit=Y&empAllRec=false";
			document.frmOtherMst.action=url;
			document.frmOtherMst.submit();
		}	
		else if("${msg}"!=null&&"${msg}"!=''&& "${empAllRecOther}"=='true' )
		{
	
            alert("${msg}");
			var url="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES&PreviewBill=YES&empId=${empId}&empAllRec=true&otherId=${otherId}&edit=Y";
		
			document.frmOtherMst.action=url;
			document.frmOtherMst.submit();
		}
	
 		 disableFields('${empType}'); 

	 

 		 
 		 document.getElementById("initialBasic").focus();	
	--></script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
	
	
</hdiits:form>
</body>


<%
		} catch (Exception e) {
			e.printStackTrace();

	}
%>