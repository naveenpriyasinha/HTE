var createOrUpdateFlag;

function deleteBillGroup()
{
	var billGroupId = document.getElementById("txtBillGroupNo").value;
	
	if(billGroupId != '')
	{
	
		var subBGOrNot = document.getElementById("hidSubBGOrNot").value;
		var empsInBgOrNot = document.getElementById("hidEmpsInBGOrNot").value;
		
		var vctPostInBGOrNot = document.getElementById("hidVctPostInBGOrNot").value;
		
		if(subBGOrNot == 0)
		{
			alert('You cannot delete the main bill group.');
			return;
		}
		
		if(empsInBgOrNot == 'true')
		{
			alert('You cannot delete the bill group since employees are attached to it.');
			return;
		}
		
		if(vctPostInBGOrNot == 'true')
		{
			alert('You cannot delete the bill group since vacant posts are attached to it.');
			return ;
		}
		
		
		
		var answer = confirm("Are you sure you want to delete this bill group?");
		if(answer)
		{
			var uri="ifms.htm?actionFlag=deleteSubBillGroup";
			var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:"billGroupId="+billGroupId,
				        onSuccess: function(myAjax) {
							getDataStateChangedForDeleteBillGroup(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...');} 
				          } );
		}
		else
		{
			return;
		}
	}
}

function getDataStateChangedForDeleteBillGroup(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblFlagBillGroupDeleteSuccessFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblFlagBillGroupDeleteSuccessFlag=='true') {
		
			alert('Bill Group deleted Successfully');
			self.location.reload(true);
	}
}

