function allegDisplayPart()
{
	document.getElementById('attachTab').style.display='none'; 
	document.getElementById('prePrelim4').style.display='';
	dispAlleg2();
	}

function prosecPending()
{
	document.getElementById("deptInquiry5").style.display="";	
	document.getElementById("prosecInitBtn").style.display="none";
	document.getElementById("chkBoxInitProsec").style.display="none";
		
}

function prosecInit()
{
	document.getElementById("deptInquiry5").style.display="";	
	document.getElementById("prosecInitBtn").style.display="none";
	document.getElementById("chkBoxInitProsec").style.display="none";
	document.getElementById("formSubmitButton").disabled=false;
}

function suspPending()
{
	document.getElementById("deptInquiry4").style.display="";	
	document.getElementById("prosecInitBtn").style.display="";
	document.getElementById("chkBoxInitProsec").style.display="";
	document.getElementById("suspInitBtn").style.display="none";
	document.getElementById("chkBoxInitSusp").style.display="none";
	document.getElementById("formSubmitButton").disabled=true;
}

function suspInit()
{
document.getElementById("deptInquiry4").style.display="";	
document.getElementById("suspInitBtn").style.display="none";
document.getElementById("chkBoxInitSusp").style.display="none";
document.getElementById("formSubmitButton").disabled=false;
}

function deptPend()
{
document.getElementById("deptInquiry3").style.display="";	
document.getElementById("deptInitBtn").style.display="none";
document.getElementById("chkBoxInitDept").style.display="none";
document.getElementById("suspInitBtn").style.display="";
document.getElementById("chkBoxInitSusp").style.display="";
document.getElementById("formSubmitButton").disabled=true;
document.getElementById("major").style.display="none";
document.getElementById("minor").style.display="none";

//For both req and file
document.inquiryCaseTracking.flagEnqOfficer[0].disabled=true;
document.inquiryCaseTracking.flagEnqOfficer[1].disabled=true;
document.inquiryCaseTracking.flagPresenOfficer[0].disabled=true;
document.inquiryCaseTracking.flagPresenOfficer[1].disabled=true;	
document.inquiryCaseTracking.flagPresidOfficer[0].disabled=true;
document.inquiryCaseTracking.flagPresidOfficer[1].disabled=true;
document.inquiryCaseTracking.decisionFlag[0].disabled=true;
document.inquiryCaseTracking.decisionFlag[1].disabled=true;
}


function deptInit()
{
document.getElementById("deptInquiry3").style.display="";	
document.getElementById("deptInitBtn").style.display="none";
document.getElementById("chkBoxInitDept").style.display="none";
document.getElementById("formSubmitButton").disabled=false;
}

function prelimInit()
{
document.getElementById("deptInquiry2").style.display="";	
document.getElementById("prelimInitBtn").style.display="none";
document.getElementById("chkBoxInitPrelim").style.display="none";
document.getElementById("formSubmitButton").disabled=false;
}

function prelimPending()
{
document.getElementById("deptInquiry2").style.display="";
document.getElementById("prelimInitBtn").style.display="none";
document.getElementById("chkBoxInitPrelim").style.display="none";
document.getElementById("deptInitBtn").style.display="";
document.getElementById("chkBoxInitDept").style.display="";
document.getElementById("formSubmitButton").disabled=true;

//for both req and file
document.inquiryCaseTracking.radioenqOffPrelim[0].disabled=true;
document.inquiryCaseTracking.radioenqOffPrelim[1].disabled=true;
document.inquiryCaseTracking.refToVC[0].disabled=true;
document.inquiryCaseTracking.refToVC[1].disabled=true;
document.inquiryCaseTracking.noOfEmpSel.disabled=true;	
}


function dispDispPrevNext()
{
document.inquiryCaseTracking.Prev.disabled=true;
document.inquiryCaseTracking.Next.disabled=true;
}

function displayAllNoUserId()
{
document.getElementById("userIdInput").style.display='';
document.inquiryCaseTracking.radioenqOff[0].checked=true;
enqOffDisp();
}


 function disableTweety(){
 hidePrePrelim();
 document.getElementById("expandTicket").style.display="";
 document.getElementById("collapseTicket").style.display="none";
 document.getElementById("prelimInitBtn").style.display="";
 document.getElementById("chkBoxInitPrelim").style.display="";
 document.getElementById("formSubmitButton").disabled=true;
 //For both "req" and "file"
 document.inquiryCaseTracking.radioenqOff[0].disabled=true;
 document.inquiryCaseTracking.radioenqOff[1].disabled=true;
 document.getElementById("Search1").disabled=true;		
 }
 function closewindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.forms[0].action=urlstyle;
		document.forms[0].submit();
	} 
