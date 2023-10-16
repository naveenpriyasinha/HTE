//gloabal Variable;
	var totalLength=0;
	var totalRow=0;
	var srId=0;
	var empArray=new Array();
	var cntSC=0,cntST=0,cntSEBC=0,cntPH=0;
	var counterRoster=0;
	var previousSize=0;
	

function submitDtls()
{	
	if(!formValidate())
	{
		return;
	}
	var urlstyle="hrms.htm?actionFlag=submitAllocationDlts";
	document.Roster.action=urlstyle;
	document.Roster.submit();
}	




function closewindow()
{
		
		var urlstyle="hrms.htm?actionFlag=getHomePage";
		document.Roster.action=urlstyle;
		document.Roster.submit();
		
}

//ToolTip Implementation

function xstooltip_show(e,text,maintooltipId,tooltipId, parentId)
{
//Showing tooltip onmouseover
		if(document.all)e = event;
    	it = document.getElementById(maintooltipId);
   		tooltip_content = document.getElementById(tooltipId);
         
        img = document.getElementById(parentId); 
    	
    	//Giving the content to the tooltip
    	
    	if(text=='previouslist')
    	{
     	var empname = document.getElementById("empName"+parentId).innerHTML;
    	var dateofjoining= document.getElementById("joindate"+parentId).innerHTML;
    	var plan_category= document.getElementById("plan_category"+parentId).innerHTML;
    	var act_category= document.getElementById("lblDept"+parentId).innerHTML;
    	//Added by Hardik
    	var tmpScVal=0;
    	var tmpStVal=0;
    	var tmpSebcVal=0;
    	var tmpPhVal=0;
    	for(var i=parentId;i>=1;i--)
    	{
	    	var scname="sc"+i;
	    	var scValue=document.getElementById(scname).innerHTML;
	    	if(scValue.length!=0)
 			{
 				tmpScVal=scValue;
 				break;
 			}
	    	
    	}
    	//Calculation For ST
    	for(var i=parentId;i>=1;i--)
    	{
	  	    var stname="st"+i;
	    	var stValue=document.getElementById(stname).innerHTML;
	    	if(stValue.length!=0)
 			{
 				tmpStVal=stValue;
 				break;
 			}
	    	
    	}
    	//Calculation For SEBC
    	for(var i=parentId;i>=1;i--)
    	{
	    	var sebcname="sebc"+i;
	    	var sebcValue=document.getElementById(sebcname).innerHTML;
	    	if(sebcValue.length!=0)
 			{
 				tmpSebcVal=sebcValue;
 				break;
 			}
	    	
    	}
       	//Calculation For PHs
    	for(var i=parentId;i>=1;i--)
    	{
	    	var phname="ph"+i;
	    	var phValue=document.getElementById(phname).innerHTML;
	    	if(phValue.length!=0)
 			{
 				tmpPhVal=phValue;
 				break;
 			}
	    	
    	}
    	//Ended by Hardik
		
    	tooltip_content.innerHTML = empname + '<br>' + dateofjoining+'<br>'+"Planned Category: <b>"+plan_category+'</b><br>'+"Actual Category:<b> "+act_category+"</b><br>"+"<b> SC:</b>"+tmpScVal+"<b> ST:</b>"+tmpStVal+"<b> SEBC:</b>"+tmpSebcVal+"<b> PH:</b>"+tmpPhVal;
    	}
    	else
    	{
    		var empname = document.getElementById("empName"+parentId).value;
    		
    		if(empname==""||empname==null)
    		{
    		var tmpScVal = document.getElementById("lscval"+parentId).innerHTML;
    		var tmpStVal = document.getElementById("lstval"+parentId).innerHTML;
    		var tmpSebcVal = document.getElementById("lsebcval"+parentId).innerHTML;
    		var tmpPhVal = document.getElementById("lphval"+parentId).innerHTML;
    		tooltip_content.innerHTML ="<b>SC:</b>"+tmpScVal+" "+"<b>ST:</b>"+tmpStVal+" "+"<b>SEBC:</b>"+tmpSebcVal+" "+"<b>PH:</b>"+tmpPhVal;
    		}
    		else
    		{
    			
    	var dateofjoining= document.getElementById("lblDoj"+parentId).value;
    
    	var act_category= document.getElementById("category"+parentId).innerHTML;
    	//Added by Hardik
    	var tmpScVal=0;
    	var tmpStVal=0;
    	var tmpSebcVal=0;
    	var tmpPhVal=0;
    	for(var i=parentId;i>=1;i--)
    	{
	    	var scname="sc"+i;
	    	var scValue=document.getElementById(scname).innerHTML;
	    	if(scValue.length!=0)
 			{
 				tmpScVal=scValue;
 				break;
 			}
	    	
    	}
    	//Calculation For ST
    	for(var i=parentId;i>=1;i--)
    	{
	  	    var stname="st"+i;
	    	var stValue=document.getElementById(stname).innerHTML;
	    	if(stValue.length!=0)
 			{
 				tmpStVal=stValue;
 				break;
 			}
	    	
    	}
    	//Calculation For SEBC
    	for(var i=parentId;i>=1;i--)
    	{
	    	var sebcname="sebc"+i;
	    	var sebcValue=document.getElementById(sebcname).innerHTML;
	    	if(sebcValue.length!=0)
 			{
 				tmpSebcVal=sebcValue;
 				break;
 			}
	    	
    	}
       	//Calculation For PHs
    	for(var i=parentId;i>=1;i--)
    	{
	    	var phname="ph"+i;
	    	var phValue=document.getElementById(phname).innerHTML;
	    	if(phValue.length!=0)
 			{
 				tmpPhVal=phValue;
 				break;
 			}
	    	
    	}
    	//Ended by Hardik
		
    	tooltip_content.innerHTML = empname + '<br>' + dateofjoining+'<br>'+"Actual Category:<b> "+act_category+"</b><br>"+"<b> SC:</b>"+tmpScVal+"<b> ST:</b>"+tmpStVal+"<b> SEBC:</b>"+tmpSebcVal+"<b> PH:</b>"+tmpPhVal;
    
    		}
    	} 	
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
   it.style.top=(posy-130)+"px";
   it.style.left=(posx-105)+"px";
    	
    	        
    
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
			
			
			
			document.getElementById(document.getElementById('hidUserId').value).value= single[4].trim();
			
			//Date Calculation
			var array = single[12].split (" ");
			
			var returnArray = new Array();
			var date = array[0];
	//		
			var dateArray =  date.split ("-");
			var dateStr = dateArray[2]+"/"+dateArray[1] +"/"+dateArray[0]; 
			returnArray[0] = dateStr;
			
			var time = array[1];
			
			var timeArray = time.split(":"); 		
			var timeStr = timeArray[0]+":"+timeArray[1];
			returnArray[1] = timeStr;
			
			document.getElementById(document.getElementById('doj').value).value= returnArray[0];
			
			//End	
			cntSC=0;
			cntST=0;
			cntSEBC=0;
			cntPH=0;
			
			for(var j=1;j<=totalRow;j++)
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
			cntSC=0;
			cntST=0;
			cntSEBC=0;
			cntPH=0;
			for(var i=1;i<=totalRow;i++)
			{
				
				srId=i;
				var name="empName"+i;
				
				var boxVal=document.getElementById(name).value;
				
				
				var len=boxVal.length;
			
				if(len!=0)
				{
					presetCountForSurplus();
				}
			}
		}
