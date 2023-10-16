//flag = 0;
//var removeArrValue ="";
//var removeArrText ="";
var gString;
var tempFlag;// for Validate that any branch is already mapped with any auditor or not :: yes - return false
function SelectBranches()
{
	
	if(validSelectBranches())
	{
		var cmbTargetBranchName = document.forms.auditorMappingInfo.cmbTargetBranchName;
		for(i = 0;i<cmbTargetBranchName.length;i++)
		{
			if(cmbTargetBranchName[i].selected)
			{
				var cmbTargetBranchNameValue = cmbTargetBranchName[i].value;
				var cmbTargetBranchNameText = cmbTargetBranchName[i].text;
				var newOption = document.createElement('<option value="'+cmbTargetBranchNameValue+'">');
				document.all.cmbDisplayBranchName.options.add(newOption);
				newOption.innerText = cmbTargetBranchNameText;
			}
		}
		for(i=cmbTargetBranchName.length-1;i>=0;i--)
		{
			if(cmbTargetBranchName.options[i].selected)
			cmbTargetBranchName.remove(i);
		}
	
		//flag=0;
		//removeArrValue ="";
		//removeArrText =""; 
	}
}
function validSelectBranches()
{
	//Validate that any branch is selected or not
	if(document.getElementById("cmbTargetBranchName").value == ""  || document.getElementById("cmbTargetBranchName").value == -1 )
	{
		alert('Please select valid branch to map with auditor.');
		return false;
	}
	
	//Validate that any branch is already mapped with any auditor or not :: yes - return false
	vaildateBnkBrnchWithAuditor();
	if(document.getElementById("hidTempFlag").value != 'true')
	{
		return false;
	}
	return true;
}
function vaildateBnkBrnchWithAuditor()
{
	var selectedBranches ="";
	var AuditorPostId = document.getElementById("cmbAuditorName").value;
	var BankCode = document.getElementById("cmbBankName").value;
	var cmbTargetBranchName = document.forms.auditorMappingInfo.cmbTargetBranchName;
	for(i = 0;i<cmbTargetBranchName.length;i++)
	{
		if(cmbTargetBranchName[i].selected)
		{
			selectedBranches = selectedBranches + cmbTargetBranchName[i].value + "~";
		}
	}
	var url = "ifms.htm?actionFlag=vaildateBnkBrnchWithAuditor&selectedBranches="+selectedBranches+"&AuditorPostId="+AuditorPostId+"&BankCode="+BankCode; 
	var myAjax = new Ajax.Request(url,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {vaildateBnkBrnchWithAuditorCaseStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});

}
function vaildateBnkBrnchWithAuditorCaseStateChanged(myAjax)
{

	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var auditorNameList = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	var branchNameList = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue;
	var tempFlagVal = XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue;
	var bankNameList = XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue;
	
	
	
	
	if(auditorNameList != "null")  // tempFlagVal = false
	{
		var tempAuditorNameArr = auditorNameList.substr(1,auditorNameList.length-2);
		var arrTotalAuditorName = tempAuditorNameArr.split("~");
		
		var tempBranchNameArr = branchNameList.substr(1,branchNameList.length-2);
		var arrTotalBranchName = tempBranchNameArr.split("~");
		
		var tempBankNameArr = bankNameList.substr(1,bankNameList.length-2);
		var arrTotalBankName = tempBankNameArr.split("~");
		
		var tempString ="";
		
		for(var j=0;j<(arrTotalAuditorName.length-1);j++)
		{
			tempString=tempString+'\nAuditor Name:-'+arrTotalAuditorName[j]+' Bank Name:-'+arrTotalBankName[j]+' Branch Name:-'+arrTotalBranchName[j]+'\n';
		}
		alert('Following bank-branches are already mapped with following auditors:-\n'+tempString);
		/*var overlay=document.createElement('div');
		overlay.setAttribute('style',"overflow:scroll;height:100px;width:100px;");
		overlay.appendChild(document.createTextNode('hiiiiiiiiiiiiiiiiiiiiiiiiiiii'));
		document.body.appendChild(overlay);*/
	/*	var cw = window.open("","","status=no,toolbar=no,menubar=no,location=no,scrollbars=yes,resizable=yes,width=750");
		cw.document.write('Following bank-branches are already mapped with following auditors.Check below table:-');
		cw.document.write('<html><body><table border=\'1\'><tr><th>Auditor Name</th><th>Bank Name</th><th>Branch Name</th></tr>');
		
		for(var j=0;j<arrTotalAuditorName.length;j++)
		{
			cw.document.write('<tr><td>'+arrTotalAuditorName[j]+'</td><td>'+arrTotalBankName[j]+'</td><td>'+arrTotalBranchName[j]+'</td></tr>');
		}
		cw.document.write('</table></body></html>');
		*/
		if(confirm("Do you want to continue?"))
		{
			document.getElementById("hidTempFlag").value = 'true';
		}
		else
		{
			document.getElementById("hidTempFlag").value = tempFlagVal ;
		}
	}
	else // tempFlagVal = true
	{
		document.getElementById("hidTempFlag").value = tempFlagVal;
	}
	
}

