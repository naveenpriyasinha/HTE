<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<script type="text/javascript"  src="script/pensionpay/HeaderFields.js"></script>
<script type="text/javascript"  src="script/pensionpay/PaymentTab.js"></script>

<c:set var="resultObj" value="${result}" />
<c:set var="resValue" value="${resultObj.resultValue}" />
<c:set var="trnPensionRqstHdrVO" value="${resValue.TrnPensionRqstHdrVO}" />

<script>
function validateCvpDtls()
{
	if(IsEmptyFun("txtCvpOrderNo",COMTNORDERNO,'5')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtCvpPaidDate",COMTNORDERDT,'5')==false)
	{
		return false;
	}
	/*
	if(IsEmptyFun("txtTotalCvpAmount",COMTNAMT,'5')==false)
	{
		return false;
	}*/
	if(IsEmptyFun("txtCvpPayableAmount",COMTNPAYBLEAMT,'5')==false)
	{
		return false;
	}
	return true;
}
function saveCvpHistory()
{
	if(document.getElementById("hdnPensionerCode").value != "")
	{
		if(validateCvpDtls())
		{
			
			    var uri;
			    var pensionerCode = document.getElementById("hdnPensionerCode").value;
			    var cvpOrderNo = document.getElementById("txtCvpOrderNo").value;
			    var cvpOrderDate = document.getElementById("txtCvpPaidDate").value;
			   // var cvpTotalAmount = document.getElementById("txtTotalCvpAmount").value; //COMMUTATION  AMOUNT (CVP)
			    var cvpPaymentAmount = document.getElementById("txtCvpPayableAmount").value; // COMMUTATION  PAYABLE AMOUNT
			    var voucherNo = document.getElementById("txtCvpVoucherNo").value;
			    var voucherDate = document.getElementById("txtCvpVoucherDate").value;
			    
			    uri = 'ifms.htm?actionFlag=saveCommutationHistory&pensionerCode='+pensionerCode+'&cvpOrderNo='+cvpOrderNo+'&cvpOrderDate='+cvpOrderDate+
			    		'&cvpPaymentAmount='+cvpPaymentAmount+'&voucherNo='+voucherNo+'&voucherDate='+voucherDate;
			    saveCvpHistoryUsingAjax(uri);
		}
		
	}
	else
	{
		alert("Please save the case incase pension case is not saved.");
	}
}

function saveCvpHistoryUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
    {
     method: 'post',
     asynchronous: false,
     onSuccess: function(myAjax) {
			saveCvpHistoryOnStateChanged(myAjax);
		},
     onFailure: function(){ alert('Something went wrong...')} 
       } ); 
       
}

function saveCvpHistoryOnStateChanged(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
    
    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
        
    alert(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue);
    document.getElementById("txtCvpOrderNo").value = "";
    document.getElementById("txtCvpPaidDate").value = "";
    //document.getElementById("txtTotalCvpAmount").value = "";
    document.getElementById("txtCvpPayableAmount").value = "";
    document.getElementById("txtCvpVoucherNo").value = "";
    document.getElementById("txtCvpVoucherDate").value = "";
}


</script>

<fieldset class="tabstyle" style="width:100%"><legend> <b>
	<fmt:message key="PPMT.CVPDTLS" bundle="${pensionLabels}"></fmt:message></b> </legend>	
