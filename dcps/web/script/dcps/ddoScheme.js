function setSchemeCode()
{
	document.getElementById("txtSchemeCode").value=document.getElementById("cmbSchemeName").value;
	clear();
}


function clear()
{

		//alert("Inside the if of CheckSubSchemeExistForTable ");
		var nextRow= Number (document.getElementById("hidTotalRows").value);
	 	document.getElementById("txtSubSchemeCode").value="";
		document.getElementById("cmbSubSchemeName").value="";
		//alert("Value of the next row"+nextRow);
	 	if (nextRow>0)
	 	{

	 		for(var i=0;i<=nextRow;i++)
	 	 	{
	 	 	 	//alert("Inside the for of loop  of clear ");
	 	 	 	document.getElementById("txtSubSchemeCode"+(i+1)).value="";
	 	 	 	document.getElementById("cmbSubSchemeName"+(i+1)).value="";
	 	 	 		
	 	 	 		
	 	 		}
	 	 		
	 	 	}

}

//modified by saurabh
function SaveDataAfterValidation(){
	 
	var isSubScheme =document.getElementById("isSubScheme").value;

	if(isSubScheme==1){
	 
	 
	if(chkEmpty(document.getElementById('txtSchemeCode'),'Scheme Code') && chkEmpty(document.getElementById('cmbSchemeName'),'Scheme Name'))
	{
	SaveData();
	}
	}
	 
	else{
	if(chkEmpty(document.getElementById('txtSchemeCode'),'Scheme Code') && chkEmpty(document.getElementById('cmbSchemeName'),'Scheme Name')&& chkEmpty(document.getElementById('txtSubSchemeCode'),'Sub-Scheme Code')&& chkEmpty(document.getElementById('cmbSubSchemeName'),'Sub-Scheme Name'))
	SaveData();
	 
	}
	}


/* commented by saurabh function SaveDataAfterValidation(){
	//alert("hi111111111");
	if(chkEmpty(document.getElementById('txtSchemeCode'),'Scheme Code') && chkEmpty(document.getElementById('cmbSchemeName'),'Scheme Name') )
	{
		//alert("hi11");
		SaveData();
	}
}*/



// added by roshan
//added by roshan
function filterInstitute(){
	var ddoCode= document.getElementById("ddoCode").value;
	var taluka= document.getElementById("cmbTaluka").value;
	var url;

		url="./hrms.htm?actionFlag=loadDdoSchemesAndBillGroups&edit=N&elementId=9000189&taluka="+taluka+"&ddoCode="+ddoCode;
		document.frmScheme.action= url;
		document.frmScheme.submit();
}

function filterInstituteforDept(){
	//alert('hiiii ghf');
	var ddoCode= document.getElementById("ddoCode").value;
	var taluka= document.getElementById("cmbTaluka").value;
	var url;

		url="./hrms.htm?actionFlag=DeptCompMpg&taluka="+taluka+"&ddoCode="+ddoCode;
		document.DeptCompMPG.action= url;
		document.DeptCompMPG.submit();
}



function filterCreatedPost(){
	//alert('hiiii ghf');
	var ddoCodeForFilter= document.getElementById("ddoCodeForFilter").value;
	
	var url;

		url="./hrms.htm?actionFlag=showAdminPostDtl&ddoCodeForFilter="+ddoCodeForFilter;
		document.frmAdminCrtPost.action= url;
		document.frmAdminCrtPost.submit();
}

function filterInstituteForPostEntry(){
	//alert('hiiii ghf');
	//modified by vaibhav
	var ddoCodeForFilter= document.getElementById("ddoCodeforFilter").value;
	var taluka= document.getElementById("cmbTaluka").value;
	var url;
		url="./hrms.htm?actionFlag=addAdminPostDtl&taluka="+taluka+"&ddoCodeForFilter="+ddoCodeForFilter;
		document.frmAdminCrtPost.action= url;
		document.frmAdminCrtPost.submit();
}

