	
	
	
function submitDtls()
{
	var urlstyle="hrms.htm?actionFlag=submitAllocationDlts";
	document.frm1.action=urlstyle;
	document.frm1.submit();
}
function showRoster()
{
	var urlstyle="hrms.htm?actionFlag=rosterView&RosterType=P";
	document.frm1.action=urlstyle;
	document.frm1.submit();
}	

function closeWindows()
{
	var urlstyle="hdiits.htm?actionFlag=getHomePage";
	document.frm1.action=urlstyle;
	document.frm1.submit();mainTable
}

function xstooltip_show(e,maintooltipId,tooltipId, parentId)
{
//Showing tooltip onmouseover
		if(document.all)e = event;
    	it = document.getElementById(maintooltipId);
   		tooltip_content = document.getElementById(tooltipId);
         
        img = document.getElementById(parentId); 
    	
    	//Giving the content to the tooltip
    	
    	var empname = document.getElementById("empName"+parentId).innerHTML;
    	var dateofjoining= document.getElementById("joindate"+parentId).innerHTML;
    	var plan_category= document.getElementById("plan_category"+parentId).innerHTML;
    	var act_category= document.getElementById("act_category"+parentId).innerHTML;
    	var tmpScVal=0;
    	var tmpStVal=0;
    	
 		for(var i=1;i<=parentId;i++)
 		{
 			var scname="sc"+i;
 			var stname="st"+i;
 			
 			
 			
 		
 			var scValue=document.getElementById(scname).innerHTML;
			var stValue=document.getElementById(stname).innerHTML;
			
 			if(scValue.length!=0)
 			{
 				if(scValue==0)
 				{
 					tmpScVal=tmpScVal*1+scValue*1;
				}
				else
				{
					tmpScVal=scValue*1;
				}
 			}
 			if(stValue.length!=0)
 			{
 				if(stValue==0)
 				{
 					tmpStVal=tmpStVal*1+stValue*1;
				}
				else
				{
					tmpStVal=stValue*1;
				}
 			}
 			 			
 		}
		
    	tooltip_content.innerHTML = empname + '<br>' + dateofjoining+'<br>'+"Planned Category: <b>"+plan_category+'</b><br>'+"Actual Category:<b> "+act_category+"</b><br>"+"<b> SC:</b>"+tmpScVal+"<b> ST:</b>"+tmpStVal;
    	
    	it.style.display = 'block';
    	
    	//Setting the position of the tooltip
    	
    	var posx=0,posy=0;
	if(e==null) e=event;
	if(e.pageX || e.pageY){
    posx=e.pageX; posy=e.pageY;
    }
	else if(e.clientX || e.clientY){
    if(document.documentElement.scrollTop){
        posx=e.clientX+document.documentElement.scrollLeft;
        posy=e.clientY+document.documentElement.scrollTop;
        }
    else{
        posx=e.clientX+document.body.scrollLeft;
        posy=e.clientY+document.body.scrollTop;
        }
    }
it.style.top=(posy-420)+"px";
it.style.left=(posx-110)+"px";
    	
    	        
     
}

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
		function empSearch(finalEmpArr)
		{
		
			document.getElementById(document.getElementById('empSearchName').value).value = finalEmpArr[0];
			document.getElementById(document.getElementById('deptname').value).innerHTML =finalEmpArr[2];
			document.getElementById(document.getElementById('hidcatId').value).value=finalEmpArr[4];
			document.getElementById(document.getElementById('doj').value).value= finalEmpArr[1];
			var tmpArray=finalEmpArr[1].split(" ");
			var joinDate=new Date(tmpArray[0]);
			
			
			document.getElementById(document.getElementById('hidreset').value).style.display="";
		
			
			document.getElementById(document.getElementById('hidUserId').value).value= finalEmpArr[3].trim();
	   		setCountForSurplus();
}
function resetEmp(form)
{
	
	document.getElementById('hidVal').value="N";
	
	var id =form.id;
	
	var rowNo=id.substring(5,id.length);
	var hidName="userId"+rowNo;

	
	document.getElementById('hidUserId').value=document.getElementById(hidName).value;
	addOrUpdateRecordForEmpSearch('addRecord','updateTransferVO',new Array('hidUserId','hidVal'));
	document.getElementById('empName'+rowNo).value = "";
	document.getElementById('lblDept'+rowNo).innerHTML ="";
	document.getElementById('lblDoj'+rowNo).value= "";
	document.getElementById('reset'+rowNo).style.display="none";
	document.getElementById('sc'+rowNo).innerHTML="";
	
	document.getElementById('st'+rowNo).innerHTML = "";
	
}
function addOrUpdateRecordForEmpSearch(methodName, actionFlag, fieldArray) 
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
			
			
		}
		
	}
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
		for(var j=rowNo;j<=counterRoster;j++)
		{
			var scVal='sc'+j;
			var stVal='st'+j;
			
			document.getElementById(scVal).innerHTML="";
			document.getElementById(stVal).innerHTML="";
			
			
			document.getElementById('scsur').innerHTML ="";
		    document.getElementById('stsur').innerHTML = "";
		}
		
		for(var i=rowNo;i<=counterRoster;i++)
		{
			srId=i;
			setCountForSurplus();
		}
	}
}
function presetCountForSurplus()
{
		
		if(document.getElementById('Categorysel'+srId).innerHTML == "SC")
		{
			cntSC--;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		
		else if(document.getElementById('Categorysel'+srId).innerHTML == "ST")
		{
			cntST--;		
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		
		
		if(document.getElementById('lblDept'+srId).innerHTML == "SC")
		{
			cntSC++;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		
		else if(document.getElementById('lblDept'+srId).innerHTML == "ST")
		{
			cntST++;
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		
		// For the last row
			document.getElementById('scsur').innerHTML = cntSC;
		    document.getElementById('stsur').innerHTML = cntST;
			
	}
function setCountForSurplus()
{
		
		if((document.getElementById('Categorysel'+srId).value == "302017") ||(document.getElementById('Categorysel'+srId).value == "302022"))
		{
			cntSC--;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		
		else if((document.getElementById('Categorysel'+srId ).value == "302018")||(document.getElementById('Categorysel'+srId ).value == "302023"))
		{
			cntST--;		
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		
		
		if((document.getElementById('catId'+srId).value == "302017") ||(document.getElementById('catId'+srId).value == "302022"))
		{
			cntSC++;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		
		else if((document.getElementById('catId'+srId ).value == "302018")||(document.getElementById('catId'+srId ).value == "302023"))
		{
			cntST++;
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		
		// For the last row
			document.getElementById('scsur').innerHTML = cntSC;
		    document.getElementById('stsur').innerHTML = cntST;
			
	}
		
		