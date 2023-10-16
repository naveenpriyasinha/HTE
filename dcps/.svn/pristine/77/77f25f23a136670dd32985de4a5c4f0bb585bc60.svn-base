
	//gloabal Variable;
	var totalLength=0;
	var srId=0;
	var empArray=new Array();
	var cntSC=0,cntST=0,cntSEBC=0,cntPH=0;
	var counter=0;
	var transferArray=new Array();
	var transferCnt=0;
	var proArry=new Array();
	var proCnt=0;

function submitDtls()
{
	
	
	var urlstyle="hrms.htm?actionFlag=submitAllocationDlts";
	document.Roster.action=urlstyle;
	document.Roster.submit();
}	

function closewindow()
	{
		
		var urlstyle="hrms.htm?actionFlag=RosterSubmit";
		document.Roster.action=urlstyle;
		document.Roster.submit();
		
	}
	
//ToolTip Implementation



function xstooltip_hide(maintooltipId,id)
{
//Hiding the tooltip onmouseout
    it = document.getElementById(maintooltipId); 
    it.style.display = 'none';
}



		function trimAll(sString) 
		{ 
			var ss=sString.trim();
			return ss; 
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
		document.getElementById(document.getElementById('hidcatId').value).value=single[14];
		document.getElementById(document.getElementById('doj').value).value= single[12];
		
	document.getElementById(document.getElementById('hidUserId').value).value= single[6].trim();
	setCountForSurplus();
}
function enableSearch(form)
{
	
	var SrNo=form.id;
	var rowNo=SrNo.substring(11,SrNo.length);
	var search="Search"+rowNo;
	document.getElementById(search).disabled="";
	var name="empName"+rowNo;
	var boxVal=document.getElementById(name).value;
	
	var len=boxVal.length;
	
	if(len!=0)
	{
		cntSC=0;
		cntST=0;
		cntSEBC=0;
		cntPH=0;
		for(var j=rowNo;j<=counter;j++)
		{
			var scVal='sc'+j;
			var stVal='st'+j;
			var sebcVal='sebc'+j
			var phVal='ph'+j;
			document.getElementById(scVal).innerHTML="";
			document.getElementById(stVal).innerHTML="";
			document.getElementById(sebcVal).innerHTML="";
			document.getElementById(phVal).innerHTML="";
			
			document.getElementById('scsur').innerHTML ="";
		    document.getElementById('stsur').innerHTML = "";
			document.getElementById('sebcsur').innerHTML = "";
			document.getElementById('phsur').innerHTML = "";
		}
		
		for(var i=rowNo;i<=counter;i++)
		{
			srId=i;
			setCountForSurplus();
		}
	}
}
function setCountForSurplus()
{
		if((document.getElementById('plannedCatId'+srId).value == "302017") ||(document.getElementById('plannedCatId'+srId).value == "302022"))
		{
			cntSC--;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		else if((document.getElementById('plannedCatId'+srId).value == "302019")||(document.getElementById('plannedCatId'+srId).value == "302024"))
		{
			cntSEBC--;
			document.getElementById('sebc'+srId).innerHTML = cntSEBC;
		}
		else if((document.getElementById('plannedCatId'+srId ).value == "302018")||(document.getElementById('plannedCatId'+srId ).value == "302023"))
		{
			cntST--;		
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		else if((document.getElementById('plannedCatId'+srId).value == "302020")||(document.getElementById('plannedCatId'+srId).value == "302025"))
		{
			cntPH--;			
			document.getElementById('ph'+srId).innerHTML = cntPH;
		}
		
		if((document.getElementById('catId'+srId).value == "302017") ||(document.getElementById('catId'+srId).value == "302022"))
		{
			cntSC++;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		else if((document.getElementById('catId'+srId).value == "302019")||(document.getElementById('catId'+srId).value == "302023"))
		{
			cntSEBC++;
			document.getElementById('sebc'+srId).innerHTML = cntSEBC;
		}
		else if((document.getElementById('catId'+srId ).value == "302018")||(document.getElementById('catId'+srId ).value == "302023"))
		{
			cntST++;
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		else if((document.getElementById('catId'+srId).value == "302020")||(document.getElementById('catId'+srId).value == "302025"))
		{
			cntPH++;
			document.getElementById('ph'+srId).innerHTML = cntPH;
		}
		// For the last row
			document.getElementById('scsur').innerHTML = cntSC;
		    document.getElementById('stsur').innerHTML = cntST;
			document.getElementById('sebcsur').innerHTML = cntSEBC;
			document.getElementById('phsur').innerHTML = cntPH;
	}
		
		
