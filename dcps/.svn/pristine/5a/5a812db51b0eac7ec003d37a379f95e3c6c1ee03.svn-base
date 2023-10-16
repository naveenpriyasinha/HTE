
function AttachAndDetachEmp()
{
	if(document.getElementById("dcpsEmpIdstoBeDetached").value == "" && document.getElementById("dcpsEmpIdstoBeAttached").value == ""){
		alert("No data is Saved, as no change has been made");
		return;
	}
	
	var dcpsEmpIdstoBeDetached = document.getElementById("dcpsEmpIdstoBeDetached").value ;
	var dcpsEmpIdstoBeAttached = document.getElementById("dcpsEmpIdstoBeAttached").value ;
	var billGroupId = document.getElementById("cmbBillGroup").value ;
	var typeOfOperation = document.getElementById("cmbTypeofAttachDetach").value;
	
	//alert('cmbTypeofAttachDetach is --'+document.getElementById("cmbTypeofAttachDetach").value);

	var uri = "ifms.htm?actionFlag=attachAndDetachEmpToBG";
	var url = "dcpsEmpIdstoBeDetached=" + dcpsEmpIdstoBeDetached
	+ "&dcpsEmpIdstoBeAttached=" + dcpsEmpIdstoBeAttached + "&billGroupId=" + billGroupId + "&typeOfAttachDetach=" + typeOfOperation ;	
	
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForModifyBG(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
}

function getDataStateChangedForModifyBG(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var test_Id = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	
	if(test_Id)
	{
		alert("Bill Group Modified Successfully");
	}
	self.location.href = "ifms.htm?actionFlag=dcpsBillGroup&elementId=700017&billGroupId="+document.getElementById("cmbBillGroup").value;	
}
function validateBeforeAttach(){
	
	var attachDetachTestFlag = document.getElementById('hdnAttachDetachFlag').value; 
	var payMonth = document.getElementById('payMonth').value; 
	var payYear = document.getElementById('payYear').value; 
	
	//alert('flag is'+attachDetachTestFlag);
	if(attachDetachTestFlag == 'true')
	{
		alert('Kindly complete Voucher Entry for Pending Pay Bill for Month: '+ payMonth +' and Year: '+ payYear +' ');
		return false;
	}
	
	
	var chkBoxArr=document.getElementsByName('GroupCheck'); 
	var chkLength=chkBoxArr.length;
	var typeOfOperation = document.getElementById('cmbTypeofAttachDetach');

	for(var i=0;i<chkLength;i++){ 
		if(chkBoxArr[i].checked)
		{
			AddRowInEmpBGTable();
			return true; 
		}
	} 
	if(typeOfOperation.Value == 1)
		alert("Please select an employee to attach to Bill group");
	if(typeOfOperation.Value == 2)
		alert("Please select a Post to attach to Bill group");
	return false;
}
function AddRowInEmpBGTable()
{
	var color1 = "#F0F0F0";
	var color2 = "#7B68EE";
	
	var counterEmp = document.getElementById("counterEmp").value ;
	var counterEmpBG = document.getElementById("counterEmpBG").value ;
	var dcpsEmpIdsToBeAddedToBGTable = new Array() ;
	var dcpsEmpNamesToBeAddedToBGTable = new Array() ;
	var selectedEmpIds = new Array();
	var totalSelectedEmpIds=0;
	var newRow;
	var Cell1;
	var Cell2;
	var counter = 1 ;
	var tableEmpBG = document.getElementById("tableEmpBG");
	var tableEmp =document.getElementById("tableEmp");
	
	for(var i=1;i<=counterEmp;i++)
	{
		if(document.getElementById("GroupCheck"+i).checked)
		{
			dcpsEmpIdsToBeAddedToBGTable[counter] = document.getElementById("GroupCheck"+i).value ;
			dcpsEmpNamesToBeAddedToBGTable[counter] = document.getElementById("empName"+i).innerHTML ;
			totalSelectedEmpIds = totalSelectedEmpIds + 1 ; 
			selectedEmpIds[counter] = i ;
			counter++ ;
		}
	}

	
	for(i=1;i<=totalSelectedEmpIds;i++)
	{
		counterEmpBG = Number(counterEmpBG) + 1 ;
		newRow = tableEmpBG.insertRow(tableEmpBG.rows.length);
		newRow.style.backgroundColor = color1;
		newRow.style.borderColor = color2;
		
		Cell1 = newRow.insertCell(-1);
		Cell2 = newRow.insertCell(-1);
		Cell1.align="center";
	   	Cell2.align="left";
	   	
	   	Cell1.innerHTML = '<input type="checkbox" name="GroupCheckBG" id="GroupCheckBG'+counterEmpBG+'" value="'+dcpsEmpIdsToBeAddedToBGTable[i]+'" />' ;
	    Cell2.innerHTML = '<label id="empNameBG'+counterEmpBG+'"><b>'+dcpsEmpNamesToBeAddedToBGTable[i]+'</b></label>' ;

	    document.getElementById("counterEmpBG").value = Number(document.getElementById("counterEmpBG").value) + 1 ;
	    document.getElementById("dcpsEmpIdstoBeAttached").value = document.getElementById("dcpsEmpIdstoBeAttached").value +  dcpsEmpIdsToBeAddedToBGTable[i] + "~" ;

	    document.getElementById("dcpsEmpIdstoBeDetached").value = document.getElementById("dcpsEmpIdstoBeDetached").value.replace(dcpsEmpIdsToBeAddedToBGTable[i] + "~","") ;
	    
	}
	

	for(i=counterEmp;i>=1;i--)
	{
		if(document.getElementById("GroupCheck"+i).checked)
		{
			tableEmp.rows[i].style.display = 'none' ;
			document.getElementById("GroupCheck"+i).checked = false ;
		}
	}
}
function validateBeforeDetach(){
	//alert("hiiiiiiiiiii");
	
	var attachDetachTestFlag = document.getElementById('hdnAttachDetachFlag').value; 
	var payMonth = document.getElementById('payMonth').value; 
	var payYear = document.getElementById('payYear').value; 
	//alert('flag is'+attachDetachTestFlag);
	//alert('payMonth'+payMonth);
	//alert('payYear'+payYear);
	if(attachDetachTestFlag == 'true')
	{
		alert('Kindly complete Voucher Entry for Pending Pay Bill for Month: '+ payMonth +' and Year: '+ payYear +' ');
		return false;
	}
	
	var chkBoxArr=document.getElementsByName('GroupCheckBG'); 
	var chkLength=chkBoxArr.length;
	var typeOfOperation = document.getElementById('cmbTypeofAttachDetach');

	for(var i=0;i<chkLength;i++){ 
		if(chkBoxArr[i].checked)
		{
			AddRowInEmpTable();
			return true; 
		}
	} 
	if(typeOfOperation.Value == 1)
		alert("Please select an employee to attach to Bill group");
	if(typeOfOperation.Value == 2)
		alert("Please select a Post to attach to Bill group");
	return false;	
}
function AddRowInEmpTable()
{

	var color1 = "#F0F0F0";
	var color2 = "#7B68EE";
	
	var counterEmp = document.getElementById("counterEmp").value ;
	var counterEmpBG = document.getElementById("counterEmpBG").value ;
	
	var dcpsEmpIdsToBeAddedToTable = new Array() ;
	var dcpsEmpNamesToBeAddedToTable = new Array() ;
	var selectedEmpIds = new Array();
	var totalSelectedEmpIds=0;
	var newRow;
	var Cell1;
	var Cell2;
	var counter = 1 ;
	var tableEmpBG = document.getElementById("tableEmpBG");
	var tableEmp = document.getElementById("tableEmp");
	
	for(var i=1;i<=counterEmpBG;i++)
	{
		if(document.getElementById("GroupCheckBG"+i).checked)
		{
			dcpsEmpIdsToBeAddedToTable[counter] = document.getElementById("GroupCheckBG"+i).value ;
			dcpsEmpNamesToBeAddedToTable[counter] = document.getElementById("empNameBG"+i).innerHTML ;
			totalSelectedEmpIds = totalSelectedEmpIds + 1 ; 
			selectedEmpIds[counter] = i ;
			counter++ ;
		}
	}

	for(i=1;i<=totalSelectedEmpIds;i++)
	{
		counterEmp = Number(counterEmp) + 1 ;
		newRow = tableEmp.insertRow(tableEmp.rows.length);
		newRow.style.backgroundColor = color1;
		newRow.style.borderColor = color2;
		
		Cell1 = newRow.insertCell(-1);
		Cell2 = newRow.insertCell(-1);
		Cell1.align="center";
	   	Cell2.align="left";
	   	
	   	Cell1.innerHTML = '<input type="checkbox" name="GroupCheck" id="GroupCheck'+counterEmp+'" value="'+dcpsEmpIdsToBeAddedToTable[i]+'" />' ;
	    Cell2.innerHTML = '<label id="empName'+counterEmp+'"><b>'+dcpsEmpNamesToBeAddedToTable[i]+'</b></label>' ;

	    document.getElementById("counterEmp").value = Number(document.getElementById("counterEmp").value) + 1 ;
	    document.getElementById("dcpsEmpIdstoBeDetached").value = document.getElementById("dcpsEmpIdstoBeDetached").value +  dcpsEmpIdsToBeAddedToTable[i] + "~" ;

	    document.getElementById("dcpsEmpIdstoBeAttached").value = document.getElementById("dcpsEmpIdstoBeAttached").value.replace(dcpsEmpIdsToBeAddedToTable[i] + "~","") ;
	}

	for(i=counterEmpBG;i>=1;i--)
	{
		if(document.getElementById("GroupCheckBG"+i).checked)
		{
			tableEmpBG.rows[i].style.display = 'none' ;
			document.getElementById("GroupCheckBG"+i).checked = false ;
		}
	}

}
function displayListsAfterValidation(){
	//alert('Inside displayListsAfterValidation');
	if(chkEmpty(document.getElementById('cmbBillGroup'),'Bill Group') ){
		displayLists();		
	}
	 
}
function displayLists()
{
//	alert('Inside DisplayList');
	if(chkEmpty(document.getElementById('cmbTypeofAttachDetach'),'Type of Attach Detach'))
	{
	//Disables the Attach and Detach buttons;
	var billGroupId = document.getElementById("cmbBillGroup").value ;
	var typeOfAttachDetach = document.getElementById("cmbTypeofAttachDetach").value ;
	url = "ifms.htm?actionFlag=dcpsBillGroup&elementId=700017&billGroupId="+billGroupId+"&typeOfAttachDetach="+typeOfAttachDetach ;
	self.location.href = url ;
	showProgressbar("Please wait...");
	}
}
function checkUncheckAll(theElement)
{
	var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name == 'GroupCheck' )
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}	
function checkUncheckAllBG(theElement)
{
	var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name == 'GroupCheckBG')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}

