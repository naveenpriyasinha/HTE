function isNull(e)
{
  // alert("In ISNULL function");
   if((e.value==""))
   {
    alert("This Field is Compulsory");
//    e.focus();
    return true;
   }
}
function isNumeric(form1)
{
	var len,str,str1,i
	len=form1.value.length
	str=form1.value
	str1="0123456789."
	for(i=0;i<len;i++)
	{
		if((str1.indexOf(str.charAt(i)))==-1)
		{
			alert("Enter Numeric Data in this field");
 	                form1.value = "";
                  form1.focus();
			return false;
		}
	}
	return true
}
function chkdate(objName) {
  	
  	 var strDate;
	var strDateArray;
	var strDay;
	var strMonth;
	var strYear;
	var intday;
	var intMonth;
	var intYear;
	var booFound = false;
	var datefield = objName;
	var strSeparatorArray = new Array("-"," ","/",".");
	var intElementNr;
	var err = 0;
        var curdate=new Date();
         var comparedate;
	var strMonthArray = new Array(12);
	strMonthArray[0] = "Jan";
	strMonthArray[1] = "Feb";
	strMonthArray[2] = "Mar";
	strMonthArray[3] = "Apr";
	strMonthArray[4] = "May";
	strMonthArray[5] = "Jun";
	strMonthArray[6] = "Jul";
	strMonthArray[7] = "Aug";
	strMonthArray[8] = "Sep";
	strMonthArray[9] = "Oct";
	strMonthArray[10] = "Nov";
	strMonthArray[11] = "Dec";
	strDate = datefield.value;

 	if (strDate.length < 6) {
	return false;
	}
	for (intElementNr = 0; intElementNr < strSeparatorArray.length; intElementNr++) {
	if (strDate.indexOf(strSeparatorArray[intElementNr]) != -1) {
	strDateArray = strDate.split(strSeparatorArray[intElementNr]);
	if (strDateArray.length != 3) {
	err = 1;
	return false;
	}
	else {
	strDay = strDateArray[0];
	strMonth = strDateArray[1];
	strYear = strDateArray[2];
	}
	booFound = true;
 	  }
	}
	if (booFound == false) {
	if (strDate.length>5) {
	strDay = strDate.substr(0, 2);
	strMonth = strDate.substr(2, 2);
	strYear = strDate.substr(4);
	   }
	}
      

        if (strYear.length == 3) {
	return false;
	}
	if (strYear.length == 2) {
	strYear = '20' + strYear;
	}

  
	intday = parseInt(strDay, 10);
	if (isNaN(intday)) {
	err = 2;
	return false;
	}
	intMonth = parseInt(strMonth, 10);
	if (isNaN(intMonth)) {
	for (i = 0;i<12;i++) {
	if (strMonth.toUpperCase() == strMonthArray[i].toUpperCase()) {
	intMonth = i+1;
	strMonth = strMonthArray[i];
	i = 12;
	   }
	}
	if (isNaN(intMonth)) {
	err = 3;
	return false;
	   }
	}
	intYear = parseInt(strYear, 10);
	if (isNaN(intYear)) {
	err = 4;
	return false;
	}
	if (intMonth>12 || intMonth<1) {
	err = 5;
	return false;
	}
	if ((intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) && (intday > 31 || intday < 1)) {
	err = 6;
	return false;
	}
	if ((intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) && (intday > 30 || intday < 1)) {
	err = 7;
	return false;
	}
	if (intMonth == 2) {
	if (intday < 1) {
	err = 8;
	return false;
	}
	if (LeapYear(intYear) == true) {
	if (intday > 29) {
	err = 9;
	return false;
	}
	}
	else {
	if (intday > 28) {
	err = 10;
	return false;
	}
	}
	}
  
     tempdate=strDay +"/"+intMonth+"/"+strYear;         
//     datefield.value = strDay +"/"+intMonth+"/"+strYear;
     datefield.value = strDay +"/"+strMonth+"/"+strYear;
       
	return true;
	}
