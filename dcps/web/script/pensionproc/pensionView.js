



var ALERT_TITLE = "";
var ALERT_BUTTON_TEXT = "Ok";
if(document.getElementById) {
	window.alert = function(txt) {
		createCustomAlert(txt);
	}
}
function createCustomAlert(txt) {
	d = document;
	if(d.getElementById("modalContainer")) return;
	mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
	mObj.id = "modalContainer";
	mObj.style.height = d.documentElement.scrollHeight + "px";
	alertObj = mObj.appendChild(d.createElement("div"));
	alertObj.id = "alertBox";
	if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
	alertObj.style.left = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2 + "px";
	alertObj.style.visiblity="visible";
	h3 = alertObj.appendChild(d.createElement("h3"));
	h3.appendChild(d.createTextNode(ALERT_TITLE));
	msg = alertObj.appendChild(d.createElement("p"));
	//msg.appendChild(d.createTextNode(txt));
	msg.innerHTML = txt;
	btn = alertObj.appendChild(d.createElement("a"));
	btn.id = "closeBtn";
	btn.appendChild(d.createTextNode(ALERT_BUTTON_TEXT));
	btn.href = "#";
	btn.focus();
	btn.onclick = function() { removeCustomAlert();return false; }
	alertObj.style.display = "block";
}
function removeCustomAlert() {
	document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
}



function chkValidation(){

	
				if(
						chkEmpty(document.getElementById('fromMonth'),' \u0915\u094B\u0923\u0924\u094D\u092F\u093E \u092E\u0939\u093F\u0928\u094D\u092F\u093E\u092A\u093E\u0938\u0942\u0928 \u0930\u093F\u0915\u093E\u092E\u093E \u0905\u0938\u0941 \u0928\u092F\u0947')&&
						chkEmpty(document.getElementById('fromYear'),' \u0915\u094B\u0923\u0924\u094D\u092F\u093E \u092E\u0939\u093F\u0928\u094D\u092F\u093E\u092A\u093E\u0938\u0942\u0928 \u0930\u093F\u0915\u093E\u092E\u093E \u0905\u0938\u0941 \u0928\u092F\u0947')&&
						chkEmpty(document.getElementById('toMonth'),'\u0915\u094B\u0923\u0924\u094D\u092F\u093E \u092E\u0939\u093F\u0928\u094D\u092F\u093E\u092A\u0930\u094D\u092F\u0902\u0924 \u0930\u093F\u0915\u093E\u092E\u093E \u0905\u0938\u0941 \u0928\u092F\u0947')&&
						chkEmpty(document.getElementById('toYear'),'\u0915\u094B\u0923\u0924\u094D\u092F\u093E \u092E\u0939\u093F\u0928\u094D\u092F\u093E\u092A\u0930\u094D\u092F\u0902\u0924 \u0930\u093F\u0915\u093E\u092E\u093E \u0905\u0938\u0941 \u0928\u092F\u0947')&&
						chkEmpty(document.getElementById('cmbTreasuryCode'),'\u0915\u094B\u0937\u093E\u0917\u093E\u0930 \u0930\u093F\u0915\u093E\u092E\u093E \u0905\u0938\u0941 \u0928\u092F\u0947') && 
						chkEmpty(document.getElementById('cmbBankName'),'\u092C\u0901\u0915\u0947\u091A\u0947 \u0928\u093E\u0935 \u0930\u093F\u0915\u093E\u092E\u0947 \u0905\u0938\u0942 \u0928\u092F\u0947')&&
						chkEmpty(document.getElementById('cmbBranchName'),'\u092C\u0901\u0915\u0947\u091A\u0940 \u0936\u093E\u0916\u093E \u0930\u093F\u0915\u093E\u092E\u0940 \u0905\u0938\u0942 \u0928\u092F\u0947')&&
						chkEmpty(document.getElementById('pension'),'\u0928\u093F\u0935\u0943\u0924\u094D\u0924\u0940\u0935\u0947\u0924\u0928\u0927\u093E\u0930\u0915\u093E\u091A\u0947 \u0928\u093E\u0935 \u0930\u093F\u0915\u093E\u092E\u0947 \u0905\u0938\u0942 \u0928\u092F\u0947')&&
						chkEmpty(document.getElementById('account'),'\u092C\u0901\u0915 \u0916\u093E\u0924\u0947 \u0915\u094D\u0930\u092E\u093E\u0902\u0915 \u0930\u093F\u0915\u093E\u092E\u093E \u0905\u0938\u0942 \u0928\u092F\u0947')&& validate())
				
				{
		

			 chkAccount(document.getElementById('account'));
				var flag=document.getElementById("flag").value;
				if(flag=="Y")
					{
						ShowReport();
				}
	}
}
function chkAccount(ctrl)
{

	
	var acc = ctrl.value;
	var ppoNo=document.getElementById('PPONo').value;
	var bnk=document.getElementById('cmbBankName').value;
	var brnch=document.getElementById('cmbBranchName').value;
	var uri="ifms.htm?actionFlag=checkAccNo&bnkCode="+bnk+"&brnCode="+brnch+"&accNo="+acc;
	
	var myAjax = new Ajax.Request(uri,
	        {
	        	method: 'post',
	        	asynchronous: false,
	        	parameters: "&ppoNo="+ppoNo,
	        	onSuccess: function(myAjax) {
    	
				getAccNum(myAjax);
			},
	        onFailure: function(){ alert('Something went wrong...')} 
	          } );
    return myAjax;
    
}
function getAccNum(myAjax)
{
	
	var flag=null;
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('FLAG');
	
	flag=XmlHiddenValues[0].childNodes[0].nodeValue;

	if(flag=="N")
	{
		var msg="\u092C\u0901\u0915 \u0916\u093E\u0924\u0947 \u0915\u094D\u0930\u092E\u093E\u0902\u0915 \u0935\u0948\u0927 \u0928\u093E\u0939\u0940 ";
		alert(msg);
		//alert("\u092C\u0901\u0915 \u0916\u093E\u0924\u0947 \u0915\u094D\u0930\u092E\u093E\u0902\u0915 \u0935\u0948\u0927 \u0928\u093E\u0939\u0940 ");
		document.getElementById("account").value="";
		document.getElementById("account").focus();
		hideProgressbar();
		return true;
	}
	
	document.getElementById("flag").value=flag;
	hideProgressbar();
}


