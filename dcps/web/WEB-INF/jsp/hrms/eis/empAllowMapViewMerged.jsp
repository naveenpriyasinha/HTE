<%
	try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ page
	import="com.tcs.sgv.eis.valueobject.HrEisEmpMst,java.text.SimpleDateFormat"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@page import="com.tcs.sgv.eis.valueobject.HrPayPaybill"%>
<%@page import="java.util.List, java.util.ArrayList"%>
<%@page import="java.lang.reflect.Method"%>
<%@page import="com.tcs.sgv.eis.valueobject.HrPayEdpCompoMpg"%>
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


<fmt:setLocale value="${sessionScope.locale}" />
<fmt:setBundle basename="resources.eis.eis_common_lables"
	var="commonLables" scope="page" />
<fmt:setBundle basename="resources.Payroll" var="constantVariables"
	scope="request" />


<fmt:message var="contractEmpType" key="contract"
	bundle="${constantVariables}" scope="request">
</fmt:message>
<fmt:message var="fixedEmpType" key="fixed"
	bundle="${constantVariables}" scope="request">
</fmt:message>
<fmt:message var="probationEmpType" key="probation"
	bundle="${constantVariables}" scope="request">
</fmt:message>
<fmt:message var="pageSize" key="pageSize" bundle="${constantVariables}"
	scope="request">
</fmt:message>


<%
	long empId = 0;
		long otherId = 0;

		//System.out.println("the test emp id from jsp is==>"+ request.getParameter("empId") + " manoj");

		if (request.getParameter("empId") != null
				&& !request.getParameter("empId").equals("")
				&& !request.getParameter("empId").equals(" "))
			empId = Long.valueOf(request.getParameter("empId")
					.toString());

		//System.out.println("the test other id  from jsp is==>"+ request.getParameter("otherId") + " manoj");

		if (request.getParameter("otherId") != null
				&& !request.getParameter("otherId").equals("")
				&& !request.getParameter("otherId").equals(" "))
			otherId = Long.valueOf(request.getParameter("otherId")
					.toString());

		String empAllRec = "false";
		if (request.getParameter("empAllRec") != null
				&& !request.getParameter("empAllRec").equals("")
				&& !request.getParameter("empAllRec").equals(""))
			empAllRec = request.getParameter("empAllRec").toString();

		pageContext.setAttribute("empId", empId);
		pageContext.setAttribute("otherId", otherId);
		pageContext.setAttribute("empAllRec", empAllRec);

		//System.out.println("the empall rec from jsp is==>" + empAllRec);
%>

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="allowList" value="${resValue.allowList}"></c:set>

<c:set var="ttlAll" value="${resValue.totalAllw}"></c:set>
<c:set var="allowNamesFromMpg" value="${resValue.allowNamesFromMpg}"></c:set>
<c:set var="mpgList" value="${resValue.allowNamesFromMpg}"></c:set>


<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="allowMpgSize" value="${resValue.allowMpgSize}">
</c:set>
<c:set var="hrEisMst" value="${resValue.hrEisMst}">
</c:set>
<c:set var="allowNamesFromExpr" value="${resValue.allowNamesFromExpr}">
</c:set>
<c:set var="hrEisOtherDtls" value="${resValue.hrEisOtherDtls}">
</c:set>
<c:set var="msg" value="${resValue.msg}"></c:set>
<c:set var="otherList" value="${resValue.dataList}"></c:set>
<c:set var="gpfAccoungFlag" value="${resValue.gpfAccoungFlag}"></c:set>
<c:set var="gpfFlagForZeroCheck" value="${resValue.gpfFlagForZeroCheck}"></c:set>
<c:if test="${otherId eq 0}">
	<c:set var="otherId" value="${resValue.otherId}"></c:set>
</c:if>
<c:if test="${otherId eq 0}">
	<c:set var="otherId" value="${hrEisOtherDtls.otherId}"></c:set>
</c:if>
<c:set var="deducActionList" value="${resValue.deducActionList}"></c:set>
<c:set var="totalDed" value="${resValue.totalDeduc}"></c:set>
<c:set var="loanTotal" value="${resValue.loanTotal}"></c:set>

<c:set var="otherDtlsLoan" value="${resValue.otherDtlsLoan}">
</c:set>
<c:set var="loanList" value="${resValue.loanList}"></c:set>
<c:set var="deducNamesFromMpg" value="${resValue.deducNamesFromMpg}"></c:set>
<!-- <c:set var="empList" 		value="${resValue.empList}" ></c:set>  -->
<c:set var="deducMpgSize" value="${resValue.deducMpgSize}">
</c:set>
<c:set var="deducNamesFromExpr" value="${resValue.deducNamesFromExpr}">
</c:set>
<!-- <c:set var="msg" 			value="${resValue.msg}" ></c:set>  -->
<!--  <c:set var="hrEisMst" 	value="${resValue.hrEisMst}" > </c:set> -->
<!-- <c:set var="otherList" 	value="${resValue.dataList}" > </c:set>  -->
<c:set var="incometax" value="${resValue.incometax}">
</c:set>
<c:set var="gpfGrpDVal" value="${resValue.gpfGrpDVal}">
</c:set>
<c:set var="gpfGrpAbcVal" value="${resValue.gpfGrpAbcVal}">
</c:set>
<c:set var="gisIps" value="${resValue.gisIps}">
</c:set>
<c:set var="miscRecov" value="${resValue.miscRecov}">
</c:set>
<c:set var="pt" value="${resValue.pt}">
</c:set>

<c:set var="vpfAmt" value="${resValue.vpfAmt}">
</c:set>
<c:set var="UserId" value="${resValue.UserId}">
</c:set>

<fmt:message var="armId" key="armId" bundle="${constantVariables}"
	scope="request">
</fmt:message>


<!-- Added for disable deduction -->
<fmt:setBundle basename="resources.Payroll" var="constantVariables"
	scope="request" />
<fmt:message var="ptId" key="ptId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="vpfId" key="vpfId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="cpfId" key="cpfId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="surItId" key="surItId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="GpfId" key="GpfId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="pliId" key="pliId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="itId" key="itId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="GpfIdIV" key="GpfIdIV" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="PF" key="PF" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="JeepRent" key="JeepRent" bundle="${constantVariables}"
	scope="request">
</fmt:message>

<fmt:message var="aisSfId" key="aisSfId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="aisIfId" key="aisIfId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<!--  added by manish  -->
<fmt:message var="hrrId" key="hrrId" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<c:set var="ltaVal" value="${resValue.ltaVal}">
</c:set>
<c:out value="${resValue.ltaVal}"></c:out>

<fmt:message var="contract" key="contract" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<fmt:message var="fixed" key="fixed" bundle="${constantVariables}"
	scope="request">
</fmt:message>
<!-- End -->

<!-- added by ravysh -->
<fmt:message var="dpId" key="dpCode" bundle="${constantVariables}"
	scope="request">
</fmt:message>


<c:set var="resultPaybillVo" value="${resValue.resultPaybillVo}"></c:set>
<c:set var="lastAllwMap" value="${resValue.lastAllwMap}"></c:set>
<c:set var="allCustomList" value="${resValue.allCustomList}"></c:set>
<c:set var="deducCustomList" value="${resValue.deducCustomList}"></c:set>
<c:set var="advanceCustomList" value="${resValue.advanceCustomList}"></c:set>
<c:set var="loanCustomList" value="${resValue.loanCustomList}"></c:set>
<c:set var="hrAdvList" value="${resValue.hrAdvList}"></c:set>
<c:set var="hrAllowList" value="${resValue.hrAllowList}"></c:set>
<c:set var="hrDeducList" value="${resValue.hrDeducList}"></c:set>
<c:set var="hrMiscRecoveryList" value="${resValue.hrMiscRecoveryList}"></c:set>
<c:set var="hrLoanList" value="${resValue.hrLoanList}"></c:set>


<!-- end -->