function dispAlleg2()
{
	
	document.getElementById("prePrelim1").style.display='block';
	document.getElementById("prePrelim2").style.display='block';
	document.getElementById("prePrelimAdd").style.display='block';
	document.getElementById("allegLabel").style.display='block'; 
	document.getElementById("expandTicketAlleg").style.display='none'; 
	document.getElementById("collapseTicketAlleg").style.display=''; 
 }
 function displayAllPrelim2(){
		 document.getElementById("enqTable").style.display='';
		document.getElementById("delinqDtlTable").style.display='';
		document.getElementById("noOfEmpTab").style.display='';		
		
 }
	function checkInit()
	{
	
	if(document.inquiryCaseTracking.initRadio[0].checked==true)
		{
		document.inquiryCaseTracking.initBtn.disabled=false;
		}
	else if(document.inquiryCaseTracking.initRadio[1].checked==true)
		{
		document.inquiryCaseTracking.initBtn.disabled=false;
		}

	}

	function dispPrePrelim()
	{
	
	document.getElementById("expandTicket").style.display="none";
	document.getElementById("collapseTicket").style.display="";
	
	
	displayAll();
	hideAlleg();
	}
	
	function hidePrePrelim()
	{
	
	hideAlleg();
	document.getElementById("expandTicket").style.display="";
	document.getElementById("collapseTicket").style.display="none";
	hideAll();
	}
	
	function dispPrelim()
	{
	
	document.getElementById("expandTicket2").style.display='none';
	document.getElementById("collapseTicket2").style.display='';
	document.getElementById("deptInquiry2").style.display='';
		
	displayAllPrelim();
	}
	
	function hidePrelim()
	{
	
	document.getElementById("expandTicket2").style.display='';
	document.getElementById("collapseTicket2").style.display='none';
	document.getElementById("deptInquiry2").style.display='';
	document.getElementById('VCTable').style.display='none';
		
	hideAllPrelim();
	}
	
	function dispDept()
	{
	
	document.getElementById("expandTicket3").style.display='none';
	document.getElementById("collapseTicket3").style.display='';
	document.getElementById("deptInquiry3").style.display='';
		
	displayAllDept();
	}
	
	function hideDept()
	{
	
	document.getElementById("expandTicket3").style.display='';
	document.getElementById("collapseTicket3").style.display='none';
	document.getElementById("deptInquiry3").style.display='';
	
	document.getElementById("major").style.display="none";
	document.getElementById("minor").style.display="none";
	hideAllDept();
	}
	
	function dispSusp()
	{
	
	document.getElementById("expandTicket4").style.display='none';
	document.getElementById("collapseTicket4").style.display='';
	document.getElementById("deptInquiry4").style.display='';
	displaySuspReview();
	displaySuspDtls();
	}
	
	function hideSusp()
	{
	
	document.getElementById("expandTicket4").style.display='';
	document.getElementById("collapseTicket4").style.display='none';
	document.getElementById("deptInquiry4").style.display='';
	hideSuspReview();
	hideSuspDtls();
	}
	
	
	function dispPros()
	{
	
	document.getElementById("expandTicket5").style.display='none';
	document.getElementById("collapseTicket5").style.display='';
	document.getElementById("deptInquiry5").style.display='';
	displayProsec();
	}
	
	function hidePros()
	{
	
	document.getElementById("expandTicket5").style.display='';
	document.getElementById("collapseTicket5").style.display='none';
	document.getElementById("deptInquiry5").style.display='';
	hideProsec();
	}

	function makePrePrelimReadOnly()
	{
	
	makeReadOnly('deptPrePrilimDtls');
	document.getElementById("Search1").disabled=true;
	document.getElementById("Search2").disabled=true;
	document.getElementById("sourceInquiryPrePre").disabled=true;
	document.getElementById("firstName").disabled=true;
	document.getElementById("middleName").disabled=true;
	document.getElementById("lastName").disabled=true;

	document.getElementById("dateOfBirth").disabled=true;
	document.getElementById("retireDt").disabled=true;
	document.getElementById("presentPay").disabled=true;
	document.getElementById("payScale").disabled=true;
	document.getElementById("nextIncDt").disabled=true;
//	document.getElementById("joinCurrPositionDt").disabled=true;
//	document.getElementById("desigAtLapse").disabled=true;
	document.getElementById("empName").disabled=true;
	
	document.getElementById("empName2").disabled=true;
	document.getElementById("dateOfBirth2").disabled=true;
	document.getElementById("retireDt2").disabled=true;
	document.getElementById("presentPay2").disabled=true;
	document.getElementById("payScale2").disabled=true;
	document.getElementById("nextIncDt2").disabled=true;
//	document.getElementById("joinCurrPositionDt2").disabled=true;
//	document.getElementById("desigAtLapse2").disabled=true;
	
	document.getElementById("phone").disabled=true;
	document.getElementById("fax").disabled=true;
	document.getElementById("prePrelimDept").disabled=true;
	document.getElementById("email").disabled=true;
	document.getElementById("div").disabled=true;
	document.getElementById("prePrelimbranch").disabled=true;
	document.getElementById("InqfirstName").disabled=true;
	document.getElementById("InqmiddleName").disabled=true;
	document.getElementById("InqlastName").disabled=true;
	document.getElementById("Inqfax").disabled=true;
	document.getElementById("userIdEnqOff").disabled=true;
	document.getElementById("nameWitness").disabled=true;
	document.getElementById("firstnameWitness").disabled=true;
	document.getElementById("midnameWitness").disabled=true;
	
	document.getElementById("period").disabled=true;
	document.getElementById("dtlAllegation").disabled=true;
	
	document.inquiryCaseTracking.stmtReq[0].disabled=true;
	document.inquiryCaseTracking.stmtReq[1].disabled=true;
	document.inquiryCaseTracking.docEvidence[0].disabled=true;
	document.inquiryCaseTracking.docEvidence[1].disabled=true;
	document.inquiryCaseTracking.radioenqOff[0].disabled=true;
	document.inquiryCaseTracking.radioenqOff[1].disabled=true;
	document.getElementById("enqStartDate").disabled=true; 
	document.getElementById("appDate").disabled=true;
	document.getElementById("eventDt").disabled=true;
	
	document.getElementById("enqOffPhone").disabled=true; 
	document.getElementById("enqOffPhoneResi").disabled=true;
	document.getElementById("enqOffPhoneMobile").disabled=true;
	
	document.getElementById("frmAllegDt").disabled=true;
	document.getElementById("toAllegDt").disabled=true;
	
	//MAke attatchment ReadOnly
	document.getElementById('target_uploadAllegAttatchment').style.display='none';
	document.getElementById('formTable1AllegAttatchment').firstChild.firstChild.style.display='none';
	
	}
	
	
	function makePrelimReadOnly()
	{
	
	document.inquiryCaseTracking.queryVCDt.disabled=true;
	document.inquiryCaseTracking.compVCDt.disabled=true;
	document.inquiryCaseTracking.adviceVCDt.disabled=true;
	document.inquiryCaseTracking.referenceVCDt.disabled=true;
	document.inquiryCaseTracking.inqEntrustedDt.disabled=true;
	document.inquiryCaseTracking.inqReportSubDt.disabled=true;
	document.inquiryCaseTracking.decisionMadeDt.disabled=true;
	
	document.inquiryCaseTracking.decisionOnReport[0].disabled=true;
	document.inquiryCaseTracking.decisionOnReport[1].disabled=true;
	document.inquiryCaseTracking.refToVC[0].disabled=true;
	document.inquiryCaseTracking.refToVC[1].disabled=true;
	document.inquiryCaseTracking.chgIssued[0].disabled=true;
	document.inquiryCaseTracking.chgIssued[1].disabled=true;
	document.inquiryCaseTracking.radioenqOffPrelim[0].disabled=true;
	document.inquiryCaseTracking.radioenqOffPrelim[1].disabled=true;
	
	document.inquiryCaseTracking.delinqName1.disabled=true;
	document.inquiryCaseTracking.delinqName2.disabled=true;
	document.inquiryCaseTracking.delinqName3.disabled=true;
	document.inquiryCaseTracking.delinqName4.disabled=true;
	
	
	document.inquiryCaseTracking.InqfirstNamePrelim.disabled=true;
	document.inquiryCaseTracking.InqmiddleNamePrelim.disabled=true;
	document.inquiryCaseTracking.InqlastNamePrelim.disabled=true;
	document.inquiryCaseTracking.enqOffPhonePrelim.disabled=true;
	
	document.inquiryCaseTracking.enqOffPhoneResiPrelim.disabled=true;
	document.inquiryCaseTracking.enqOffPhoneMobilePrelim.disabled=true;
	document.inquiryCaseTracking.InqfaxPrelim.disabled=true;
	document.inquiryCaseTracking.userIdEnqOffPrelim.disabled=true;
	
	document.inquiryCaseTracking.dtlsDecisionReport.disabled=true;
	document.inquiryCaseTracking.folderVCNo.disabled=true;
	document.inquiryCaseTracking.Position.disabled=true;
	
	document.inquiryCaseTracking.noOfEmpSel.disabled=true;
	document.inquiryCaseTracking.typePunish.disabled=true;
	document.inquiryCaseTracking.selectVC.disabled=true;
	document.inquiryCaseTracking.class1.disabled=true;
	document.inquiryCaseTracking.class2.disabled=true;
	document.inquiryCaseTracking.class3.disabled=true;
	document.inquiryCaseTracking.class4.disabled=true;
	
	document.inquiryCaseTracking.Search6.disabled=true;
	}
	
	function makeDeptReadOnly()
	{
	
	document.inquiryCaseTracking.inquiryCaseNo.disabled=true;
	document.inquiryCaseTracking.source.disabled=true;
	document.inquiryCaseTracking.inquiryFileNo.disabled=true;
	document.inquiryCaseTracking.deptName.disabled=true;
	document.inquiryCaseTracking.flagEnqOfficer[0].disabled=true;
	document.inquiryCaseTracking.flagEnqOfficer[1].disabled=true;
	document.inquiryCaseTracking.inqOffNameDept.disabled=true;
	document.inquiryCaseTracking.inqOffNameMidDept.disabled=true;
	document.inquiryCaseTracking.inqOffNameLastDept.disabled=true;
	document.inquiryCaseTracking.phoneMobDept.disabled=true;
	document.inquiryCaseTracking.deptRule.disabled=true;
	document.inquiryCaseTracking.phoneOff.disabled=true;
	document.inquiryCaseTracking.phoneRes.disabled=true;
	
	document.inquiryCaseTracking.inqOffDesigDept.disabled=true;
	document.inquiryCaseTracking.appInquiryOffDate.disabled=true;
	
	
	document.inquiryCaseTracking.inqOffUserId.disabled=true;
	document.inquiryCaseTracking.flagPresenOfficer[0].disabled=true;
	document.inquiryCaseTracking.flagPresenOfficer[1].disabled=true;
	document.inquiryCaseTracking.presentOffName.disabled=true;
	document.inquiryCaseTracking.presentOffMidName.disabled=true;
	document.inquiryCaseTracking.presentOffLastName.disabled=true;
	document.inquiryCaseTracking.phoneNoPresentMob.disabled=true;
		
	document.inquiryCaseTracking.presentOffDesig.disabled=true;
	document.inquiryCaseTracking.appPresentOffDt.disabled=true;
	document.inquiryCaseTracking.phoneNoPresentRes.disabled=true;
	document.inquiryCaseTracking.phoneNoPresentOff.disabled=true;
	document.inquiryCaseTracking.presentOffUserId.disabled=true;
	document.inquiryCaseTracking.caseHandedOverDt.disabled=true;
	document.inquiryCaseTracking.inquiryReportSubDt.disabled=true;
	
	document.inquiryCaseTracking.presOffName.disabled=true;
	document.inquiryCaseTracking.presOffMidName.disabled=true;
	document.inquiryCaseTracking.presOffLastName.disabled=true;
	
	document.inquiryCaseTracking.phoneNoPresOff.disabled=true;
	document.inquiryCaseTracking.phoneNoPresRes.disabled=true;
	document.inquiryCaseTracking.phoneNoPresMob.disabled=true;
	
	document.inquiryCaseTracking.flagPresidOfficer[0].disabled=true;
	document.inquiryCaseTracking.flagPresidOfficer[1].disabled=true;
	document.inquiryCaseTracking.presOffAppDt.disabled=true;
	document.inquiryCaseTracking.designation.disabled=true;
	document.inquiryCaseTracking.presidOffUserId.disabled=true;
	document.inquiryCaseTracking.decisionFlag[0].disabled=true;
	document.inquiryCaseTracking.decisionFlag[1].disabled=true;
	
	document.inquiryCaseTracking.Search3.disabled=true;
	document.inquiryCaseTracking.Search4.disabled=true;
	document.inquiryCaseTracking.Search5.disabled=true;
	
	makeMinorPunishReadOnly();
	makeMajorPunishReadOnly();
	}
	
	function makeSuspReadOnly()
	{
	
	document.inquiryCaseTracking.inq_text.disabled=true;
	document.inquiryCaseTracking.appdate.disabled=true;
	
	document.inquiryCaseTracking.suspensFromdate.disabled=true;
	document.inquiryCaseTracking.suspensTodate.disabled=true;
	
	document.inquiryCaseTracking.isPrelimReq[0].disabled=true;
	document.inquiryCaseTracking.isPrelimReq[1].disabled=true;
	document.inquiryCaseTracking.departmentalname.disabled=true;
	document.inquiryCaseTracking.remarks.disabled=true;
	document.inquiryCaseTracking.incdate.disabled=true;
	document.inquiryCaseTracking.depName.disabled=true;
	document.inquiryCaseTracking.division.disabled=true;
	document.inquiryCaseTracking.branchSusp.disabled=true;
	document.inquiryCaseTracking.suspOrderNo.disabled=true;
	document.inquiryCaseTracking.orderdate.disabled=true;
	document.inquiryCaseTracking.suspdetails.disabled=true;
	document.inquiryCaseTracking.suspallAllow.disabled=true;
	
	
	document.inquiryCaseTracking.suspensionDt.disabled=true;
	document.inquiryCaseTracking.reviewDt.disabled=true;
	document.inquiryCaseTracking.lastReviewDt.disabled=true;
	document.inquiryCaseTracking.reasonNotReview.disabled=true;
	document.inquiryCaseTracking.oneYearSusp.disabled=true;
	document.inquiryCaseTracking.twoYearSusp.disabled=true;
	document.inquiryCaseTracking.suspendedVCRadio[0].disabled=true;
	document.inquiryCaseTracking.suspendedVCRadio[1].disabled=true;
	document.inquiryCaseTracking.suspAllowance.disabled=true;
	document.inquiryCaseTracking.headQuater.disabled=true;
	document.inquiryCaseTracking.pvtBusinessRadio[0].disabled=true;
	document.inquiryCaseTracking.nonEmpRadio[1].disabled=true;
	document.inquiryCaseTracking.reinstatementRadio[0].disabled=true;
	document.inquiryCaseTracking.pvtBusinessRadio[1].disabled=true;
	document.inquiryCaseTracking.nonEmpRadio[0].disabled=true;
	document.inquiryCaseTracking.reinstatementRadio[1].disabled=true;
	document.inquiryCaseTracking.briefDetails.disabled=true;
	document.inquiryCaseTracking.reinstatementDt.disabled=true;
	}
	
	function makeProsecReadOnly()
	{
	
	document.inquiryCaseTracking.prosecCasePend[0].disabled=true;
	document.inquiryCaseTracking.prosecCasePend[1].disabled=true;
	document.inquiryCaseTracking.decisionProsec[0].disabled=true;
	document.inquiryCaseTracking.decisionProsec[1].disabled=true;
	document.inquiryCaseTracking.prosecOrderNo.disabled=true;
	document.inquiryCaseTracking.causeProsec.disabled=true;
	document.inquiryCaseTracking.prosecChargeSheetDt.disabled=true;
	document.inquiryCaseTracking.prosecOrderDt.disabled=true;
	document.inquiryCaseTracking.prosecLatestPosition.disabled=true;
	}
	
	
	function closeBtnStatus()
	{
	
	if(document.inquiryCaseTracking.chkBoxCloseEnq.checked==true)
		{
		document.inquiryCaseTracking.close.disabled=false;
		}
	else
		{
		document.inquiryCaseTracking.close.disabled=true;
		}
	}
	
	
	


	

	function addData(form)

