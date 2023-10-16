function showSearchCrtVal()
{
	//----Hiding all search criteria first...
	var lArrElements = document.getElementsByClassName("searchClass");
	
	//----Resetting all the search Values to default starts <<<
	document.getElementById("txtSearch").value = "";
	var lArrCmbElements = document.getElementsByName("cmbSearch");
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
	if((document.getElementById("cmbSearchCrt").value == "prh.ppo_no") 
		|| (document.getElementById("cmbSearchCrt").value == "mph.first_name")
		|| (document.getElementById("cmbSearchCrt").value == "mpd.registration_no")
		|| (document.getElementById("cmbSearchCrt").value == "prh.PPO_INWARD_DATE")
		|| (document.getElementById("cmbSearchCrt").value == "prh.PPO_REG_DATE")
		|| (document.getElementById("cmbSearchCrt").value == "mpd.branch_code")
		|| (document.getElementById("cmbSearchCrt").value == "mph.date_of_retirement")
		|| (document.getElementById("cmbSearchCrt").value == "prh.commencement_date")
		|| (document.getElementById("cmbSearchCrt").value == "mpd.acount_no")
		|| (document.getElementById("cmbSearchCrt").value == "sch.call_date"))
	{
		document.getElementById("txtSearch1").style.display = "block";
		document.getElementById("txtSearch").readOnly = false;
		//----For showing datepicker
		if((document.getElementById("cmbSearchCrt").value == "prh.PPO_INWARD_DATE")
			|| (document.getElementById("cmbSearchCrt").value == "prh.PPO_REG_DATE")
			|| (document.getElementById("cmbSearchCrt").value == "mph.date_of_retirement")
			|| (document.getElementById("cmbSearchCrt").value == "prh.commencement_date")	
			|| (document.getElementById("cmbSearchCrt").value == "sch.call_date"))
		{
			document.getElementById("txtSearch").readOnly = true;
			document.getElementById("dtpicker").style.display = "inline";
		}
	}
	//----For  Combobox
	else if(document.getElementById("cmbSearchCrt").value == "prh.type_flag")
	{
		document.getElementById("txtSearch2").style.display = "block";
	}
	else if(document.getElementById("cmbSearchCrt").value == "audi.post_id" || document.getElementById("cmbSearchCrt").value == "prh.case_owner")
	{
		document.getElementById("txtSearch3").style.display = "block";
	}
	else if(document.getElementById("cmbSearchCrt").value == "prh.inward_mode")
	{
		document.getElementById("txtSearch4").style.display = "block";
	}
	else if(document.getElementById("cmbSearchCrt").value == "mpd.bank_code")
	{
		document.getElementById("txtSearch5").style.display = "block";
	}
	else if(document.getElementById("cmbSearchCrt").value == "sch.schedule_status")
	{
		document.getElementById("txtSearch6").style.display = "block";
	}
	else if(document.getElementById("cmbSearchCrt").value == "prh.case_Status")
	{
		document.getElementById("txtSearch7").style.display = "block";
	}
	else{
		document.getElementById("txtSearch1").style.display = "block";
	}
}
function searchCases(showCaseFor,elementCode)
{
	var lStrSearchCrt = document.getElementById("cmbSearchCrt").value;
	var lStrSearchVal = "";
	var lTxtSearchVal = document.getElementById("txtSearch").value;
	if(lTxtSearchVal != null && lTxtSearchVal.length > 0)
	{	
		lStrSearchVal = lTxtSearchVal;
	}
	else
	{
		var lArrCmbSearchVal = document.getElementsByName("cmbSearch");
		for(var indx = 0; indx < lArrCmbSearchVal.length;indx++)
		{
			if(lArrCmbSearchVal[indx].value.length > 0 && lArrCmbSearchVal[indx].value != -1 )
			{
				lStrSearchVal = lArrCmbSearchVal[indx].value;
			}
		}
	}
	document.forms[0].action ='ifms.htm?actionFlag=getOLPenCases&showCaseFor='+showCaseFor+"&lStrSearchCrt="+lStrSearchCrt+"&lStrSearchVal="+lStrSearchVal+"&elementId="+elementCode;
	document.forms[0].submit();
	disable();
    showProgressbar();
}
