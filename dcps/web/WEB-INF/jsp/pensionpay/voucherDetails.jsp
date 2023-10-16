<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript" src='<c:url value="script/common/calendar.js"/>'></script>
<script type="text/javascript" src='<c:url value="script/common/CalendarPopup.js"/>'></script>
<script type="text/javascript" src="script/pensionpay/PensionPayWorkList.js"></script>
<script type="text/javascript"  src="script/common/common.js"></script>
<script type="text/javascript" src="script/pensionpay/HeaderFields.js"></script>

<fmt:setBundle basename="resources.pensionpay.PensionCaseLabels" var="pensionLabels" scope="request" />
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="lpnsnRqstId" value="${resValue.pnsnRqstId}"></c:set>
<c:set var="lpensionerDtlId" value="${resValue.pensionerDtlId}"></c:set>
<input type="hidden" name="hdnPensionerCode" id = "hdnPensionerCode" value="${resValue.PensionerCode}"/>
<input type="hidden" name="hdnLibraryNo" id = "hdnLibraryNo" />
<hdiits:form name="frmVoucherDetails" method="post" validate="" >
<fieldset style="width:100%"  class="tabstyle">
<legend	id="headingMsg">
			<b><fmt:message key="PPMT.VOUCHERDTLS" bundle="${pensionLabels}"></fmt:message></b>
	</legend>
	
	
	<input type="hidden" name="hidBillNo" id="hidBillNo" value="${resValue.BillNo}"/>
	<input type="hidden" name="currdate" id="currdate" value="${resValue.currdate}"/>
	<table align="center">
		<tr>
			<td><fmt:message key="PPMT.VOUCHERNO" bundle="${pensionLabels}" /></td>
			<td><input type="text" id="txtVoucherNo" name="txtVoucherNo" /></td>
		</tr>			
		<tr>
			<td><fmt:message key="PPMT.VOUCHERDATE" bundle="${pensionLabels}"/></td>
			<td><input type="text" id="txtVoucherDate" name="txtVoucherDate" value='' onkeypress="digitFormat(this);dateFormat(this);" maxlength="10" onBlur="chkValidDate(this);compareDatesForVoucher(this,document.getElementById('currdate'),'Please enter Voucher Date less than current date.','<');"/>
			&nbsp;<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open("txtVoucherDate",375,570)'style="cursor: pointer;" /></td>
		</tr>
	</table>

	
</fieldset>		
	<table align="center">
<hdiits:button name="btnSave" id="btnSave" type="button"  classcss="bigbutton" captionid="PPMT.SAVE" bundle="${pensionLabels}" onclick="saveVoucherDetailsUsingAJAX();" />
<hdiits:button name="btnClose" id="btnClose" type="button"  classcss="bigbutton" captionid="PPMT.CLOSE" bundle="${pensionLabels}" onclick="self.close();"/>
</table>
</hdiits:form>
<script type="text/javascript">

		var lpnsnRqstId='${resValue.pnsnRqstId}';
		var lpensionerDtlId= '${resValue.pensionerDtlId}';
</script>
<script >
function validateSaveVoucherDetails()
{
	if(document.getElementById("txtVoucherNo").value == "" || document.getElementById("txtVoucherNo").value == null)
	{
		alert("Please enter Voucher No.");
		return false;
	}
	if(document.getElementById("txtVoucherDate").value == "" || document.getElementById("txtVoucherDate").value == null)
	{
		alert("Please enter Voucher Date.");
		
		return false;
	}
	if(compareDatesForVoucher(document.getElementById("txtVoucherDate"),document.getElementById('currdate'),'Please enter Voucher Date less than current date.','<')== false)
	{
		//alert("Please enter Voucher Date less than current date.");
		//document.getElementById("txtVoucherDate").value="";
		//document.getElementById("txtVoucherDate").focus();
		return false;
	}

	return true;
}
function saveVoucherDetailsUsingAJAX()
{
	if(validateSaveVoucherDetails())
	{
		var voucherNo=document.getElementById("txtVoucherNo").value;
		var voucherDate=document.getElementById("txtVoucherDate").value;
		var billNo=document.getElementById("hidBillNo").value;
		url="ifms.htm?actionFlag=saveVoucherDetails";
		var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&voucherNo="+voucherNo+"&voucherDate="+voucherDate+"&billNo="+billNo,
		        onSuccess: function(myAjax) {
							getDataStateChangedForVoucherDetails(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	}
}
function getDataStateChangedForVoucherDetails(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	var flag="";
	
	if(XMLDoc != null)
	{
		var XmlHiddenValues = XMLDoc.getElementsByTagName('SAVED');
		
		for( var i=0; i<XmlHiddenValues.length ;i++) {
			
		  	 flag= XmlHiddenValues[i].childNodes[0].nodeValue;
		  	 }
	  	
		if(flag=='Y')
		{
			alert("Voucher Details saved Succesfully.");
			document.getElementById("btnSave").disabled=true;
			var showCaseFor=window.opener.document.getElementById("hidshowCaseFor").value;
			showProgressbar();
			forwCaseUsingAJAXForVoucher(showCaseFor,lpnsnRqstId,lpensionerDtlId);
		}
		else
		{
			
			alert("Voucher Details not Saved.Please Try again.");
			hideProgressbar();
		}
	}
}
function compareDatesForVoucher(fieldName1,fieldName2,alrtStr,flag)
{
	
	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;
    var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var day1=parseFloat(la_Date1[0]);
    var month1=parseFloat(la_Date1[1]);
    var year1=parseFloat(la_Date1[2]);

    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    var day2=parseFloat(la_Date2[0]);
    var month2=parseFloat(la_Date2[1]);
    var year2=parseFloat(la_Date2[2]);

    if (year2 == year1 && month2 == month1 && day2 == day1)
    {
    	if(flag == '=')
    	{
    		alert(alrtStr);
    	    fieldName1.focus();
    	    fieldName1.value="";
    	    return false;
    	}
    	else
    	{
	        return true;
	    }
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 && flag != '=')
    {
        alert(alrtStr);
        if(flag == '<')
        {
    	    fieldName1.focus();
    	    fieldName1.value="";
    	    return false;
    	}
    	else if(flag == '>')
    	{
    	    fieldName1.focus();
    	    fieldName1.value="";
    	    return false;
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {     
             alert(alrtStr);
             if(flag == '<')
	        {
    		    fieldName1.focus();
    		    fieldName1.value="";
    		    return false;
	    	}
    		else if(flag == '>')
    		{
	    	    fieldName1.focus();
	    	    fieldName1.value="";
	    	    return false;
    		}
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                 alert(alrtStr);
                 if(flag == '<')
			     {
    			    fieldName1.focus();
    			    fieldName1.value="";
    			    return false;
			   	}
		    	else if(flag == '>')
    			{
    			    fieldName1.focus();
    			    fieldName1.value="";
    			    return false;
		    	}
            }
        }
    }
    return true ;
}

</script>