{

var empDtlTable=document.getElementById('empDtlTable');
	if(form.value!="-1")
	{
         
		clearRows();
		addOrUpdateRecord1('addRecord','submitEnqReq',new Array('user_id1'));
		var xmlKey=document.getElementById('xmlKey');
	
		var response=xmlKey.value;
	

		
		amtVar=0;
		empDtlTable.style.display="";
		
		getData(response);
		

	}else
	{
		clearRows();
		empDtlTable.style.display="none";
	}
}
function addOrUpdateRecord1(methodName, actionFlag, fieldArray) 
	{	
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		showProgressbar("Please Wait...");
		
		var reqBody = getRequestBody(fieldArray);	
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange=function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				hideProgressbar();
			}	
		}
	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}	


function getData(ajaxKeyArray){

			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null) 
			{
			  alert ("Your browser does not support AJAX!");
			  return;
			} 
			var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + ajaxKeyArray;
			
			xmlHttp.onreadystatechange = function() 
								{
									eval('populateForm()'); 
									
								}
			xmlHttp.open("POST",encodeURI(url),false);
			xmlHttp.send(null);	
			
			
	
}
function clearRows()
{

		
}


function addRecord()
	{
		if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			var xmlKey=document.getElementById('xmlKey');
			xmlKey.value=encXML;

	}
}

