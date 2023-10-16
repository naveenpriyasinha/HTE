
	function checkNumberForOnlyOneDot(fieldValue)
	{
		var num;
		num = window.event.keyCode;
		if ((eval(num)<46||eval(num)==47)||eval(num)>58)
		{
			return false;
		}	
		else
		{
		 	if (eval(num)==46)
		 	{
			 	var dotCount=0;
			 	var length = fieldValue.length;
			 	if (length == 0)
			 		return false;	
			 	for(var i=0;i<length;i++)
				{
					var keyId = fieldValue.charCodeAt(i);		
					if(keyId==46) 
					{
						dotCount=parseInt(dotCount)+1;
					}
				}
				if(dotCount>=1)
				{
					return false;
				}
			}
			return true;
		}
	}
	
	function submitInDb()
	{
			document.ServiceExaminationDtls.action = "hrms.htm?actionFlag=saveSrvcExamDtlInDB";
			document.ServiceExaminationDtls.submit();
	}
	
	function addOrUpdateExamDtls(btnObj)
	{
		if (btnObj == 'Reset')
		{
			resetEmpExamData(); 
		}
		else
		{		
			var addExamDtlArray= new Array('drodnQualifngExm','drodnPreServc','drodnYearFPass');
			var statusAddExamDtlValidation =  validateSpecificFormFields(addExamDtlArray);
			if (btnObj == 'Add')
			{
				if (statusAddExamDtlValidation)
				{
					var hdnExamId = 0;
					var ExamDtls = "ExamDtls";   
					addOrUpdateRecord('addEmpExamRecord','addEmpServcExamDtl&hdnExamId='+ hdnExamId , new Array('drodnQualifngExm','drodnPreServc','drodnMonthOfPass','drodnYearFPass','txtMarksObt','txtMarksOutOf','drodnClasDivn','drodnResult'));
			    }
				else
				{
					return false;
				}
			}
			else if(btnObj == 'Update')
			{	
				if (statusAddExamDtlValidation)
				{
					var hdnExamId = document.getElementById('hdnExamId').value;
					var ExamDtls = "ExamDtls";   
	    			addOrUpdateRecord('updateEmpExamRecord','addEmpServcExamDtl&hdnExamId='+ hdnExamId , new Array('drodnQualifngExm','drodnPreServc','drodnMonthOfPass','drodnYearFPass','txtMarksObt','txtMarksOutOf','drodnClasDivn','drodnResult'));
	    		}
	    		else
				{
					return false;
				}	
		    }	
		    getFieldGroupObj(document.getElementById("txnAddEmpServcExamDtl"));	
	    }
	}
	
    function addEmpExamRecord()
	{
		if (xmlHttp.readyState == 4)
		{ 				
			displayFieldArray1 = new Array('drodnQualifngExm','drodnPreServc','drodnMonthOfPass','drodnYearFPass','txtMarksObt','txtMarksOutOf','drodnClasDivn','drodnResult');
			eprofileMyCmbFieldValue(displayFieldArray1);
			addDataInTable('txnAddEmpServcExamDtl','encXMLExm',displayFieldArrayInTable, 'editEmpExamRecord','deleteEmpExamRecord');
   	    	resetEmpExamData();			
	    }	
	}
		   
	function updateEmpExamRecord() 
	{
	  if (xmlHttp.readyState == 4) 
	  { 	
		var displayFieldArray = new Array('drodnQualifngExm','drodnPreServc','drodnMonthOfPass','drodnYearFPass','txtMarksObt','txtMarksOutOf','drodnClasDivn','drodnResult')
		eprofileMyCmbFieldValue(displayFieldArray);
		updateDataInTable('encXMLExm', displayFieldArrayInTable);		
		resetEmpExamData(); 
     	document.getElementById('btnServExamDtlAdd').style.display='';
		document.getElementById('btnServExamDtlUpdate').style.display='none';
	  }
	}
	
	function deleteEmpExamRecord(rowId)
	{
		deleteRecord(rowId);
	}
	
	function deleteDBEmpExamRecord(rowId)
	{
		deleteDBRecord(rowId);
	}
	
	function editEmpExamRecord(rowId) 
	{
		sendAjaxRequestForEdit(rowId,'populateFormExam');
		document.getElementById('btnServExamDtlAdd').style.display='none';
		document.getElementById('btnServExamDtlUpdate').style.display='';
	}	   	
	
	function populateFormExam() 
	{				
 		 if (xmlHttp.readyState == 4) 
		 {	 	
		 	if (xmlHttp.status == 200) 
			{
			  	var decXML = xmlHttp.responseText;				  	
	  	        var xmlDOM = getDOMFromXML(decXML);
				document.getElementById('hdnExamId').value = getXPathValueFromDOM(xmlDOM, 'empSrvcexamDtlsId');
				
				str = getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByExamLookupId/lookupName');	
						if(str==null || str=='' || str=='null')
						{
						document.getElementById('drodnQualifngExm').value='Select';
						}	
						else
						{
						document.getElementById('drodnQualifngExm').value=str;
						}	
						
						
				str = getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByPreserviceLookupId/lookupName');	
						if(str==null || str=='' || str=='null')
						{
						document.getElementById('drodnPreServc').value='0';
						}	
						else
						{
						document.getElementById('drodnPreServc').value=str;
						}	
						
				str = getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByPassingMonthLookupId/lookupName');	
						if(str==null || str=='' || str=='null')
						{
						document.getElementById('drodnMonthOfPass').value='0';
						}	
						else
						{
						document.getElementById('drodnMonthOfPass').value=str;
						}	
						
				str = getXPathValueFromDOM(xmlDOM, 'passingYear');	
						if(str==null || str=='' || str=='null')
						{
						document.getElementById('drodnYearFPass').value='0';
						}	
						else
						{
						document.getElementById('drodnYearFPass').value=str;
						}	
						
				str = getXPathValueFromDOM(xmlDOM, 'marksObtainted');	
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						document.getElementById('txtMarksObt').value = str;	   
								
				str = getXPathValueFromDOM(xmlDOM, 'marksOutOf');	
						if(str==null || str=='' || str=='null')
						{
						str='';
						}
						document.getElementById('txtMarksOutOf').value = str;	   
												
						
				str = getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByClassDivLookupId/lookupName');	
						if(str==null || str=='' || str=='null')
						{
						document.getElementById('drodnClasDivn').value='0';
						}	
						else
						{
						document.getElementById('drodnClasDivn').value=str;
						}	
						
				str = getXPathValueFromDOM(xmlDOM, 'cmnLookupMstByResultLookupId/lookupName');	
						if(str==null || str=='' || str=='null')
						{
						document.getElementById('drodnResult').value='0';
						}	
						else
						{
						document.getElementById('drodnResult').value=str;
						}			
			 }		   		    
		 }	
	 }
	

	function resetEmpExamData()
	{
	    document.getElementById('drodnQualifngExm').value='0';
		document.getElementById('drodnPreServc').value='0';
		document.getElementById('drodnMonthOfPass').value='0';
		document.getElementById('drodnYearFPass').value='0';
		document.getElementById('txtMarksObt').value='';
		document.getElementById('txtMarksOutOf').value='';
		document.getElementById('drodnClasDivn').value='0';
		document.getElementById('drodnResult').value='Pass';
		
		updateRow=null;
		
		document.getElementById('btnServExamDtlAdd').style.display='';
		document.getElementById('btnServExamDtlUpdate').style.display='none';
	}	
	
	function marksComparison()
	{
		if(document.getElementById("txtMarksObt").value!="" && document.getElementById("txtMarksOutOf").value!="")
		{
			if(document.getElementById("txtMarksObt").value>0)
			{
				var marksOdt=parseFloat(document.getElementById("txtMarksObt").value);
            	var marksOutOf=parseFloat(document.getElementById("txtMarksOutOf").value);
            	var diff=marksOutOf-marksOdt;
				if(diff<0)
				{
     				alert(empServiceExamAlertMsgArr[0]);
					document.getElementById('txtMarksObt').value='';
					document.getElementById('txtMarksOutOf').value='';
					document.ServiceExaminationDtls.txtMarksObt.focus();
				}
			}
			
		}	
	}
	
