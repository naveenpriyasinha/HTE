
function window_open(val,x,y,afterDateSelect,dateInputParams){
    var newWindow;
 
    if(afterDateSelect == undefined) {
		afterDateSelect = '';
	}
	glbAfterDateSelect = afterDateSelect;
   
    var urlstring = "common/calendarDppf.jsp?requestSent=" +val;
    
    dateChkInputs = dateInputParams;
    var X = window.event.screenX  - window.event.offsetX;
    var Y = window.event.screenY  + (20 - window.event.offsetY);	    
  
    var urlstyle = 'height=230,width=315,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+Y+',left='+X;
	
	newWindow = displayModalDialog(urlstring,'Calendar',urlstyle);
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


function RemoveTableRow(obj, tblId)
{
	var rowID = showRowCell(obj); 
	var tbl = document.getElementById(tblId); 
	tbl.deleteRow(rowID); 
}


function SaveData(callFromApprove)
{
	if(validatePhysicalCaseInward(callFromApprove))
	{
			var uri;
			if(document.getElementById("hdnPensionerCode").value != "")
			{
				//calcOtherRecovery();
				uri = 'ifms.htm?actionFlag=insertPhysicalPensionCase&PensionerId='+document.getElementById("hdnPensionerCode").value+"&callFromApprove="+callFromApprove+"&currRole="+document.getElementById("hdnCurrRole").value;
			}
			else
			{
				//calcOtherRecovery();
				uri = 'ifms.htm?actionFlag=insertPhysicalPensionCase';
			}
			showProgressbar();
			savePhysicalCaseUsingAjx(uri);
					
	}
	else
	{
		document.getElementById("btnSave").disabled=false;
		document.getElementById("btnClose").disabled=false;
	}
   
}

 
function savePhysicalCaseUsingAjx(uri)
{
   xmlHttp=GetXmlHttpObject();

   if (xmlHttp==null)
   {
      return;
   }  
        
   var url = runForm(0); 
   url = uri + url;
   var lPnsrStateName = document.getElementById("cmbPnsnrAddrState").options[document.getElementById("cmbPnsnrAddrState").selectedIndex].text;
   var lPnsrDistName = document.getElementById("cmbPnsnrAddrDist").options[document.getElementById("cmbPnsnrAddrDist").selectedIndex].text;
   var lGuardianStateName="";
   var lGuardianDistName=""; 
   if(document.getElementById("cmbGuardianAddrState").value != -1)
   {
	   lGuardianStateName = document.getElementById("cmbGuardianAddrState").options[document.getElementById("cmbGuardianAddrState").selectedIndex].text;
   }
   if(document.getElementById("cmbGuardianAddrDist").value != -1)
   {
	   lGuardianDistName = document.getElementById("cmbGuardianAddrDist").options[document.getElementById("cmbGuardianAddrDist").selectedIndex].text;
   }
   url = url+"&lPnsrStateName="+lPnsrStateName+"&lPnsrDistName="+lPnsrDistName+"&lGuardianStateName="+lGuardianStateName+"&lGuardianDistName="+lGuardianDistName;
   var elementMinor = document.getElementsByName("chkMinor");
	for(var i=0;i<elementMinor.length;i++)
	{
		if(elementMinor[i].checked)
		{
			url = url+'&chkMinorVar=Y';
		}
		else
		{
			url = url+'&chkMinorVar=';
		}
	}
	var elementMarried = document.getElementsByName("chkMarried");
	for(var j=0;j<elementMarried.length;j++)
	{
		if(elementMarried[j].checked)
		{
			url = url+'&chkMarriedVar=Y';
		}
		else
		{
			url = url+'&chkMarriedVar=';
		}
	}
	var elementRestnAplnRecvd = document.getElementsByName("chkRestnAplnRecvd");
	for(var k=0;k<elementRestnAplnRecvd.length;k++)
	{
		if(elementRestnAplnRecvd[k].checked)
		{
			url = url+'&chkRetnAplnRecvd=Y';
		}
		else
		{
			url = url+'&chkRetnAplnRecvd=';
		}
	}
   xmlHttp.onreadystatechange=caseStateChanged;
   xmlHttp.open("POST",uri,true);
   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
   xmlHttp.send(url);
   
}


function caseStateChanged() 
{ 
   if (xmlHttp.readyState==complete_state)
   { 
	   	  var XMLDoc=xmlHttp.responseXML.documentElement;
	   	 
    	  var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
    	  
    	  if(XmlHiddenValues[0].childNodes[1].text=='insert')
    	  {
    		  alert("Pension Case "+ XmlHiddenValues[0].childNodes[0].text +" is Saved Successfully.");
    	  }
    	  else if(XmlHiddenValues[0].childNodes[1].text=='update')
    	  {
    		  alert("Pension Case "+ XmlHiddenValues[0].childNodes[0].text +" is Updated Successfully.");
    		 
    		  var showCaseFor=document.getElementById("hdnShowCaseFor").value;
    		  if(showCaseFor=='15' || showCaseFor=='20')
    		  {
    			    var currRole = document.getElementById("hdnCurrRole").value;
    				document.loadForm.action = "ifms.htm?actionFlag=loadPhyPenCase&pensionerId="+document.getElementById("hdnPensionerCode").value + "&showCaseFor="+showCaseFor+"&currRole="+currRole;
    				document.loadForm.submit();
    				hideProgressbar();
    		  }
    		  if(document.getElementById("hdnPnsnRqstId").value != "")
    		  {
    			  window.opener.location.reload();
    		  }
    	  }
    	  else if(XmlHiddenValues[0].childNodes[1].text=='updateonapprove')
    	  {
    		  approveCase();
    	  }
    	  document.getElementById("hdnPpoNo").value=XmlHiddenValues[0].childNodes[0].text;
    	  document.getElementById("hdnPensionerCode").value=XmlHiddenValues[0].childNodes[2].text;
    	  document.getElementById("btnSave").disabled=false;
    	  document.getElementById("btnClose").disabled=false;
    	  hideProgressbar();
   }
}

function chkDates()
{
	var dobDt = document.getElementById("txtDateOfBirth").value;
	var dojDt = document.getElementById("txtDateOfJoining").value;
	var dorDt = document.getElementById("txtDateOfRetirement").value;
	var doeDt="";
	if(document.getElementById("cmbAlive").value=="N")
	{
	  doeDt = document.getElementById("txtDateofExpiry").value;
	}
	var dodDc = document.getElementById("txtDateOfCommencement").value;
	var fp1dt="";
	var fp2dt="";
	fp1dt=document.getElementById("txtFp1Date").value;
	fp2dt=document.getElementById("txtFp2Date").value;
	var ppoEndDt=document.getElementById("txtPpoEndDate").value;
	var reEmpFrmDt=document.getElementById("txtReEmpFromDate").value;
	var reEmpToDt=document.getElementById("txtReEmpToDate").value;
	//var cvpPaidDt=document.getElementById("txtCvpPaidDate").value;
	//var dcrgPaidDt=document.getElementById("txtDcrgPaidDate").value;
	//var provPnsnFrmDt=document.getElementById("txtProvPnsnFromDate").value;
	//var provPnsnToDt=document.getElementById("txtProvPnsnToDate").value;
	var agOutDt=document.getElementById("txtPpoAGOutwDate").value;
	var currentDt=document.getElementById("hdnCurrDate").value;
		
	if(agOutDt.length > 0 && currentDt.length >0)
	{
		if(agOutDt != currentDt)
		{
			if(compareDate(agOutDt,currentDt) == false)
			{
				alert(AGOUTDTCURDT);
				document.getElementById("txtPpoAGOutwDate").value="";
				goToFieldTab('txtPpoAGOutwDate',0);
				return false;
			}
		}
	}
	if((dobDt.length > 0 && doeDt.length > 0) || (dobDt.length > 0 && dojDt.length > 0) || (dobDt.length > 0 && dorDt.length > 0))
	{
		if(dojDt.length>0)
		{
			if(compareDate(dobDt,dojDt) == false)
			{
				alert(JOININGBIRTHDT);
				document.getElementById("txtDateOfJoining").value="";
				goToFieldTab('txtDateOfJoining',0);
				return false;
			}
		}
		if(dorDt.length>0)
		{
			if(compareDate(dobDt,dorDt) == false)
			{
				alert(RETBIRTHDT);
				document.getElementById("txtDateOfRetirement").value="";
				goToFieldTab('txtDateOfRetirement',0);
				return false;
			}
			else
			{
					if(document.getElementById("cmbPensionType").value == SUPERANNU)
					{
						if(validateDateDiff(dobDt,dorDt,58) == false){
							alert("Retirement Date should be greater than or equal to 58 years from Date of Birth.");
							document.getElementById("txtDateOfRetirement").value="";
							goToFieldTab('txtDateOfRetirement',0);
							return false;
						}
					}
			}
			
		}
		if(doeDt.length>0)
		{
			if(compareDate(dobDt,doeDt) == false)
			{
				alert(EXPBIRTH);
				document.getElementById("txtDateofExpiry").value="";
				goToFieldTab('txtDateofExpiry',0);
				return false;
			}
		}
	}
	if((dojDt.length>0 && dorDt.length>0) || (dojDt.length>0 && doeDt.length>0))

	{
		if(dorDt.length>0)
		{
			if(compareDate(dojDt,dorDt) == false)
			{
				alert(RETJOINDT);
				document.getElementById("txtDateOfRetirement").value="";
				goToFieldTab('txtDateOfRetirement',0);
				return false;
			}
		}
		if(doeDt.length>0)
		{
			if(compareDate(dojDt,doeDt) == false)
			{
				alert(EXPJOINDT);
				document.getElementById("txtDateofExpiry").value="";
				goToFieldTab('txtDateofExpiry',0);
				return false;
			}
		}
	}
	if(doeDt.length > 0 && dorDt.length >0)
	{
		if(dorDt != doeDt)
		{
			if(compareDate(dorDt,doeDt) == false)
			{
				alert(EXPIRYDATELTRETDATE);
				document.getElementById("txtDateofExpiry").value="";
				goToFieldTab('txtDateofExpiry',0);
				return false;
			}
		}
	}
	if(dodDc.length >0 && dorDt.length>0)
	{
		if(document.getElementById("cmbPensionType").value != VOLUNTARY64 && document.getElementById("cmbPensionType").value != VOLUNTARY65)
		{
			if(compareDate(dorDt,dodDc) == false)
			{
				alert(CMNDATEGTRETDATE);
				document.getElementById("txtDateOfCommencement").value="";
				goToFieldTab('txtDateOfCommencement',0);
				return false;
			}
			else
			{
				if(document.getElementById("cmbPensionerType").value != 'Teacher')
				{
					var validDate=document.getElementById("hdnCommencementDate").value;
					if(dodDc != validDate)
					{
						alert("Pension Commencement Date must be next date of Retirement Date.");
						//document.getElementById("txtDateOfCommencement").value="";
						goToFieldTab('txtDateOfCommencement',0);
						return false;
					}
				}
			}
		}
		else
		{
			if(dorDt != dodDc)
			{
				if(compareDate(dorDt,dodDc) == false)
				{
					alert(CMNDATEGTRETDATE);
					document.getElementById("txtDateOfCommencement").value="";
					goToFieldTab('txtDateOfCommencement',0);
					return false;
				}
			}
		}

	}

	if(dodDc.length >0 && fp1dt.length>0)
	{
		if(compareDate(dodDc,fp1dt) == false || dodDc == fp1dt)
		{
			alert(FP1DTGRTCOMDT);
			document.getElementById("txtFp1Date").value="";
			goToFieldTab('txtFp1Date',1);
			return false;
		}
	}
	if(fp1dt.length >0 && fp2dt.length>0)
	{
		if(compareDate(fp1dt,fp2dt) == false || fp1dt == fp2dt)
		{
			alert(FP2DTGRTFP1DT);
			document.getElementById("txtFp2Date").value="";
			goToFieldTab('txtFp2Date',1);
			return false;
		}
	}
	
	if(dodDc.length >0 && ppoEndDt.length>0)
	{
		if(compareDate(dodDc,ppoEndDt) == false || dodDc == ppoEndDt)
		{
			alert(PPOENDDTGRTCOMDT);
			document.getElementById("txtPpoEndDate").value="";
			goToFieldTab('txtPpoEndDate',0);
			return false;
		}
	}
	if(dodDc.length >0 && reEmpFrmDt.length>0)
	{
		if(compareDate(dodDc,reEmpFrmDt) == false || dodDc == reEmpFrmDt)
		{
			alert(REEMPFROMCOMNTDT);
			document.getElementById("txtReEmpFromDate").value="";
			goToFieldTab('txtReEmpFromDate',0);
			return false;
		}
//		if(reEmpFrmDt != currentDt)
//		{
//			if(compareDate(reEmpFrmDt,currentDt) == false)
//			{
//				alert(REEMPFROMCOMNTDT);
//				document.getElementById("txtReEmpFromDate").value="";
//				goToFieldTab('txtReEmpFromDate',0);
//				return false;
//			}
//		}
	}
	if(reEmpFrmDt.length >0 && reEmpToDt.length>0)
	{
		if(compareDate(reEmpFrmDt,reEmpToDt) == false || reEmpFrmDt == reEmpToDt)
		{
			alert(REEMPFROMTODATE);
			document.getElementById("txtReEmpToDate").value="";
			goToFieldTab('txtReEmpToDate',0);
			return false;
		}
		if(compareDate(currentDt,reEmpToDt) == false)
		{
			alert(REEMPTODTCURDT);
			document.getElementById("txtReEmpToDate").value="";
			goToFieldTab('txtReEmpToDate',0);
			return false;
		}
	}
//	if(dodDc.length >0 && cvpPaidDt.length>0)
//	{
//		if(compareDate(dodDc,cvpPaidDt) == false)
//		{
//			alert(COMPAIDDTCOMDT);
//			document.getElementById("txtCvpPaidDate").value="";
//			goToFieldTab('txtCvpPaidDate',1);
//			return false;
//		}
//	}
//	if(dodDc.length >0 && dcrgPaidDt.length>0)
//	{
//		if(compareDate(dodDc,dcrgPaidDt) == false)
//		{
//			alert(DCRGPAIDDTCOMDT);
//			document.getElementById("txtDcrgPaidDate").value="";
//			goToFieldTab('txtDcrgPaidDate',1);
//			return false;
//		}
//	}
//	if(provPnsnFrmDt.length >0 && provPnsnToDt.length>0)
//	{
//		if(compareDate(provPnsnFrmDt,provPnsnToDt) == false)
//		{
//			alert(PROVPNSNDTS);
//			document.getElementById("txtProvPnsnToDate").value="";
//			goToFieldTab('txtProvPnsnToDate',1);
//			return false;
//		}
//	}
	return true;
}




/*---------Date Validation---------*/
var dtCh= "/";
var minYear=1900;
var maxYear=2100;

/*---------Comparing two dates---------*/
function compareDates(fieldName1,fieldName2,alrtStr,flag)
{
	var Date1 = fieldName1.value;
	var Date2 = fieldName2.value;
    var la_Date1 = new Array();
    la_Date1 = Date1.split("/");
    var day1=parseFloat(la_Date1[0]);
    var month1=parseFloat(la_Date1[1]);
    var year1=parseFloat(la_Date1[2]);

    var la_Date2 = new Array();
    la_Date2 = Date2.split("/");
    var day2=parseFloat(la_Date2[0]);
    var month2=parseFloat(la_Date2[1]);
    var year2=parseFloat(la_Date2[2]);

    if (year2 == year1 && month2 == month1 && day2 == day1)
    {
    	if(flag == '=')
    	{
    		alert(alrtStr);
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else
    	{
	        return true;
	    }
    }
    else if( year2 > year1 )
    {
        return true;
    }
    else if( year2 < year1 && flag != '=')
    {
        alert(alrtStr);
        if(flag == '<')
        {
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    	else if(flag == '>')
    	{
    	    fieldName2.focus();
    	    fieldName2.value="";
    	}
    }
    else if (flag != '=')
    {
        if( month2 > month1 )
        {
            return true;
        }
        else if( month2 < month1 )
        {     
             alert(alrtStr);
             if(flag == '<')
	        {
    		    fieldName2.focus();
    		    fieldName2.value="";
	    	}
    		else if(flag == '>')
    		{
	    	    fieldName2.focus();
	    	    fieldName2.value="";
    		}
        }
        else
        {
            if( day2 > day1 )
            {
                return true;
            }
            else if( day2 < day1 )
            {
                 alert(alrtStr);
                 if(flag == '<')
			     {
    			    fieldName2.focus();
    			    fieldName2.value="";
			   	}
		    	else if(flag == '>')
    			{
    			    fieldName2.focus();
    			    fieldName2.value="";
		    	}
            }
        }
    }
    return true ;
}

//validate email id
function validateEmailID(txtEmail,alerttxt)
{
	var emailfilter=/^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i;
	if(txtEmail.value.length > 0)
	{
		var returnval=emailfilter.test(txtEmail.value)
		if (returnval==false)
		{
			alert(alerttxt)
			txtEmail.focus();
			return false;
		}
		return returnval;
	}
	return false;
}

function chkLength(fieldname)
{
	var numberField=fieldname.value;
	if(numberField.length>0)
	{
		if(numberField.length >= 10)
		{
			return true;
		}
		else
		{
			alert("Mobile Number must be of 10 digit. ");
			fieldname.focus();
			return false;
		}	
	}
	return true;
}

function upperCase(event)
{

	if (event.keyCode >= 97 && event.keyCode <= 122 )
		event.keyCode = event.keyCode-32;
	if(((window.event.keyCode >32 && window.event.keyCode <= 57 && window.event.keyCode != 46 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127)))
	{
		window.event.keyCode = 0;
	}	
}

function compareDate(frmStr,toStr)
{
	if(frmStr.length > 0 && frmStr.length > 0)
	{
		var frmdaysInMonth = DaysArray(12)
		var frmpos1=frmStr.indexOf(dtCh)
		var frmpos2=frmStr.indexOf(dtCh,frmpos1+1)
		var frmDay=frmStr.substring(0,frmpos1)
		var frmMonth=frmStr.substring(frmpos1+1,frmpos2)
		var frmYear=frmStr.substring(frmpos2+1)

		var todaysInMonth = DaysArray(12)
		var topos1=toStr.indexOf(dtCh)
		var topos2=toStr.indexOf(dtCh,topos1+1)
		var toDay=toStr.substring(0,topos1)
		var toMonth=toStr.substring(topos1+1,topos2)
		var toYear=toStr.substring(topos2+1)

		if(frmYear > toYear)
		{
			return false;
		}
		if(frmYear == toYear)
		{
			if(frmMonth > toMonth)
			{
				return false;
			}
		}
		if((frmYear == toYear) && (frmMonth == toMonth) && (frmDay == toDay))
		{
			return false;
		}
		if((frmYear == toYear) && (frmMonth == toMonth))
		{
			if(frmDay > toDay)
			{
				return false;
			}
		}
		return true;
	}
	return false;
}

//function compareDate(frmStr,toStr)
//{
//	if(frmStr.length > 0 && frmStr.length > 0)
//	{
//		var frmdaysInMonth = DaysArray(12)
//		var frmpos1=frmStr.indexOf(dtCh)
//		var frmpos2=frmStr.indexOf(dtCh,frmpos1+1)
//		var frmDay=frmStr.substring(0,frmpos1)
//		var frmMonth=frmStr.substring(frmpos1+1,frmpos2)
//		var frmYear=frmStr.substring(frmpos2+1)
//
//		var todaysInMonth = DaysArray(12)
//		var topos1=toStr.indexOf(dtCh)
//		var topos2=toStr.indexOf(dtCh,topos1+1)
//		var toDay=toStr.substring(0,topos1)
//		var toMonth=toStr.substring(topos1+1,topos2)
//		var toYear=toStr.substring(topos2+1)
//		alert("from year="+frmYear);
//		alert("toYear="+toYear);
//		if(frmYear > toYear)
//		{
//			return false;
//		}
//		if(frmYear == toYear)
//		{
//			if(frmMonth > toMonth)
//			{
//				return false;
//			}
//		}
//		if((frmYear == toYear) && (frmMonth == toMonth))
//		{
//			if(frmDay > toDay)
//			{
//				return false;
//			}
//		}
//		return true;
//	}
//	return false;
//}
function onChangeAlive()
{
//	  if(IsEmptyFun("txtDateOfBirth",'Please Enter Date Of Birth.','0')==false)
//	   {
//		   return false;
//	   }
//	  if(IsEmptyFun("cmbAlive",'Please select value','0')==false)
//	   {
//		   return false;
//	   }
	   if(document.getElementById("cmbAlive").value == 'N')
	   {
		   document.getElementById("txtDateofExpiry").disabled=false;  
		   document.getElementById("imgDateofExpiry").disabled=false;
//		   if(IsEmptyFun("txtDateofExpiry",'Please enter date of expiry.','0')==false)
//			{
//				return false;
//			} 
//		   if(compareDate(document.getElementById("txtDateOfBirth").value,document.getElementById("txtDateofExpiry").value ))
//		   {
//		   	   return true;
//		   }
//		   else
//		   {
//			   alert("Date of Expiry should be greater than Date of Birth.");
//			   document.getElementById("txtDateofExpiry").focus;
//			   return false;
//		   }
			   
	   }
	   
	   if(document.getElementById("cmbAlive").value == 'Y')
	   {
		   document.getElementById("txtDateofExpiry").disabled=true;
		   document.getElementById("imgDateofExpiry").disabled=true;
		   document.getElementById("txtDateofExpiry").value="";
		} 
	   
	   
	   return true;
}

function validatePhysicalCaseInward(callFromApprove)
{	
	document.getElementById("btnSave").disabled=true;
	document.getElementById("btnClose").disabled=true;
	if(IsEmptyFun("txtPpoNo",PPONO,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbPensionClass",PNSNCLASS,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbPensionType",PNSNCASETYPE,'0')==false) 
	{
		return false;
	}
	if(IsEmptyFun("cmbPensionStatus",PNSNCASESTATUS,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbCalculationType",CALCTYPE,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbCaseReceivedFrom",CASERECEIVEFROM,'0')==false)
	{
		return false;
	}
	if(document.getElementById("cmbCaseReceivedFrom").value==OTHRSTATE)
	{
		if(IsEmptyFun("cmbOtherState",RECEIVEDOFFICE,'0')==false)
		{
			return false;
		}
		
	}
	if(document.getElementById("cmbCaseReceivedFrom").value==OTHRTRSURY)
	{
		if(IsEmptyFun("cmbReceivedTrsyOffice",RECEIVEDOFFICE,'0')==false)
		{
			return false;
		}
	}
	if(document.getElementById("cmbCaseReceivedFrom").value==ANYOTHRSRC)
	{
		if(IsEmptyFun("txtCaseReceivedOffice",RECEIVEDOTHERSRC,'0')==false)
		{
			return false;
		}
	}
	if(IsEmptyFun("txtPpoAGOutwNO",AGOUTWARDNO,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtPpoAGOutwDate",AGOUTWARDDT,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtPnsnrName",FULLNAMEPNSNR,'0')==false)
	{
		return false;
	}
//	if(IsEmptyFun("txtPnsnrNameInMarathi",NAMEINMARATHI,'0')==false)
//	{
//		return false;
//	}
	if(IsEmptyFun("txtDateOfBirth",DATEOFBIRTH,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbDojAvailable",'Please Select Date of Joining Available or not.','0')==false)
	{
		return false;
	}
	if(document.getElementById("cmbDojAvailable").value == 'Y')
	{
		if(IsEmptyFun("txtDateOfJoining",DATEOFJOINING,'0')==false)
		{
			return false;
		}
	}
	if(IsEmptyFun("txtDateOfRetirement",DATEOFRET,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("txtDateOfCommencement",COMDATE,'0')==false) 
	{
		return false;
	}
	if(IsEmptyFun("cmbAlive",ALIVE,'0')==false)
	{
		return false;
	}
//	if(IsEmptyFun("txtPanNo",PANNO,'0')==false)
//	{
//		return false;
//	}
	if(IsEmptyFun("txtDesignation",DESIGNATION,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbPensionerType",PNSNRTYPE,'0')==false)  
	{
		return false;
	}
//	if(IsEmptyFun("txtPnsnrAddr1",BUILDING,'0')==false)
//	{
//		return false;
//	}
	if(IsEmptyFun("cmbPnsnrAddrState",STATE,'0')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbPnsnrAddrDist",DISTRICT,'0')==false)
	{
		return false;
	}
//	if(IsEmptyFun("txtPnsnrAddrPincode",PINCODE,'0')==false)
//	{
//		return false;
//	}
	var reEmpFlag=0;
	var radioReEmpVal="";
	var radioReEmployment=document.getElementsByName("radioReEmployment");
	for (i=0; i<radioReEmployment.length; i++)
	{
		  if (radioReEmployment[i].checked == true)
		  {
			  radioReEmpVal=radioReEmployment[i].value;
			  reEmpFlag=1;
		  }

	}
	if(radioReEmpVal == "Y")
	{
		if(IsEmptyFun("txtReEmpFromDate",REEMPLTFROMDATE,'0')==false)
		{
			return false;
		}
//		if(IsEmptyFun("txtReEmpToDate",REEMPLTTODATE,'0')==false)
//		{
//			return false;
//		}
		if(IsEmptyFun("cmbDaInPnsnSalary",DAINPNSNSAL,'0')==false)
		{
			return false;
		}
	}	
//	if(IsEmptyFun("txtGuardianName",NAME,'0')==false)
//	{
//		return false;
//	}
//	if(IsEmptyFun("txtGuardianFHName",FATHERNAME,'0')==false)
//	{
//		return false;
//	}
//	if(IsEmptyFun("txtGuardianAddrBuilding",BUILDING,'0')==false)
//	{
//		return false;
//	}
//	if(IsEmptyFun("cmbGuardianAddrState",STATE,'0')==false)
//	{
//		return false;
//	}
//	if(IsEmptyFun("cmbGuardianAddrDist",DISTRICT,'0')==false)
//	{
//		return false;
//	}
//	if(IsEmptyFun("txtGuardianAddrPincode",PINCODE,'0')==false)
//	{
//		return false;
//	}
	if(validateReEmploymentDtls()==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbPaymentScheme",PAYMENTSCHEME,'1')==false)
	{
		return false;
	}	
	if(IsEmptyFun("cmbHeadCode",HEADCODE,'1')==false)
	{
		return false;
	}
	if(IsEmptyFun("cmbStateCode",STATECODE,'1')==false)
	{
		return false;
	}
//	var fp1Date=document.getElementById("txtFp1Date").value;
//	var fp2Date=document.getElementById("txtFp2Date").value;
//	var fp1Amount=document.getElementById("txtFp1amount").value;
//	var fp2Amount=document.getElementById("txtFp2amount").value;
//	if(document.getElementById("cmbPensionType").value == "Family Pension" || fp1Date.length > 0 || fp2Date.length > 0 ||
//			fp1Amount.length > 0 && fp1Amount != '0.00'|| fp2Amount.length > 0 && fp2Amount != '0.00')
//	{
		
//	}
	var accountNo=document.getElementById("txtAccountNo").value;
	
	if(document.getElementById("cmbBankCode").value == "-1" && accountNo.length > 0)
	{
		if(IsEmptyFun("cmbBankCode",BANKNAME,'1')==false)
		{
			return false;
		}
	}
	if(document.getElementById("cmbBankCode").value != "-1" || accountNo.length > 0)
	{
		if(IsEmptyFun("cmbBankBranch",BRANCHNAME,'1')==false)
		{
			return false;
		}
		if(document.getElementById("cmbPaymentScheme").value != MONEYORDER)
		{
			if(IsEmptyFun("txtAccountNo",ACCOUNTNO,'1')==false)
			{
				return false;
			}
		}
	}
	if(document.getElementById("cmbPaymentScheme").value != MONEYORDER)
	{
		if(document.getElementById("cmbBankCode").value == BANKCODEFORMONEYORDER)
		{
			alert("POST MASTER bank is mapped with payment scheme money order only.");
			document.getElementById("cmbBankCode").value="-1";
			document.getElementById("cmbBankBranch").value="-1";
			document.getElementById("cmbBankCode").disabled = false;
			document.getElementById("cmbBankBranch").disabled = false;
			document.getElementById("cmbBankCode").focus();
			return false;
		}
	}
	//--------Validation for bank details
	if(callFromApprove == 'Y')
	{
		var identFlag = false;
		var lArrIdentification = document.getElementsByName("radioPpoIdentify");
		var radioId;
		if(lArrIdentification != null)
		{
			for(var indx = 0;indx < lArrIdentification.length ; indx++)
			{
				if(lArrIdentification[indx].checked == true)
				{
					radioId = lArrIdentification[indx].id;
					if(lArrIdentification[indx].value == 'Y')
					{
						identFlag = true;
					}
				}
			}
		}
		var lStrIdentCallDate = document.getElementById("hdnCallDate").value;
		var lStrIdentCallSlotNo = document.getElementById("hdnCallSlotNo").value;
		if((lStrIdentCallDate.length == 0) || (lStrIdentCallSlotNo.length == 0))
		{
			alert("Pensioner can't be identified without scheduling.Please schedule identification for the pensioner.");
			return false;
		}
		
		if(IsEmptyFun("cmbBankCode",BANKNAME,'1')==false)
		{
			return false;
		}
		if(IsEmptyFun("cmbBankBranch",BRANCHNAME,'1')==false)
		{
			return false;
		}
		if(IsEmptyFun("txtAccountNo",ACCOUNTNO,'1')==false)
		{
			return false;
		}
		getAuditorNameFromBranchCode();
		if(document.getElementById("cmbBankBranch").value=='-1')
		{
			goToFieldTab("cmbBankBranch",'1');
			return false;
		}
		if(IsEmptyFun("txtLedgerNo",LEDGERNO,'0')==false)
		{
			return false;
		}
		if(IsEmptyFun("txtPageNo",PAGENO,'0')==false)
		{
			return false;
		}
		var currentDt = document.getElementById("hdnCurrDate").value;
		var retirementDt = document.getElementById("txtDateOfRetirement").value;
			
		if(currentDt.length > 0 && retirementDt.length > 0)
		{
			if(compareDate(retirementDt,currentDt) == false)
			{
				alert(RETDTLTCURDT);
				
				goToFieldTab('txtDateOfRetirement',0);
				return false;
			}
		}
		if(!identFlag)
		{
			alert("Please check the pensioner as identified");
			goToFieldTab(radioId,'0');
			return false;
		}
	}
	if(IsEmptyFun("cmbRopType",ROPTYPE,'1')==false)
	{
		return false;
	}
	if(document.getElementById("cmbRopType").value == '1996')
	{
		var flag=0;
		if(document.getElementById("radioDpMergeY").checked)
		{
			flag=1;
		}
		if(document.getElementById("radioDpMergeN").checked)
		{
			flag=1;
		}
		if(flag==0)
		{
			alert("Please Select DP Merge.");
	    	goToFieldTab('radioDpMergeN',1);
	    	document.getElementById("radioDpMergeN").focus();
			return false;
		}
	}
	if(IsEmptyFun("txtBasicPensionAmt",BASICPNSNAMT,'1')==false)
	{
		return false;
	}
	if(document.getElementById("radioFpAvailableY").checked)
	{
		if(IsEmptyFun("txtFp1Date",FP1DATE,'1')==false)
		{
			return false;
		}
		if(IsEmptyFun("txtFp1amount",FP1AMOUNT,'1')==false)
		{
			return false;
		}
		if(IsEmptyFun("txtFp2Date",FP2DATE,'1')==false)
		{
			return false;
		}
		if(IsEmptyFun("txtFp2amount",FP2AMOUNT,'1')==false)
		{
			return false;
		}
		
	}

	
//	var ProvGrtyFlag=0;
//	var radioProvGratuityVal;
//	var radioProvGratuity=document.getElementsByName("radioProvGratuity")
//	for (i=0; i<radioProvGratuity.length; i++)
//	{
//		  if (radioProvGratuity[i].checked == true)
//		  {
//			  radioProvGratuityVal=radioProvGratuity[i].value;
//			  ProvGrtyFlag=1;
//		  }
//
//	}
//    if(ProvGrtyFlag == 0)
//    {
//    	alert(PROVGRATUITY);
//    	goToFieldTab('radioProvGratuity',1);
//    	document.getElementById("radioProvGratuity").focus();
//    	return false;
//    }
//	if(radioProvGratuityVal=='Y')
//	{
//		if(IsEmptyFun("txtGratuityAmount",PROVGRATUITYAMT,'1')==false)
//		{
//			return false;
//		}
//		if(IsEmptyFun("txtGratuityActualAmtPaid",ACTUALAMTPAID,'1')==false)
//		{
//			return false;
//		}
//		if(IsEmptyFun("txtGratuityPaymentDate",PAYMENTDT,'1')==false)
//		{
//			return false;
//		}
//		
//	}
//	var ProvPnsnFlag=0;
//	var radioProvPnsnVal;
//	var radioProvPnsn=document.getElementsByName("radioProvPnsn")
//	for (i=0; i<radioProvPnsn.length; i++)
//	{
//		  if (radioProvPnsn[i].checked == true)
//		  {
//			  radioProvPnsnVal=radioProvPnsn[i].value;
//			  ProvPnsnFlag=1;
//		  }
//
//	}
//	if(ProvPnsnFlag == 0)
//	{
//		alert(PROVPNSN);
//    	goToFieldTab('radioProvPnsn',1);
//    	document.getElementById("radioProvPnsn").focus();
//    	return false;
//	}
//	if(radioProvPnsnVal=='Y')
//	{
//		if(IsEmptyFun("txtProvPnsnAmt",PROVPNSNAMT,'1')==false)
//		{
//			return false;
//		}
//		if(IsEmptyFun("txtProvPnsnFromDate",PROVPNSNFROMDT,'1')==false)
//		{
//			return false;
//		}
//		if(IsEmptyFun("txtProvPnsnToDate",PROVPNSNTODT,'1')==false)
//		{
//			return false;
//		}
//		if(IsEmptyFun("txtProvPnsnTotalAmountPaid",TOTALAMTPAID,'1')==false)
//		{
//			return false;
//		}
//	}
//	if(IsEmptyFun("txtFp1Date",FP1DATE,'1')==false)
//	{
//		return false;
//	}
//	if(IsEmptyFun("txtFp1amount",FP1AMOUNT,'1')==false)
//	{
//		return false;
//	}
//	if(IsEmptyFun("txtFp2Date",FP2DATE,'1')==false)
//	{
//		return false;
//	}
//	if(IsEmptyFun("txtFp2amount",FP2AMOUNT,'1')==false)
//	{
//		return false;
//	}
	if(isEmptyProvPnsnDtls() == false)
	{	
		return false;
	}
	if(document.getElementById('cmbAlive').value=='N')
	{
		if(IsEmptyFun("txtDateofExpiry",'Please Enter Date of Expiry.','0')==false)
		{
			return false;
		} 
		if((document.getElementById('tblPnsnrFamilyDtls').rows.length)==1)
		{
			goToTab(2);
			alert(PNSNRFAMILYDTLS);
			return false;
		}
	}
	if(validatePunishmentCut() == false)
	{
		return false;
	}
//	if((document.getElementById('tblNomineeDtls').rows.length)==1)
//	{
//		goToTab(2);
//		alert(PNSNRNOMDTLS);
//		return false;
//	}
	
	/*if(validateVoucherDetails()==false)
	{
		return false;
	}*/
	if(validateFamilyDtls()==false)
	{
		return false;
	}
	if(validateRecoveryDtls() == false)
	{
		return false;
	}
//	if(document.getElementById("radioAllocIndicatorY").checked)
//	{
	if(validateAllocationAmt()==false)
	{
		return false;
	}
	//}
	if(validateAllocationRevisionDtls() == false)
	{
		return false;
	}
	if(document.getElementById("hdnShowCaseFor").value == '15')
	{
		if((document.getElementById('tblForRemarks').rows.length)==1)
		{
			alert(REMARKS);
			return false;
		}
		if(validateRemarksDtls() == false)
		{
			return false;
		}
	}
	if(document.getElementById("cmbPensionType").value != VOLUNTARY64 && document.getElementById("cmbPensionType").value != VOLUNTARY65)
	{
		getNextDate();
	}
	if(chkDates()==false)
	{
		return false;
	}
	
	return true;
}
function IsEmptyFun(varStr,alrtStr,tabNo)
{
	var element = document.getElementById(varStr).value;
	var lStr = new String(element);
	element = lStr.trim();
	if( element == "" || element.length == 0 || element == "-1" || element == "0.00" || element == "0")
	{
		alert(alrtStr);
		goToFieldTab(varStr,tabNo);
		return false;
	}
	return true;
}


function goToFieldTab(field,cnt)
{
	goToTab(cnt);

	if(document.getElementById(field) != null && ! document.getElementById(field).disabled)
	{
		document.getElementById(field).focus();
	}
}
function setFP1AndFp2Date()
{
	if(onChangeAlive())
	{
	setFp1Date("txtDateOfBirth","hidFp1Date1","dateOfBirth");
	if(document.getElementById("cmbAlive").value == 'N')
	{
	   setFp1Date("txtDateofExpiry","hidFp1Date2","dateOfExpiry");
	
		if(compareDate(document.getElementById("hidFp1Date1").value ,document.getElementById("hidFp1Date2").value))
		{
			document.getElementById("txtFp1Date").value=document.getElementById("hidFp1Date1").value;
		}
		else
		{
			document.getElementById("txtFp1Date").value=document.getElementById("hidFp1Date2").value;
		}
	}
	else
	{
		document.getElementById("txtFp1Date").value=document.getElementById("hidFp1Date1").value;
	}
	setFp2Date();
	document.getElementById("hidFp2Date").value=document.getElementById("txtFp2Date").value;
	}
}



function setFp1Date(sourceFieldId,targetFieldId,str)
{
	lStrDate = document.getElementById(sourceFieldId).value;

	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		document.getElementById(targetFieldId).value = (new String(""));
		return;
	}

	if(str == "dateOfBirth")
	{
		date.setFullYear(date.getFullYear()+68);
	}
	if(str == "dateOfExpiry")
	{
		date.setFullYear(date.getFullYear()+10);
	}
	
	if(lArrDate[0] == 1)
	{
		var x;
		if(lArrDate[1] == 1)
		{
			x = DaysArray2(12 ,lArrDate[2]-1);
			lArrDate[1] = 13;
			date.setFullYear(date.getFullYear()-1);
		}
		else
		{
			if(lArrDate[1]-1 == 2 )
			{
				x = daysInFebruaryFP(lArrDate[2]);
			}
			else
			{
				x = DaysArrayFP(lArrDate[2]);
			}
		}
		document.getElementById(targetFieldId).value =  (new String(x).length > 1 ? x : "0" + new String(x)) + "/" +(new String(lArrDate[1] -1).length > 1 ? lArrDate[1] -1 : "0"+new String(lArrDate[1] -1))+"/"+date.getFullYear();

	}
	else
	{
		document.getElementById(targetFieldId).value = (new String(lArrDate[0]-1).length > 1 ? lArrDate[0]-1 : "0" + new String(lArrDate[0]-1)) + "/" +(new String(lArrDate[1]).length > 1 ? lArrDate[1] : "0" + new String(lArrDate[1]) )+"/"+date.getFullYear();
	}
	
}

function daysInFebruaryFP (year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}
function DaysArrayFP(n)
{
	var daysFp;
	for (var i = 1; i <= n; i++)
	{
		if(i != 2)
		{
			daysFp = 31
		}
		if (i==4 || i==6 || i==9 || i==11)
		{
			daysFp = 30
		}
	 	}
		return daysFp;
}
function setFp2Date()
{
	var lStrDate = document.getElementById("txtFp1Date").value;
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		document.getElementById("txtFp2Date").value = (new String(""));
		return;
	}
	date.setDate(date.getDate()+1);

	if(date.getMonth()==11)
	 {
		document.getElementById("txtFp2Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();

	 }
	 else
	 {
		  document.getElementById("txtFp2Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();

	 }
}
function DaysArray2(m,y)
{
	var days = '';
	if (m==4 || m==6 || m==9 || m==11)
	{
		days = 30;
	}
	else
	if(m == 1)
	{
		days = daysInFebruary2(y)
	}
	else
	{
		days = 31;
	}
		return days;
}

function daysInFebruary2(year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}

function validatePPONo()
{
	var newPpoNo=document.getElementById("txtPpoNo").value;
	var oldPpoNo=document.getElementById("hdnPpoNo").value;

	if(document.getElementById("txtPpoNo").value != "")
	{
		if(!(newPpoNo.toUpperCase().trim() == oldPpoNo.toUpperCase().trim()))
		{
			var uri;
			uri = 'ifms.htm?actionFlag=validationOfPPONo';
			validatePPOUsingAjax(uri);
		}
	}
}

function validatePPOUsingAjax(uri)
{
	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&PpoNo="+document.getElementById("txtPpoNo").value,
		        onSuccess: function(myAjax) {
					validatePPOOnStateChanged(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
//   xmlHttp=GetXmlHttpObject();
//
//   if (xmlHttp==null)
//   {
//      return;
//   }  
//           
//   xmlHttp.onreadystatechange=validatePPOOnStateChanged;
//   xmlHttp.open("POST",uri,false);
//   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//   xmlHttp.send(uri);
   
}


function validatePPOOnStateChanged(myAjax) 
{ 
	   var XMLDoc =  myAjax.responseXML.documentElement;
	   
	   var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	    
	     if(XmlHiddenValues[0].childNodes[1].childNodes[0].nodeValue == 'Y')
	     {
	    	 alert(XmlHiddenValues[0].childNodes[0].childNodes[0].nodeValue+ " is already exist in "+XmlHiddenValues[0].childNodes[2].childNodes[0].nodeValue);
	    	 //document.getElementById("txtPpoNo").value="";
	    	 document.getElementById("txtPpoNo").focus();
	     }
	     
//   if (xmlHttp.readyState==complete_state)
//   { 
//	  var XMLDoc=xmlHttp.responseXML.documentElement;
//   
//      var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
//    
//     if(XmlHiddenValues[0].childNodes[1].text == 'Y')
//     {
//    	 alert(XmlHiddenValues[0].childNodes[0].text+ " is already exist in "+XmlHiddenValues[0].childNodes[2].text);
//    	 document.getElementById("txtPpoNo").value="";
//    	 document.getElementById("txtPpoNo").focus();
//     }
//
//   }
}

function approveCase()
{
	/*var identFlag = false;
	var lArrIdentification = document.getElementsByName("radioPpoIdentify");
	var radioId;
	if(lArrIdentification != null)
	{
		for(var indx = 0;indx < lArrIdentification.length ; indx++)
		{
			if(lArrIdentification[indx].checked == true)
			{
				radioId = lArrIdentification[indx].id;
				if(lArrIdentification[indx].value == 'Y')
				{
					identFlag = true;
				}
			}
		}
	}
	if(identFlag)
	{	*/
	 var lPnsrCode =  document.getElementById("hdnPensionerCode").value;
	 var lPnsnRqstId = document.getElementById("hdnPnsnRqstId").value;
	 var lPnsrName = document.getElementById("txtPnsnrName").value;
	 var uri = "ifms.htm?actionFlag=identApprovePnsr";
	 var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters: "&pnsrCode="+lPnsrCode+"&pnsnRqstId="+lPnsnRqstId,
		        onSuccess: function(myAjax) {
					approveCaseUsingAjax(myAjax,lPnsrName);
				},
		        onFailure: function(){ alert('Something went wrong...')} 
		          } );
	
	
	
	
//		xmlHttp=GetXmlHttpObject();
//		xmlHttp=GetXmlHttpObject();
//	
//		   if (xmlHttp==null)
//		   {
//		      return;
//		   } 
//		 var lPnsrCode =  document.getElementById("hdnPensionerCode").value;
//		 var lPnsnRqstId = document.getElementById("hdnPnsnRqstId").value;
//		 var lPnsrName = document.getElementById("txtPnsnrName").value;
//		 var uri = "ifms.htm?actionFlag=identApprovePnsr";
//		 var url = "pnsrCode="+lPnsrCode+"&pnsnRqstId="+lPnsnRqstId;
//		 xmlHttp.onreadystatechange = function(){
//				if (xmlHttp.readyState==complete_state)
//				  {
//					var XMLDoc = xmlHttp.responseXML.documentElement;
//					if (XMLDoc != null) {
//						var lArrMessage = XMLDoc.getElementsByTagName('MESSAGE');
//						if(lArrMessage != null && lArrMessage.length > 0)
//						{
//							alert(lPnsrName+" is identified successfully and case is forwarded to Pension Auditor.");
//							window.close();
//							window.opener.location.reload();
//							hideProgressbar();
//							
//						}
//						else{
//							alert("Some problem occured.Please try again.");
//							hideProgressbar();
//						}
//				  }
//				}	
//			};
//		 xmlHttp.open("POST",uri,false);
//		 xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
//		 xmlHttp.send(url);
	/*}
	else
	{
		alert("Please check the pensioner as identified");
		goToFieldTab(radioId,'0');
	}*/
}

function approveCaseUsingAjax(myAjax,lPnsrName)
{
	 var XMLDoc =  myAjax.responseXML.documentElement;
	   
	 
	 if (XMLDoc != null) {
		    var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			var lArrMessage = XMLDoc.getElementsByTagName('MESSAGE');
			if(lArrMessage != null && lArrMessage.length > 0)
			{
				alert(lPnsrName+" is identified successfully and case is forwarded to Pension Auditor.");
				window.close();
				window.opener.location.reload();
				hideProgressbar();
				
			}
			else{
				alert("Some problem occured.Please try again.");
				hideProgressbar();
			}
	  }
	    
}

function getFocus()
{
	document.getElementById("btnSave").focus();
}

function onChangePensionType()
{
	if(document.getElementById("cmbPensionType").value == FAMILYPNSN)
	{
		document.getElementById("cmbAlive").value="N";
		document.getElementById("cmbAlive").disabled=true;
		document.getElementById("txtDateofExpiry").disabled=false;  
		document.getElementById("imgDateofExpiry").disabled=false;
	}
	else
	{
		document.getElementById("cmbAlive").value="Y";
		document.getElementById("cmbAlive").disabled=false;
		document.getElementById("txtDateofExpiry").value="";
		document.getElementById("txtDateofExpiry").disabled=true;  
		document.getElementById("imgDateofExpiry").disabled=true;
	}
}

function getCaseReceivedOffice()
{
	if(document.getElementById("cmbCaseReceivedFrom").value==OTHRSTATE)
	{
		document.getElementById("cmbOtherState").style.display="inline";
		document.getElementById("cmbReceivedTrsyOffice").style.display="none";
		document.getElementById("txtCaseReceivedOffice").style.display="none";
		document.getElementById("cmbReceivedOffice").style.display="none";
	}
	else if(document.getElementById("cmbCaseReceivedFrom").value==OTHRTRSURY)
	{
		document.getElementById("cmbReceivedTrsyOffice").style.display="inline";
		document.getElementById("cmbOtherState").style.display="none";
		document.getElementById("txtCaseReceivedOffice").style.display="none";
		document.getElementById("cmbReceivedOffice").style.display="none";
		
	}
	else if(document.getElementById("cmbCaseReceivedFrom").value==ANYOTHRSRC)
	{
		document.getElementById("cmbReceivedTrsyOffice").style.display="none";
		document.getElementById("cmbOtherState").style.display="none";
		document.getElementById("txtCaseReceivedOffice").style.display="inline";
		document.getElementById("cmbReceivedOffice").style.display="none";
	}
	else
	{
		document.getElementById("cmbReceivedOffice").style.display="inline";
		document.getElementById("cmbReceivedTrsyOffice").style.display="none";
		document.getElementById("cmbOtherState").style.display="none";
		document.getElementById("txtCaseReceivedOffice").style.display="none";
	}
}

function maxSizeReached(lThis,size,nextElement)
{
	var lStr = new String(lThis.value);
	if(lStr.length >= Number(size))
	{
		if(window.event.keyCode == 13)
		{
			document.getElementById(nextElement).focus();
			window.event.keyCode = 0;
		}
		else
		{
			window.event.keyCode = 0;
		}
	}
}
function upperCaseNumber(event)
{
	if (event.keyCode >= 97 && event.keyCode <= 122 )
		event.keyCode = event.keyCode-32;
	if(((window.event.keyCode >32 && window.event.keyCode < 47 && window.event.keyCode != 46 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127)))
	{
		window.event.keyCode = 0;
	}	
}

function getPnsnCaseAmtDtls()
{
	
    var ppoNo=document.getElementById("txtPpoNo").value;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId=9&PPONo='+ppoNo+'&isNewSavingBill=Y';
	
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	
	window.open(urlstring,"FirstPaymentBillGeneration", urlstyle);
}
function generatePaymentHistory()
{
	var pensionerCode=document.getElementById("hdnPensionerCode").value;
	
	var uri="ifms.htm?actionFlag=reportService&reportCode=365457&action=generateReport&pensionerCode="+pensionerCode+"&asPopup=TRUE";
	
	window_new_update(uri);
	
}	
function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "ECSReport", urlstyle);
}

function chkDateDiffInYear(date1,date2)
{
	var fromDate=document.getElementById(date1.id).value;
	var toDate=document.getElementById(date2.id).value;
		
	if(validateDateDiff(fromDate,toDate,18)==false){

		if (!confirm("Are you sure pensioner joined before age of 18 years?")) 
		{ 
			document.getElementById(date2.id).value=""; 
		} 
//		else 
//		  { 
//			  document.getElementById(date2.id).value="";
//		  } 
//
//		alert("Pensioner join before 18 years age!")
	}
}

function validateDateDiff(birthDate,retDate,noOfYear)
{
	var frmDate=birthDate;
	var toDate=retDate;
	var splitFromDate=frmDate.split("/");	
	
	var fromDay=parseInt(splitFromDate[0],10);
	var fromMonth=parseInt(splitFromDate[1],10);
	var fromYear=parseInt(splitFromDate[2]);

	var splitToDate=toDate.split("/");
	
	toDay=parseInt(splitToDate[0],10);
	toMonth=parseInt(splitToDate[1],10);
	toYear=parseInt(splitToDate[2]);
	
	var yearDiff = toYear - fromYear;
	var monthDiff = toMonth - fromMonth;
	var dayDiff = toDay - fromDay;
	
	if(yearDiff < noOfYear)
	{
		return false;
	}
	if(yearDiff == noOfYear)
	{
		if(toMonth < fromMonth)
		{
			return false;
		}
		if(toMonth > fromMonth)
		{
			return true;
		}
		if(toMonth == fromMonth)
		{
			if(toDay < fromDay)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
	}
	return true;
//	var fDate= new Date(fromYear,fromMonth,fromDay);
//	var tDate= new Date(toYear,toMonth,toDay);
//
//		
//	var user_date = Date.parse(myVar);
//	var today_date = new Date();
//	var diffDate =  tDate - fDate;
//
//	var numYears = diffDate/31536000000;
//	var num_months = (diff_date % 31536000000)/2628000000;
//	var num_days = ((diff_date % 31536000000) % 2628000000)/86400000;
//
//	document.write("Number of years: " + Math.floor(num_years) + "<br>");
//	document.write("Number of months: " + Math.floor(num_months) + "<br>");
//	document.write("Number of days: " + Math.floor(num_days) + "<br>");
//
//	
//	alert("Number of years: " + Math.floor(numYears));
//	//return d2.getFullYear()-d1.getFullYear();
//	return Math.floor(numYears);

}

function getNextDate()
{
	var lStrDate = document.getElementById("txtDateOfRetirement").value;
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		document.getElementById("hdnCommencementDate").value = (new String(""));
		document.getElementById("txtDateOfCommencement").value = (new String(""));
		return;
	}
	date.setDate(date.getDate()+1);

	if(date.getMonth()==11)
	 {
		document.getElementById("hdnCommencementDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
		document.getElementById("txtDateOfCommencement").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
	 }
	 else
	 {
		  document.getElementById("hdnCommencementDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
		  document.getElementById("txtDateOfCommencement").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();
	 }
}

function validateCommencementDate()
{
	var commencementDate=document.getElementById("txtDateOfCommencement").value;
	var validDate=document.getElementById("hdnCommencementDate").value;
	if(document.getElementById("cmbPensionerType").value != 'Teacher' && document.getElementById("cmbPensionType").value != VOLUNTARY64 && document.getElementById("cmbPensionType").value != VOLUNTARY65)
	{
		if(commencementDate != validDate)
		{
			alert("Pension Commencement Date must be next date of Retirement Date.");
			//document.getElementById("txtDateOfCommencement").select();
			//document.getElementById("txtDateOfCommencement").value="";
			//document.getElementById("txtDateOfCommencement").focus();
		}
	}
}

function onChangeDojAvaliable()
{
	var dojAvailable=document.getElementById("cmbDojAvailable").value;
	if(dojAvailable == "Y")
	{
		document.getElementById("txtDateOfJoining").disabled=false;
	    document.getElementById("imgDateOfJoining").disabled=false;
	}
	else
	{
		document.getElementById("txtDateOfJoining").value="";
		document.getElementById("txtDateOfJoining").disabled=true;
	    document.getElementById("imgDateOfJoining").disabled=true;
	}
}

function populateGuardianAddr(obj)
{
	if(obj.value == "Y")
	{
		document.getElementById("txtGuardianAddr1").value=document.getElementById("txtPnsnrAddr1").value;
		document.getElementById("txtGuardianAddr2").value=document.getElementById("txtPnsnrAddr2").value;
		document.getElementById("txtGuardianAddrTown").value=document.getElementById("txtPnsnrAddrTown").value;
		document.getElementById("cmbGuardianAddrState").value=document.getElementById("cmbPnsnrAddrState").value;
		var lPnsnrDistName = document.getElementById("cmbPnsnrAddrDist").options[document.getElementById("cmbPnsnrAddrDist").selectedIndex].text;
		var lPnsnrDistId = document.getElementById("cmbPnsnrAddrDist").value;
		var theOption = new Option;	
		theOption.value = lPnsnrDistId;
		theOption.text = lPnsnrDistName;
		document.getElementById("cmbGuardianAddrDist").options[0] = theOption;
		document.getElementById("cmbGuardianAddrDist").options[0].selected="selected";
		document.getElementById("txtGuardianAddrPincode").value=document.getElementById("txtPnsnrAddrPincode").value;
		document.getElementById("txtGuardianAddr1").disabled=true;
		document.getElementById("txtGuardianAddr2").disabled=true;
		document.getElementById("txtGuardianAddrTown").disabled=true;
		document.getElementById("cmbGuardianAddrState").disabled=true;
		document.getElementById("cmbGuardianAddrDist").disabled=true;
		document.getElementById("txtGuardianAddrPincode").disabled=true;
	}
	else
	{
		document.getElementById("txtGuardianAddr1").value="";
		document.getElementById("txtGuardianAddr2").value="";
		document.getElementById("txtGuardianAddrTown").value="";
		document.getElementById("cmbGuardianAddrState").value="-1";
		document.getElementById("cmbGuardianAddrDist").value="-1";
		document.getElementById("txtGuardianAddrPincode").value="";
		document.getElementById("txtGuardianAddr1").disabled=false;
		document.getElementById("txtGuardianAddr2").disabled=false;
		document.getElementById("txtGuardianAddrTown").disabled=false;
		document.getElementById("cmbGuardianAddrState").disabled=false;
		document.getElementById("cmbGuardianAddrDist").disabled=false;
		document.getElementById("txtGuardianAddrPincode").disabled=false;
	}
}

function enableReEmplDtls(obj)
{
	if(obj.value=="Y")
	{
		document.getElementById("txtReEmpFromDate").disabled=false;
	 	document.getElementById("imgReEmpFromDate").disabled=false;
	 	document.getElementById("txtReEmpToDate").disabled=false;
	 	document.getElementById("imgReEmpToDate").disabled=false;
	 	document.getElementById("cmbDaInPnsnSalary").disabled=false;
	 	document.getElementById("cmbDaInPnsnSalary").options[2].selected="selected";
	}
	else
	{
		document.getElementById("txtReEmpFromDate").disabled=true;
	 	document.getElementById("imgReEmpFromDate").disabled=true;
	 	document.getElementById("txtReEmpToDate").disabled=true;
	 	document.getElementById("imgReEmpToDate").disabled=true;
	 	document.getElementById("cmbDaInPnsnSalary").disabled=true;
	 	document.getElementById("txtReEmpFromDate").value="";
	 	document.getElementById("txtReEmpToDate").value="";
	 	document.getElementById("cmbDaInPnsnSalary").value='-1';
	}
}

function reEmploymentDtlsTableAddRow()
{		
	Row_ID_Reemp = document.getElementById("hidReEmpDtlsGridSize").value;
	//var newRow =  document.all("tblReempDtls").insertRow();			  		

	var table=document.getElementById("tblReEmpDtls");
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
	var Cell5 = newRow.insertCell(4);
   	Cell5.className = "tds";
   	Cell5.align="center";
   	var Cell6=newRow.insertCell(5);
   	Cell6.className="tds";	
   	Cell6.align="center";

   	Cell1.innerHTML = '<input type="hidden" name="hdnReEmpltId" id="hdnReEmpltId'+Row_ID_Reemp+'"/><input type="text" name="txtReEmpltFromDate" id="txtReEmpltFromDate'+Row_ID_Reemp+'" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtReEmpltFromDate'+Row_ID_Reemp+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Reemp)+');" />';
   	Cell2.innerHTML = '<input type="text" name="txtReEmpltToDate" id="txtReEmpltToDate'+Row_ID_Reemp+'" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtReEmpltToDate'+Row_ID_Reemp+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Reemp)+');" />'; 	
   	Cell3.innerHTML = '<select name="cmbDAInPnsnSal" id="cmbDAInPnsnSal'+Number(Row_ID_Reemp)+'" ><option value="-1">--Select--</option>'+LISTDAIN+'</select>';
   	Cell4.innerHTML = '<input type="text" name="txtEmployeeOrderNo" id="txtEmployeeOrderNo'+Row_ID_Reemp+'" onfocus="onFocus(this)" onblur="onBlur(this);" size="12" maxlength="30"/>';
	Cell5.innerHTML = '<input type="text" name="txtEmployeeOrderDate" id="txtEmployeeOrderDate'+Row_ID_Reemp+'" maxlength="10" size="15" onkeypress="digitFormat(this);dateFormat(this);" onblur="onBlur(this);chkValidDate(this);"/> <img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtEmployeeOrderDate'+Row_ID_Reemp+'\', 375, 570, \'\', \'\', '+Number(Row_ID_Reemp)+');" />';
   	Cell6.innerHTML ='<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveTableRow(this, \'tblReEmpDtls\')" /> '; 	
   		
   	document.getElementById("hidReEmpDtlsGridSize").value = Number(Row_ID_Reemp)+1; 
}

function validateReEmploymentDtls()
{
	 var flag = 0;
	 var prvsReEmplToDate="";
	 var commencementDt = document.getElementById("txtDateOfCommencement").value;
	 var reEmpDtlCntLength=document.getElementById("hidReEmpDtlsGridSize").value;
	 if(document.getElementById('tblReEmpDtls').rows.length > 1)
	 {
		for(var rowCnt=0;rowCnt<Number(reEmpDtlCntLength);rowCnt++)
		{		
			try
			{				 
				if(IsEmptyFun("txtReEmpltFromDate"+rowCnt,REEMPLTFROMDATE,'0')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtReEmpltToDate"+rowCnt,REEMPLTTODATE,'0')==false)
				{
					return false;
				}
				else if(IsEmptyFun("cmbDAInPnsnSal"+rowCnt,DAINPNSNSAL,'0')==false)
				{
					return false;
				}
				
				var reEmpltFromDate=document.getElementById("txtReEmpltFromDate"+rowCnt).value;
				var reEmpltToDate=document.getElementById("txtReEmpltToDate"+rowCnt).value;
				
				
				if(compareDate(commencementDt,reEmpltFromDate) == false)
				{
					alert(REEMPFROMCOMNTDT);
					goToFieldTab("txtReEmpltFromDate"+rowCnt,0);
					return false;
				}
				if(compareDate(reEmpltFromDate,reEmpltToDate) == false)
				{
					alert(REEMPFROMTODATE);
					goToFieldTab("txtReEmpltToDate"+rowCnt,0);
					return false;
				}
				if(flag == 1)
				{
					if(compareDate(prvsReEmplToDate,reEmpltFromDate) == false)
					{
						alert("Re-Employment From Date must be greater than previous re-employment To Date.");
						goToFieldTab("txtReEmpltFromDate"+rowCnt,0);
						return false;
					}
				}
				flag = 1;
				prvsReEmplToDate=document.getElementById("txtReEmpltToDate"+rowCnt).value;
												 
			}
			catch(ex)
			{
				
			}
		}
	 }
	 return true;
}

function upperCasePPONumber(e)
{
	if(e.preventDefault)
	{
		if (parseInt(e.which) >= 97 && parseInt(e.which) <= 122 )
			e.which = parseInt(e.which)-32;
		if(((parseInt(e.which) >32 && parseInt(e.which) <= 47 && parseInt(e.which) != 46 && parseInt(e.which) != 45 ) || (parseInt(e.which) >57 && parseInt(e.which) < 65) || (parseInt(e.which) >90 && parseInt(e.which) < 97) || (parseInt(e.which) >122 && parseInt(e.which) < 127)  || (parseInt(e.which) == 45) || (parseInt(e.which) == 46)))
		{
			e.preventDefault();
		}
	}
	else
	{
		//alert("event.keycode="+event.keyCode);
		if (event.keyCode >= 97 && event.keyCode <= 122 )
			event.keyCode = event.keyCode-32;
		if(((window.event.keyCode >32 && window.event.keyCode <= 47 && window.event.keyCode != 46 && window.event.keyCode != 45 ) || (window.event.keyCode >57 && window.event.keyCode < 65) || (window.event.keyCode >90 && window.event.keyCode < 97) || (window.event.keyCode >122 && window.event.keyCode < 127) || (window.event.keyCode == 45) || (window.event.keyCode == 46)))
		{
			window.event.keyCode = 0;
		}
	}	
}	

function validatePanNumber(object)
{
	var elementId=object.id;
	var panNumberFormat = /^([A-Z a-z]{5})+([0-9]{4})+([A-Z a-z]{1})$/; 
	if(document.getElementById(elementId).value != "")
	{
		if (!(document.getElementById(elementId).value.match(panNumberFormat)))
		{
			alert("PAN number should be in correct format.");
			var elementVal=eval(document.getElementById(elementId));
			elementVal.focus();
			elementVal.select();
			return false ; 
		}
	}
	return true;
}

function onChangeHeight()
{
	var heightInFt=document.getElementById("cmbCmsFtInch").value;
	if(heightInFt == 'Cms')
	{
		document.getElementById("txtHeightInCms").style.display="inline";
		document.getElementById("cmbHeightInFt").style.display="none";
		document.getElementById("cmbHeightInInch").style.display="none";
	}
	else if(heightInFt == 'Feet/Inches')
	{
		document.getElementById("txtHeightInCms").value="";
		document.getElementById("txtHeightInCms").style.display="none";
		document.getElementById("cmbHeightInFt").style.display="inline";
		document.getElementById("cmbHeightInInch").style.display="inline";
	}
	else
	{
		document.getElementById("txtHeightInCms").value="";
		document.getElementById("txtHeightInCms").style.display="none";
		document.getElementById("cmbHeightInFt").style.display="none";
		document.getElementById("cmbHeightInInch").style.display="none";
	}
}

function autoTab(input,len, e) {
	 
var keyCode =  e.keyCode; 
var filter =  [0,8,9,16,17,18,37,38,39,40,46];
if(input.value.length >= len && !containsElement(filter,keyCode)) {
  input.value = input.value.slice(0, len);
  input.form[(getIndex(input)+1) % input.form.length].focus();
}

function containsElement(arr, ele) {
  var found = false, index = 0;
  while(!found && index < arr.length)
  if(arr[index] == ele)
  found = true;
  else
  index++;
  return found;
}

function getIndex(input) {
  var index = -1, i = 0, found = false;
  while (i < input.form.length && index == -1)
  if (input.form[i] == input)index = i;
  else i++;
  return index;
}
return true;
}

function onChangePnsnrType()
{
	if(document.getElementById("cmbPensionerType").value == "Others")
	{
		document.getElementById("txtOtherPnsnrType").style.display = "inline";
	}
	else
	{
		document.getElementById("txtOtherPnsnrType").style.display = "none";
	}
}
function addRowForRemarks()
{
 	var rowCnt = document.getElementById("hidGridSize").value;
 	var newRow =  document.getElementById("tblForRemarks").insertRow(document.getElementById("tblForRemarks").rows.length);	
	var Cell1 = newRow.insertCell(-1);
	Cell1.className = "tds";
   	Cell1.align="center";
	var Cell2 = newRow.insertCell(-1);
	Cell2.className = "tds";
   	Cell2.align="center";
	var Cell3 = newRow.insertCell(-1);
	Cell3.className = "tds";
   	Cell3.align="center";
	var Cell4 = newRow.insertCell(-1);
	Cell4.className = "tds";
   	Cell4.align="center"; 
	
	Cell1.innerHTML = '<input type="text" class="'+Number(rowCnt)+'" name="txtOrderNo" id="txtOrderNo'+Number(rowCnt)+'"   />';																	
	Cell2.innerHTML = '<input type="text" class="'+Number(rowCnt)+'" name="txtOrderDate"  id="txtOrderDate'+Number(rowCnt)+'"    onblur=\"validateDate(this);\" maxlength="10" onkeypress="dateFormat(this);" />&nbsp;<img src="images/CalendarImages/ico-calendar.gif" style="width:16px" onClick="window_open(\'txtOrderDate'+rowCnt+'\', 375, 570, \'\', \'\', '+Number(rowCnt)+');" /> ';  		
	Cell3.innerHTML = '<textarea class="'+Number(rowCnt)+'" name="txtRemarks" id="txtRemarks'+Number(rowCnt)+'"  style="height: 30px;width:250px"  />' ;
	Cell4.innerHTML = '<img name="Image'+Number(rowCnt)+'" id="Image'+Number(rowCnt)+'" src=\'images/CalendarImages/DeleteIcon.gif\' onClick=\'RemoveTableRow(this,"tblForRemarks")\'/>';
	
	document.getElementById("hidGridSize").value = Number(rowCnt)+1;

}
function showPensionCaseRemarks()
{
	var currRole = document.getElementById("hdnCurrRole").value;
	var pensionerCode = document.getElementById("hdnPensionerCode").value;
	var url = "ifms.htm?actionFlag=getPensionCaseRemarks&pensionerCode="+pensionerCode+"&currRole="+currRole;
	var newWindow = null;
   	var height = screen.height - 300;
   	var width = screen.width - 500;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=no,menubar=no,location=no,scrollbars=yes,top=180,left=250";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
function validateRemarksDtls()
{
	var rowCnt = document.getElementById("hidGridSize").value;
	if(document.getElementById('tblForRemarks').rows.length > 1)
	 {
		for(var Cnt=0;Cnt<Number(rowCnt);Cnt++)
		{		
			try
			{				 
				if(IsEmptyFun("txtOrderNo"+Cnt,ORDERNO,'0')==false)
				{
					return false;
				}
				else if(IsEmptyFun("txtOrderDate"+Cnt,ORDERDATE,'0')==false)
				{
					return false;
				}
				else if(IsEmptyPercentage("txtRemarks"+Cnt,REMARKS,'0')==false)
				{
					return false;
				}
			}
			catch(ex)
			{
				
			}
		}
	 }
}
function setRetirementDate(object)
{
	var pensionerType = document.getElementById("cmbPensionerType").value;
	if(pensionerType != '-1' && document.getElementById("cmbPensionType").value != FAMILYPNSN)
	{
		if(pensionerType == 'Group A' || pensionerType == 'Group B' || pensionerType == 'Group C')
		{
			addNoOfYearsInDate(object,"txtDateOfRetirement",58);
		}
		else if(pensionerType == 'Group D' || pensionerType == 'IAS' || pensionerType == 'IFS' || pensionerType == 'IPS')
		{
			addNoOfYearsInDate(object,"txtDateOfRetirement",60);
		}
		else if(pensionerType == 'High Court')
		{
			addNoOfYearsInDate(object,"txtDateOfRetirement",62);
		}
		getNextDate();
	}
}
function daysInMonth(month,year) {
	
	var m = [31,28,31,30,31,30,31,31,30,31,30,31];
	if (month != 2) return m[month - 1];
	if (year%4 != 0) return m[1];
	if (year%100 == 0 && year%400 != 0) return m[1];
	return m[1] + 1;
} 

function addNoOfYearsInDate(object,targetField,noOfYears)
{
	lStrDate = object.value;
	
	var lArrDate = lStrDate.split("/");
	var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
	if(lStrDate == "")
	{
		document.getElementById(targetField).value = (new String(""));
		return;
	}
	date.setFullYear(date.getFullYear()+ Number(noOfYears));
		
	document.getElementById(targetField).value =   (new String(daysInMonth(date.getMonth()+1,date.getFullYear()))) + "/" +(new String(date.getMonth()+1).length > 1 ? date.getMonth()+1 : "0" + Number(date.getMonth()+1))+"/"+date.getFullYear();


		
//	if(lArrDate[0] == 1)
//	{
//		var x;
//		if(lArrDate[1] == 1)
//		{
//			x = DaysArray2(12 ,lArrDate[2]-1);
//			lArrDate[1] = 13;
//			date.setFullYear(date.getFullYear()-1);
//		}
//		else
//		{
//			if(lArrDate[1]-1 == 2 )
//			{
//				x = daysInFebruaryFP(lArrDate[2]);
//			}
//			else
//			{
//				x = DaysArrayFP(date.getMonth());
//			}
//		}
//		
//		document.getElementById(targetField).value =  (new String(x).length > 1 ? x : "0" + new String(x)) + "/" +(new String(lArrDate[1] -1).length > 1 ? lArrDate[1] -1 : "0"+new String(lArrDate[1] -1))+"/"+date.getFullYear();
//
//	}
//	else
//	{
//		document.getElementById(targetField).value = (new String(lArrDate[0]-1).length > 1 ? lArrDate[0]-1 : "0" + new String(lArrDate[0]-1)) + "/" +(new String(lArrDate[1]).length > 1 ? lArrDate[1] : "0" + new String(lArrDate[1]) )+"/"+date.getFullYear();
//	}
}

function onBlurDojAvailable()
{
	if(document.getElementById("cmbDojAvailable").value == 'N' && document.getElementById("cmbPensionType").value == FAMILYPNSN)
	{
		document.getElementById("txtDateofExpiry").focus();
	}
}

function onBlurDoj()
{
	if(document.getElementById("cmbPensionType").value == FAMILYPNSN)
	{
		document.getElementById("txtDateofExpiry").focus();
	}
}

function onBlurDateofExpiry()
{
	var doe = document.getElementById("txtDateofExpiry").value;
	if(doe.length > 0 && document.getElementById("cmbPensionType").value == FAMILYPNSN)
	{
		document.getElementById("txtDateOfRetirement").value = document.getElementById("txtDateofExpiry").value;
		getNextDate();
	}
}