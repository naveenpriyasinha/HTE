function addInvestDetails(actionType)
			{   		
						var empId=document.getElementById("Employee_ID_investSearch").value;
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
					    
					    if(validateSpecificFormFields(new Array('investType','investAmount','investDate')))
						{
						var investDetailsArray = new Array('Employee_ID_investSearch','Employee_Name_investSearch','GPF_NM_investSearch','Dsgn_NM_investSearch','Police_ST_NM_investSearch','DISTRICT_NM_investSearch','investType','investAmount','isProofSubmit','investDate','policyNo','isProofApproved');
						addOrUpdateRecord('addInvestRecord', 'PR_multiAddEmpInvest',investDetailsArray);
						}
			}
function addInvestRecord() 
			{
				if(document.frmEmpInvestDtls.isProofApproved.checked==1)
					document.frmEmpInvestDtls.ProofApproved.value='Yes';
				else
					document.frmEmpInvestDtls.ProofApproved.value='No';
				
				if(document.frmEmpInvestDtls.isProofSubmit.checked==1)
					document.frmEmpInvestDtls.ProofSubmit.value='Yes';
				else
					document.frmEmpInvestDtls.ProofSubmit.value='No';
							
				    
				if (xmlHttp.readyState == 4)
	  			{ 	
	  				var displayFieldArray;
	  				var displayFieldArray = new Array('Employee_Name_investSearch','investType','investAmount','ProofSubmit','investDate','policyNo','ProofApproved');
	  				addDataInTable('tblEmpInvestDtls','encInvestXML', displayFieldArray,'','deleteInvestRecord');
	  			}	
   			}

function deleteInvestRecord(rowId)	
{
		var result = deleteRecord(rowId);
}	

function checkInvestDetails()
{	
	if(noTableRowForMultipleAdd('tblEmpInvestDtls'))
	 {	
		alert(document.getElementById('jsfieldCheckInvestLabel').value) ;
		expandtab("maintab",0);
		return  false;
	 }
	 else
	 return true;
}






