function viewOuterMonthlyBill(billNo,billDate)
{
	showProgressbar();
	var url = "ifms.htm?actionFlag=viewMonthlyPensionOuterBill&billNo="+billNo+"&billDate="+billDate;
	var newWindow;
	var height = screen.height - 100;
	var width = screen.width;
	var urlstyle = "height=" + height + ",width=" + width + ",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
	newWindow = window.open(url, "MonthlyPensionOuterBill", urlstyle);
	hideProgressbar();
}
function rejectMonthlyPensionBill()
{
	var arrChkBox = document.getElementsByName("chkbxBillNo");
	var counter = 0;
	var lForMonth = "";
	var chkBoxId = "";
	var rowNum = "";
	var lMonthYear = "";
	if(arrChkBox.length > 0)
	{
		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked==true)
			{
				chkBoxId = arrChkBox[i].id;
				rowNum = chkBoxId.split("_");
				lForMonth=document.getElementById("formonth_"+rowNum[1]).value;
				lMonthYear = document.getElementById("forMonthDesc_"+rowNum[1]).innerHTML;
				counter++;
			}
		}
	}
	if(counter == 0)
	{
		alert("Please select any one record.");
	}
	else if(counter > 1)
	{
		alert("Please select only one record at a time.");
	}
	else
	{
		if(confirm("All change statements and monthly pension bills of all schemecode for "+lMonthYear+" will be rejected.\n Do you want to continue?"))
		{
			var params = "forMonth="+lForMonth;
			url = "ifms.htm?actionFlag=rejectAllChngStmntByBill";  
			var myAjax = new Ajax.Request(url,
				       {
				        method: 'post',
				        asynchronous: false,
				        parameters: params,
				        onSuccess: function(myAjax) {
									responseRejectMonthlyBill(myAjax,lMonthYear);
						},
				        onFailure: function(){ alert('Something went wrong...');} 
				          } );
		}
	}
}
function responseRejectMonthlyBill(myAjax,lMonthYear)
{
	var XMLDoc =  myAjax.responseXML.documentElement;
	if(XMLDoc != null)
	{
		var lArrStatus = XMLDoc.getElementsByTagName('STATUS');
		if(lArrStatus[0] != null)
		{
			var status  = lArrStatus[0].childNodes[0].nodeValue;
			if(status == "REJECTED")
			{
				alert("All the monthly pension bills for "+lMonthYear+" rejected successfully.");
			}
			else
			{
				alert("Some problem occurred during rejection of bills.Please try again later.");
			}
		}
		else
		{
			alert("Some problem occurred during rejection of bills.Please try again later.");
		}
	}
	else
	{
		alert("Some problem occurred during rejection of bills.Please try again later.");
	}
}