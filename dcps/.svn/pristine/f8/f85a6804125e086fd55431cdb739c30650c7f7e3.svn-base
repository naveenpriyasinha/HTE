emailfilter=/^\w+[\+\.\w-]*@([\w-]+\.)*\w+[\w-]*\.([a-z]{2,4}|\d+)$/i
flagForSaveAndNew = false;
DcrgDiffAmt = parseInt(0);
globArray = new Array(12);
disableArray = new Array();
accResult = null;

function checkmail(e)
{
	if(e.value.length > 0)
	{
		var returnval=emailfilter.test(e.value)
		if (returnval==false)
		{
			alert(VALIDEMAILADDRESS)
			e.value = "";
		}
		return returnval
	}
}
//New Method added by Soumya
function chkIfValid()
{
   var iChars = "!@#$%^&*()+=-[]\\\';,./{}|\":<>?~_"; 
   
   var str = document.getElementById("txtPanNo").value;
   for (var i = 0; i < str.length; i++) 
   {
	  	if (iChars.indexOf(str.charAt(i)) != -1) 
	  	{
	  	  	alert ("PAN Number contains special character which is not allowed.");
	  	  	document.getElementById("txtPanNo").value = "";
	  	  	document.getElementById("txtPanNo").focus();
	  		return false;
	  	}
   }
}
function amountFormat(lThis)
{
	if(window.event.keyCode == 46)
	{
		var lStr = new String(lThis.value);
		lStr = lStr.trim();
		if(lStr.indexOf('.') != -1)
		{
			window.event.keyCode = 0;
		}
	}
	else if(!(window.event.keyCode > 47 && window.event.keyCode < 58))
	{
		window.event.keyCode = 0;
	}
}
function validPensionCaseData()
	{
		if(document.getElementById("hidCaseForPSBAdmin").value != 'Y')
		{
			if(! chk1StTabValidation() || ! chk2NdTabValidation() || !chkFamilyDtls() || dateValidationForFamilyMembers() == false)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else
		{
			if(document.getElementById("txtbranchCode").value.length > 0)
			{
				if(document.getElementById("txtACNo").value.length > 0)
				{
					return true;
				}
				else
				{
					alert("Please Enter Account Number ");
					goToFieldTab("txtACNo",1);
					return false;
				}
			}
			else
			{
				alert("Please Enter Branch Code");
				goToFieldTab("txtbranchCode",1);
				return false;
			}
		}

	}
	function chkFamilyDtls()
	{
	    var fElement = document.getElementsByName("txtFmName");
	    var FMPercent = document.getElementsByName("txtFMPercent");
	    var FMDOD = document.getElementsByName("txtFMDateOfDeath");
	    var sum = 0;
		var flag = 0;
		var deadFlag = 0;
		for(var i=0;i<fElement.length;i++)
		{
			if(FMPercent[i].value.length > 0&&fElement[i].value.length>0)
			{
				sum = Number(sum)+Number(FMPercent[i].value);
				flag = 1;
				if(FMDOD[i].value.length>0)
				{
					deadFlag = Number(deadFlag)+1;
				}
			}
		}
		if(flag == 1 &&  sum != 100 && deadFlag != fElement.length)
		{
			document.getElementById("txtFMPercent0").value = "";
			alert(SUM_FM_PER);
			goToFieldTab("txtFMPercent0",1);
			return false;
		}
		var nElement = document.getElementsByName("txtNMPercentage");
		var nNameElement = document.getElementsByName("txtNMName");
		var sum2 = 0;
		var nFlag = 0;
		for(var i=0;i<nElement.length;i++)
		{
			if(nNameElement[i].value.length > 0)
			{
				sum2 = Number(sum2) + Number(nElement[i].value);
				nFlag = 1;
			}
		}
		if(nFlag == 1 && sum2 != 100)
		{
			alert(SUM_NM_PER);
			document.getElementById("txtNMPercentage0").value = "";
			goToFieldTab("txtNMPercentage0",1);
			return false;
		}
		var handicap = document.getElementsByName("chkbxHandiCap");
		var gurdName = document.getElementsByName("txtFMGardianName");
		var minor = document.getElementsByName("chkbxFMMajorMinor");
		for(var i=0 ;i<handicap.length;i++)
		{
			if(fElement[i].value.length > 0)
			{
				//var minor = document.getElementById("chkbxFMMajorMinor"+i);
				if('M' == handicap[i].value || minor[i].checked )
				{
					if(gurdName[i].value == '' || gurdName[i].value.length == 0 )
					{
						alert(GURDIANNAME);
						goToTab(1);
						gurdName[i].focus();
						return false;
					}
				}
			}
		}
		return true;
	}

	function dateValidationForFamilyMembers()
	{
		var fmRelation = document.getElementsByName("cmbRelation");
		var fElement = document.getElementsByName("txtFmName");
		var FMPercent = document.getElementsByName("txtFMPercent");

		var fmDOB = document.getElementsByName("txtFMDateOfBirth");
		var fmDOD = document.getElementsByName("txtFMDateOfDeath");

		var PnsnrDOB = document.getElementById("txtDateOfBirth").value;
		for(var i=0;i<fmRelation.length;i++)
		{
			if(fElement[i].value.length>0)
			{
				if(fmDOB[i].value.length>0 && fmDOD[i].value.length>0)
				{
					if(isProper1(fmDOB[i].value,fmDOD[i].value) == false)
					{
						alert("Family Member Date of Death Should be Greater than His/Her Date of Birth)");
						fmDOD[i].value = "";
						goToTab(1);
						fmDOD[i].focus();
						return false;
					}
				}
				if(fmRelation[i].value == '-1')
				{
					alert("Please Select Family Member Relation");
					goToTab(1);
					fmRelation[i].focus();
					return false;
				}
				else
				{
					if(PnsnrDOB.length>0 && fmDOB[i].value.length>0)
					{
						if(fmRelation[i].value == 'Father' || fmRelation[i].value == 'Mother' )
						{
							if(isProper1(fmDOB[i].value,PnsnrDOB) == false)
							{
								alert("Family Member ("+fmRelation[i].value+") Date of Birth Should be Greater than Pensioner Date of Birth");
								goToTab(1);
								fmDOB[i].value = "";
								fmDOB[i].focus();
								return false;
							}
						}
						if(fmRelation[i].value == 'Son' || fmRelation[i].value == 'Daughter' )
						{
							if(isProper1(PnsnrDOB,fmDOB[i].value) == false)
							{
								alert("Family Member ("+fmRelation[i].value+") Date of Birth Should be Less than Pensioner Date of Birth");
								goToTab(1);
								fmDOB[i].value = "";
								fmDOB[i].focus();
								return false;
							}
						}
					}
				}
			}
		}
	}


	function chkDigitAmnt(lvalue)
	{
		if(lvalue.value > 100)
		{
			alert(VALID_VAL);
			lvalue.focus();
			lvalue.value = "";
		}
	}
	function getADPAmountByAjax()
	{
		var uri = "";
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
			alert (AL_AJAX);
			return;
		}
		uri = "ifms.htm?actionFlag=getHeadDesc";
		uri = uri+"&BasicPension="+document.getElementById("txtBasicPensionAmnt").value+"&isForAdp=Y";
		if((document.getElementById("Rop2006chkBx").checked ) && (document.getElementById("ROP2006A").checked  || document.getElementById("ROP2006P").checked))
		{
			uri = uri+"&ROP2006=Y";
		}
		if((document.getElementById("Rop2006chkBx").checked ) && (document.getElementById("ROP2006A").checked  || document.getElementById("ROP2006P").checked))
		{
			uri = uri+'&txtDateOfBirth='+document.getElementById("txtDateOfBirth").value+'&txtBasicPensionAmnt='+document.getElementById("txtBasicPensionAmnt").value;
		   if(document.getElementById("txtDateOfDeath").value.length>0)
		   {
	      	   var tempPrcnt = document.getElementsByName("txtFMPercent");
			   var tempFMDob = document.getElementsByName("txtFMDateOfBirth");
			   uri = uri+'&txtDateOfDeath='+document.getElementById("txtDateOfDeath").value;
			   for(var i=0;i<tempPrcnt.length;i++)
			   {
			   		if(tempPrcnt[i].value == 100)
			   		{
			   			uri = uri+'&FMDob='+tempFMDob[i].value;
			   		}
			   }
			   	if(document.getElementById("txtFp1Date").value.length>0&&Number(document.getElementById("txtFp1Amnt").value)>0 && isProper1(document.getElementById("hidCurDate").value,document.getElementById("txtFp1Date").value) == true)
			    {
			   		uri = uri+'&FPAmount='+document.getElementById("txtFp1Amnt").value;
			    }
			    else if(document.getElementById("txtFp2Date").value.length>0&&Number(document.getElementById("txtFp2Amnt").value)>0 && isProper1(document.getElementById("hidCurDate").value,document.getElementById("txtFp2Date").value) == false)
			    {
			    	uri = uri+'&FPAmount='+document.getElementById("txtFp2Amnt").value;
			    }
		   }
		}
		xmlHttp.onreadystatechange=function ()
		{
			if (xmlHttp.readyState==complete_state)
			{
				 XMLDoc = xmlHttp.responseXML.documentElement;
				var hiddenValues = XMLDoc.getElementsByTagName('ADP');
				 if(hiddenValues != null && hiddenValues[0] != null)
				 {
				 	document.getElementById("txtADPAmount").value = hiddenValues[0].childNodes[0].text;
				 }
				 else
				 {
				 	document.getElementById("txtADPAmount").value = "0.00";
				 }
				 //calculateRedcdPen2();
				 //calulateReducedPension();
			}
		}
		xmlHttp.open("POST",uri,true);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(uri);
	}
	function changeHeadDesc()
	{
		document.getElementById("txtTiPrct").value = "";
		document.getElementById("txtMaAmnt").value = "";
		if(document.getElementById("cmbHeadCode").value == "-1")
		{
			document.getElementById("cmbHeadCodeDesc").value = "";
		}
		else
		{
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null)
			{
				alert (AL_AJAX);
				return;
			}
			var uri = "";
			uri = "ifms.htm?actionFlag=getHeadDesc&HeadCode="+document.getElementById("cmbHeadCode").value+"&isRop="+document.getElementById("cmbRop").value;
			uri = uri+"&BasicPension="+document.getElementById("txtBasicPensionAmnt").value+"&isFromCase=Y";

			if(document.getElementById("Rop1986chkBx").checked)
			{
				uri = uri+"&ROP1986=Y";
			}
			if(document.getElementById("Rop1996chkBx").checked)
			{
				uri = uri+"&ROP1996=Y";
			}
			if((document.getElementById("Rop2006chkBx").checked ) && (/*document.getElementById("ROP2006A").checked  ||*/ document.getElementById("ROP2006P").checked))
			{
				uri = uri+"&ROP2006=Y";
			}
			if(document.getElementById("radiosplCaseY").checked)
			{
				uri = uri+"&SpecialCase=Y";
			}
			else if(document.getElementById("radiosplCaseN").checked)
			{
				uri = uri+"&SpecialCase=N";
			}
			uri = uri+"&ComnDate="+document.getElementById("txtCommensionDate").value;
			if(document.getElementById("CmbCalcType").value == 'M')
			{
				uri = "ifms.htm?actionFlag=getHeadDesc&HeadCode="+document.getElementById("cmbHeadCode").value;
			}
			if((document.getElementById("Rop2006chkBx").checked ) && (document.getElementById("ROP2006A").checked  || document.getElementById("ROP2006P").checked))
			{
				uri = uri+'&txtDateOfBirth='+document.getElementById("txtDateOfBirth").value+'&txtBasicPensionAmnt='+document.getElementById("txtBasicPensionAmnt").value;
			   if(document.getElementById("txtDateOfDeath").value.length>0)
			   {
		      	   var tempPrcnt = document.getElementsByName("txtFMPercent");
				   var tempFMDob = document.getElementsByName("txtFMDateOfBirth");
				   uri = uri+'&txtDateOfDeath='+document.getElementById("txtDateOfDeath").value;
				   for(var i=0;i<tempPrcnt.length;i++)
				   {
				   		if(tempPrcnt[i].value == 100)
				   		{
				   			uri = uri+'&FMDob='+tempFMDob[i].value;
				   		}
				   }
				   	if(document.getElementById("txtFp1Date").value.length>0&&Number(document.getElementById("txtFp1Amnt").value)>0 && isProper1(document.getElementById("hidCurDate").value,document.getElementById("txtFp1Date").value) == true)
				    {
				   		uri = uri+'&FPAmount='+document.getElementById("txtFp1Amnt").value;
				    }
				    else if(document.getElementById("txtFp2Date").value.length>0&&Number(document.getElementById("txtFp2Amnt").value)>0 && isProper1(document.getElementById("hidCurDate").value,document.getElementById("txtFp2Date").value) == false)
				    {
				    	uri = uri+'&FPAmount='+document.getElementById("txtFp2Amnt").value;
				    }
			   }
			}
			xmlHttp.onreadystatechange=getHeadDesc;
			xmlHttp.open("POST",uri,true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send(uri);
		}
	}
	function getHeadDesc()
	{
		if (xmlHttp.readyState==complete_state)
		{
			 XMLDoc = xmlHttp.responseXML.documentElement;
			 var XmlHiddenValues;
			 if(XMLDoc != null && XMLDoc.getElementsByTagName('XMLDOC') != null)
			 {
			 	XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			 }
			 if(XmlHiddenValues != null && XmlHiddenValues[0] != null)
			 {
			 	 document.getElementById("cmbHeadCodeDesc").value = XmlHiddenValues[0].childNodes[0].text.replace('~','&');
				 if( document.getElementById("hidDcrg"))
				 {
				 	 document.getElementById("hidDcrg").value =   XmlHiddenValues[0].childNodes[1].text;
				 }
				 if(document.getElementById("hidPension"))
				 {
				 	 document.getElementById("hidPension").value =  XmlHiddenValues[0].childNodes[2].text;
				 }
				 if(document.getElementById("hidCVP"))
				 {
				 	 document.getElementById("hidCVP").value  =  XmlHiddenValues[0].childNodes[3].text;
				 }
				 if(document.getElementById("cmbHeadCode").value == 16 || document.getElementById("cmbHeadCode").value == 17 || document.getElementById("cmbHeadCode").value == 18 ||document.getElementById("cmbHeadCode").value == 19)
				 {
				 	if(XmlHiddenValues[0].childNodes[5] != null)
					 {
					 		document.getElementById("txtBasicPensionAmnt").value = XmlHiddenValues[0].childNodes[5].text;
					 }
					 setValidAmountFormat(document.getElementById("txtBasicPensionAmnt"));

					  if(XmlHiddenValues[0].childNodes[6] != null)
					 {
					 	document.getElementById("hidMaAmt").value = XmlHiddenValues[0].childNodes[6].text;
						if(document.getElementById("radioMaY").checked && document.getElementById("hidMaAmt").value >= 0 )
						{
							document.getElementById("txtMaAmnt").value = document.getElementById("hidMaAmt").value;
						}
					 }
				 }
				 else
				 {
				 	 if(XmlHiddenValues[0].childNodes[4] != null)
					 {
				 		document.getElementById("txtTiPrct").value = XmlHiddenValues[0].childNodes[4].text;
					 }
					 var tiAmnt = 0;
					 if(XmlHiddenValues[0].childNodes[5] != null)
					 {
					 	tiAmnt = XmlHiddenValues[0].childNodes[5].text;
					 }
					 setTiMinAmt(tiAmnt);
		  		     if(XmlHiddenValues[0].childNodes[6] != null)
					 {
					 	document.getElementById("hidMaAmt").value = XmlHiddenValues[0].childNodes[6].text;
						if(document.getElementById("radioMaY").checked && document.getElementById("hidMaAmt").value >= 0 )
						{
							document.getElementById("txtMaAmnt").value = document.getElementById("hidMaAmt").value;
						}
					 }
					 if(XmlHiddenValues[0].childNodes[7] != null)
					 {
					 	document.getElementById("txtDpPrct").value = XmlHiddenValues[0].childNodes[7].text;
					 	setBasicPension();
					 }
					 else
					 {
					 	setBasicPension();
					 }
					 if(XmlHiddenValues[0].childNodes[8] != null && XmlHiddenValues[0].childNodes[9] != null)
					 {
					 	if(document.getElementById("cmbRop").value == 'N')
					 	{
					 		if(Number(setIRAmnt(XmlHiddenValues[0].childNodes[8].text)) > Number(XmlHiddenValues[0].childNodes[9].text) )
						 	{
						 		document.getElementById("txtIr").value = Number(setIRAmnt(XmlHiddenValues[0].childNodes[8].text)) ;
						 	}
						 	else
						 	{
						 		document.getElementById("txtIr").value = Number(XmlHiddenValues[0].childNodes[9].text);
						 	}
					 	}
					 	else
					 	{
					 		document.getElementById("txtIr").value = "";
					 	}
					 	setValidAmountFormat(document.getElementById("txtIr"));
					 }
					 else if (XmlHiddenValues[0].childNodes[9] != null)
					 {
					 	document.getElementById("txtIr").value = Number(XmlHiddenValues[0].childNodes[9].text);
					 	setValidAmountFormat(document.getElementById("txtIr"));
					 }
					 else if (XmlHiddenValues[0].childNodes[8] != null)
					 {
					 	document.getElementById("txtIr").value = Number(setIRAmnt(XmlHiddenValues[0].childNodes[8].text));
					 	setValidAmountFormat(document.getElementById("txtIr"));
					 }
				 }
			 }
			 var hiddenValues = XMLDoc.getElementsByTagName('ADP');
			 if(hiddenValues != null && hiddenValues[0] != null)
			 {
			 	document.getElementById("txtADPAmount").value = hiddenValues[0].childNodes[0].text;
			 }
			 else
			 {
			 	document.getElementById("txtADPAmount").value = "0.00";
			 }
			 calculateRedcdPen2();
			 calulateReducedPension();
		}
	}
	function setIRAmnt(lStr)
	{
		var BasicAmnt = document.getElementById("txtBasicPensionAmnt").value;
		var DpAmnt = document.getElementById("txtDpAmnt").value;
		var x = Number(BasicAmnt)+Number(DpAmnt);
		var IrAmt = Number(x)*Number(lStr)/100;
		return IrAmt;
	}
	function setTiMinAmt(tiAmt)
	{
		setTIAmount();
		if(Number(document.getElementById("txtTiAmnt").value) > Number(tiAmt))
		{
			document.getElementById("txtTiAmnt").value = Number(document.getElementById("txtTiAmnt").value);
		}
		else
		{
			document.getElementById("txtTiAmnt").value = Number(tiAmt);
		}
		
		if(document.getElementById("Rop2006chkBx").checked)
		{
			document.getElementById("txtTiAmnt").value = Math.ceil(document.getElementById("txtTiAmnt").value);
		}
		else if(document.getElementById("Rop1996chkBx").checked)
		{
			document.getElementById("txtTiAmnt").value = Math.round(document.getElementById("txtTiAmnt").value);
		}
		
		//document.getElementById("txtTiAmnt").value = Math.round(document.getElementById("txtTiAmnt").value);
		setValidAmountFormat(document.getElementById("txtTiAmnt"));
	}
	function chk1StTabValidation()
	{
		var status = document.getElementById("CmbpensionClass").value;
		if(IsEmptyFun("CmbpensionClass",PNSN_CLASS,'0') == false)
		{
			return false;
		}
		if(status != 'P')
		{
			if(IsEmptyFun("cmbPnsnType",PNSNTYPE,'0') == false)
			{
				return false;
			}
		}

		if(IsEmptyFun("txtPpoNo",PPONO,'0') == false)
		{
			return false;
		}
		/*if(IsEmptyFun("txtPpoDate",PPODATE,'0') == false)
		{
			return false;
		}*/
		if(status != 'P')
		{
			if(IsEmptyFun("cmbHeadCode",HEADCODE,'0') == false)
			{
				return false;
			}
		}
		if(status != 'P')
		{
			if(IsEmptyFun("cmbPnsnStatus",PNSNSTATUS,'0') == false)
			{
				return false;
			}
			if(document.getElementById("cmbPnsnStatus").value == "With Held")
			{
				if(IsEmptyFun("txtWHDate",ENTRWITHHELDDATE,'0') == false)
				{
					return false;
				}
				if(IsEmptyFun("txtWHReason",ENTRWITHHELDREASON,'0') == false)
				{
					return false;
				}
			}
		}
		if(document.getElementById("cmbPnsnStatus").value == 'ContinueTransfer')
		{
			if(IsEmptyFun("cmbStatusTresContinue",'Please Select Transfer Treasury','0') == false)
			{
				return false;
			}
		}
		if(document.getElementById("cmbPnsnStatus").value == 'CloseTransfer')
		{
			if(IsEmptyFun("cmbStatusTresClose",'Please Select Transfer Treasury','0') == false)
			{
				return false;
			}
		}
		/*if(IsEmptyFun("prefixName",'Please Select Prefix of Pensioner','0') == false)
		{
			return false;
		}*/
		if(IsEmptyFun("txtFirstName",FISRTNAME,'0') == false)
		{
			return false;
		}
		if(IsEmptyFun("txtLastName",LASTNAME,'0') == false)
		{
			return false;
		}
		if(! (document.getElementById("radioGenderM").checked || document.getElementById("radioGenderF").checked ))
		{
			alert(SELECTGNDR);
			goToFieldTab('radioGender',0);
			return false
		}
		/*if(status != 'P')
		{
			if(IsEmptyFun("txtDateOfBirth",DOB,'0') == false)
			{
				return false;
			}
		}
		if(status != 'P')
		{
			if(IsEmptyFun("txtDateOfJoin",DOJ,'0') == false)
			{
				//return false;
			}
		}*/
		if(status != 'P')
		{
			if(IsEmptyFun("txtDateOfRetirement",DOR,'0') == false)
			{
				return false;
			}
		}
		if(document.getElementById("cmbPnsnType").value == 'Family')
		{
			if(IsEmptyFun("txtDateOfDeath",ENTRDEATHDATE,'0') == false)
			{
				return false;
			}
		}
		if(document.getElementById("cmbPnsnType").value == 'Family (Lost)')
		{
			if(IsEmptyFun("txtDateOfDeath",ENTRFIRDATE,'0') == false)
			{
				return false;
			}
		}
		if(IsEmptyFun("txtCommensionDate",CMN_CMNCMNTDT,'0') == false)
		{
			return false;
		}
		//if(status != 'P')
		//{
			/* if(IsEmptyFun("txtPayScale",PAYSCL,'0') == false)
			{
				return false;
			}
			if(IsEmptyFun("txtLastPay",LASTPAY,'0') == false)
			{
				return false;
			}*/
			if(document.getElementById("radioSeenY").checked == true)
			{
				if(IsEmptyFun("txtSeenDate",ENTRSEENDATE,'0') == false)
				{
					return false;
				}
			}
		//}
		if(document.getElementById("OMRCol2").style.display != 'none')
		{

			if(IsEmptyFun("cmbOmrType",SELECTOMRTYPE,'0') == false)
			{
				//return false;
			}
		}

		if(document.getElementById("radioCVPY").checked)
		{
			if(IsEmptyFun("txtCvpAmnt",ENTRCVPAMNT,'0') == false)
			{
				return false;
			}
			if(IsEmptyFun("txtCvpDate",ENTRCVPDATE,'0') == false)
			{
				return false;
			}
			if(IsEmptyFun("txtCvpRestorianDate",ENTRCVPRSTRDATE,'0') == false)
			{
				return false;
			}
		}
		if(document.getElementById("radioDCRGY").checked)
		{
			if(IsEmptyFun("txtDcrgAmnt",ENTRDCRGAMT,'0') == false)
			{
				return false;
			}
			if(IsEmptyFun("txtDcrgDate",ENTRDCRGDATE,'0') == false)
			{
				return false;
			}
		}
		if(status == 'P')
		{
			if(document.getElementById("radioAliveN").checked == true)
			{
				if(IsEmptyFun("txtDateOfDeath",CMN_DTHDT,0) == false)
				{
					return false;
				}
			}
		}

		if(isProper1(document.getElementById("hidCurDate").value,document.getElementById("txtCommensionDate").value) == true)
		{
			if(IsEmptyFun("txtCVPEffectMM","Please Enter CVP Effect From Month",0) == false || IsEmptyFun("txtCVPEffectYYYY","Please Enter CVP Effect From Year",0) == false)
			{
				return false;
			}
		}
		if(document.getElementById("CmbCalcType").value == 'A' && document.getElementById("CmbMrCase").value !=  'Y')
		{
			if(IsEmptyFun("cmbRop",ROPCMBSELECT,'0') == false)
			{
				return false;
			}
		}
		/*if(document.getElementById("CmbMrCase").value != 'Y')
		{
			if(status != 'P')
			{
				if(document.getElementById("txtReducedPnsnAmnt").value == "" || document.getElementById("txtReducedPnsnAmnt").value.trim() < 0)
				{
					alert(RED_AMT);
					goToFieldTab("txtPensionableAmnt",0);
					return false;
				}
			}
			if(document.getElementById("txtBasicPensionAmnt").value == "" || document.getElementById("txtBasicPensionAmnt").value.trim()<0 )
			{
				alert(PENS_AMT);
				goToFieldTab("txtBasicPensionAmnt",0);
				return false;
			}
		}*/
		if(document.getElementById("CmbMrCase").value != 'Y')
		{
			if((document.getElementById("txtFp1Amnt").value != '0.00' && document.getElementById("txtFp1Amnt").value.length > 0) || document.getElementById("txtFp1Date").value.length > 0)
			{
				if(IsEmptyFun("txtFp1Amnt",FP1AMT,0) == false)
				{
					return false;
				}
				if(IsEmptyFun("txtFp1Date",FP1DT,0) == false)
				{
					return false;
				}
			}
			if((document.getElementById("txtFp2Amnt").value != '0.00' && document.getElementById("txtFp2Amnt").value.length > 0) || document.getElementById("txtFp2Date").value.length > 0)
			{
				if(IsEmptyFun("txtFp2Amnt",FP2AMT,0) == false)
				{
					return false;
				}
				if(IsEmptyFun("txtFp2Date",FP2DT,0) == false)
				{
					return false;
				}
			}
		}
		if(document.getElementById("cmbFPPension"))
		{
			if(IsEmptyFun("cmbFPPension","Please Select First Pension Payment Mode",0) == false)
			{
				return false;
			}
		}
		if(document.getElementById("txtTotalServiceDD").value.length == 1 )
		{
			document.getElementById("txtTotalServiceDD").value = new String("0"+document.getElementById("txtTotalServiceDD").value);
		} else if(document.getElementById("txtTotalServiceDD").value.length == 0)
		{
			document.getElementById("txtTotalServiceDD").value = new String("0"+"0");
		}
		if(document.getElementById("txtTotalServiceMM").value.length == 1 )
		{
			document.getElementById("txtTotalServiceMM").value = new String("0"+document.getElementById("txtTotalServiceMM").value);
		} else if(document.getElementById("txtTotalServiceMM").value.length == 0)
		{
			document.getElementById("txtTotalServiceMM").value = new String("0"+"0");
		}
		if(document.getElementById("txtTotalServiceYY").value.length == 1 )
		{
			document.getElementById("txtTotalServiceYY").value = new String("0"+document.getElementById("txtTotalServiceYY").value);
		} else if(document.getElementById("txtTotalServiceYY").value.length == 0)
		{
			document.getElementById("txtTotalServiceYY").value = new String("0"+"0");
		}
		document.getElementById("hidTotSrvc").value=new String(document.getElementById("txtTotalServiceDD").value+document.getElementById("txtTotalServiceMM").value+document.getElementById("txtTotalServiceYY").value)
		return true;
	}

	function chk2NdTabValidation()
	{
		var status = document.getElementById("CmbpensionClass").value;
		if(document.getElementById("cmbPwrAtrny").value == "Y")
		{
			/*if(IsEmptyFun("txtbranchCode","Please Enter Branch Code",'1') == false)
			{
				return false;
			}
			if(IsEmptyFun("cmbBank","Please Select a Bank",'1') == false)
			{
				return false;
			}
			if(IsEmptyFun("cmbBranch","Please Select a Branch",'1') == false)
			{
				return false;
			}*/
			if(document.getElementById("txtbranchCode").value.length <= 0 && (document.getElementById("cmbBank").value == "-1" || document.getElementById("cmbBranch").value == "-1" ))
			{
				alert("Please Enter Bank Details.");
				goToFieldTab("txtbranchCode",1);
				return false;
			}
		}

		if(status == 'P')
		{
			if(IsEmptyFun("txtAddress",ENTRPNSNRADDRESS,'1') == false)
			{
				return false;
			}
		}
		if(status != 'P')
		{
			if(IsEmptyFun("cmbTreasury",TRESURY,'1') == false)
			{
				return false;
			}
			if(IsEmptyFun("txtOffAddress",OFFADDR,'1') == false)
			{
				return false;
			}
			if(IsEmptyFun("cmbDesignation",DESIG,'1') == false)
			{
				return false;
			}
//			if(IsEmptyFun("cmbDepartment",DEPT,'1') == false)
//			{
//				return false;
//			}
//			if(IsEmptyFun("cmbClass",CLASS,'1') == false)
//			{
//				return false;
//			}
			if(IsEmptyFun("cmbCadre",CADRE,'1') == false)
			{
				return false;
			}
		}
		if(document.getElementById("cmbBank").value != '-1' && document.getElementById("cmbBranch").value != '-1')
		{
			if(document.getElementById("txtACNo").value.length == 0 || document.getElementById("txtACNo").value == "" )
			{
				alert(ACC_NO);
				goToFieldTab("txtACNo",1);
				return false;
			}
		}
		if(document.getElementById("CmbFrom22").value == 'Y')
		{
			if(IsEmptyFun("txtform22Auth",CMN_FRM22AUTH,'1') == false)
			{
				return false;
			}
//			if(IsEmptyFun("txtFrm22Date",CMN_FRM22DT,'1') == false)
//			{
//				return false;
//			}
		}
		if( document.getElementById("CmbLPC").value == 'Y')
		{

			if(IsEmptyFun("txtLPCAuth",CMN_LPCAUTH,'1') == false)
			{
				return false;
			}
//			if(IsEmptyFun("txtLPCDate",CMN_LPCDT,'1') == false)
//			{
//				return false;
//			}
		}
		return true;
	}
	function IsEmptyFun(varStr,alrtStr,tabNo)
	{
		var element = document.getElementById(varStr).value;
		var lStr = new String(element);
		element = lStr.trim();
		if( element == "" || element.length == 0 || element == "-1" || element == "0.00")
		{
			alert(alrtStr);
			goToFieldTab(varStr,tabNo);
			return false;
		}
	}

	function IsEmpty(aTextField)
   	{
	  	 if ( aTextField.value==null ||aTextField.value.trim().length==0)
	  	 {
	     	 return true;
	  	 }
	  	 else
	  	  {
	  	  	return false;
	  	  }
	}
	// AJAX For Validate PPO NO. check ...... Start.....

	function validatePPONo(lvalue)
	{
		if( document.getElementById("hidCaseId").value == ""  ||  document.getElementById("hidIsConvertSave").value == 'Y' )
		{
			if(lvalue.value != ' ')
			{
				PnsnBillToken = lvalue.value;
				getPPONoValidByAJAX(lvalue);
			}
			else if(lvalue.value == '')
			{
				document.getElementById("txtPpoNo").value = "";
			}
		}
	}

	function getPPONoValidByAJAX(PPONo)
	{
		if(PPONo.length > 0)
        {
			if(PPONo.search("--") >= 0)
	        {
				alert("You Cant enter -- in PPONubmer");
				document.getElementById("txtPpoNo").value = "";
				getPreFixCmb();
				checkOtherAuth();
				goToFieldTab("txtPpoNo",0);
	        }
	        else
	        {
	        	req = createXMLHttpRequest();
				if(req != null)
				{
					var baseUrl = "ifms.htm?actionFlag=validatePPONo&PPONo="+PPONo;
					req.open("post", baseUrl, true);
					req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					req.onreadystatechange = responsePPONoValid;
					req.send(baseUrl);
				}
				else
				{
					alert (AL_AJAX);
				}
	        }
        }

	}

	function responsePPONoValid()
	{
		if(req.readyState==complete_state)
		{
			var XMLDoc = req.responseXML.documentElement;
			var XMLEntries = XMLDoc.getElementsByTagName("PPONO");

			var blnResult = XMLEntries[0].text;

			if(blnResult == 'true'){
				alert(USED_PPO);
				document.getElementById("txtPpoNo").value = "";
				document.getElementById("txtPpoNo").focus();
			}
		}
	}
	function isProperDate(varStr,altStr)
	{
		var curdate = document.getElementById("hidCurDate").value;
			SRCH_PROYR = altStr+" "+curdate;
			SRCH_PROMNTH = altStr+" "+curdate;
			SRCH_PRODAY = altStr+" "+curdate;
			if(isProper(varStr.value,curdate,SRCH_PROMNTH) == false)
			{
				varStr.value=""
				varStr.focus();
			}
	}