function populateForm()
	{
		 if (xmlHttp.readyState == 4) 
		 { 				  
			  	var decXML = xmlHttp.responseText;
			  
			  	var xmlDom=getDOMFromXML(decXML);
			
				
				//var joinCurrPositionDt=document.getElementById('joinCurrPositionDt');
				//var desigAtLapse=document.getElementById('desigAtLapse');
							
				var  expDate = getXPathValueFromDOM (xmlDom, 'expDate');   

				if(expDate != null)
				{
					var dateArray1 = getDateAndTimeFromDateObj(expDate);
					document.getElementById('retireDt').value = dateArray1[0];
				}
				
				var  dob = getXPathValueFromDOM (xmlDom, 'dob');   

				if(dob != null)
				{
					var dateArray1 = getDateAndTimeFromDateObj(dob);
					document.getElementById('dateOfBirth').value = dateArray1[0];
				}
				/*
				var  dateOfIncrement = getXPathValueFromDOM (xmlDom, 'dateOfIncrement');   

				if(dateOfIncrement != null)
				{
					var dateArray1 = getDateAndTimeFromDateObj(dateOfIncrement);
					document.getElementById('nextIncDt').value = dateArray1[0];
				}
				
				document.getElementById('retireDt').value=getXPathValueFromDOM(xmlDom,'expDate');
				document.getElementById('dateOfBirth').value=getXPathValueFromDOM(xmlDom,'dob'); */
				
				if(getXPathValueFromDOM(xmlDom,'dateOfIncrement')!=null)
				{
				document.getElementById('nextIncDt').value=getXPathValueFromDOM(xmlDom,'dateOfIncrement'); 
				}
				if(getXPathValueFromDOM(xmlDom,'presentPay')!=null)
				{
				document.forms.inquiryCaseTracking.prePay.value=getXPathValueFromDOM(xmlDom,'presentPay'); 
				}
				if(getXPathValueFromDOM(xmlDom,'scale')!=null)
				{
				document.getElementById('payScale').value=getXPathValueFromDOM(xmlDom,'scale'); 
				}
				document.getElementById('empName').value=getXPathValueFromDOM(xmlDom,'empName'); 
		 }	  	
	}
	
function addRecordPunish2() 
	{
	
	  if (xmlHttp.readyState == 4)
	  { 	
	 			
		var displayFieldArray = new Array("nameDelinq", "sourceInq", "subInqReportDt", "decisionDate");
		addDataInTable('txnAdd2', 'encXML', displayFieldArray,   'editRecordPunish', 'deleteRecord');
				
   	    resetDataPunish();   			
	   }	
	}
function editRecordPunish2(rowId)
	 {

		sendAjaxRequestForEdit(rowId, 'populateFormPunish2');
	 }	
	 
	  function resetDataPunish2() 
	{	

		document.getElementById('nameDelinq').value = '';
		document.getElementById('sourceInq').value = '';
   		document.getElementById('subInqReportDt').value = '';	
   		document.getElementById('decisionDate').value = '';
   		
   	}		
function populateFormPunish2()
	{
	
  	    if (xmlHttp.readyState == 4) 
  	    { 	
	  	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);
		
	  document.getElementById('chargeNo2').innerHTML= getXPathValueFromDOM(xmlDOM, 'nameOfDelinquent');   
 	    document.getElementById('repFind2').innerHTML = getXPathValueFromDOM(xmlDOM, 'sourceEnquiry'); 
	 
	   document.getElementById('inqRepDtls2').innerHTML= getXPathValueFromDOM(xmlDOM, 'decisionDate'); 
	     document.getElementById('discAuthorityFind2').innerHTML=getXPathValueFromDOM(xmlDOM, 'submissionEnqDate'); 
	     }
	}
	   
	
 function updateRecordPunish2() 
	{
	
	  if (xmlHttp.readyState == 4) 
	  	{ 	
		var displayFieldArray = new Array("nameDelinq", "sourceInq", "subInqReportDt", "decisionDate");
		updateDataInTable('encXML', displayFieldArray);		
	
		resetDataPunish2();
		document.getElementById('btnAdd2').style.display='';
		document.getElementById('btnUpdate2').style.display='none';    	
		}
	}	
	
	
	
function showEnqOfficerDtl()
{
	document.getElementById("enqOfficerDtl").style.display="";
	document.getElementById("EnqOfficerUserId").style.display="none";
}
function showEnqOfficerUserId()
{
	document.getElementById("enqOfficerDtl").style.display="none";
	document.getElementById("EnqOfficerUserId").style.display="";
}
function showPresenOfficerDtl()
{
	document.getElementById("presenOfficerDtl").style.display="";
	document.getElementById("presenOfficerUserId").style.display="none";
}
function showPresenOfficerUserId()
{
	document.getElementById("presenOfficerDtl").style.display="none";
	document.getElementById("presenOfficerUserId").style.display="";
}
function showPresidOfficerDtl()
{
	document.getElementById("presidOfficerDtl").style.display="";
	document.getElementById("presidOfficerUserId").style.display="none";
}
function showPresidOfficerUserId()
{
	document.getElementById("presidOfficerDtl").style.display="none";
	document.getElementById("presidOfficerUserId").style.display="";
}

function showRuleDtl()
{
	document.getElementById("decision").style.display="";
}

function hideRuleDtl()
{
	document.getElementById("decision").style.display="none";
	document.getElementById("major").style.display="none";
	document.getElementById("minor").style.display="none";
	document.getElementById("deptRule").value=-1;
}

	function displayOffence()
	{
		if(document.getElementById("deptRule").value=="dept_rule9")
		{
		document.getElementById("minor").style.display="";
		document.getElementById("major").style.display="none";
		displayMinorPunish();
		}
		
		if(document.getElementById("deptRule").value=="dept_rule11")
		{
		document.getElementById("major").style.display="";
		document.getElementById("minor").style.display="none";
		displayMajorPunish();
		}
	}
	
	
