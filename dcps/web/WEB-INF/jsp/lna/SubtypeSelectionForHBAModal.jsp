<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="twoPloatPurchase" value="${resValue.twoPloatPurchase}"/>
<c:set var="onePloatPurchase" value="${resValue.onePloatPurchase}"/>
<c:set var="threeLaterCons" value="${resValue.threeLaterCons}"/>
<c:set var="twoLaterCons" value="${resValue.twoLaterCons}"/>
<c:set var="oneLaterCons" value="${resValue.oneLaterCons}"/>
<c:set var="SevaarthId" value="${resValue.SevaarthId}"/>

<input type="hidden" name="hidTwoPloatPurchase" id="hidTwoPloatPurchase" value="${twoPloatPurchase}" />
<input type="hidden" name="hidOnePloatPurchase" id="hidOnePloatPurchase" value="${onePloatPurchase}" />
<input type="hidden" name="hidThreeLaterCons" id="hidThreeLaterCons" value="${threeLaterCons}" />
<input type="hidden" name="hidTwoLaterCons" id="hidTwoLaterCons" value="${twoLaterCons}" />
<input type="hidden" name="hidOneLaterCons" id="hidOneLaterCons" value="${oneLaterCons}" />
<input type="hidden" name="hidSevaarthId" id="hidSevaarthId" value="${SevaarthId}" />

<script language="JavaScript">
var twoPloatPurchase = document.getElementById("hidTwoPloatPurchase").value;
var onePloatPurchase = document.getElementById("hidOnePloatPurchase").value;
var threeLaterCons = document.getElementById("hidThreeLaterCons").value;
var twoLaterCons = document.getElementById("hidTwoLaterCons").value;
var oneLaterCons = document.getElementById("hidOneLaterCons").value;
var sevaarthId = document.getElementById("hidSevaarthId").value;

var url;
if(twoPloatPurchase != '' && twoPloatPurchase != null){
	url = "ifms.htm?viewName=SubtypeSelectionSecondCF";	
}else if(onePloatPurchase != '' && onePloatPurchase != null){
	url = "ifms.htm?viewName=SubtypeSelectionFirstCF";	
}else if(threeLaterCons != '' && threeLaterCons != null){
	url = "ifms.htm?viewName=SubtypeSelectionFourLC";	
}else if(twoLaterCons != '' && twoLaterCons != null){
	url = "ifms.htm?viewName=SubtypeSelectionThirdLC";	
}else if(oneLaterCons != '' && oneLaterCons != null){
	url = "ifms.htm?viewName=SubtypeSelectionSecondLC";	
}


var returnvalue = window.showModalDialog(url, "","dialogHeight:150px;dialogWidth:500px;scroll:No;resizable:no;status:no;resizable:no");

 
	if(returnvalue=="Stop2ndCF"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Stop2ndCF";
	}
	else if(returnvalue=="Club2nd3rdCF"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Club2nd3rdCF";
	}
	else if(returnvalue=="Want2ndCF"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Want2ndCF";
	}
	else if(returnvalue=="Stop3rdCF"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Stop3rdCF";
	}
	else if(returnvalue=="Want3rdCF"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Want3rdCF";
	}
	else if(returnvalue=="Stop4thLC"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Stop4thLC";
	}
	else if(returnvalue=="Want4thLC"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Want4thLC";
	}
	else if(returnvalue=="Stop3rdLC"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Stop3rdLC";
	}
	else if(returnvalue=="Club3rd4thLC"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Club3rd4thLC";
	}
	else if(returnvalue=="Want3rdLC"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Want3rdLC";
	}
	else if(returnvalue=="Stop2ndLC"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Stop2ndLC";
	}
	else if(returnvalue=="Want2ndLC"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=Want2ndLC";
	}
	else if(returnvalue=="WantSp"){
		self.location.href="ifms.htm?actionFlag=loadLoanAdvanceRequest&elementId=800025&txtSevaarthId="
			+sevaarthId+"&criteria=1&requestType=800029&userType=HODASST2&disburse=800059";
	}	
	else if(returnvalue == "No")
	{	self.location.href="ifms.htm?actionFlag=loadLNASearchForm&userType=HODASST2&elementId=800025";
	} 

</script>