// AJAX For Validate PPO NO. check ...... End......
	function chkForExistingPPO()
	{
		 if(document.getElementById("txtPpoNo").disabled == false)
		  {
		  	 validatePPONo(this.value);
		  }
		  else
		  {
		  	return true;
		  }
	}

	function  saveData123()
	{
		var FPPayType = "";
	  	if(document.getElementById("cmbFPPension"))
	  	{
	  		FPPayType = document.getElementById("cmbFPPension").value;
	  	}
	  	var AlertString = "";
		if(FPPayType == "Y" )
	  	{
			AlertString = "Do You want include First Pension Payemnt In Motnhly Pension";
	  	}
		else
	  	{
	  		AlertString = "";
	  	}
  		if(funPensionBillConfirm(AlertString) == true &&validPensionCaseData() == true && compareWithCommencementDate(null) != false && compareBasicAndFPAmounts()!= false && compareDatesForMingap() != false)
        {
  			//Soumya New Code for Supp
           		var nommArr = document.getElementsByName("txtNMName");
           		var nommBankArr = document.getElementsByName("cmbBankNom");
           		var nommBranchArr = document.getElementsByName("cmbBranchNom");
           		var nommAccNoArr = document.getElementsByName("txtACNoNom");

           		if(document.getElementById("cmbCVPPayMode").value == "S")
           		{
           			if(document.getElementById("txtDateOfDeath").value != "")
           			{
					    var FMPercent = document.getElementsByName("txtFMPercent");
					    var FMDeath = document.getElementsByName("txtFMDateOfDeath");
					    
						for(var i=0;i<FMPercent.length;i++)
						{
							if(FMPercent[i].value == 100 && FMDeath[i].value.length > 0)
							{
								for(var i=0;i<nommArr.length;i++)
		           				{
		           					/*if(nommArr[i].value == "")
		           					{
		           						alert("Please enter Nominee Details");
		           						return false;
		           					}
		           					else*/ 
		           					if((nommBankArr[i].value == "-1" || nommBranchArr[i].value == "-1" || nommAccNoArr[i].value == "") && nommArr[i].value.length>0)
		           					{
		           						alert("Please enter Bank Details (Bank , Branch and Account No) of the Nominee");
		           						return false;
		           					}
		           				}
							}
						}
						
           				
           			}
           		}
           		//added By $oumya
				if(document.getElementById('txtLinkedPpoNo').value != null && document.getElementById('txtLinkedPpoNo').value != '')
				{
					alert(document.getElementById("hidPPONo").value+" is linked with "+document.getElementById('txtLinkedPpoNo').value);
				}
				//added By $oumya

		        var x = document.getElementById("hidInwardStauts").value;
		        if(document.getElementById("hidCaseId").value == "" )
	        	{
	 		  	  var pensionerCode = document.getElementById("hidpensionerCode").value;
	 		   	  var uri = 'ifms.htm?actionFlag=insertPensionCaseDtls&stauts='+x+'&auditFlag='+document.getElementById("auditFlag").value;
	 		  	  validateAccno(uri);
		        }
		       else
		       {
		    		var pensionerCode = document.getElementById("hidpensionerCode").value;
			   		var caseId = document.getElementById("hidCaseId").value;
			   		var pensionerDtlsId = document.getElementById("hidpensionerDtlsId").value;
			   		var uri = 'ifms.htm?actionFlag=insertPensionCaseDtls&stauts='+x+'&PensionCode='+ pensionerCode+'&auditFlag='+document.getElementById("auditFlag").value;
			    	validateAccno(uri);
	       	   }
         }
   	}
	var toOpen = "";
	function saveCase(type)
	{
		toOpen = type ;
		if(document.getElementById("hidCaseId").value == "" )
		{
			if(confirm("Before entering detail system will save this Pension Case."))
			{
				if(validPensionCaseData() == true && compareWithCommencementDate(null) != false && compareBasicAndFPAmounts() != false && compareDatesForMingap() != false)
		     	{
		     		var uri = 'ifms.htm?actionFlag=insertPensionCaseDtls';
					validateAccno(uri);
				}
			}
		}
		else
		{
			openType();
		}
	}

	function openType()
	{
		var pensionerCode = document.getElementById("hidpensionerCode").value;
		var caseId = document.getElementById("hidCaseId").value;
		var ppoNo = document.getElementById("hidPPONo").value;
		var linkedPpoNo = document.getElementById("txtLinkedPpoNo").value;

		if(toOpen == 'arrear')
		{
			var height = screen.height - 100;
	   		var width = screen.width;
			var urlstring = "ifms.htm?actionFlag=getArrearDtls&pnsnarrear_PnsnCode="+pensionerCode+"&pnsnarrear_reqstId="+caseId+'&caseDiable='+document.getElementById("hidCaseDisable").value+"&ppoNo="+ppoNo+"&linkedPpo="+linkedPpoNo;
	    	urlstring = urlstring+"&AuditFlag="+document.getElementById("auditFlag").value;

	    	urlstring = urlstring+"&pensionerDtlsId="+document.getElementById("hidpensionerDtlsId").value;
	    	urlstring = urlstring+"&mstPensionerHdrId="+document.getElementById("hidmstPensionerHdrId").value;
	    	urlstring = urlstring+"&hidCaseFromLevel="+document.getElementById("hidCaseFromLevel").value;


	    	var urlstyle = "height=360,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=160,left=160";
	    	var arrearWindow = null;
	    	arrearWindow = window.open(urlstring, "open", urlstyle);
	    	globArray[0] = arrearWindow;
		}
		if(toOpen == 'pensionCut')
		{
			var urlstring = "ifms.htm?actionFlag=openCutDtlsWindow&buttonType=Pension Cut&pension_request_id="+caseId+"&pensioner_code="+pensionerCode+'&caseDiable='+document.getElementById("hidCaseDisable").value+"&ppoNo="+ppoNo+"&linkedPpo="+linkedPpoNo;
	    	var urlstyle = "height=360,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=Yes,top=160,left=160";
	    	var pnsnCutWindow = null;
	    	pnsnCutWindow = window.open(urlstring, "open", urlstyle);
	    	globArray[1] = pnsnCutWindow;
		}
		if(toOpen == 'OtherBenefit')
		{
			var urlstring = "ifms.htm?actionFlag=openCutDtlsWindow&buttonType=Other Benefit&pension_request_id="+caseId+"&pensioner_code="+pensionerCode+'&caseDiable='+document.getElementById("hidCaseDisable").value+"&ppoNo="+ppoNo+"&linkedPpo="+linkedPpoNo;
	    	var urlstyle = "height=360,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=Yes,top=160,left=160";
	    	var othrBnftWindow = null;
	    	othrBnftWindow = window.open(urlstring, "open", urlstyle);
	    	globArray[2] = othrBnftWindow;
		}
		if(toOpen == 'itCut')
		{
			var urlstring = "ifms.htm?actionFlag=openCutDtlsWindow&buttonType=IT Cut&pension_request_id="+caseId+"&pensioner_code="+pensionerCode+'&caseDiable='+document.getElementById("hidCaseDisable").value+"&ppoNo="+ppoNo+"&linkedPpo="+linkedPpoNo;
	    	var urlstyle = "height=360,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=160,left=160";
	    	var itCutWindow = null;
	    	itCutWindow = window.open(urlstring, "open", urlstyle);
	    	globArray[3] = itCutWindow;
		}
		if(toOpen == 'specialCut')
		{
			var urlstring = "ifms.htm?actionFlag=openCutDtlsWindow&buttonType=Special Cut&pension_request_id="+caseId+"&pensioner_code="+pensionerCode+'&caseDiable='+document.getElementById("hidCaseDisable").value+"&ppoNo="+ppoNo+"&linkedPpo="+linkedPpoNo;
			var urlstyle = "height=360,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=160,left=160";
	    	var splCutWindow = null;
	    	splCutWindow = window.open(urlstring, "open", urlstyle);
	    	globArray[4] = splCutWindow;
		}
		if(toOpen == 'otherPartyPayment')
		{
			var urlstring = "ifms.htm?actionFlag=getOtherPartyPaymentDtls&buttonType=Other Party&pension_request_id="+caseId+"&pensioner_code="+pensionerCode+'&caseDiable='+document.getElementById("hidCaseDisable").value+"&ppoNo="+ppoNo+"&linkedPpo="+linkedPpoNo;
			var urlstyle = "height=360,width=700,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=160,left=160";
	    	var splCutWindow = null;
	    	splCutWindow = window.open(urlstring, "open", urlstyle);
	    	globArray[4] = splCutWindow;
		}
		if(toOpen == 'recovery')
		{
			var recoveryWindow = null;
	    	var x = 0;
	    	var y = 0;
	    	var h = screen.height - 100;
	    	var w = screen.width;
	    	var urlstring = "ifms.htm?actionFlag=openRecoveryPage&pension_request_id="+caseId+"&pensioner_code="+pensionerCode+'&caseDiable='+document.getElementById("hidCaseDisable").value+"&ppoNo="+ppoNo+"&linkedPpo="+linkedPpoNo;
	    	var urlstyle = "height="+ h +",width= "+ w +",toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=" + x + ",left=" + y;
	    	recoveryWindow = window.open(urlstring, "recovery", urlstyle);
	    	globArray[5] = recoveryWindow;
		}
		if(toOpen == 'rivision')
		{
			var audit = document.getElementById("auditFlag").value;
			var headCode = document.getElementById("cmbHeadCode").value;
			var commDate = document.getElementById("txtCommensionDate").value;

			var height = 610;
	  		var width = 870;
			var urlstring = "ifms.htm?actionFlag=createRevision&PPONo="+ppoNo+"&auditFlag="+audit+"&headCode="+headCode+"&commDate="+commDate+'&caseDiable='+document.getElementById("hidCaseDisable").value+'&caseRqstId='+document.getElementById("hidCaseId").value+"&ppoNo="+ppoNo+"&linkedPpo="+linkedPpoNo+"&cmbCVPPayMode="+document.getElementById("cmbCVPPayMode").value+"&cmbDCRGPayMode="+document.getElementById("cmbDCRGPayMode").value;
			var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,top=0,left=0";
		    var revWindow = null;
		   	revWindow = window.open(urlstring, "Revision", urlstyle);
		   	globArray[6] = revWindow;
		}
		//globArray[5].window.close();
		toOpen = "";
	}


// AJAX For Saving Pension Case ......... Start ..........
	var xmlHttp;
	var newURI;
	function savePensionCaseUsingAjx(uri)
	{
			if(document.getElementById("txtPpoNo").disabled == false)
			{
				var PPONo = document.getElementById("txtPpoNo");
				req = createXMLHttpRequest();
				if(req != null)
				{
					var baseUrl = "ifms.htm?actionFlag=validatePPONo&PPONo="+PPONo.value;
					req.open("post", baseUrl, true);
					req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
					req.onreadystatechange = function()
					{
						if(req.readyState==complete_state)
						{
							var XMLDoc = req.responseXML.documentElement;
							var XMLEntries = XMLDoc.getElementsByTagName("PPONO");
							var blnResult = XMLEntries[0].text;
							if(blnResult == 'true')
							{
								alert(USED_PPO);
								document.getElementById("txtPpoNo").value = "";
								document.getElementById("txtPpoNo").focus();
								return false;
							}
							else
							{
								newURI = uri;
								showProgressbar();
								window.setTimeout("HandleAJAXSave()",700);
							}
					    }
				    }
				    req.send(baseUrl);
			    }
			}
			else
			{
				newURI = uri;
				showProgressbar();
				window.setTimeout("HandleAJAXSave()",700);
			}
	}

	function HandleAJAXSave()
	{
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null)
		{
			alert (AL_AJAX);
			return;
		}
		if(document.getElementById("auditFlag").value != 'Y')
		{
			setBillRelatedFlags();
		}
		
		var uri = newURI;
		var url = run();
		url = uri + url;
		var elementMinor = document.getElementsByName("chkbxFMMajorMinor");
		for(var i=0;i<elementMinor.length;i++)
		{
			if(elementMinor[i].checked)
			{
				url = url+'&chkbxFMMajorMinorVar=Y';
			}
			else
			{
				url = url+'&chkbxFMMajorMinorVar=';
			}
		}
		var elementMaried = document.getElementsByName("chkbxFMMarried");
		for(var i=0;i<elementMaried.length;i++)
		{
			if(elementMaried[i].checked)
			{
				url = url+'&chkbxFMMarriedVar=Y';
			}
			else
			{
				url = url+'&chkbxFMMarriedVar=';
			}
		}
		xmlHttp.onreadystatechange=caseStateChanged;
		xmlHttp.open("POST",uri,false);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(url);
		newURI = "";
	}

	function caseStateChanged()
	{
		if (xmlHttp.readyState==complete_state)
		{
			hideProgressbar();
			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			document.getElementById("hidpensionerCode").value = XmlHiddenValues[0].childNodes[0].text;
			document.getElementById("hidpensionerDtlsId").value = XmlHiddenValues[0].childNodes[1].text;
			document.getElementById("hidCaseId").value = XmlHiddenValues[0].childNodes[2].text;
			document.getElementById("hidPPONo").value = XmlHiddenValues[0].childNodes[3].text;
			document.getElementById("hidStoredLocId").value = XmlHiddenValues[0].childNodes[5].text;
			document.getElementById("hidCaseStatus").value = XmlHiddenValues[0].childNodes[6].text;
			document.getElementById("hidSeenFlag").value = XmlHiddenValues[0].childNodes[7].text;
			document.getElementById("hidScheme").value = XmlHiddenValues[0].childNodes[8].text;
			if(document.getElementById("cmbFPPension"))
			{
				document.getElementById("hidcmbFPPension").value = XmlHiddenValues[0].childNodes[9].text;
			}
			disableDeathDateAndLastPaidDate();
	 	 	alert(XmlHiddenValues[0].childNodes[4].text);
			openType();

			if(XmlHiddenValues[0].childNodes[10] != null)
			{
				var x = XmlHiddenValues[0].childNodes[10].text;
				if(x=="Y")
				{
					var ResString = XmlHiddenValues[0].childNodes[11].text;
					alert(ResString);
					if(XmlHiddenValues[0].childNodes[12] != null)
					{
						var y = XmlHiddenValues[0].childNodes[12].text;
						if(y == "Y")
						{
							var newWindow = null;
		    				var x = 0;
		    				var y = 0;
		    				var h = screen.height - 100;
		    				var w = screen.width;
		    				var urlstring = "ifms.htm?actionFlag=openRecoveryPage&pension_request_id="+document.getElementById("hidCaseId").value+"&pensioner_code="+document.getElementById("hidpensionerCode").value+'&isFromCase=Y&isForBill=N';
		    				var urlstyle = "height="+ h +",width= "+ w +",toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=" + x + ",left=" + y;
		    				newWindow = window.open(urlstring, "recoverybutton", urlstyle);
		    				globArray[7] = newWindow;
						}
					}
					var cw = window.open("","","height=900,width=1200,status=yes,toolbar=no,menubar=yes,location=no,scrollbars=yes,resizable=yes");
					cw.document.write('<style media="print">');
				    cw.document.write('@FONT-FACE { font-family: sans-serif;}');
				    cw.document.write('@media print {BODY { font-size: 10pt }}');
				    cw.document.write('@media screen {BODY { font-size: 10pt } }');
				    cw.document.write('@media screen, print { BODY { line-height: 1.2 }}');
				    cw.document.write('@page cheque{ size 30in 30in; margin: 0.5cm }');
				    cw.document.write('DIV {page: cheque; page-break-after: left;  }');
				    cw.document.write('</style>');
				    cw.document.write('<body>');
				    cw.document.write(XmlHiddenValues[0].childNodes[11].text);
				    cw.document.write('</body>');
				    cw.document.write('<script language="javascript">');
					cw.document.write("window.print();");
				 	cw.document.write( '<' + "/script" + '>');
				    cw.location.reload(false);
				}
				else
				{
					if(XmlHiddenValues[0].childNodes[11] != null)
					{
						var x = XmlHiddenValues[0].childNodes[11].text;
						alert(x);
					}
				}
			}
			//setPesnionBillButton();
		 	if(flagForSaveAndNew)
		 	{
		 		document.getElementById("hidCaseStatus").value = "NEW";
		 		document.inwdPension.reset();
   	 		    goToFieldTab("CmbpensionClass",0)
		 	}

		}
	}