function displaySchemeNameForCode()
{
	if(document.getElementById("cmbSchemeName").value == -1)
	{
		return false;
	}
	
	document.getElementById("txtSchemeCode").value = document.getElementById("cmbSchemeName").value ;
	
	var uri="ifms.htm?actionFlag=getTotalSubBGsForScheme";
	var schemeCode = document.getElementById("txtSchemeCode").value;
	
	var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:"schemeCode="+schemeCode,
			        onSuccess: function(myAjax) {
						getDataStateChangedForDisplaySchemeNameForCode(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
}

function getDataStateChangedForDisplaySchemeNameForCode(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var countOfSubBG =  XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	countOfSubBG = Number(countOfSubBG) + 1 ;
	var schemeNameDesc = document.getElementById("cmbSchemeName").options[document.getElementById("cmbSchemeName").selectedIndex].text;
	document.getElementById("txtDescription").value = "Bill Group For Scheme " + schemeNameDesc + " " + countOfSubBG;
	//document.getElementById("txtDescription").readOnly = true;
}

function popUpBillGroupDtls(billGroupIdValue)
{
	//alert('in');
	var billGroupId = billGroupIdValue ;
	
	var uri="ifms.htm?actionFlag=popUpBillGroupDtls";
		
	var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:"billGroupId="+billGroupId,
			        onSuccess: function(myAjax) {
		//alert('suc3');
						getDataStateChangedForPopBillGroupDtls(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
		
}
	
function getDataStateChangedForPopBillGroupDtls(myAjax)
{
	//alert('sucss');
		XMLDoc = myAjax.responseXML.documentElement;
		var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				
				document.getElementById("btnSave").style.display = "" ;
				document.getElementById("btnCreateAndSave").style.display = 'none' ;
				
				document.getElementById("RadioPermenantTempBothP").checked = true ;
				document.getElementById("GroupNA").checked = false ;
				document.getElementById("GroupA").checked = false ;
				document.getElementById("GroupB").checked = false ;
				document.getElementById("GroupBnGz").checked = false ;
				document.getElementById("GroupC").checked = false ;
				document.getElementById("GroupD").checked = false ;

				document.getElementById("txtBillGroupNo").value = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
				document.getElementById("txtDescription").value = XmlHiddenValues[0].childNodes[1].firstChild.nodeValue;
				//document.getElementById("txtDescription").readOnly = true;
				//document.getElementById("cmbSchemeName").value = XmlHiddenValues[0].childNodes[2].text;
				document.getElementById("txtSchemeCode").value = XmlHiddenValues[0].childNodes[3].firstChild.nodeValue;
				
				
				//Added by saurabh
				document.getElementById("txtSubSchemeCode").value = XmlHiddenValues[0].childNodes[6].firstChild.nodeValue;
				if(document.getElementById("txtSubSchemeCode").value!="" || document.getElementById("txtSubSchemeCode").value!= null )
				{
					getSubSchemeDetails();
				}


				for(var lInt1=0;lInt1<document.getElementById("cmbSubSchemeName").length;lInt1++)
				{
					if(document.getElementById("cmbSubSchemeName").options[lInt1].value==XmlHiddenValues[0].childNodes[6].firstChild.nodeValue)
					{
						document.getElementById("cmbSubSchemeName").options[lInt1].selected = true;
					}
				}
				//Ended by saurabh
				
				var typeOfPost = XmlHiddenValues[0].childNodes[4].firstChild.nodeValue;
				
				if(typeOfPost=='P')
				{
					document.getElementById("RadioPermenantTempBothP").checked = true ;
				}

				if(typeOfPost=='T')
				{
					document.getElementById("RadioPermenantTempBothT").checked = true ;
				}

				if(typeOfPost=='S')
				{
					document.getElementById("RadioPermenantTempBothS").checked = true ;
				}
				
				if(typeOfPost=='B')
				{
					document.getElementById("RadioPermenantTempBothB").checked = true ;
				}
				
				for(var lInt=0;lInt<document.getElementById("cmbSchemeName").length;lInt++)
				{
					if(document.getElementById("cmbSchemeName").options[lInt].value==XmlHiddenValues[0].childNodes[3].firstChild.nodeValue)
					{
						document.getElementById("cmbSchemeName").options[lInt].selected = true;
					}
				}
	
				var classGroup;
				var totalClassGroups = XmlHiddenValues[0].childNodes[5].firstChild.nodeValue ;
				for(var i=0;i<totalClassGroups;i++)
				{
					classGroup = XmlHiddenValues[0].childNodes[8+i].firstChild.nodeValue ;
					if(classGroup == 'NA')
							{	 document.getElementById("GroupNA").checked = true ;	}
					if(classGroup == 'A')
							{	 document.getElementById("GroupA").checked = true ;	}
					if(classGroup == 'B')
							{	 document.getElementById("GroupB").checked = true ;	}
					if(classGroup == 'BnGz')
					{	 document.getElementById("GroupBnGz").checked = true ;	}
					if(classGroup == 'C')
							{	 document.getElementById("GroupC").checked = true ;	}
					if(classGroup == 'D')
							{	 document.getElementById("GroupD").checked = true ;	}
			
				}
				
				var nextCount = 8 + i ;
				var subBillGroupOrNot = XmlHiddenValues[0].childNodes[nextCount].firstChild.nodeValue;
				var empsInBGOrNot = XmlHiddenValues[0].childNodes[nextCount+1].firstChild.nodeValue;
				
				var vctPostInBGOrNot = XmlHiddenValues[0].childNodes[nextCount+3].firstChild.nodeValue;
				//alert('vctPostInBGOrNot: '+vctPostInBGOrNot);
				
				document.getElementById("hidSubBGOrNot").value = subBillGroupOrNot;
				//alert("subBillGroupOrNot----"+subBillGroupOrNot);
				document.getElementById("hidEmpsInBGOrNot").value = empsInBGOrNot;
				//alert("empsInBGOrNot----"+empsInBGOrNot);
				document.getElementById("hidVctPostInBGOrNot").value = vctPostInBGOrNot;
				//alert("vctPostInBGOrNot----"+vctPostInBGOrNot);
				
} 

function disable()
{
	if(document.frmDdoGroupBill.GroupNA.checked == true)
	{
			document.getElementById("GroupA").disabled = true ;
			document.getElementById("GroupB").disabled = true ;
			document.getElementById("GroupBnGz").disabled = true ;
			document.getElementById("GroupC").disabled = true ;
			document.getElementById("GroupD").disabled = true ;
			
			document.getElementById("GroupA").checked = false ;
			document.getElementById("GroupB").checked = false ;
			document.getElementById("GroupBnGz").checked = false ;
			document.getElementById("GroupC").checked = false ;
			document.getElementById("GroupD").checked = false ;
			
	}
	else{
		document.getElementById("GroupA").disabled = false ;
		document.getElementById("GroupB").disabled = false ;
		document.getElementById("GroupBnGz").disabled = false ;
		document.getElementById("GroupC").disabled = false ;
		document.getElementById("GroupD").disabled = false ;
	}
}
function disableNA()
{
	if(document.frmDdoGroupBill.GroupA.checked == true)
	{
		document.getElementById("GroupNA").disabled = true ;
		return ;
	}
	else
	{
		document.getElementById("GroupNA").disabled = false ;
	}
	
	
	if(document.frmDdoGroupBill.GroupB.checked == true)
	{
		document.getElementById("GroupNA").disabled = true ;
		return ;
	}
	else
	{
		document.getElementById("GroupNA").disabled = false ;
	}

	
	if(document.frmDdoGroupBill.GroupBnGz.checked == true)
	{
		document.getElementById("GroupNA").disabled = true ;
		return ;
	}
	else
	{
		document.getElementById("GroupNA").disabled = false ;
	}

	
	if(document.frmDdoGroupBill.GroupC.checked == true)
	{
		document.getElementById("GroupNA").disabled = true ;
		return ;
	}
	else
	{
		document.getElementById("GroupNA").disabled = false ;
	}

	
	if(document.frmDdoGroupBill.GroupD.checked == true)
	{
		document.getElementById("GroupNA").disabled = true ;
		return ;
	}
	else
	{
		document.getElementById("GroupNA").disabled = false ;
	}
}

function SaveDataAfterValidation(flag){
	
	showProgressbar();
	var saveOrUpdate = flag ;
	
	var chkBoxArr=document.getElementsByName('Group'); 
	var chkLength=chkBoxArr.length;
	var checkBoxEmptyFlag = true; 
	var checkBoxNASelected = false;
	
	for(var i=0;i<chkLength;i++){ 
		
		if(chkBoxArr[i].checked)
		{
			checkBoxEmptyFlag = false;
			break;
		}
		
	} 
	
	for(var i=0;i<chkLength;i++){ 
		
		if(chkBoxArr[i].checked && chkBoxArr[i].value.trim() == 'NA')
		{
			checkBoxNASelected = true;
			break;
		}
	}
	
	var radioBtnArr = document.getElementsByName('RadioPermenantTempBoth');
	var radioLength=radioBtnArr.length;
	var radioBtnEmptyFlag = true; 
	for(i=0;i<radioLength;i++){ 
		if(radioBtnArr[i].checked)
		{
			radioBtnEmptyFlag = false;
			break;
		}
	} 
	
	if(chkEmpty(document.getElementById('txtDescription'),'Description') &&
			chkEmpty(document.getElementById('cmbSchemeName'),'Scheme Name') &&
			chkEmpty(document.getElementById('cmbSchemeName'),'Scheme Name'))
		{
			if(checkBoxEmptyFlag == false && radioBtnEmptyFlag == false && checkBoxNASelected == false){
				SaveData(saveOrUpdate);
			}
			else
			{
				if(checkBoxEmptyFlag == true)
				{
					alert("Please select a Group");
					hideProgressbar();
				}
				else if (checkBoxNASelected == true)
				{
					alert("You cannot select Group NA");
					hideProgressbar();
				}
				else
				{
					alert("Please select type of post");
					hideProgressbar();
				}
			}
		}
	else
		{
			hideProgressbar();
		}
}

function SaveData(saveOrUpdate)
{
	
	createOrUpdateFlag = saveOrUpdate ;
	var url;
	var txtBillGroupNo = document.getElementById("txtBillGroupNo").value;
	document.getElementById("txtBillGroupNo").readOnly = true ;
	var txtDescription = document.getElementById("txtDescription").value;
	document.getElementById("txtDescription").readOnly = true ;
	var cmbSchemeName = document.getElementById("cmbSchemeName").value;
	var txtSchemeCode = document.getElementById("txtSchemeCode").value;
	var cmbSubSchemeName = document.getElementById("cmbSubSchemeName").value;
	var txtSubSchemeCode = document.getElementById("txtSubSchemeCode").value;
	var RadioPermenantTempBothArr = document.frmDdoGroupBill.RadioPermenantTempBoth ;
	var RadioPermenantTempBoth ;
	
	for (var i=0; i<RadioPermenantTempBothArr.length; i++)
	{
		  if (RadioPermenantTempBothArr[i].checked == true)
		  {
			  RadioPermenantTempBoth = RadioPermenantTempBothArr[i].value ;
		  }
	}
	var	lListGroup = document.getElementsByName("Group");
	var lListGroupLength = lListGroup.length;
	var groups="";
	
	for(i=0;i<lListGroupLength;i++)
	{
		if(document.forms[0].Group[i].checked)
		{
			groups = groups + lListGroup[i].value + ",";
		}
	}
	
	if(createOrUpdateFlag == 1)
	{
		uri = "ifms.htm?actionFlag=saveDdoGroupBill";
	}	
	if(createOrUpdateFlag == 2)
	{
		uri = "ifms.htm?actionFlag=createDdoGroupBill";
		var answer = confirm("Do you really want to create a bill group?");
		
	}
	
	url = "txtBillGroupNo="+txtBillGroupNo+"&groups="+groups+"&txtDescription="+txtDescription+"&cmbSchemeName="+cmbSchemeName+
	"&txtSchemeCode="+txtSchemeCode+"&txtSubSchemeCode="+txtSubSchemeCode+"&RadioPermenantTempBoth="+RadioPermenantTempBoth+"&createOrUpdateFlag="+createOrUpdateFlag ;
	//alert(url);
	//Code for Ajax Added
	
	if((createOrUpdateFlag == 2 && answer) || (createOrUpdateFlag == 1))
	{
	
		var myAjax = new Ajax.Request(uri,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters:url,
				        onSuccess: function(myAjax) {
							getDataStateChangedForSaveBillGroupDtls(myAjax);
						},
				        onFailure: function(){ alert('Something went wrong...')} 
				          } );
	}
	else
	{
		hideProgressbar();
	}
}

function getDataStateChangedForSaveBillGroupDtls(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblFlagBillGroupSuccessFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if (lblFlagBillGroupSuccessFlag=='true') {
		
		if(createOrUpdateFlag == 1)
		{
			alert('Bill Group has been Modified');
		}
		if(createOrUpdateFlag == 2)
		{
			alert('Bill Group has been Created');
		}
			hideProgressbar();
			self.location.href = 'ifms.htm?actionFlag=loadBillGroupCreation&elementId=700016';
	}

}

function checkUncheckAll(theElement){
    var theForm = theElement.form, z = 0;
	 for(z=0; z<theForm.length;z++)
	 {
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'SelectAll'){
		      if(theForm[z].name == 'dcpsEmpId')
		      {
			  		theForm[z].checked = theElement.checked;
		      }
		  }
    }
}		 


function ReturnPage()
{
	self.location.href="ifms.htm?actionFlag=validateLogin";
}


//Code from here Added by saurabh

function getSubSchemeDetails()
{
	var txtSchemeCode = document.getElementById("txtSchemeCode").value;
	
		var uri = 'ifms.htm?actionFlag=getSubSchemeDetails';
		var url = 'txtSchemeCode='+txtSchemeCode;
		
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

function getDataStateChangedForPopUpSchemes(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	
	var cmbSchemeName = document.getElementById('cmbSubSchemeName');
	cmbSchemeName.options.length = 0;
	var optn = document.createElement("OPTION");
	optn.text = "-- Select --";
	optn.value = "-1";
	cmbSchemeName.options.add(optn);

	var totalSchemes = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	var optnScheme ;
	var demandCode ;

	var count=1;
	while(count<=(3*totalSchemes))
	{
		optnScheme = document.createElement("OPTION");
		optnScheme.text = XmlHiddenValues[0].childNodes[count].firstChild.nodeValue;
		optnScheme.title= XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
		optnScheme.value = XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
		document.getElementById("cmbSubSchemeName").options.add(optnScheme);
		demandCode = XmlHiddenValues[0].childNodes[count+2].firstChild.nodeValue;
		count=count+3;
	}
	if(demandCode=="T-09" || demandCode=="T-10" ){
		document.getElementById('cmbSubSchemeName').disabled=false;
	}
	else
		document.getElementById('cmbSubSchemeName').disabled=true;
}

function chkEmpty1(ctrl,msg)
{
	if(document.getElementById('cmbSubSchemeName').disabled){
		return true;
	}
	else{
		var str = ctrl.value;
		if(str=="" || str == "-1")
		{
			alert(msg +" Cannot be Empty.");
			ctrl.focus();		
			return false;
		}		
		else
			return true;
	}
		
}
//Ended by saurabh