<%
	//added by manish

		long basicSalary = 0;
		
		
		long dp = 0;
		long incomeTax = 0;
		long surcharge = 0;
		long grossAmount = 0;
		long totalDeduc = 0;
		long netAmount = 0;
		long gpfGrpD = 0;
		long lta = 0;
		long otherAllwVal = 0;
		long gisIps = 0;
		long gisIas = 0;
		long gisIfs = 0;
		long techAllowVal = 0;

		long gis = 0;
		long centralGis = 0;
		long armVal = 0;
		long armourerVal = 0;
		long bmiVal = 0;
		long cidVal = 0;
		long cashVal = 0;
		long conveyanceVal = 0;

		long emergencyVal = 0;
		//long ESISVal = 0;
		long extraLectureVal = 0;
		long fitnessVal = 0;
		long gallantryVal = 0;
		long kitVal = 0;
		long licenseVal = 0;
		long mechanicalVal = 0;
		long medicalEducationVal = 0;
		long messVal = 0;
		long naxelAreaVal = 0;
		long nonPracticingVal = 0;
		long sumptuaryVal = 0;
		long projectVal = 0;
		long specialDutyVal = 0;
		long additionalPayVal = 0;
		long uniformVal = 0;

		long PG = 0;
		long familyPlan = 0;
		long hilly = 0;
		long tribal = 0;
		long ta = 0;
		long ATS30 = 0;
		long ATS50 = 0;
		long force25 = 0;
		long force100 = 0;
		//manish

		long maVal = 0;

		long gpfIASOtherState = 0;
		long gpfIAS = 0;
		long gpfIPS = 0;
		long gpfIFS = 0;
		long serviceCharge = 0;
		long otherdeduction = 0;
		long MahaStateLifeInsurance = 0;

		long claVal = 0;
		long hraVal = 0;
		long daVal = 0;

		HrPayPaybill payBillOut = (HrPayPaybill) pageContext
				.getAttribute("resultPaybillVo");
		if (payBillOut != null) {
			basicSalary = Math.round(payBillOut.getBasic0101())
					+ Math.round(payBillOut.getBasic0102());
			
			
			claVal = Math.round(payBillOut.getAllow0111());
			hraVal = Math.round(payBillOut.getAllow0110());
			daVal = Math.round(payBillOut.getAllow0103());
			dp = Math.round(payBillOut.getAllow0119())
					+ Math.round(payBillOut.getAllow0120());
			//System.out.println("DP value on JSP is " + dp);
			incomeTax = Math.round(payBillOut.getDeduc9510());
			//System.out.println("gisIps in JSP "		+ payBillOut.getDeduc1000());
			gpfGrpD = Math.round(payBillOut.getDeduc9583());
			gisIps = Math.round(payBillOut.getDeduc1000());
			//System.out.println("gisIps " + gisIps);
			gisIas = Math.round(payBillOut.getDeduc1001());
			//System.out.println("gisIas " + gisIas);
			gisIfs = Math.round(payBillOut.getDeduc1002());
			surcharge = Math.round(payBillOut.getSurcharge());
			grossAmount = Math.round(payBillOut.getGrossAmt());
			//System.out.println("Gross in JSP "			+ payBillOut.getGrossAmt());
			totalDeduc = Math.round(payBillOut.getTotalDed());
			netAmount = Math.round(payBillOut.getNetTotal());
			techAllowVal = Math.round(payBillOut.getAllow1003());
			armVal = Math.round(payBillOut.getAllow1006());

			armourerVal = Math.round(payBillOut.getAllow1007());
			bmiVal = Math.round(payBillOut.getAllow1008());
			cashVal = Math.round(payBillOut.getAllow1009());
			cidVal = Math.round(payBillOut.getAllow1010());
			conveyanceVal = Math.round(payBillOut.getAllow1011());

			ta = Math.round(payBillOut.getAllow0113());
			//System.out.println("TA in JSP " + payBillOut.getAllow0113());

			lta = Math.round(payBillOut.getAllow0105());
			otherAllwVal = Math.round(payBillOut.getAllow0104());

			//System.out.println("oa value in jsp" + otherAllwVal);

			centralGis = Math.round(payBillOut.getDeduc1004());
			gis = Math.round(payBillOut.getDeduc1005());
			//manish
			emergencyVal = Math.round(payBillOut.getAllow1012());

			//ESISVal = Math.round(payBillOut.getAllow1013());
			extraLectureVal = Math.round(payBillOut.getAllow1014());

			fitnessVal = Math.round(payBillOut.getAllow1015());
			gallantryVal = Math.round(payBillOut.getAllow1016());
			kitVal = Math.round(payBillOut.getAllow1017());

			licenseVal = Math.round(payBillOut.getAllow1018());
			mechanicalVal = Math.round(payBillOut.getAllow1019());
			medicalEducationVal = Math.round(payBillOut.getAllow1020());
			messVal = Math.round(payBillOut.getAllow1021());
			naxelAreaVal = Math.round(payBillOut.getAllow1022());
			nonPracticingVal = Math.round(payBillOut.getAllow1023());

			sumptuaryVal = Math.round(payBillOut.getAllow1024());
			projectVal = Math.round(payBillOut.getAllow1025());
			specialDutyVal = Math.round(payBillOut.getAllow1026());
			additionalPayVal = Math.round(payBillOut.getAllow1027());
			uniformVal = Math.round(payBillOut.getAllow1028());
			//manish

			//japen
			PG = Math.round(payBillOut.getAllow1029());
			familyPlan = Math.round(payBillOut.getAllow1030());
			//System.out.println("Family Planning in jsp " + familyPlan);
			hilly = Math.round(payBillOut.getAllow1031());
			tribal = Math.round(payBillOut.getAllow1032());
			ATS30 = Math.round(payBillOut.getAllow1033());
			ATS50 = Math.round(payBillOut.getAllow1034());
			force25 = Math.round(payBillOut.getAllow1035());
			force100 = Math.round(payBillOut.getAllow1036());
			//japen
			maVal = Math.round(payBillOut.getAllow0107());

			//System.out.println("maval in jsp " + maVal);

			gpfIASOtherState = Math.round(payBillOut.getDeduc1037());
			gpfIAS = Math.round(payBillOut.getDeduc1038());
			gpfIPS = Math.round(payBillOut.getDeduc1039());
			gpfIFS = Math.round(payBillOut.getDeduc1040());
			serviceCharge = Math.round(payBillOut.getDeduc1041());
			otherdeduction = Math.round(payBillOut.getDeduc1042());
			MahaStateLifeInsurance = Math.round(payBillOut
					.getDeduc9709());

			//System.out.println("MahaStateLifeInsurance in jsp"+MahaStateLifeInsurance);
			//System.out.println("gpfIASOtherState in jsp"+gpfIASOtherState);
			//System.out.println("gpfIPS in jsp"+gpfIPS);
			//System.out.println("gpfIFS in jsp"+gpfIFS);
			//System.out.println("serviceCharge in jsp"+serviceCharge);
			//System.out.println("otherdeduction in jsp"+otherdeduction);
			//System.out.println("gpfIAS in jsp"+gpfIAS);

		}
		//ends
%>



<script><!--

var status="";
var size=0;
function showEmpName()
{
 document.getElementById('allowance').style.display='none';
 //resetMainFormCombo();
 childWindow = window.open("ifms.htm?actionFlag=getEmpData&EmpMpg=Y","EmployeeNames","toolbar=no, location=no, directories=no,status=no, menubar=no, scrollbars=yes, resizable=yes, resize = no, width = 800, height = 550, copyhistory=no, top=80,left=80");
 if (childWindow.opener == null) childWindow.opener = self;
}

function onclosefn()
{
				document.forms[0].btnClose.disabled=true;
	if('${empAllRec}'=='true')
		window.location="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES&Employee_ID_EmpOtherSearch=${empId}&empAllRec=true";
	else
		window.location="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES";
	//document.forms[0].action="./hrms.htm?actionFlag=getOtherData";
	//document.forms[0].submit();
	//history.go(-1);return false;
}
function setPreviousLoanValues(loanId,loanVal)
{
	var txtname='loanTxtBill'+loanId;
	
	if(document.btnClose(txtname) != null)
		document.getElementById(txtname).value=loanVal;
	
	
}
 function setPreviousAdvanceValues(advId,advVal)
 {
 	var txtname='loanTxtBill'+advId;
 	
 	if(document.getElementById(txtname) != null)
 		document.getElementById(txtname).value=advVal;
 	
 	
 }
function insertEmpAllowMpg1()
{
  //alert('function called');
  //document.forms[0].btnsubmit.disabled=true;
  status=document.forms[0].status.value;
   	  	
  if(status== 'Add')
  {
   // var empid = document.insEmpMpg.Employee_ID_EmpSearch.value;
  // alert('deduc size is '+ ${resValue.deducMpgSize});
    url = "ifms.htm?actionFlag=insertEmpAllowMpg&MergedScreen=YES&PreviewBill=YES&batchupdate=true&edit=N&size="+size+"&txtEmpId=" + ${empId} + "&otherId=" + ${otherId}+"&deducSize="+ ${resValue.deducMpgSize};
  }
  else //if(status== 'update')
  {
	    //var empid = document.getElementById("Employee_ID_EmpSearch").value;
		
	if('${empAllRec}'=='true' || '${empAllRec}'=='Y' ){
	   if(confirm("Are you sure to update records"))
	   {
		   //alert('deduc size is '+ ${resValue.deducMpgSize});
           url = "ifms.htm?actionFlag=insertEmpAllowMpg&MergedScreen=YES&PreviewBill=YES&batchupdate=true&edit=Y&size="+size+"&txtEmpId=" + ${empId} + "&otherId=" + ${otherId}+'&empAllRec=Y'+"&deducSize="+ ${resValue.deducMpgSize};
	   }
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
    	  // alert('deduc size is '+ ${resValue.deducMpgSize});
         url = "ifms.htm?actionFlag=insertEmpAllowMpg&MergedScreen=YES&PreviewBill=YES&batchupdate=true&edit=Y&size="+size+"&updatePaybillFlg="+updatePaybillFlg+"&paybillMonth="+paybillMonth+"&paybillYear="+paybillYear+"&empId="+${empId}+"&txtEmpId=" + ${empId} + "&otherId=" + ${otherId}+'&empAllRec=N'+"&deducSize="+ ${resValue.deducMpgSize};
       }
       else
	     return false;
    }     
 }
 	document.forms[0].action = url;
 	document.forms[0].submit();
 	showProgressbar("Please wait<br>While Update the Data...");
}

