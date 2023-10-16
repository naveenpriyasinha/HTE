/* Cmn_Lookup_Name Data */
	var addDeclareNew = 'PE_new_Add';
	var permanentAddress='PE_Permanent_Add';
	var presentAddress='PE_Present_Add';
/*For Lookup Data*/

function gotoHomePage(frmName)
{
	if(confirm('Do you want to Close This Application?')==true)
	{
		document.forms[frmName].action = "hrms.htm?actionFlag=getHomePage";
	   	document.forms[frmName].submit();
	}
}
function Age(birthDate)
{
	var splitDate=birthDate.split("/");							
	var bday=parseInt(splitDate[0]);
	var bmo=(parseInt(splitDate[1])-1);
	var byr=parseInt(splitDate[2]);
	var age;
	var now = new Date();		
	tday=now.getUTCDate();
	tmo=(now.getUTCMonth());
	tyr=(now.getUTCFullYear());
	if((tmo > bmo)||(tmo==bmo & tday>=bday)) {age=byr;}
	else  {age=byr+1}
	if(isNaN(tyr-age)==true)
	{
		return 0;
	}
	else if((tyr-age)>150 || (tyr-age)<=-1)			
	{			
		return "N.A.";
	}
	else {	return tyr-age;	}
}
function getAddressForComm(addForCommObj)
{
	var addForCommValue =addForCommObj.value;
	if(addForCommValue==addDeclareNew)
	{
		document.getElementById('addressTable').style.display='';
		document.getElementById('cmmAddTextArea').value="";			
	}
	else
	{
		document.getElementById('addressTable').style.display='none';		
		if(addForCommValue=='Select')			
		{
			document.getElementById('cmmAddTextArea').value="";
			return;
		}
		else
		{
			if(permanentAddress==addForCommValue)
			{
				document.getElementById('cmmAddTextArea').value=document.getElementById('PermanentAdd').value;
			}
			else if(presentAddress==addForCommValue)
			{
				document.getElementById('cmmAddTextArea').value=document.getElementById('PresentAdd').value;
			}
			else
			{
				document.getElementById('cmmAddTextArea').value="";				
			}
		}
	}
}