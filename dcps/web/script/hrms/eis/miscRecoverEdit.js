
function calculateAmt()
{
 	var miscInst = document.miscRecover.installment.value;
	if(miscInst!=null && miscInst!='')
	{
		if(miscInst==0)
		{
			alert('Installment No must be greater than Zero');
			document.miscRecover.installment.value='';
			document.miscRecover.installment.focus();
			return false;
		}
		var totAmt=document.miscRecover.amount.value;
		var installmentNo = document.miscRecover.installment.value;
		var installmentAmount = totAmt/installmentNo;
		document.miscRecover.installmentAmt.value=installmentAmount;
	}
}

function updateActivate(activateFlag)
{
	document.miscRecover.miscActivateFlag.value=activateFlag.value;
}

function cmpDate()
{

	 var diff = compareDate(document.miscRecover.date.value,document.miscRecover.endDate.value);   

	 if(document.miscRecover.endDate.value!=null && document.miscRecover.endDate.value!='')
	 {
	 
	 	var MonthDiff=	datediff(document.miscRecover.date.value,document.miscRecover.endDate.value);
	 	if(diff < 0  || MonthDiff==-1)
  	 	{
   			alert("End Date must be greater than Start Date");
	   		document.miscRecover.endDate.value='';
   			return false;
  	 	}
	  	
  	 }

}

function chkInstallmentAmt()
{
		var totAmt = document.miscRecover.amount.value;
	    var instAmt = document.miscRecover.installmentAmt.value;
	    if(eval(instAmt) > eval(totAmt))
	    {
	    	alert('Installment amount must not greater than Total Amount');
	    	document.miscRecover.installmentAmt.value='';
	    	return false;
	    }
	    else
	    	return true;
	
}

