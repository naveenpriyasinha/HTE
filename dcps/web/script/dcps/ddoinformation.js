



function EdisValidation()
   {
		x=document.getElementById("txtDDOName");
        x.disabled = !x.disabled;
        
        x=document.getElementById("txtWEFDate");
        x.disabled = !x.disabled;

        x=document.getElementById("instituteType");
        x.disabled = !x.disabled;
        
        x=document.getElementById("cmbDesignation");
        x.disabled = !x.disabled;

        var x=document.getElementById("txtTANNo");
        if(x==null){
        x.disabled = !x.disabled;
        }
        
        x=document.getElementById("txtITWardCircle");
        x.disabled = !x.disabled;

        x=document.getElementById("cmbBankName");
        x.disabled = !x.disabled;

        x=document.getElementById("cmbBranchName");
        x.disabled = !x.disabled;
        
        x=document.getElementById("txtIFSCCode");
        x.disabled = !x.disabled;

        x=document.getElementById("txtAccountNo");
        x.disabled = !x.disabled;

        x=document.getElementById("txtRemarks");
        x.disabled = !x.disabled;
        return false;
        
    }

function SaveDataAfterValidation()
{
	if(!chkEmpty(document.getElementById('txtAdminDept'),'Administrative Department'))
	{
		return false; 
	}
	if(!chkEmpty(document.getElementById('txtFieldDept'),'Field/HOD Department'))
	{
		return false;
	}
	if(!chkEmpty(document.getElementById('txtDDOName'),'Name of DDO'))
	{
		return false;
	}
	if(!chkEmpty(document.getElementById('cmbDesignation'),'Designation'))
	{
		return false;
	}
	if(!chkEmpty(document.getElementById('txtWEFDate'),'With Effect From Date'))
	{
		return false;
	}
	if(!chkEmpty(document.getElementById('txtTANNo'),'TAN no'))
	{
		return false;
	}
/*	if(!chkEmpty(document.getElementById('txtITWardCircle'),'ITO/Ward/Circle'))
	{
		return false;
	}*/
	if(!chkEmpty(document.getElementById('cmbBankName'),'Bank Name'))
	{
		return false;
	}
	if(!chkEmpty(document.getElementById('instituteType'),'Institution Type'))
	{
		return false;
	}

	if(!chkEmpty(document.getElementById('txtAccountNo'),'Account No'))
	{
		return false;
	}
	if(!validateDate(document.getElementById('txtWEFDate')))
	{
		return false;
	}	
	if(!compareDates(document.getElementById('txtWEFDate'),document.getElementById("currDate"),'WEFDate cannot be greater than current date','<'))
	{
		return false;
	}
	
	SaveDataUsingAjax();
	return true;
}

function SaveDataUsingAjax() {
	
	var uri = 'ifms.htm?actionFlag=addDDOInfo';
    var url = runForm(0); 
	
    var SelectOption=document.getElementById("cmbDesignation");
    var SelectedIndex=SelectOption.selectedIndex;
    var desigName=SelectOption.options[SelectOption.selectedIndex].text;
    
    url = url + "&DesigName="+desigName;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForDdoInfoSave(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}
function getDataStateChangedForDdoInfoSave(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			
	var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(test_Id)
	{
		alert("Record Saved Successfully.");
		self.location.reload(true);
		//self.location.href = "ifms.htm?actionFlag=loadDDOInfo";
	}	
}

function popUpIFSCCodeForBsrCode()
{
	//alert("hiiiiiiiiii");
	var cmbBranchName = document.getElementById("cmbBranchName").value;
	//var cbs = document.getElementById("cmbBranchName");
	//alert("cmbBranchName :- "+cmbBranchName);
	//alert("cbs :- "+cbs);
	
	var uri="ifms.htm?actionFlag=displayIFSCCodeForBranchBsrCode";
	var url="cmbBranchName="+cmbBranchName;
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForIFSCCodeDsply(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );

}

function getDataStateChangedForIFSCCodeDsply(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			
	var IFSCCode = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if(IFSCCode != 'null')
	{
		document.getElementById("txtIFSCCode").value = IFSCCode ;
	}
}

function ReturnPage()
{
	self.location.href="ifms.htm?actionFlag=validateLogin";
}

function ClearIfsCode()
{
	document.getElementById("txtIFSCCode").value = "";
}

function tanNoValidation()
{
	var tanValue=document.getElementById('txtTANNo').value;
	var regex1=/^[A-Za-z]{4}\d{5}[A-Za-z]{1}$/;  

	if(regex1.test(tanValue)== false && tanValue.length != 0){  
		alert('Please enter valid TAN No');  
		document.getElementById('txtTANNo').focus();
		return false;
	}
	return true;
}
function nameFormat(formfield)
{	var val;
	if((window.event.keyCode> 64 && window.event.keyCode < 91) || (window.event.keyCode> 96 && window.event.keyCode < 123) || (window.event.keyCode==32) || (window.event.keyCode==46))
	{
		val=formfield.value;
		if(val[1]!=null)
		{
			if(val[1].length>1)
			{
				window.event.keyCode=0;
			}
		}
	}
	else
	{
		window.event.keyCode=0;
	}
}