/*	
function addRecordPunish() 
	{
	
	  if (xmlHttp.readyState == 4)
	  { 	
	 			
		var displayFieldArray = new Array("nameDelinquent", "sourceInquiry", "subInquiryReportDt", "decisionDt");
		addDataInTable('txnAdd3', 'encXML', displayFieldArray,   'editRecordPunish', 'deleteRecord');
				
   	    resetDataPunish();   			
	   }	
	}
function editRecordPunish(rowId)
	 {

		sendAjaxRequestForEdit(rowId, 'populateFormPunish');
	 }	
	 
	  function resetDataPunish() 
	{	

		document.getElementById('nameDelinquent').value = '';
		document.getElementById('sourceInquiry').value = '';
   		document.getElementById('subInquiryReportDt').value = '';	
   		document.getElementById('decisionDt').value = '';
   		
   	}		
function populateFormPunish()
	{
	
  	    if (xmlHttp.readyState == 4) 
  	    { 	
	  	var decXML = xmlHttp.responseText;
		var xmlDOM = getDOMFromXML(decXML);
		
	  document.getElementById('chargeNo').innerHTML= getXPathValueFromDOM(xmlDOM, 'nameOfDelinquent');   
 	    document.getElementById('repFind').innerHTML = getXPathValueFromDOM(xmlDOM, 'sourceEnquiry'); 
	 
	   document.getElementById('inqRepDtls').innerHTML= getXPathValueFromDOM(xmlDOM, 'decisionDate'); 
	     document.getElementById('discAuthorityFind').innerHTML=getXPathValueFromDOM(xmlDOM, 'submissionEnqDate'); 
	     }
	}
	   
	
 function updateRecordPunish() 
	{
	
	  if (xmlHttp.readyState == 4) 
	  	{ 	
		var displayFieldArray = new Array("nameDelinquent", "sourceInquiry", "subInquiryReportDt", "decisionDt");
		updateDataInTable('encXML', displayFieldArray);		
	
		resetDataPunish();
		document.getElementById('btnAdd3').style.display='';
		document.getElementById('btnUpdate3').style.display='none';    	
		}
	}	 
*/

 function resetDataAlleg() 
	{	
		document.getElementById('dtlAllegation').value = '';
		document.getElementById('nameWitness').value = '';
		document.getElementById('firstnameWitness').value = '';
		document.getElementById('midnameWitness').value = '';
   		document.getElementById('period').value = '';	
   		document.inquiryCaseTracking.stmtReq[0].checked = '';
   		document.inquiryCaseTracking.docEvidence[0].checked = '';
   		document.inquiryCaseTracking.stmtReq[1].checked = '';
   		document.inquiryCaseTracking.docEvidence[1].checked = '';
   		
   		document.getElementById('toAllegDt').value = '';
		document.getElementById('frmAllegDt').value = '';
		
   	}		

	 function editRecordAlleg(rowId,rowNum)
	 {
		sendAjaxRequestForEditAttachment(rowId, 'populateFormAlleg','AllegAttatchment',rowNum);
	 }	
	 
	
	 
	 
 function populateFormAlleg()
	{
  	    if (xmlHttp.readyState == 4) 
  	    { 	
	  	var decXML = xmlHttp.responseText;
	  	
		var xmlDOM = populateAttachment(decXML,'inquiryCaseTracking');
	
		if(getXPathValueFromDOM(xmlDOM, 'allegationDetails')!=null)
		{ 	
		document.getElementById('dtlAllegation').value = getXPathValueFromDOM(xmlDOM, 'allegationDetails');   
		}
		else
			{
			document.getElementById('dtlAllegation').value = '-';   
		
			}
		if(getXPathValueFromDOM(xmlDOM, 'eventPeriod')!=null)
 	    {
 	    document.getElementById('period').value = getXPathValueFromDOM(xmlDOM, 'eventPeriod'); 
 	    }
 	    if(getXPathValueFromDOM(xmlDOM, 'cmnPersonMst/lastName')!=null)
		{
		document.getElementById('nameWitness').value = getXPathValueFromDOM(xmlDOM, 'cmnPersonMst/lastName'); 
		}
			
		if(getXPathValueFromDOM(xmlDOM, 'cmnPersonMst/firstName')!=null)
		{
	 	document.getElementById('firstnameWitness').value = getXPathValueFromDOM(xmlDOM, 'cmnPersonMst/firstName');
	 	}
		 	
	 	if(getXPathValueFromDOM(xmlDOM, 'cmnPersonMst/middleName')!=null)
		{
	 	document.getElementById('midnameWitness').value = getXPathValueFromDOM(xmlDOM, 'cmnPersonMst/middleName');
	 	}
	 		
	 	
	    var isStmt= getXPathValueFromDOM(xmlDOM, 'isWitStmntRecvd'); 
	    var stmtRq=document.getElementsByName('stmtReq');
	   
	   	if(isStmt=='y')
		 	{
		 	  stmtRq[0].checked="true";
		 	  isStmt="Yes";
			}
		else if(isStmt=='n')
			{
			stmtRq[1].checked="true";
			isStmt="No";
			}
		var isDoc = getXPathValueFromDOM(xmlDOM, 'isDocEvidFound'); 
		var docEvidence=document.getElementsByName('docEvidence');
		if(isDoc=='y')
		   	{
		      docEvidence[0].checked="true";
		      isDoc="Yes";
			}
		else if(isDoc=='n')
		   	{
		      docEvidence[1].checked="true";
		      isDoc="No";
			}
			    
		document.getElementById('btnAdd').style.display='none'; 	    
		document.getElementById('btnUpdate').style.display='';   	    		   		    
	   }
	}
	
 function updateRecordAlleg() 
	{
	  if (xmlHttp.readyState == 4) 
	  	{ 	

		var displayFieldArray = new Array("dtlAllegation", "firstnameWitness","period", "stmtReqHid", "docEvidenceHid");
		var rowNo=updateDataInTableAttachment(txnAdd, 'encXML', displayFieldArray);		
		
		removeRowFromTableAllegAttatchment(rowNo,'inquiryCaseTracking');	
		
		resetDataAlleg();
		document.getElementById('btnAdd').style.display='';
		document.getElementById('btnUpdate').style.display='none';    	
		}
		hideAddBtn();
	}
	
 function hideAlleg()
	{
		
		document.getElementById('attachTab').style.display='none'; 
		document.getElementById('prePrelim4').style.display='none';
		document.getElementById('prePrelim5').style.display='none';    	
		document.getElementById('prePrelim3').style.display='none'; 
		document.getElementById('txnAdd').style.display='none';  
		document.getElementById('collapseTicketAlleg').style.display='none';	
		document.getElementById('expandTicketAlleg').style.display='';
	}
	

	function prePrelimDateReadonly()
	{
		document.getElementById("enqStartDate").readOnly=true; 
		document.getElementById("appDate").readOnly=true;
		document.getElementById("eventDt").readOnly=true;
		document.getElementById("toAllegDt").readOnly=true;
		document.getElementById("frmAllegDt").readOnly=true;
		document.getElementById("period").readOnly=true;
	}
	
	function enqOffDisp()
	{
		if(document.inquiryCaseTracking.radioenqOff[0].checked==true)
		{
			document.getElementById("name1Input").style.display='none';
			document.getElementById("name2Input").style.display='none';
			document.getElementById("phone1Input").style.display='none';
			document.getElementById("phone2Input").style.display='none';
			document.getElementById("fax1Input").style.display='none';
			document.getElementById("userIdInput").style.display='';					
		}
		if(document.inquiryCaseTracking.radioenqOff[1].checked==true)
		{
			document.getElementById("name1Input").style.display='';
			document.getElementById("name2Input").style.display='';
			document.getElementById("phone1Input").style.display='';
			document.getElementById("phone2Input").style.display='';			
			document.getElementById("fax1Input").style.display='';
			document.getElementById("userIdInput").style.display='none';					
			
		}
	}
	