function showDeductions()
{ 
 
//    var empid = document.getElementById("Employee_ID_EmpSearch").value;
    /*if(document.insEmpMpg.cstrStatus.value=="2")
    {*/
    url = "ifms.htm?actionFlag=getDeducType&EmpDeducDtls=Y&EmpId=" + ${empId};
  	document.insEmpMpg.action = url;
    document.insEmpMpg.submit();
    //}
   
} 
var grossAmount = 0;
var totalDeduc = 0;
var totalAllow=0;
//alert('1.totalDeduc = '+totalDeduc);
//function which shows values after insert.
function checkComboStates(allow_code,val,amount)
{
//alert('in checkstate funct..' + val + 'amount ' + amount );

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
	if(document.getElementById(txtname) != null)
	{
		
     document.getElementById(txtname).value = amount;
  // alert('Text name ' + txtname + ' amount is ' + amount + 'for  remainning'+val);
	}
	if(document.getElementById(txthidden) != null)
	{
     document.getElementById(txthidden).value = amount;
	}
	if(document.getElementById(txtIsChecked) != null)
	{
     document.getElementById(txtIsChecked).value="1";
	}
	if(document.getElementById(txtname) != null)
	{
     document.getElementById(txtname).style.display = '';
	}
      //document.getElementById(txtname).style.backgroundColor= '#E5F0F2';
     //document.getElementById(txtname).disabled=true;
/*     var btnAdd = 'btn_add' + val; 
     document.getElementById(btnAdd).style.display = 'none';
     document.getElementById(btnAdd).value='Edit';*/
     if(document.getElementById(allowcodeId) !=null)
     {         
     document.getElementById(allowcodeId).value=allow_code;
     }

grossAmount = eval(grossAmount)+eval(amount);

   }
   grossAmount=eval(grossAmount);
   //alert('Geross is ' + grossAmount);
   
  //calculateTotal(amount);
}
function calculateTotal()
{

//	alert('allowList'+${allowList});
	
	var id;
	//id= ${allowType[0]};

	
    var str = '';
    var total=0;
    var elem = document.getElementById("allowDiv").elements;
   
    for(var i = 0; i < elem.length; i++)
    {
        
      total+=document.getElementById(elem[i]).value;
      
    } 
    //totalAllow+=amount;
   //alert("total is "+totalAllow);
   
}

function checkComboStatesDefault(allow_code,val,amount)
{
	
//alert('in checkComboStatesDefault main page funct..' + val + 'amount ' + amount +'code is '+allow_code);
//alert('inside function valu is '+amount +'val '+val );

 for(i=0;i<document.forms[0].tax_name.length;i++)
  if(document.forms[0].tax_name[i].value == val)
   {
     //document.forms[0].tax_name[i].checked = true;     
     //document.forms[0].tax_name[i].disabled = true;
      document.forms[0].tax_name[i].style.display = 'none';
      var txtname = 'txt' + val; 
      var txtnamebill = 'txtBill' + val; 
     // alert(txtnamebill +'inside function valu is '+amount +'val '+val );
      var txthidden = 'txthidden' + val; 
      var txtIsChecked = 'txtIsChecked' + val;
      var allowcodeId = 'txtAllowCode' + val;
        //    alert('textbox name ' + txtname);
   //   alert('value of textbox is ' + document.getElementById(txtname).value);
      
     
       
	//if(document.getElementById(txtnamebill) != null)
//	{
  //   document.getElementById(txtnamebill).value = amount;
    
	//} 
	if(document.getElementById(txtname) != null)
	{
     document.getElementById(txtname).value = amount;
    // alert('amount is '+ amount +'for computational code'+val);
	}
     //alert('done221');
     if(document.getElementById(txthidden) != null)
     {
     document.getElementById(txthidden).value = '-1';
     }
     //alert('done21');
     if( document.getElementById(txtIsChecked)!=null) {
      document.getElementById(txtIsChecked).value="1";     
     document.getElementById(txtIsChecked).style.display = 'none';
     }
     //alert('done1');
     document.getElementById(txtname).style.display = '';
     //document.getElementById(txtname).disabled=true;
     //alert('going to make reaad only ');
     	document.getElementById(txtname).readOnly=true;
     	//alert('done');
     	if(document.getElementById(txtname)!=null)
            document.getElementById(txtname).style.backgroundColor = "#E5F0F2";
  /*   var btnAdd = 'btn_add' + val; 
     document.getElementById(btnAdd).style.display = 'none';
     document.getElementById(btnAdd).value='Edit';*/
     //alert('allowcode id is ' + allowcodeId);
     if(document.getElementById(allowcodeId)!=null) {
     document.getElementById(allowcodeId).value=allow_code;
    // alert('document.getElementById(allowcodeId).value ' + document.getElementById(allowcodeId).value);
     }


   }

}

function newCheckComboStatesDefault(allow_code,val,amount)
{
		//alert('function called');
		var txtnamebill = 'txtBill' + val; 
		if(document.getElementById(txtnamebill) != null)
		{
			//alert('inside if ' +val + 'amount '+amount);
			document.getElementById(txtnamebill).value = amount;
			    
		} 
			  
}