function chkEmpty(ctrl,msg)
{
	var str = ctrl.value;
	if(str=="" || str == "-1"||str=="--")
	{
		alert(msg);
		ctrl.focus();		
		return false;
	}		
	else
		return true;
}

function ToYear(){
	
	
	var select1 = document.getElementById("fromYear");
	var select2 = document.getElementById("toYear");

	    while (select2.firstChild) {
	        select2.removeChild(select2.firstChild);

	 	   }

	    var op = document.createElement("option");
        op.value = "-1";
        op.innerHTML = "Select";
        select2.appendChild(op);

        
        if (select1.selectedIndex != 0) {
	    
		        for (var i = select1.selectedIndex; i < select1.options.length; i++) {
			        var o = document.createElement("option");
			        o.value = select1.options[i].value;
			        o.innerHTML = select1.options[i].text;
			        select2.appendChild(o);
			        
			    }
	    }
	   

}


function getLastDateOfMonth(Year,Month){
	 return new Date(Year, Month, 0).getDate();


	}



function validate(){

var flag=true;
	var fmnth = document.getElementById("fromMonth");
	var Fmonth=fmnth.options[fmnth.selectedIndex].value;
	var tmnth = document.getElementById("toMonth");
	var Tmonth=tmnth.options[tmnth.selectedIndex].value;
	var fyr = document.getElementById("fromYear");
	var Fyear=fyr.options[fyr.selectedIndex].value;
	var tyr = document.getElementById("toYear");
	var Tyear=tyr.options[tyr.selectedIndex].value;
	
	if(Fyear==Tyear){
	
		if(Fmonth > Tmonth){
			
			alert("\u092A\u0930\u094D\u092F\u0902\u0924\u091A\u093E \u092E\u0939\u093F\u0928\u093E \u092A\u093E\u0938\u0942\u0928\u091A\u094D\u092F\u093E  \u092E\u0939\u093F\u0928\u094D\u092F\u093E\u092A\u0947\u0915\u094D\u0937\u093E \u092E\u094B\u0920\u093E \u0905\u0938\u093E\u0935\u093E ");
			document.getElementById("toMonth").value=-1;
			flag= false;
		}
		else {
			flag= true; }
	}
	var date=getLastDateOfMonth(Tyear,Tmonth);

	var fdate="01/"+Fmonth+"/"+Fyear;
	document.getElementById("fromdate").value=fdate;
	var tdate=date+"/"+Tmonth+"/"+Tyear;
	document.getElementById("todate").value=tdate;
	return flag;
}
function ShowReport()
{
	
	var tcode = document.getElementById("cmbTreasuryCode");
	var bcode=document.getElementById("cmbBankName");
	var brcode=document.getElementById("cmbBranchName");
	var treasuryCode=document.getElementById("cmbTreasuryCode").value;
	var bankCode=bcode.value;
	var branchCode=brcode.value;
	var treName=tcode.options[tcode.selectedIndex].text;
	var bnkCode=bcode.options[bcode.selectedIndex].text;
	var brnCode=brcode.options[brcode.selectedIndex].text;
	var From=document.getElementById("fromdate").value;
	var To=document.getElementById("todate").value;
	var bank=document.getElementById("cmbBankName").value;
	var branch=document.getElementById("cmbBranchName").value;
	var name=document.getElementById("pension").value;
	var ppoNo=document.getElementById("PPONo").value;
	var account=document.getElementById("account").value;
	
	
	
	document.PensionerView.action = "ifms.htm?actionFlag=reportService&reportCode=365502&action=generateReport&FromParaPage=TRUE&treasuryCode="+treasuryCode+"&bankCode="+bankCode+"&branchCode="+branchCode+"&treCode="+treName+"&brnCode="+brnCode+"&bnkCode="+bnkCode+"&accountNo="+account+"&PPONo="+ppoNo+"&PensionerName="+name+"&From="+From+"&To="+To;

	document.PensionerView.submit();

	showProgressbar("Please wait...");
	
}

















/*
function getBillAfterValidation(){
	
	if(chkEmpty(document.getElementById('cmbTreasuryCode'),'Treasury Name') && 
			chkEmpty(document.getElementById('cmbBankName'),'Bank Name')&& 
			chkEmpty(document.getElementById('cmbBranchName'),'Branch Name') ){
		
		getBillforPension();

	}
}

function getBillforPension()
{
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var bankName = document.getElementById("cmbBankName").value;
	
	var url =  "ifms.htm?actionFlag=getBillForPensioner &bankCode="+bankName+"&treasuryCode="+treasuryCode;
	
	//alert('url-->'+url);
	self.location.href = url ;
	
}
*/