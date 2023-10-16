<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript"  src="script/pensionproc/pensionCaseBasicDtls.js"></script>
<script>
function validateCriteria()
{
	var sevaarthId = document.getElementById("txtSevaarthId").value;	
	var reqType=document.getElementById("cmbClassOfPnsn").value;

	 if(!document.getElementById("PensionCaseTypeRegular").checked &&  !document.getElementById("PensionCaseTypeDeputation").checked)
	 {
		 alert("Please select pension case type");
		 return false ;
	}
	 else if(sevaarthId.trim() == "")
	{
		alert('Please enter Sevaarth Id');
		document.getElementById("txtSevaarthId").focus();
		return false ;

	}else if(reqType == "-1"){
		alert('Please select Type of Pension');
		document.getElementById("cmbClassOfPnsn").focus();
		return false;
	}
	else if(document.getElementById("judCasePendingyes").checked) {
	//	alert("Since Judicial / Departmental enquiry is pending, the pension case cannot be processed. The pensioner is entitled for provisional pension as per the rules. Please proceed accordingly.");
		return true;
	}
	else if(document.getElementById("deptInqPendingyes").checked) {
	//	alert("Since Judicial / Departmental enquiry is pending, the pension case cannot be processed. The pensioner is entitled for provisional pension as per the rules. Please proceed accordingly.");
		return true;
	}
/*	else if(document.getElementById("contriReceivedno").checked) {
		alert("Since Pension contribution is not received From AG, the pension case cannot be processed. The pensioner is entitled for provisional pension as per the rules. Please proceed accordingly.");
		return false;
	}
	else if(document.getElementById("contriSanctionedno").checked) {
		alert("Since Leave contribution is not sanctioned by AG, the pension case cannot be processed. The pensioner is entitled for provisional pension as per the rules. Please proceed accordingly.");
		return false;
	}*/
	return true ;
}

function submitSearchDetailsReg112()
{ 

	alert("Inside submit*************");
  var pensionCaseType1="Regular";
  var url;

  alert("pensionCaseType1*%%%%%%**"+pensionCaseType1);
//Added by shraddha for Deputation module changes
	if(pensionCaseType1=="Regular"){
		alert("Inside Regular");
	//showPensionCasePopup();
	var flag = document.getElementById("btnYes").value;
	alert("flag***"+flag);
	if(flag=="Yes"){
		alert("iNSIDE Y %%%%");
		url = "ifms.htm?actionFlag=loadPenProcInwardForm&elementId=376394&pensionCaseType="+pensionCaseType1;
		alert(url);
		
		if(!validateCriteria())
		{
			return false ;
		}
	}
	
	else{

		document.pensionCasePopup.action = url ;
		document.pensionCasePopup.submit();

		}

	}

	else{
	 url = "ifms.htm?actionFlag=loadPenProcInwardForm&elementId=376394&pensionCaseType="+pensionCaseType1;
	//alert("url"+url);
	if(!validateCriteria())
	{
		return false ;
	}
	}
	 var sevaarthId=document.getElementById("txtSevaarthId").value;

		if(pensionCaseType1=="Deputation"){
	 	if(!checkIfCommonPool(sevaarthId))
		 {
			 return false;
		}
	 	else{

			document.pensionCasePopup.action = url ;
			document.pensionCasePopup.submit();

			}
		}

		
		else if(pensionCaseType1=="Provisional"){

			var judReaason="",deptReason="";
			if(document.getElementById('judCasePendingyes').checked==true)
			{
				if(document.getElementById('judCasePendingReason').value=='')
					{alert("Please enter Reason.");
					return false;
					}
				else
				{
					judReaason=document.getElementById('judCasePendingReason').value;
				
				}
			}
			 if(document.getElementById('deptInqPendingyes').checked==true){
				if(document.getElementById('deptInqPendingReason').value=='')
					{alert("Please enter Reason.");
					return false;
					}
				else
				{
					deptReason=document.getElementById('deptInqPendingReason').value;
				}
				}
			url=url+"&judCasePendingReason="+judReaason+"&deptInqPendingReason="+deptReason;
			//alert("url2"+url);
			document.pensionCasePopup.action = url ;
			document.pensionCasePopup.submit();
			}

		else{
		//	alert("ur3"+url);
	//var url = "ifms.htm?actionFlag=loadPensionCaseInwardForm&elementId=376394";	
	document.pensionCasePopup.action = url ;
	document.pensionCasePopup.submit();
		}
}

function checkIfCommonPool(sevaarthId)
{
	//alert(objVal);
	var status=false;
	
	uri = "ifms.htm?actionFlag=checkIfCommonPool&sevaarthId="+sevaarthId;
	myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:uri,
		        onSuccess: function(myAjax) {if(checkIfCommonPoolAjax(myAjax))status=true;},
		        onFailure: function(){ alert('Something went wrong...')} 
			});
	return status;
}


function checkIfCommonPoolAjax(myAjax)
{ 
	var XMLDoc = myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{				
		var XmlHiddenValues = XMLDoc.getElementsByTagName('MESSAGE');
		var lSaveStatus = XmlHiddenValues[0].childNodes[0].nodeValue;
		if(lSaveStatus == "Fail")
		{
			alert("Please enter employee details belonging to common pool. ");
			return false;
		}
	
		else {
					return true;}
}
	
}








</script>
<hdiits:form name="pensionCasePopup" method="post" validate="" >
<fieldset  class="tabstyle">
	<b>Is the Pension case preparation DDO same as that of Salary drawn DDO for this employee?</b>
	<br/><br/>
	<% String pensionType=request.getParameter("pensionCaseType1"); %>
	
	<center>
	<hdiits:button name="btnYes" id="btnYes" type="button" value="Y" classcss="bigbutton" caption="Yes" onclick="submitSearchDetailsReg112();" />
	<hdiits:button name="btnNo" id="btnNo" type="button" value="N"  classcss="bigbutton" caption="No"  />
	</center>
</fieldset>
</hdiits:form>