function newCheckComboStatesDefault1(allow_code,val,amount)
{
		//alert('function called for deduction');
		var txtnamebill = 'txttBill' + val; 
		if(document.getElementById(txtnamebill) != null)
		{
			//alert('inside if ' +val + 'amount '+amount);
			document.getElementById(txtnamebill).value =parseInt(amount);
			    
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
	if(document.getElementById("Emp_allow") != null)
	{
		document.getElementById("Emp_allow").value='y';
	}
		
		addOrUpdateRecord('ChkEmp', 'chkEmpDetail', new Array('Employee_ID_EmpSearch','Emp_allow'));
		
		//document.getElementById("Employee_ID_EmpSearch").value='';
		if(document.getElementById("Emp_allow") != null)
		{
		document.getElementById("Emp_allow").value='';
		}
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
									url = "ifms.htm?actionFlag=getAllwType&EmpAllowMpg=Y&EmpId=" + empId;
									window.location=url;
								}
							}
								
			}
		}
}
function chknumber1(number1)
{
        name1='txt'+number1;
        if(document.getElementById(name1) != null)
        {
		var field_value=document.getElementById(name1).value;
        }
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
          if(document.getElementById(name1) != null)
          {
          document.getElementById(name1).value="";
          document.getElementById(name1).focus();
          }
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

//alert('function called for deduction'+allow_code+'and the code value is '+val);
 for(i=0;i<document.forms[0].tax_name1.length;i++)
 {
		var allowcodeIdNew = 'txttDeducCode' + val;
		//alert('new id'+ allowcodeIdNew);
	 if(document.getElementById(allowcodeIdNew) !=null)
     {
		  //   alert('inside if');
     document.getElementById(allowcodeIdNew).value=allow_code;
     }
	if(document.forms[0].tax_name1[i].value == val)
   	{
    	//document.forms[0].tax_name[i].checked = true;     
     	//document.forms[0].tax_name[i].disabled = true;
     
      	var txtname1 = 'txtt' + val; 
      	var txtIsChecked1 = 'txtIsChecked' + val;
      	var allowcodeId1 = 'txttDeducCode' + val;
		//alert('textbox name ' + txtname);
		//alert('value of textbox is ' + opener.document.getElementById(txtname).value);
		// if(document.getElementById(allowcodeId) !=null)
    // {
		//     alert('inside if');
     //document.getElementById(allowcodeId1).value=allow_code;
     //}

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
				if(document.getElementById("gpfAmt") != null)
				{
		 		document.getElementById("gpfAmt").value=Math.round(amount);
				}
				amount=vpfAmt;

				if(document.getElementById(txtname1) != null)
				{
				document.getElementById(txtname1).readOnly="readonly";
				
				document.getElementById(txtname1).style.backgroundColor="#E5F0F2";
				}
				if(document.getElementById("pfType") != null)
				{
				document.getElementById("pfType").value="GPF";
				}
				if(document.getElementById("pfId") != null)
				{
				document.getElementById("pfId").value="${vpfId}";
				}
	 		}
	
			document.getElementById("gpfAmt").value=Math.round(amount);
			amount=vpfAmt;

			 if(document.getElementById(txtname1) != null)
			 {
			document.getElementById(txtname1).readOnly="readonly";
			document.getElementById(txtname1).style.backgroundColor="#E5F0F2";
			 }
			 if(document.getElementById("pfType") != null)
			 {
			document.getElementById("pfType").value="GPF";
			 }
			 if(document.getElementById("pfId") != null)
			 {
			document.getElementById("pfId").value="${vpfId}";
			 }
	  
		}
	 	else
	 	{
	 		if(val=="${PF}")
	 		{
	 		
	 		 	if(document.getElementById("pfType") != null)
	 		 	{
				document.getElementById("pfType").value="GPF";
	 		 	}
				//document.getElementById("span${PF}").innerHTML="<b>(GPF)</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
				if(document.getElementById("pfId") != null)
				{
				document.getElementById("pfId").value=val;
				}
				if(document.getElementById("gpfAmt") != null)
				{
	 			document.getElementById("gpfAmt").value=Math.round(amount);
				}
	 				amount=eval(amount)+eval(cpfamt);

	 				if(document.getElementById(txtname1) != null)
	 				{
	 			document.getElementById(txtname1).disabled=false;
	 			document.getElementById(txtname1).style.backgroundColor="#ffffff";
	 				}
	  		}
	 	}
 
 	
 	
		if(val!="${itId}")
			totalDeduc=totalDeduc+amount;
      
			//alert('2.totalDeduc = '+totalDeduc);
	 
	 
			document.getElementById(txtname1).value = Math.round(amount);
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
   
   /*if(val=="${cpfId}" && amount!=0)
 	{
 		document.getElementById("gpfAmt").value=amount; 		
		 cpfamt=amount;
		 document.getElementById("txtt${PF}").value=amount;
		 document.getElementById("pfType").value="GPF";
		 document.getElementById("pfId").value="${cpfId}";
	}*/
    
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
      var allowcodeId1 = 'txttDeducCode' + val;
  //          alert('textbox name ' + txtname);
//      alert('value of textbox is ' + opener.document.getElementById(txtname).value);

 if(status==0)
 {
 amount=vpfAmt;
}
 
	//alert('amount is '+amount +'for deduccode computational'+val);
     document.getElementById(txtname1).value = Math.round(amount);
     if(document.getElementById(txtIsChecked1) != null)
     {
     document.getElementById(txtIsChecked1).value="1";
     }
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
     // alert('chknumber11');
        name1='txtt'+number1;
        if(document.getElementById(name1) != null)
        {
		var field_value=document.getElementById(name1).value;
        }
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
          if(document.getElementById(name1) != null)
          {
          document.getElementById(name1).value="";
          document.getElementById(name1).focus();
          }
          return false;
        }
        }
        } 
	/*	var field_value=document.getElementById(name11).value;
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
        } */
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
		var url = "ifms.htm?actionFlag=getVpfDtlsByEmpId&empId=${hrEisMst.orgEmpMst.empId}&check=2";  
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
							document.getElementById("txtt${PF}").value=Math.round(pfAmt);
							//document.getElementById("txtt${PF}").focus();
							return false;
						}
						else if(vpfAmount > maxAmount){
								 alert("VPF Amount must be Less then "+maxAmount);
								document.getElementById("txtt${PF}").value=Math.round(pfAmt);
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
										document.getElementById("txtt${PF}").value=Math.round(vpfAmt);
									}	
									else
									{            	
										document.getElementById("txtt${PF}").value=Math.round(vpfAmount);
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

//added by ravysh

function EmpDtlsFromBasicDtls(Link)
{
	var otherId='<%=pageContext.getAttribute("otherId").toString()%>';
	var UserId='${UserId}';
	var EmpId='<%=empId%>';
	var url='';

	if(Link==3)
	 url="./ifms.htm?actionFlag=getQuarterDtls&FromBasicDtlsNew=YES&otherId="+otherId+"&edit=x&USER_ID_EmpQtrSearch="+UserId;
	else if(Link==2)
		url="./ifms.htm?actionFlag=getBankDtlsView&FromBasicDtlsNew=YES&Employee_ID_EmpLoanSearch="+EmpId;
	else if(Link==4)
		url="./ifms.htm?actionFlag=getLoanValue&FromBasicDtlsNew=YES&otherId="+otherId+"&Employee_ID_EmpLoanSearch="+EmpId;
	else if(Link==1)
		url="./ifms.htm?actionFlag=getEmpData&FromBasicDtlsNew=YES&Employee_ID_EmpInfoSearch="+EmpId+"&otherId="+otherId;			
	else if(Link==5)
		url="./ifms.htm?actionFlag=getMiscData&FromBasicDtlsNew=YES&Employee_ID_EmpInfoSearch="+EmpId+"&otherId="+otherId;		
	else if(Link==6)
		url="./ifms.htm?actionFlag=getVPFView&FromBasicDtlsNew=YES&Employee_ID_EmpInfoSearch="+EmpId+"&otherId="+otherId;	

		window.open(url,"MergScreenLinks","width=850,height=700,scrollbars=yes,resizable=yes,status=no,left=80 ,top=50");
	//window.showModalDialog(url,"MergScreenLinks","dialogWidth:900px; dialogHeight:600px; center:yes; status:yes;menubar:yes");
	
}


--></script>

<!--<body onload="calculateTotal();">

   -->
<TABLE width="100%" border="1" align="center">

	<tr>
		<!--<td><a 
			id="EmployeeDtlsLinks"><b>Employee Details</b></a></td>
		--><!--<td><a href="javascript:EmpDtlsFromBasicDtls(2);"
			id="BankDtlsLinks"><b>Bank Details</b></a></td>-->
			<!--<td>
			<a href="javascript:EmpDtlsFromBasicDtls(2);" id="BankDtlsLinks"><b>Loss Of Pay</b></a></td>
		--><!--<td><a 
			id="QuarterDtlsLinks"><b>Quarter Details</b></a></td>
		--><!--  <td><a  href="#" onclick="updateDtls('getVpfDtlsById&vpfId=0&edit=Y&updatePaybillEmpId');"   id="empDtlsLinks" ><b>VPF details</b></a></td> -->
		<!--<td><a 
			id="LoanDtlsLinks"><b>Loan Details</b></a></td>
		<td><a 
			id="MiscDetails"><b>Misc Details</b></a></td>
		--><!--  <td><a  href="javascript:EmpDtlsFromBasicDtls(6);" id="VPFDetails" ><b>VPF Details</b></a></td>  -->
		<!-- <td><a  href="#" onclick="updateDtls('getMiscData&Employee_ID_miscSearch');"   id="empDtlsLinks" ><b>Misc Details</b></a></td> -->


	</tr>

</TABLE>




<table border=1 width="100%">
	<!-- <tr>

	<c:choose>
	<c:when test="${empAllRec eq 'true' or empAllRec eq 'y'}">
			<td colspan="3" align="center"><font size="3"><b><u><a href="./hrms.htm?actionFlag=viewTemporaryPayBill&empId=${empId}&empAllRec=true&otherId=${otherId}">Preview Bill</a></u></b></font></td>

	</c:when>
		<c:otherwise>
				<td colspan="3" align="center"><font size="3"><b><u><a href="./hrms.htm?actionFlag=viewTemporaryPayBill&empId=${empId}&otherId=${otherId}">Preview Bill</a></u></b></font></td>
		</c:otherwise>

	</c:choose>

</tr>
-->
	<tr>
		<td colspan="1" style="border: 0px;"><hdiits:text name="Message"
			style="border:0px; background: #E5F0F2;" readonly="true" size="4" />-<b><i><font
			face="Comic Sans MS " size="1px" color="blue">INDICATES
		PAYBILL VALUES</font></i></b></td>
		<td colspan="2"></td>
	</tr>
	<tr>
		<td width="33%" valign="top"><c:set var="allowcode" value="${0}" />
		<c:choose>
			<c:when test="${allowMpgSize eq 0}">
				<table width="100%" border=0 align="left" id="allowance"
					style="display: none">
					</c:when>
					<c:otherwise>
						<table width="100%" align="left" border=0 id="allowance">
							<tr>
								<td align="right"><font color="red"> <b>
								Allowances (A)</b></font></td>
								<td><font color="red"> <b>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Current&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Previous</b></font></td>
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
								<td align="right"><b><c:out value="Basic Pay" /></b></td>
								<td align="right" style="padding-right: 5px"><hdiits:text
									name="basicPay" readonly="true"
									style="background: #E5F0F2; text-align:right;	font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;"
									size="5" caption="basicPay" id="basicPay"
									default="${hrEisOtherDtls.otherCurrentBasic}" /> <hdiits:text
									name="basicPayBill" readonly="true"
									style="border:0px; background: #FFDDDD; text-align:right;	font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;"
									size="5" caption="basicPay" id="basicPayBill" /> <script>
            	grossAmount="${hrEisOtherDtls.otherCurrentBasic}";
            	document.getElementById("basicPayBill").value = '<%=basicSalary%>';
			</script></td>
							</tr>


							<c:forEach var="allowType" items="${allowList}">

								<script>
								//alert('inside allow type master'+${allowType[0]});
		size++;</script>
								<TR>
									<TD width="65%" align="right"><hdiits:hidden
										default="${allowType[0]}" name="tax_name" id="tax_name" /> <b><c:out
										value="${allowType[1]}" /></b></TD>
									<td width="35%" align="right" style="padding-right: 5px">
									<c:set var="allowcode" value="${allowcode+1}" /> <hdiits:text readonly="true"
										name="txt${allowType[0]}" size="5" caption="Allowance"
										default="0" captionid="Allowance" id="txt${allowType[0]}"
										maxlength="5"
										onblur="chknumber1(${allowType[0]});document.getElementById('txthidden${allowType[0]}').value=document.getElementById('txt${allowType[0]}').value"
										style="background-color: #E5F0F2; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
									<hdiits:text name="txtBill${allowType[0]}" size="5" default="0"
										caption="Allowance" captionid="Allowance"
										id="txtBill${allowType[0]}" maxlength="5" readonly="true"
										style="border: 0px; background: #FFDDDD; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
										
									<hdiits:button name="btn_add${allowType[0]}" type="button"
										style="display:none" value="Add"
										onclick="insertEmpAllowMpg(${allowType[0]})" /> <hdiits:hidden
										name="txthidden${allowType[0]}" id="txthidden${allowType[0]}" />
									<hdiits:hidden name="txtIsChecked${allowType[0]}"
										caption="Check Status" captionid="CheckStatus" /> <hdiits:hidden
										name="txtAllowCode${allowType[0]}"
										id="txtAllowCode${allowType[0]}" /> <hdiits:hidden
										default="${allowType[0]}" name="Allowcode${allowcode}" /></td>
								</TR>
							</c:forEach>

							<%
								List hrAllowList = (List) pageContext
											.getAttribute("hrAllowList");

									if (hrAllowList != null && hrAllowList.size() > 0) {

										String edpCode = "";
										String mthdName = "";
										String allowCode = "";
										long basic;
										for (int i = 0; i < hrAllowList.size(); i++) {

											HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg) hrAllowList
													.get(i);

											edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();

											allowCode = edpCompMpg.getTypeId();

											mthdName = "getAllow" + edpCode;

											Class pay = payBillOut.getClass();
											Method payMthd = pay.getMethod(mthdName, null);
											basic = Math.round((Double) payMthd.invoke(payBillOut,
													null));
							%>


							<script>
			var allowcode='<%=allowCode%>';
            var docname = "txtBill"+allowcode;

            if(  document.getElementById(docname)!=null)
            {   
            document.getElementById(docname).value = '<%=basic%>';
            }
            if(   document.getElementById("txtBill${dpId}")!=null)
            {   
            document.getElementById("txtBill${dpId}").value = '<%=dp%>';
            }
            </script>

							<%
								}
									}
							%>


						</table>

						<c:if test="${allowMpgSize ne 0}">
							<script>