// AJAX For Saving Pension Case ......... End ..........
	function goToFieldTab(field,cnt)
	{
		goToTab(cnt);

		if(document.getElementById(field) != null && ! document.getElementById(field).disabled)
		{
			document.getElementById(field).focus();
		}
	}

	function chckDate()
	{
		var dobDt = document.getElementById("txtDateOfBirth").value;
		var dojDt = document.getElementById("txtDateOfJoin").value;
		var dorDt = document.getElementById("txtDateOfRetirement").value;
		var dodDt = document.getElementById("txtDateOfDeath").value;
		var dodDc = document.getElementById("txtCommensionDate").value;
		var cvpDate = document.getElementById("txtCvpDate").value;
		var dcrgDate = document.getElementById("txtDcrgDate").value;
		var cvpRestoration = document.getElementById("txtCvpRestorianDate").value;
		var endDate = document.getElementById("txtPPOEndDate").value;
		var lastPaidDate = document.getElementById("txtLastPaidDate").value;
		if((dobDt.length > 0 && dodDt.length>0) || (dobDt.length>0&&dojDt.length>0) || (dobDt.length>0 && dorDt.length > 0))
		{
			if(dojDt.length>0)
			{
				if(isProper1(dobDt,dojDt) == false)
				{
					alert(DOBJ);
					document.getElementById("txtDateOfJoin").value="";
					goToFieldTab('txtDateOfJoin',0);
					return false;
				}
			}
			else if(dorDt.length>0)
			{
				if(isProper1(dobDt,dorDt) == false)
				{
					alert(DOBR);
					document.getElementById("txtDateOfRetirement").value="";
					goToFieldTab('txtDateOfRetirement',0);
					return false;
				}
			}
			else if(dodDt.length>0)
			{
				if(isProper1(dobDt,dodDt) == false)
				{
					alert(DOBD);
					document.getElementById("txtDateOfDeath").value="";
					goToFieldTab('txtDateOfDeath',0);
					return false;
				}
			}
		}
		if((dojDt.length>0 && dorDt.length>0) || (dojDt.length>0 && dodDt.length>0))

		{
			if(dorDt.length>0)
			{
				if(isProper1(dojDt,dorDt) == false)
				{
					alert(DOJR);
					document.getElementById("txtDateOfRetirement").value="";
					goToFieldTab('txtDateOfRetirement',0);
					return false;
				}
			}
			else if(dodDt.length>0)
			{
				if(isProper1(dojDt,dodDt) == false)
				{
					alert(DOJD);
					document.getElementById("txtDateOfDeath").value="";
					goToFieldTab('txtDateOfDeath',0);
					return false;
				}
			}
		}
		var doSdt = document.getElementById("txtSeenDate").value;
		var prevSeenDate = document.getElementById("lastSeenDate").value;
		if(dorDt.length >0 && doSdt.length>0 )
		{
			if(isProper1(dorDt,doSdt) == false)
			{
				alert(ENTRPROPSEENDATE);
				document.getElementById("txtSeenDate").value="";
				document.getElementById("revalidLbl").style.display =  "none";
				document.getElementById("revalidBtn").style.display =  "none";
				goToFieldTab('txtSeenDate',0);
				return false;
			}
		}
		if(prevSeenDate.length >0 && doSdt.length > 0)
		{
			if(isProper1(prevSeenDate,doSdt) == false)
			{
				alert("Please Enter Seen Date Geater than Previous Approved SeenDate "+prevSeenDate);
				document.getElementById("txtSeenDate").value="";
				document.getElementById("revalidLbl").style.display =  "none";
				document.getElementById("revalidBtn").style.display =  "none";
				goToFieldTab('txtSeenDate',0);
				return false;
			}
		}
		if(dodDt.length > 0 && dorDt.length >0)
		{
			if(isProper1(dorDt,dodDt) == false)
			{
				alert(DEATHDATELTRETDATE);
				document.getElementById("txtDateOfDeath").value="";
				goToFieldTab('txtDateOfDeath',0);
				setOmrType();
				return false;
			}
		}
		if(dodDc.length >0 && dorDt.length>0)
		{
			if(isProper1(dorDt,dodDc) == false || dorDt == dodDc)
			{
				alert(CMNDATEGTRETDATE);
				document.getElementById("txtCommensionDate").value="";
				goToFieldTab('txtCommensionDate',0);
				return false;
			}
		}
		var cvpRadio = document.getElementById("radioCVPY");
		if(dodDc.length >0)
		{
			if(cvpDate.length>0&&cvpRadio.checked)
			{
				if(isProper1(dodDc,cvpDate) == false)
				{
					alert("Please Enter Proper CVP Date");
					document.getElementById("txtCvpDate").value="";
					goToFieldTab('txtCvpDate',0);
					return false;
				}
			}
			if(cvpRestoration.length > 0&&cvpRadio.checked)
			{
				if(isProper1(dodDc,cvpRestoration) == false)
				{
					alert("Please Enter Proper CVP Restoration Date");
					document.getElementById("txtCvpRestorianDate").value="";
					goToFieldTab('txtCvpRestorianDate',0);
					return false;
				}
			}
			if(document.getElementById("txtAppliedDate"))
			{
				var appliedDate = document.getElementById("txtAppliedDate").value;
			}
			if(document.getElementById("AppCol2").style.display != 'none' && appliedDate.length > 0&&cvpRadio.checked)
			{
				if(isProper1(dodDc,appliedDate) == false)
				{
					alert("Please Enter Proper Restoration Applied Date");
					document.getElementById("txtAppliedDate").value="";
					goToFieldTab('txtAppliedDate',0);
					return false;
				}
			}
		}
		var DcrgRadio = document.getElementById("radioDCRGY");
		if(dodDc.length >0 && dcrgDate.length>0&&DcrgRadio.checked)
		{
			if(isProper1(dodDc,dcrgDate) == false)
			{
				alert("Please Enter Proper DCRG Date");
				document.getElementById("txtDcrgDate").value="";
				goToFieldTab('txtDcrgDate',0);
				return false;
			}
		}
		if(endDate.length>0 && dodDc.length>0)
		{
			if(isProper1(dodDc,endDate) == false)
			{
				alert("Please Enter Proper PPO End Date");
				document.getElementById("txtPPOEndDate").value="";
				goToFieldTab('txtPPOEndDate',0);
				return false;
			}
		}
		if(lastPaidDate.length>0 && dodDc.length>0)
		{
			if(isProper1(dodDc,lastPaidDate) == false)
			{
				alert("Please Enter Proper PPO Last Paid Date");
				document.getElementById("txtLastPaidDate").value="";
				goToFieldTab('txtLastPaidDate',0);
				return false;
			}
		}
	}


	function isProper1(frmStr,toStr)
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
				if((frmYear == toYear) && (frmMonth == toMonth))
				{
					if(frmDay > toDay)
					{
						return false;
					}
				}
				return true;
			}
		}
		function chkValidYrMon()
		{
			if(document.getElementById("txtCVPEffectMM").value != null || document.getElementById("txtCVPEffectMM").value == "")
			{
				if(document.getElementById("txtCVPEffectMM").value < 0 || document.getElementById("txtCVPEffectMM").value > 12)
				{
					alert("Month mast be greater than 0 and less than 12");
					document.getElementById("txtCVPEffectMM").value = "";
					document.getElementById("txtCVPEffectMM").focus();
					return false;
				}
			}
			if(document.getElementById("txtCVPEffectYYYY").value != null || document.getElementById("txtCVPEffectYYYY").value == "")
			{
				if(document.getElementById("txtCVPEffectYYYY").value < 1900 || document.getElementById("txtCVPEffectYYYY").value > 2500)
				{
					alert("Year mast be between 1900 and 2500");
					document.getElementById("txtCVPEffectYYYY").value = "";
					document.getElementById("txtCVPEffectYYYY").focus();
					return false;
				}
			}
		}	
		function convertCase()
		{
			/*if(validPensionCaseData() == true)
     		{
     			var x = document.getElementById("hidInwardStauts").value;
				var pensionerCode = document.getElementById("hidpensionerCode").value;
				var caseId = document.getElementById("hidCaseId").value;
				var pensionerDtlsId = document.getElementById("hidpensionerDtlsId").value;
				document.forms[0].action = 'ifms.htm?actionFlag=insertPensionCaseDtls&stauts=P&PensionCode='+ pensionerCode+'&isConverting=Y&auditFlag='+document.getElementById("auditFlag").value+'&txtPpoNo='+document.getElementById("txtPpoNo").value;
				document.forms[0].submit();
			}*/
			document.getElementById("CmbpensionClass").value = "R";
			document.getElementById("sancAuthCmb").value = "-1";
			getPreFixCmb();
			checkOtherAuth();
			document.getElementById("provBlock").style.display = "inline";
			document.getElementById("txtProvNum").value = document.getElementById("txtPpoNo").value;
			document.getElementById("txtProvAmt").value = document.getElementById("txtBasicPensionAmnt").value;
			document.getElementById("txtProvPenDate").value = document.getElementById("hidPpoInwardDate").value ;
			document.getElementById("txtPpoInwdDate").value = document.getElementById("hidCurDate").value;
			if(document.getElementById("txtProDcrgAmnt") != null)
			{
				document.getElementById("txtProDcrgAmnt").value = document.getElementById("txtDcrgAmnt").value;
			}
			document.getElementById("txtPorvFp1Date").value = document.getElementById("txtFp1Date").value ;
			document.getElementById("txtPorvFp2Date").value = document.getElementById("txtFp2Date").value ;
			document.getElementById("txtPorvFp1Amnt").value = document.getElementById("txtFp1Amnt").value ;
			document.getElementById("txtPorvFp2Amnt").value = document.getElementById("txtFp2Amnt").value ;
			document.getElementById("hidIsConvertSave").value = "Y";
			document.getElementById("txtPpoNo").disabled = false;
			document.getElementById("btnConvert").style.display = "none";
			changeMandtry();
			//isConverting
		}
	function validateDDMMYY(txtElmnt,max)
	{
		var max2 = max.charAt(0);
		var max1 = max.charAt(1);
		var num = Number(String.fromCharCode(window.event.keyCode));

		if(document.getElementById(txtElmnt).value == "")
		{
			if(num > max2)
			{
				window.event.keyCode = 0;
			}
		}
		if(document.getElementById(txtElmnt).value == max2)
		{
			if(num > max1)
			{
				window.event.keyCode = 0;
			}
		}
	}

	function setBasicPension()
	{
		var DPPFAmnt = document.getElementById("txtPensionableAmnt").value;
		var DpPrct = document.getElementById("txtDpPrct").value;
	    var curdate = "01/04/2004";
	    var commnDate  = document.getElementById("txtCommensionDate").value;
		if(isProper1(commnDate,curdate) == false)
		{
			if( Number(DPPFAmnt) > 0 )
			{
				var BasicAmnt = Math.round((DPPFAmnt*100)/(100*1+DpPrct*1));
				document.getElementById("hidBasicAmt").value = (DPPFAmnt*100)/(100*1+DpPrct*1);
				var DpAmnt = Math.round(DPPFAmnt - BasicAmnt);
				document.getElementById("txtBasicPensionAmnt").value = (BasicAmnt*100)/100;
			}
			else
			{
				BasicAmnt = document.getElementById("txtBasicPensionAmnt").value;
				document.getElementById("hidBasicAmt").value = BasicAmnt;
				var DpAmnt =  (BasicAmnt*DpPrct)/100;
			}
			document.getElementById("txtDpAmnt").value = (DpAmnt*100)/100;
		}
		else
		{
			 if(Number(DPPFAmnt)>0)
			 {
			 	document.getElementById("hidBasicAmt").value = DPPFAmnt;
     			document.getElementById("txtBasicPensionAmnt").value = DPPFAmnt;
     			document.getElementById("txtDpAmnt").value = (DPPFAmnt*DpPrct)/100;
			 }
			 else
			 {
			 	document.getElementById("hidBasicAmt").value = document.getElementById("hidBasicAmt").value;
     			document.getElementById("txtBasicPensionAmnt").value = document.getElementById("hidBasicAmt").value;
     			DPPFAmnt = document.getElementById("txtBasicPensionAmnt").value;
     			document.getElementById("txtDpAmnt").value = (DPPFAmnt*DpPrct)/100;
			 }

		}
		setValidAmountFormat(document.getElementById("txtBasicPensionAmnt"));
		setValidAmountFormat(document.getElementById("txtDpAmnt"));
		//setValidAmountFormat(document.getElementById("hidBasicAmt"));
		calculateRedcdPen2();
		calulateReducedPension();
		var pnsnType = document.getElementById("cmbPnsnType").value ;
		if(pnsnType == 'Family (Lost)' || pnsnType == 'Family')
		{
			if(Number(document.getElementById("txtPensionableAmnt").value) > 0 )
			{
				document.getElementById("txtFp1Amnt").value = document.getElementById("txtPensionableAmnt").value;
			}
			else
			{
				document.getElementById("txtFp1Amnt").value = document.getElementById("txtBasicPensionAmnt").value;
			}
			document.getElementById("txtFp1Amnt").disabled = true;
		}
		else
		{
			if(lStrDisable == "")
			document.getElementById("txtFp1Amnt").disabled = false;
		}
	}

	keyPreview = 1;

	function setTIAmount()
	{
		var TiPrct = document.getElementById("txtTiPrct").value;
		var BasicAmnt = document.getElementById("txtBasicPensionAmnt").value;
		var DpAmnt = document.getElementById("txtDpAmnt").value;
		var x = Number(BasicAmnt)+Number(DpAmnt)
		if(Number(document.getElementById("txtPnsnCutAmnt").value) >0)
		{
			x  = x - Number(document.getElementById("txtPnsnCutAmnt").value);
		}
		if(Number(document.getElementById("txtADPAmount").value) >0 && document.getElementById("Rop2006chkBx").checked)
		{
			x = x + Number(document.getElementById("txtADPAmount").value)
		}
		var TiAmnt = Number(x)*TiPrct/100;
		
		if(document.getElementById("Rop2006chkBx").checked)
		{	
			TiAmnt = Math.ceil(TiAmnt);
		}
		else if(document.getElementById("Rop1996chkBx").checked)
		{
			TiAmnt = Math.round(TiAmnt*100)/100;
		}
		document.getElementById("txtTiAmnt").value = TiAmnt;  //Math.round(TiAmnt*100)/100;
		setValidAmountFormat(document.getElementById("txtTiAmnt"));
	}


	function setMA()
	{
		if(document.getElementById("radioMaY").checked && document.getElementById("hidMaAmt").value >= 0 )
		{
			document.getElementById("txtMaAmnt").value = document.getElementById("hidMaAmt").value;
			document.getElementById("txtMaAmnt").disabled = "disabled";
		}
		else if(document.getElementById("radioMaN").checked)
		{
			document.getElementById("txtMaAmnt").disabled = true;
			document.getElementById("txtMaAmnt").value = "0.00";
		}
		setValidAmountFormat(document.getElementById("txtMaAmnt"));
		calulateReducedPension();
	}
	var currentEnabled = null;
	function enableElement(elem) {
		if (currentEnabled) {
		currentEnabled.disabled = true;
		}
		elem.disabled = false;
		currentEnabled = elem;
		}


	function disableElement(elem) {
		if (currentEnabled) {
		currentEnabled.disabled = false;
		}
		elem.disabled = true;
		currentEnabled = elem;
		elem.value = '';
		calulateReducedPension();
		}

	function calulateReducedPension()
	{
		chngBasicPension();
		setTIAmount();
		var BasicPension = document.getElementById("txtBasicPensionAmnt").value;
		var DpAmnt = document.getElementById("txtDpAmnt").value;
		var TiAmnt = document.getElementById("txtTiAmnt").value;
		var MaAmnt = document.getElementById("txtMaAmnt").value;
		var ArrearAmnt = document.getElementById("txtArrearAmnt").value;
		var PensionCutAmnt = document.getElementById("txtPnsnCutAmnt").value;
		var ItCutAmnt = document.getElementById("txtItAmnt").value;
		var CVPMonthly = document.getElementById("txtCvpMonthlyAmnt").value;
		var Revocery = document.getElementById("txtRecoveryAmnt").value;
		var PersonalPension = document.getElementById("txtPersonalPension").value;
		var SpecialCut =  document.getElementById("txtSpecialAmnt").value;
		var Ir = document.getElementById("txtIr").value;
		var OtherBenefit = document.getElementById("txtOtherBenefit").value;
		var ADPAmopunt = document.getElementById("txtADPAmount").value;
		var ReducedPension = 0;
		if(!IsEmpty(document.getElementById("txtBasicPensionAmnt")))
		{
			ReducedPension = ReducedPension*1 + BasicPension*1;
		}
		if(!IsEmpty(document.getElementById("txtPnsnCutAmnt")))
		{
			ReducedPension = ReducedPension*1 - PensionCutAmnt*1;
		}
		if(!IsEmpty(document.getElementById("txtDpAmnt")))
		{
			ReducedPension = ReducedPension*1 + DpAmnt*1;
		}
		if(!IsEmpty(document.getElementById("txtTiAmnt")))
		{
			ReducedPension = ReducedPension*1 + TiAmnt*1;
		}
		if(!IsEmpty(document.getElementById("txtADPAmount")))
		{
			ReducedPension = ReducedPension*1 + ADPAmopunt*1;
		}
		if(!IsEmpty(document.getElementById("txtMaAmnt")))
		{
			ReducedPension = ReducedPension*1 + MaAmnt*1;
		}
		if(!IsEmpty(document.getElementById("txtOtherBenefit")))
		{
			ReducedPension = ReducedPension*1 + OtherBenefit*1;
		}
		if(!IsEmpty(document.getElementById("txtPersonalPension")))
		{
			ReducedPension = PersonalPension*1 + ReducedPension*1;
		}
		if(!IsEmpty(document.getElementById("txtIr")))
		{
			ReducedPension = Ir*1 + ReducedPension*1;
		}
		if(!IsEmpty(document.getElementById("txtArrearAmnt")))
		{
			ReducedPension = ReducedPension*1 + ArrearAmnt*1;
		}
	    if(!IsEmpty(document.getElementById("txtPnsnCutAmnt")))
		{
			ReducedPension = ReducedPension*1 - SpecialCut*1;
		}
		if(!IsEmpty(document.getElementById("txtItAmnt")))
		{
			ReducedPension = ReducedPension*1 - ItCutAmnt*1;
		}
		if(!IsEmpty(document.getElementById("txtCvpMonthlyAmnt")))
		{
			ReducedPension = ReducedPension*1 - CVPMonthly*1;
		}
		if(!IsEmpty(document.getElementById("txtRecoveryAmnt")))
		{
			ReducedPension = ReducedPension*1 - Revocery*1;
		}

		ReducedPension=Math.round(ReducedPension*100)/100;
		document.getElementById("txtReducedPnsnAmnt").value = ReducedPension;
		setValidAmountFormat(document.getElementById("txtReducedPnsnAmnt"));
		document.getElementById("ReducedPnsnTd").innerHTML='<b>'+ document.getElementById("txtReducedPnsnAmnt").value +'</b>'
	}
	function calculateRedcdPen2()
	{
		var BasicPension = document.getElementById("txtBasicPensionAmnt").value;
		var DpAmnt = document.getElementById("txtDpAmnt").value;
		var PensionCutAmnt = document.getElementById("txtPnsnCutAmnt").value;
		var CVPMonthly = document.getElementById("txtCvpMonthlyAmnt").value;
		var ADPAmopunt = document.getElementById("txtADPAmount").value;
		var tot2 = Number(0);
		tot2 = Number(tot2) + Number(BasicPension);
		tot2 = Number(tot2) - Number(PensionCutAmnt);
		tot2 = Number(tot2) + Number(DpAmnt);
		tot2 = Number(tot2) - Number(CVPMonthly);
		tot2 = Number(tot2) + Number(ADPAmopunt);
		document.getElementById("hidRdcdPnsn2").value = tot2;
		setValidAmountFormat(document.getElementById("hidRdcdPnsn2"));
		document.getElementById("reduced2").innerHTML =  ':&nbsp<b>'+ document.getElementById("hidRdcdPnsn2").value +'</b>'
	}
	function chngBasicPension()
	{
		var PensionCutAmnt = document.getElementById("txtPnsnCutAmnt").value;
		if(Number(PensionCutAmnt)> 0)
		{
			//document.getElementById("hidBasicAmt").value = (DPPFAmnt*100)/(100*1+DpPrct*1);
			var DPPFAmnt = document.getElementById("txtPensionableAmnt").value;
			var BasicAmnt = document.getElementById("hidBasicAmt").value;
			var DpPrct = document.getElementById("txtDpPrct").value;
			var TiPrct = document.getElementById("txtTiPrct").value;
			BasicAmnt = BasicAmnt - Number(PensionCutAmnt);
			var DpAmnt = (BasicAmnt*DpPrct/100);
			var TiAmnt = ((BasicAmnt+ DpAmnt) *TiPrct/100);
			document.getElementById("txtDpAmnt").value = Math.round(DpAmnt*100)/100;
			
			if(document.getElementById("Rop2006chkBx").checked)
			{
				TiAmnt = Math.ceil(TiAmnt);
			}
			else if(document.getElementById("Rop1996chkBx").checked)
			{
				TiAmnt = Math.round(TiAmnt*100)/100;
			}
			
			document.getElementById("txtTiAmnt").value = TiAmnt;   // Math.round(TiAmnt*100)/100;
			setValidAmountFormat(document.getElementById("txtDpAmnt"));
			setValidAmountFormat(document.getElementById("txtTiAmnt"));
		}
		else
		{
			/*var DPPFAmnt = document.getElementById("txtPensionableAmnt").value;
			var DpPrct = document.getElementById("txtDpPrct").value;
			if( Number(DPPFAmnt) > 0 )
			{
				var BasicAmnt = Math.round((DPPFAmnt*100)/(100*1+DpPrct*1));
				var DpAmnt = Math.round(DPPFAmnt - BasicAmnt);
			}
			else
			{
				BasicAmnt = document.getElementById("txtBasicPensionAmnt").value;
			    var DpAmnt =  Math.round((BasicAmnt*DpPrct)/100);
			}
			 var curdate = "01/04/2004";
		    var commnDate  = document.getElementById("txtCommensionDate").value;
			if(isProper1(commnDate,curdate) == false)
			{
				document.getElementById("txtDpAmnt").value = (DpAmnt*100)/100;
			}
			else
			{
				document.getElementById("txtDpAmnt").value = "0.00";
			}
			setValidAmountFormat(document.getElementById("txtDpAmnt"));
			alert("-----"+document.getElementById("txtDpAmnt").value);*/
			var DPPFAmnt = document.getElementById("txtPensionableAmnt").value;
			var DpPrct = document.getElementById("txtDpPrct").value;
		    var curdate = "01/04/2004";
		    var commnDate  = document.getElementById("txtCommensionDate").value;
			if(isProper1(commnDate,curdate) == false)
			{
				if( Number(DPPFAmnt) > 0 )
				{
					var BasicAmnt = Math.round((DPPFAmnt*100)/(100*1+DpPrct*1));
					document.getElementById("hidBasicAmt").value = (DPPFAmnt*100)/(100*1+DpPrct*1);
					var DpAmnt = Math.round(DPPFAmnt - BasicAmnt);
					document.getElementById("txtBasicPensionAmnt").value = (BasicAmnt*100)/100;
				}
				else
				{
					BasicAmnt = document.getElementById("txtBasicPensionAmnt").value;
					document.getElementById("hidBasicAmt").value = BasicAmnt;
					var DpAmnt =  (BasicAmnt*DpPrct)/100;
				}
				document.getElementById("txtDpAmnt").value = (DpAmnt*100)/100;
			}
			else
			{
				 if(Number(DPPFAmnt)>0)
				 {
				 	document.getElementById("hidBasicAmt").value = DPPFAmnt;
	     			document.getElementById("txtBasicPensionAmnt").value = DPPFAmnt;
	     			document.getElementById("txtDpAmnt").value = (DPPFAmnt*DpPrct)/100;
				 }
			}
			setValidAmountFormat(document.getElementById("txtBasicPensionAmnt"));
			setValidAmountFormat(document.getElementById("txtDpAmnt"));
		}
	}

	function setGender(lthis)
	{
		document.getElementById("cmbGender").value = lthis.value;
	}
	var Row_One = 1;
	var Row_Two = 1;
	var branchDtls="";
	var ajaxCounter = "";
	var dCounter = "";
	function Table_One_AddRow()
   	{
  		Row_One = Row_One + 1;
		var lastRow = document.getElementById('TableOne').rows.length;
		var newRow = document.all("TableOne").insertRow(lastRow);
	    var varCnt = document.getElementsByName("txtNMName").length;
    	var Cell1 = newRow.insertCell();
    	var Cell2 = newRow.insertCell();
    	var Cell3 = newRow.insertCell();
    	var Cell4 = newRow.insertCell();
    	var Cell5 = newRow.insertCell();
    	var Cell6 = newRow.insertCell();
    	var Cell7 = newRow.insertCell();

    	Cell1.innerHTML = '<input type="text" onkeypress="upperCase(event)"  size="25" maxlength=100 name="txtNMName" onblur="changeOnBlur(this),goToRemarks()" onfocus="changeOnFocus(this)" id="txtNMName'+Number(varCnt)+'"class="texttag" title="'+CMN_NAME+'"/>';
    	Cell2.innerHTML = '<input type="text" size="5" maxlength="5" name="txtNMPercentage" id="txtNMPercentage'+Number(varCnt)+'" class="texttag" onblur="chkDigitAmnt(this),changeOnBlur(this)" onfocus="changeOnFocus(this)" onkeypress="amountFormat(this)" title="'+CMN_PERCENTAGE+'"/> ';
    	Cell3.innerHTML = '<input type="text" name="txtbranchCodeNom" id="txtbranchCodeNom'+Number(varCnt)+'" maxlength="20" onkeypress="NumberFormet()"  value="" ${read} onblur="getBranchByBranchCodeNom(this)" style="width:85%" title="'+CMN_BRANCHCD+'"/> ';
		Cell4.innerHTML = '<select style="width:87%" name="cmbBankNom" id="cmbBankNom'+Number(varCnt)+'" onchange="displyBankAndBranch()" ${read} title="'+CMN_BANK+'">'+cmbBankOption+'</select>';
		Cell5.innerHTML = '<select style="width:81%" name="cmbBranchNom" id="cmbBranchNom'+Number(varCnt)+'" onchange="displyBankAndBranch()" ${read}  title="'+CMN_BRANCH+'">'+cmbBranchOption+'</select>';
		Cell6.innerHTML = '<input type="text" name="txtACNoNom" maxlength="20" id="txtACNoNom'+Number(varCnt)+'" onblur="Table_One_AddRow()" onkeypress="NumberFormet()" style="width:85%" value="" ${read} title="'+CMN_ACCNO+'"/><label style="display: none;" id="mandtryBankNom" class="mandatoryindicator">*</label>';
    	Cell7.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveMedTableRow(this, \'TableOne\')" /> ';
    	
    	document.getElementById("txtNMName"+Number(varCnt)).focus();
	}

	var RowCounter = 0;
	var familyrow =0 ;
	var iteration = 1;
	var varCnt ;
	function Table_Two_AddRow()
   	{
 		varCnt = document.getElementsByName("txtFmName").length;
    	var newRow =  document.all("TableTwo").insertRow(varCnt+1);
    	var Cell1 = newRow.insertCell();
    	var Cell2 = newRow.insertCell();
    	var Cell3 = newRow.insertCell();
    	var Cell4 = newRow.insertCell();
    	var Cell5 = newRow.insertCell();
    	var Cell6 = newRow.insertCell();
    	var Cell7 = newRow.insertCell();
    	var Cell8 = newRow.insertCell();
    	var Cell9 = newRow.insertCell();
    	var Cell10 = newRow.insertCell();
    	var Cell11 = newRow.insertCell();
    	var Cell12 = newRow.insertCell();
    	var Cell13 = newRow.insertCell();
    	var Cell14 = newRow.insertCell();
    	Cell1.innerHTML = '<input type="text" onkeypress="upperCase(event)"  name="txtFmName" maxlength="100" id="txtFmName" onblur="changeOnBlur(this)" onfocus="changeOnFocus(this)" title="'+CMN_NAME+'" />';
    	Cell2.innerHTML = '<select name="cmbRelation" id="cmbRelation'+Number(varCnt)+'" title="'+CMN_RELATIONSHIP+'" ><option value="-1">--Select--</option>' + fRelation +  '</select>';
    	Cell3.innerHTML = '<input type="text" onkeypress="amountFormat(this)"  name="txtFMPercent" id="txtFMPercent'+Number(varCnt)+'" onblur="chkDigitAmnt(this),chkForsum100(this),changeOnBlur(this)" onfocus="changeOnFocus(this)"  size="6" maxlength="3"  title="'+CMN_PERCENTAGE+'" />';
    	Cell4.innerHTML = '<select name="cmbBankFd" style="width:100%;display: none" id="cmbBankFd'+Number(varCnt)+'" title="'+CMN_BANK+'"><option value="-1">--Select--</option></select>';
    	Cell5.innerHTML = '<select name="cmbBranchFd" style="width:100%;display: none" id="cmbBranchFd'+Number(varCnt)+'"  title="'+CMN_BRANCH+'" ><option value="-1">--Select--</option></select>';
    	Cell6.innerHTML = '<input type="text" name="txtFMAcntNo" maxlength="20" onblur="changeOnBlur(this)" onfocus="changeOnFocus(this)" id="txtFMAcntNo'+Number(varCnt)+'" size="8" title="'+CMN_ACNO+'" />';
    	Cell7.innerHTML = '<input type="text" onKeyup="dateFormat(this);"  name="txtFMDateOfBirth"  id="txtFMDateOfBirth'+ Number(varCnt) +'" size="9" onblur="formateWholeDate(this),validateDate(this),isProperDate(this,FMDOB),changeOnBlur(this)" onfocus="changeOnFocus(this)" title="'+CMN_DATEOFBIRTH+'"/>';
    	Cell8.innerHTML = '<input type="checkbox" name="chkbxFMMajorMinor" id="chkbxFMMajorMinor'+Number(varCnt)+'" value="Y"  title="'+CMN_MINOR+'" />';
    	Cell9.innerHTML = '<input type="checkbox" name="chkbxFMMarried" id="chkbxFMMarried'+Number(varCnt)+'" value="Y" title="'+CMN_MARRIED+'"/>';
    	Cell10.innerHTML = '<input type="text" name="txtFMSalary" maxlength="15" id="txtFMSalary'+Number(varCnt)+'" size="8" onfocus="changeOnFocus(this),setValidAmountFormat(this)" onblur="changeOnBlur(this)" onkeypress="amountFormat(this)" title="'+CMN_SALARY+'" />';
    	Cell11.innerHTML = '<select name="chkbxHandiCap" id="chkbxHandiCap'+Number(varCnt)+'" title="'+CMN_PHYHANDICAP+'" /> <option value="N"> --Select-- </option>'+ cmbHndiCpStr + '</select>';
		Cell12.innerHTML = '<input type="text"  onkeypress="upperCase(event)"  name="txtFMGardianName" maxlength="100" id="txtFMGardianName'+Number(varCnt)+'" onblur="changeOnBlur(this)" onfocus="changeOnFocus(this)" size="12" title="'+CMN_GUARDIAN+'" />';
		Cell13.innerHTML = '<input type="text" onKeyup="dateFormat(this);"  name="txtFMDateOfDeath" id="txtFMDateOfDeath'+ Number(varCnt) +'" size="9" onblur="formateWholeDate(this),validateDate(this),isProperDate(this,FMDOD),changeOnBlur(this),goToNewRow(this)" onfocus="changeOnFocus(this)"  title="'+CMN_DATEOFDEATH+'" />';
		Cell14.innerHTML = '<img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveMedTableRow(this, \'TableTwo\')" />';
		Cell4.style.display ='none';
		Cell5.style.display ='none';
		Cell6.style.display ='none';
		displyBankAndBranch();
		if(document.getElementById("auditFlag").value == 'Y')
		{
			var fDeath = document.getElementsByName("txtFMDateOfDeath");
			for(var i=0;i<fDeath.length;i++)
			{
				fDeath[i].disabled = "disabled";
			}	
		}
	}
   	function RemoveMedTableRow(obj, tblId)
	{
		var rowID = showRowCell(obj);
	    var tbl = document.getElementById(tblId);
	    tbl.deleteRow(rowID);
	    if(tblId == 'TableOne')
	    {
	    	Row_One = Row_One - 1;
	    }
	    varCnt = varCnt-1;
	    if(tblId == 'TableTwo')
	    {
	    	checkForSeenFlag();
	    }
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
	 function winCls()
	 {
	 	try
	 	{
	 	 	if(window.opener.document)
	 	 	{
	 	 		if(document.getElementById("hidCaseDisable") && document.getElementById("hidCaseDisable").value == 'Y')
	 	 		{
	 	 			window.close();
	 	 		}
	 	 		else
	 	 		{
	 	 			var url = window.opener.location.href;
			 	 		if(document.getElementById("hidCaseFromLevel")&&window.opener.document.getElementById("cmbSearch") && ! window.opener.document.getElementById("BillFromlevel"))
			 	 		{
			 	 			if(window.opener.document.getElementById("cmbSearch").value != '-1'&&window.opener.document.getElementById("hidSrchValue"))
			 	 			{
			 	 				url =  url+'&cmbSearch='+ window.opener.document.getElementById("cmbSearch").value+'&txtSearch='+window.opener.document.getElementById("hidSrchValue").value
			 	 				var x = document.getElementById("hidCaseFromLevel").value;
			 	 				url= url+'&caseFromlevel='+x;
			 	 			}
			 	 		}
			 	 		if(window.opener.document.getElementById("BillFromlevel"))
			 	 		{
			 	 			var x = window.opener.document.getElementById("BillFromlevel").value;
			 	 			url= url+'&BillFromlevel='+x;
			 	 		}
			 	 		window.opener.location.href = url;
			 	 		window.close();
			 	 		/*if(document.getElementById("hidCaseDisable").value.length <= 0 )
			 	 		{
				 	 		if(document.getElementById("auditFlag").value == 'Y')
				 	 		{
								window.opener.location.href = 'ifms.htm?actionFlag=getSavedCases&status=1&caseFromlevel='+document.getElementById("hidCaseFromLevel").value;
				 	 		}
				 	 		else
				 	 		{
				 	 			if(document.getElementById("hidCaseFromLevel").value.length > 0 )
				 	 			{
				 	 				window.opener.location.href = 'ifms.htm?actionFlag=getSavedCases&status=0&caseFromlevel='+document.getElementById("hidCaseFromLevel").value;
				 	 			}
				 	 			else
				 	 			{
				 	 				window.opener.location.href = 'ifms.htm?actionFlag=auditorCaseDisribute';
				 	 			}

				 	 		}
			 	 		}
			 	 		else
			 	 		{*/
			 	 			//window.opener.location.href = window.opener.location.href;
			 	 		//}
			 	 		if(globArray.length > 0)
			 	 		{
			 	 			for(var i=0;i<globArray.length;i++)
			 	 			{
			 	 				if(globArray[i] != null)
			 	 				{
			 	 					globArray[i].window.close();
			 	 				}
			 	 			}
			 	 		}
			 	 	}
	 	 		}

	 	}catch(err)
	 	{
	 		document.forms[0].action = 'ifms.htm?actionFlag=getHomePage';
			document.forms[0].submit();
	 	}
	 }

	
	function ChkArrearOrRecovery(lStr)
	{
		if(document.getElementById("hidCaseTypeFlag").value == 'P' || document.getElementById("hidCaseTypeFlag").value == 'R' )
		{
			return true;
		}
		else
		{
			showProgressbar();
			setArrearOrRecovery(lStr);
		}
	}

	function billGenerateAction(lStr)
	{

		showProgressbar();
		var message;
		var ppoNo = document.getElementById('txtPpoNo').value;
		var cvpAmnt = document.getElementById('txtCvpAmnt').value;
		var url = '';
		var dcrgAmnt = document.getElementById('txtDcrgAmnt').value;

		var pensionAmnt = document.getElementById('txtReducedPnsnAmnt').value;
        var totalBills =0;
        url = 'ifms.htm?actionFlag=generateBillCase&BillType='+lStr+'&ppoNo='+ppoNo;
        var url2 = '';
		if(document.getElementById("auditFlag").value == 'Y')
		{
			if(lStr == "CVP")
			{
				var x = Number(document.getElementById("hidAprvdCvpAmt").value) - Number(document.getElementById("hidChngdCvpAmt").value);

				//if(x>0)
				function generateBill1(lStr)
				{
					var tempBillcmb = "";
					var BillString = "";
					if(lStr == 'PENSION')
					{
						if(document.getElementById("cmbFPPension"))

						{
							tempBillcmb = document.getElementById("hidcmbFPPension").value; 
							if(tempBillcmb == 'Y' )
							{
								alert("You had selected FP Pension to include in Monthly \r\n So you cant generate Pension Bill");
								return false;
							}
						}
					}
					else
					{
						if(document.getElementById("cmbFPPension"))
						{
							var tempBillcmb2 = document.getElementById("hidcmbFPPension").value; 
							if(tempBillcmb2 == 'Y')
							{
								BillString = "Are You Sure to include FP Pension in Monthly \r\n";
								BillString = BillString+"If you want to Change Please Change before saving the bill";
							}
						}
					}
					if(funPensionBillConfirm(BillString) == true)
					{
						if(document.getElementById("hidCaseStatus").value != 'REJECT' || document.getElementById("auditFlag").value != 'Y')
						{

							if(document.getElementById("hidCaseId").value == "" )
							{
								alert(SAVE_CASE);
								return false;
							}
							if(document.getElementById("cmbPnsnStatus").value != 'Continue' && document.getElementById("cmbPnsnStatus").value != 'ContinueTransfer')
							{
								alert(STS_CON);
								goToFieldTab("cmbPnsnStatus",0);
								return false;
							}
							
							if(document.getElementById("hidSeenFlag").value == 'Y' && document.getElementById("txtSeenDate").value.length > 0)
							{

								var flag = 1;
								var isCrtBills = 'Y';
								var isSubTres = 'N';
								if(caseRevalidDtls() != false)
								{
									if((document.getElementById("auditFlag").value != 'Y') || (document.getElementById("auditFlag").value == 'Y' && document.getElementById("hidCaseStatus").value == 'APPROVED'))
									{
										billGenerateAction(lStr);
									}
									else
									{
										alert(NOTAPPRVD);
										return false;
									}
								}
							}
							else
							{
								if(document.getElementById("hidSeenFlag").value == 'Y')
								{
									alert("Please Update the Case By Giving Seen Date");
									goToFieldTab("txtSeenDate",0);
									return false;
								}
								else (document.getElementById("hidSeenFlag").value == 'N')
								{
									alert("Please Update the Case By Chnaging Seen Yes");
									goToFieldTab("radioSeenY",0);
									return false;
								}
							}
						}
						else
						{
							alert(CANTGENRTBILLFORREJCASE);
							return false;
						}
					}
				}		//{
					cvpAmnt = x;
					url = url+'&CVPAmt='+cvpAmnt+'&CVPFlag=Y';
					url2 = '&CVPAmt='+cvpAmnt+'&CVPFlag=Y';
				//}
				/*else
				{
					cvpAmnt = 0;
					alert(CHNGCVPAMNT);
					hideProgressbar();
					return false;
				}*/
			}
			if(lStr == "DCRG")
			{
				/* var y = document.getElementById("txtNewDAPrcnt").value;
				if(Number(y)>0)
				{
					var mergDcrg = document.getElementById("txtDcrgAmnt").value;
					var DAPrcnt = document.getElementById("txtDAPrcnt").value;
					var origAmnt = (parseFloat(mergDcrg)*100)/(100+parseFloat(DAPrcnt));
					var NewDa = document.getElementById("txtNewDAPrcnt").value;
					var newDcrg = (origAmnt*parseFloat(NewDa)/100)+ origAmnt;
					dcrgAmnt = parseFloat(newDcrg) - parseFloat(mergDcrg);
				}
				else
				{
					dcrgAmnt = Number(document.getElementById("hidAprvdDcrgAmt").value)-Number(document.getElementById("hidChngdDCRGAmt").value)
				}
				if(dcrgAmnt < 0)
				{
					alert(CHNGDCRGAMNT);
					hideProgressbar();
					return false;
				}
				else
				{*/
					url = url+'&DCRGAmt='+dcrgAmnt+'&DCRGFlag=Y';
					url2 = '&DCRGAmt='+dcrgAmnt+'&DCRGFlag=Y';;
				//}
			}
		}
		else
		{
			if(Number(dcrgAmnt) >0 && document.getElementById("hidDcrg").value == "Y")
	        {
	        	url = url+"&DCRGFlag=Y";
	        }
	        else
	       	{
	       		url = url+"&DCRGFlag=N";
	       		if(lStr == 'DCRG')
	       		{
	       			alert(NODCRG);
	       			hideProgressbar();
	       			return false;
	       		}
	        }
	         if(Number(cvpAmnt) >0 && document.getElementById("hidCVP").value == "Y")
	        {
	        	url = url+"&CVPFlag=Y"
	        }
	        else
	        {
	        	url = url+"&CVPFlag=N";
	        	if(lStr == 'CVP')
	       		{
	       			alert(NOCVP);
	       			hideProgressbar();
	       			return false;
	       		}
	        }
	        // if(Number(pensionAmnt) >0 && document.getElementById("hidPension").value == "Y")
	        if(document.getElementById("hidPension").value == "Y")
	        {
	        	var pnsnFalg = "Y";
	        	if(document.getElementById("cmbFPPension"))
				{
					if( document.getElementById("hidcmbFPPension").value == 'Y')
					{
						pnsnFalg = "N";
					}
				}
	        	url = url+"&PENSIONFlag="+pnsnFalg;
	        }
	        else
	        {
	        	url = url+"&PENSIONFlag=N";
	        	if(lStr == 'PENSION')
	       		{
	       			alert(NOPENSION);
	       			hideProgressbar();
	       			return false;
	       		}
	        }
		}
		url = url+"&AuditFlag="+document.getElementById("auditFlag").value;

		message = 'Are You Sure to Generate the Bill';
		var x = window.confirm(message);
		if(x)
		{
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null)
			{
				alert (AL_AJAX);
				hideProgressbar();
				return;
			}
			xmlHttp.onreadystatechange=function()
			{
				 if (xmlHttp.readyState==complete_state)
				 {
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
					if( XmlHiddenValues[0].childNodes[1] != null)
					{
						alert(XmlHiddenValues[0].childNodes[1].text);
						hideProgressbar();
						return false;
					}
					if (xmlHttp.readyState==complete_state)
					{

						var subId;
						if(lStr == 'CVP')
						{
							subId = 11;
						}
						else if (lStr == 'DCRG')
						{
							subId= 10;
						}
						else if(lStr == 'PENSION')
						{
							subId = 9;
						}

						var height = screen.height - 100;
					   	var width = screen.width;

						if(lStr == 'PENSION')
						{
							openCurntMntPopUp();
							/*message = INCLUDECURRMNTHPNSN;
							var x = window.confirm(message);
							if(x)
							{
								var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId='+subId+'&PPONo='+ppoNo+'&auditFlag='+document.getElementById("auditFlag").value+'&isNewSavingBill=Y&chkbxCrntMnt=Y';
							}
							else
							{
								var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId='+subId+'&PPONo='+ppoNo+'&auditFlag='+document.getElementById("auditFlag").value+'&isNewSavingBill=Y';
							}*/
						}
						else
						{
							var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId='+subId+'&PPONo='+ppoNo+'&auditFlag='+document.getElementById("auditFlag").value+'&isNewSavingBill=Y';

						}
						if(url2.length > 0)
						{
							urlstring = urlstring + url2;
						}
						//urlstring = urlstring+run()
						var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";

					   	if(lStr == 'CVP' || lStr == 'DCRG')
						{
					   		if((lStr == 'DCRG') && (document.getElementById("hidCaseTypeFlag").value == 'C' || document.getElementById("hidCaseTypeFlag").value == 'S'))
							{
								if(document.getElementById("txtProDcrgAmnt") != null)
								{
									dcrgAmnt = Number(document.getElementById("txtDcrgAmnt").value) - Number(document.getElementById("txtProDcrgAmnt").value);
								}
								else
								{
									dcrgAmnt = Number(document.getElementById("txtDcrgAmnt").value);
								}
								urlstring = urlstring+"&DCRGAmt="+dcrgAmnt;
							}
					   		if((lStr == 'CVP') && (document.getElementById("hidCaseTypeFlag").value == 'C' || document.getElementById("hidCaseTypeFlag").value == 'S'))
							{
								if(document.getElementById("txtProCvpAmnt") != null)
								{
									cvpAmnt = Number(document.getElementById("txtCvpAmnt").value) - Number(document.getElementById("txtProCvpAmnt").value);
								}
								else
								{
									cvpAmnt = Number(document.getElementById("txtCvpAmnt").value);
								}
								urlstring = urlstring+"&CVPAmt="+cvpAmnt;
							}
					   		var CVPDCRGWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
					   		globArray[10] = CVPDCRGWindow;
					   	}
					}
				 }
			}
			xmlHttp.open("POST",url,true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send(url);
		}
		else
		{
			hideProgressbar();
			return false;
		}
	}
	function showBill1(url)
	{
	window.open(url,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=no,resizable=no,top=40,left=40,width=400,height=300");
//	window.open(url);
	}
	function displyBankAndBranch()
	{
		if(document.getElementsByName("txtFmName"))
		{
			var x = document.getElementsByName("txtFmName").length;
			for(var j =0 ;j<x;j++)
			{
				for(var i=0;i<document.getElementById("cmbBank").length;i++)
				{
					if(document.getElementById("cmbBank").value == document.getElementById("cmbBank").options[i].value)
					{
						document.getElementById("cmbBankFd"+j).options[0].value = document.getElementById("cmbBank").options[i].value;
						document.getElementById("cmbBankFd"+j).options[0].innerHTML = document.getElementById("cmbBank").options[i].innerHTML;
						document.getElementById("cmbBankFd"+j).disabled = "disabled";
					}
				}
				for(var i=0;i<document.getElementById("cmbBranch").length;i++)
				{
					if(document.getElementById("cmbBranch").value == document.getElementById("cmbBranch").options[i].value)
					{
						document.getElementById("cmbBranchFd"+j).options[0].value = document.getElementById("cmbBranch").options[i].value;
						document.getElementById("cmbBranchFd"+j).options[0].innerHTML = document.getElementById("cmbBranch").options[i].innerHTML;
						document.getElementById("cmbBranchFd"+j).disabled = "disabled";
					}
				}
			}
		}
		if(document.getElementById("mandtryBank"))
		{
			if(document.getElementById("cmbBranch").value != '-1')
			{
				document.getElementById("mandtryBank").style.display = "inline";
			}
			else
			{
				document.getElementById("mandtryBank").style.display = "none";
			}
		}
	}
	function setPercentageAmt(index)
	{
		var Relelement = document.getElementsByName("cmbRelation");
		var FpPrcnt = document.getElementsByName("txtFMPercent");
		var marriedFlag = document.getElementsByName("chkbxFMMarried");
		var FMDOD = document.getElementsByName("txtFMDateOfDeath");
		var minorFlag = document.getElementsByName("chkbxFMMajorMinor");
		var dFlag = 0;
		for(var i=0;i<Relelement.length;i++)
		{
			if(( Relelement[i].value == 'Son' || Relelement[i].value == 'Daughter' || Relelement[i].value == 'Other') && (marriedFlag[i].checked)  || (FMDOD[i].value.length > 0))
			{
				FpPrcnt[i].value = 0;
				alert(PER_ZERO);
				FpPrcnt[i].disabled = "disabled";
			}
			else
			{
				FpPrcnt[i].disabled = "";
			}
			if(FMDOD[i].value.length > 0)
			{
				dFlag = Number(dFlag)+1;
			}
			if(Relelement[i].value == 'Mother' || Relelement[i].value == 'Father' || Relelement[i].value == 'Spouse')
			{
				minorFlag[i].checked = false;
				//marriedFlag[i].checked = true;
				minorFlag[i].disabled = "disabled";
			}
			else
			{
				minorFlag[i].disabled = "";
			}
		}
		if(dFlag == Relelement.length)
		{
			alert(CHNGCASESTATUSCLOSE);
			goToFieldTab("cmbPnsnStatus",0);

		}
	}
	function setMinorFlag()
	{
		var Relelement = document.getElementsByName("cmbRelation");
		var marriedFlag = document.getElementsByName("chkbxFMMarried");
		var minorFlag = document.getElementsByName("chkbxFMMajorMinor");
		for(var i=0;i<Relelement.length;i++)
		{
			if(Relelement[i].value == 'Mother' || Relelement[i].value == 'Father' || Relelement[i].value == 'Spouse')
			{
				minorFlag[i].checked = false;
				marriedFlag[i].checked = true;
				minorFlag[i].disabled = "disabled";
			}
			else
			{
				minorFlag[i].disabled = "";
			}
		}
	}
	function setPercentageDisable(index,lStr)
	{
		document.getElementById("txtFMPercent"+index).disabled = lStr;

	}
	function setComDate(lStrDate)
	{
		lStrDate = new String(lStrDate.value);
		if(lStrDate == "")
		{
			document.getElementById("txtCommensionDate").value = (new String(""));
			return;
		}
		var lArrDate = lStrDate.split("/");
		var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
		date.setDate(date.getDate() + 1);
		if(date.getMonth()==11)
		  {
		  	document.getElementById("txtCommensionDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
		  }
		else
		  {
		    date.setMonth(date.getMonth()+ 1 );
		    document.getElementById("txtCommensionDate").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()).length > 1 ? date.getMonth() : "0" + date.getMonth())+"/"+date.getFullYear();
		  }
		}
		function setRetDate1(lStrDate)
		{
			lStrDate = new String(lStrDate.value);
			if(lStrDate == "")
			{
				document.getElementById("txtCommensionDate").value = (new String(""));
				return;
			}
			var lArrDate = lStrDate.split("/");
			var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
			date.setDate(date.getDate() - 1);
			if(date.getMonth()==11)
			 {
			  	document.getElementById("txtDateOfRetirement").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
			  }
			else
			  {
			    document.getElementById("txtDateOfRetirement").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()).length > 1 ? date.getMonth() : "0" + date.getMonth())+"/"+date.getFullYear();
			  }
		}
