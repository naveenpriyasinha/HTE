function saveDesigInfo()
{
	
	var uri;
    if(validDesigInfo() == true)
	{
			   uri = "ifms.htm?actionFlag=saveDesigInfo";
			   saveDesigDetailsUsingAjx(uri);
    } 
}

function onchangevalues()
{
	var str="",i;
	var qualList = document.forms.dcpsDesigInfo;
	var moreQualification="";
	for (i=0;i<qualList.moreQualification.length;i++) {
	    if (dcpsDesigInfo.moreQualification[i].selected) {
	        str = str + dcpsDesigInfo.moreQualification.options[i].value + ",";
	        document.getElementById("txtmoreQualification").value=str;
	    	}
	}
	document.getElementById("txtmoreQualification").value=str;
	//document.getElementById(moreQualification).value=str;
	moreQualification=document.getElementById("txtmoreQualification").value
	//alert("Options selected are " + moreQualification);
	
}

function fieldDisplay()
{
	var btn = document.getElementById("btnaddqualification").value;
	
	document.getElementById("moreQualification").style.display="block";
}

function morequalification()
{
	var uri;
    if(validDesigInfo() == true)
	{
			   uri = "ifms.htm?actionFlag=moreQualificationAdd";
			   saveDesigDetailsUsingAjx(uri);
    } 
}

function saveDesigDetailsUsingAjx(uri)
{
	//Fields to be added
	
	var txtDesig = document.getElementById("txtDesig").value.trim();
	var cmbFieldDepartment = document.getElementById("cmbFieldDepartment").value.trim();
	var txtDesigCode = document.getElementById("txtDesigCode").value.trim();
	var cmbCadre = document.getElementById("cmbCadre").value.trim();
	var cmbPayCommission = document.getElementById("cmbPayCommission").value.trim();
	var txtDesigShort = document.getElementById("txtDesigShort").value.trim();
	var qualification = document.getElementById("qualification").value.trim();
	var str="",i;
	var qualList = document.forms.dcpsDesigInfo;
	var moreQualification="";
	for (i=0;i<qualList.moreQualification.length;i++) {
	    if (dcpsDesigInfo.moreQualification[i].selected) {
	        str = str + dcpsDesigInfo.moreQualification.options[i].value + ",";
	        document.getElementById("txtmoreQualification").value=str;
	        
	    	}
	}
	document.getElementById("txtmoreQualification").value=str;
	//document.getElementById(moreQualification).value=str;
	moreQualification=document.getElementById("txtmoreQualification").value
	
	
	//alert(moreQualification);
	//alert(qualification);
//   var url = runForm(0); 
	
	var url = "txtDesig="+txtDesig+"&cmbFieldDepartment="+cmbFieldDepartment+"&txtDesigCode="+txtDesigCode+
			  "&cmbCadre="+cmbCadre+"&cmbPayCommission="+cmbPayCommission+"&txtDesigShort="+txtDesigShort+"&qualification="+qualification+"&moreQualification="+moreQualification;
   //alert(url);
   var myAjax = new Ajax.Request(uri,
	       {
	        method: 'post',
	        asynchronous: false,
	        parameters:url,
	        onSuccess: function(myAjax) {
	   			desigCaseStateChanged(myAjax);
			},
	        onFailure: function(){ alert('Something went wrong...');} 
	          } );
}

function desigCaseStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (successFlag=='true') {
			alert("Designation Details saved successfully.");
			var currFieldDeptId = document.getElementById("cmbFieldDepartment").value;
			self.location.href = "ifms.htm?actionFlag=loadDesigInfo&elementId=700042&currFieldDeptId="+currFieldDeptId ;
	}
}

function validDesigInfo()
{
	if(!chkEmpty(document.getElementById('cmbFieldDepartment'),'Field Department') || 
			!chkEmpty(document.getElementById('txtDesigCode'),'Designation Code') || 
			!chkEmpty(document.getElementById('txtDesig'),'Designation') || 
			!chkEmpty(document.getElementById('txtDesigShort'),'Desgnation Short') ||
			!chkEmpty(document.getElementById('cmbCadre'),'Cadre') ||
			!chkEmpty(document.getElementById('cmbPayCommission'),'Pay Commission')){
		return false;
	}
	else{
		return true;
	}
}

function saveOrgInfo()
{
	var uri;
    if(validOrgInfo() == true)
	{
	   uri = "ifms.htm?actionFlag=saveOrgInfo";
	   saveOrgDetailsUsingAjx(uri);
    } 
}

function saveOrgDetailsUsingAjx(uri)
{
   var url = runForm(0); 
   var myAjax = new Ajax.Request(uri,
	       {
	        method: 'post',
	        asynchronous: false,
	        parameters:url,
	        onSuccess: function(myAjax) {
	   			orgCaseStateChanged(myAjax);
			},
	        onFailure: function(){ alert('Something went wrong...');} 
	          } );
}

function orgCaseStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (successFlag=='true') {
			alert("Organization Details saved successfully.");
			self.location.href="ifms.htm?actionFlag=loadOrgInfo&elementId=700071";
	}
}

function validOrgInfo()
{
	if(!chkEmpty(document.getElementById('txtOrgType'),'Organization Type') || 
			!chkEmpty(document.getElementById('txtOrgDesc'),'Organization Description') || 
			!chkEmpty(document.getElementById('txtEmpHeadAcc'),'Employee Head Account') || 
			!chkEmpty(document.getElementById('txtEmpSchemeCode'),'Employee Scheme Code') ||
			!chkEmpty(document.getElementById('txtEmplrHeadAcc'),'Employer Head Account') ||
			!chkEmpty(document.getElementById('txtEmplrSchemeCode'),'Employer Scheme Code') ||
			!chkEmpty(document.getElementById('txtDeptEmpHeadAcc'),'Deputation Employee Head Account') ||
			!chkEmpty(document.getElementById('txtDeptEmpSchemeCode'),'Deputation Employee Scheme Code') ||
			!chkEmpty(document.getElementById('txtDeptEmplrHeadAcc'),'Deputation Employer Head Account') ||
			!chkEmpty(document.getElementById('txtDeptEmplrSchemeCode'),'Deputation Employer Scheme Code')){
		return false;
	}
	else{
		return true;
	}
}

function clearAllValues()
{
	document.getElementById("txtOrgType").value='';
	document.getElementById("txtOrgDesc").value='';
	document.getElementById("txtEmpHeadAcc").value='';
	document.getElementById("txtEmpSchemeCode").value='';
	document.getElementById("txtEmplrHeadAcc").value='';
	document.getElementById("txtEmplrSchemeCode").value='';
	document.getElementById("txtDeptEmpHeadAcc").value='';
	document.getElementById("txtDeptEmpSchemeCode").value='';
	document.getElementById("txtDeptEmplrHeadAcc").value='';
	document.getElementById("txtDeptEmplrSchemeCode").value='';
}

function validCadreInfo(){
	
	if(!chkEmpty(document.getElementById('cmbFieldDepartment'),'Field Department') || 
			!chkEmpty(document.getElementById('cmbGroup'),'Group') || 
			!chkEmpty(document.getElementById('txtCadreCode'),'Cadre Code') || 
			!chkEmpty(document.getElementById('txtCadreDesc'),'Cadre Description') ||
			!chkEmpty(document.getElementById('txtSuperAnnuAge'),'Super Annuation Age')){
		return false;
	}
	else{
		if(document.dcpsCadreInfo.radioCadreControlDept[1].checked && !chkEmpty(document.getElementById('cmbCntrlFieldDept'),'Controlled By Field Department')){
			return false;
		}
		else{
			return true;
		}
	}
}

function hideRadio(object)
{
	var radioval=object.value;

	   if(radioval == "Y")
	   {
		   document.getElementById("T1").style.display="none"; 		   
	   }
	   else if(radioval == "N")
	   {		   			     	
		   document.getElementById("T1").style.display ="";     
	   }
}

function saveCadreData()
{
	var uri;
    if(validCadreInfo() == true)
	{
			   uri = "ifms.htm?actionFlag=InsertCadre";
			   saveCadreDetailsUsingAjx(uri);
    } 
}

function saveCadreDetailsUsingAjx(uri)
{
   var url = runForm(0); 
   var myAjax = new Ajax.Request(uri,
	       {
	        method: 'post',
	        asynchronous: false,
	        parameters:url,
	        onSuccess: function(myAjax) {
	   			cadreCaseStateChanged(myAjax);
			},
	        onFailure: function(){ alert('Something went wrong...');} 
	          } );
}

function cadreCaseStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	if (successFlag=='true') {
			alert("Cadre Details saved successfully.");
			var currFieldDeptId = document.getElementById("cmbFieldDepartment").value;
			self.location.href = "ifms.htm?actionFlag=loadCadreInfo&elementId=700041&currFieldDeptId="+currFieldDeptId ;
	}
}

function getCadresForTheFieldDept()
{
	var currFieldDeptId = document.getElementById("cmbFieldDepartment").value;
	self.location.href = "ifms.htm?actionFlag=loadCadreInfo&elementId=700041&currFieldDeptId="+currFieldDeptId ;
}

function getDesigsForTheFieldDept()
{
	var currFieldDeptId = document.getElementById("cmbFieldDepartment").value;
	self.location.href = "ifms.htm?actionFlag=loadDesigInfo&elementId=700042&currFieldDeptId="+currFieldDeptId ;
}