function showSearchCrtVal()
{
	//----Hiding all search criteria first...
	var lArrElements = document.getElementsByClassName("searchClass");
	
	//----Resetting all the search Values to default starts <<<
	document.getElementById("txtSearch").value = "";
	var lArrCmbElements = document.getElementsByClassName("cmbSearch");
	for(var i = 0 ;i<lArrCmbElements.length;i++)
	{
		lArrCmbElements[i].value = "-1";
	}
	//----Resetting all the search Values to default ends >>>>>
	
	for(var indx = 0 ; indx < lArrElements.length;indx++)
	{
		lArrElements[indx].style.display = "none";
	}
	document.getElementById("dtpicker").style.display = "none";
	//-----For Textbox and Datepicker
	if((document.getElementById("cmbSearchCrt").value == "mcr.branch_code") 
		|| (document.getElementById("cmbSearchCrt").value == "mcr.upto_date"))
	{
		document.getElementById("txtSearch2").style.display = "block";
		document.getElementById("txtSearch").readOnly = false;
		//----For showing datepicker
		if(document.getElementById("cmbSearchCrt").value == "mcr.upto_date")
		{
			document.getElementById("txtSearch").readOnly = true;
			document.getElementById("dtpicker").style.display = "inline";
		}
	}
	//----For  Combobox
	else if(document.getElementById("cmbSearchCrt").value == "mcr.bank_code")
	{
		document.getElementById("txtSearch1").style.display = "block";
	}
	else if(document.getElementById("cmbSearchCrt").value == "mcr.status")
	{
		document.getElementById("txtSearch3").style.display = "block";
	}
	else if(document.getElementById("cmbSearchCrt").value == "mcr.for_month")
	{
		document.getElementById("txtSearch4").style.display = "block";
	}
	else if(document.getElementById("cmbSearchCrt").value == "mcr.created_post_id")
	{
		document.getElementById("txtSearch5").style.display = "block";
	}
	else{
		document.getElementById("txtSearch2").style.display = "block";
	}
}
function searchCases()
{
	var lStrSearchCrt = document.getElementById("cmbSearchCrt").value;
	var lStrSearchVal = "";
	var lStrBankCode = "";
	var lStrBranchCode = "";
	var lStrYearCode = "";
	var lStrYearId = "";
	var lStrMonthId = "";
	var lTxtSearchVal = document.getElementById("txtSearch").value;
	if(lTxtSearchVal != null && lTxtSearchVal.length > 0)
	{	
		lStrSearchVal = lTxtSearchVal;
	}
	else
	{
		if(lStrSearchCrt == "mcr.bank_code")
		{
			lStrBankCode = document.getElementById("cmbBank").value;
			lStrBranchCode = document.getElementById("cmbBranch").value;
			if(lStrBankCode == "-1")
			{
				alert("Plese select bank.");
				return false;
			}
		}
		else if(lStrSearchCrt == "mcr.for_month")
		{
			lStrYearCode = document.getElementById("cmbYear").options[document.getElementById("cmbYear").selectedIndex].text;
			lStrYearId = document.getElementById("cmbYear").value;
			lStrMonthId = document.getElementById("cmbMonth").value;
			if(lStrYearCode == "-1")
			{
				alert("Please select year.");
				return false;
			}
			if(lStrMonthId != "-1")
			{
				if(parseInt(lStrMonthId) < 10)
				{
					lStrMonthId = "0"+lStrMonthId;
				}
			}
			else
			{
				alert("Please select month.");
				return false;
			}
			lStrSearchVal = lStrYearCode+lStrMonthId;
		}
		else
		{
			var lArrCmbSearchVal = document.getElementsByClassName("cmbSearch");
			for(var indx = 0; indx < lArrCmbSearchVal.length;indx++)
			{
				if(lArrCmbSearchVal[indx].value.length > 0 && lArrCmbSearchVal[indx].value != -1 )
				{
					lStrSearchVal = lArrCmbSearchVal[indx].value;
				}
			}
		}
	}
	document.getElementById("hdnSearchCrt").value = lStrSearchCrt;
	document.getElementById("hdnSearchVal").value = lStrSearchVal;
	document.getElementById("hdnBankCode").value = lStrBankCode;
	document.getElementById("hdnBranchCode").value = lStrBranchCode;
	document.getElementById("hdnYearCode").value = lStrYearId;
	document.getElementById("hdnMonthId").value = document.getElementById("cmbMonth").value;
	
	document.forms[0].action ="ifms.htm?actionFlag=loadMonthlyList";
	document.forms[0].submit();
	disable();
    showProgressbar();
}
