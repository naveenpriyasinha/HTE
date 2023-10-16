

function getLocationCmb(){
	clearForm();
	var locCmb=document.getElementById('location');
	document.getElementById('dsgn').selectedIndex="0";
	for(var i=0;i<=locCmb.options.length;i++)
	{
		locCmb.remove(1);
	}
	document.getElementById('location').disabled="";
	addOrUpdateRecord('addLocationCmb','getLocationCmb',new Array('office','rosterType'));
}
function addLocationCmb(){
	
	if (xmlHttp.readyState == 4) 
	{
		var encXML=xmlHttp.responseText;
		
		if(encXML!="error")
		{
			var locList=encXML.split("$");
			for (var i=0; i < locList.length;++i){
				var keyValPair=locList[i].split("/");
				addOption(document.frm1.location, keyValPair[0], keyValPair[1]);
			}
			
		}else
		{
			var locCmb=document.getElementById('location');
	
			for(var i=0;i<=locCmb.options.length;i++)
			{
				locCmb.remove(1);
			}
			document.getElementById('location').disabled="true";
		}	
		
		
	}
}
function clearForm(){
	document.getElementById('errorId').innerHTML="";
	document.getElementById('SubmitBt').disabled="";
	document.getElementById('scCat').value="";
	document.getElementById('scCat').readonly="";
	document.getElementById('stCat').value="";
	document.getElementById('stCat').readonly="";



	document.getElementById('reqId').value="";
	document.getElementById('noOfVac').value="";
	document.getElementById('vac').value="";
}
function checkForApprove()
{
	
	addOrUpdateRecordForRoster('addRecord','checkForApprove',new Array('location','office','dsgn','rosterType','subdsgn'));
}
function getDBData()
{	
	clearForm();
	addOrUpdateRecordForRoster('generatingSubCatCmb','getsubDesignation',new Array('dsgn'));
	
}
function generatingSubCatCmb()
{
	
	if (xmlHttp.readyState == 4) 
	{
		var encXML=xmlHttp.responseText;
		if(encXML=="error")
		{
			addOrUpdateRecordForRoster('addRecord','getCatData',new Array('location','office','dsgn','rosterType'));
			var sublocCmb=document.getElementById('subdsgn');
			var cmbSize=sublocCmb.options.length;
			for(var i=0;i<=cmbSize;i++)
			{
				sublocCmb.remove(1);
			}		
			document.getElementById('cmbSubDsgnCol').style.display="none";
			document.getElementById('lblSubDsgn').style.display="none";
			
			
		}else
		{
			document.getElementById('cmbSubDsgnCol').style.display="";
			document.getElementById('lblSubDsgn').style.display="";
			var sublocList=encXML.split("$");
			for (var i=0; i < sublocList.length;++i){
				var keyValPair=sublocList[i].split("/");
				addOption(document.frm1.subdsgn, keyValPair[0], keyValPair[1]);
			}
		}
		
	}	
}
function addOrUpdateRecordForRoster(methodName, actionFlag, fieldArray) 
	{	
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		  alert ("Your browser does not support AJAX!");
		  return;
		} 
		showProgressbar("Please Wait...");
		
		var reqBody = getRequestBody(fieldArray);	
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		
		xmlHttp.onreadystatechange=function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				hideProgressbar();
			}	
		}
	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
	}	

function addRecord()
{
	if (xmlHttp.readyState == 4) 
	{
		var encXML=xmlHttp.responseText;
		if(encXML=="error")
		{
			
		}
		else
		{
			var paraArray=encXML.split("/");
			document.getElementById('scCat').value=paraArray[0];
			document.getElementById('scCat').readonly="true";
			document.getElementById('stCat').value=paraArray[1];
			document.getElementById('stCat').readonly="true";
			document.getElementById('reqId').value=paraArray[4];
			document.getElementById('noOfVac').value=paraArray[5];
			document.getElementById('flagOfupdation').value=paraArray[6]; 
			if(paraArray[6]!="true")
			{
				document.getElementById('errorId').innerHTML="Roster Is Not Approved";
				document.getElementById('SubmitBt').disabled="true";
			}
		}
		
	}
}
function addOption(selectbox,text,value )
{
	var optn = document.createElement("OPTION");
	optn.text = text;
	optn.value = value;
	selectbox.options.add(optn);
}

function submitDtls()
{
	if(!formValidate())
	{
		return;
	}
	var urlstyle="hdiits.htm?actionFlag=promotionAllocation";
	document.frm1.action=urlstyle;
	document.frm1.submit();
}
function closeWindows()
{
	var urlstyle="hdiits.htm?actionFlag=getHomePage";
	document.frm1.action=urlstyle;
	document.frm1.submit();
}