//		function setFPDate(lStrDate)
//		{
//			lStrDate = new String(lStrDate.value);
//			if(lStrDate == "")
//			{
//				document.getElementById("txtFp1Date").value = (new String(""));
//				document.getElementById("txtFp2Date").value = (new String(""));
//				return;
//			}
//			var lArrDate = lStrDate.split("/");
//			var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
//			date.setFullYear(date.getFullYear()+7);
//			if(date.getMonth()==11)
//			 {
//			  	document.getElementById("txtFp1Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
//			  	date.setFullYear(date.getFullYear()+7);
//			  	document.getElementById("txtFp2Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
//			  }
//			else
//			  {
//			    date.setMonth(date.getMonth()+ 1 );
//				document.getElementById("txtFp1Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()).length > 1 ? date.getMonth() : "0" + date.getMonth())+"/"+date.getFullYear();
//				document.getElementById("txtFp2Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()).length > 1 ? date.getMonth() : "0" + date.getMonth())+"/"+date.getFullYear();
//			  }
//		}
		function setFp1Date()
		{

			var pnsnType = document.getElementById("cmbPnsnType").value;
			if(pnsnType == 'Family' || pnsnType == 'Family (Lost)')
			{
				lStrDate = document.getElementById("txtDateOfDeath").value;
			}
			else
			{
				lStrDate = document.getElementById("txtDateOfBirth").value;
			}

			var lArrDate = lStrDate.split("/");
			var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
			if(lStrDate == "")
			{
				document.getElementById("txtFp1Date").value = (new String(""));
				return;
			}
			if(pnsnType == 'Family' || pnsnType == 'Family (Lost)')
			{
				date.setFullYear(date.getFullYear()+7);
			}
			else
			{
				date.setFullYear(date.getFullYear()+65);
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
				if(document.getElementById("txtFp1Date").value.length == 0)
				{
					document.getElementById("txtFp1Date").value =  (new String(x).length > 1 ? x : "0" +new String(x)) + "/" +(new String(lArrDate[1] -1).length > 1 ? lArrDate[1] -1 : "0" + new String(lArrDate[1] -1))+"/"+date.getFullYear();
				}
			}
			else
			{
				if(document.getElementById("txtFp1Date").value.length == 0)
				{
					document.getElementById("txtFp1Date").value = (new String(lArrDate[0]-1).length > 1 ? lArrDate[0]-1 : "0" + new String(lArrDate[0]-1)) + "/" +(new String(lArrDate[1]).length > 1 ? lArrDate[1] : "0" + new String(lArrDate[1]) )+"/"+date.getFullYear();
				}

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
				if(document.getElementById("txtFp2Date").value.length == 0)
				{
					document.getElementById("txtFp2Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +"12"+"/"+date.getFullYear();
				}
			 }
			 else
			 {
			   date.setMonth(date.getMonth()+ 1 );
			   if(document.getElementById("txtFp2Date").value.length == 0)
			   {
				   document.getElementById("txtFp2Date").value = (new String(date.getDate()).length > 1 ? date.getDate() : "0" + date.getDate()) + "/" +(new String(date.getMonth()).length > 1 ? date.getMonth() : "0" + date.getMonth())+"/"+date.getFullYear();
			   }
			 }
		}


	function setAlive(set)
	{
		document.getElementById("txtDateOfDeath").disabled=set;
		document.getElementById("imgDateOfDeath").disabled=set;
		document.getElementById("txtDateOfDeath").value = "";
		setOmrType();
		disableDeathDateAndLastPaidDate();
	}
	/*function setSeen(set)
	{
		document.getElementById("txtSeenDate").disabled=set;
		document.getElementById("imgSeenDate").disabled=set;
		getRevalidYear();
	}*/
	function setSeen(set)
	{
		document.getElementById("txtSeenDate").disabled=set;
		document.getElementById("imgSeenDate").disabled=set;
		if(set == true)
		{
			document.getElementById("seenMnd").style.display = "none";
		}
		else
		{
			document.getElementById("seenMnd").style.display = "inline";
		}
		getRevalidYear();
	}
	function setDCRG(set)
	{
		if(set == 'Y')
		{
			document.getElementById("txtDcrgDate").disabled="";
			document.getElementById("radioDCRGY").checked = true;
		}
		else
		{
			document.getElementById("txtDcrgDate").disabled=true;
			document.getElementById("radioDCRGN").checked = true;
		}
	}
	function setCVP(set)
	{
		if(set == 'Y')
		{
			document.getElementById("txtCvpDate").disabled=lStrDisable;
			document.getElementById("txtCvpRestorianDate").disabled=lStrDisable;
			document.getElementById("radioCVPY").checked = true;
		}
		else
		{
			document.getElementById("txtCvpDate").disabled="disabled";
			document.getElementById("txtCvpRestorianDate").disabled="disabled";
			document.getElementById("radioCVPN").checked = true;
		}
	}
	function setOrderNo()
	{
		if(document.getElementById("txtPppoNo").value != null)
		{
			document.getElementById("txtCvpOrderNo").value=document.getElementById("txtPppoNo").value
			document.getElementById("txtDcrgOrderNo").value=document.getElementById("txtPppoNo").value
		}
	}
	function showCloseDate()
	{
		if(document.getElementById("cmbPnsnStatus").value == "Close")
		{
			document.getElementById("PPOClsDate").style.display = "inline" ;
			document.getElementById("PPOClsLbl").style.display = "inline" ;
			document.getElementById("rowHeldReason").style.display = "inline" ;
			document.getElementById("PPOWHDate").style.display = "none" ;
			document.getElementById("PPOWHLbl").style.display = "none" ;
			document.getElementById("rowHeldReason1").style.display = "none" ;
			document.getElementById("rowHeldReason2").style.display = "none" ;
			document.getElementById("transferTres2").style.display = "none" ;
			document.getElementById("transferTres1").style.display = "none" ;
			document.getElementById("transferContTres2").style.display = "none" ;
			document.getElementById("transferContTres1").style.display = "none" ;
			if(document.getElementById("clsprps2"))
			{
				document.getElementById("clsprps2").style.display = "none" ;
			}
		}
		else if(document.getElementById("cmbPnsnStatus").value == "With Held")
		{
			document.getElementById("PPOWHDate").style.display = "inline" ;
			document.getElementById("PPOWHLbl").style.display = "inline" ;
			document.getElementById("PPOClsDate").style.display = "none" ;
			document.getElementById("PPOClsLbl").style.display = "none" ;
			document.getElementById("rowHeldReason").style.display = "inline" ;
			document.getElementById("rowHeldReason1").style.display = "inline" ;
			document.getElementById("rowHeldReason2").style.display = "inline" ;
			document.getElementById("transferTres2").style.display = "none" ;
			document.getElementById("transferTres1").style.display = "none" ;
			document.getElementById("transferContTres2").style.display = "none" ;
			document.getElementById("transferContTres1").style.display = "none" ;
			if(document.getElementById("clsprps2"))
			{
				document.getElementById("clsprps2").style.display = "inline" ;
			}
		}
		else if(document.getElementById("cmbPnsnStatus").value == "ContinueTransfer")
		{
			document.getElementById("PPOWHDate").style.display = "none" ;
			document.getElementById("PPOWHLbl").style.display = "none" ;
			document.getElementById("PPOClsDate").style.display = "none" ;
			document.getElementById("PPOClsLbl").style.display = "none" ;
			document.getElementById("rowHeldReason").style.display = "inline" ;
			document.getElementById("rowHeldReason1").style.display = "none" ;
			document.getElementById("rowHeldReason2").style.display = "none" ;
			document.getElementById("transferContTres2").style.display = "inline" ;
			document.getElementById("transferContTres1").style.display = "inline" ;
			document.getElementById("transferTres2").style.display = "none" ;
			document.getElementById("transferTres1").style.display = "none" ;
			if(document.getElementById("clsprps2"))
			{
				document.getElementById("clsprps2").style.display = "inline" ;
			}
		}
		else if(document.getElementById("cmbPnsnStatus").value == "CloseTransfer")
		{
			document.getElementById("PPOClsDate").style.display = "inline" ;
			document.getElementById("PPOClsLbl").style.display = "inline" ;
			document.getElementById("PPOWHDate").style.display = "none" ;
			document.getElementById("PPOWHLbl").style.display = "none" ;
			document.getElementById("rowHeldReason").style.display = "inline" ;
			document.getElementById("rowHeldReason1").style.display = "none" ;
			document.getElementById("rowHeldReason2").style.display = "none" ;
			document.getElementById("transferTres2").style.display = "inline" ;
			document.getElementById("transferTres1").style.display = "inline" ;
			document.getElementById("transferContTres2").style.display = "none" ;
			document.getElementById("transferContTres1").style.display = "none" ;
			if(document.getElementById("clsprps2"))
			{
				document.getElementById("clsprps2").style.display = "inline" ;
			}
		}
		else
		{
			document.getElementById("PPOClsDate").style.display = "none" ;
			document.getElementById("PPOClsLbl").style.display = "none" ;
			document.getElementById("PPOWHDate").style.display = "none" ;
			document.getElementById("PPOWHLbl").style.display = "none" ;
			document.getElementById("rowHeldReason").style.display = "none" ;
			document.getElementById("rowHeldReason1").style.display = "none" ;
			document.getElementById("rowHeldReason2").style.display = "none" ;
			document.getElementById("transferTres1").style.display = "none" ;
			document.getElementById("transferTres2").style.display = "none" ;
			document.getElementById("transferContTres2").style.display = "none" ;
			document.getElementById("transferContTres1").style.display = "none" ;
			if(document.getElementById("clsprps2"))
			{
				document.getElementById("clsprps2").style.display = "inline" ;
			}
		}
	}
	function getSancAuth()
	{
		if(document.getElementById("CmbpensionClass").value == "P")
		{
			//document.getElementById("sancAuthCmbTd").style.display = "none";
			document.getElementById("sancAuthCmb").style.display = "inline";
			if(document.getElementById("cmbPnsnType").value == '-1')
			{
				document.getElementById("sancAuthCmb").value = 10046;
				document.getElementById("txtSancAuth").style.display = "block";
			}
		/*	else
			{
				document.getElementById("txtSancAuth").style.display = "none";
			}*/
			document.getElementById("hidInwardStauts").value = 'P'

		}
		else
		{
			document.getElementById("sancAuthCmbTd").style.display = "inline";
			if(document.getElementById("hidCaseId").value.length <= 0)
			{
				document.getElementById("hidInwardStauts").value = 'R';
				document.getElementById("sancAuthCmb").value = "-1";
				document.getElementById("txtSancAuth").style.display = "none";
			}
		}
	}

	function setPPOPrifix(lthis)
	{
		if(lthis.value.length > 0 && document.getElementById("txtPpoNo").disabled != true)
		{
			document.getElementById("txtPpoNo").value = lthis.value;
		}
	}
	function getPreFixCmb()
	{
		if(document.getElementById("txtPpoNo").disabled != true)
		{
			req = createXMLHttpRequest();
			if(req != null)
			{
				var uri = "ifms.htm?actionFlag=getSanctAuthPreFix&sancAuthCmb="+document.getElementById("sancAuthCmb").value;
				req.open("post", uri, true);
				req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
				req.onreadystatechange = getPreFixSancAuthAjx;
				req.send(uri);
			}
			else
			{
				alert (AL_AJAX);
				return;
			}
		}
	}
	function getPreFixSancAuthAjx()
	{
		if(req.readyState==complete_state)
		{
			var XMLDoc = req.responseXML.documentElement;
			var XmlResValues = XMLDoc.getElementsByTagName('XMLDOC');
	     	document.getElementById("txtPpoNo").value = XmlResValues[0].childNodes[0].text;
		}
	}
	function getLPCDtls()
	{
		if( document.getElementById("CmbLPC").value == 'Y')
		{
			document.getElementById("txtLPCDate").disabled=frmDis;
			document.getElementById("txtLPCAuth").disabled=frmDis;
			var x = document.getElementsByName("mandtrylpc");
			for(var i=0;i<x.length;i++)
			{
				x[i].style.display = "inline";
			}
		}
		else
		{
			document.getElementById("txtLPCDate").value="";
			document.getElementById("txtLPCAuth").value="-1";
			document.getElementById("txtLPCDate").disabled=true;
			document.getElementById("txtLPCAuth").disabled=true;
			var x = document.getElementsByName("mandtrylpc");
			for(var i=0;i<x.length;i++)
			{
				x[i].style.display = "none";
			}
		}
	}
	function getFrom22Dtls()
	{
		if(document.getElementById("CmbFrom22").value == 'Y')
		{
			document.getElementById("txtFrm22Date").disabled=frmDis;
			document.getElementById("txtform22Auth").disabled=frmDis;
			var x = document.getElementsByName("mandtryFrm22");
			for(var i=0;i<x.length;i++)
			{
				x[i].style.display = "inline";
			}
		}
		else
		{
			document.getElementById("txtFrm22Date").value="";
			document.getElementById("txtform22Auth").value="-1";
			document.getElementById("txtFrm22Date").disabled=true;
			document.getElementById("txtform22Auth").disabled=true;
			var x = document.getElementsByName("mandtryFrm22");
			for(var i=0;i<x.length;i++)
			{
				x[i].style.display = "none";
			}
		}
	}
	function setTi()
	{
		if(document.getElementById("hidCaseId").value == "")
			{
				document.getElementById("txtTiPrct").value = "";
			}
			document.getElementById("txtTiPrct").disabled = "disabled";
		setTIAmount();
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
	function setRetDate(lStrDate)
	{
		lStrDate = new String(lStrDate.value);
		if(lStrDate == "")
		{
			document.getElementById("txtDateOfRetirement").value = (new String(""));
			return;
		}
		var lArrDate = lStrDate.split("/");
		var date = new Date(lArrDate[1] + "/" + lArrDate[0] + "/" + lArrDate[2]);
		date.setFullYear(date.getFullYear() + 58);
		date.setDate(DaysArray2(date.getMonth(),date.getFullYear()));
		/*if(lArrDate[1]==11)
		 {
		  	document.getElementById("txtDateOfRetirement").value = (new String(DaysArray2(date.getMonth(),date.getFullYear())).length > 1 ? DaysArray2(date.getMonth(),date.getFullYear()) : "0" + DaysArray2(date.getMonth(),date.getFullYear())) + "/" +"12"+"/"+date.getFullYear();
		 }
		else
		  {*/
			document.getElementById("txtDateOfRetirement").value = (new String(DaysArray2(date.getMonth(),date.getFullYear())).length > 1 ? DaysArray2(date.getMonth(),date.getFullYear()) : "0" + DaysArray2(date.getMonth(),date.getFullYear())) + "/" +(new String(lArrDate[1]).length > 1 ? lArrDate[1] : "0" + lArrDate[1])+"/"+date.getFullYear();
		  //}
	}

	function setOrgPnsnValue()
	{
		var x =0;
		if(document.getElementById("txtCvpMonthlyAmnt").value.length >0)
		{
			x = parseFloat(document.getElementById("txtCvpMonthlyAmnt").value);
		}
		document.getElementById("origPensn3").value = document.getElementById("txtPensionableAmnt").value;
		document.getElementById("rdcdPensn3").value = parseFloat(document.getElementById("hidBasicAmt").value)+parseFloat(document.getElementById("txtDpAmnt").value) - x ;
		document.getElementById("rdcdPensn3").value = parseFloat(document.getElementById("rdcdPensn3").value).toFixed(2);
		setValidAmountFormat(document.getElementById("origPensn3"));
		amountFormat(document.getElementById("rdcdPensn3"))


	}
	function  goToNewRow(lThis)
	{
		var lStr = document.getElementsByName("txtFmName");
		var x = document.getElementsByName("txtFmName").length-1;
		var y = document.getElementsByName("txtFMDateOfDeath");
		if(lStr[x].value.length > 0)
		{
			if(lThis.id == y[y.length -1].id)
			{
				Table_Two_AddRow();
				var lStr = document.getElementsByName("txtFmName");
				var x = document.getElementsByName("txtFmName").length-1;
				lStr[x].focus();
			}
		}
		else
		{
			if(document.getElementsByName("txtNMName"))
			{
				var lNmnStr = document.getElementsByName("txtNMName");
				lNmnStr[0].focus();
			}
		}
	}
	function goToNomineeRow(lThis)
	{
		var lStr = document.getElementsByName("txtNMName");
		var x = document.getElementsByName("txtNMName").length-1;
		var y = document.getElementsByName("txtNMPercentage");
		if(lStr[x].value.length > 0)
		{
			if(lThis.id == y[y.length -1].id)
			{
				Table_One_AddRow()
				var lStr = document.getElementsByName("txtNMName");
				var x = document.getElementsByName("txtNMName").length-1;
				lStr[x].focus();
			}
		}
		else
		{
			goToRemarks();
		}
	}
	function chkTiAndDpPrcnt(lThis)
	{
		var lStr= lThis.value;

		var tokens = lStr.split(".");

		if( tokens[0] > 999 )
	    {
			alert(VALID_VAL);
			lThis.focus();
			lThis.value = "";
		}
		if( tokens[1] > 99 )
		{
			alert(VALID_VAL);
			lThis.focus();
			lThis.value = "";
    	}
	}
	function goToRemarks()
	{
		var x = document.getElementsByName("txtNMName").length-1;
		var lStr = document.getElementsByName("txtNMName");
		var lStr2 = document.getElementsByName("txtNMPercentage");
		if(lStr[x].value.length > 0)
		{
			lStr2[x].focus();
		}
		else
		{
			goToRemFrmOutwd();
		}
	}
	function goToRemFrmOutwd()
	{
		if(document.getElementById("cmbSubTreasury").value != '-1' && (document.getElementById("cmbSubTreasury").value  != document.getElementById("cmbTreasury").value))
		{
			var x = document.getElementsByName("txtOutWrdRemarks").length;

			if(x == '1')
			{
				document.getElementById("txtOutwardDt0").focus();
			}
			else if (x >1)
			{
				if(document.getElementById("inWardStatus0").value == 'Reject')
				{
					document.getElementById("txtOutwardDt"+Number(x)-1).focus();
				}
				else
				{
					goToRemarsTb();
				}
			}
		}
		else
		{
			goToRemarsTb();
		}

	}
	function tabexit()
	{
		if((document.getElementById("inWardStatus0").value) == 'Reject')
		{
			var x = document.getElementsByName("txtOutWrdRemarks").length-1;

			document.getElementById("txtOutwardDt"+x).focus();
		}
		else
		{
			goToRemarsTb();
		}
	}

	function tabexitAddRow()
	{
		goToRemarsTb();
	}

	function ppoFocus()
	{
    	var elem = document.getElementById("txtPpoNo");
    	caretPos = elem.value.length;
	    if(elem != null) {
	        if(elem.createTextRange) {
	            var range = elem.createTextRange();
	            range.move('character', caretPos);
	            range.select();
	        }
	        else {
	            if(elem.selectionStart) {
	                elem.focus();
	                elem.setSelectionRange(caretPos, caretPos);
	            }
	            else
	                elem.focus();
	        }
	    }
	}
function changeMandtry()
{
	if(document.getElementById("CmbpensionClass").value == 'P')
	{
		var mand = document.getElementsByName("mandtryFinal");
		for(var i=0;i<mand.length;i++)
		{
			mand[i].style.display = "none";
		}
		var mand1 = document.getElementsByName("mandtryProvsnl");
		for(var i=0;i<mand1.length;i++)
		{
			mand1[i].style.display = "inline";
		}
	}
	else
	{
		var mand = document.getElementsByName("mandtryFinal");
		for(var i=0;i<mand.length;i++)
		{
			mand[i].style.display = "inline";
		}
		var mand1 = document.getElementsByName("mandtryProvsnl");
		for(var i=0;i<mand1.length;i++)
		{
			mand1[i].style.display = "none";
		}
	}
}
function validateDcrgAmt(lThis)
{
	var x = lThis.value;
	if(Number(x) > 350000)
	{
		lThis.value = "0.00";
		alert(DCRGLIMIT);
		lThis.focus();
		return false;
	}
}
function getROPButton()
{
	if(document.getElementById("cmbRop").value == 'Y' )
	{
		document.getElementById("ropSpan").style.display = 'inline';
		document.getElementById("Rop1986chkBx").checked = false;
		document.getElementById("Rop1996chkBx").checked = false;
		document.getElementById("Rop2006chkBx").checked = false;
		document.getElementById("Rop1986chkBx").disabled=false;
		document.getElementById("Rop1996chkBx").disabled=false;
		document.getElementById("Rop2006chkBx").disabled=false;
		document.getElementById("ROP1986P").checked = false;
		document.getElementById("ROP1996P").checked = false;
		document.getElementById("ROP2006P").checked = false;
		document.getElementById("dApplyPaid1986").disabled=false;
		document.getElementById("dApplyPaid1996").disabled=false;
		document.getElementById("dApplyPaid2006").disabled=false;
		document.getElementById("ADA").style.display = "none";
		document.getElementById("rADAN").style.display = "none";
		document.getElementById("rADAY").style.display = "none";
		document.getElementById("DA").style.display = "none";
		document.getElementById("rDAY").style.display = "none";
		document.getElementById("rDAN").style.display = "none";
		document.getElementById("dApplyPaid1986").style.display = "none";
		document.getElementById("dApplyPaid1996").style.display = "none";
		document.getElementById("dApplyPaid2006").style.display = "none";

	}
	else if ( document.getElementById("cmbRop").value == 'P') {
		document.getElementById("ropSpan").style.display = 'inline';
		document.getElementById("Rop1986chkBx").checked = true;
		document.getElementById("Rop1996chkBx").checked = true;
		document.getElementById("Rop2006chkBx").checked = true;
		document.getElementById("Rop1986chkBx").disabled='disabled';
		document.getElementById("Rop1996chkBx").disabled='disabled';
		document.getElementById("Rop2006chkBx").disabled='disabled';
		document.getElementById("dApplyPaid1986").style.display = "inline";
		document.getElementById("dApplyPaid1996").style.display = "inline";
		document.getElementById("dApplyPaid2006").style.display = "inline";
		document.getElementById("ROP1986P").checked = true;
		document.getElementById("ROP1996P").checked = true;
		document.getElementById("ROP2006P").checked = true;
		document.getElementById("dApplyPaid1986").disabled='disabled';
		document.getElementById("dApplyPaid1996").disabled='disabled';
		document.getElementById("dApplyPaid2006").disabled='disabled';
		document.getElementById("Apply").style.display = "none";
		document.getElementById("Applydtls").style.display = "none";
	}
	else
	{
		document.getElementById("ropSpan").style.display = '';
		document.getElementById("Rop1986chkBx").checked = 'true';
		document.getElementById("dApplyPaid1986").style.display = '';
		document.getElementById("ROP1986P").checked = 'true';
		document.getElementById("Rop1986chkBx").disabled = 'disabled';
		document.getElementById("Rop1996chkBx").checked = '';

		document.getElementById("ROP1996A").checked = '';
		document.getElementById("ROP1996P").checked = '';
		document.getElementById("Rop1996chkBx").disabled = 'disabled';
		document.getElementById("Rop2006chkBx").checked = '';
		document.getElementById("ROP2006A").checked = '';
		document.getElementById("ROP2006P").checked = '';
		document.getElementById("Rop2006chkBx").disabled = 'disabled';

		document.getElementById("dApplyPaid1986").disabled='disabled';
		document.getElementById("dApplyPaid1996").style.display = 'none';
		document.getElementById("dApplyPaid2006").style.display = 'none';
	}
	setBasicPension();
}

/* Get Pension Case History Details By AJAX Added By Sagar Start  */

	function getPnsnCaseHst()
	{
		//disable();
		showProgressbar();
		window.setTimeout("CaseHstAjaxSave()",1000);
		//CaseHstAjaxSave();
	}

	function CaseHstAjaxSave()
	{
		xmlHttp=GetXmlHttpObject();

		if (xmlHttp==null)
		{
			alert ("Your browser does not support AJAX!");
			return;
		}
		var url = '&txtPpoNo='+document.getElementById("txtPpoNo").value+'&hidCaseStatus='+document.getElementById("hidCaseStatus").value+'&hidLocation='+document.getElementById("cmbTreasury").value;
		var uri = "ifms.htm?actionFlag=getPensionCaseHistory";
		url = uri + url;
		xmlHttp.onreadystatechange=caseHstStateChanged;
		xmlHttp.open("POST",uri,false);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(url);

	}

	function caseHstStateChanged()
	{
		if (xmlHttp.readyState==complete_state)
		{
			//enable_div();
			hideProgressbar();
			//enable();
			XMLDoc = xmlHttp.responseXML.documentElement;
			if(XMLDoc != null)
			{
				var XmlPrintValue = XMLDoc.getElementsByTagName('XMLPRINTDOC');
				document.getElementById("hidPrintString").value = XmlPrintValue[0].childNodes[0].text;

				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				var lTotalAmt = 0;

				for(var i=0;i<XmlHiddenValues.length;i++)
				{
					var newRow = document.all("CaseHstTab").insertRow(i);

					newRow.className = "odd";
					var cell1 = newRow.insertCell();
					var cell2 = newRow.insertCell();
					var cell3 = newRow.insertCell();
					var cell4 = newRow.insertCell();
					var cell5 = newRow.insertCell();
					var cell6 = newRow.insertCell();
					var cell7 = newRow.insertCell();
					var cell8 = newRow.insertCell();

					cell1.align="center"; cell1.width="5%";
					cell2.align="left";	  cell2.width="10%";
					cell3.align="left"; cell3.width="12%";
					cell4.align="center";  cell4.width="8%";
					cell5.align="right";  cell5.width="10%";
					cell6.align="left";  cell6.width="15%";
					cell7.align="left";  cell7.width="15%";
					cell8.align="left";  cell8.width="15%";

					var lBillType = XmlHiddenValues[i].childNodes[2].text;
					var lBillForMnt = XmlHiddenValues[i].childNodes[7].text;

					cell1.innerHTML = XmlHiddenValues[i].childNodes[0].text;
					cell2.innerHTML = XmlHiddenValues[i].childNodes[8].text;
					cell3.innerHTML = lBillType +''+ lBillForMnt;
					cell4.innerHTML = XmlHiddenValues[i].childNodes[1].text;
					var lBillAmt = Number(XmlHiddenValues[i].childNodes[3].text);
					var lBillDtlId = XmlHiddenValues[i].childNodes[6].text;
					if(lBillType != 'MR')
					{
						cell5.innerHTML = "<a href='#' onclick='getMonthlyPaidBillDtls("+lBillDtlId+",\""+lBillType+"\")' >"+ lBillAmt + ".00</a>";
					}
					else
					{
						cell5.innerHTML = lBillAmt;
					}

					lTotalAmt = lTotalAmt + lBillAmt;
					cell6.innerHTML = XmlHiddenValues[i].childNodes[4].text + '-' + XmlHiddenValues[i].childNodes[5].text;
					cell7.innerHTML = XmlHiddenValues[i].childNodes[9].text;
					cell8.innerHTML = XmlHiddenValues[i].childNodes[10].text;

				}

				document.getElementById("CaseHstTextTab").style.display = 'block';
				document.getElementById("lblTotalHTAmt").innerHTML = "<b>"+lTotalAmt+".00"+"</b>";
				document.getElementById("lblHTAmtInWrd").innerHTML = "<b>"+getAmountInWords(lTotalAmt)+"</b>";
				document.getElementById("btnLoadData").style.visibility = 'hidden';
				document.getElementById("MsgLb").style.visibility = 'hidden';
			}

		}
	}

	function getMonthlyPaidBillDtls(lBillDtlId,lBillType)
	{
		var urlstring = "ifms.htm?actionFlag=getHstMonthlyBillDtls&billDtlId="+lBillDtlId+"&billType="+lBillType;
    	var urlstyle = "height=470,width=330,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no,top=10,left=10";

    	billdtlsopen = window.open(urlstring, "billdtlsopen", urlstyle);
	}

	function closePaidBillDtls(lBillDtlId)
	{
		billdtlsopen.close();
	}


	function getMonthlyPayableBillDtls()
	{
		if(document.getElementById("txtLastPaidDate").value == '')
		{
			alert("Please specify the last paid date.");
			return false;
		}
		
		var lLastPaidDate = document.getElementById("txtLastPaidDate").value;
		var lArrDate = lLastPaidDate.split("/");
		var lStrLstMonth = 0;
     	var lStrLstYear = 0;
		if(Number(lArrDate[1]) == 12)
		{
			lStrLstMonth = Number(1);
       		lStrLstYear = Number(lArrDate[2])+Number(1);
       	}
       	else
       	{
       		lStrLstMonth = Number(lArrDate[1])+Number(1);
       		lStrLstYear = Number(lArrDate[2]);
       	}
		
       	var currentTime = new Date();
		var lStrCrntMonth = currentTime.getMonth() + 1;
		if(Number(lStrCrntMonth) < 10)
	
		lStrCrntMonth = '0'+lStrCrntMonth;
	
       	var lStrCrntYear = currentTime.getFullYear();

		var lStrCrntYYYYMM = Number(lStrCrntYear +''+ lStrCrntMonth);
		var lStrLstDtYYYYMM = Number(lStrLstYear +''+ lStrLstMonth);

		var lStrMonth = 0;
       	var lStrYear = 0;
		
		if(lStrLstDtYYYYMM < lStrCrntYYYYMM)
		{
			lStrMonth = currentTime.getMonth() + 1;
       		lStrYear = lStrCrntYear;
		}
		else
		{
			lStrMonth = lStrLstMonth;
			lStrYear = lStrLstYear;
		}
		
       	var lStrBank = document.getElementById("cmbBank").value;
       	var lStrBranch = document.getElementById("cmbBranch").value;
       	var lStrScheme = document.getElementById("cmbSchemeType").value;

       	var lStrPnsnerCode = document.getElementById("hidpensionerCode").value;
       	var lStrCaseStatus = document.getElementById("hidCaseStatus").value;
       	var url = "&cmbForMonth="+lStrMonth+"&cmbForYear="+lStrYear+"&cmbBank="+lStrBank+"&cmbBranch="+lStrBranch+"&cmbForScheme="+lStrScheme+"&hidpensionerCode="+lStrPnsnerCode+"&pnsnCaseStatus="+lStrCaseStatus;
		getBillValidByAJAXViewPayable(url);
    	//
	}

	function getBillValidByAJAXViewPayable(url)
	{
		req = createXMLHttpRequest();
		if(req != null)
		{
			var baseUrl = "ifms.htm?actionFlag=validateBill&"+url;
			req.open("post", baseUrl, true); 
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = responseBillValidViewPayable; 
			req.send(baseUrl);
		}
		else
		{
			alert (AL_AJAX);
		}
	}
	
	function responseBillValidViewPayable()
	{
		if(req.readyState==complete_state)
		{ 
			var XMLDoc = req.responseXML.documentElement;
			var XMLEntries = XMLDoc.getElementsByTagName("BILL");	
			
			
			var blnResult = XMLEntries[0].text;
			if(blnResult == 'bilApproved')
			{
				alert("  Bill is already created for this month.It can be viewed from the case history. ");
				return false;
				
			}
			else if(blnResult == 'bilNotApproved')
			{
				alert( " Bill is already created for this month. Please approve or reject the bill before viewing the payable details." )
				return false;
			}
			else
			{
				var lLastPaidDate = document.getElementById("txtLastPaidDate").value;
				var lArrDate = lLastPaidDate.split("/");
				var lStrLstMonth = 0;
       			var lStrLstYear = 0;
				if(Number(lArrDate[1]) == 12)
				{
					lStrLstMonth = Number(1);
		       		lStrLstYear = Number(lArrDate[2])+Number(1);
		       	}
		       	else
		       	{
		       		lStrLstMonth = Number(lArrDate[1])+Number(1);
		       		lStrLstYear = Number(lArrDate[2]);
		       	}
				
		       	var currentTime = new Date();
				var lStrCrntMonth = currentTime.getMonth() + 1;
				if(Number(lStrCrntMonth) < 10)
			
				lStrCrntMonth = '0'+lStrCrntMonth;
			
		       	var lStrCrntYear = currentTime.getFullYear();
		
				var lStrCrntYYYYMM = Number(lStrCrntYear +''+ lStrCrntMonth);
				var lStrLstDtYYYYMM = Number(lStrLstYear +''+ lStrLstMonth);
		
				var lStrMonth = 0;
		       	var lStrYear = 0;
				
				if(lStrLstDtYYYYMM < lStrCrntYYYYMM)
				{
					lStrMonth = currentTime.getMonth() + 1;
		       		lStrYear = lStrCrntYear;
				}
				else
				{
					lStrMonth = lStrLstMonth;
					lStrYear = lStrLstYear;
				}
				
		       	var lStrBank = document.getElementById("cmbBank").value;
		       	var lStrBranch = document.getElementById("cmbBranch").value;
		       	var lStrScheme = document.getElementById("cmbSchemeType").value;
		
		       	var lStrPnsnerCode = document.getElementById("hidpensionerCode").value;
		       	var lStrCaseStatus = document.getElementById("hidCaseStatus").value;
		       	var url = "&cmbForMonth="+lStrMonth+"&cmbForYear="+lStrYear+"&cmbBank="+lStrBank+"&cmbBranch="+lStrBranch+"&cmbForScheme="+lStrScheme+"&hidpensionerCode="+lStrPnsnerCode+"&pnsnCaseStatus="+lStrCaseStatus;
			
				var height = screen.height - 500;
   				var width = screen.width - 400;
				var urlstring = "ifms.htm?actionFlag=monthlyPension"+url;
				var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=0,left=0";
    			billdtlsopen = window.open(urlstring, "payableBillDtls", urlstyle);
			}	
		}
	}
/*  Get Pension Case History Details By AJAX Added By Sagar End */
	function getRevalidYear()
	{
		var currYear = document.getElementById("hidCurDate").value;
		var tempRevalidDate = '';
		if(document.getElementById("radioSeenN").checked)
		{
			var retDate = document.getElementById("txtSeenDate").value;
			var lArrDate = retDate.split("/");
			var lArrDate1 = Number(lArrDate[2])+Number(2);
			tempRevalidDate  = new String(lArrDate[0]+"/"+lArrDate[1]+"/"+lArrDate1);
		}
		else
		{
			var retDate = document.getElementById("txtSeenDate").value;
			var lArrDate = retDate.split("/");
			var lArrDate1 = Number(lArrDate[2])+Number(2);
			tempRevalidDate  = new String(lArrDate[0]+"/"+lArrDate[1]+"/"+lArrDate1);
		}
		if(tempRevalidDate.length > 0 && currYear.length > 0)
		{
			if(isProper1(tempRevalidDate,currYear))
			{
				if(document.getElementById("revalidBtn"))
				{
					document.getElementById("revalidLbl").style.display =  "inline";
					document.getElementById("revalidBtn").style.display =  "inline";
				}
			}
			else
			{
				if(document.getElementById("revalidBtn"))
				{
					document.getElementById("revalidLbl").style.display =  "none";
					document.getElementById("revalidBtn").style.display =  "none";
				}
			}
		}
	}
	lStrSanAuth = "";
	function addReavlidRow()
	{
		var lastRow = document.getElementById('RevalidTab').rows.length;
		var newRow = document.all("RevalidTab").insertRow(lastRow);
		lastRow = lastRow - 1;
		var Cell1 = newRow.insertCell();
	   	var Cell2 = newRow.insertCell();
	   	var Cell3 = newRow.insertCell();
	   	var Cell4 = newRow.insertCell();
	   	var Cell5 = newRow.insertCell();
	   	var Cell6 = newRow.insertCell();

	   	Cell1.innerHTML = '<input type="text" size="9" onfocus="changeOnFocus(this)" onKeyup="dateFormat(this);" name="txtOutwrdDt"  id="txtOutwrdDt" onblur="formateWholeDate(this),validateDate(this),changeOnBlur(this),chkRevalidationDate()" />';
	   	Cell2.innerHTML = '<input type="text" size="9" onfocus="changeOnFocus(this)" onKeyup="dateFormat(this);" name="txtInwrdDt"  id="txtInwrdDt" onblur="formateWholeDate(this),validateDate(this),changeOnBlur(this),chkRevalidationDate()" />';
	   	Cell3.innerHTML = '<input type="text" size="9" onfocus="changeOnFocus(this)" onKeyup="dateFormat(this);" name="txtRevalidDt"  id="txtRevalidDt" onblur="formateWholeDate(this),validateDate(this),changeOnBlur(this),chkRevalidationDate()" />';
	   	Cell4.innerHTML = '<input type="text" onblur="changeOnBlur(this)"  onkeypress="upperCase(event)" onfocus="changeOnFocus(this)" name="txtRevalidName" id="txtRevalidName" maxlength="50">';
	   	Cell5.innerHTML = '<select name="cmbRevalidAuth" onblur="changeOnBlur(this)"  id="cmbRevalidAuth"> <option value="-1"> --Select-- </option>'+ lStrSanAuth +'</select>';
	   	Cell6.innerHTML = '<textarea ${read} onblur="changeOnBlur(this)"  rows="2" onfocus="changeOnFocus(this)" name="txtRevalidRemarks" maxlength="600" id="txtRevalidRemarks" style="text-transform: uppercase;" ${read}></textarea> <img src="images/CalendarImages/DeleteIcon.gif" onclick="RemoveRevalidTableRow(this)" />'
	}
	function RemoveRevalidTableRow(obj)
	{
		var rowID = showRowCell(obj);
	    var tbl = document.getElementById("RevalidTab");
	    tbl.deleteRow(rowID);
	}
	function setPnsnHidDate(cellObj)
	{

		var x = 375;
		var y = 570;
		var urlstring = "common/calendar.jsp?requestSent=hidSetDate";
		var urlstyle = "height=230,width=320,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no,top=" + x + ",left=" + y;
	    var newWindow = window.open(urlstring, "Calendar", urlstyle);
	    while(true)
	    {
	    	if(newWindow.closed == true)
	    	{
	    		var rowId = parseInt(showRowCell(cellObj));
	    		var element = document.getElementsByName("txtRevalidDt");
	    		element[rowId - 1].value = document.getElementById("hidSetDate").value;
	    		document.getElementById("hidSetDate").value = "";
	    		//element[rowId - 1].fireEvent('onblur');
	    		break;
	    	}
	    }
	}
	function caseRevalidDtls()
	{
		if(document.getElementById("revalidBtn").style.display == 'inline')
		{
			var reValidYear = "";
			var currYear = document.getElementById("hidCurDate").value;
			var tempRevalidDate = '';
			if(document.getElementById("hidRevalidDate").value.length>0)
			{
				var revalidDate = document.getElementById("hidRevalidDate").value.split("/");
				reValidYear = Number(revalidDate[2])+Number(2);
				tempRevalidDate = new String(revalidDate[0]+"/"+revalidDate[1]+"/"+reValidYear);
			}
			if(tempRevalidDate.length >0 && currYear.length >0 && isProper1(tempRevalidDate,currYear) != false)
			{
				return true;
			}
			else
			{
				alert(ENTRREVALIDDTLS);
				getRevalidPopup();
				return false;
			}
		}
		else
		{
			return true;
		}
	}
	function setPensionClass(lStr)
	{
		if(lStr == '' || lStr != 'P' )
		{
			document.getElementById("CmbpensionClass").value='R';
		}
		else
		{
			document.getElementById("CmbpensionClass").value = 'P';
		}
	}
	function setInwardDate(lStr)
	{
		if(lStr.length > 0)
		{
			document.getElementById("txtPpoInwdDate").value = lStr;
		}
	}
	function setSchemeAndStatus()
	{
		if(document.getElementById("cmbSchemeType").value == "-1")
		{
			document.getElementById("cmbSchemeType").value="RUBARU"
		}
		if(document.getElementById("cmbPnsnStatus").value == "-1")
		{
			document.getElementById("cmbPnsnStatus").value="Continue";
		}
	}
	function setAliveFlag(lStr)
	{
		if(lStr.length > 0)
		{

			document.getElementById("radioAliveN").checked="checked";
			var x = document.getElementById("cmbPnsnType").value;
			if(x == 'Family (Lost)' || x == 'Family')
			{
				document.getElementById("txtDateOfDeath").disabled=true;
				document.getElementById("imgDateOfDeath").disabled=true;
			}
			else
			{
				document.getElementById("txtDateOfDeath").disabled=false;
				document.getElementById("imgDateOfDeath").disabled=false;
			}
			disableDeathDateAndLastPaidDate();
		}
		else
		{
			document.getElementById("radioAliveY").checked="checked";
			document.getElementById("txtDateOfDeath").disabled=true;
			document.getElementById("imgDateOfDeath").disabled=true;
		}
	}
	function disableDeathDateAndLastPaidDate()
	{
		if(document.getElementById("auditFlag").value=='Y')
		{
			var temp = document.getElementById("hidScheme").value;
			if(temp != 'PSB')
			{
				document.getElementById("txtDateOfDeath").disabled=true;
				document.getElementById("txtLastPaidDate").disabled=true;
				document.getElementById("imglastPaidDate").disabled=true;
				document.getElementById("imgDateOfDeath").disabled=true;
			}
			if(temp == 'PSB')
			{
				document.getElementById("txtDateOfDeath").disabled=false;
				document.getElementById("txtLastPaidDate").disabled=false;
				document.getElementById("imglastPaidDate").disabled=false;
				document.getElementById("imgDateOfDeath").disabled=false;
			}
		}
	}
	function setSeenFlag(lStr1,lStr2,lStr3)
	{
		if(lStr1 == 'N')
		{
			document.getElementById("radioSeenN").checked = true;
			if(lStr2 != null)
			{
				document.getElementById("txtSeenDate").value=lStr2;
			}
			document.getElementById("seenMnd").style.display = "none";
			setSeen(true);
		}
		else
		{
			document.getElementById("radioSeenY").checked = true;
			if(lStr2 != null)
			{
				document.getElementById("txtSeenDate").value=lStr2;
			}
			document.getElementById("txtSeenDate").disabled = lStr3;
			document.getElementById("seenMnd").style.display = "inline";
		}
	}
	function setServiceDtls(totSrvc)
	{
		if(totSrvc.length > 0)
		{
			document.getElementById("txtTotalServiceDD").value = totSrvc.substring(0,2);
			document.getElementById("txtTotalServiceMM").value = totSrvc.substring(2,4);
			document.getElementById("txtTotalServiceYY").value = totSrvc.substring(4,6);
		}

	}
	function setSpecial(lStr)
	{
		if(lStr == 'Y')
		{
			document.getElementById("radiosplCaseY").checked = true;
		}
		else
		{
			document.getElementById("radiosplCaseN").checked = true;
		}
	}
	function setMedFlag(lStr)
	{
		if(Number(lStr) > 0)
		{
			document.getElementById("radioMaY").checked = true;
		}
		else
		{
			document.getElementById("radioMaN").checked = true;
			document.getElementById("txtMaAmnt").disabled = true;
		}
	}
	function getAutoManual(lStr)
	{
		if(document.getElementById("CmbCalcType").value == 'M')
		{
			document.getElementById("txtTiPrct").disabled = lStr;
			document.getElementById("txtTiAmnt").disabled = lStr;
			if(document.getElementById("radioMaY").checked && document.getElementById("hidMaAmt").value >= 0)
			{
				document.getElementById("txtMaAmnt").disabled = lStr;
			}
			document.getElementById("txtIr").disabled = lStr;
			document.getElementById("txtDpPrct").disabled = lStr;
			document.getElementById("radiosplCaseY").disabled=lStr;
			document.getElementById("radiosplCaseN").disabled=lStr;
		}
		else if(document.getElementById("CmbCalcType").value == 'A')
		{
			//setTi();
			setMA();
			document.getElementById("txtIr").disabled = 'disabled';
			document.getElementById("txtDpPrct").disabled = 'disabled';
		}
		if(document.getElementById("mandtryROP"))
		{
			if(document.getElementById("CmbCalcType").value == 'A')
			{
				document.getElementById("mandtryROP").style.display = "inline";
			}
			else
			{
				document.getElementById("mandtryROP").style.display = "none";
			}

		}
	}
	function setnextTab1()
	{
		if(document.getElementById("txtIr").disabled != "")
		{
			setnextTab();
		}
	}
	function changeDeathDate(lStr)
	{
		if(document.getElementById("cmbPnsnType").value == 'Family')
		{
			document.getElementById("radioAliveN").checked = true;
			document.getElementById("divDeathDate").innerHTML = "Death Date"
			document.getElementById("radioAliveN").disabled = "disabled";
			document.getElementById("radioAliveY").disabled = "disabled";
			document.getElementById("txtDateOfDeath").disabled = "disabled";
			if(document.getElementById("txtDateOfRetirement").value.length>0)
			{
				document.getElementById("txtDateOfDeath").value  = document.getElementById("txtDateOfRetirement").value;
			}

			if(Number(document.getElementById("txtPensionableAmnt").value) > 0 )
			{
				document.getElementById("txtFp1Amnt").value = document.getElementById("txtPensionableAmnt").value;
			}
			else
			{
				document.getElementById("txtFp1Amnt").value = document.getElementById("txtBasicPensionAmnt").value;
			}
			document.getElementById("txtFp1Amnt").disabled = true;
			//document.getElementById("radioCVPY").disabled=true;
			//document.getElementById("radioCVPN").disabled=true;
			//document.getElementById("radioCVPN").checked=true;
			//document.getElementById("txtCvpAmnt").disabled=true;
			//document.getElementById("txtCvpOrderNo").disabled = true;
			//document.getElementById("txtCvpAmnt").value = "0.00";
			//document.getElementById("txtCvpOrderNo").value = "";
			//disableCVPBlock("disabled");
		}
		else if(document.getElementById("cmbPnsnType").value == 'Family (Lost)')
		{
			document.getElementById("divDeathDate").innerHTML = "FIR Date"
			document.getElementById("radioAliveN").checked = true;
			document.getElementById("radioAliveN").disabled = "disabled";
			document.getElementById("radioAliveY").disabled = "disabled";
			document.getElementById("txtDateOfDeath").disabled = "disabled";
			document.getElementById("txtDateOfDeath").value  = document.getElementById("txtDateOfRetirement").value;

			if(Number(document.getElementById("txtPensionableAmnt").value) > 0 )
			{
				document.getElementById("txtFp1Amnt").value = document.getElementById("txtPensionableAmnt").value;
			}
			else
			{
				document.getElementById("txtFp1Amnt").value = document.getElementById("txtBasicPensionAmnt").value;
			}
			document.getElementById("txtFp1Amnt").disabled = true;
			//document.getElementById("radioCVPY").disabled=true;
			//document.getElementById("radioCVPN").disabled=true;
			//document.getElementById("radioCVPN").checked=true;
		//	document.getElementById("txtCvpAmnt").disabled=true;
			//document.getElementById("txtCvpOrderNo").disabled = true;
			//document.getElementById("txtCvpAmnt").value = "0.00";
			//document.getElementById("txtCvpOrderNo").value = "";
			//disableCVPBlock("disabled");
		}
		else
		{
			if( document.getElementById("txtDateOfDeath").value.length > 0)
			{
				document.getElementById("radioAliveN").checked = true;
			}
			else
			{
				document.getElementById("radioAliveY").checked = true;
			}
			document.getElementById("divDeathDate").innerHTML = "Death Date"
			document.getElementById("radioAliveN").disabled = lStr;
			document.getElementById("radioAliveY").disabled = lStr;
			if(document.getElementById("radioAliveY").checked)
			{
				document.getElementById("txtDateOfDeath").value = "";
				document.getElementById("txtDateOfDeath").disabled = "disabled";
			}
			else
			{
				document.getElementById("txtDateOfDeath").disabled = "disabled";
			}
			if(lStrDisable == "")
			{
				document.getElementById("txtFp1Amnt").disabled = false;
				//document.getElementById("txtCvpOrderNo").disabled = false;

				//document.getElementById("radioCVPY").disabled=false;
				//document.getElementById("radioCVPN").disabled=false;
				//document.getElementById("txtCvpAmnt").disabled=false;
			}
			//disableCVPBlock("");
			//disableForProvisionCase();
		}
		setOmrType()
	}
	function setOmrType()
	{
		if(document.getElementById("txtDateOfDeath").value.length>0 && document.getElementById("cmbPnsnType").value != 'Family (Lost)' && document.getElementById("cmbPnsnType").value != 'Family')
		{
			document.getElementById("OMRCol1").style.display = 'inline';
			document.getElementById("OMRCol2").style.display = 'inline';
		}
		else
		{
			document.getElementById("OMRCol1").style.display = 'none';
			document.getElementById("OMRCol2").style.display = 'none';
			document.getElementById("cmbOmrType").value = "-1";
		}
	}
	function disableCVPBlock(lstr)
	{
			document.getElementById("radioCVPY").disabled = lstr;
			document.getElementById("radioCVPN").disabled = lstr;
			//document.getElementById("txtCvpOrderNo").disabled = lstr;
			document.getElementById("txtCvpAmnt").disabled = lstr;
			document.getElementById("radioCVPN").checked = true;
			if(document.getElementById("radioCVPN").checked)
			{
				document.getElementById("txtCvpDate").disabled = "disabled";
				document.getElementById("txtCvpRestorianDate").disabled = "disabled";
			}
			if(lStrDisable.length > 0)
			{
				//document.getElementById("txtCvpOrderNo").disabled = lStrDisable;
				document.getElementById("txtCvpAmnt").disabled = lStrDisable;
				document.getElementById("radioCVPY").disabled = lStrDisable;
			   document.getElementById("radioCVPN").disabled = lStrDisable;
				if(! document.getElementById("radioCVPN").checked)
				{
					document.getElementById("txtCvpDate").disabled = lStrDisable;
					document.getElementById("txtCvpRestorianDate").disabled = lStrDisable;
				}
			}
			if(lstr != "")
			{
				document.getElementById("txtCvpOrderNo").value = "";
				document.getElementById("txtCvpAmnt").value = "";
				document.getElementById("txtCvpDate").value = "";
				document.getElementById("txtCvpRestorianDate").value = "";
			}

	}
	function dateFormat(field)
	{	if(event.keyCode != 8)
		{
			var value=new String(field.value);
			if(value.length==2)
			{
				field.value=value+'/'
			}
			if(value.length==5)
			{
				field.value=value+'/'
			}
		}
	}
	function setCvpDisable(lThis)
	{
		if(Number(lThis.value) <= 0)
		{
			document.getElementById("txtCvpMonthlyAmnt").disabled = true;
			//document.getElementById("txtCvpMonthlyAmnt").value = "";
		}
		else
		{
			document.getElementById("txtCvpMonthlyAmnt").disabled = lStrDisable;
		}
	}
	function getDeptHod()
	{
		if(document.getElementById("cmbHOD").value == "-1")
		{
			document.getElementById("cmbHOD").options[document.getElementById("cmbHOD").options.length] =new Option(document.getElementById("cmbDepartment").options[document.getElementById("cmbDepartment").options.selectedIndex].innerHTML, document.getElementById("cmbDepartment").value, true)
			document.getElementById("cmbHOD").value = document.getElementById("cmbDepartment").value;
		}
		setDescToHiiden('hidHODDesc',document.getElementById("cmbHOD"));
		setDescToHiiden('hidDeptDesc',document.getElementById("cmbDepartment"));
	}
	function getTresSubtres()
	{
		if(document.getElementById("cmbSubTreasury").value == "-1")
		{
			document.getElementById("cmbSubTreasury").options[document.getElementById("cmbSubTreasury").options.length] =new Option(document.getElementById("cmbTreasury").options[document.getElementById("cmbTreasury").options.selectedIndex].innerHTML, document.getElementById("cmbTreasury").value, true)
			document.getElementById("cmbSubTreasury").value = document.getElementById("cmbTreasury").value;
		}
	}
function disableForProvisionCase()
{
	var status = document.getElementById("CmbpensionClass").value;
	if(status == 'P')
	{
		document.getElementById("radioCVPY").disabled = "disabled";
		document.getElementById("radioCVPN").disabled = "disabled";
		document.getElementById("txtCvpAmnt").disabled = "disabled";
		document.getElementById("txtCvpDate").disabled = "disabled";
		document.getElementById("txtCvpRestorianDate").disabled = "disabled";
		document.getElementById("txtCvpOrderNo").value = "";
		document.getElementById("txtCvpAmnt").value = "";
		document.getElementById("txtCvpDate").value = "";
		document.getElementById("txtCvpRestorianDate").value = "";
	}
}
function setArrearOrRecovery(lStr)
{
	xmlHttp=GetXmlHttpObject();
	if (xmlHttp==null)
	{
		alert (AL_AJAX);
		return;
	}
	xmlHttp.onreadystatechange=function ()
	{
		if (xmlHttp.readyState==complete_state)
		{

			hideProgressbar();
			XMLDoc = xmlHttp.responseXML.documentElement;

			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

			if(XmlHiddenValues[0].childNodes[1] != null)
			{
				alert(XmlHiddenValues[0].childNodes[1].text);
				document.getElementById("hidCaseTypeFlag").value == 'R';
				billGenerateAction(lStr);
				return true;
			}
			var resString;
			if(XmlHiddenValues[0].childNodes[1] != null)
			{
				resString = XmlHiddenValues[0].childNodes[0].text;
			}
			if(resString == "Y")
			{
				var newWindow = null;
	    		var x = 0;
	    		var y = 0;
	    		var h = screen.height - 100;
	    		var w = screen.width;
	    		var urlstring = "ifms.htm?actionFlag=openRecoveryPage&pension_request_id="+document.getElementById("hidCaseId").value+"&pensioner_code="+document.getElementById("hidpensionerCode").value+'&isFromCase=Y&isForBill=Y';
	    		var urlstyle = "height="+ h +",width= "+ w +",toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=" + x + ",left=" + y;
	    		newWindow = window.open(urlstring, "recoverybutton", urlstyle);
			}

		}
	}
	var uri = 'ifms.htm?actionFlag=setArrearsAndRecovery&ppoNo='+document.getElementById("txtPpoNo").value+'&pensionerCode='+document.getElementById("hidpensionerCode").value;
	var url = uri+run();
	xmlHttp.open("POST",uri,true);
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(url);
}
function checkOtherAuth()
{
	if(document.getElementById("sancAuthCmb").value == 10046)
	{
		document.getElementById("txtSancAuth").style.display = "block";
	}
	else
	{
		document.getElementById("txtSancAuth").style.display = "none";
	}
}
function getPrevReasons()
{
	if(document.getElementById("hidpensionerCode").value.length > 0)
	{
		var newWindow = null;
		var x = 0;
		var y = 0;
		var h = 200;
		var w = 500;
		var urlstring = "ifms.htm?actionFlag=getPnsnrPrevHeldReasons&PensionerCode="+document.getElementById("hidpensionerCode").value+'&caseDiable='+document.getElementById("hidCaseDisable").value;
		var urlstyle = "height="+ h +",width= "+ w +",toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=" + x + ",left=" + y;
		newWindow = window.open(urlstring, "getPnsnrPrevHeldReasons", urlstyle);
		globArray[11] = newWindow;
	}
	else
	{
		alert("No Records Found");
	}
}
function getCMBTitle(lThis)
{
	lThis.title = lThis.options[lThis.options.selectedIndex].innerHTML;
}
function disableDCRGBlok()
{
	//document.getElementById("radioDCRGY").disabled = true;
	//document.getElementById("radioDCRGN").disabled = true;
	//document.getElementById("txtDcrgOrderNo").disabled = true;
	//document.getElementById("txtDcrgAmnt").disabled = true;
	if(document.getElementById("auditFlag").value == 'Y')
	{
		if(document.getElementById("radioDCRGN").checked)
		{
			document.getElementById("txtDAPrcnt").disabled = true;
		}
		else
		{
			document.getElementById("txtDAPrcnt").disabled = false;
		}
	}
}
function disableNewDa(lThis)
{
	if(document.getElementById("hidAprvdDcrgAmt"))
	{
		if(Number(lThis.value) == Number(document.getElementById("hidAprvdDcrgAmt").value))
		{
			document.getElementById("txtNewDAPrcnt").disabled = "";
		}
		else
		{
			document.getElementById("txtNewDAPrcnt").disabled = true;
			document.getElementById("txtNewDAPrcnt").value = "";
		}
	}
}
function disableDcrgAmt(element)
{
	if(Number(element.value) > 0)
	{
		document.getElementById("txtDcrgAmnt").disabled = true;
	}
	else
	{
		document.getElementById("txtDcrgAmnt").disabled = "";
	}
}
function chkRevalidationDate()
{
	var inWardDateArr = document.getElementsByName("txtInwrdDt");
	var outWardDateArr = document.getElementsByName("txtOutwrdDt");
	var revalidDateArr = document.getElementsByName("txtRevalidDt");
	var hidseenDate =  document.getElementById("hidseenDate").value;
	for(var i=0;i<inWardDateArr.length;i++)
	{
		var inWardDate = inWardDateArr[i].value;
		var outWardDate = outWardDateArr[i].value;
		var revalidDate = revalidDateArr[i].value;
		if(outWardDate.length > 0)
		{
			 if(isProper1(hidseenDate,outWardDate) == false)
			 {
			 	alert(PRPOUTWDDATE);
				outWardDateArr[i].value="";
				outWardDateArr[i].focus();
				return false;
			 }
		}
		if((inWardDate.length>0 && outWardDate.length >0) || (inWardDate.length>0 && revalidDate.length >0) || (outWardDate.length>0 && revalidDate.length >0))
		{

			if(inWardDate.length>0)
			{
				if(isProper1(outWardDate,inWardDate) == false || isProper1(hidseenDate,outWardDate) == false)
				{
					alert(PRPINWDDATE);
					inWardDateArr[i].value="";
					inWardDateArr[i].focus();
					return false;
				}
			}
			if(revalidDate.length > 0)
			{
				if(isProper1(outWardDate,revalidDate) == false || isProper1(hidseenDate,revalidDate) == false)
				{
					alert(PRPREVALIDDATE);
					revalidDateArr[i].value="";
					revalidDateArr[i].focus();
					return false;
				}
				if( inWardDate.length>0 && isProper1(revalidDate,inWardDate) == false)
				{
					alert(PRPREVALIDDATE);
					revalidDateArr[i].value="";
					revalidDateArr[i].focus();
					return false;
				}
			}
		}
	}
}
function showApplicationDate()
{
	var str1 = document.getElementById("txtCvpRestorianDate").value;
	var str2 = document.getElementById("hidCurDate").value;
	if(str1.length > 0 && str2.length > 0)
	{
		if(isProper1(str2,str1) == false)
		{
			document.getElementById("AppCol1").style.display = 'inline';
			document.getElementById("AppCol2").style.display = 'inline';
		}
	}
	if(str1.length <= 0)
	{
		document.getElementById("AppCol1").style.display = 'none';
		document.getElementById("AppCol2").style.display = 'none';
	}
}
function disableForMROnly()
{
	if(document.getElementById("CmbMrCase").value=="Y")
	{
		document.getElementById("txtBasicPensionAmnt").value = "0.00";
		document.getElementById("txtBasicPensionAmnt").disabled = true;
		document.getElementById("CmbCalcType").value = "A";
		document.getElementById("CmbCalcType").disabled = true;
		document.getElementById("radioCVPY").disabled = true;
		document.getElementById("radioCVPN").checked = true;
		document.getElementById("radioCVPN").disabled = true;
		document.getElementById("radioDCRGY").disabled = true;
		document.getElementById("radioDCRGN").disabled = true;
		document.getElementById("txtCvpAmnt").disabled = true;
		document.getElementById("txtCvpAmnt").value  = "0.00";
		document.getElementById("txtDcrgAmnt").disabled = true;
		document.getElementById("txtDcrgAmnt").value  = "0.00";
		document.getElementById("txtDAPrcnt").disabled = true;
		document.getElementById("txtDAPrcnt").value  = "";
		document.getElementById("txtAppliedDate").disabled = true;
		document.getElementById("txtAppliedDate").value = "";
		document.getElementById("txtNewDAPrcnt").disabled = true;
		document.getElementById("txtNewDAPrcnt").value  = "";
		document.getElementById("cmbRop").disabled = true;
		document.getElementById("radiosplCaseY").disabled = true;
		document.getElementById("radiosplCaseN").disabled = true;
		document.getElementById("txtPensionableAmnt2").disabled = true;
		document.getElementById("txtPensionableAmnt").disabled = true;
		document.getElementById("txtBasicPensionAmnt").disabled = true;
		document.getElementById("txtPensionableAmnt2").value = "0.00";
		document.getElementById("txtPensionableAmnt").value = "0.00";
		document.getElementById("txtBasicPensionAmnt").value = "0.00";
		document.getElementById("btnPnsnCutDtls").disabled = true;
		document.getElementById("txtPersonalPension").disabled = true;
		document.getElementById("txtPersonalPension").value = "";
		document.getElementById("txtFp1Date").disabled = true;
		document.getElementById("txtFp1Amnt").disabled = true;
		document.getElementById("txtFp2Date").disabled = true;
		document.getElementById("txtFp2Amnt").disabled = true;
		document.getElementById("txtFp1Date").value = "";
		document.getElementById("txtFp1Amnt").value = "";
		document.getElementById("txtFp2Date").value = "";
		document.getElementById("txtFp2Amnt").value = "";
		document.getElementById("btnOtherBenefit").disabled = true;
		document.getElementById("btnArrearDtls").disabled = true;
		document.getElementById("btnSpecialCutDtls").disabled = true;
		document.getElementById("btnItCutDtls").disabled = true;
		document.getElementById("btnRecovery").disabled = true;
		document.getElementById("radioMaY").disabled = true;
		document.getElementById("radioMaN").disabled = true;
	}
	else  if(document.getElementById("hidCaseId").value.length <= 0 )
	{
		document.getElementById("txtBasicPensionAmnt").value = "0.00";
		document.getElementById("txtBasicPensionAmnt").disabled = false;
		document.getElementById("CmbCalcType").value = "A";
		document.getElementById("CmbCalcType").disabled = false;
		document.getElementById("radioCVPY").disabled = false;
		document.getElementById("radioCVPN").disabled = false;
		document.getElementById("radioDCRGY").disabled = false;
		document.getElementById("radioDCRGN").disabled = false;
		document.getElementById("txtCvpAmnt").disabled = false;
		document.getElementById("txtCvpAmnt").value  = "0.00";
		document.getElementById("txtDcrgAmnt").disabled = false;
		document.getElementById("txtDcrgAmnt").value  = "0.00";
		document.getElementById("txtDAPrcnt").disabled = false;
		document.getElementById("txtDAPrcnt").value  = "";
		document.getElementById("txtAppliedDate").disabled = false;
		document.getElementById("txtAppliedDate").value = "";
		document.getElementById("txtNewDAPrcnt").disabled = false;
		document.getElementById("txtNewDAPrcnt").value  = "";
		document.getElementById("cmbRop").disabled = false;
		document.getElementById("radiosplCaseY").disabled = false;
		document.getElementById("radiosplCaseN").disabled = false;
		document.getElementById("txtPensionableAmnt2").disabled = false;
		document.getElementById("txtPensionableAmnt").disabled = false;
		document.getElementById("txtBasicPensionAmnt").disabled = false;
		document.getElementById("txtPensionableAmnt2").value = "0.00";
		document.getElementById("txtPensionableAmnt").value = "0.00";
		document.getElementById("txtBasicPensionAmnt").value = "0.00";
		document.getElementById("btnPnsnCutDtls").disabled = false;
		document.getElementById("txtPersonalPension").disabled = false;
		document.getElementById("txtPersonalPension").value = "";
		document.getElementById("txtFp1Date").disabled = false;
		document.getElementById("txtFp1Amnt").disabled = false;
		document.getElementById("txtFp2Date").disabled = false;
		document.getElementById("txtFp2Amnt").disabled = false;
		document.getElementById("txtFp1Date").value = "";
		document.getElementById("txtFp1Amnt").value = "";
		document.getElementById("txtFp2Date").value = "";
		document.getElementById("txtFp2Amnt").value = "";
		document.getElementById("btnOtherBenefit").disabled = false;
		document.getElementById("btnArrearDtls").disabled = false;
		document.getElementById("btnSpecialCutDtls").disabled = false;
		document.getElementById("btnItCutDtls").disabled = false;
		document.getElementById("btnRecovery").disabled = false;
		document.getElementById("radioMaY").disabled = false;
		document.getElementById("radioMaN").disabled = false;
	}
}
function getReMarriedDtls()
{
		if(document.getElementById("cmbRemarried").value == 'Y')
		{
			document.getElementById("txtRemariedDate").disabled = false;
			document.getElementById("ImgRemariedDate").disabled = false;
		}
		else
		{
			document.getElementById("txtRemariedDate").disabled = true;
			document.getElementById("ImgRemariedDate").disabled = true;
			document.getElementById("txtRemariedDate").value = "";
		}
}
function validateBasicByRop(lThis)
{

	var hdCode = document.getElementById("cmbHeadCode").value;
	if(lThis == undefined)
	{
		lThis = document.getElementById("txtBasicPensionAmnt");
	}

	if(hdCode != '16' && hdCode != '17' && hdCode != '18' && hdCode != '19' && document.getElementById("CmbCalcType").value == 'A')
	{
		if(document.getElementById("ROP1986A").checked)
		{
			var x = lThis.value;
			if(Number(x)>1500)
			{
				alert(BASICROP1986);
				lThis.value = "";
				document.getElementById("txtPensionableAmnt").focus();
				return false;
			}
		}
		else if(document.getElementById("ROP1996A").checked)
		{
			var x = lThis.value;
			if(Number(x)>4500)
			{
				alert(BASICROP1996);
				lThis.value = "";
				document.getElementById("txtPensionableAmnt").focus();
				return false;
			}
		}
		else if(document.getElementById("ROP2006A").checked)
		{
			/*var x = lThis.value;
			if(Number(x)>15000)
			{
				alert(BASICROP2006);
				lThis.value = "";
				document.getElementById("txtPensionableAmnt").focus();
				return false;
			}*/
		}
	}
}
function chkForExistingPPO()
{
	PPONo = document.getElementById("txtPpoNo");
	if(PPONo.disabled == false)
	{
		 req = createXMLHttpRequest();

		if(req != null)
		{
			var baseUrl = "ifms.htm?actionFlag=validatePPONo&PPONo="+PPONo.value;
			req.open("post", baseUrl, true);
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = function()
			{
				if(req.readyState==complete_state)
				{
					var XMLDoc = req.responseXML.documentElement;
					var XMLEntries = XMLDoc.getElementsByTagName("PPONO");
					var blnResult = XMLEntries[0].text;
					if(blnResult == 'true')
					{
						alert(USED_PPO);
						document.getElementById("txtPpoNo").value = "";
						document.getElementById("txtPpoNo").focus();
						return false;
					}
					else
					{
						return true;
					}
				}
			}
			req.send(baseUrl);
		}
		else
		{
			alert (AL_AJAX);
		}
	}
	else
	{
		return true;
	}
}
function setDCRGButton(lStrSet)
{
	if(document.getElementById("btnDCRG"))
	{
		if(lStrSet == 'Y')
		{
			document.getElementById("btnDCRG").style.display = "none"
		}
		else
		{
			document.getElementById("btnDCRG").style.display = "inline"
		}
	}

}
function setCVPBUtton(lStrSet)
{
	if(document.getElementById("btnCVP"))
	{
		if(lStrSet == 'Y')
		{
			document.getElementById("btnCVP").style.display = "none"
		}
		else
		{
			document.getElementById("btnCVP").style.display = "inline"
		}
	}
}
function setPesnionBillButton()
{
	if(document.getElementById("radioAliveN").checked && document.getElementById("btnPnsn"))
	{
		if(Number(document.getElementById("txtFp2Amnt").value)>0 && Number(document.getElementById("txtFp1Amnt").value)>0 )
		{
			document.getElementById("btnPnsn").style.display = "inline";
		}
		else
		{
			document.getElementById("btnPnsn").style.display = "none";
		}
	}
}

// For Currnet Month POPUP .. Added By Sagar .. Start
function openCurntMntPopUp()
{
	try
	{
		showProgressbar();
		document.getElementById('id_tblCaseSrch').style.display='inline';
		document.getElementById('id_tblCaseSrch').style.left=500;
		document.getElementById('id_tblCaseSrch').style.top=700;
		document.getElementById('id_tblCaseSrch').style.width=250;
		document.getElementById('Y').focus();
	}
	catch(e){}
}

function closeCaseSearch()
{
	try{
		document.getElementById('id_tblCaseSrch').style.display='none';
	}catch(e){}
}

function callPensionBill(lValue)
{
	var subId = 9;
	var ppoNo = document.getElementById('txtPpoNo').value;
	var height = screen.height - 100;
	var width = screen.width;

	if(lValue == 'Y')
	{
		var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId='+subId+'&PPONo='+ppoNo+'&auditFlag='+document.getElementById("auditFlag").value+'&isNewSavingBill=Y&chkbxCrntMnt=Y&PENSIONFlag=Y';
	}
	else if(lValue == 'N')
	{
		var urlstring = 'ifms.htm?actionFlag=createPensionSpecificBills&subjectId='+subId+'&PPONo='+ppoNo+'&auditFlag='+document.getElementById("auditFlag").value+'&isNewSavingBill=Y&chkbxCrntMnt=N&PENSIONFlag=Y';
	}

	closeCaseSearch();
	//hideProgressbar();
	urlstring = urlstring;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	var pnsnWindow = window.open(urlstring, "frmViewOnlineBill", urlstyle);
	globArray[10] = pnsnWindow;
}

document.onkeypress = function(){
	//alert(document.forms[0].name)

	if((event.keyCode == 121 || event.keyCode == 89) && document.forms[0].name =='inwdPension' && document.getElementById('id_tblCaseSrch').style.display=='inline') // y || Y
	{
		callPensionBill('Y');
	}
	else if(( event.keyCode == 110 || event.keyCode == 78) && document.forms[0].name =='inwdPension' && document.getElementById('id_tblCaseSrch').style.display=='inline') // n || N
	{
		callPensionBill('N');
	}
	/*else if((event.keyCode == 112 || event.keyCode == 80) && document.forms[0].name =='inwdPension')  // p || P
	{
		generateBill1('PENSION');
	}
	else if((event.keyCode == 100 || event.keyCode == 68) && document.forms[0].name =='inwdPension') // d || D
	{
		generateBill1('DCRG');
	}
	else if((event.keyCode == 99 || event.keyCode == 67) && document.forms[0].name =='inwdPension') // c || C
	{
		generateBill1('CVP');
	}*/
}
// For Currnet Month POPUP .. Added By Sagar .. End
function getDpMrgd(lStr,isonLoad)
{
	if(document.getElementById("Rop1986chkBx").checked)
	{
		document.getElementById("radiosplCaseN").checked = true;
		document.getElementById("radiosplCaseY").disabled = true;
		document.getElementById("radiosplCaseN").disabled = true;
		if(isonLoad == 'N')
		{
			changeHeadDesc();
		}
	}
	if(document.getElementById("Rop1996chkBx").checked)
	{
		document.getElementById("radiosplCaseY").checked = true;
		document.getElementById("radiosplCaseY").disabled = lStr;
		document.getElementById("radiosplCaseN").disabled = lStr;
		if(isonLoad == 'N')
		{
			changeHeadDesc();
		}
	}
}

function validateOutWardDate(fromDate,todate)
{
	if(fromDate == null)
	{
		fromDate = document.getElementById("txtCommensionDate");
	}
	if(fromDate.value.length >0 && todate.value.length > 0 && document.getElementById("outwrdDtls").style.display != 'none'  )
	 {
	 	if(isProper1(fromDate.value,todate.value) == false)
	 	{
	 		alert("OutWard Date must be GreaterThan Commencment Date");
	 		todate.value = "";
	 		goToFieldTab(todate.id,1);

	 	}
	 }
}
function validateInwardDate(fromDate,todate)
{
	if(fromDate == null)
	{
		fromDate = document.getElementById("txtCommensionDate");
	}
	if(fromDate.value.length >0 && todate.value.length > 0 && document.getElementById("outwrdDtls").style.display != 'none'  )
	 {
	 	if(isProper1(fromDate.value,todate.value) == false)
	 	{
	 		alert("Inward Date must be GreaterThan Commencment Date");
	 		todate.value = "";
	 		goToFieldTab(todate.id,1);

	 	}
	 }
}

function validatePrevOutWrdDate(lThis,lCount)
{
	var x = '';
	var y = '';
	if(document.getElementById("hidFinOutWrdDate").value.length > 0 && lCount == '0'  )
	{
		x = document.getElementById("hidFinOutWrdDate").value;
	}
	else if(lCount != 0)
	{
		x = document.getElementById("txtOutwardDt"+(Number(lCount)-1)).value;

		if(document.getElementById("txtOutwardDt"+(Number(lCount)+1)))
		{
			y = document.getElementById("txtOutwardDt"+(Number(lCount)+1)).value;
		}
	}
	else if(lCount == 0)
	{
		x = document.getElementById("txtOutwardDt0").value;

		if(document.getElementById("txtOutwardDt1"))
		{
			y = document.getElementById("txtOutwardDt1").value;
		}
	}
	if(x.length > 0 && lThis.value.length > 0)
	{
		if(isProper1(x,lThis.value) == false)
	 	{
	 		alert("OutWard must be GreaterThan Previous OutWard Date");
	 		lThis.value = "";
	 		goToFieldTab(lThis.id,1);
	 	}
	}
	if(y.length > 0 && lThis.value.length > 0 )
 	{
 		if(isProper1(lThis.value,y) == false)
	 	{
	 		alert("OutWard must be GreaterThan Previous OutWard Date");
	 		document.getElementById("txtOutwardDt"+(Number(lCount)+1)).value = "";
	 		goToFieldTab("txtOutwardDt"+(Number(lCount)+1),1);
	 	}
 	}
}
function getSubTresButtn(lThis)
{
	if(lThis.value.length > 0 )
	{
		document.getElementById("subTresOutWrd").disabled = false;
	}
	else
	{
		document.getElementById("subTresOutWrd").disabled = true;
	}
}
function chkForsum100(lThis)
{
	if(Number(lThis.value) != 100 && Number(lThis.value)>0 )
	{
		alert("Family Member Percentage Must be 100 or 0")
		lThis.value = "";
		goToFieldTab(lThis.id,1);
	}
}
function validateSubTresOutWardInwarddates(outCount,inCount)
{
	if(document.getElementById("txtOutwardDt"+outCount).value.length>0 && document.getElementById("txtInwardDt"+inCount).value.length>0)
	{
				if(isProper1(document.getElementById("txtOutwardDt"+outCount).value,document.getElementById("txtInwardDt"+inCount).value) == false)
				{
					alert("Inward Date Must be Greater Than Outward Date");
					document.getElementById("txtInwardDt"+inCount).value="";
					goToFieldTab('txtInwardDt'+inCount,1);
					return false;
				}
	}
}
function openRevArrear()
{
	var pensionerCode = document.getElementById("hidpensionerCode").value;
	var caseId = document.getElementById("hidCaseId").value;
	var ppoNo = document.getElementById("hidPPONo").value;
	var head_code = document.getElementById("cmbHeadCode").value;
	var fmPrcnts = document.getElementsByName("txtFMPercent");
	var fmDobArr = document.getElementsByName("txtFMDateOfBirth");
	var fmDodArr = document.getElementsByName("txtFMDateOfDeath");

	var pnsnrDob = document.getElementById("txtDateOfBirth").value;
	var pnsnrDod = document.getElementById("txtDateOfDeath").value;

	var FP1Date = document.getElementById("txtFp1Date").value;
    var FP2Date = document.getElementById("txtFp2Date").value;
    var NewFP1Basic = document.getElementById("txtFp1Amnt").value;
    var NewFP2Basic = document.getElementById("txtFp2Amnt").value;
    var CommensionDate = document.getElementById("txtCommensionDate").value;
    var EndDate = document.getElementById("txtPPOEndDate").value;

	var fmDob = "" ;
	var fmDod = "";
	for(var i=0;i<fmPrcnts.length;i++)
	{
		if(Number(fmPrcnts[i].value) == 100)
		{
			fmDob = fmDobArr[i].value;
			fmDod = fmDodArr[i].value;
		}
	}
	var recoveryWindow = null;
   	var x = 0;
   	var y = 0;
   	var h = screen.height - 100;
   	var w = screen.width;
   	var urlstring = 'ifms.htm?actionFlag=openRevisedArrear&pension_request_id='+caseId+'&pensioner_code='+pensionerCode+'&oldBasic='+document.getElementById("txtBasicPensionAmnt").value+'&old_cvp='+document.getElementById("txtCvpMonthlyAmnt").value+'&effFrom='+document.getElementById("txtCommensionDate").value+'&ppo_no='+ppoNo+'&headcode='+head_code;
    urlstring = urlstring+"&PnsnrDOB="+pnsnrDob+"&PnsnrDOD="+pnsnrDod+"&FMDOB="+fmDob+"&FMDOD="+fmDod+"&FP1Date="+FP1Date+"&FP2Date="+FP2Date+"&NewFP1Basic="+NewFP1Basic+"&NewFP2Basic="+NewFP2Basic+"&CommensionDate="+CommensionDate+"&PPOEndDate="+EndDate;
   	var urlstyle = "height="+h+",width="+w+",toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=yes,top=" + x + ",left=" + y;
   	window.open(urlstring, "revisedarrearbutton", urlstyle);
}

function getotherTextBox(lStr,lthis)
{
	if(lthis.value == '0')
	{
		document.getElementById(lStr+'Div1').style.display = 'inline';
		document.getElementById(lStr+'Div2').style.display = 'inline';
	}
	else
	{
		document.getElementById(lStr+'Div1').style.display = 'none';
		document.getElementById(lStr+'Div2').style.display = 'none';
	}
}
function validateCVPMonthly()
{
	if(document.getElementById("txtCvpMonthlyAmnt").value.length >0 && document.getElementById("txtCvpAmnt").value.length>0)
	{
		if(Number(document.getElementById("txtCvpMonthlyAmnt").value) > Number(document.getElementById("txtCvpAmnt").value))
		{
			document.getElementById("txtCvpMonthlyAmnt").value = "";
			goToFieldTab('txtCvpMonthlyAmnt',0);
			alert("CVP Monthly Cant be Exceed CVP Amount");
			return false;
		}
	}
}
function getCaseFromFPAuditorByPPONO(lStr)
{
	req = createXMLHttpRequest();
	if(req != null)
	{
		var baseUrl = "ifms.htm?actionFlag=validatePPONo&PPONo="+lStr+"&ValidateType=FP";
		req.open("post", baseUrl, true);
		req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		req.onreadystatechange = function ()
		{
			if(req.readyState==complete_state)
			{
				var XMLDoc = req.responseXML.documentElement;
				if(XMLDoc != null)
				{
					var XMLEntries = XMLDoc.getElementsByTagName("PnsnrRqstId");
					if(XMLEntries[0] != null)
					{
						var blnResult = XMLEntries[0].text;
						if(Number(blnResult)>0)
						{
							var XMLCommEntries = XMLDoc.getElementsByTagName("CommensionDate");
							var CommensionDate = XMLCommEntries[0].text;
							var ProvCommensionDate = document.getElementById("txtCommensionDate").value;
							if(CommensionDate != ProvCommensionDate )
							{
								alert("Mismatch in CommensionDate of Provisional Case and Final Case");
								enableCase();
								return false;
							}
						//window.close();
						var newWindow;
						var height = screen.height - 100;
						var width = screen.width;
						var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
						/*if(document.getElementById("hidProvFlag"))
						{
							if(document.getElementById("hidProvFlag").value == 2)
							{
								url = url+"&isProvCase=2"
							}
						}*/
						alert("Please Adjust Provisional pension from Final Pension.");
						url = "ifms.htm?actionFlag=getPensionCaseData&auditFlag=N&ppoNo="+lStr+"&rqstHdrId="+blnResult+"&ProvPPORqstId="+document.getElementById("hidCaseId").value;
						newWindow = window.open(url, "frmPensionCase", urlstyle);
						}
					}
					else
					{
						alert("Please Enter Proper PPO Number(This PPONumber Doesnot exists or Not Lying With CurretnAuditor) ");
						document.getElementById("txtFinalPPONo").value = "";
						document.getElementById("txtFinalPPONo").focus();
						enableCase();
						return false;
					}
				}
				else
				{
					alert("Please Enter Proper PPO Number(This PPONumber Doesnot exists or Not Lying With CurretnAuditor) ");
					document.getElementById("txtFinalPPONo").value = "";
					document.getElementById("txtFinalPPONo").focus();
					enableCase();
					return false;
				}
			}
		}
		req.send(baseUrl);
	}
	else
	{
		alert (AL_AJAX);
	}

}
function enableCase()
{
	var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{
		if(disableArray.indexOf(document.forms[0].elements[i].id) >0 )
		{
			document.forms[0].elements[i].disabled=false
		}
	}
}
function disableCase()
{
	var totalElements= document.forms[0].elements.length
	for(i=0;i<totalElements;i++)
	{
		if(document.forms[0].elements[i].disabled == false && document.forms[0].elements[i].type != undefined)
		{
			disableArray[i] = document.forms[0].elements[i].id;
			document.forms[0].elements[i].disabled=true


		}
	}
}
function disableCVPEffectFromMMYYY()
{
	if(isProper1(document.getElementById("hidCurDate").value,document.getElementById("txtCommensionDate").value) == true)
	{
		document.getElementById("txtCVPEffectMM").disabled=lStrDisable;
		document.getElementById("txtCVPEffectYYYY").disabled=lStrDisable;
		document.getElementById("cvpEffect").style.display = 'inline';
	}
	else
	{
		document.getElementById("txtCVPEffectMM").disabled="disabled";
		document.getElementById("txtCVPEffectYYYY").disabled="disabled";
		document.getElementById("cvpEffect").style.display = 'none';
	}
}
function validateAccno(lstruri)
{
	if(checkForCvpMonthly() == true)
	{
		if(document.getElementById("txtbranchCode").value.length >0 && document.getElementById("txtACNo").value.length >0)
		{
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null)
			{
				alert (AL_AJAX);
				return 1;
			}
			var uri = "";
			uri = "ifms.htm?actionFlag=checkForPPOwithSameAccountNo&txtbranchCode="+document.getElementById("txtbranchCode").value+'&txtACNo='+document.getElementById("txtACNo").value+'&txtppoNo='+document.getElementById("txtPpoNo").value;
			if(document.getElementById("txtProvNum"))
			{
				uri = uri+'&ProvPPONo='+document.getElementById("txtProvNum").value
			}
			xmlHttp.onreadystatechange=function()
			{
				if (xmlHttp.readyState==complete_state)
				{
					XMLDoc = xmlHttp.responseXML.documentElement;
				 	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
				 	if(XmlHiddenValues[0].childNodes[0] != null)
				 	{
				 		if(XmlHiddenValues[0].childNodes[0].text.length>0)
				 		{
				 			if(confirm("Given account number already exists \r\nin same bank branch For the following PPO Number:\r\n"+XmlHiddenValues[0].childNodes[0].text+"\r\nDo you want to proceed with the update?"))
				 			{
				 				savePensionCaseUsingAjx(lstruri)
				 			}
				 			else
				 			{
				 				//savePensionCaseUsingAjx(uri)
				 				return false;
				 			}
				 		}
				 		else
				 		{
				 			savePensionCaseUsingAjx(lstruri)
				 		}
				 	}
				 	else
				 	{
				 		savePensionCaseUsingAjx(lstruri)
				 	}
				}
			}
			xmlHttp.open("POST",uri,true);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send(uri);
		}
		else
		{
			savePensionCaseUsingAjx(lstruri)
		}
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


function maxSizeReachedToSave(lThis,size)
{
	var lStr = new String(lThis.value);
	if(lStr.length >= Number(size))
	{
		if(window.event.keyCode == 13)
		{
			if(document.getElementById("btnSave"))
			{
				document.getElementById("btnSave").focus();
			}
			if(document.getElementById("btnUpdate"))
			{
				document.getElementById("btnUpdate").focus();
			}
			window.event.keyCode = 0;
		}
		else
		{
			window.event.keyCode = 0;
		}
	}
}


function  changeBankBranchMandtry()
{

	if(document.getElementById("cmbPwrAtrny").value == "Y")
	{
		var x = document.getElementsByName("mandtryPwrAtrny");
		for(var i=0;i<x.length;i++)
		{
			x[i].style.display = "inline";
		}
	}
	else
	{
		var x = document.getElementsByName("mandtryPwrAtrny");
		for(var i=0;i<x.length;i++)
		{
			x[i].style.display = "none";
		}
	}
}

function checkForCvpMonthly()
{
	var BasicAmnt = document.getElementById("txtBasicPensionAmnt").value;
	var DpAmnt = document.getElementById("txtDpAmnt").value;
	var Basic = Number(BasicAmnt) + Number(DpAmnt);

	var Basic40Prcnt = (Number(Basic)*40)/100;
	var Basic50Prcnt = (Number(Basic)*50)/100;
	var cvpMnthlyAmnt = document.getElementById("txtCvpMonthlyAmnt").value;
	if(Number(cvpMnthlyAmnt) > 0 && Number(Basic) > 0  && Number(cvpMnthlyAmnt) >= Number(Basic40Prcnt) && Number(cvpMnthlyAmnt) <= Number(Basic50Prcnt))
	{
		alert("CVP Monthly Amount has exceeded 40 % of Basic");
		return true;
	}
	else if(Number(cvpMnthlyAmnt) > 0 && Number(Basic) > 0 && Number(cvpMnthlyAmnt) > Number(Basic50Prcnt))
	{
		alert("CVP Monthly Amount Cant exceed 50 % of Basic");
		document.getElementById("txtCvpMonthlyAmnt").value = "";
		goToFieldTab("txtCvpMonthlyAmnt",0)
		return false;
	}
	else
	{
		return true;
	}

}
function checkForSeenFlag()
{
	if(document.getElementById("radioAliveN").checked)
	{
		var fmPrcnt = document.getElementsByName("txtFMPercent");
		var fmDod = document.getElementsByName("txtFMDateOfDeath");
		var countHndrd = 1;
		if(fmPrcnt.length > 0)
		{
			for(var i=0;i<fmPrcnt.length;i++)
			{
				if(Number(fmPrcnt[i].value) == 100 )
				{
					countHndrd = 0;
					if(fmDod[i].value.length > 0)
					{
						document.getElementById("radioSeenN").checked = true;
						setSeen(true);
					}
				}
			}
			if(countHndrd == 1)
			{
				document.getElementById("radioSeenN").checked = true;
				setSeen(true);
				alert("Please Enter Family Details(Family Member With 100% is Must) To Make Seen Yes ");
			}
		}
		else
		{
			document.getElementById("radioSeenN").checked = true;
			setSeen(true);
			alert("Please Enter Family Details(Family Member With 100% is Must) To Make Seen Yes ");
		}
	}
}

function setDescToHiiden(lStrVarName,lThis)
{
	try
	{
		if(document.getElementById(lStrVarName))
		{
			if(lThis.value != '-1' && lThis.value != "0")
			{
				if(lThis.options[lThis.options.selectedIndex].innerHTML.search("--")<0)
				document.getElementById(lStrVarName).value = lThis.options[lThis.options.selectedIndex].innerHTML;
				if(document.getElementById(lStrVarName).value.indexOf("&") > 0)
				{
					document.getElementById(lStrVarName).value = document.getElementById(lStrVarName).value.replace("&","~");
					if(document.getElementById(lStrVarName).value.indexOf("~amp;") > 0 )
					{
						if(lThis.options[lThis.options.selectedIndex].innerHTML.search("--")<0)
						document.getElementById(lStrVarName).value = document.getElementById(lStrVarName).value.replace("~amp;","~");
					}
					if(document.getElementById(lStrVarName).value.indexOf("amp;") > 0 )
					{
						if(lThis.options[lThis.options.selectedIndex].innerHTML.search("--")<0)
						document.getElementById(lStrVarName).value = document.getElementById(lStrVarName).value.replace("amp;","");
					}
				}
			}
		}
	}
	catch(err)
	{
	}
}
function setStateDescToHiiden()
{
	try
	{
		if(document.getElementById("hidStateDesc"))
		{
			lThis = document.getElementById("cmbState");
			if(lThis.options[lThis.options.selectedIndex].innerHTML.search("--")<0)
			document.getElementById("hidStateDesc").value = lThis.options[lThis.options.selectedIndex].innerHTML;
		}
	}
	catch(err)
	{
	}
}

function setBankDescToHidden()
{
	if(document.getElementById("hidBankDesc"))
	{
		lThis = document.getElementById("cmbBank");
		if(lThis.options[lThis.options.selectedIndex].innerHTML.search("--")<0)
		document.getElementById("hidBankDesc").value = lThis.options[lThis.options.selectedIndex].innerHTML;
	}
}
function setBranchDescToHidden()
{
	if(document.getElementById("hidBranchDesc"))
	{
		lThis = document.getElementById("cmbBranch");
		if(lThis.options[lThis.options.selectedIndex].innerHTML.search("--")<0)
		{
			document.getElementById("hidBranchDesc").value = lThis.options[lThis.options.selectedIndex].innerHTML;
		}

	}
}
function compareWithCommencementDate(lThis)
{
	if(document.getElementById("txtCommensionDate").value.length>0)
	{
		/*if(lThis == null || lThis.name == "txtPpoDate")
		{
			if(document.getElementById("txtPpoDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtPpoDate").value) == false)
				{
					alert("Sanction Date Should be later than Date of Commencement");
					document.getElementById("txtPpoDate").value = "";
					goToFieldTab("txtPpoDate",0);
					return false;
				}
			}
		}*/
		if(lThis == null || lThis.name == "txtPpoClsDate")
		{
			var caseStatus = document.getElementById("cmbPnsnStatus").value;
			if(document.getElementById("txtPpoClsDate").value.length>0)
			{
				if(caseStatus == 'Close' ||caseStatus == 'CloseTransfer')
				{
					if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtPpoClsDate").value) == false)
					{
						alert("PPO Close Date Should be later than Date of Commencement");
						document.getElementById("txtPpoClsDate").value = "";
						goToFieldTab("txtPpoClsDate",0);
						return false;
					}
				}
			}
		}
		if(lThis == null || lThis.name == "txtWHDate")
		{
			var caseStatus = document.getElementById("cmbPnsnStatus").value;
			if(document.getElementById("txtWHDate").value.length>0)
			{
				if(caseStatus == 'With Held')
				{
					if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtWHDate").value) == false)
					{
						alert("Withheld Date Should be later than Date of Commencement");
						document.getElementById("txtWHDate").value = "";
						goToFieldTab("txtWHDate",0);
						return false;
					}
				}
			}
		}
		if(lThis == null || lThis.name == "txtDateOfDeath")
		{
			var pnsnType = document.getElementById("cmbPnsnType").value;
			if(document.getElementById("txtDateOfDeath").value.length>0)
			{
				if(pnsnType != 'Family' && pnsnType != 'Family (Lost)')
				{
					if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtDateOfDeath").value) == false)
					{
						alert("Death Date Should be later than Date of Commencement");
						document.getElementById("txtDateOfDeath").value = "";
						goToFieldTab("txtDateOfDeath",0);
						return false;
					}
				}
			}
		}
		if(lThis == null || lThis.name == "txtSeenDate")
		{
			if(document.getElementById("txtSeenDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtSeenDate").value) == false)
				{
					alert("Seen Date Should be later than Date of Commencement");
					document.getElementById("txtSeenDate").value = "";
					goToFieldTab("txtSeenDate",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtCvpDate")
		{
			if(document.getElementById("txtCvpDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtCvpDate").value) == false)
				{
					alert("CVP Paid Date Should be later than Date of Commencement");
					document.getElementById("txtCvpDate").value = "";
					goToFieldTab("txtCvpDate",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtCvpRestorianDate")
		{
			if(document.getElementById("txtCvpRestorianDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtCvpRestorianDate").value) == false)
				{
					alert("CVP Restoration Date Should be later than Date of Commencement");
					document.getElementById("txtCvpRestorianDate").value = "";
					goToFieldTab("txtCvpRestorianDate",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtAppliedDate")
		{
			if(document.getElementById("txtAppliedDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtAppliedDate").value) == false)
				{
					alert("CVP Restoration Applied Date Should be later than Date of Commencement");
					document.getElementById("txtAppliedDate").value = "";
					goToFieldTab("txtAppliedDate",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtAppliedDate" || lThis.name == "txtCvpRestorianDate" )
		{
			if(document.getElementById("txtAppliedDate").value.length>0 && document.getElementById("txtCvpRestorianDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCvpRestorianDate").value,document.getElementById("txtAppliedDate").value) == false)
				{
					alert("CVP Restoration Applied Date Should be Greater than CVP Restoration Date");
					document.getElementById("txtAppliedDate").value = "";
					goToFieldTab("txtAppliedDate",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtDcrgDate")
		{
			if(document.getElementById("txtDcrgDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtDcrgDate").value) == false)
				{
					alert("DCRG Paid Date Should be later than Date of Commencement");
					document.getElementById("txtDcrgDate").value = "";
					goToFieldTab("txtDcrgDate",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtFp1Date")
		{
			if(document.getElementById("txtFp1Date").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtFp1Date").value) == false)
				{
					alert("FP1 Date Should be later than Date of Commencement");
					document.getElementById("txtFp1Date").value = "";
					goToFieldTab("txtFp1Date",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtFp2Date")
		{
			if(document.getElementById("txtFp2Date").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtFp2Date").value) == false)
				{
					alert("FP2 Date Should be later than Date of Commencement");
					document.getElementById("txtFp2Date").value = "";
					goToFieldTab("txtFp2Date",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtPPOEndDate")
		{
			if(document.getElementById("txtPPOEndDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtPPOEndDate").value) == false)
				{
					alert("PPO End Date Should be later than Date of Commencement");
					document.getElementById("txtPPOEndDate").value = "";
					goToFieldTab("txtPPOEndDate",0);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtLastPaidDate")
		{
			if(document.getElementById("txtLastPaidDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtLastPaidDate").value) == false)
				{
					alert("Last Paid Date Should be later than Date of Commencement");
					document.getElementById("txtLastPaidDate").value = "";
					goToFieldTab("txtLastPaidDate",0);
					return false;
				}
			}
		}

		if(lThis == null || lThis.name == "txtFrm22Date")
		{
			if(document.getElementById("txtFrm22Date").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtFrm22Date").value) == false)
				{
					alert("From22 Issued Date Should be later than Date of Commencement");
					document.getElementById("txtFrm22Date").value = "";
					goToFieldTab("txtFrm22Date",1);
					return false;
				}
			}
		}
		if(lThis == null || lThis.name == "txtLPCDate")
		{
			if(document.getElementById("txtLPCDate").value.length>0)
			{
				if(isProper1(document.getElementById("txtCommensionDate").value,document.getElementById("txtLPCDate").value) == false)
				{
					alert("LPC Issued Date Should be later than Date of Commencement");
					document.getElementById("txtLPCDate").value = "";
					goToFieldTab("txtLPCDate",1);
					return false;
				}
			}
		}
	}
	if(lThis == null || lThis.name == "txtFp2Date" || lThis.name == "txtFp1Date")
	{
		if(document.getElementById("txtFp2Date").value.length>0 && document.getElementById("txtFp1Date").value.length>0)
		{
			if(isProper1(document.getElementById("txtFp1Date").value,document.getElementById("txtFp2Date").value) == false || document.getElementById("txtFp1Date").value == document.getElementById("txtFp2Date").value)
			{
				alert("FP2 Date Should be Greater than FP1 Date");
				document.getElementById("txtFp2Date").value = "";
				goToFieldTab("txtFp2Date",0);
				return false;
			}
			/*var lStrDate = document.getElementById("txtFp1Date").value;
			var fp2Date = getDateByAddingNumberOfDays(lStrDate);
			if(document.getElementById("txtFp2Date").value != fp2Date)
			{
				alert("FP2 Date Should be Next Day of FP1 Date");
				document.getElementById("txtFp2Date").value = "";
				goToFieldTab("txtFp2Date",0);
				return false;
			}*/
		}
	}
}
function compareBasicAndFPAmounts()
{
	 /*var curdate = "01/04/2004";
	 var commnDate  = document.getElementById("txtCommensionDate").value;
	 if(commnDate.length>0)
	 {
	 	 if(isProper1(commnDate,curdate) == true)
		 {
		 	var basic = "0.00";
			var Fp1Amnt = "0.00";
			var Fp2Amnt = "0.00";
			if(document.getElementById("txtBasicPensionAmnt").value.length > 0)
			{
				basic = Number(document.getElementById("txtBasicPensionAmnt").value);
			}
			if(document.getElementById("txtFp1Amnt").value.length > 0)
			{
				Fp1Amnt = Number(document.getElementById("txtFp1Amnt").value);
			}
			if(document.getElementById("txtFp2Amnt").value.length > 0)
			{
				Fp2Amnt = Number(document.getElementById("txtFp2Amnt").value);
			}
			if(basic < Fp1Amnt)
			{
				alert("Basic Pension Amount Must be Greater Than or Equal To FP1 Amount");
				document.getElementById("txtFp1Amnt").value = "0.00";
				goToFieldTab("txtFp1Amnt",0);
				return false;
			}
			if(basic < Fp2Amnt)
			{
				alert("Basic Pension Amount Must be Greater Than or Equal To FP2 Amount");
				document.getElementById("txtFp2Amnt").value = "0.00";
				goToFieldTab("txtFp2Amnt",0);
				return false;
			}
			if(Fp1Amnt < Fp2Amnt)
			{
				alert("FP1 Amount Amount Must be Greater Than or Equal To FP2 Amount");
				document.getElementById("txtFp2Amnt").value = "0.00";
				goToFieldTab("txtFp2Amnt",0);
				return false;
			}
		 }
	 }*/
}
function compareDatesForMingap()
{
	var DOB = document.getElementById("txtDateOfBirth").value;
	var DOJ = document.getElementById("txtDateOfJoin").value;
	var DOR = document.getElementById("txtDateOfRetirement").value;
	var DOC = document.getElementById("txtCommensionDate").value;
	var minDoj;
	var dobDays;
    var dobMonths;
    var dobYear;
    var newDob;
	/*if(DOB.length>0 && DOJ.length>0)
	{
		minDoj = Number(DOB.split("/")[2])+18;
		dobDays = DOB.split("/")[0];
		dobMonths = DOB.split("/")[1];
		newDob = dobDays+"/"+dobMonths+"/"+minDoj;
		if(isProper1(newDob,DOJ) == false)
		{
			alert("Date of Joining Should be Greater than or Equal To "+newDob+"\r\n(18 Years From Date of Birth)");
			document.getElementById("txtDateOfJoin").value = "";
			goToFieldTab("txtDateOfJoin",0);
			return false;
		}
	}*/
	if(DOB.length>0 && DOR.length>0)
	{
		minDoj = Number(DOB.split("/")[2])+18;
		dobDays = DOB.split("/")[0];
		dobMonths = DOB.split("/")[1];
		newDob = dobDays+"/"+dobMonths+"/"+minDoj;
		if(isProper1(newDob,DOR) == false)
		{
			alert("Date of Retirement Should be Greater than or Equal To "+newDob+"\r\n(18 Years From Date of Birth)");
			document.getElementById("txtDateOfRetirement").value = "";
			goToFieldTab("txtDateOfRetirement",0);
			return false;
		}
	}
	if(DOB.length>0 && DOC.length>0)
	{
		minDoj = Number(DOB.split("/")[2])+18;
		dobDays = DOB.split("/")[0];
		dobMonths = DOB.split("/")[1];
		newDob = dobDays+"/"+dobMonths+"/"+minDoj;
		if(isProper1(newDob,DOC) == false)
		{
			alert("Date of Commencement Should be Greater than or Equal To "+newDob+"\r\n(18 Years From Date of Birth)");
			document.getElementById("txtCommensionDate").value = "";
			goToFieldTab("txtCommensionDate",0);
			return false;
		}
	}
}

function getDateByAddingNumberOfDays(varDate)
{
	var pos1=varDate.indexOf("/");
    var pos2=varDate.indexOf("/",pos1+1);
	var days=varDate.substring(0,pos1);
	var month=varDate.substring(pos1+1,pos2);
	var year=varDate.substring(pos2+1);
	var daystoReturn;
	var monthtoReturn;
	var yeartoReturn;
	if(month == 2)
	{
		if(funDaysInFebruary(year) == days)
		{
			daystoReturn = "01";
			monthtoReturn = "03";
			yeartoReturn = year;
		}
		else
		{
			daystoReturn = Number(days)+1;
			if(daystoReturn<10 && daystoReturn.length == 1)
			{
				daystoReturn = "0"+daystoReturn
			}
			monthtoReturn = "02";
			yeartoReturn = year;
		}
	}
	else if(month == 12)
	{
		if(funDaysInFebruary(year) == days)
		{
			daystoReturn = "01";
			monthtoReturn = "01";
			yeartoReturn = Number(year)+1;
		}
		else
		{
			daystoReturn = Number(days)+1;
			if(daystoReturn<10 && daystoReturn.length == 1)
			{
				daystoReturn = "0"+daystoReturn
			}
			monthtoReturn = month;
			yeartoReturn = year;
		}
	}
	else
	{
		if(funDaysInFebruary(year) == days)
		{
			daystoReturn = "01";
			monthtoReturn = Number(month)+1;
			if(monthtoReturn<10 && monthtoReturn.length == 1)
			{
				monthtoReturn = "0"+monthtoReturn
			}
			yeartoReturn = year;
		}
		else
		{
			daystoReturn = Number(days)+1;
			if(daystoReturn<10 && daystoReturn.length == 1)
			{
				daystoReturn = "0"+daystoReturn
			}
			monthtoReturn = month;
			yeartoReturn = year;
		}
	}
	return daystoReturn+"/"+monthtoReturn+"/"+yeartoReturn;
}

function funDaysInFebruary (year)
{
   return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}

function funDaysArray(n)
{
	for (var i = 1; i <= n; i++)
	{
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
 	}
	return this;
}
function funPensionBillConfirm(BillString)
{
	if(BillString.length>0)
	{
		if(confirm(BillString))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{
		return true;
	}
}
function setBillRelatedFlags()
{
	if(document.getElementById("cmbPnsnStatus").value == 'Continue' || document.getElementById("cmbPnsnStatus").value == 'ContinueTransfer')
	{
			if(document.getElementById("radioSeenY").checked  && document.getElementById("txtSeenDate").value.length > 0)
			{
				if(caseRevalidDtls() != false)
				{
					var cvpAmnt = document.getElementById('txtCvpAmnt').value;
					var dcrgAmnt = document.getElementById('txtDcrgAmnt').value;
					var pensionAmnt = document.getElementById('txtReducedPnsnAmnt').value;
					document.getElementById('hidSplFpPensionStatusFlag').value  = "N";
					// For DCRG Start
					if(Number(dcrgAmnt) >0 && document.getElementById("hidDcrg").value == "Y" && document.getElementById("radioDCRGN").checked)
					{
						document.getElementById('hidSplFpDCRGFlag').value  = "Y";
					}
					else
					{
						document.getElementById('hidSplFpDCRGFlag').value  = "N";
					}
					// For	DCRG End
				
					// For CVP Start
					
					if(Number(cvpAmnt) >0 && document.getElementById("hidCVP").value == "Y" && document.getElementById("radioCVPN").checked)
					{
						document.getElementById('hidSplFpCVPFlag').value  = "Y";
					}
					else
					{
						document.getElementById('hidSplFpCVPFlag').value  = "N";
					}
					 	// For CVP End
					
					// For Pension Start
					if(Number(pensionAmnt) >0 && document.getElementById("hidPension").value == "Y")
					{
						document.getElementById('hidSplFpPensionFlag').value  = "Y";
						if(document.getElementById('cmbFPPension'))
						{
							if(document.getElementById("cmbFPPension").value == 'Y')
							{
								document.getElementById('hidSplFpPensionFlag').value  = "N";
								if(document.getElementById('hidSplFpCVPFlag').value == 'N' && document.getElementById('hidSplFpDCRGFlag').value == 'N')
								{
									if(confirm("After Updation of Case Will appear in TO Approve FpCases Link\r\n Do You Want to Continue"))
									{
										document.getElementById('hidSplFpPensionStatusFlag').value  = "Y";
									}
								}
								
							}
						}
					}
					else
					{
						document.getElementById('hidSplFpPensionFlag').value  = "N";
					}
					 	// For Pension End
				}
			}
	}
}

// to set Nominee bank branch
var tempCCount = 0;
function getBranchByBranchCodeNom(obj)
{
	var ltemp = obj.id;
	ltemp = ltemp.substring(16,ltemp.length);
	
	tempCCount = ltemp;
	if(document.getElementById("txtbranchCodeNom"+ltemp).value.length > 0 )
	{
		req = createXMLHttpRequest();
		if(req != null)
		{
			var baseUrl = "ifms.htm?actionFlag=getBranchbyCode&txtbranchCode="+document.getElementById("txtbranchCodeNom"+ltemp).value;
			if(document.getElementById("cmbTreasury"))
			{
				baseUrl = baseUrl+"&cmbTreasury="+document.getElementById("cmbTreasury").value;
			}
			req.open("post", baseUrl, true); 
			req.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			req.onreadystatechange = responsegetBranchByBranchCodeNom; 
			req.send(baseUrl);
		}
		else
		{
			alert (AL_AJAX);
		}
	}
	else
	{
		document.getElementById("cmbBankNom"+ltemp).value = "-1";
		document.getElementById("cmbBranchNom"+ltemp).options[0].value = "-1";
		document.getElementById("cmbBranchNom"+ltemp).options[0].innerHTML = " -- Select --";
		document.getElementById("cmbBankNom"+ltemp).disabled="";
		document.getElementById("cmbBranchNom"+ltemp).disabled="";
		if(document.getElementById("mandtryBankNom"+ltemp))
		{
			document.getElementById("mandtryBankNom"+ltemp).style.display = "none";
		}
	}
}
function responsegetBranchByBranchCodeNom()
{
	if(req.readyState==complete_state)
	{ 
		var XMLDoc = req.responseXML.documentElement;
		var XmlResValues = XMLDoc.getElementsByTagName('XMLDOC');	
			if(XmlResValues[0].childNodes[0] != null)
			{
				document.getElementById("cmbBankNom"+tempCCount).value = XmlResValues[0].childNodes[0].text
				document.getElementById("cmbBranchNom"+tempCCount).options[0].value = XmlResValues[0].childNodes[1].text;
				document.getElementById("cmbBranchNom"+tempCCount).options[0].innerHTML = XmlResValues[0].childNodes[2].text;
				document.getElementById("cmbBranchNom"+tempCCount).options[0].selected = "selected"
				document.getElementById("cmbBankNom"+tempCCount).disabled="disabled";
				document.getElementById("cmbBranchNom"+tempCCount).disabled="disabled";
				if(document.getElementById("mandtryBankNom"+tempCCount))
				{
					document.getElementById("mandtryBankNom"+tempCCount).style.display = "inline";
				}
			}
		
		else
		{
			alert("Please Enter Proper Bankcode");
			document.getElementById("txtbranchCodeNom"+tempCCount).focus();
			document.getElementById("txtbranchCodeNom"+tempCCount).value = "";
			document.getElementById("cmbBankNom"+tempCCount).value = "-1";
			document.getElementById("cmbBranchNom"+tempCCount).options[0].value = "-1";
			document.getElementById("cmbBranchNom"+tempCCount).options[0].innerHTML = " -- Select --";
			document.getElementById("cmbBankNom"+tempCCount).disabled="";
			document.getElementById("cmbBranchNom"+tempCCount).disabled="";
			if(document.getElementById("mandtryBankNom"+tempCCount))
			{
				document.getElementById("mandtryBankNom"+tempCCount).style.display = "none";
			}
		}
	}
}
