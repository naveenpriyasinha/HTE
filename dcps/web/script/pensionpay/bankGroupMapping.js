var Row_ID_BANKGRP = 0;
var lTotalPnsrs = 0;
function showAddUpdtContent(obj)
{
	if(obj.value == "Update")
	{
		document.getElementById("txtGrpName").style.display = "none";
		document.getElementById("cmbGrpName").style.display = "block";
		document.getElementById("trBankBranchDtls").style.display = "block";
		document.getElementById("deleteRow").style.display = "block";
		clearAllData();
	}
	else if(obj.value == "Delete")
	{
		document.getElementById("trBankBranchDtls").style.display = "none";
		document.getElementById("txtGrpName").style.display = "none";
		document.getElementById("cmbGrpName").style.display = "block";
		document.getElementById("deleteRow").style.display = "none";
		clearAllData();
	}
	else
	{
		document.getElementById("trBankBranchDtls").style.display = "block";
		document.getElementById("txtGrpName").style.display = "block";
		document.getElementById("cmbGrpName").style.display = "none";
		document.getElementById("deleteRow").style.display = "block";
		clearAllData();
	}
}

function clearAllData()
{
	document.getElementById("txtGrpName").value = "";
	document.getElementById("cmbGrpName").value = "-1";
	document.getElementById("cmbBankName").value = "-1";
	document.getElementById("cmbBranchName").value = "-1";
	var table=document.getElementById("tblBankGrpDtls");
	
	/*
	 * Deleting all rows before populating
	 */
	 for(var i = table.rows.length; i > 1;i--)
     {
		 table.deleteRow(i -1);
     }
}
function addBranchToGroup()
{
	var bankCode = document.getElementById("cmbBankName").value;
	var branchCode = document.getElementById("cmbBranchName").value;
	var bankName = document.getElementById("cmbBankName").options[document.getElementById("cmbBankName").options.selectedIndex].innerHTML;
	var branchName = document.getElementById("cmbBranchName").options[document.getElementById("cmbBranchName").options.selectedIndex].innerHTML;
	lTotalPnsrs = 0;
	if(validateAddBranchToGroup(bankCode,branchCode,bankName,branchName))
	{
		var params = "bankCode="+bankCode+"&branchCode="+branchCode;
		url = "ifms.htm?actionFlag=validateBranchForGrp";  
		var myAjax = new Ajax.Request(url,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters: params,
			        onSuccess: function(myAjax) {
								responseAddBranchToGroup(myAjax,bankName,branchName,bankCode,branchCode);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
	}
}

function validateAddBranchToGroup(bankCode,branchCode,bankName,branchName)
{
	var lArrBankCode = document.getElementsByName("hidBankCode");
	var lArrBranchCode = document.getElementsByName("hidBranchCode");
	var currBankCode = "";
	var currBranchCode = "";
	for(var k = 0;k < lArrBankCode.length ;k++)
	{
		currBankCode = lArrBankCode[k].value;
		currBranchCode = lArrBranchCode[k].value;
		if((currBankCode == bankCode) && (currBranchCode == branchCode ))
		{
			alert(branchName+","+bankName+" is already added to this group");
			return false;
		}
	}
	/*getTotalPensionersOfBranch(bankCode,branchCode);
	if(lTotalPnsrs > 100)
	{
		alert("Bank branch having pensioners less than 100 can only be added to the bank branch group");
		return false;	
	}*/
	return true;
}
function responseAddBranchToGroup(myAjax,paraBankName,paraBranchName,paraBankCode,paraBranchCode)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{
		var groupName = XMLDoc.getElementsByTagName('GRPNAME');
		if(groupName[0] != null)
		{
			alert(paraBranchName+" , "+paraBankName+" is already grouped with "+groupName[0].childNodes[0].nodeValue);
			return false;
		}
		else
		{
			bankGrpTableAddRow(paraBankName,paraBranchName,paraBankCode,paraBranchCode);
		}
	}
	else
	{
		alert("Some problem occurred during save.");
	}
}

function bankGrpTableAddRow(bankName,branchName,bankCode,branchCode)
{
		Row_ID_BANKGRP = document.getElementById("hidBankGrpGridSize").value;
		
		var table=document.getElementById("tblBankGrpDtls");
		var rowCount=table.rows.length;
		var newRow = table.insertRow(rowCount); 	  		
		
	   	var Cell1 = newRow.insertCell(0);
	   	Cell1.className = "tds";
	   	Cell1.align="center";
	   	var Cell2 = newRow.insertCell(1);
	   	Cell2.className = "tds";
	   	Cell2.align="center";
	   	var Cell3 = newRow.insertCell(2);
	   	Cell3.className = "tds";
	   	Cell3.align="center";
	   	var Cell4 = newRow.insertCell(3);
		Cell4.className = "tds";
	   	Cell4.align="center";
	      	   
	   	Cell1.innerHTML = Row_ID_BANKGRP+'<input type="hidden" name="hidSrNo" value="'+Row_ID_BANKGRP+'" />';
		Cell2.innerHTML = bankName+'<input type="hidden" name="hidBankCode" id="hidBankCode'+Row_ID_BANKGRP+'" value="'+bankCode+'" />';
	   	Cell3.innerHTML = branchName+'<input type="hidden" name="hidBranchCode" id="hidBranchCode'+Row_ID_BANKGRP+'" value="'+branchCode+'" />';
	   	Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblBankGrpDtls\')" /> ';
	   	document.getElementById("hidBankGrpGridSize").value = Number(Row_ID_BANKGRP)+1; 
}


function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
}
function showRowCell(element)
{
	var cell, row;    
 
    if (element.parentNode) 
    {
      do
      	cell = element.parentNode;
      while (cell.tagName.toLowerCase() != 'td')
      row = cell.parentNode;
    }
    else if (element.parentElement) 
    {
      do
      	cell= element.parentElement;
      while (cell.tagName.toLowerCase() != 'td')
      row = cell.parentElement;
    }
    
    return row.rowIndex;
}
function saveBankGroupDtls()
{
	var userAction = getUserAction();
	if(userAction == "Add")
	{
		var groupName = document.getElementById("txtGrpName").value.trim();
		if(groupName.length == 0)
		{
			alert("Group Name cannot be blank");
			return false;
		}
	}
	var params = prepareParams();
	showProgressbar();
	url = "ifms.htm?actionFlag=saveBankBrnchGrp";  
	var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: params,
		        onSuccess: function(myAjax) {
							saveBankGroupDtlsOnSuccess(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function getUserAction()
{
	var radBtnArr = document.getElementsByName("actionGrp");
	var userAction = "";
	for(var cnt = 0;cnt<radBtnArr.length;cnt++)
	{
		if(radBtnArr[cnt].checked)
		{
			userAction = radBtnArr[cnt].value;
		}
	}
	return userAction;
}
function prepareParams()
{
	var userAction = getUserAction();
	var lArrSrNo = document.getElementsByName("hidSrNo");
	var params = "";
	var crntSrNo = "";
	
	params = "userAction="+userAction;
	if(userAction == "Update" || userAction == "Delete")
	{
		params = params + "&groupId="+document.getElementById("cmbGrpName").value;
	}
	if(userAction == "Add")
	{
		var groupName = document.getElementById("txtGrpName").value;
		params = params + "&txtGrpName="+groupName;
	}
	for(var i = 0 ; i < lArrSrNo.length ;i++)
	{
		crntSrNo = lArrSrNo[i].value;
		lBankCode = document.getElementById("hidBankCode"+crntSrNo).value;
		lBranchCode = document.getElementById("hidBranchCode"+crntSrNo).value;
		params = params + "&bankBranchGrpDtls="+lBankCode+"~"+lBranchCode;
	}
	return params;
}

function saveBankGroupDtlsOnSuccess(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{
		var saveStatus = XMLDoc.getElementsByTagName('STATUS');
		if(saveStatus[0] != null)
		{
			alert("Bank branch group "+saveStatus[0].childNodes[0].nodeValue+" successfully");
			window.self.location.reload();
		}
	}
	else
	{
		alert("Some problem occurred during save.");
		hideProgressbar();
	}
}

function showBankGroupDtls(objCmbGrp)
{
	var lGrpId = objCmbGrp.value;
	var url = "ifms.htm?actionFlag=getBankGroupDtlsByGrpId"; 
	var params = "grpId="+lGrpId;
	var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: params,
		        onSuccess: function(myAjax) {
							populateBankBranchGrpDtls(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function populateBankBranchGrpDtls(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	var bankName = "";
	var branchName = "";
	var bankCode = "";
	var branchCode = "";
	var userAction =  getUserAction();
	document.getElementById("hidBankGrpGridSize").value = 1;
	var table=document.getElementById("tblBankGrpDtls");
	
	/*
	 * Deleting all rows before populating
	 */
	 for(var i = table.rows.length; i > 1;i--)
     {
		 table.deleteRow(i -1);
     }  
	if(XMLDoc != null)
	{
		var bnkBranchGrpDtls = XMLDoc.getElementsByTagName('GRPDTLS');
		document.getElementById("deleteRow").style.display = "none";
		if(bnkBranchGrpDtls.length > 0)
		{
			for(var i = 0;i <bnkBranchGrpDtls.length ;i++)
			{
				bankName = bnkBranchGrpDtls[i].childNodes[0].childNodes[0].nodeValue;
				branchName = bnkBranchGrpDtls[i].childNodes[1].childNodes[0].nodeValue;
				bankCode = bnkBranchGrpDtls[i].childNodes[2].childNodes[0].nodeValue;
				branchCode = bnkBranchGrpDtls[i].childNodes[3].childNodes[0].nodeValue;
				
				var rowCount=table.rows.length;
				var newRow = table.insertRow(rowCount); 
					  		
				Row_ID_BANKGRP = document.getElementById("hidBankGrpGridSize").value;
				
			   	var Cell1 = newRow.insertCell(0);
			   	Cell1.className = "tds";
			   	Cell1.align="center";
			   	var Cell2 = newRow.insertCell(1);
			   	Cell2.className = "tds";
			   	Cell2.align="center";
			   	var Cell3 = newRow.insertCell(2);
			   	Cell3.className = "tds";
			   	Cell3.align="center";
			   	if(userAction != "Delete")
			   	{			   	
			   		var Cell4 = newRow.insertCell(3);
			   		Cell4.className = "tds";
			   		Cell4.align="center";
			   		document.getElementById("deleteRow").style.display = "block";
			   	}
			      	   
			   	Cell1.innerHTML = Row_ID_BANKGRP+'<input type="hidden" name="hidSrNo" value="'+Row_ID_BANKGRP+'" />';
				Cell2.innerHTML = bankName+'<input type="hidden" name="hidBankCode" id="hidBankCode'+Row_ID_BANKGRP+'" value="'+bankCode+'" />';
			   	Cell3.innerHTML = branchName+'<input type="hidden" name="hidBranchCode" id="hidBranchCode'+Row_ID_BANKGRP+'" value="'+branchCode+'" />';
				if(userAction != "Delete")
			   	{
					Cell4.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblBankGrpDtls\')" /> ';
			   	}
			   	document.getElementById("hidBankGrpGridSize").value = Number(Row_ID_BANKGRP)+1;
			}
		}
	}
}
function getTotalPensionersOfBranch(bankCode,branchCode)
{
	var url = "ifms.htm?actionFlag=getTotalPnsrsOfBranch"; 
	var params = "bankCode="+bankCode+"&branchCode="+branchCode;
	var myAjax = new Ajax.Request(url,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: params,
		        onSuccess: function(myAjax) {
							getTotalPensionersOfBranchOnSuccess(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getTotalPensionersOfBranchOnSuccess(myAjax)
{
	XMLDoc =  myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{
		var lArrTotalPnsrs = XMLDoc.getElementsByTagName('TOTALPNSRCOUNT');
		if(lArrTotalPnsrs[0] != null)
		{
			lTotalPnsrs = lArrTotalPnsrs[0].childNodes[0].nodeValue;
		}
	}
	else
	{
		alert("Some problem occurred during save.");
		hideProgressbar();
	}
}