function filterInstituteForApprove(){
	var ddoCode= document.getElementById("ddoCode").value;
	var taluka= document.getElementById("cmbTaluka").value;
	var url;

		url="./hrms.htm?actionFlag=loadApproveDdoOffice&taluka="+taluka+"&ddoCode="+ddoCode;
		document.frmDCPSDdoOffice.action= url;
		document.frmDCPSDdoOffice.submit();
}

//end by roshan
//end by roshan
function SaveData() {

	var txtSchemeCode = document.getElementById("txtSchemeCode").value;
	//alert("txtSchemeCode"+txtSchemeCode);
	var cmbSchemeName = document.getElementById("cmbSchemeName").text;
	//alert("cmbSchemeName"+cmbSchemeName);
	var cmbsubDdos=document.getElementById("cmbsubDdos").value;
	var nextRow= Number (document.getElementById("hidTotalRows").value);
	
	var txtSubSchemeCode="";
	for(var counter=1;counter<=nextRow;counter++)
	{
	txtSubSchemeCode = txtSubSchemeCode + document.getElementById("txtSubSchemeCode"+counter).value + "~";
	}
	//alert("subschemecode"+txtSubSchemeCode);
	var uri="ifms.htm?actionFlag=addSchemesAndBillGroupsToDdo";
	var url="txtSchemeCode="+txtSchemeCode+"&txtSubSchemeCode="+txtSubSchemeCode+"&cmbsubDdos="+cmbsubDdos;
	//alert(url);
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForSaveSchemes(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForSaveSchemes(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var lFlagSchemeAddedOrNot = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
	var lFlagSubSchemeAddedOrNot = XmlHiddenValues[0].childNodes[2].firstChild.nodeValue;
	
	if(lFlagSchemeAddedOrNot=='false')
	{
		alert('The scheme has already been attached to DDO');
	}
	
	if (lFlagSubSchemeAddedOrNot=='false')
	{
		
		alert("The selected scheme and subscheme has  been already  added to DDO");
		self.location.href = 'ifms.htm?actionFlag=loadDdoSchemesAndBillGroups&elementId=700015';
	}
	
	if(lBlFlag=='true')
	{
		if(lFlagSchemeAddedOrNot=='true')
		{
			alert("The selected scheme and bill group has been successfully added to DDO");
			self.location.href = 'ifms.htm?actionFlag=loadDdoSchemesAndBillGroups&elementId=700015';
		}
	}
}

function popUpSchemeName()
{
	var txtSchemeCode = document.getElementById("txtSchemeCode").value;
	var cmbsubDdos = document.getElementById("cmbsubDdos").value;
	if(txtSchemeCode.length >= 4)
	{
		var uri="ifms.htm?actionFlag=displaySchemeNameForCode&txtSchemeCode="+txtSchemeCode+"&cmbsubDdos="+cmbsubDdos;
		var url="txtSchemeCode="+txtSchemeCode;
		
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getDataStateChangedForPopUpSchemes(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	else
	{
		var cmbSchemeName = document.getElementById('cmbSchemeName');
		cmbSchemeName.options.length = 0;
		var optn = document.createElement("OPTION");
		optn.text = "-- Select --";
		optn.value = "-1";
		cmbSchemeName.options.add(optn);
	}
}

function getDataStateChangedForPopUpSchemes(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	var cmbSchemeName = document.getElementById('cmbSchemeName');
	cmbSchemeName.options.length = 0;
	var optn = document.createElement("OPTION");
	optn.text = "-- Select --";
	optn.value = "-1";
	cmbSchemeName.options.add(optn);

	var totalSchemes = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	var optnScheme ;

		var count=1;
		while(count<=(2*totalSchemes))
		{
			optnScheme = document.createElement("OPTION");
			optnScheme.text = XmlHiddenValues[0].childNodes[count].firstChild.nodeValue;
			optnScheme.title= XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
			optnScheme.value = XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
			document.getElementById("cmbSchemeName").options.add(optnScheme);
			count=count+2;
		}
}
