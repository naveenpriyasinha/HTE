	function getDateFromDateObj (dateObj)
	{
		var returnArray;
		var array = dateObj.split (" ");
		var date = array[0];
		var dateArray =  date.split ("-");
		var dateStr = dateArray[2]+"/"+dateArray[1] +"/"+dateArray[0]; 
		
		returnArray = dateStr; 
		
		return returnArray;
	}
	function submitInDb()
	{
		document.frmEmpProfDtls.action = "hrms.htm?actionFlag=savePreEmplDtlInDB&workFlowEnabled="+ workFlowEnabled;
		document.frmEmpProfDtls.submit();
	}

	function addOrUpdateProfessionalDtls(btnObj)
	{
		if (btnObj == 'Reset')
		{
			resetEmpPrefessionalData(); 
		}		
		else
		{
			var addPorfessionalDtlArray= new Array('txtOrgNm','drodnTypOfOrg','txtDurationYrs','txtDurationMonths','txtDurationDays');
			var statusAddProfessionalDtlValidation =  validateSpecificFormFields(addPorfessionalDtlArray);
			
			if(statusAddProfessionalDtlValidation)
			{
				/* Address Validation Starts */
				var addrName = 'preEmployerAddress';
				var addrLookUpName = 'Permanent Address'; 
				var addressArray = addressParameters(addrName, addrLookUpName);  // returns an array of address parameters												
				
				if(isAddressClosed(addrName)== false)				
				{
					statusAddProfessionalDtlValidation =  validateSpecificFormFields(addressArray);		
				}
				/* Address Validation Ends */
			}
			
			if (statusAddProfessionalDtlValidation)
			{
				if(days_between())
				{
					if (btnObj == 'Add')
					{
						var hdnProfessionId = 0;
						var hdnOrganizationId = 0;
						var txtareaRemarks = document.getElementById('txtareaRemarks').value;
							
						var addressParameter = addressParameters('preEmployerAddress','Permanent Address');
						displayFieldArray = new Array('txtOrgNm','drodnTypOfOrg','dtDtOfJoin','dtDtOfRelevng','txtDsgAtTimeOfRelvng','txtareaJobProfCarrerSkls','IsContinue','txtDurationYrs','txtDurationMonths','txtDurationDays');//change by sunil on 31/05/08
						displayFieldArray = displayFieldArray.concat(addressParameter);//concat address with array
						addOrUpdateRecord('addEmpProfessionalRecord','addEmpProfessionalDtl&hdnProfessionId='+ hdnProfessionId +'&txtareaRemarks='+txtareaRemarks +'&hdnOrganizationId='+ hdnOrganizationId, displayFieldArray);
					 }
					 else if(btnObj == 'Update')
					 {
						var hdnProfessionId = document.getElementById('hdnProfessionId').value;
						var txtareaRemarks = document.getElementById('txtareaRemarks').value;
						var hdnOrganizationId = document.getElementById('hdnOrganizationId').value;
							
						var addressParameter = addressParameters('preEmployerAddress','Permanent Address');
						displayFieldArray = new Array('txtOrgNm','drodnTypOfOrg','dtDtOfJoin','dtDtOfRelevng','txtDsgAtTimeOfRelvng','txtareaJobProfCarrerSkls','IsContinue','txtDurationYrs','txtDurationMonths','txtDurationDays');
						displayFieldArray = displayFieldArray.concat(addressParameter);
						addOrUpdateRecord('updateEmpProfessionalRecord','addEmpProfessionalDtl&hdnProfessionId='+ hdnProfessionId +'&txtareaRemarks='+txtareaRemarks +'&hdnOrganizationId='+ hdnOrganizationId, displayFieldArray);
					 }
					 getFieldGroupObj(document.getElementById("txnAddEmpProfessionalDtl"));	
				 }
			 }
	 	 }  
	}

	function addEmpProfessionalRecord()
	{
		if (xmlHttp.readyState == 4)
		{ 				
			showTotalDuration();
		   	removeSelect();
			displayFieldArray1 = new Array('txtOrgNm','drodnTypOfOrg','dtDtOfJoin','dtDtOfRelevng','txtDsgAtTimeOfRelvng','txtareaJobProfCarrerSkls','hdnIsContinue','hdnDuration');
			addDataInTable('txnAddEmpProfessionalDtl','encXMLPreEmpl',displayFieldArray1, 'editEmpProfessionalRecord','deleteEmpProfessionalRecord');
   	    	resetEmpPrefessionalData();			
	    }	
	}	   
	
	function editEmpProfessionalRecord(rowId)
	{
		sendAjaxRequestForEdit(rowId,'populateFormProfessional');
		document.getElementById('btnEmpProfDtlsAdd').style.display='none';
		document.getElementById('btnEmpProfDtlsUpdate').style.display='';
	}	   	
	
	function populateFormProfessional() 
	{				
 		 if (xmlHttp.readyState == 4) 
		 {	 	
		 	if (xmlHttp.status == 200) 
			{
			  	var decXML = xmlHttp.responseText;				  	
	  	        var xmlDOM = getDOMFromXML(decXML);

				document.getElementById('hdnProfessionId').value = getXPathValueFromDOM(xmlDOM, 'empPreemplPk');
				document.getElementById('hdnOrganizationId').value = getXPathValueFromDOM(xmlDOM, 'cmnOrganizationMst/organizationId');
				
				getFieldGroupObj(document.getElementById("countryStateTablepreEmployerAddress"));
				
				editAddress('preEmployerAddress',xmlDOM,'cmnOrganizationMst/cmnAddressMst');//to get address during populate
				
				str = getXPathValueFromDOM(xmlDOM, 'cmnOrganizationMst/organizationName');	
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtOrgNm').value = str;
						
				str = getXPathValueFromDOM(xmlDOM, 'cmnOrganizationMst/cmnLookupMst/lookupName');	
				if(str==null || str=='' || str=='null')
				{
					document.getElementById('drodnTypOfOrg').value='0';
				}	
				else
				{
					document.getElementById('drodnTypOfOrg').value=str;
				}			

				str = getXPathValueFromDOM(xmlDOM, 'dateOfJoining');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				if (str != '')
				{
					var dateArray = getDateFromDateObj(str);
					document.getElementById('dtDtOfJoin').value = dateArray;	
				}
							
				str = getXPathValueFromDOM(xmlDOM, 'dateOfReleving');						
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				if (str != '')
				{
					var dateArray = getDateFromDateObj(str);
					document.getElementById('dtDtOfRelevng').value = dateArray;	
				}
						
				str = getXPathValueFromDOM(xmlDOM, 'designation');	
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtDsgAtTimeOfRelvng').value = str;	   
		
				str = getXPathValueFromDOM(xmlDOM, 'jobProfile');	
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtareaJobProfCarrerSkls').value = str;
				
				str = getXPathValueFromDOM(xmlDOM, 'remarks');	
				if(str==null || str=='' || str=='null')
				{
					str='';
				}
				document.getElementById('txtareaRemarks').value = str;		
				
				str = getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByIsContinue/lookupName');	
				if(str==null || str=='' || str=='null')
				{
					document.getElementById('IsContinue').value='0';
				}	
				else
				{
					document.getElementById('IsContinue').value=str;
					showDurationTd();
				}	
					
	    		if(document.getElementById('IsContinue').value=='Yes_srvc')
		    	{
					str = getXPathValueFromDOM(xmlDOM, 'durationYears');	
					if(str==null || str=='' || str=='null'){str='';}
					document.getElementById('txtDurationYrs').value = str;		
					
					str = getXPathValueFromDOM(xmlDOM, 'durationMonths');	
					if(str==null || str=='' || str=='null'){str='';}
					document.getElementById('txtDurationMonths').value = str;		
					
					str = getXPathValueFromDOM(xmlDOM, 'durationDays');	
					if(str==null || str=='' || str=='null'){str='';}
					document.getElementById('txtDurationDays').value = str;		
				}
			 }		   		    
		 }	
	 }
	
	function updateEmpProfessionalRecord()
	{
	  if(xmlHttp.readyState == 4) 
	  { 	
		showTotalDuration();
	    removeSelect();
		var displayFieldArray = new Array('txtOrgNm','drodnTypOfOrg','dtDtOfJoin','dtDtOfRelevng','txtDsgAtTimeOfRelvng','txtareaJobProfCarrerSkls','hdnIsContinue','hdnDuration');
		updateDataInTable('encXMLPreEmpl', displayFieldArray);		
		resetEmpPrefessionalData(); 
     	document.getElementById('btnEmpProfDtlsAdd').style.display='';
		document.getElementById('btnEmpProfDtlsUpdate').style.display='none';
	  }
	}
	
	function resetEmpPrefessionalData()
	{
		document.getElementById('txtOrgNm').value='';
		document.getElementById('drodnTypOfOrg').value='0';
		document.getElementById('dtDtOfJoin').value='';
		document.getElementById('dtDtOfRelevng').value='';
		document.getElementById('txtDsgAtTimeOfRelvng').value='';
		document.getElementById('txtareaJobProfCarrerSkls').value='';
		document.getElementById('txtareaRemarks').value='';
		document.getElementById('txtDurationYrs').value='';
		document.getElementById('txtDurationMonths').value='';
		document.getElementById('txtDurationDays').value='';
		document.getElementById('IsContinue').value='0';
		
		showDurationTd();
		resetAddress('preEmployerAddress');
		closeAddress('preEmployerAddress'); 
		
		updateRow = null;
		document.getElementById('btnEmpProfDtlsAdd').style.display='';
		document.getElementById('btnEmpProfDtlsUpdate').style.display='none';
		if (document.getElementById('txnAddEmpProfessionalDtl') != null)
		{
			var rows = document.getElementById('txnAddEmpProfessionalDtl').rows.length;
			if (rows > 1 && document.getElementById('profNoRecords') != null)
				document.getElementById('profNoRecords').style.display='none';
		}
	}
	
	function deleteEmpProfessionalRecord(rowId)
	{
		deleteRecord(rowId);
	}
	function deleteDBEmpProfessionalRecord(rowId)
	{
		deleteDBRecord(rowId);
	}
	
	function dateComparisonForPreEmpl()
    {
   		if(document.getElementById("dtDtOfJoin").value!="" && document.getElementById("dtDtOfRelevng").value!="")
		{
			var lFrmDate=document.getElementById("dtDtOfJoin").value;							
			var lToDate=document.getElementById("dtDtOfRelevng").value;	
			
			if(compareDate(lFrmDate,lToDate) < 0 )				
			{
				alert(empPreEmploymentAlertMsgArr[0]);	
				document.getElementById('dtDtOfJoin').value='';
				document.getElementById('dtDtOfRelevng').value='';			
				document.frmEmpProfDtls.dtDtOfJoin.focus();
			}
			
		}	
	} 
	
	function closeWindow()
	{
		if(workFlowEnabled=="true")
		{
			document.frmEmpProfDtls.action = "hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
		   	document.frmEmpProfDtls.submit();
		}
		else
		{
			window.close();
		}
	}	
	
	function checkForMaxMonths(key)
	{
		var num;
		num = window.event.keyCode;
		if (eval(num)<=47 ||eval(num)>58)
		{
			return false;
		}
		else
		{
			var month=document.getElementById("txtDurationMonths").value;
			if(month!='' && !isNaN(month))
			{
				if(eval(month)==1)
				{
		 			if(eval(num)!=49 && eval(num)!=48 )
		 			{
		 				return false;
		 			}
		 			else
		 				return true;
		 		}	
		 		else 
		 		{
		 			return false;
				}
			}else return true;
		}
	}
	
	function checkForMaxDays(key)
	{
		var num;
		num = window.event.keyCode;
		if (eval(num)<=47 ||eval(num)>58)
		{
			return false;
		}
		else
		{
			var days=document.getElementById("txtDurationDays").value;
			if(days!='' && !isNaN(days))
			{
				if(eval(days)==3)
				{
		 			if(eval(num)==48)
		 			{
		 				return true;
		 			}
		 			else
		 				return false;
		 		}
		 		else if(eval(days)<3)
		 		{
		 			return true;
		 		}	
		 		else 
		 		{
		 			return false;
				}
			}else return true;
		}
	}
	
	function checkDecimalNumber(key)
	{
		var num;
		num = window.event.keyCode;
		if (eval(num)<=47 ||eval(num)>58)
			return false;
		else
		 	return true;
	}
	
    function showDurationTd()
    {
    	var lookupId=document.getElementById("IsContinue").value
    	if(lookupId=='Yes_srvc')
    	{
    		document.getElementById('durationTdLabelId').style.display=''
	   		document.getElementById("durationTdId").style.display=''
    	}
    	else
    	{
    		document.getElementById('txtDurationYrs').value='';
			document.getElementById('txtDurationMonths').value='';
			document.getElementById('txtDurationDays').value='';
    		document.getElementById('durationTdLabelId').style.display='none';
	   		document.getElementById("durationTdId").style.display='none'
    	}
    }
    
    function showTotalDuration()
    {
    	if(document.getElementById("IsContinue").value=='Yes_srvc')
	    	{
	    		var durYrs=document.getElementById("txtDurationYrs").value;
	    		var durMon=document.getElementById("txtDurationMonths").value;
	    		var durDay=document.getElementById("txtDurationDays").value;
	    		if(durYrs==''){durYrs=0;}if(durMon==''){durMon=0;}if(durDay==''){durDay=0;}
			    document.getElementById("hdnDuration").value  = durYrs+empPreEmploymentAlertMsgArr[4]+ durMon +empPreEmploymentAlertMsgArr[5]+durDay+empPreEmploymentAlertMsgArr[6];
			}
			else
				document.getElementById("hdnDuration").value  = '-';
    }
    
	function days_between()
	{
	    if(document.getElementById('IsContinue').value=='Yes_srvc')
		{	
		 	var DOJ=document.getElementById('dtDtOfJoin').value;
		 	var DOR=document.getElementById('dtDtOfRelevng').value;
		 	dojStatus=true;
		 	dorStatus=true;	
		 
		 	if(DOJ=='')
		 	{
		 		dojStatus=false;
		 		alert(empPreEmploymentAlertMsgArr[1]);
		 		return false;
		 	}
		 	if(DOR=='')
		 	{
		 		dorStatus=false;
		 		alert(empPreEmploymentAlertMsgArr[2]);
		 		return false;
		 	}	
		 		
		 	if(dojStatus==true && dorStatus==true)
		 	{
			 	var date1=new Date(dateFormate(DOJ));
			 	var date2=new Date(dateFormate(DOR));
			 	
			 	var DurYrs=document.getElementById('txtDurationYrs').value;
			 	var DurMon=document.getElementById('txtDurationMonths').value;
			 	var DurDays=document.getElementById('txtDurationDays').value;
			 	
			 	if(DurYrs=='')DurYrs=0;
			 	if(DurMon=='')DurMon=0;
			 	if(DurDays=='')DurDays=0;
			 	
			 	var duration_days=parseInt(DurYrs)*365+parseInt(DurMon)*30+parseInt(DurDays);

			    // The number of milliseconds in one day
			    var ONE_DAY = 1000 * 60 * 60 * 24;
			
			    // Convert both dates to milliseconds
			    var date1_ms = date1.getTime();
			    var date2_ms = date2.getTime();
			    // Calculate the difference in milliseconds
			    var difference_ms = Math.abs(date1_ms - date2_ms);
			    
			    var difference_days=Math.round(difference_ms/ONE_DAY);
			    if(difference_days<duration_days)
			    {
			    	alert(empPreEmploymentAlertMsgArr[3]);
			    	return false;
			    }
			}
			else return false;
		}
		return true;
	}

	function removeSelect()
	{
		if(document.getElementById('IsContinue').value=='0')
	   	{
	   		document.getElementById('hdnIsContinue').value='-';
	   	}
	   	else 
	   	{
	   		var selectedItem=document.frmEmpProfDtls.IsContinue;
			var option=selectedItem.options[selectedItem.selectedIndex].text;
	    	document.getElementById('hdnIsContinue').value=option;
	   	}
	   	var DOJ=document.getElementById('dtDtOfJoin').value;
		var DOR=document.getElementById('dtDtOfRelevng').value;
	   	var Dsgn=document.getElementById('txtDsgAtTimeOfRelvng').value;
		var jobProf=document.getElementById('txtareaJobProfCarrerSkls').value;
		var remarks=document.getElementById('txtareaRemarks').value;
	   	if(DOJ=='' || DOJ==null)
	  	{
	   		document.getElementById('dtDtOfJoin').value='-'
	  	}
	  	if(DOR=='' || DOR==null)
	   	{
	   		document.getElementById('dtDtOfRelevng').value='-'
	   	}
	   	if(Dsgn=='' || Dsgn==null)
	   	{
	   		document.getElementById('txtDsgAtTimeOfRelvng').value='-'
	   	}		
	   	if(jobProf=='' || jobProf==null)
	   	{
	   		document.getElementById('txtareaJobProfCarrerSkls').value='-'
	   	}	
	   	if(remarks=='' || remarks==null)
	   	{
	   		document.getElementById('txtareaRemarks').value='-'
	   	}	
	}
	
	function dateFormate(date)
	{
		var splitDate=date.split("/");							
		var day=splitDate[0];
		var mon=splitDate[1];
		var yrs=splitDate[2];
		var dateString=mon+'/'+day+'/'+yrs;
		return  dateString;
	}
	