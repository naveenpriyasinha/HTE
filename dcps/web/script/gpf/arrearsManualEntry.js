function approveGPFArrearsCase()
{
	var url;
	var sevaarthId = document.getElementById("txtSevaarthIdForEmp").value;
	if(sevaarthId=="" || sevaarthId== null){		
		var flag=0;
		var gpfAccNo="";
		var yearId = "";
		var monthId = "";
		var amount = "";
		var instAmount = "";
		var arrChkBox = document.getElementsByName("chkReq");
		var currentRecord ="";

		for(var i=0;i<arrChkBox.length;i++)
		{
			if(arrChkBox[i].checked == true)
			{
				currentRecord = (arrChkBox[i].id.split("_"))[1];
				if (!chkEmpty(document.getElementById("cmbYear"+currentRecord), "Year")
						|| !chkEmpty(document.getElementById("cmbMonth"+currentRecord), "Month")
						|| !chkEmpty(document.getElementById("txtTotalAmount"+currentRecord), "Total Amount")
						|| !chkEmpty(document.getElementById("txtInstAmount"+currentRecord), "Installment Amount"))
				{
					return;
				}
				gpfAccNo= gpfAccNo + document.getElementById("hidGpfAccNo"+currentRecord).value + "~";
				yearId= yearId + document.getElementById("cmbYear"+currentRecord).value + "~";
				monthId= monthId + document.getElementById("cmbMonth"+currentRecord).value + "~";
				amount= amount + document.getElementById("txtTotalAmount"+currentRecord).value + "~";
				instAmount= instAmount + document.getElementById("txtInstAmount"+currentRecord).value + "~";
				flag=1;
			}
		}
		if(flag == 1){
			url = "&gpfAccNo="+gpfAccNo+"&yearId="+yearId+"&monthId="+monthId+"&amount="+amount+"&instAmount="+instAmount+"&entryType=multiple";
		}else{
			alert("Please select an entry to Approve.");
			return;
		}
	}	
	else{
		if (!chkEmpty(document.getElementById("cmbYearForEmp"), "Year")
				|| !chkEmpty(document.getElementById("cmbMonthForEmp"), "Month")
				|| !chkEmpty(document.getElementById("txtTotalAmountForEmp"), "Total Amount")
				|| !chkEmpty(document.getElementById("txtInstallmentAmountForEmp"), "Installment Amount")) 
		{
			return;
		}
		
		var PreYearId = document.getElementById("hidPreYearId").value;
		var PreMonthId = document.getElementById("hidPreMonthId").value;
		var selectedYearId = document.getElementById("cmbYearForEmp").value;
		var selectedMonthId = document.getElementById("cmbMonthForEmp").value;
		if(Number(selectedYearId)<Number(PreYearId)){
			alert("The schedule for selected month has been generated already. Please select another month.");
			return;
		}else if(Number(selectedYearId) == Number(PreYearId) && Number(PreMonthId)<=Number(PreMonthId)){
			alert("The schedule for selected month has been generated already. Please select another month.");
			return;
		}
		url = runForm(0)+"&entryType=single";
	}
	var uri="ifms.htm?actionFlag=approveGPFArrears";
	var myAjax = new Ajax.Request(uri,
					{
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getApprovedMsg(myAjax);
					},
			        onFailure: function(){ alert('Something went wrong...');} 
			          } );
}
function backToArrEntryForm(){
	self.location.href="ifms.htm?viewName=GPFArrearsEntryForm";
}
function backToOfflineEntryForm(){
	self.location.href="ifms.htm?viewName=GPFArrearsOfflineEntry";
}
function getApprovedMsg(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblApprovedFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var errors = XmlHiddenValues[0].childNodes[1].text;
	if(errors != null && errors !=""){
		errors="\n Errors For Following GPF Account Numbers: "+errors;
	}
	if (lblApprovedFlag) {
		alert('Arrear Details has been approved.'+errors);
		self.location.href="ifms.htm?viewName=GPFArrearsEntryForm";
	}
}
function downloadFile(){
	
	var totalEmployees= document.getElementById("totalCount").value;

	var flag=0;
	var sevaarthId="";
	var empName="";
	var instNo="";
	var gpfAccNo="";
	var yearId = "";
	var monthId = "";
	var amount = "";
	var instAmount = "";
	var arrChkBox = document.getElementsByName("chkReq");
	var currentRecord = "";

	for(var i=0;i<arrChkBox.length;i++)
	{
		if(arrChkBox[i].checked == true)
		{
			currentRecord = (arrChkBox[i].id.split("_"))[1];
			sevaarthId= sevaarthId + document.getElementById("hidSevaarthId"+currentRecord).value + "~";
			empName= empName + document.getElementById("hidEmpName"+currentRecord).value + "~";
			instNo= instNo + document.getElementById("hidInstNo"+currentRecord).value + "~";
			
			gpfAccNo= gpfAccNo + document.getElementById("hidGpfAccNo"+currentRecord).value + "~";
			yearId= yearId + document.getElementById("cmbYear"+currentRecord).value + "~";
			monthId= monthId + document.getElementById("cmbMonth"+currentRecord).value + "~";
			amount= amount + document.getElementById("txtTotalAmount"+currentRecord).value + "~";
			instAmount= instAmount + document.getElementById("txtInstAmount"+currentRecord).value + "~";
			flag=1;
		}
	}
	if(flag == 1){
		url = "&sevaarthId="+sevaarthId+"&empName="+empName+"&instNo="+instNo+"&gpfAccNo="+gpfAccNo+"&yearId="+yearId+"&monthId="+monthId+"&amount="+amount+"&instAmount="+instAmount;
	}else{
		alert("Please select entries to download to file.");
		return;
	}
	self.location.href = "ifms.htm?actionFlag=createOfflineArrearsFile"+url;
}

function validateSchdlMonYear(rowNo)
{
	if(Number(document.getElementById("cmbYear"+rowNo).value)!=-1 && document.getElementById("cmbMonth"+rowNo).value!=-1){
		if(Number(document.getElementById("cmbYear"+rowNo).value) < Number(document.getElementById("hidPrevYearId"+rowNo).value)){
			alert("The schedule for selected month has been generated already. Therefore you can update the arrear details");
			document.getElementById("cmbYear"+rowNo).value = -1;
			return;
		}else if(Number(document.getElementById("cmbYear"+rowNo).value) == Number(document.getElementById("hidPrevYearId"+rowNo).value) && Number(document.getElementById("cmbMonth"+rowNo).value)<=Number(document.getElementById("hidPrevMonId"+rowNo).value)){
			alert("The schedule for selected month has been generated already. Therefore you can update the arrear details");
			document.getElementById("cmbMonth"+rowNo).value = -1;
			return;
		}
	}
}