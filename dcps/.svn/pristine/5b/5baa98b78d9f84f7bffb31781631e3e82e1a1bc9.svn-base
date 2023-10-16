<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>

<script type="text/javascript" language="javascript" src="<c:url value="/script/common/xp_progress.js"/>">
</script>	

<script>
Event.observe(window, 'unload', function(){
<% 
if(session.getAttribute("fmsTemplateAttributeMstLst")!=null)
	session.removeAttribute("fmsTemplateAttributeMstLst");
if(session.getAttribute("fmsTemplateAttributeDtlsEngLst")!=null)
	session.removeAttribute("fmsTemplateAttributeDtlsEngLst");
if(session.getAttribute("fmsTemplateAttributeDtlsGujLst")!=null)
	session.removeAttribute("fmsTemplateAttributeDtlsGujLst");
if(session.getAttribute("fmsTemplateCategoryMstLst")!= null)
	session.removeAttribute("fmsTemplateCategoryMstLst");
if(session.getAttribute("fmsTemplateCategoryDtlsEngLst")!= null)
	session.removeAttribute("fmsTemplateCategoryDtlsEngLst");
if(session.getAttribute("fmsTemplateCategoryDtlsGujLst")!= null)
	session.removeAttribute("fmsTemplateCategoryDtlsGujLst");
if(session.getAttribute("fmsCtgryAttribDsplDtlLst")!=null)
	session.removeAttribute("fmsCtgryAttribDsplDtlLst");
%>
//alert('ff');
});
function changeSource(src)
{
	var ullist=document.getElementById('tabmenu').getElementsByTagName("li");
	for (var i=0; i<ullist.length; i++)
	{
		ullist[i].getElementsByTagName("a")[0].className="unselected";
		ullist[i].getElementsByTagName("a")[0].style.color="black";
	}
	tabname=src.id+"_Link";
	document.getElementById(tabname).className="selected";
	if(src.id == 'tab1')
		document.getElementById('mainFrame').src="hdiits.htm?viewName=wf-defineDraft";
	else if(src.id == 'tab2')
		document.getElementById('mainFrame').src="hdiits.htm?viewName=wf-defineAttribute";
	else if(src.id == 'tab3')
		document.getElementById('mainFrame').src="hdiits.htm?viewName=wf-defineCategory";
	else if(src.id == 'tab4')
		document.getElementById('mainFrame').src="hdiits.htm?viewName=wf-defineTemplate";
	else if(src.id == 'tab5')
		document.getElementById('mainFrame').src="hdiits.htm?viewName=wf-defineCategoryAttributeMapping";
		
}
var counter = 0;
function chkTab(frm)
{
	if(counter==5)
	{
		counter=0;
		var ullist=document.getElementById('tabmenu').getElementsByTagName("li");
		for (var i=0; i<ullist.length; i++)
		{
			ullist[i].getElementsByTagName("a")[0].className="unselected";
			ullist[i].getElementsByTagName("a")[0].style.color="black";
		}
	
	}
	if(counter==0)
	{
	document.getElementById('tab1_Link').className="selected";
	}
	else if(counter==1)
	{
	document.getElementById('tab2_Link').className="selected";
	}
	else if(counter==2)
	{
	document.getElementById('tab3_Link').className="selected";
	}
	else if(counter==3)
	{
	document.getElementById('tab4_Link').className="selected";
	}
	else if(counter==4)
	{
	document.getElementById('tab5_Link').className="selected";
	}
	counter++;
}

function resize_iframe()
{
	document.getElementById("mainFrame").height=100 ;
	document.getElementById('mainFrame').height=window.frames["mainFrame"].document.body.scrollHeight;
	hideProgressbar();
}

</script>

<table width="100%" id="table1" align="left">
	<tr>
		<td colspan="2" valign="bottom">
		<div id="tabmenu">
			<ul id="maintab" class="shadetabs">	
				<li id="tab1" ><a id="tab1_Link">Define Draft</a></li>
				<li id="tab2" ><a id="tab2_Link">Define Attribute</a></li>
				<li id="tab3" ><a id="tab3_Link">Define Category</a></li>
				<li id="tab4" ><a id="tab4_Link">Define Template</a></li>
				<li id="tab5" ><a id="tab5_Link">Category Attribute Mapping</a></li>
			</ul>
		</div>
		</td>
	</tr>
	<tr>
		<td width="100%" height="100%" >
			<table width="100%" heigth="100%" cellspacing="0" cellpadding="2" border="1" style="border:1px solid #c3daf9;">
			<tr>
				<td> 
					<iframe id="mainFrame" name="mainFrame" width="100%" height="100%" style="border: 1px solid black;" onload='resize_iframe(), chkTab(this)'></iframe>
				</td>
			</tr>
			</table>
		</td>
	</tr>
</table>
<script type="text/javascript">
	changeSource(document.getElementById('tab1'));	
</script>