var PFTypePrevBill=0;

if( document.getElementById("jsptext")!=null)
{
   				document.getElementById("jsptext").style.display='';
}
if(document.getElementById("jsptable")!=null)
{
   				document.getElementById("jsptable").style.display='none';
}
				//document.getElementById("empTable").style.display='none';
   				var value="${hrEisMst.orgEmpMst.empFname} ${hrEisMst.orgEmpMst.empMname} ${hrEisMst.orgEmpMst.empLname}";
				//document.getElementById("EmpName").value=value;
   				//document.getElementById("Employee_ID_EmpSearch").value="${hrEisMst.orgEmpMst.empId}";
   	   		</script>
   	   		
   	   	
							
							<c:forEach var="list" items="${allowNamesFromMpg}">			
								<script>																
								checkComboStates('${list[0]}', '${list[1].allowCode}',Math.round('${list[2]}'));</script>
							</c:forEach>
						</c:if>

						<c:forEach var="exprList" items="${allowNamesFromExpr}">
						<script>//alert('ExprList AllowCode '+${exprList.hrPayAllowTypeMst.allowCode});</script>
							<c:forEach var="list" items="${allowNamesFromMpg}">
								<script>//alert('ExprList AllowCode '+exprList.hrPayAllowTypeMst.allowCode +' list mpg allowCode '+ ${list[1].allowCode});</script>
								<c:choose>
								
									<c:when
										test="${list[1].allowCode==exprList.hrPayAllowTypeMst.allowCode}">
										<script>
										//alert('allowCode '+${list[1].allowCode}+' value is  ' +  ${list[2]});
										checkComboStatesDefault('${list[0]}', '${list[1].allowCode}', Math.round('${list[2]}'));</script>
									</c:when>
								</c:choose>
							</c:forEach>
						</c:forEach>



						<c:forEach var="list" items="${allCustomList}">
							<script>
						//alert('code is '+${list.allwID} +'value is '+${list.allowanceVal});
						newCheckComboStatesDefault(0, ${list.allwID}, Math.round(${list.allowanceVal}));</script>

						</c:forEach>


						</td>
						<td width="33%" valign="top"><!-- Added by Mrugesh --> <c:set
							var="deduccode" value="${0}" /> <c:choose>
							<c:when test="${deducMpgSize eq 0}">
								<table width="100%" align="right" border=0 id="deduction"
									style="display: none">
									</c:when>
									<c:otherwise>
										<table width="100%" align="right" border=0 id="deduction">
											<tr>
												<td align="right"><b><font color="red">Deductions
												(B)</font></b></td>
												<td><font color="red"> <b>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Current&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Previous</b></font></td>
												<td><hdiits:hidden name="pfType" /><br>
												<hdiits:hidden name="gpfAmt" /><br>
												<hdiits:hidden name="pfId" /></td>
											</tr>
											</c:otherwise>
											</c:choose>
											<c:forEach var="deducType" items="${deducActionList}">
												<c:if test="${deducType[0] ne vpfId }">
													<%--<c:if test="${deducType[0] ne cpfId }">--%>
														<c:if test="${deducType[0] ne surItId }">
															<c:if test="${deducType[0] ne GpfId }">
																<c:if test="${deducType[0] ne pliId }">
																	<c:if test="${deducType[0] ne GpfIdIV }">
																		<c:if test="${deducType[0] ne '71'}">
																			<TR>
																				<TD width="65%" align="right"><hdiits:hidden
																					default="${deducType[0]}" name="tax_name1"
																					id="tax_name1" /> <b><c:out
																					value="${deducType[1]}" /></b> <span
																					id="span${deducType[0]}"></span></TD>
																				<td width="35%" align="right"
																					style="padding-right: 5px"><c:set
																					var="deduccode" value="${deduccode+1}" /> <!--<script>
											alert('deduc code is '+${deducType[0]});</script> --><hdiits:text
																					size="5" onblur="chknumber11(${deducType[0]})"
																					readonly="true" name="txtt${deducType[0]}"
																					caption="Allowance1" captionid="Allowance1"
																					default="0" id="txtt${deducType[0]}" maxlength="8"
																					style="background: #E5F0F2;	text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
																				<hdiits:text size="5" readonly="true"
																					name="txttBill${deducType[0]}" caption="Allowance1"
																					captionid="Allowance1" default="0"
																					id="txttBill${deducType[0]}" maxlength="8"
																					style="border:0px; background: #FFDDDD;	text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
																				<hdiits:hidden  default="${deducType[0]}"
																					name="deduccode${deduccode}" /> <hdiits:hidden																						
																					name="txttDeducCode${deducType[0]}"
																					id="txttDeducCode${deducType[0]}" /></td>
																			</TR>
																		</c:if>
																	</c:if>
																</c:if>
															</c:if>
														<%--</c:if>--%>
													</c:if>
												</c:if>
											</c:forEach>


											<%
												List hrDeducList = (List) pageContext
															.getAttribute("hrDeducList");

													long payRecov = 0;
													if (hrDeducList != null && hrDeducList.size() > 0) {

														String edpCode = "";
														String mthdName = "";
														String deducCode = "";

														long basic;
														for (int i = 0; i < hrDeducList.size(); i++) {

															HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg) hrDeducList
																	.get(i);

															edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();

															deducCode = edpCompMpg.getTypeId();
															mthdName = "getDeduc" + edpCode;

															Class pay = payBillOut.getClass();
															Method payMthd = pay.getMethod(mthdName, null);
															basic = Math.round((Double) payMthd.invoke(payBillOut,
																	null));

															if (edpCode.trim().equals("0101")) {
																payRecov = basic;
															}
											%>


											<script>
			var dedcode="<%=deducCode%>";
            var docname = "txttBill"+dedcode;
            
			if(<%=basic%>!=0){
			 document.getElementById(docname).value = "<%=basic%>";
			 }
			
           </script>

											<%
												//System.out.println("Deduc code " + deducCode);
															if ((deducCode.equals("44") || deducCode.equals("59") || deducCode
																	.equals("71"))
																	&& basic != 0) {
											%>
											<script>
        	   PFTypePrevBill=dedcode;
        	   document.getElementById("txttBill45").value = "<%=basic%>";
        	   </script>
											<%
												}
														}
													}
											%>





											<script>

//code need to be entered here from notepad




//manish



//manish





document.getElementById("txtt${PF}").onkeypress=chkKey;
document.getElementById("txtt${PF}").onblur=checkAvailability;
	//document.getElementById("txtt${PF}").onblur=testCurrency;
