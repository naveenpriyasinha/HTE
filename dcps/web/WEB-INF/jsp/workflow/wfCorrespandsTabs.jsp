<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript">
var src=document.getElementById("tab1");



function changecolor(src)
{


	var ullist=document.getElementById('tabmenu').getElementsByTagName("li")
	for (var i=0; i<ullist.length; i++)
	{
		ullist[i].getElementsByTagName("a")[0].className="unselected"  //deselect all tabs
		ullist[i].getElementsByTagName("a")[0].style.color="black"
	}
	tabname=src.id+"_Link";
	document.getElementById(tabname).className="selected";

	return true;
}
function saveData(src)
{
			
			
			parent.document.getElementById('tabclickcnt').value=src.id;
			
			if(src.id != "tab3")
				parent.whichLinkClicked = false;
			else if(src.id == "tab3")
				parent.whichLinkClicked = true;
				
				var tabname=src.id+"_Link"
				parent.document.getElementById('Target_frame').src=document.getElementById(tabname).href;
			if(!parent.whichLinkClicked)
			{
							
						if(parent.frames['Target_frame'].document.forms[0]!=null)
						{	
							var formname=parent.frames['Target_frame'].document.forms[0].name;
							if(confirm("Save Previous Tab Details?"))
							{
							
									//var temp=eval("parent.document.getElementById('Target_frame').contentWindow.validateForm_"+formname+"()");
									//if(temp==true)
									if(eval("parent.document.getElementById('Target_frame').contentWindow.validateForm_"+formname+"()"))
									{
										    try
										    {
										    changecolor(src);
										 	parent.frames['Target_frame'].document.forms[0].submit();
											}
											catch(e)
											{
											alert(e);
											}
											changecolor(src);
											return true;
									}
									else
									{
										
										return false;
									}	
							}
							else
							{
									changecolor(src);
									return true;
							}
					  }
					  else
						{
							changecolor(src);
							return true;
						}
						
						
			}
			else
			{
				changecolor(src);
				return true;
			}					
				
}


</script>

<div id="tabmenu">
	<ul id="maintab">	
		<li id="tab1"  onclick="return saveData(this);"><a id="tab1_Link"  href="#" target="Target_frame" > <fmt:message key="WF.Drafts"  bundle="${fmsLables}"></fmt:message></a></li> 
		<li id="tab2"  onclick="return saveData(this)"><a id="tab2_Link" target="Target_frame"> <fmt:message key="WF.Attachment" bundle="${fmsLables}"></fmt:message></a></li>
		<li id="tab3" onclick="return saveData(this)"><a id='tab3_Link' target="Target_frame"> <fmt:message key="WF.BasicInfo"  bundle="${fmsLables}"></fmt:message></a></li>
		<li id="tab4" onclick="return saveData(this)"><a id='tab4_Link' target="Target_frame"> <fmt:message key="FMS.OUTWARDVIEW"  bundle="${fmsLables}"></fmt:message></a></li>
	</ul>
</div>

<script type="text/javascript">
	var ullist=document.getElementById('tabmenu').getElementsByTagName("li")
	for (var i=0; i<ullist.length; i++)
	{
		ullist[i].getElementsByTagName("a")[0].className="unselected"  //deselect all tabs
		ullist[i].getElementsByTagName("a")[0].style.color="black"
	}
	document.getElementById('tab3_Link').className="selected";
	var infotabflag=parent.document.getElementById('infotabflag').value;
	if(infotabflag=="no")
	{
	document.getElementById('tab3_Link').style.display="none";
	document.getElementById('tab2_Link').className="selected";
	}

	for(var i=0;i<parent.tab_arr.length;i++)
	{
		if(parent.tab_arr[i]==4)
			parent.tab_arr[i]=1;
		var tabno="tab"+parent.tab_arr[i]+"_Link";
		
		if(document.getElementById(tabno))
		{
			document.getElementById(tabno).style.background="#B6BAD5";
		}	
		
	}
</script>

	