function makeMinorPunishReadOnly()
	{
	document.inquiryCaseTracking.futureEffMinor[0].disabled=true;
	document.inquiryCaseTracking.futureEffMinor[1].disabled=true;
	
	document.inquiryCaseTracking.minorPunish.disabled=true;
	document.inquiryCaseTracking.incMinorPunish.disabled=true;
	document.inquiryCaseTracking.yrsMinorPunish.disabled=true;
	document.inquiryCaseTracking.mntsMinorPunish.disabled=true;
	document.inquiryCaseTracking.amtInstalllMinorPunish.disabled=true;
	document.inquiryCaseTracking.noInstallMinorPunish.disabled=true;
	}
	
	function hideMinorPunish()
	{
	document.getElementById("minorPunish1").style.display='none';
	document.getElementById("minorPunish2").style.display='none';
	document.getElementById("minorPunish3").style.display='none';
	}

	function displayMinorPunish()
	{
	document.getElementById("minorPunish1").style.display='';
	document.getElementById("minorPunish2").style.display='';
	document.getElementById("minorPunish3").style.display='none';
	}
	
	function makeMajorPunishReadOnly()
	{
	document.inquiryCaseTracking.extensionShowCause[0].disabled=true;
	document.inquiryCaseTracking.extensionShowCause[1].disabled=true;
	document.inquiryCaseTracking.oralHearingSought[0].disabled=true;
	document.inquiryCaseTracking.oralHearingSought[1].disabled=true;
	
	document.inquiryCaseTracking.oralHearingDt.disabled=true;
	document.inquiryCaseTracking.toDt.disabled=true;
	document.inquiryCaseTracking.frmDt.disabled=true;
	document.inquiryCaseTracking.showCauseReplyDt.disabled=true;
	document.inquiryCaseTracking.showCauseDt.disabled=true;
	document.inquiryCaseTracking.decisionFinalDt.disabled=true;
	
	document.inquiryCaseTracking.punishMajorPunish.disabled=true;
	document.inquiryCaseTracking.amtMajorPunish.disabled=true;
	document.inquiryCaseTracking.yrsMajorPunish.disabled=true;
	document.inquiryCaseTracking.mnthsMajorPunish.disabled=true;
	document.inquiryCaseTracking.detailsPunish.disabled=true;
	document.inquiryCaseTracking.typePunishMajor.disabled=true;
	}


	function makeMajorDtReadOnly()
	{
	document.inquiryCaseTracking.oralHearingDt.readOnly=true;
	document.inquiryCaseTracking.toDt.readOnly=true;
	document.inquiryCaseTracking.frmDt.readOnly=true;
	document.inquiryCaseTracking.showCauseReplyDt.readOnly=true;
	document.inquiryCaseTracking.showCauseDt.readOnly=true;
	document.inquiryCaseTracking.decisionFinalDt.readOnly=true;
	}
	
	
	
function hideMajorPunish()
	{
	document.getElementById("majorPunish1").style.display='none';
	document.getElementById("majorPunish2").style.display='none';
	document.getElementById("majorPunish3").style.display='none';
	}

function displayMajorPunish()
	{
	document.getElementById("majorPunish1").style.display='';
	document.getElementById("majorPunish2").style.display='';
	document.getElementById("majorPunish3").style.display='none';
	
	}
	
	
	
		function hideAllPrelim()
	{
		document.getElementById("enqTable").style.display='none';
		document.getElementById("delinqDtlTable").style.display='none';
		document.getElementById("noOfEmpTab").style.display='none';		
	}

	function prelimDateReadonly()
	{
		document.getElementById("queryVCDt").readOnly=true; 
		document.getElementById("compVCDt").readOnly=true;
		document.getElementById("adviceVCDt").readOnly=true;
		document.getElementById("referenceVCDt").readOnly=true; 
		document.getElementById("inqEntrustedDt").readOnly=true;
		document.getElementById("inqReportSubDt").readOnly=true;
		document.getElementById("decisionMadeDt").readOnly=true;
	}

	function enqOffDispPrelim()
	{
	if(document.inquiryCaseTracking.radioenqOffPrelim[0].checked==true)
		{
			document.getElementById("name1InputPrelim").style.display='none';
			document.getElementById("name2InputPrelim").style.display='none';
			document.getElementById("phone1InputPrelim").style.display='none';
			document.getElementById("phone2InputPrelim").style.display='none';
			document.getElementById("fax1InputPrelim").style.display='none';
			document.getElementById("userIdInputPrelim").style.display='';					
		}
		if(document.inquiryCaseTracking.radioenqOffPrelim[1].checked==true)
		{
			document.getElementById("name1InputPrelim").style.display='';
			document.getElementById("name2InputPrelim").style.display='';
			document.getElementById("phone1InputPrelim").style.display='';
			document.getElementById("phone2InputPrelim").style.display='';			
			document.getElementById("fax1InputPrelim").style.display='';
			document.getElementById("userIdInputPrelim").style.display='none';					
		}
	}
	
	
	
	function hideAllDept()
	{
	document.getElementById("deptTab1").style.display='none';
	document.getElementById("deptTab2").style.display='none';
	document.getElementById("deptTab3").style.display='none';
	document.getElementById("deptTab4").style.display='none';
	document.getElementById("deptTab5").style.display='none';
	document.getElementById("deptTab6").style.display='none';
	document.getElementById("deptTab7").style.display='none';
	document.getElementById("deptTab8").style.display='none';
	document.getElementById("deptTab9").style.display='none';
	document.getElementById("presenOfficerDtlLab").style.display='none';
	
	document.getElementById("enqOfficerDtl").style.display='none';
	document.getElementById("EnqOfficerUserId").style.display='none';
	document.getElementById("presenOfficerDtl").style.display='none';
	document.getElementById("presenOfficerUserId").style.display='none';
	document.getElementById("presidOfficerDtl").style.display='none';
	document.getElementById("presidOfficerUserId").style.display='none';
	document.getElementById("decision").style.display='none';
	}
	
	function makeDeptDateReadOnly()
	{
	document.getElementById("appInquiryOffDate").readOnly=true; 
	document.getElementById("appPresentOffDt").readOnly=true;
	document.getElementById("caseHandedOverDt").readOnly=true;
	document.getElementById("inquiryReportSubDt").readOnly=true; 
	document.getElementById("presOffAppDt").readOnly=true;
	
	}
	
