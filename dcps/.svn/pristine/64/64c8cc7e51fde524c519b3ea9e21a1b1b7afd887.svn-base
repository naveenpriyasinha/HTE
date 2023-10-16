function actionOnChallanType(){
	var challanType = document.getElementById("cmbChallanType").value;
	if(challanType == 800044){
		var gpfAccNo = document.getElementById("txtGpfAcNo").value;
		var txtName = document.getElementById("txtName").value;
		var txtGroup = document.getElementById("txtGroup").value;
		if(gpfAccNo.trim() != "") {
			self.location.href = "ifms.htm?actionFlag=loadGPFChallanEntry&txtGpfAcNo="+gpfAccNo+"&cmbChallanType="+challanType
			+"&txtName="+txtName+"&txtGroup="+txtGroup;
		}
		document.getElementById("trDisplayTab").style.display = 'inline';
	}
	else{
		document.getElementById("trDisplayTab").style.display = 'none';
	}
	if(challanType == 800045){
		document.getElementById("trAmountDept").style.display = 'inline';
	}else{
		document.getElementById("trAmountDept").style.display = 'none';
	}
	if(challanType == 800046){
		document.getElementById("trTrnId").style.display = 'inline';
		document.getElementById("trExcessPay").style.display = 'inline';
	}else{
		document.getElementById("trTrnId").style.display = 'none';
		document.getElementById("trExcessPay").style.display = 'none';
	}
}
function getEmployeeData(){
	populateNameAndGroup();
	var challanType = document.getElementById("cmbChallanType").value;
	var gpfAccNo = document.getElementById("txtGpfAcNo").value;
	if(challanType ==800044){
		
		if(gpfAccNo.trim() != "") {
			var url = runForm(0);
			self.location.href = "ifms.htm?actionFlag=loadGPFChallanEntry"+url;
		}
	}
	
}
function populateNameAndGroup(){
	var gpfAccNo = document.getElementById("txtGpfAcNo").value;
	if(gpfAccNo.trim() !="")
	{
		var uri = 'ifms.htm?actionFlag=populateEmpDataForGPFChallan';
		var url = "&gpfAccNo="+gpfAccNo;
		var myAjax = new Ajax.Request(uri,
        {
	        method: 'post',
	        asynchronous: false,
	        parameters:url,
	        onSuccess: function(myAjax) {
				getNameAndGroup(myAjax);
			},
	        onFailure: function(){ alert('Something went wrong...')} 
        } );
	}
}
function getNameAndGroup(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var name = XmlHiddenValues[0].childNodes[0].text;
	var group =  XmlHiddenValues[0].childNodes[1].text;
	var joiningDate =  XmlHiddenValues[0].childNodes[2].text;
	var superAnnDate =  XmlHiddenValues[0].childNodes[3].text;

	document.getElementById('txtGroup').value = group;
	document.getElementById('txtName').value = name;
	if(name == ""){
		alert("Invalid GPF Account Number!");
		document.getElementById('txtGpfAcNo').value = "";
	}
	document.getElementById('hidJoiningDate').value = joiningDate;
	document.getElementById('hidSuperAnnDate').value = superAnnDate;
}
function approveChallan(){
	var date = new Date();
	
	if(!chkEmpty(document.getElementById("txtChallanNo"),"Challan No") || !chkEmpty(document.getElementById("txtChallanDate"),"Challan Date") || !chkEmpty(document.getElementById("cmbChallanType"),"Challan Type") || !chkEmpty(document.getElementById("txtGpfAcNo"),"GPF Account No"))
	{
		return;
	}
	if(document.getElementById("txtName").value == "" || document.getElementById("txtGroup").value ==""){
		alert('Please enter a valid GPF Account Number');
		return;
	}
	var strJoinDate = document.getElementById("hidJoiningDate").value;
	var	day = strJoinDate.split("/")[0];
	var month = strJoinDate.split("/")[1]; 
	var year = strJoinDate.split("/")[2];
	var joinDate = new Date(Number(year)+1, month-1, day); 
	
	var strSuperAnnDate = document.getElementById("hidSuperAnnDate").value;
	day = strSuperAnnDate.split("/")[0];
	month = strSuperAnnDate.split("/")[1]; 
	year = strSuperAnnDate.split("/")[2];
	var superAnnDate=new Date(year, month-4, day);
	
	if(date>superAnnDate){
		alert("Employee is not eligible for challan after 3 months of the retirement.");
		return;
	}
	if(date<joinDate){
		alert("Employee is not eligible for challan before completion of 1 year of service.");
		return;
	}
	if(document.getElementById("cmbChallanType").value == 800044){

		if(document.getElementById("hidAdvanceReqCount").value ==0 
				|| !chkEmpty(document.getElementById("txtPayment"),"Payment")){
			return;
		}

		var payment = document.getElementById("txtPayment").value;
		var outstandingAmt = document.getElementById("hidOutstandingAmt").value;
		var installmentAmt = document.getElementById("hidInstAmt").value;
		if(Number(payment)>Number(outstandingAmt)){
			alert("Payment can not be greater than the outstanding amount");
			document.getElementById("txtPayment").value="";
			return;
		}
		var amountLeft = Number(outstandingAmt) - Number(payment);
		var n = Number(payment)/Number(installmentAmt);
		if((n % 1 != 0 && payment!=outstandingAmt) || (payment!=outstandingAmt && amountLeft<installmentAmt)){
			alert("Payment should be multiple of installment or the outstanding amount");
			document.getElementById("txtPayment").value="";
			return;
		}
		
	}
	
	if((document.getElementById("cmbChallanType").value == 800045 && !chkEmpty(document.getElementById("txtAmountDept"),"Challan Amount"))
			|| (document.getElementById("cmbChallanType").value == 800046 && (!chkEmpty(document.getElementById("txtTrnId"),"Transaction ID")
					|| !chkEmpty(document.getElementById("txtExcessPay"),"Excess Payment"))))
	{
		return;
	}
	var uri = 'ifms.htm?actionFlag=approveGPFChallan';
	var url = runForm(0);

	var myAjax = new Ajax.Request(uri,
    {
	    method: 'post',
	    asynchronous: false,
	    parameters:url,
	    onSuccess: function(myAjax) {
		getApproveChallanMsg(myAjax);
		},
	    onFailure: function(){ alert('Something went wrong...')} 
    } );
	
}
function getApproveChallanMsg(myAjax){
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lblApproveFlag = XmlHiddenValues[0].childNodes[0].text;

	if (lblApproveFlag == 'true') {
		alert('Challan has been approved');		
		self.location.reload();
	}else {
		alert("Invalid Transaction Id!");
	}
}
function clearData(){
	document.getElementById("cmbChallanType").value = "-1";
	document.getElementById("txtGpfAcNo").value = "";
	document.getElementById("txtChallanNo").value = "";
	document.getElementById("txtChallanDate").value = "";
	document.getElementById("txtPayment").value = "";
	document.getElementById("txtName").value = "";
	document.getElementById("txtGroup").value = "";
	
}
function getChallanHistory(){
	var url = "ifms.htm?actionFlag=getChallanHistoryForFinYear";
	var height = screen.height - 300;
   	var width = screen.width - 400;
   	var urlstring = url;
   	var urlstyle ="height="+height+",width="+width+",titlebar=no,directories=no,location=no,resizable=no,scrollbars=yes,status=no,titlebar=no,toolbar=no,left=150";
   	   	 
	var newWindow = null;
   	newWindow = window.open(urlstring,"ChallanHistory",urlstyle,"false");
}