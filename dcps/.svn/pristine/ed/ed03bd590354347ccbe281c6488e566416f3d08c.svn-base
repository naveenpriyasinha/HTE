<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript">

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
			parent.document.getElementById('currenttabid').value=src.id;
			if(src.id != "tab8")
				parent.whichLinkClicked = false;
			else if(src.id == "tab8")
				parent.whichLinkClicked = true;
				
			var tabname=src.id+"_Link"
			parent.document.getElementById('Target_frame').src=document.getElementById(tabname).href;
			
			if(!parent.whichLinkClicked)
			{
				if(parent.frames['Target_frame'].document.forms[0]!=null)
				{
							var formname=parent.frames['Target_frame'].document.forms[0].name;
													
									var msg = '<fmt:message key="WF.SavePreDtls" bundle="${fmsLables}"/>';
									if(confirm(msg))
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
		<!-- <li id="tab1"  onclick="return saveData(this)"  style="background-color: white;display: none"   onmouseover="applyColor('tab1_Link')" onmouseout="reDrawColor('tab1_Link')"><a id='tab1_Link' href="#" target="Target_frame"  ><fmt:message key="WF.NotingSide"  bundle="${fmsLables}"></fmt:message></a></li>
		 -->
		
		<li id="tab2"  onclick="return saveData(this)"  ><a id='tab2_Link'  target="Target_frame" ><fmt:message key="WF.TappalSide" bundle="${fmsLables}"></fmt:message></a></li>
		
		<!--
		<li id="tab3"  onclick="return saveData(this)"  style="background-color: #99CC99;display: none" onmouseover="applyColor('tab3_Link')" onmouseout="reDrawColor('tab3_Link')"><a id='tab3_Link' href="#" target="Target_frame" ><fmt:message key="WF.PrecedentCases"  bundle="${fmsLables}"></fmt:message></a></li>
		 -->
		<li id="tab4"  onclick="return saveData(this)"  ><a id='tab4_Link'  target="Target_frame" ><fmt:message key="WF.Drafts"   bundle="${fmsLables}"></fmt:message></a></li>
		<!--
		<li id="tab5"  onclick="return saveData(this)"  style="background-color: #99CC99;display: none" onmouseover="applyColor('tab5_Link')" onmouseout="reDrawColor('tab5_Link')"><a id='tab5_Link' href="#" target="Target_frame"><fmt:message key="WF.ReferenceDocs"   bundle="${fmsLables}"></fmt:message></a></li>
		<li id="tab6"  onclick="return saveData(this)"  style="background-color: #99CC99;display: none" onmouseover="applyColor('tab6_Link')" onmouseout="reDrawColor('tab6_Link')"><a id='tab6_Link' href="#" target="Target_frame"><fmt:message key="WF.CheckList"    bundle="${fmsLables}"></fmt:message></a></li>
		<li id="tab7"  onclick="return saveData(this)"  style="background-color: #99CC99;display: none" onmouseover="applyColor('tab7_Link')" onmouseout="reDrawColor('tab7_Link')"><a id='tab7_Link' href="#" target="Target_frame"><fmt:message key="WF.LinkedFiles"  bundle="${fmsLables}"></fmt:message></a></li>
		-->
		<li id="tab6"  onclick="return saveData(this)"  ><a id='tab6_Link' target="Target_frame"><fmt:message key="WF.CheckList"  bundle="${fmsLables}"></fmt:message></a></li>		
		<li id="tab7"  onclick="return saveData(this)"  ><a id='tab7_Link'  target="Target_frame"><fmt:message key="WF.PrecedentCases"  bundle="${fmsLables}"></fmt:message></a></li>		
		<li id="tab9"  onclick="return saveData(this)"  ><a id='tab9_Link'  target="Target_frame"><fmt:message key="WF.Communique"  bundle="${fmsLables}"></fmt:message></a></li>		
		<li id="tab8"  onclick="return saveData(this)"  ><a id='tab8_Link'  target="Target_frame"><fmt:message key="WF.BasicInfo"  bundle="${fmsLables}"></fmt:message></a></li>		
		<li id="tab10" onclick="return saveData(this)"  ><a id='tab10_Link'  target="Target_frame"><fmt:message key="FMS.OUTWARDVIEW"  bundle="${fmsLables}"></fmt:message></a></li>		
	</ul>
</div>
<script language="javascript">
var ullist=document.getElementById('tabmenu').getElementsByTagName("li")
	for (var i=0; i<ullist.length; i++)
	{
		ullist[i].getElementsByTagName("a")[0].className="unselected"  //deselect all tabs
		ullist[i].getElementsByTagName("a")[0].style.color="black"
		
	}
	document.getElementById('tab8_Link').className="selected";
	var infotabflag=parent.document.getElementById('infotabflag').value;
	if(infotabflag=="no")
	{
	
	document.getElementById('tab8_Link').style.display="none";
	document.getElementById('tab2_Link').className="selected";
	}
	for(var i=0;i<parent.tab_arr.length;i++)
	{
		var tabno="tab"+parent.tab_arr[i]+"_Link";
		if(document.getElementById(tabno))
		{
			document.getElementById(tabno).style.background="#B6BAD5";
		}	
	}
</script>