<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.ess.wll.wll"
	var="commonLables1" scope="request" />

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
	
	

<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>

<script>

function calculate(flg)
{

 var totalArray=new Array('LoanAmount','LoanInterest','years');										 
				var statusValidation =  validateSpecificFormFields(totalArray);	
						
if(statusValidation == true)
{						
  //alert(flg);
   if(flg == 1)
  {
    
    
    
    var loantermtype = document.emi.LoanTerm.value;
    var principal =  document.emi.LoanAmount.value;
    var Rate = document.emi.LoanInterest.value;
    var interest = document.emi.LoanInterest.value / 100 / 12;
    //alert('flag1'+loantermtype);
    //alert('flag1'+principal);
    //alert('flag1'+interest);

    var payments = 0;
    var chkpay = 0;
   
    if(loantermtype=="M")
    {
      chkpay = document.emi.years.value;
      payments = document.emi.years.value;
    }
    else
    {
      chkpay = document.emi.years.value;
      payments = document.emi.years.value * 12;
    }

    var x = Math.pow(1 + interest, payments);
    //alert('x to caluclate monthly'+x);
    var monthly = (principal*x*interest)/(x-1);
    //alert('monthly'+monthly);
    if (!isNaN(monthly) && 
        (monthly != Number.POSITIVE_INFINITY) &&
        (monthly != Number.NEGATIVE_INFINITY))
    {
      document.emi.payment.value = round(monthly);
      document.emi.total.value = round(monthly * payments);
      document.emi.totalinterest.value = round((monthly * payments) - principal);
    }
    else 
    {
      document.emi.payment.value = round(principal/payments);
      document.emi.total.value = principal;
      document.emi.totalinterest.value = "0";
    }
  }
  else
  {
  
    var loantermtype = document.emi.LoanTerm.value;
    var principal =  document.emi.LoanAmount.value;
    var interest = document.emi.LoanInterest.value / 100 / 12;
    var payments = 0;
   // alert(loantermtype);
    //alert(principal);
    //alert(interest);
 
    if(loantermtype=="M")
    {
     // alert('inside M');
      payments = document.emi.years.value;
    }
    else
    {
     // alert('inside Y');
      payments = document.emi.years.value * 12;
    }
    var x = Math.pow(1 + interest, payments);
    //alert(x);
    var monthly = (principal*x*interest)/(x-1);
    //alert(monthly);

    if (!isNaN(monthly) && (principal != "") &&
        (monthly != Number.POSITIVE_INFINITY) &&
        (monthly != Number.NEGATIVE_INFINITY))
    {
      document.emi.payment.value = round(monthly);
      document.emi.total.value = round(monthly * payments);
      document.emi.totalinterest.value = round((monthly * payments) - principal);
    }
    else 
    {
      document.emi.payment.value = "";
      document.emi.total.value = "";
      document.emi.totalinterest.value = "";
    }
  }
}

else
{
return
}
}
function round(x)
{
  return Math.round(x*100)/100;
}


</script>

<hdiits:form name="emi" validate="true" method="POST"  encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>WelFare</b></a></li>
	</ul>
</div>
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">

	 
	 <table class="tabtable">

	<tr>
		<td class="fieldLabel" colspan="6" align="center">
			<font color="#ffffff">
				<b><hdiits:caption captionid="HR.EIS.EmiCal" bundle="${commonLables1}"/></b>
			</font>
		</td>
	</tr>
	
    <tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.LoanAmount" bundle="${commonLables1}" />:</b></td>
	<td width="25%"><hdiits:number id ="LoanAmount"  captionid="HR.EIS.LoanAmount"  bundle="${commonLables1}" name="LoanAmount"  mandatory="true" validation="txt.isrequired,txt.isnumber"  /></td>
	<td width="25%"></td>
	<td width="25%"></td>
	</tr>
	
	<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.LoanInterest" bundle="${commonLables1}" />:</b></td>
	<td width="25%"><hdiits:text id ="LoanInterest"  captionid="HR.EIS.LoanInterest"  bundle="${commonLables1}" name="LoanInterest"  mandatory="true" validation="txt.isrequired" /></td>
    <td width="25%"></td>
    <td width="25%"></td>
    </tr>
    
    <tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.LoanTerm" bundle="${commonLables1}" />:</b></td>
	<td width="25%"><hdiits:number id ="years"  captionid="HR.EIS.LoanTerm"  bundle="${commonLables1}" name="years"  mandatory="true" validation="txt.isrequired,txt.isnumber" /></td>
	<td>
	<hdiits:select captionid="HR.EIS.LoanTerm" name="LoanTerm" id="LoanTerm"  onchange="calculate(0)"> 
			
			<option value="M"><hdiits:caption captionid="HR.EIS.months" bundle="${commonLables1}" captionLang="single"/></option>
			<option value="Y"><hdiits:caption captionid="HR.EIS.years" bundle="${commonLables1}" captionLang="single"/></option>			
			
			</hdiits:select></td>
	<td width="25%"></td>
	</tr>
	
	<tr><td colspan ="4" align= "center">
   <hdiits:button name="calculate1" type="button" onclick="calculate(1)" value="Calculate" captionid="HR.ESS.Calculate" bundle="${commonLables1}"/>
	</td>
	</tr>
	
	
	<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.MonthlyPayment" bundle="${commonLables1}" />:</b></td>
	<td width="25%"><hdiits:text name="payment" id="payment" readonly="true" /></td>
    <td width="25%"></td>
    <td width="25%"></td>
    </tr>
    
    <tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.totalPayment" bundle="${commonLables1}" />:</b></td>
	<td width="25%"><hdiits:text name="total" id="total" readonly="true" /></td>
	<td width="25%"></td>
	<td width="25%"></td>
	</tr>
	
	<tr>
	<td width="25%"><b><hdiits:caption captionid="HR.EIS.InterestPayment" bundle="${commonLables1}" />:</b></td>
	<td width="25%"><hdiits:text name="totalinterest" id="totalinterest" readonly="true" /></td>
    <td width="25%"></td>
    <td width="25%"></td>
    </tr>
    
    
    
<tr><td colspan ="4" align= "center">	
<hdiits:button name="closebtn" id="closebtn" type="button" onclick="window.close();" captionid="HR.ESS.Close" bundle="${commonLables1}"/></td>

</tr>
</table>
	
	
	 
	<script type="text/javascript">
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="test" locale='<%=(String)session.getAttribute("locale")%>' />


</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>