</script>




											<script>
								if(document.getElementById("txttBillPayRec") != null)
								{
								document.getElementById("txttBillPayRec").value='<%=payRecov%>';
								}
								</script>


											<%
												long miscRecovery = 0;
													List hrMiscRecoveryList = (List) pageContext
															.getAttribute("hrMiscRecoveryList");
													if (hrMiscRecoveryList != null && hrMiscRecoveryList.size() > 0) {
														String shortName = "";
														String edpCode = "";
														String mthdName = "";

														for (int i = 0; i < hrMiscRecoveryList.size(); i++) {

															HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg) hrMiscRecoveryList
																	.get(i);

															shortName = edpCompMpg.getRltBillTypeEdp()
																	.getEdpShortName();
															edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();
															mthdName = "getDeduc" + edpCode;

															Class pay = payBillOut.getClass();
															Method payMthd = pay.getMethod(mthdName, null);
															miscRecovery += Math.round((Double) payMthd.invoke(
																	payBillOut, null));

														}
													}
											%>
											<script>
		if(document.getElementById("txttBillMiscRecov") != null)
		{
		document.getElementById("txttBillMiscRecov").value='<%=miscRecovery%>';
		}
		</script>


											<tr>
												<td align="right" colspan="2"><%--jsp:include page="../../core/PayTabnavigation.jsp" --%>
												<c:if test="${deducMpgSize ne 0}">
													<c:forEach var="list" items="${deducNamesFromMpg}">
														<script>
							//alert('real value of the code is'+${list[0]});
							checkComboStates1('${list[0]}', '${list[1].deducCode}', '${list[2]}','${list[3]}',Math.round("${vpfAmt}"));
							</script>
														<script>
   							/*var value="${hrEisMst.orgEmpMst.empFname} ${hrEisMst.orgEmpMst.empMname} ${hrEisMst.orgEmpMst.empLname}";*/
										//document.getElementById("txtt"+${itId}).value="${incometax}";
										//document.getElementById("txtt"+${itId}).value="${incometax}"; 
							<%--temp code - Added by Ankit --%>
							//document.getElementById("txtt36").value="${gpfGrpDVal}";
							//document.getElementById("txtt72").value="${gpfGrpAbcVal}";
							//document.getElementById("txtt82").value="${gisIps}";
							//document.getElementById("txtt34").value="${ltaVal}";
							
							//alert(' income tax is' + ${incometax});
							//totalDeduc=totalDeduc+eval("${incometax}");
						</script>
													</c:forEach>

												</c:if></td>
											</tr>
											<c:forEach var="exprList" items="${deducNamesFromExpr}">
												<c:forEach var="list" items="${deducNamesFromMpg}">
													<c:choose>
														<c:when
															test="${list[1].deducCode==exprList.hrPayDeducTypeMst.deducCode}">
															<script>
						//alert('computational dedction ');
						checkComboStatesDefault1('${list[0]}', '${list[1].deducCode}', '${list[2]}','${list[3]}',Math.round("${vpfAmt}"));</script>
														</c:when>
													</c:choose>
												</c:forEach>
											</c:forEach>


											<c:forEach var="list" items="${deducCustomList}">
												<script>
						//alert('deadauction '+${list.deducId} + 'raja '+${list.deducVal});
						newCheckComboStatesDefault1(0, '${list.deducId}', Math.round('${list.deducVal}'));
						</script>

											</c:forEach>
							
																											
												
											<script><!--

												//document.getElementById("txtt${ptId}").value="${pt}";
		//pt department
		//totalDeduc=eval(totalDeduc)+eval("${pt}");
		//alert('4.totalDeduc = '+totalDeduc);
		
	

		document.getElementById("txtt${JeepRent}").onkeypress=chkNumberForJeepRent;
		document.getElementById("txtt${JeepRent}").readOnly="readOnly";
		document.getElementById("txtt${JeepRent}").disabled=true;
		//document.getElementById("txtt${PF}").readOnly="readonly";
 		document.getElementById("txtt${JeepRent}").style.backgroundColor="#E5F0F2";
 		document.getElementById("txtt${JeepRent}").style.font-color="Black";
 		

 			//document.getElementById("txtt${Re}").style.backgroundColor="#ffffff";
 			
 			/*document.getElementById("txtt${aisIfId}").readOnly=false;
 			document.getElementById("txtt${aisIfId}").style.backgroundColor="#ffffff";
 			document.getElementById("txtt${aisIfId}").value='${gpfGrpDVal}';*/

 			//document.getElementById("txtt${hrrId}").readOnly=true;
 			//document.getElementById("txtt${hrrId}").style.backgroundColor="#ffffff";

 			

 			//document.getElementById("txtt36").value="${gpfGrpDVal}";
 			

 			//document.getElementById("txtt72").readOnly=true;
 			//document.getElementById("txtt36").readOnly=true;


 		//	document.getElementById("txt34").readOnly=true;
 			//document.getElementById("txt34").style.backgroundColor="#ffffff";



 		//	document.getElementById("txt37").readOnly=false;
 		//	document.getElementById("txt34").style.backgroundColor="#ffffff";


 			//document.getElementById("txt12").readOnly=false;
 			//document.getElementById("txt12").style.backgroundColor="#ffffff";
 			
 		 
 			//document.getElementById("txtt${34}").value='${ltaVal}';
 		//document.getElementById("txtt${PF}").readOnly=false;
 		//document.getElementById("txtt${JeepRent}").style.backgroundColor="#ffffff";
 		

 		//alert('1.vpf is: ' +parseFloat(${vpfAmt}));
 		//alert('gpf amnt is: ' +parseFloat(document.getElementById("gpfAmt").value) );


		if(document.getElementById("pfId").value=="${cpfId}" && parseFloat("${vpfAmt}")==0)
	 	{
	 		//document.getElementById("span${PF}").innerHTML="<b>(CPF)</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
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

		 if(PFTypePrevBill=="${cpfId}")
		 	document.getElementById("span${PF}").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;<b>(CPS)</b>"; 	

		 else if(PFTypePrevBill=="${GpfIdIV}")
		    document.getElementById("span${PF}").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;<b>(GPF-ClassIV)</b>"; 	
		 else if(PFTypePrevBill=="71")
			document.getElementById("span${PF}").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;<b>(IAS)</b>"; 	
		 else
		 	document.getElementById("span${PF}").innerHTML="&nbsp;&nbsp;&nbsp;&nbsp;<b>(VPF)</b>";

	 	document.getElementById("gpfAmt").value=parseFloat("${vpfAmt}");

		document.getElementById("pfType").value="GPF";
		document.getElementById("pfId").value="${vpfId}";
		
	 }
 		
		--></script>

											<c:if
												test="${hrEisOtherDtls.hrEisEmpMst.empType eq contract or hrEisOtherDtls.hrEisEmpMst.empType eq fixed}">
												<script>
//document.getElementById("txtt${PF}").readOnly=true;
//document.getElementById("txtt${PF}").style.backgroundColor="#E5F0F2";
</script>

											</c:if>


											<c:if test="${gpfFlagForZeroCheck eq 0}">
												<script>
//document.getElementById("txtt${PF}").readOnly=true;
totalDeduc=totalDeduc-document.getElementById("txtt${PF}").value;
document.getElementById("txtt${PF}").value=0;
document.getElementById("txtt${PF}").style.backgroundColor="#FFFFFF";
//document.getElementById("txtt${PF}").disabled=true;




</script>

											</c:if>

											<!-- Modified By Mrugesh For removing readonly Income-Tax textbox -->
											<script>
											if(document.getElementById("txtt${itId}") != null){
		document.getElementById("txtt${itId}").readOnly="readonly";
		document.getElementById("txtt${itId}").style.backgroundColor="#E5F0F2";}
		 //alert("The IT is---->>>"+document.getElementById("txtt${itId}").value);
		document.frmOtherMst.incometax.value = document.getElementById("txtt${itId}").value;//Added for basic detail screen's income-tax value
		totalDeduc=totalDeduc+eval(document.getElementById("txtt${itId}").value);
	var ScooterCarLoanType=0;