function DeSelectBranches()
{
	if(validDeSelectBranches() == true)
	{

		var cmbDisplayBranchName = document.forms.auditorMappingInfo.cmbDisplayBranchName;
		var removeArrValue ="";
		var removeArrText ="";
		for(i = 0;i<cmbDisplayBranchName.length;i++)
		{
			
			if(cmbDisplayBranchName[i].selected)
			{
				
				var cmbDisplayBranchNameValue = cmbDisplayBranchName[i].value;
				var cmbDisplayBranchNameText = cmbDisplayBranchName[i].text;
				removeArrValue = removeArrValue + cmbDisplayBranchName[i].value + "~"; 
				removeArrText = removeArrText + cmbDisplayBranchName[i].text + "~";
			}
		}
		/*if(confirm("Do you want to remove mapping between selected branches and current auditor?"))
		{
			
			flag=1;
			saveMappingData();
		}
		else
		{
			return;
		}*/
		
	//	if(flag==1)
		{
			var removeValue = new Array();
			removeValue = removeArrValue.split("~");
			var removeText = new Array();
			removeText = removeArrText.split("~");
			for(j=0;j<removeValue.length-1;j++)
			{
				var newOption = document.createElement('<option value="'+removeValue[j]+'">');
				document.all.cmbTargetBranchName.options.add(newOption);
				newOption.innerText = removeText[j];
			}
			for(i=cmbDisplayBranchName.length-1;i>=0;i--)
			{
				if(cmbDisplayBranchName.options[i].selected)
					cmbDisplayBranchName.remove(i);
			}
		}
	//	flag=0;
	//	removeArrValue ="";
	//	removeArrText ="";
	}
}
function validDeSelectBranches()
{
	
	if(document.getElementById("cmbDisplayBranchName").value == ""  || document.getElementById("cmbDisplayBranchName").value == -1 )
	{
		alert('Please select valid branch for removal of mapping.');
		return false;
	}
	return true;
}
function SelectAllBranches()
{
	var cmbTargetBranchNameObj  = document.getElementById("cmbTargetBranchName");
	var cmbDisplayBranchNameObj = document.getElementById("cmbDisplayBranchName");
	var tempLength = cmbTargetBranchNameObj.length;
	for(i = 1;i<tempLength;i++)
	{
		var newOption = document.createElement('<option value="'+cmbTargetBranchNameObj[i].value+'">');
	    document.all.cmbDisplayBranchName.options.add(newOption);
	    newOption.innerText = document.getElementById("cmbTargetBranchName").options[i].text;
	}
	//flag=0;
	//removeArrValue ="";
	//removeArrText ="";
	for(j = tempLength -1;j>=0;j--)
	{
		if(j == 0)
		{
			return;
		}
		document.all.cmbTargetBranchName.options.remove(j);
	}
	
	
}
function DeSelectAllBranches()
{
	var cmbDisplayBranchNameObj  = document.getElementById("cmbDisplayBranchName");
	var cmbTargetBranchNameObj = document.getElementById("cmbTargetBranchName");
	var tempLength = cmbDisplayBranchNameObj.length;
	var removeArrValue ="";
	var removeArrText ="";
	for(i = 1;i<tempLength;i++)
	{
		removeArrValue = removeArrValue + cmbDisplayBranchNameObj[i].value + "~"; 
		removeArrText = removeArrText + cmbDisplayBranchNameObj[i].text + "~"; 
	}
		
	/*if(confirm("Do you want to remove mapping between selected branches and current auditor?"))
	{
		flag=1;
		saveMappingData();
		
	}
	else
	{
		return;
	}*/
	
	
	

	//if(flag==1)
	{
		
		var removeValue = new Array();
		removeValue = removeArrValue.split("~");
		var removeText = new Array();
		removeText = removeArrText.split("~");
		
		for(j=0;j<removeValue.length-1;j++)
		{
			
			var newOption = document.createElement('<option value="'+removeValue[j]+'">');
			document.all.cmbTargetBranchName.options.add(newOption);
			newOption.innerText = removeText[j];
		}
		for(j = tempLength -1;j>=0;j--)
		{
			if(j == 0)
			{
				return;
			}
			document.all.cmbDisplayBranchName.options.remove(j);
		}
		
	}
	
	//flag=0;
	//removeArrValue ="";
	//removeArrText ="";
	
	
}