function enableSearch(form)
{
	
	
	var SrNo=form.id;
	var rowNo=SrNo.substring(11,SrNo.length);
	var search="Search"+rowNo;
	var disSearch="disSearch"+rowNo;
	if(form.value=="-1")
	{
		document.getElementById(search).style.display="none";
		document.getElementById(disSearch).style.display="";
		return;	
	}else
	{
		document.getElementById(search).style.display="";
		document.getElementById(disSearch).style.display="none";
	}
	
	var name="empName"+rowNo;

	var boxVal=document.getElementById(name).value;

	var len=boxVal.length;
	
	if(len!=0)
	{
		
		for(var j=1;j<=totalRow;j++)
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
		cntSC=0;
		cntST=0;
		cntSEBC=0;
		cntPH=0;
		
		for(var i=1;i<=totalRow;i++)
		{
			srId=i;
			var ename="empName"+i;
			var eboxVal=document.getElementById(ename).value;
			var elen=eboxVal.length;
			if(elen!=0)
			{
				presetCountForSurplus();
			}
		}
	}
	
}
function presetCountForSurplus()
{
	
		if((document.getElementById('Categorysel'+srId).value == "302017") ||(document.getElementById('Categorysel'+srId).value == "302022"))
		{
			cntSC--;
			document.getElementById('sc'+srId).innerHTML = cntSC;
		}
		else if((document.getElementById('Categorysel'+srId).value == "302019")||(document.getElementById('Categorysel'+srId).value == "302024"))
		{
			cntSEBC--;
			document.getElementById('sebc'+srId).innerHTML = cntSEBC;
		}
		else if((document.getElementById('Categorysel'+srId ).value == "302018")||(document.getElementById('Categorysel'+srId ).value == "302023"))
		{
			cntST--;		
			document.getElementById('st'+srId).innerHTML = cntST;
		}
		else if((document.getElementById('Categorysel'+srId).value == "302020")||(document.getElementById('Categorysel'+srId).value == "302025"))
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
	
		
		