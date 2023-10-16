
function validateNewSubsAmount()
{
	
	var salary = document.getElementById("hidSalary").value;
	var minAmount = Number(salary)*(0.06);
	var newSubsAmount = document.getElementById("txtNewSubscription").value;
	if(newSubsAmount != "" && newSubsAmount !=null){
		if(Number(newSubsAmount)<Number(minAmount) || Number(newSubsAmount) >Number(salary))
		{
			alert("Amount must be greater than 6% of emoluments and not more than total emoluments  ");
			document.getElementById("txtNewSubscription").value = "";
		}
	}
}
function chckMonth()
{
	var month = document.getElementById("cmblstEffectFromMonth").value;
	var todayMonth = document.getElementById("hidLastScheduleMonth").value;
	
	//var year  = document.getElementById("hidLastScheduleMonth").value;
	if(todayMonth !="" && todayMonth != "0"){
		if(Number(todayMonth)<4 )
	    {
			if(Number(month) >=4 || Number(todayMonth)>=Number(month))
			{
	    		alert('The selected month schedule is already generated.Please select another month');
	    		document.getElementById("cmblstEffectFromMonth").value=-1;
	    		document.getElementById("cmblstEffectFromMonth").focus();
	        	return false;
			}
	    }
		else{
			if(Number(month)<= Number(todayMonth) && Number(month)>3)
			{
				alert('The selected month schedule is already generated.Please select another month');
	    		document.getElementById("cmblstEffectFromMonth").value=-1;
	    		document.getElementById("cmblstEffectFromMonth").focus();
	        	return false;
			}
		}
	}
	return true;
}