function dateCheck(dt)
{
   if(dt.value.length != 0)
   {
     if (dt.value != "dd/mm/yyyy")
     {
      if((dt.value.length < 6) || (dt.value.length >= 6 && !chkdate(dt)))
      {
         alert("Please fill valid date ");
         dt.value = "";
         //dt.focus();
         return false;
      }
      return doDateCheck("01/09/2006",dt.value);
     } 
   }else
	{
	   alert("Please Enter Bill Date...");
	   return false;
	}
   return true;
}
 function validateBudHead(frmField,level,fieldName,type)
 {
       getHeadDetails(type);
       flag = 0;
       if(frmField.value=='')
       {
       		flag=1;
	    	return;
       }
       else
       if(level == 0)
       {
       		if(level==0 && frmField.value=='000000')
	   		{
	   			flag=1
	   			return;
	   		}
	   		
	   		var scheme = new Array();
	   		for(var i=0;i<arrHeadStruct.length;i++)
	   		{
		   		if(arrHeadStruct[i][level] == frmField.value)
		   		{
		   			scheme.push(arrHeadStruct[i]);
		   			flag=1
				}
	   		}
	   			if(flag == 1 && scheme.length > 1)
				{
						var vObj = window.showModalDialog("common/schemepopup.html",scheme ,"dialogHeight:400px;dialogWidth:400px;center:1;resizable:1;scroll:1");	
						cmbDmd.value = vObj[1];
		   				cmbMjrHd.value = vObj[2];
		   				cmbSubMjrHd.value = vObj[3];
		   				cmbMinHd.value = vObj[4];
		   				cmbSubHd.value = vObj[5];
		   			    return;
		   		}else if(flag == 1 && scheme.length == 1)
		   		{
		   			    cmbDmd.value = scheme[0][1];
		   				cmbMjrHd.value = scheme[0][2];
		   				cmbSubMjrHd.value = scheme[0][3];
		   				cmbMinHd.value = scheme[0][4];
		   				cmbSubHd.value = scheme[0][5];
		   			    return;
		   		}
       }
       else if(level == 1)
       {
	   		for(var i=0;i<arrHeadStruct.length;i++)
	   		{
		   		if(arrHeadStruct[i][level] == frmField.value)
		   		{
		   			flag=1
					return;
				}
	   		}
       }
       else if(level == 2)
       {
	   		for(var i=0;i<arrHeadStruct.length;i++)
	   		{
		   		if(arrHeadStruct[i][1] == cmbDmd.value && arrHeadStruct[i][level] == frmField.value)
		   		{
		   			flag=1
					return;
				}
	   		}
       }
       else if(level == 3)
       {
	   		for(var i=0;i<arrHeadStruct.length;i++)
	   		{
		   		if(arrHeadStruct[i][1] == cmbDmd.value && arrHeadStruct[i][2] == cmbMjrHd.value && arrHeadStruct[i][level] == frmField.value)
		   		{
		   			flag=1
					return;
				}
	   		}
       }
       else if(level == 4)
       {
	   		for(var i=0;i<arrHeadStruct.length;i++)
	   		{
		   		if(arrHeadStruct[i][1] == cmbDmd.value && arrHeadStruct[i][2] == cmbMjrHd.value && arrHeadStruct[i][3] == cmbSubMjrHd.value && arrHeadStruct[i][level] == frmField.value)
		   		{
		   			flag=1
					return;
				}
	   		}
       }
       else if(level == 5)
       {
       		var plngCode ="";
	   		for(var i=0;i<arrHeadStruct.length;i++)
	   		{
		   		if(arrHeadStruct[i][1] == cmbDmd.value && arrHeadStruct[i][2] == cmbMjrHd.value && arrHeadStruct[i][3] == cmbSubMjrHd.value && arrHeadStruct[i][4] == cmbMinHd.value && arrHeadStruct[i][level] == frmField.value)
		   		{   
		   			var scheme = new Array();
		   			if(cmbSchemeCode.value != '000000')
		   			{
		   				for(var i=0;i<arrHeadStruct.length;i++)
	   					{
	   						/*if(arrHeadStruct[i][1] == cmbDmd.value && arrHeadStruct[i][2] == cmbMjrHd.value && arrHeadStruct[i][3] == cmbSubMjrHd.value && arrHeadStruct[i][4] == cmbMinHd.value && arrHeadStruct[i][level] == frmField.value && arrHeadStruct[i][0]==cmbSchemeCode.value)
	   						{
	   							flag=1
	   							return;
	   						}*/
	   						if(arrHeadStruct[i][1] == cmbDmd.value && arrHeadStruct[i][2] == cmbMjrHd.value && arrHeadStruct[i][3] == cmbSubMjrHd.value && arrHeadStruct[i][4] == cmbMinHd.value && arrHeadStruct[i][level] == frmField.value)
	   						{
	   							scheme.push(arrHeadStruct[i]);
	   						}
	   					}
		   			}
		   			if(scheme.length > 1)
		   			{
		   				var vObj = window.showModalDialog("common/schemepopup.html",scheme ,"dialogHeight:500px;dialogWidth:450px;center:1;resizable:1;scroll:1");
		   				cmbSchemeCode.value = vObj[0];
		   			}
		   			else
		   			{
		   				if(cmbSchemeCode.value != '000000')
	   						cmbSchemeCode.value = scheme[0][0];
		   			}
		   			flag=1
					return;
				}
	   		}
       }
	   if(flag == 0)
	   {
	   		alert('Please enter valid '+fieldName);
	   		frmField.value='';
	   		frmField.focus();
	   }
}

// Move From hdiits/web/web-inf/jsp/acl/changePassword.jsp.

function checkForNumber(val)
{
	var i;
	for (i = 0; i < val.length; i++) 
      	{ 
	      	ls_char1 = val.charAt(i); 
          if(ls_char1 >= '0' && ls_char1 <= '9')
			return true;
	}
	return false ;
}
function checkForSpecialChrs(val)
{
  var i;
  var splCharStr="!@#$%^&*()<>,./?\|[]{}'~`+=_";
	for (i = 0; i < val.length; i++) 
      	{ 
	      	ls_char1 = val.charAt(i);
          if(splCharStr.indexOf(ls_char1) >= 0)
			return true;
	}
	return false ;
}
function checkForChrs(val)
{
  var i;
  var splCharStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	for (i = 0; i < val.length; i++) 
      	{ 
	      	ls_char1 = val.charAt(i);
          if(splCharStr.indexOf(ls_char1) >= 0)
			return true;
	}
	return false ;
}
function toSetFocus()
{
  document.forms[0].txtOldPassword.focus();
}

function EnterkeyPressed(e,form)
{	
	var whichCode = (window.Event) ? e.which : e.keyCode;
	if ((e.keyCode==13))
	{
    validateChangePassword();
    return;
	}
}
function clearAllPasswords()
{
  document.forms[0].txtOldPassword.value = "";
  document.forms[0].txtNewPassword.value = "";
  document.forms[0].txtReEntNewPassword.value = "";  
  document.forms[0].txtOldPassword.focus();
}
     