</script>
											<!-- Ended by Mrugesh -->
										</table>
										</td>



										<!-- ADDED BY VARUN SHARMA FOR LOAN -->

										<td width="33%" valign="top">
										<table width="100%" align="left" border="0">
											<tr>
												<!-- SET LOAN HEADER  -->

												<!--<td width="25%"></td>
												--><td width="25%" align="center"><b><font
													color="red">Loan (C) </font></b></td>
													<td width="28%" colspan="2" align="Right"><font color="red"> <b>
													Current</b></font></td>
													<td width="14%"  align="center"><font color="red"> <b>Previous</b></font></td>
												<td width="33%" align="center"></td>

											</tr>

											<!-- SET LOAN INFORMATION -->
												
												

											<c:set var="size" value="1"></c:set>
											<%!int i = 1;%>
											<%
												i = (i - i) + 1;
													pageContext.setAttribute("size", i);
											%>

											<c:forEach var="loanList" items="${otherDtlsLoan}">
												<c:choose>

													<c:when test="${loanList.loanPrinEmiAmt ne 0}">
														<c:set var="empLoanId" value="${loanList.empLoanId}"></c:set>
														<c:set var="loanName" value="${loanList.loanName}"></c:set>
														<c:set var="emiAmt" value="${loanList.loanPrinEmiAmt}"></c:set>
														<c:set var="emiNo" value = "${loanList.loanPrinEmiAmtString}" ></c:set>
														<c:set var="amtType" value="Principal"></c:set>
														<c:set var="outstandingPrinAmt"
															value="${loanList.outstandingPrincipleAmt}"></c:set>

														<!-- added by ravysh -->
														<c:set var="loanAdvId" value="${loanList.loanTypeId}"></c:set>

														<tr>

															<td style="width: 25%" align="right"><b>
														<%-- 	<a	href="javascript:openWindow(${empLoanId})"
																title="Loan Will Be Opened in Edit Mode"
																onmouseover="window.status='loan';return true;"
																onmouseout="window.status='';"></a>--%>
																<c:out	value="${loanName}"  />
																</b></td>
															<td width="14%" align="center">
															<b><c:out value="${emiNo}"></c:out></b>
															</td>
															<td width="14%" align="center">
															<hdiits:number readonly="true" name="loanTxt${size}"
																id="loanTxt" maxlength="5" default="${emiAmt}" size="5"
																style="background: #E5F0F2; font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />
																</td>
															<!--<script>
										if(${loanAdvId}==31 || ${loanAdvId}==23 || ${loanAdvId}==27)
										ScooterCarLoanType=${loanAdvId};
										</script> -->		
																<td width="14%" align="center">
																<hdiits:number name="loanTxtBill${loanAdvId}"
																id="loanTxtBill${loanAdvId}" maxlength="5" size="3"  default="0"
																style="border:0px; background: #FFDDDD; font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />
																<b><c:out value="${amtType}"></c:out></b></td>
																
															<td align="center" width="33%"><font color=red><b><c:out
																value="${outstandingPrinAmt} remaining"></c:out></b></font></td>
															<hdiits:hidden name="loanAmtType${size}" id="loanAmtType"
																default="${amtType}" />
															<hdiits:hidden name="loanTxtId${size}" id="loanTxtId"
																default="${loanList.empLoanId}" />
														</tr>

														<script>
								//alert(" totalDeduc in princ is: " +totalDeduc);
								//alert(" emiAmt is: ${emiAmt}");
								
								//SET TOTAL DEDUC AMOUNT
								totalDeduc=totalDeduc+eval("${emiAmt}");
							</script>
													</c:when>

													<c:otherwise>
														<c:set var="empLoanId" value="${loanList.empLoanId}"></c:set>
														<c:set var="loanName" value="${loanList.loanName}"></c:set>
														<c:set var="emiAmt" value="${loanList.loanIntEmiAmt}"></c:set>
														<c:set var="emiNo" value = "${loanList.loanIntEmiAmtString}" ></c:set>
														<c:set var="amtType" value="Interest"></c:set>
														<c:set var="outstandingIntAmt"
															value="${loanList.outstandingInterestAmt}"></c:set>

														<!-- added by ravysh -->
														<c:set var="loanAdvId" value="${loanList.loanTypeId}"></c:set>

														<tr>
															<td style="width: 25%" align="right"><b>
															<%-- <a	href="javascript:openWindow(${empLoanId})"
																title="Loan Will Be Opened in Edit Mode"
																onmouseover="window.status='loan';return true;"
																onmouseout="window.status='';"></a>--%>
																<c:out	value="${loanName}" />
																</b></td>
															<td width="14%" align="center">
															<b><c:out value="${emiNo}"></c:out></b>
															</td>
															<td width="14%" align="center">
															<hdiits:number name="loanTxt${size}"
																id="loanTxt" readonly="true" maxlength="5" default="${emiAmt}" size="5"
																style="background: #E5F0F2; font-family: Verdana;  text-align:right;font-size: 11px; font-weight: bold;	color :Black;" />
																</td>
																<!--
															<script>
										if(${loanAdvId}==31 || ${loanAdvId}==23 || ${loanAdvId}==27)
										ScooterCarLoanType=${loanAdvId};
										</script> -->
																<td width="14%" align="center">
																<hdiits:number name="loanTxtBill${loanAdvId}"
																id="loanTxtBill${loanAdvId}" maxlength="5" size="5"
																style="border:0px; background: #FFDDDD; font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />
																<b><c:out value="${amtType}"></c:out></b>
																</td>

															<td width="33%" align="center"><font color=red><b><c:out
																value="${outstandingIntAmt} remaining"></c:out></b></font>
																</td>
															<hdiits:hidden name="loanAmtType${size}" id="loanAmtType"
																default="${amtType}" />
															<hdiits:hidden name="loanTxtId${size}" id="loanTxtId"
																default="${loanList.empLoanId}" />
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
													i = i + 1;
														pageContext.setAttribute("size", i);
												%>
											</c:forEach>
											<hdiits:hidden name="loanSize" id="loanSize"
												default="${size-1}" />


											<!-- <tr id="PrevBillCarLoan">
								<td style="width:25%" align="center" ><b>Car/Sct/Moped ADV/Int.</b></td>
								<td width="40%">
							<hdiits:number name="loanTxtBillPrin"  id="loanTxtBillPrin"  maxlength="5"  size="5" style="border:0px;  font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />
							&nbsp;&nbsp;<hdiits:number name="loanTxtBillCarMoped"  id="loanTxtBillCarMoped"  maxlength="5"  size="5" style="border:0px; background: #FFDDDD; font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />	
								&nbsp;<b>Principle</b>
								</td>
								</tr>	
								
								<tr id="PrevBillCarLoan2">
								<td style="width:25%" align="center" ><b>Car/Sct/Moped ADV/Int.</b></td>
								<td width="40%">
							<hdiits:number name="loanTxtBillInt"  id="loanTxtBillInt"  maxlength="5"  size="5" style="border:0px;  font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />
							&nbsp;&nbsp;<hdiits:number name="loanTxtBillCarMopedInt"  id="loanTxtBillCarMopedInt"  maxlength="5"  size="5" style="border:0px; background: #FFDDDD; font-family: Verdana;  text-align:right;font-size: 11px;	font-weight: bold;	color :Black;" />	
								&nbsp;<b>Interest</b>
								</td>
								</tr>	 -->

											<%
												List hrLoanList = (List) pageContext.getAttribute("hrLoanList");
													if (hrLoanList != null && hrLoanList.size() > 0) {
														String shortName = "";
														String edpCode = "";
														String mthdName = "";
														String intMthdName = "";
														long basic;
														long basicInt;
														String loanCode = "";
														for (int i = 0; i < hrLoanList.size(); i++) {

															HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg) hrLoanList
																	.get(i);

															shortName = edpCompMpg.getRltBillTypeEdp()
																	.getEdpShortName();
															edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();
															loanCode = edpCompMpg.getTypeId();

															

															Class pay = payBillOut.getClass();
															Method payMthd =null;
															Method intPayMthd =null;
															try{
																mthdName = "getLoan" + edpCode;
																intMthdName = "getLoanInt" + edpCode;
																
																payMthd =pay.getMethod(mthdName, null);
																intPayMthd = pay.getMethod(intMthdName, null);
															}
															catch(Exception e)
															{
																
																mthdName = "getAdv" + edpCode;
																intMthdName = "getAdvInt" + edpCode;
																 payMthd = pay.getMethod(mthdName, null);
																  intPayMthd = pay.getMethod(intMthdName, null);
															}
															basic = Math.round((Double) payMthd.invoke(payBillOut,
																	null));

															
															basicInt = Math.round((Double) intPayMthd.invoke(
																	payBillOut, null));
											%>

											<script>
			var loancode="<%=loanCode%>";
            var docname = "loanTxtBill"+loancode;
            var loanamt='<%=basic%>';
          
          var carMopedAmt=loancode.split(",");

          if(carMopedAmt.size()==3){
				 
				if('<%=basic%>'!=0)
				{
					document.getElementById("loanTxtBill"+ScooterCarLoanType).value = '<%=basic%>';
				}
				else if('<%=basicInt%>'!=0)
				{
				document.getElementById("loanTxtBill"+ScooterCarLoanType).value = '<%=basicInt%>';
				}
				
				<%-- document.getElementById("loanTxtBillCarMoped").value = '<%=basic%>';
				
					document.getElementById("loanTxtBillCarMopedInt").value = '<%=basicInt%>';	--%> 
					
					 } else{
            
            if(loanamt!=0 && document.getElementById(docname)!=null){
			 document.getElementById(docname).value = '<%=basic%>';
            }else{
                if(document.getElementById(docname)!=null)
			 document.getElementById(docname).value = '<%=basicInt%>';	
            }
           
					 }
           </script>

											<%
												}
													}
											%>


											<%
												List hrAdvList = (List) pageContext.getAttribute("hrAdvList");
													if (hrAdvList != null && hrAdvList.size() > 0) {
														String shortName = "";
														String edpCode = "";
														String mthdName = "";
														long basic;
														String AdvCode = "";
														for (int i = 0; i < hrAdvList.size(); i++) {

															HrPayEdpCompoMpg edpCompMpg = (HrPayEdpCompoMpg) hrAdvList
																	.get(i);

															shortName = edpCompMpg.getRltBillTypeEdp()
																	.getEdpShortName();
															edpCode = edpCompMpg.getRltBillTypeEdp().getEdpCode();
															mthdName = "getAdv" + edpCode;

															AdvCode = edpCompMpg.getTypeId();

															Class pay = payBillOut.getClass();
															try{
															Method payMthd = pay.getMethod(mthdName, null);
															basic = Math.round((Double) payMthd.invoke(payBillOut,
																	null));
															}
															catch(Exception e )
															{
																continue;
															}
															if (edpCode.equals("9670")) {
																mthdName = "getAdvIV" + edpCode;

																Class pay1 = payBillOut.getClass();
																Method payMthd1 = pay.getMethod(mthdName, null);
																long basic1 = Math.round((Double) payMthd1.invoke(
																		payBillOut, null));
																basic = basic + basic1;
															}
											%>
											<script>
			var advcode='<%=AdvCode%>';
            var docname = "loanTxtBill"+advcode;
            var loanamt='<%=basic%>';
            document.getElementById(docname).value = '<%=basic%>';
			 
           </script>



											<%
												}
													}
											%>


										</table>
										</td>





										</tr>
										<c:forEach var="prevLoan" items="${loanCustomList}">
											
											<script>
											
										setPreviousLoanValues(${prevLoan.loanId},${prevLoan.loanValue});
										</script>
												</c:forEach>	
							
							<c:forEach var="prevAdv" items="${advanceCustomList}">
											
											<script>
											
											setPreviousAdvanceValues(${prevAdv.advanceId},${prevAdv.advanceVal});
										</script>
												</c:forEach>	
										<tr>
											<!--    <td align="right" style="padding-left: 120px;">-->
											<td align="left" style="padding-left: 185px;"><b>
											Total Sum Of A <font color="red">(D) (<Font face='Rupee Foradian'>`</Font>)</font> : </b> <!--<hdiits:text id="grossAmount" name="grossAmount" size="7"  style="border: 0px; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" readonly="true"/>-->
											<hdiits:text name="grossAmountBill" size="7"
												caption="Allowance" captionid="Allowance"
												id="grossAmountBill" readonly="true"
												style="border: 0px; background: #E5F0F2; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
											</td>

											<td align="left" style="padding-left: 165px;"><b>
											Deduction [B + C] <font color="red"> ( E ) (<Font face='Rupee Foradian'>`</Font>) </font> : </b> <!--<hdiits:text id="totalDeduc" name="totalDeduc" size="7"  style="border: 0px; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" readonly="true"/>-->
											<hdiits:text name="totalDeducBill" size="7"
												caption="Allowance" captionid="Allowance"
												id="totalDeducBill" readonly="true"
												style="border: 0px; background: #E5F0F2; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
											</td>

											<td align="right" style="padding-left: 120px;"><b>
											Net Amount [D - E] (<Font face='Rupee Foradian'>`</Font>) : </b> <!--<hdiits:text id="netAmount" name="netAmount" size="7"  style="border: 0px; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" readonly="true"/>-->
											<hdiits:text name="netAmountBill" size="7"
												caption="Allowance" captionid="Allowance" id="netAmountBill"
												readonly="true"
												style="border: 0px; background: #E5F0F2; text-align:right; font-family: Verdana;	font-size: 11px;	font-weight: bold;	color :Black;" />
											</td>
										</tr>

										<!-- ENDED BY VARUN SHARMA -->

										<tr>
											<td colspan="3" align="center"><!--<hdiits:button
												name="btnsubmit" id="btnsubmit" type="button"
												onclick="insertEmpAllowMpg1()" value="Save"
												title="Clicking this will freeze the salary for this Month" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;11 jan 2012 -->
											<hdiits:button name="btnClose" type="button" value="Close"
												onclick="onclosefn();" title="Clicking this will take you to Salary Config. Worklist" /></td>
										</tr>
								</table></td>
						</tr>
				</table>


				<!-- Ended by Mrugesh -->




				<script type="text/javascript"><!--




//if(document.getElementById("loanTxtBillCarMoped").value==0)
   //document.getElementById("PrevBillCarLoan").style.display="none";
//if(document.getElementById("loanTxtBillCarMopedInt").value==0)
	//document.getElementById("PrevBillCarLoan2").style.display="none";
	

	
		//document.getElementById("grossAmount").value=grossAmount;

		//document.getElementById("totalDeduc").value=totalDeduc;

		//document.getElementById("netAmount").value=grossAmount-totalDeduc;


	 //added by ravysh
	 //alert('going to disabled');
	 //document.getElementById("grossAmountBill").value='=grossAmount%>';
	 //document.getElementById("totalDeducBill").value='%=totalDeduc%>';
	 //ocument.getElementById("netAmountBill").value='netAmount';
	 var totalA=eval('${ttlAll}')+eval(document.getElementById("basicPay").value);
	 var netT=eval(totalA)-eval('${totalDed+loanTotal}');
	// alert(' net is '+netT);
	 document.getElementById("grossAmountBill").value= totalA;
	 document.getElementById("totalDeducBill").value=parseInt('${totalDed+loanTotal}');
	 document.getElementById("netAmountBill").value=netT;
	 
	 //alert('going to disabled1');
	 if(document.getElementById("txtBill91")!=null)
	   document.getElementById("txtBill91").value='<%=otherAllwVal%>';

	   if( document.getElementById("txtt36") != null)
	   {
	 document.getElementById("txtt36").style.backgroundColor="#E5F0F2";
	 document.getElementById("txtt36").readOnly="readonly";//5
	   }
	 //alert('going to disabled2');
	    /*if( document.getElementById("txtt72") != null)
	   {
	 document.getElementById("txtt72").value='${gpfGrpAbcVal}';
	 document.getElementById("txtt72").style.backgroundColor="#ffffff";
		document.getElementById("txtt72").readOnly=false;
	   } */
		//alert('going to disabled3');
		   if( document.getElementById("txtt75") != null)
	   {
	 document.getElementById("txtt75").style.backgroundColor="#E5F0F2";
	document.getElementById("txtt75").readOnly="readonly";
	   }
	//alert('going to disabled4');
	//manish
	if( document.getElementById("txtt76")!=null) {
 	 document.getElementById("txtt76").style.backgroundColor="#E5F0F2";
	 document.getElementById("txtt76").readOnly="readonly";
	}
	 if( document.getElementById("txtt77")!=null) {
	 document.getElementById("txtt77").style.backgroundColor="#E5F0F2";
	 document.getElementById("txtt77").readOnly="readonly";
	 }
	 if( document.getElementById("txtt78")!=null) {
	 document.getElementById("txtt78").style.backgroundColor="#E5F0F2";
	 document.getElementById("txtt78").readOnly="readonly";
	 }
	 if(document.getElementById("txtt80")!=null) {
	 document.getElementById("txtt80").style.backgroundColor="#E5F0F2";
	 document.getElementById("txtt80").readOnly="readonly";
	 }
	 if( document.getElementById("txtt81")!=null) {
	 document.getElementById("txtt81").style.backgroundColor="#E5F0F2";
	 document.getElementById("txtt81").readOnly="readonly";
	 }
	 if( document.getElementById("txtt87")!=null) {
	 document.getElementById("txtt87").style.backgroundColor="#E5F0F2";
	 document.getElementById("txtt87").readOnly="readonly";
	 }

	 if(document.getElementById("txtt80")!=null) {
		 document.getElementById("txtt80").style.backgroundColor="#E5F0F2";
		 document.getElementById("txtt80").readOnly="readonly";
		 }
		 if( document.getElementById("txtt81")!=null) {
		 document.getElementById("txtt81").style.backgroundColor="#E5F0F2";
		 document.getElementById("txtt81").readOnly="readonly";
		 }				
				
	 
	 if(document.getElementById("txtt75")!=null && document.getElementById("txtt75").value != 0)
	 {
		 
	 //alert("jay mataji  "+ document.getElementById("txtt75").value );
	 document.getElementById("txttBill75").value='<%=gpfIASOtherState%>';
	// alert("in the 1st::::"+document.getElementById("txttBill75").value);
	}
	if(document.getElementById("txtt76")!=null &&  document.getElementById("txtt76").value != 0 )
	{
	 //alert("jay mataji");
	 document.getElementById("txttBill76").value='<%=gpfIAS%>';
	}
	
	 //manish
		//manish
	//alert('jay bajarang bali');
	if(document.getElementById("txtt77")!=null && document.getElementById("txtt77").value != 0)
	{
	//alert("jay mataji");
		
	document.getElementById("txttBill77").value='<%=gpfIPS%>';
	}
	if(document.getElementById("txtt78")!=null && document.getElementById("txtt78").value != 0)
	{
	//alert("jay mataji");
	document.getElementById("txttBill78").value='<%=gpfIFS%>';
	}
	if(document.getElementById("txtt80")!=null && document.getElementById("txtt80").value != 0)
	{
	//alert("jay mataji");
	document.getElementById("txttBill80").value='<%=serviceCharge%>';
	}
	if(document.getElementById("txtt81")!=null && document.getElementById("txtt81").value != 0)
	{
	//alert("jay mataji");
	document.getElementById("txttBill81").value='<%=otherdeduction%>';
	}
	if(document.getElementById("txtt87")!=null && document.getElementById("txtt87").value != 0)
	{
	//alert("jay mataji");
	document.getElementById("txttBill87").value='<%=MahaStateLifeInsurance%>';
	}

	
	//manish
					
		
	
	//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.

	//alert('hi');
	//window.location="./hrms.htm?actionFlag=viewTemporaryPayBill&empId=${empId}&otherId=${otherId}";
	
	if("${msg}"!=null&&"${msg}"!='')
	{
		alert("${msg}");
		if("${msg}"!=null&&"${msg}"!=''&& ("${empAllRec}"=='Y' || "${empAllRec}"=='true' || "${empAllRec}"=='TRUE') )
		{
			var url="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES&PreviewBill=YES&empId=${empId}&empAllRec=true&otherId=${otherId}&edit=Y";
		}
		else
		{
			var url="./ifms.htm?actionFlag=getOtherDataMerged&MergedScreen=YES&PreviewBill=YES&otherId=${otherId}&edit=Y&empAllRec=false";
		}

		document.forms[0].action=url;
		document.forms[0].submit();
	}
	initializetabcontent("maintab");

	function openWindow(empLoanId)
	{
		var otherId='<%=pageContext.getAttribute("otherId").toString()%>';
		var url="./ifms.htm?actionFlag=getLoanValue&edit=Y&FromBasicDtlsNew=YES&otherId="+otherId+"&empLoanId="+empLoanId;
		window.open(url,'','status=0,height=600,scrollbars=yes');
	}
	if(document.getElementById("txtt${PF}") != null)
	{
	document.getElementById("txtt${PF}").readOnly="readonly";
	//document.getElementById("txtt${PF}").disabled = true;
	document.getElementById("txtt${PF}").style.backgroundColor="#E5F0F2";
	}
--></script>


				</body>
				<%
					} catch (Exception e) {
						e.printStackTrace();
					}
				%>