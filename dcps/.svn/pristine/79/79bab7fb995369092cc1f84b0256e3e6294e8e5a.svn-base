





var totalLength=0;
	var srId;
	var empArray=new Array();
	var cntSC=0,cntST=0,cntSEBC=0,cntPH=0;
	var counterRoster=0;
	


function submitDtls()
{
	
		var urlstyle="hrms.htm?actionFlag=approveRoster";
	document.Roster.action=urlstyle;
	document.Roster.submit();
}	

function closewindow()
	{
		
		var urlstyle="hrms.htm?actionFlag=RosterSubmit";
		document.Roster.action=urlstyle;
		document.Roster.submit();
		alert('submitted............ended');
	}


	

function empSearch(from)
{
	
	for(var i=0; i<from.length; i++)
	{
		empArray[i] = from[i].split("~"); 
	}
	var single = empArray[0];
	document.getElementById(document.getElementById('empSearchName').value).value = single[1];
	document.getElementById(document.getElementById('deptname').value).innerHTML = single[13];
	document.getElementById(document.getElementById('doj').value).innerHTML = single[12];
	setCountForSurplus();
}
function setCountForSurplus()
{
	
		if(document.getElementById('Categorysel'+srId).value == "SC")
		{
			cntSC--;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		else if(document.getElementById('Categorysel'+srId).value == "SEBC")
		{
			cntSEBC--;
			document.getElementById('sebc'+srId).innerHTML = cntSEBC;
		}
		else if(document.getElementById('Categorysel'+srId ).value == "ST")
		{
			cntST--;		
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		else if(document.getElementById('Categorysel'+srId).value == "PH")
		{
			cntPH--;			
			document.getElementById('ph'+srId).innerHTML = cntPH;
		}
		
		if(document.getElementById('lblDept'+srId).innerHTML == "SC")
		{
			cntSC++;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		else if(document.getElementById('lblDept'+srId).innerHTML == "SEBC")
		{
			cntSEBC++;
			document.getElementById('sebc'+srId).innerHTML = cntSEBC;
		}
		else if(document.getElementById('lblDept'+srId).innerHTML == "ST")
		{
			cntST++;
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		else if(document.getElementById('lblDept'+srId).innerHTML == "PH")
		{
			cntPH++;
			document.getElementById('ph'+srId).innerHTML = cntPH;
		}
		// For the last row
			document.getElementById('scsur11').innerHTML = cntSC;
		    document.getElementById('stsur21').innerHTML = cntST;
			document.getElementById('sebcsur31').innerHTML = cntSEBC;
			document.getElementById('phsur41').innerHTML = cntPH;
}

function disableTextArea(){
			alert('Inside the disable methhod');
			document.getElementById('Approve').disabled=true;
			document.getElementById('remarksTxt').readOnly=false;
			}
			
			function rejectRoster(){
			alert('in a submit methoe');
				alert(document.getElementById('vacancyNo').value);
				var urlstyle="hrms.htm?actionFlag=rejectRoster";
				document.Roster.action=urlstyle;
				document.Roster.submit();
			}
			function allotApplicant(){
				alert('in a submit methoe');
				alert(document.getElementById('vacancyNo').value);
				var urlstyle="hrms.htm?actionFlag=fillAllotment";
				document.Roster.action=urlstyle;
				document.Roster.submit();
			}