<table width="100%">	
<!-- 
	<tr>
		<td width="20%">
	       <fmt:message key="PPMT.CVPPAID" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		 <c:choose>
		 <c:when test="${trnPensionRqstHdrVO.cvpPaidFlag=='Y'}">
		    <fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioCvpPaid" name="radioCvpPaid" value="Y" checked="checked" onclick="enableCommutationDtls(this);"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioCvpPaid" name="radioCvpPaid" value="N" onclick="enableCommutationDtls(this);"/>
			</c:when>
			<c:otherwise>
			<fmt:message key="PPMT.YES" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioCvpPaid" name="radioCvpPaid" value="Y" onclick="enableCommutationDtls(this);"/>
			<fmt:message key="PPMT.NO" bundle="${pensionLabels}"></fmt:message>
			<input type="radio"id="radioCvpPaid" name="radioCvpPaid" value="N" checked="checked" onclick="enableCommutationDtls(this);"/>
			</c:otherwise>
			</c:choose>
		 </td>

		
	   		 		      
	</tr>
	 -->
	<tr>
		<td width="20%">
	       <fmt:message key="PPMT.CVPORDERNO" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtCvpOrderNo" name="txtCvpOrderNo" value="${trnPensionRqstHdrVO.cvpOrderNo}" onfocus="onFocus(this)"  onblur="onBlur(this);" maxlength="35"/>		      		      
		 </td>
		<td width="15%">
	 	 <fmt:message key="PPMT.CVPPAIDDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	     <input type="text" id="txtCvpPaidDate" name="txtCvpPaidDate"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.cvpDate}" />"
		       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfJoining,this,COMPAIDDTCOMDT,'<')"/>
		      <img id="imgCvpPaidDate" src='images/CalendarImages/ico-calendar.gif'  
					        onClick='window_open("txtCvpPaidDate",375,570)'style="cursor: pointer;" />
	    </td>
	   
	
		
		 	 		 
	</tr>
	<tr>
	<!--
		<td width="15%">
	       <fmt:message key="PPMT.CVPPAIDDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
		 <td width="35%">
		      <input type="text" id="txtCvpPaidDate" name="txtCvpPaidDate"  value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.cvpDate}" />"
		       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" disabled="disabled"
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);compareDates(txtDateOfCommencement,this,COMPAIDDTCOMDT,'<')"/>
		      <img id="imgCvpPaidDate" src='images/CalendarImages/ico-calendar.gif'  
					        onClick='window_open("txtCvpPaidDate",375,570)'style="cursor: pointer;" />
		 </td> 
		  
		<td width="20%">
	       <fmt:message key="PPMT.CVPAMOUNT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtTotalCvpAmount" name="txtTotalCvpAmount" value="${trnPensionRqstHdrVO.totalCvpAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right"/>	    
		 </td> --> 	
	 	 <td width="15%">
	       <fmt:message key="PPMT.CVPPAYABLEAMT" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtCvpPayableAmount" name="txtCvpPayableAmount" value="${trnPensionRqstHdrVO.cvpAmount}" onfocus="onFocus(this)"  onblur="onBlur(this);setValidAmountFormat(this);" onkeypress="amountFormat(this);"  style="text-align: right" />	    
		 </td>
		 <td width="20%">
	 	 <fmt:message key="PPMT.CVPVOUCHERNO" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	    <input type="text" id="txtCvpVoucherNo" name="txtCvpVoucherNo" value="${trnPensionRqstHdrVO.cvpVoucherNo}"  onfocus="onFocus(this)"  onblur="onBlur(this);" onKeyPress="numberFormat(this)" maxlength="20"/>
	     
	    </td>
	   
	</tr>
	<tr>
		
	    <td width="15%">
	       <fmt:message key="PPMT.CVPVOUCHERDATE" bundle="${pensionLabels}"></fmt:message>
	    </td>
	    <td width="35%">
	        <input type="text" id="txtCvpVoucherDate" name="txtCvpVoucherDate" value="<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${trnPensionRqstHdrVO.cvpVoucherDate}" />"  
		       onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" 
					onKeyPress="numberFormat(this)" onfocus="onFocus(this)" onblur="onBlur(this);chkValidDate(this);"/>
		      <img id="imgCvpPaidDate" src='images/CalendarImages/ico-calendar.gif'  
					        onClick='window_open("txtCvpVoucherDate",375,570)'style="cursor: pointer;" />	    
		 </td>
		 	 		 
	</tr>
	
	<tr>
		<td width="20%">
	    </td>
	    <td width="35%">
	    
	     <hdiits:button id="btnViewCvpHistory" name="btnViewCvpHistory" type="button" captionid="PPMT.VIEWHISTORY" onclick='showCommutationHistory();' bundle="${pensionLabels}" classcss="bigbutton"  />
	    
 	    </td>
	    <td width="15%">
	    </td>
	    <td width="35%">
	  
	        <hdiits:button id="btnSaveCvpHistory" name="btnSaveCvpHistory" type="button" captionid="PPMT.SAVEHISTORY" onclick='saveCvpHistory();' bundle="${pensionLabels}" classcss="bigbutton"  />
	     
		 </td>
	</tr>
	<!--  
	<script>
		document.getElementById("imgCvpPaidDate").disabled=true;
	</script>
	<c:if test="${trnPensionRqstHdrVO.cvpPaidFlag=='Y'}">
	<script>
		document.getElementById("txtCvpPaidDate").disabled=false;
		document.getElementById("imgCvpPaidDate").disabled=false;
	</script>
	</c:if>
	-->
</table>
</fieldset>
<!-- 
<c:if test="${trnPensionRqstHdrVO.cvpPaidFlag == 'Y'}">
<script>
document.getElementById("txtCvpOrderNo").disabled=false;
document.getElementById("txtCvpPaidDate").disabled=false;
document.getElementById("txtCvpVoucherNo").disabled=false;
document.getElementById("txtCvpVoucherDate").disabled=false;
document.getElementById("txtCvpPayableAmount").disabled=false;
document.getElementById("txtTotalCvpAmount").disabled=false;
</script>
</c:if> -->