function delinqDtls()
{
	var emp=0;
	
	var noEmp=document.inquiryCaseTracking.noOfEmpSel.value;
	if(noEmp=='dept_1')
		{
			emp=1;
		}
	else if(noEmp=='dept_2')
		{
			emp=2;
		}
	else if(noEmp=='dept_3')
		{
			emp=3;
		}
	else if(noEmp=='dept_4')
		{
			emp=4;
		}
	

	switch(emp)
		{
			case 4: 
				document.getElementById('delinq4').style.display='';
				document.getElementById('delinq1').style.display='';
				document.getElementById('delinq2').style.display='';
				document.getElementById('delinq3').style.display='';
				break;
			case 3: 
				document.getElementById('delinq4').style.display='none';	
				document.getElementById('delinq3').style.display='';
				document.getElementById('delinq1').style.display='';
				document.getElementById('delinq2').style.display='';
				break;
			case 2: 
				document.getElementById('delinq2').style.display='';
				document.getElementById('delinq1').style.display='';
				document.getElementById('delinq3').style.display='none';
				document.getElementById('delinq4').style.display='none';	
				break;
			case 1: 
				document.getElementById('delinq1').style.display='';
				document.getElementById('delinq2').style.display='none';
				document.getElementById('delinq3').style.display='none';
				document.getElementById('delinq4').style.display='none';	
				break;
		}
		
		
	if(emp==1)
		{
				document.getElementById('delinq1').style.display='';
				document.getElementById('delinq2').style.display='none';
				document.getElementById('delinq3').style.display='none';
				document.getElementById('delinq4').style.display='none';	
		}	
	else if(emp==2)
		{
				document.getElementById('delinq2').style.display='';
				document.getElementById('delinq1').style.display='';
				document.getElementById('delinq3').style.display='none';
				document.getElementById('delinq4').style.display='none';	
		}
	else if(emp==3)
		{
				document.getElementById('delinq4').style.display='none';	
				document.getElementById('delinq3').style.display='';
				document.getElementById('delinq1').style.display='';
				document.getElementById('delinq2').style.display='';
		}
	else if(emp==4)
		{
				document.getElementById('delinq4').style.display='';
				document.getElementById('delinq1').style.display='';
				document.getElementById('delinq2').style.display='';
				document.getElementById('delinq3').style.display='';
		}	
} 



 function hideAll()
	{
	document.getElementById("prePrelim1").style.display='none';
	document.getElementById("prePrelim2").style.display='none';
	document.getElementById("prePrelimAdd").style.display='none';
	document.getElementById("allegLabel").style.display='none'; 
	document.getElementById("attachTab").style.display='none'; 
	}
	
	
	
		
		
	function makeSuspReviewDtReadOnly()
	{
	document.inquiryCaseTracking.reinstatementDt.readOnly=true;
	document.inquiryCaseTracking.twoYearSusp.readOnly=true;
	document.inquiryCaseTracking.oneYearSusp.readOnly=true;
	document.inquiryCaseTracking.reviewDt.readOnly=true;
	document.inquiryCaseTracking.lastReviewDt.readOnly=true;
	document.inquiryCaseTracking.reviewDt.readOnly=true;
	}
	
	
	
function hideSuspReview()
{
	document.getElementById("suspReviewTab0").style.display='none';
	document.getElementById("suspReviewTab1").style.display='none';
}

function displaySuspReview()
{
	document.getElementById("suspReviewTab0").style.display='';
	document.getElementById("suspReviewTab1").style.display='';
}
	
	
	
	function displaySuspDtls()
	{
	document.getElementById("SuspensionTab1").style.display='';
	document.getElementById("SuspensionTab2").style.display='';
	document.getElementById("SuspensionTab3").style.display='';
	}
	
	
	
	
	function prelimInitStatus()
	{
		if(document.inquiryCaseTracking.chkBoxInitPrelim.checked==true)
		{
	    document.getElementById("prelimInitBtn").disabled=false;
	   // document.getElementById("prelimInitBtn").style.display='';
		}
		else
		{
		document.getElementById("prelimInitBtn").disabled=true;
		//document.getElementById("prelimInitBtn").style.display='none';
		}
		
	}
	
	function deptInitStatus()
	{
	if(document.inquiryCaseTracking.chkBoxInitDept.checked==true)
		{
	    document.getElementById("deptInitBtn").disabled=false;
	  //  document.getElementById("deptInitBtn").style.display='';
		}
		else
		{
		document.getElementById("deptInitBtn").disabled=true;
	//	document.getElementById("deptInitBtn").style.display='none';
		}
	}
	
	
	function suspInitStatus()
	{
	if(document.inquiryCaseTracking.chkBoxInitSusp.checked==true)
		{
	    document.getElementById("suspInitBtn").disabled=false;
	  //  document.getElementById("suspInitBtn").style.display='';
		}
		else
		{
		document.getElementById("suspInitBtn").disabled=true;
		//document.getElementById("suspInitBtn").style.display='none';
		}
	}
	
	function prosecInitStatus()
	{
	if(document.inquiryCaseTracking.chkBoxInitProsec.checked==true)
		{
	    document.getElementById("prosecInitBtn").disabled=false;
	//    document.getElementById("prosecInitBtn").style.display='';
		}
		else
		{
		document.getElementById("prosecInitBtn").disabled=true;
	//	document.getElementById("prosecInitBtn").style.display='none';
		}
	}
	
	
	function viewRecordAlleg(rowId,rowNum)
	 {
		sendAjaxRequestForEditAttachment(rowId, 'populateFormAlleg','AllegAttatchment',rowNum);
		updateRow=null;
		
		document.getElementById('dtlAllegation').readOnly = 'true';
		document.getElementById('nameWitness').readOnly = 'true';
		document.getElementById('firstnameWitness').readOnly = 'true';
		document.getElementById('midnameWitness').readOnly = 'true';
   		document.getElementById('period').readOnly = 'true';	
   		document.inquiryCaseTracking.stmtReq[0].readOnly = 'true';
   		document.inquiryCaseTracking.docEvidence[0].readOnly = 'true';
   		document.inquiryCaseTracking.stmtReq[1].readOnly = 'true';
   		document.inquiryCaseTracking.docEvidence[1].readOnly = 'true';
	 }	
	 
	function makeSuspensionDtReadOnly()
	{
	document.getElementById("suspensTodate").readOnly=true;
	document.getElementById("suspensFromdate").readOnly=true;
	document.getElementById("incdate").readOnly=true;
	document.getElementById("appdate").readOnly=true;
	}
	function hideSuspDtls()
	{
	document.getElementById("SuspensionTab1").style.display='none';
	document.getElementById("SuspensionTab2").style.display='none';
	document.getElementById("SuspensionTab3").style.display='none';
	}
	
	
	function hideProsec()
	{
	document.getElementById("prosec2").style.display='none';
	document.getElementById("prosec1").style.display='none';
	}

	function displayProsec()
	{
	document.getElementById("prosec2").style.display='';
	document.getElementById("prosec1").style.display='';
	}

	function makeProsecDtReadOnly()
	{
	document.getElementById("prosecChargeSheetDt").readOnly=true; 
	document.getElementById("prosecOrderDt").readOnly=true;
	}
	
	function  extensionStatus()
	{
	if(document.inquiryCaseTracking.extensionShowCause[0].checked==true)
		{
		
		document.getElementById("extension").style.display='';	
		}
	else if(document.inquiryCaseTracking.extensionShowCause[1].checked==true)
		{
		
		document.getElementById("extension").style.display='none';		
		}
	}
	
	function oralStatus()
	{
		if(document.inquiryCaseTracking.oralHearingSought[0].checked==true)
		{
		
		document.getElementById("oralHearing").style.display='';
		}
		else if(document.inquiryCaseTracking.oralHearingSought[1].checked==true)
		{
		
		document.getElementById("oralHearing").style.display='none';
		}
	}
	
	
	function checkDecision(form)
			{	
				var status=form.value;
				
				if(status=="y"){				
				document.getElementById('decisionDetails').style.display='';	
				document.getElementById('decisionDetails2').style.display='';	
				document.getElementById('reportDetTab').style.display='';	
				}else{				
				document.getElementById('decisionDetails').style.display='none';			
				document.getElementById('decisionDetails2').style.display='none';	
				document.getElementById('reportDetTab').style.display='none';
				} 
			}	
	 
	function displayAllDept()
	{
	document.getElementById("deptTab1").style.display='';
	document.getElementById("deptTab2").style.display='';
	document.getElementById("deptTab3").style.display='';
	document.getElementById("deptTab4").style.display='';
	document.getElementById("deptTab5").style.display='';
	document.getElementById("deptTab6").style.display='';
	document.getElementById("deptTab7").style.display='';
	document.getElementById("deptTab8").style.display='';
	document.getElementById("deptTab9").style.display='';
	document.getElementById("presenOfficerDtlLab").style.display='';
	displayAllDeptFrmDB();
	}
	
	
	
