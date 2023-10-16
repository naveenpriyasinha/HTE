function addOtherDetails(actionType)
			{   		
						var empId=document.getElementById("Employee_ID_ITDetails").value;
					    if(empId==''){
					    	alert("Select Employee Information");
					    	return false;
					   	}
					    else {
					        var flag=checkAvailability();
					        
					    }    
					    if(flag!=true){
					    	alert("Selected Employee is not in Payroll ");
					        return false;
					    } 
					    
					    if(validateSpecificFormFields(new Array('FinYear','Employee_ID_ITDetails',
					    		'cmbBankName1','cmbBankName2','cmbBankName3','cmbBankName4','cmbBankName5','cmbBankName6','cmbBankName7',
								'cmbBankName8','cmbBankName9','cmbBankName10','cmbBankName11','cmbBankName12','cmbBankName13','cmbBankName14',
								'M1Date','M2Date','M3Date','M4Date','M5Date','M6Date','M7Date',
								'M8Date','M9Date','M10Date','M11Date','M12Date','M13Date','M14Date')))
						{
						var investDetailsArray = new Array('Employee_ID_ITDetails','OtherAllowance','foreignAllow','TaxPaidChallan','TaxDedArrear','challanNumber',
															'travelAllow','profTax','hbaIntrest','gpfCpf','govtInsurance','repayHba','hbaIntrestClaimed','hbaRepayClaimed',
															'cmbBankName1','cmbBankName2','cmbBankName3','cmbBankName4','cmbBankName5','cmbBankName6','cmbBankName7',
															'cmbBankName8','cmbBankName9','cmbBankName10','cmbBankName11','cmbBankName12','cmbBankName13','cmbBankName14',
															'M1Date','M2Date','M3Date','M4Date','M5Date','M6Date','M7Date',
															'M8Date','M9Date','M10Date','M11Date','M12Date','M13Date','M14Date','FinYear',
															'it1','it2','it3','it4','it5','it6','it7','it8','it9','it10','it11','it12');
						addOrUpdateRecord('addForm16Record', 'IT_AddDetails',investDetailsArray);
						}
			}
			function addForm16Record() 
			{
				if (xmlHttp.readyState == 4)
	  			{ 	
					if(document.getElementById("tblEmpITFormDtls")){
						document.getElementById("tblEmpITFormDtls").style.display = '';
					}
	  				var displayFieldArray;
	  				var displayFieldArray = new Array('Employee_Name_ITDetails','OtherAllowance','foreignAllow','TaxPaidChallan','TaxDedArrear',
	  													'travelAllow','profTax','hbaIntrest','gpfCpf','govtInsurance','repayHba','FinYear');
	  				addDataInTable('tblEmpITFormDtls','encAddForm16XML', displayFieldArray,'','deleteForm16Record');
	  				clearData();
	  			}	
   			}

function deleteForm16Record(rowId)	
{
		var result = deleteRecord(rowId);
		alert(result);
}	

function checkForm16Details()
{	
	if(noTableRowForMultipleAdd('tblEmpITFormDtls'))
	 {	
		alert(document.getElementById('jsfieldCheckForm16Label').value) ;
		expandtab("maintab",0);
		return  false;
	 }
	 else
	 return true;
}

function clearData(){
	for(var i = 1 ; i < 15 ; i++){
		document.getElementById("cmbBankName"+i).value = 'Select';
		if(i<13){
			document.getElementById("it"+i).value = '';
		}
	}
	document.ITOtherDetails.M1Date.value='';
	document.ITOtherDetails.M2Date.value='';
	document.ITOtherDetails.M3Date.value='';
	document.ITOtherDetails.M4Date.value='';
	document.ITOtherDetails.M5Date.value='';
	document.ITOtherDetails.M6Date.value='';
	document.ITOtherDetails.M7Date.value='';
	document.ITOtherDetails.M8Date.value='';
	document.ITOtherDetails.M9Date.value='';
	document.ITOtherDetails.M10Date.value='';
	document.ITOtherDetails.M11Date.value='';
	document.ITOtherDetails.M12Date.value='';
	document.ITOtherDetails.M13Date.value='';
	document.ITOtherDetails.M14Date.value='';
	
	document.ITOtherDetails.OtherAllowance.value = '';
	document.ITOtherDetails.foreignAllow.value = ''; 
	document.ITOtherDetails.TaxPaidChallan.value = '';
	document.ITOtherDetails.TaxDedArrear.value = '';
	
	document.ITOtherDetails.travelAllow.value = '';
	document.ITOtherDetails.profTax.value = ''; 
	document.ITOtherDetails.hbaIntrest.value = '';
	document.ITOtherDetails.gpfCpf.value = ''; 
	document.ITOtherDetails.govtInsurance.value = '';
	document.ITOtherDetails.repayHba.value = '';
	
	document.ITOtherDetails.FinYear.value = 'Select';
	clearEmployee('ITDetails');
}




