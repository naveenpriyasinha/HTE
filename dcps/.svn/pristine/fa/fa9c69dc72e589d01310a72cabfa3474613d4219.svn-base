var selectedYear="";
function window_new_update(url)
{
	var newWindow = null;
   	var height = screen.height - 150;
   	var width = screen.width;
   	var urlstring = url;
   	var urlstyle = "height="+height+",width="+width+",toolbar=no,minimize=no,status=yes,menubar=no,location=no,scrollbars=yes,top=0,left=0";
   	newWindow = window.open(urlstring, "DepositAccMst", urlstyle);
}
function displayDataForGivenYear()
{
	selectedYear = document.getElementById("cmbFinancialYear").text ;
	self.location.href='ifms.htm?actionFlag=loadSixPCArrearAmountSchedule&UserType=DDOAsst&yearId='+document.getElementById("cmbFinancialYear").value;
	document.getElementById("year").value = document.getElementById("cmbFinancialYear").text ;
}
function displayDataForGivenYearDDO()
{
	selectedYear = document.getElementById("cmbFinancialYear").text ;
	self.location.href='ifms.htm?actionFlag=loadSixPCArrearAmountScheduleDDO&UserType=DDO&yearId='+document.getElementById("cmbFinancialYear").value;
	document.getElementById("year").value = document.getElementById("cmbFinancialYear").text ;
}
function printArrearScheduleReport()
{
				var cmb=document.forms[0].chkbxFormVeri;
				var flag=0;
				var Emp_Id="";
				
				if(cmb.checked == true)
				{
					flag = 1;
					Emp_Id = cmb.value;
				}
				else
				{	
					var selectedFlag=false;
					if(cmb!=null )
					{
						if(cmb.length != null)
						{
							for(i=0;i<cmb.length;i++)
							{
								if(cmb[i].checked == true)
								{
									flag = 1;
									if(i==cmb.length-1)
									{
									Emp_Id += cmb[i].value ;
									}
									else
									{
									Emp_Id += cmb[i].value + "~";
									}
								}
							}
						}
					}
				}
				if(flag ==1)
				{
					url = "ifms.htm?actionFlag=reportService&reportCode=700012&action=generateReport&empid="+Emp_Id+"&UserType="+document.getElementById("UserType").value+"&yearId="+document.getElementById("yearId").value;
					window_new_update(url);
				}
				else
				{
					alert("Please select an employee to print report");
				}
}