function addRecordAlleg() 
	{
	  if (xmlHttp.readyState == 4)
	  { 				
	  	
		if(document.inquiryCaseTracking.stmtReq[0].checked == true)
		{
			document.inquiryCaseTracking.stmtReqHid.value='Yes';
		}
		
		else if(document.inquiryCaseTracking.stmtReq[1].checked == true)
		{
			document.inquiryCaseTracking.stmtReqHid.value='No';
		}
		
		if(document.inquiryCaseTracking.docEvidence[0].checked == true)
		{
			document.inquiryCaseTracking.docEvidenceHid.value='Yes';
		}
		
		else if(document.inquiryCaseTracking.docEvidence[1].checked == true)
		{
			document.inquiryCaseTracking.docEvidenceHid.value='No';
		}

		var displayFieldArray = new Array("dtlAllegation", "firstnameWitness","period", "stmtReqHid", "docEvidenceHid");
	
		var rowNo= addDataInTableAttachment('txnAdd', 'encXML', displayFieldArray,   'editRecordAlleg', 'deleteAllegRecord','');				
		addRecordAllegDB();
	
		
   	    resetDataAlleg();  
   	    removeRowFromTableAllegAttatchment(rowNo,'inquiryCaseTracking');	
   	    
   	    document.getElementById("checkAlleg").value=1;		
	   }	
	}


 function deleteAllegRecord(rowId)
 {
 	deleteRecord(rowId);
 	if((rowId.substring(9, rowId.length)*1)<=1)
 		{
 		document.getElementById("checkAlleg").value=0;
 		document.getElementById('txnAdd').style.display='none';
 		}
 }
 
  function dispWFStatus()
  {
  	document.getElementById("deptStatusLabel").style.display='';
	document.getElementById("deptStatus").style.display='';
  }
  
  
  function getDateDiffDays(strDate1,strDate2)
	{
		strDate1 = strDate1.split("/"); 
		starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
		starttime = new Date(starttime.valueOf()); 

		//End date split to UK date format 
		strDate2 = strDate2.split("/"); 
		endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
		endtime = new Date(endtime.valueOf()); 
		if(endtime >= starttime) 
		{ 
			var setDay = 0; 
			var lIntPenSerYear = strDate2[2] - strDate1[2];
			var lIntPenSerMonth = strDate2[1]- strDate1[1];
			var lIntPenSerDay = strDate2[0] - strDate1[0]; 
			var lIntPenSerDay = lIntPenSerDay + 1;
			var intMonth = parseInt(strDate1[1]);

			var intday = parseInt(strDate1[0]);
			intYear = parseInt(strDate1[2]);
			while(parseInt(strDate2[2]) >= intYear)
			{ 
				if (intMonth>=13) 
				{ 
					intMonth=1;
					intYear++;
				}
				if (intMonth == 1 || intMonth == 3 || intMonth == 5 || intMonth == 7 || intMonth == 8 || intMonth == 10 || intMonth == 12) 
				{
					setDay = 31; 	
				}
				if (intMonth == 4 || intMonth == 6 || intMonth == 9 || intMonth == 11) 
				{
					setDay = 30;
				}
				if (intMonth == 2) 
				{
					if (LeapYear(intYear) == true) 
					{
						setDay = 29;
					}
					else 
					{
						setDay = 28;
					}
				}
				if(setDay!=0)
				{
					while(lIntPenSerDay > setDay)
					{
						lIntPenSerDay -= setDay;
						lIntPenSerMonth++;
						if(lIntPenSerMonth==12)
						{
							lIntPenSerMonth=0;
							lIntPenSerYear++;
						}
					}
					while(lIntPenSerDay < 0)
					{
						lIntPenSerDay = setDay + lIntPenSerDay;
						lIntPenSerMonth--;
						if(lIntPenSerMonth<=-1)
						{
							lIntPenSerMonth=12+lIntPenSerMonth;
							lIntPenSerYear--; 
						}
					}
					if(lIntPenSerMonth <=-1)
					{
						lIntPenSerMonth=12+lIntPenSerMonth;
						lIntPenSerYear--; 
					}
					if(strDate1.toString() == strDate2.toString())
						{lIntPenSerDay=1;}
					//return (lIntPenSerYear+'~'+lIntPenSerMonth+'~'+lIntPenSerDay);
					document.getElementById("period").value=lIntPenSerYear*365+lIntPenSerMonth*setDay+lIntPenSerDay;
					return(lIntPenSerYear*365+lIntPenSerMonth*setDay);
					
				}
				else 
				{
					return '0~0~0'; 
				}
				intMonth++; 
			}
		}
		else
		{
			return '0~0~0'; 
		}
	} 

	function LeapYear(year)
	{	
		if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0))
		{
			return true;
		}
		else 	
		{
			return false;		
		}
	}
	
	function chkDateInput()
	{
	if(document.getElementById("frmAllegDt").value!="")
		{
		if(document.getElementById("toAllegDt").value!="")
			{
			getDateDiffDays(document.getElementById("frmAllegDt").value,document.getElementById("toAllegDt").value);
			}
		}
	}
	
	
	function dueSuspDtDefault(val)
	{
	var lastreview=val.value;
	var dateToday=lastreview.substring(0,2);
	var mnthToday=lastreview.substring(3,5);
	var yearToday=lastreview.substring(6,10);
	var nextReview="";
	var mnth;
	var yr;
	nextReview=nextReview+dateToday+"/";
	if(mnthToday<=9)
		{
				mnth=(mnthToday*1)+3;
				yr=yearToday;
				if(mnth>9)
				{
				nextReview=nextReview+mnth+"/"+yr;
				}
				else
				{
				nextReview=nextReview+"0"+mnth+"/"+yr;
				}
				
		}
	else
		{
				mnth=((mnthToday*1)+3-12);
				yr=(yearToday*1)+1;
				if(mnth>9)
				{
				nextReview=nextReview+mnth+"/"+yr;
				}
				else
				{
				nextReview=nextReview+"0"+mnth+"/"+yr;
				}
		}
	document.getElementById("reviewDt").value=nextReview;
	
	}