function removeMappingData()
{
	var cmbDisplayBranchName = document.forms.auditorMappingInfo.cmbDisplayBranchNameList;
	var CmbDisplayBranchName ="";
	var uri;
	var url;
	var myAjax;
	
	if(cmbDisplayBranchName.length > 0)
	{
		for(i = 0;i<cmbDisplayBranchName.length;i++)
		{
			if(cmbDisplayBranchName[i].selected)
			{
				CmbDisplayBranchName = CmbDisplayBranchName + cmbDisplayBranchName[i].value + "~";
			}
		}
		
		uri = 'ifms.htm?actionFlag=saveAuditorBankBrnchMapping&CmbDisplayBranchName='+CmbDisplayBranchName+'&action=Remove';//+"&removeArrValue="+removeArrValue;
		url = runForm(0);
		url = uri + url;
		myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {mappingDataCaseStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
	}
	else
	{
		alert('Please add any branch before saving it.');
	}
}
function saveMappingData()
{
	var cmbDisplayBranchName = document.forms.auditorMappingInfo.cmbDisplayBranchName;
	var CmbDisplayBranchName ="";
	var uri;
	var url;
	var myAjax;
	
	if(cmbDisplayBranchName.length > 1)
	{
		for(i = 1;i<cmbDisplayBranchName.length;i++)
		{
			CmbDisplayBranchName = CmbDisplayBranchName + cmbDisplayBranchName[i].value + "~";
		}
		
		uri = 'ifms.htm?actionFlag=saveAuditorBankBrnchMapping&CmbDisplayBranchName='+CmbDisplayBranchName+'&action=Add';//+"&removeArrValue="+removeArrValue;
		url = runForm(0);
		url = uri + url;
		myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {mappingDataCaseStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
	}
	else
	{
		alert('Please add any branch before saving it.');
	}
	
}



function mappingDataCaseStateChanged(myAjax) 
{ 
  
	   var XMLDoc=myAjax.responseXML.documentElement;
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	   var hidString = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	 /*  var removeValue = new Array();
		removeValue = removeArrValue.split("~");
		var removeText = new Array();
		removeText = removeArrText.split("~");
		*/
			if(hidString == 'Add')
			{
				/*if(flag == 1 && (removeArrValue !="" || removeArrText!="" || removeArrValue !=null || removeArrText!=null))
				{
					alert("Mapping has been removed successfully.");
					document.getElementById('cmbDisplayBranchName').options.length = 0;
					var optn = document.createElement("OPTION");
					optn.value = "-1";
					optn.text = "-- Select --";
					document.getElementById('cmbDisplayBranchName').options.add(optn);
				}*/
				//if(flag == 0 && (removeArrValue =="" || removeArrText=="" || removeArrValue ==null || removeArrText==null))
				//if(removeArrValue =="" || removeArrText=="" || removeArrValue ==null || removeArrText==null)
				{
					alert("Auditor has been mapped successfully.");
					for(var i=1;i<document.forms.auditorMappingInfo.cmbDisplayBranchName.length;i++)
					{
						var newOption = document.createElement('<option value="'+document.getElementById('cmbDisplayBranchName').options[i].value+'">');
						document.all.cmbDisplayBranchNameList.options.add(newOption);
						newOption.innerText = document.getElementById('cmbDisplayBranchName').options[i].text;
					}
					document.getElementById('cmbDisplayBranchName').options.length = 0;
					var optn = document.createElement("OPTION");
					optn.value = "-1";
					optn.text = "-- Select --";
					document.getElementById('cmbDisplayBranchName').options.add(optn);
				}
			}
			if(hidString == 'Remove')
			{
				alert("Mapping has been removed successfully.");
				self.location.reload();
			}
}

function viewAuditorMapping()
{
	
	if(document.getElementById("cmbAuditorName").value == -1 || document.getElementById("cmbAuditorName").value == "" )
	{
		alert('Please selelct any auditor to view the mapping.');
		return;
	}
	var AuditorPostId = document.getElementById("cmbAuditorName").value;
	var BankCode = document.getElementById("cmbBankName").value;
	var url = "ifms.htm?actionFlag=loadAuditorBankBrnchMapping";
	var params = "AuditorPostId="+AuditorPostId+"&BankCode="+BankCode;
	var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: params,
		        onSuccess: function(myAjax) {
							populateBankBranchMapping(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
	//window_new_update(url);
	
}
function populateBankBranchMapping(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	var table=document.getElementById("tblBankBrnchMpgDtls");
	var lBankName = "";
	var lBranchName = "";
	/*
	 * Deleting all rows before populating
	 */
	for(var i = table.rows.length; i > 0;i--)
    {
		table.deleteRow(i -1);
    }  
	if(XMLDoc != null)
	{
		var bnkBranchMpgDtls = XMLDoc.getElementsByTagName('BANKBRANCHMAPPING');
		if(bnkBranchMpgDtls.length > 0)
		{
			for(var i = 0;i <bnkBranchMpgDtls.length ;i++)
			{
				lBankName = bnkBranchMpgDtls[i].childNodes[0].childNodes[0].nodeValue;
				lBranchName = bnkBranchMpgDtls[i].childNodes[1].childNodes[0].nodeValue;
				var rowCount=table.rows.length;
				var newRow = table.insertRow(rowCount); 
			 	var Cell1 = newRow.insertCell(0);
			   	Cell1.className = "tds";
			   	Cell1.width = "50%";
			   	var Cell2 = newRow.insertCell(1);
			   	Cell2.className = "tds";
			   	Cell2.width = "50%";
			 	Cell1.innerHTML = lBankName;
				Cell2.innerHTML = lBranchName;
			}
		}
	}
}

function window_new_update(url)
{
	/*
	 * var newWindow = null;
   	var height = screen.height - 300;
   	var width = screen.width - 500;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=180,left=250";
	 */
	
	
	var newWindow = null;
   	var height = screen.height - 400;
   	var width = screen.width - 500;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=100,left=200";
   
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}


function getMappingDtls()
{
	
	if(document.getElementById("cmbAuditorName").value == -1 || document.getElementById("cmbAuditorName").value == "" )
	{
		alert('Please selelct any auditor.');
		document.getElementById("cmbBankName").value = -1;
		document.getElementById("cmbAuditorName").value = -1;
		
		if (document.getElementById('cmbDisplayBranchNameList').options.length > 0)
		{
			var cmbDisplayBranchNameList = document.getElementById('cmbDisplayBranchNameList');
			cmbDisplayBranchNameList.options.length = 0;
		}
			
		if (document.getElementById('cmbDisplayBranchName').options.length > 0)
		{
			
			var cmbDisplayBranchName = document.getElementById('cmbDisplayBranchName');
			cmbDisplayBranchName.options.length = 0;
			var optn = document.createElement("OPTION");
			optn.value = "-1";
			optn.text = "-- Select --";
			cmbDisplayBranchName.options.add(optn);
		}
		
		if (document.getElementById('cmbDisplayBranchName').options.length > 0)
		{
			
			var cmbTargetBranchName = document.getElementById('cmbTargetBranchName');
			cmbTargetBranchName.options.length = 0;
			var optn = document.createElement("OPTION");
			optn.value = "-1";
			optn.text = "-- Select --";
			cmbTargetBranchName.options.add(optn);
		}
		return;
	}
	
	/*if(document.getElementById("cmbBankName").value == -1 || document.getElementById("cmbBankName").value == "")
	{
		alert('Please selelct any bank.');
		document.getElementById("cmbAuditorName").value = -1;
		document.getElementById("cmbBankName").value = -1;
		return;
	}*/
	if(document.getElementById("cmbBankName").value != -1 || document.getElementById("cmbBankName").value != "")
	{
		var AuditorPostId = document.getElementById("cmbAuditorName").value;
		var BankCode = document.getElementById("cmbBankName").value;
		var url = "ifms.htm?actionFlag=getMappingDtls&AuditorPostId="+AuditorPostId+"&BankCode="+BankCode; 
		var myAjax = new Ajax.Request(url,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {MappingDtlsCaseStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
	}
	else
	{
		var cmbDisplayBranchNameList = document.getElementById('cmbDisplayBranchNameList');
		cmbDisplayBranchNameList.options.length = 0;
		
		var cmbDisplayBranchName = document.getElementById('cmbDisplayBranchName');
		cmbDisplayBranchName.options.length = 0;
		var optn = document.createElement("OPTION");
		optn.value = "-1";
		optn.text = "-- Select --";
		cmbDisplayBranchName.options.add(optn);
		
		var cmbTargetBranchName = document.getElementById('cmbTargetBranchName');
		cmbTargetBranchName.options.length = 0;
		var option = document.createElement("OPTION");
		option.value = "-1";
		option.text = "-- Select --";
		cmbTargetBranchName.options.add(option);
		
	}
	
}

function MappingDtlsCaseStateChanged(myAjax) 
{
	var cmbDisplayBranchName = document.getElementById('cmbDisplayBranchName');
	cmbDisplayBranchName.options.length = 0;
	var optn = document.createElement("OPTION");
	optn.value = "-1";
	optn.text = "-- Select --";
	cmbDisplayBranchName.options.add(optn);
	
	var cmbDisplayBranchNameList = document.getElementById('cmbDisplayBranchNameList');
	cmbDisplayBranchNameList.options.length = 0;

	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var totalRecords = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
	
	var count=1;
	while(count<=(2*totalRecords))
	{
		var optn = document.createElement("OPTION");
		optn.value = XmlHiddenValues[0].childNodes[count].childNodes[0].nodeValue;
		optn.text = XmlHiddenValues[0].childNodes[count+1].childNodes[0].nodeValue;
		document.getElementById("cmbDisplayBranchNameList").options.add(optn);
		count=count+2;
	}
		
	var cmbTargetBranchName = document.forms.auditorMappingInfo.cmbTargetBranchName;
		
	for(i=cmbTargetBranchName.length-1;i>=0;i--)
	{
		for(j=0;j<cmbDisplayBranchNameList.length;j++)
		{
			if(cmbTargetBranchName.options[i] != null)
			{
				if(cmbTargetBranchName.options[i].value == cmbDisplayBranchNameList.options[j].value)
				{
					cmbTargetBranchName.remove(cmbTargetBranchName.options[i].index);
				}
			}
		}
	}
		
	
}

function viewRemarks()
{
	var url = "ifms.htm?actionFlag=getRemarksOfBill&BillNo="+991005527359;
	window_new_update1(url);
}
function window_new_update1(url)
{
	
	var newWindow = null;
   	var height = screen.height - 300;
   	var width = screen.width - 500;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=180,left=250";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
function getBrnchesOfBank()
{
	var url ="ifms.htm?actionFlag=getBranchListFromBankCode&bankCode="+bankCode; 
	showProgressbar('Please Wait<br>Your request is in progress...');
	document.auditorMappingInfo.action = url ;
	enableAjaxSubmit(true);
	document.auditorMappingInfo.submit();
}
function saveMainCategoryConfig()
{
	var uri;
	
	if(document.getElementById("txtMainCtgryDesc").value == "" || document.getElementById("txtPensionSchemeCode").value == "" || document.getElementById("txtCvpSchemeCode").value == "" || document.getElementById("txtDcrgSchemeCode").value == "")
	{
		alert('Please fill up all details.');
		return;
	}
	if(document.getElementById("hidString").value == 'Update')
	{
		uri = 'ifms.htm?actionFlag=saveMainCategoryConfig&hidString='+document.getElementById("hidString").value+'&hidMainCatId='+document.getElementById("hidMainCatId").value;
	}
	else
	{
		uri = 'ifms.htm?actionFlag=saveMainCategoryConfig&hidString=Add';
	}
	
	var url =  runForm(0);
	url = uri + url;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {saveMainCategoryConfigStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');} 
			});
	
	

}
function saveMainCategoryConfigStateChanged(myAjax) 
{ 
  
	   var XMLDoc=myAjax.responseXML.documentElement;
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	   var string = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		
		if(string == 'Add')
		{
			alert("Main Category Configuration  has been saved successfully.");
			self.location.reload();
			
		}
		if(string == 'Update')
		{
			alert("Main Category Configuration  has been updated successfully.");
			self.location.reload();
		}
}
function saveHeadCodeConfig()
{
	var uri;
	
	if(document.getElementById("txtPnsnrCtgry").value == "" || document.getElementById("txtDescription").value == "" || document.getElementById("txtPensionSchemeCode").value == "" || document.getElementById("txtCvpSchemeCode").value == "" || document.getElementById("txtDcrgSchemeCode").value == "")
	{
		alert('All details are compulsory to fill.Please fill up all details.');
		return;
	}
	if(document.getElementById("gString").value == 'Update')
	{
		uri = 'ifms.htm?actionFlag=saveHeadCodeConfig&gString='+document.getElementById("gString").value+'&headCode='+document.getElementById("txtPnsnrCtgry").value;
	}
	else
	{
		uri = 'ifms.htm?actionFlag=saveHeadCodeConfig&cmbMainCategoryId='+document.getElementById("cmbMainCategory").value+
		'&cmbMainCategoryDesc='+document.getElementById("cmbMainCategory").options[document.getElementById("cmbMainCategory").selectedIndex].text;
	}
	var url =  runForm(0);
	url = uri + url;
	var myAjax = new Ajax.Request(uri,
			{
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {saveHeadCodeConfigStateChanged(myAjax);},
		        onFailure: function(){ alert('Something went wrong...');} 
			});
	
	
}



function saveHeadCodeConfigStateChanged(myAjax) 
{ 
  
	   var XMLDoc=myAjax.responseXML.documentElement;
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	   var string = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue;
		
		if(string == 'Add')
		{
			alert("Head Code Configuration  has been saved successfully.");
			self.location.reload();
			
		}
		if(string == 'Update')
		{
			alert("Head Code Configuration  has been updated successfully.");
			self.location.reload();
			
		}
		if(string == 'Delete')
		{
			alert("Head Code Configuration  has been deleted successfully.");
			self.location.reload();
			
		}
	
}
function moveMainCategoryConfig(count,hidString)
{
	
	document.getElementById("txtMainCtgryDesc").value  = document.getElementById("lblDescrptn"+count).innerHTML;
	
	document.getElementById("txtPensionSchemeCode").value  = document.getElementById("lblPnsnSchemeCode"+count).innerHTML;
	
	document.getElementById("txtCvpSchemeCode").value  = document.getElementById("lblCvpSchemeCode"+count).innerHTML;
	
	document.getElementById("txtDcrgSchemeCode").value  = document.getElementById("lblDcrgSchemeCode"+count).innerHTML;
	 
	document.getElementById("hidString").value = hidString;
	
	document.getElementById("hidMainCatId").value = document.getElementById("hidMstPensionMainCategoryPk"+count).value;
	
	
	//document.getElementById("txtPnsnrCtgry").readOnly = "readOnly";
	
}
function moveHeadCodeConfig(count,gString)
{
	document.getElementById("txtPnsnrCtgry").value = document.getElementById("lblPnsnrCtgry"+count).innerHTML;
	
	//document.getElementById("hidHeadCode").value= document.getElementById("lblHeadCode"+count).innerHTML;
	
	document.getElementById("txtDescription").value  = document.getElementById("lblDescrptn"+count).innerHTML;
	
	document.getElementById("txtPensionSchemeCode").value  = document.getElementById("lblPnsnSchemeCode"+count).innerHTML;
	
	document.getElementById("txtCvpSchemeCode").value  = document.getElementById("lblCvpSchemeCode"+count).innerHTML;
	
	document.getElementById("txtDcrgSchemeCode").value  = document.getElementById("lblDcrgSchemeCode"+count).innerHTML;
	 
	document.getElementById("gString").value = gString;
	
	document.getElementById("txtPnsnrCtgry").readOnly = "readOnly";
	
}
function deleteMainCategoryConfig()
{

	var totalSelectedMainCategory= document.getElementById("totalCount").value;
	var MainCatId="";
	var tempFlag = 0;
	var hidMainCtgryTotalArr = document.getElementById("hidMainCategoryIdList").value;
	var uri;
	var url;
	var myAjax;
	var tempMainCtgryArr = hidMainCtgryTotalArr.substr(1,hidMainCtgryTotalArr.length-2);
	var arrTotalMainCtgry = tempMainCtgryArr.split(",");
	for(var i=1;i<totalSelectedMainCategory;i++)
	{
		if(document.getElementById("checkbox"+i).checked == true)
		{
			tempFlag = 1;
			MainCatId= MainCatId + document.getElementById("checkbox"+i).value + "~";
			for(var j=0;j<arrTotalMainCtgry.length;j++)
			{
				if(Number(document.getElementById("checkbox"+i).value) == Number(arrTotalMainCtgry[j]))
				{
					alert('You cannot delete ' + document.getElementById("lblDescrptn"+i).innerHTML  + '.Because It is related with some headcode/subcategory.');
					return;
				}
			}
		}
	}
	if(tempFlag == 1)
	{
		uri = 'ifms.htm?actionFlag=saveMainCategoryConfig&MainCatId='+MainCatId;
		url = runForm(0); 
		url = uri + url; 
		myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {deleteMainCategoryConfigStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
	}
	else
	{
		alert('Please select any main category to delete.');
	}
}
function deleteMainCategoryConfigStateChanged(myAjax) 
{ 
	alert("Main Category Configuration  has been deleted successfully.");
	self.location.reload();
}
function deleteHeadCodeConfig()
{
	var totalSelectedHeadCode= document.getElementById("totalCount").value;
	var PnsnHeadCodeId="";
	var tempFlag = 0;
	var HeadCode="";
	var hidHeadCodeTotalArr = document.getElementById("hidTotalHeadCode").value;
	var hidHeadCodeTotalNo = document.getElementById("hidHeadCodeTotalNo").value;
	var uri;
	var url;
	var myAjax;
	
	var tempHeadCodeArr = hidHeadCodeTotalArr.substr(1,hidHeadCodeTotalArr.length-2);
	var arrTotalHeadCode = tempHeadCodeArr.split(",");
	for(var i=1;i<totalSelectedHeadCode;i++)
	{
		if(document.getElementById("checkbox"+i).checked == true)
		{
			tempFlag = 1;
			PnsnHeadCodeId= PnsnHeadCodeId + document.getElementById("checkbox"+i).value + "~";
			HeadCode = document.getElementById("lblHeadCode"+i).innerHTML;
			for(var j=0;j<arrTotalHeadCode.length;j++)
			{
				if(Number(HeadCode) == Number(arrTotalHeadCode[j]))
				{
					alert('You cannot delete ' + document.getElementById("lblHeadCode"+i).innerHTML  + '.Because It is related with some pensioncase.');
					return;
				}
			}
		}
	}

	if(tempFlag == 1)
	{
		uri = 'ifms.htm?actionFlag=saveHeadCodeConfig&PnsnHeadCodeId='+PnsnHeadCodeId;
		url = runForm(0); 
		url = uri + url; 
		myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {deleteHeadCodeConfigStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
	}
	else
	{
		alert('Please select any head code to delete.');
	}
	
}



function deleteHeadCodeConfigStateChanged(myAjax) 
{ 
	alert("Head code has been deleted successfully.");
	self.location.reload();
}


function getBnkBrnchsFromBnkCode()
{
	if(document.getElementById("cmbAuditorName").value == -1 || document.getElementById("cmbAuditorName").value == "" )
	{
		alert('Please selelct any auditor.');
		document.getElementById("cmbBankName").value = -1;
		return;
	}
	/*
	if(document.getElementById("cmbBankName").value == -1 || document.getElementById("cmbBankName").value == "")
	{
		alert('Please selelct any bank.');
		document.getElementById("cmbAuditorName").value = -1;
		document.getElementById("cmbBankName").value = -1;
		return;
	}*/
	
	if(document.getElementById("cmbBankName").value != -1 || document.getElementById("cmbBankName").value != "")
	{
		var cmbBankName  = document.getElementById("cmbBankName").value;
		var url="ifms.htm?actionFlag=getBranchListFromBankCode&bankCode="+cmbBankName;
		var myAjax = new Ajax.Request(url,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {getBnkBrnchsFromBnkCodeStateChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
	}
	else
	{
		var cmbDisplayBranchNameList = document.getElementById('cmbDisplayBranchNameList');
		cmbDisplayBranchNameList.options.length = 0;
		
		
		var cmbDisplayBranchName = document.getElementById('cmbDisplayBranchName');
		var optn = document.createElement("OPTION");
		optn.value = "-1";
		optn.text = "-- Select --";
		cmbDisplayBranchName.options.length = 0;
		cmbDisplayBranchName.options.add(optn);
		
		var cmbTargetBranchName = document.getElementById('cmbTargetBranchName');
		cmbTargetBranchName.options.length = 0;
		var option = document.createElement("OPTION");
		option.value = "-1";
		option.text = "-- Select --";
		cmbTargetBranchName.options.add(option);
		
	}
	
}

function getBnkBrnchsFromBnkCodeStateChanged(myAjax) 
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('item');
	document.getElementById('cmbTargetBranchName').options.length = 0;
			
	for(var j = 0;j<XmlHiddenValues.length;j++)
	{
		var branchName =  XmlHiddenValues[j].childNodes[0].childNodes[0].nodeValue;
		var branchCode =  XmlHiddenValues[j].childNodes[1].childNodes[0].nodeValue;
		var optn = document.createElement("OPTION");
		optn.value = branchCode;
		optn.text = branchName;
		document.getElementById("cmbTargetBranchName").options.add(optn);
	}
			
}

function saveChangeStmntCtgryConfig()
{
	var chkChangeStmntCtgryConfig = document.getElementsByName("chkChangeStmntCtgryConfig");
	var ctgryId="";
	var flag=0;
	var uri;
	var totalCategories = 15;
	for(var i=1;i<=totalCategories;i++)
	{
		if(i == 5 || i == 13)
		{
			continue;
		}
		if(document.getElementById("chkChangeStmntCtgryConfig"+i).checked == true)
		{
			ctgryId = ctgryId + document.getElementById("chkChangeStmntCtgryConfig"+i).value + "~";
			flag=1;
		}
	}
	if(flag == 1)
	{
		uri = "ifms.htm?actionFlag=saveChangeStmntCtgryConfig&ctgryId="+ctgryId;
		myAjax = new Ajax.Request(uri,
				{
			        method: 'post',
			        asynchronous: false,
			        parameters:uri,
			        onSuccess: function(myAjax) {changeStmntCtgryConfigChanged(myAjax);},
			        onFailure: function(){ alert('Something went wrong...');} 
				});
	}
	else
	{
		alert('Please select any category.');
		return;
	}
}
function changeStmntCtgryConfigChanged(myAjax)
{

	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var string=XmlHiddenValues[0].childNodes[0].text;
	if(string == 'Add')
	{
		alert("Change statement category cofiguration have been saved successfully.");
		self.location.reload(true);
	}
}
function ppoExistsOrNot()
{
	var newPpoNo=document.getElementById("txtNewPPONo").value;
	if(document.getElementById("txtNewPPONo").value != "")
	{
		var uri;
		uri = 'ifms.htm?actionFlag=validationOfPPONo&PpoNo='+newPpoNo;
	}
	var myAjax = new Ajax.Request(uri,
	{
        method: 'post',
        asynchronous: false,
        parameters:uri,
        onSuccess: function(myAjax) {checkPPONoCaseStateChanged(myAjax);},
        onFailure: function(){ alert('Something went wrong...');} 
	});
}
function checkPPONoCaseStateChanged(myAjax)
{ 
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'Y')
    {
		alert(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue+ " is already exist in "+XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue + ".Please re-enter ppo no.");
   	 	document.getElementById("txtNewPPONo").value="";
   	 	document.getElementById("txtNewPPONo").focus();
    }
}

function getPPODetails()
{
	var ppoNo=document.getElementById("txtOldPPONo").value;
	if(document.getElementById("txtOldPPONo").value != "")
	{
		var uri;
		uri = 'ifms.htm?actionFlag=getPPODetails&ppoNo='+ppoNo;
	}
	var myAjax = new Ajax.Request(uri,
	{
        method: 'post',
        asynchronous: false,
        parameters:uri,
        onSuccess: function(myAjax) {getPPODetailsCaseStateChanged(myAjax);},
        onFailure: function(){ alert('Something went wrong...');} 
	});
}
function getPPODetailsCaseStateChanged(myAjax)
{ 
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOCPPODETAILS');
	
	if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue == 'EmptyList')
	{
		alert('No Such PPO Exists.');
		document.getElementById("txtOldPPONo").value="";
		document.getElementById("txtPnsnrName").value="";
		document.getElementById("txtAccNo").value="";
		document.getElementById("txtBankName").value = "";
		document.getElementById("txtBranchName").value="";
		return;
	}
	document.getElementById("txtPnsnrName").value = XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue; //pnsnrName
	document.getElementById("hidPnsnrCode").value = XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue; //pnsnrCode
	document.getElementById("txtBankName").value = XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue; //bankName
	document.getElementById("txtBranchName").value = XmlHiddenValues[0].childNodes[3].childNodes[0].nodeValue; //branchName
	document.getElementById("txtAccNo").value = XmlHiddenValues[0].childNodes[4].childNodes[0].nodeValue; //accNo
}
function savePPONo()
{
	if(document.getElementById("txtNewPPONo").value == "" || document.getElementById("txtOldPPONo").value == "")
	{
		alert('Please enter ppo no.');
		return;
	}
	var pnsnrCode = document.getElementById("hidPnsnrCode").value;
	var oldPpoNo=document.getElementById("txtOldPPONo").value;
	var newPpoNo=document.getElementById("txtNewPPONo").value;
	var uri = 'ifms.htm?actionFlag=savePPONo&oldPpoNo='+oldPpoNo+'&newPpoNo='+newPpoNo+'&pnsnrCode='+pnsnrCode;
	
	var myAjax = new Ajax.Request(uri,
	{
        method: 'post',
        asynchronous: false,
        parameters:uri,
        onSuccess: function(myAjax) {savePPONoCaseStateChanged(myAjax);},
        onFailure: function(){ alert('Something went wrong...');} 
	});
	
}
function savePPONoCaseStateChanged(myAjax)
{
	var XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var string=XmlHiddenValues[0].childNodes[0].text;
	if(string == 'Add')
	{
		alert("PPO no. has been changed successfully.");		
		self.location.reload(true);
	}
	
}
function showPPONoHistory()
{
	if(document.getElementById("txtOldPPONo").value == "")
	{
		alert('Please enter PPO No. to proceed ahead.');
		return;
	}
	 
	var PnsnrCode = document.getElementById("hidPnsnrCode").value;
	url = "ifms.htm?actionFlag=showPPONoHistory&PnsnrCode="+PnsnrCode;
	
	var newWindow = null;
   	var height = screen.height - 200;
   	var width = screen.width - 200 ;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=100,left=100";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);

}
function showSchemeCode(object)
{
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var url='ifms.htm?actionFlag=loadSchemeCodePopUp&schemeType=P&elementId='+object.id;
	//var url='ifms.htm?actionFlag=loadSchemeCode&elementId='+object.id;
	var urlstyle = "height=500,width=650,toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	
	newWindow = window.open(url, "SchemeCode", urlstyle);
}
function radioAction(obj)
{
	document.getElementById("divDtls").style.display="inline";
	document.getElementById("radioAction").style.display="none";
	document.getElementById("viewBtnTd").style.display="inline";
	if(obj.value == "rdAdd")
	{
		document.getElementById("addBtnTd").style.display="inline";
	//	var cmbObj = document.getElementById("cmbDisplayBranchNameList");
	//	var temp;
	//	document.getElementById("cmbDisplayBranchNameList").onfocus=abcd(cmbObj,temp);
	//	document.getElementById("cmbDisplayBranchNameList").onchange=efgh(cmbObj,temp);
	}
	else if(obj.value == "rdRemove")
	{
		document.getElementById("removeBtnTd").style.display="inline";
	}
}
/*function abcd(cmbObj)
{
	alert('abcd');
	temp  = cmbObj.selectedIndex;
}
function efgh(cmbObj)
{
	alert('efgh');
	cmbObj.selectedIndex = temp;
}*/

function checkSchemeCode(object)
{
	var schemeCode=object.value;
	var url;
	if(schemeCode != "")
	{
		url="ifms.htm?actionFlag=validateSchemeCode&SchemeType=P&SchemeCode="+schemeCode;
		//url="ifms.htm?actionFlag=checkSchemeCode&SchemeCode="+schemeCode;
		checkSchemeCodeUsingAjax(url,object.id,schemeCode);
	}
}
function checkSchemeCodeUsingAjax(uri,elementId,schemeCode)
{

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&SchemeCode="+schemeCode,
		        onSuccess: function(myAjax) {
					getStateChangedForValidSchemeCode(myAjax,elementId);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getStateChangedForValidSchemeCode(myAjax,elementId)
{
	   var XMLDoc =  myAjax.responseXML.documentElement;
	   
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	    
	     if(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue == 'N')
	     {
	    	 alert("Please Enter Correct Scheme Code.");
	    	 document.getElementById(elementId).value="";
    	 document.getElementById(elementId).focus();
	     }

}