function ReturnPage()
{
	self.location.href="ifms.htm?actionFlag=validateLogin";
}

function enableDisableDetachBtn()
{
	var empBGChecked = false;
	var lArrGroupBGCheckBoxes = document.getElementsByName("GroupCheckBG");
	
	for(var i=0;i<lArrGroupBGCheckBoxes.length;i++)
	{
		if(lArrGroupBGCheckBoxes[i].checked)
		{
			empBGChecked = true;
		}
	}
	if(empBGChecked)
	{
     	document.getElementById("btnAttach").disabled = true;
    	document.getElementById("btnDetach").disabled = false;
	}
	else
	{
		document.getElementById("btnAttach").disabled = true;
    	document.getElementById("btnDetach").disabled = true;
	}
}
function enableDisableAttachBtn()
{
	if(!document.getElementById("btnAttach").disabled)
	var lArrGroupCheckBoxes = document.getElementsByName("GroupCheck");
	var empChecked = false ;
	
	for(var i=0;i<lArrGroupCheckBoxes.length;i++)
	{
		if(lArrGroupCheckBoxes[i].checked)
		{
			empChecked = true;
		}
	}
	if(empChecked)
	{
     	document.getElementById("btnAttach").disabled = false;
    	document.getElementById("btnDetach").disabled = true;
	}
	else
	{
		document.getElementById("btnAttach").disabled = true;
    	document.getElementById("btnDetach").disabled = true;
	}
}
