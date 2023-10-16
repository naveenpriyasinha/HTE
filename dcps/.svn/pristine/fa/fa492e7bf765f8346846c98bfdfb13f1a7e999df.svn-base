function countAge(birthDate,componentName,InnerORValue,demisedate,birthDateComp,demiseDateComp,formName,aliveOrDead)
{
	var returnValue;
	var aliveOrDeadFlag = '';
	
	if(birthDate=='' && birthDateComp != '' && birthDateComp != null && birthDateComp != 'null' && birthDateComp != undefined)
		birthDate=eval("document." + formName + "." + birthDateComp + ".value");
		
	if(demisedate=='' && demiseDateComp != '' && demiseDateComp != null && demiseDateComp != 'null' && demiseDateComp != undefined)
		demisedate=eval("document." + formName + "." + demiseDateComp + ".value");
	
	if(aliveOrDead != undefined && aliveOrDead != 'undefined' && aliveOrDead != '')
		aliveOrDeadFlag = document.getElementById(aliveOrDead).value;

	if(aliveOrDeadFlag=='fm_Dead')
	{	
		if(demisedate!='' && birthDate!='')
		{			
			var age=0; 
			demisedate=setAgeDateFormat(demisedate);
			var splitDate=demisedate.split("/");
			var bday_demise=parseInt(splitDate[0]);
			var bmo_demise=(parseInt(splitDate[1])-1);
			var byr_demise=parseInt(splitDate[2]);
			
			birthDate=setAgeDateFormat(birthDate);
			var splitDate=birthDate.split("/");
			var bday_dobValue=parseInt(splitDate[0]);
			var bmo_dobValue=(parseInt(splitDate[1])-1);
			var byr_dobValue=parseInt(splitDate[2]);
	
			if((bmo_demise > bmo_dobValue)||(bmo_demise==bmo_dobValue & bday_demise>=bday_dobValue)) 
			{
				age=byr_dobValue;
			}				
			else
			{
				age=byr_dobValue+1;
			}
	
			if(isNaN(byr_demise-age)==true){}
			else if((byr_demise-age)>150 || (byr_demise-age)<=-1)			
			{	
				returnValue='';
			}
			else 
			{
				returnValue=byr_demise-age;
			}
		}
		else
		{returnValue='';}			
	}
	else
	{
		if(birthDate!='')
		{
			birthDate=setAgeDateFormat(birthDate);
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
			if(isNaN(tyr-age)==true){}
			else if((tyr-age)>150 || (tyr-age)<=-1)			
			{	
				returnValue='';
			}
			else 
			{
				returnValue=tyr-age;
			}
		}
		else
		{returnValue='';}
	}
	
	if(InnerORValue=='value')
	{
		document.getElementById(componentName).value=returnValue;
	}
	else if(InnerORValue=='innerHTML')
	{	
		document.getElementById(componentName).innerHTML=returnValue;
	}
	
	return returnValue;
}

function setAgeDateFormat(paramDate)
{
	var v= paramDate;
	if(paramDate.indexOf("-")!=-1)
	{				
		var splitDate=paramDate.split("-");				
		var byr=splitDate[0];
		var bmo=splitDate[1];
		var bday=splitDate[2];				
		v= bday+'/'+bmo+'/'+byr;
	}
	return v;
}

function compaireWithDOB(yearComboValue,empDOB)//Added by Sunil and Date Format like 'DD/MM/YYYY' OR DD-MM-YYYY
{
	var flag=false;
	if(empDOB!='' || empDOB!=null)
	{
		if(empDOB.indexOf("/")!=-1)
		{				
			var splitDate=empDOB.split("/");				
			var byear=splitDate[2];
		}
		else if (empDOB.indexOf("-")!=-1)
		{				
			var splitDate=empDOB.split("-");				
			var byear=splitDate[2];
		}
		else
		{
			alert("Please enter Date of Birth in DD/MM/YYYY OR DD-MM-YYYY format");
			return;
		}
		
		if(byear!=null && byear!='' && yearComboValue!=null && yearComboValue!='')
		{
			if(byear<yearComboValue)
			{
				flag=true;
			}
		}
	}
	return flag;
}

function sendAjaxRequestHRMS(methodName, actionFlag, fieldArray,progressBarFlag, methodType) 
{	
	hrmsXmlHttp=GetXmlHttpObject();
	if (hrmsXmlHttp==null) {
	  alert ("Your browser does not support AJAX!");
	  return;
	} 
	
	if(progressBarFlag != false)
	{
		showProgressbar("Please Wait...");	
	}
			
	var reqBody = getRequestBody(fieldArray);	
	var url='actionFlag=' + actionFlag + '&' + reqBody;
	methodName = methodName + "()";
	
	hrmsXmlHttp.onreadystatechange=function() {
		if(hrmsXmlHttp.readyState == 4) {
			eval(methodName);
			if(progressBarFlag != false)
			{
				hideProgressbar();
			}
		}	
	}
	
	if (methodType == "POST")
	{
		hrmsXmlHttp.open('POST', 'hdiits.htm', true);
		hrmsXmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		hrmsXmlHttp.setRequestHeader("Content-length", url.length);
		hrmsXmlHttp.setRequestHeader("Connection", "close");
		hrmsXmlHttp.send(url);
	}
	else
	{
		xmlHttp.open("POST",encodeURI(url),true);
		xmlHttp.send(null);
	}
}
