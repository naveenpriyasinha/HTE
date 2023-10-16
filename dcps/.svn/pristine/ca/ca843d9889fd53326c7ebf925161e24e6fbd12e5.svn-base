<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<%try{ %>
<c:set var="resultObj"	value="${result}"></c:set> 
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="resultJob" value="${resultMap.JobList}"></c:set>
<c:set var="deptNameList" value="${resultMap.deptNameList}"></c:set>

<fmt:setBundle basename="resources.WFLables" var="wfLables" scope="request"/>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="workFlowLables" scope="request"/>
 <!--  code started by abhi -->
	<fmt:message bundle="${workFlowLables}" key="WF.DBOPEN" var="label1"></fmt:message>
	<fmt:message bundle="${workFlowLables}" key="WF.DBPEND" var="label2"></fmt:message>
	<fmt:message bundle="${workFlowLables}" key="WF.DBREC" var="label3"></fmt:message>
	<fmt:message bundle="${workFlowLables}" key="WF.DBASGN" var="label4"></fmt:message>
	<fmt:message bundle="${workFlowLables}" key="WF.DBGEN" var="label5"></fmt:message>
	<fmt:message bundle="${workFlowLables}" key="WF.DBSPC" var="label6"></fmt:message>
	<!--  code ended by abhi -->
<style type="text/css">
  .redFont { font-family:Verdana;text-decoration: none;font-size:11;font-weight:bold;color:Red;}
</style>
<style type="text/css">
  .greenFont { font-family:Verdana;text-decoration: none;font-size:11;font-weight:bold;color:Green;}
</style>

<hdiits:form name="dashBoardForm" validate="true" method="post" action="./hdiits.htm">
<hdiits:hidden name="department"/>
<hdiits:hidden name="location"/>
<hdiits:hidden name="branch"/>
<hdiits:hidden name="deptSel"/>
<hdiits:hidden name="locSel"/>
<hdiits:hidden name="brnchSel"/>
<hdiits:hidden name="brnch"/>
<hdiits:hidden name="prioritySel" />

<script type="text/javascript">

	function loadLocationCombo()
	{
		if(document.getElementById('comboDept').selectedIndex=='0')
		{
			if(document.getElementById('comboLoc').selectedIndex!='0')
			{
				var url = "hdiits.htm?actionFlag=showBarChart&moduleName=DashBoard&menuName=forDashBoard";
				document.getElementById('dashBoardForm').action = url;
				document.getElementById('dashBoardForm').submit();
				return;
			}
		}
		
		var locCombo = document.getElementById('comboLoc');
		if(locCombo.options.length>'0')
		{
			for(var i=locCombo.options.length; i>0; i--)
			{
				locCombo.remove(i);
			}
		}
		locCombo.selectedIndex = '0';

/*		var brnchCombo = document.getElementById('comboBrnch');
		if(brnchCombo.options.length>'0')
		{
			for(var i=brnchCombo.options.length; i>0; i--)
			{
				brnchCombo.remove(i);
			}
		}
		brnchCombo.selectedIndex = '0';*/
		
		if(document.getElementById('comboDept').selectedIndex == '0')
		{
			return false;
		}
		
		var deptVal = document.getElementById('comboDept')[document.getElementById('comboDept').selectedIndex].value;
			
		try
	   	{   
	   	// Firefox, Opera 8.0+, Safari    
	   		xmlHttp=new XMLHttpRequest();    
	   	}
		catch(e)
		{    // Internet Explorer    
			try
     		{
     		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
     		}
		    catch(e)
		    {
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
				}
				catch (e)
				{
			        alert("Your browser does not support AJAX!");        
			        return false;        
				}
			 }
	   	}
	
		var url = "hdiits.htm?actionFlag=getLocationForDepartment&deptName="+deptVal;
	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4)
			{     
				if(xmlHttp.status == 200)
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					var Category = XMLDoc.getElementsByTagName('Loc');
					if(Category.length==0)
					{
						alert('<fmt:message key="WF.NOBRANCH" bundle="${wfLables}" />');
						return;
					}
					var comboid = document.getElementById('comboLoc');

					for (var i=0 ; i < Category.length ; i++ )
					{
	  					var LocName = Category[i].childNodes[0].text;	   						
	  					var element = document.createElement('option');
	     				element.text = LocName;
	     				element.value = LocName;
	     				try
					    {
					    	comboid.add(element,null); // standards compliant
					    }
					    catch(ex)
					    {
						    comboid.add(element); // IE only
					    }
					}  
				}
			}
	    }
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}

	function loadBranchBar()
	{
		if(document.getElementById('locationDetails').style.display!='none')
		{
			if(document.getElementById('comboLoc').selectedIndex=='0')
			{
				var url = "hdiits.htm?actionFlag=showBarChart&moduleName=DashBoard&menuName=forDashBoard";
				document.getElementById('dashBoardForm').action = url;
				document.getElementById('dashBoardForm').submit();
				return;
			}

			var comboDept = document.getElementById('comboDept');
			for(var l=1; l<comboDept.options.length; l++)
			{
				if(l!=1)
					document.getElementById('department').value+=";;";
				document.getElementById('department').value += comboDept.options[l].text;
			}
			
			var comboLoc = document.getElementById('comboLoc');
			for(var l=1; l<comboLoc.options.length; l++)
			{
				if(l!=1)
					document.getElementById('location').value+=";;";
				document.getElementById('location').value += comboLoc.options[l].text;
			}
	
			document.getElementById('deptSel').value = document.getElementById('comboDept')[document.getElementById('comboDept').selectedIndex].text;
			document.getElementById('locSel').value = document.getElementById('comboLoc')[document.getElementById('comboLoc').selectedIndex].text;

/*			alert(document.getElementById('department').value);
			alert(document.getElementById('location').value);
			alert(document.getElementById('branch').value);
			alert(document.getElementById('deptSel').value);
			alert(document.getElementById('locSel').value);
			alert(document.getElementById('brnchSel').value);
			alert(document.getElementById('brnch').value);*/

			var locVal = document.getElementById('comboLoc')[document.getElementById('comboLoc').selectedIndex].value;
			var url = "hdiits.htm?actionFlag=showBarChart&moduleName=DashBoard&menuName=forDashBoard&locName="+locVal;
			document.getElementById('dashBoardForm').action = url;
			document.getElementById('dashBoardForm').submit();
		}
		
/*		try
	   	{   
	   	// Firefox, Opera 8.0+, Safari    
	   		xmlHttp=new XMLHttpRequest();    
	   	}
		catch(e)
		{    // Internet Explorer    
			try
     		{
     		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
     		}
		    catch(e)
		    {
				try
				{
					xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
				}
				catch (e)
				{
			        alert("Your browser does not support AJAX!");        
			        return false;        
				}
			 }
	   	}
	
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4)
			{     
				if(xmlHttp.status == 200)
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					var Category = XMLDoc.getElementsByTagName('Brnch');
					if(Category.length==0)
					{
						alert("No Branch found...");
						return;
					}
					var comboid = document.getElementById('comboBrnch');

					for (var i=0 ; i < Category.length ; i++ )
					{
	  					var BranchId = Category[i].childNodes[0].text;	   						
	  					var BranchName = Category[i].childNodes[1].text;	   						
	  					var element = document.createElement('option');
	     				element.text = BranchName;
	     				element.value = BranchId;
	     				try
					    {
					    	comboid.add(element,null); // standards compliant
					    }
					    catch(ex)
					    {
						    comboid.add(element); // IE only
					    }
					}
				}
			}
	    }
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));*/
	}
	
	function updateBarChart()
	{
		if(document.getElementById('locationDetails').style.display!='none')
		{
			/*if(document.getElementById('comboBrnch').selectedIndex=='0')
			{
				var url = "hdiits.htm?actionFlag=showBarChart&moduleName=DashBoard&menuName=forDashBoard";
				document.getElementById('dashBoardForm').action = url;
				document.getElementById('dashBoardForm').submit();
				return;
			}*/

			var comboDept = document.getElementById('comboDept');
			for(var l=1; l<comboDept.options.length; l++)
			{
				if(l!=1)
					document.getElementById('department').value+=";;";
				document.getElementById('department').value += comboDept.options[l].text;
			}
			
			var comboLoc = document.getElementById('comboLoc');
			for(var l=1; l<comboLoc.options.length; l++)
			{
				if(l!=1)
					document.getElementById('location').value+=";;";
				document.getElementById('location').value += comboLoc.options[l].text;
			}
	
			/*var comboBrnch = document.getElementById('comboBrnch');
			for(var l=1; l<comboBrnch.options.length; l++)
			{
				if(l!=1)
					document.getElementById('branch').value+=";;";
				document.getElementById('branch').value += comboBrnch.options[l].text;
				document.getElementById('branch').value += ";;"+comboBrnch.options[l].value;
			}*/
	
			document.getElementById('deptSel').value = document.getElementById('comboDept')[document.getElementById('comboDept').selectedIndex].text;
			document.getElementById('locSel').value = document.getElementById('comboLoc')[document.getElementById('comboLoc').selectedIndex].text;
			//document.getElementById('brnchSel').value = document.getElementById('comboBrnch')[document.getElementById('comboBrnch').selectedIndex].text;
			//document.getElementById('brnch').value = document.getElementById('comboBrnch')[document.getElementById('comboBrnch').selectedIndex].value;

/*			alert(document.getElementById('department').value);
			alert(document.getElementById('location').value);
			alert(document.getElementById('branch').value);
			alert(document.getElementById('deptSel').value);
			alert(document.getElementById('locSel').value);
			alert(document.getElementById('brnchSel').value);
			alert(document.getElementById('brnch').value);*/
		}

		var url = "hdiits.htm?actionFlag=showBarChart&moduleName=DashBoard&menuName=forDashBoard";
		document.getElementById('dashBoardForm').action = url;
		document.getElementById('dashBoardForm').submit();
	}

</script>

	<table width="100%">
		<tr id="locationDetails">
			<td width="10%" align="left">
				<hdiits:caption captionid="WF.DEPARTMENT" bundle="${workFlowLables}" />
			</td>
			<td>
				<select id="comboDept" onchange="loadLocationCombo()">
					<option selected="selected" value="0"><hdiits:caption captionid="WF.SELECT" bundle="${wfLables}" /></option>
					<c:forEach items="${deptNameList}" var="dept">
						<option value="${dept}">
							<c:out value="${dept}" />
						</option>								
					</c:forEach>
				</select>
			</td>
			<td width="10%" align="left">
				<hdiits:caption captionid="WF.LOCATION" bundle="${workFlowLables}" />
			</td>
			<td> 
				<select id="comboLoc" onchange="loadBranchBar()">
					<option selected="selected" value="0"><hdiits:caption captionid="WF.SELECT" bundle="${wfLables}" /></option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="center" colspan="4">
				<jsp:include flush="true" page="../workflow/DashBoardSearch.jsp">
					<jsp:param name="priority" value="${resultMap.priority}" />
				</jsp:include>
			</td>
		</tr>
	</table>
<script type="text/javascript">
if('${resultMap.errorMsg}'!='')
{
	if('${resultMap.errorMsg}'=='1')
	{
		document.write('<br /><b><center><fmt:message key="WF.NOMAPFORLOC" bundle="${workFlowLables}" /></center></b>');
	}
	else if('${resultMap.errorMsg}'=='2')
	{
		document.write('<br /><b><center><fmt:message key="WF.NODEPT" bundle="${workFlowLables}" /></center></b>');
	}
}
else
{
var graphtext=new Array(); //Graph items
var graphvalue=new Array(); //Graph values (in percentage, so 70=70%)
var type=new Array();
graphtext=new Array();
graphvalue=new Array();
graphvalue=new Array();
var url=new Array();
if('${resultJob}' != '[]')
{
	var tempLst = '${resultJob}';
    var new1 = tempLst.split('[');
    var new2 = new1[1].split(']');
    var newLst = new2[0].split(',');
    var size = newLst.length;
	for(i=0;i<size;)
	{
		type.push(parseInt(newLst[i++]));
		graphvalue.push(parseInt(newLst[i++]));
		graphtext.push(newLst[i++]);
		url.push(newLst[i++]);
	}
}

var temp = -1;
var size=graphvalue.length;
for(var k=0; k<parseInt(size); k++)
{ 
  if(parseInt(graphvalue[k]) > parseInt(temp))
  {
     temp = graphvalue[k];
  }
}

var greenBarLength;
var redBarLength;
maxValue = temp;
var value = parseInt(maxValue);
var greenHeightValue;
var redHeightValue;
if (value>200)
{
   greenBarLength=200;
   redBarLength=200;
}
else
{
  if(maxValue==0)
    maxValue =1;
  greenBarLength = maxValue;  
  redBarLength = maxValue;
} 
document.write('<table border="0" width="100%" BGCOLOR="WHITE">');
document.write('<tr><td colspan="2">');
document.write('<table align ="center"  border="0" width="80%" cellspacing="3" cellpadding="3" bgcolor="#FFFFFF" height="314"><tr><td>');
document.write('<DIV STYLE="writing-mode: tb-rl; filter: flipv() fliph();"><font class="FldHierarchy"><b>---> Count </b></font></DIV></td>');
document.write('<td width= "720" height = "270"><table border="1"><tr><td><table align="center" border="0" width= "110" height = "278" cellpadding="0" cellspacing="0" bgcolor="#F3F3F3" border="0" ><tr>');
document.write(' <td width="30" background="images/workflowImages/left_bar.gif" valign="bottom"><img src="images/workflowImages/corner.gif" width="34" height="27"></td>');

var isGreen;
var len = graphvalue.length;
for(var i=0; i<len; i++)
{
	if(i==0)
	{
		isGreen = 1-type[0];
	}
	document.write('<td valign="bottom"><table border="0" cellpadding="0" cellspacing="0" border="0" >');
    document.write('<tr>');

      if(type[i]==0)
      {
	      if(greenBarLength <200 )
	      {
	          greenHeightValue = graphvalue[i]/greenBarLength*100;
	      }
	      else
	      {
	          greenHeightValue = (graphvalue[i]/maxValue)*200;
	      }
      }
      else
      {
	      if(redBarLength <200 )
	      {
	          redHeightValue = graphvalue[i]/greenBarLength*100;
	      }
	      else
	      {
	          redHeightValue = (graphvalue[i]/maxValue)*200;
	      }
      }

      document.write('<td valign="bottom" width="17"> <table width="17" border="0" cellspacing="0" cellpadding="0">');
      document.write('<tr><td>');
      var className;
      if(type[i]==0)
          className='greenFont';
      else
          className='redFont';
      if(graphvalue[i]>0)
      {
        document.write('<a href="'+url[i]+'"><b><font class='+className+'>');
        document.write(graphvalue[i]);
        document.write('</b></font></a>'); 
      }
      else
      {
        document.write(graphvalue[i]);
      }

      document.write('</td></tr><tr>');

      if(type[i]==0)
      {
	      if(graphvalue[i]>0)
	        document.write('<td><a href="'+url[i]+'"><img src="images/workflowImages/color1_top.gif" width="29" height="8" border="0" alt="' +graphtext[i]+'" /></a></td>');
	      else
	        document.write('<td><img src="images/workflowImages/color1_top.gif" width="29" height="8" border="0" alt="' +graphtext[i]+'" /></td>');
      }
      else
      {
	      if(graphvalue[i]>0)
	        document.write('<td><a href="'+url[i]+'"><img src="images/workflowImages/color2_top.gif" width="29" height="8" border="0" alt="' +graphtext[i]+'" /></a></td>');
	      else
	        document.write('<td><img src="images/workflowImages/color2_top.gif" width="29" height="8" border="0" alt="' +graphtext[i]+'" /></td>');
      }
      document.write('</tr>');
      document.write('<tr>');
      if(type[i]==0)
      {
	      if(graphvalue[i]>0)
	        document.write('<td><a href="'+url[i]+'"> <img src="images/workflowImages/color1_bar.jpg" width="29" height="'+greenHeightValue+'" border="0" alt="' +graphtext[i]+'" /></a></td>');
	      else
	        document.write('<td><img src="images/workflowImages/color1_bar.jpg" width="29" height="'+greenHeightValue+'" border="0" alt="' +graphtext[i]+'" /></td>');
      }
      else
      {
	      if(graphvalue[i]>0)
	        document.write('<td><a href="'+url[i]+'"> <img src="images/workflowImages/color2_bar.jpg" width="29" height="'+redHeightValue+'" border="0" alt="' +graphtext[i]+'" /></a></td>');
	      else
	        document.write('<td><img src="images/workflowImages/color2_bar.jpg" width="29" height="'+redHeightValue+'" border="0" alt="' +graphtext[i]+'" /></td>');
      }
      document.write('</tr>')
      document.write('<tr>')
      if(type[i]==0)
      {
	      if(graphvalue[i]>0)
    	    document.write('<td><a href="'+url[i]+'" ><img src="images/workflowImages/color1_bot.jpg" width="29" height="15" border="0" alt="' +graphtext[i]+'" /></a></td>');
          else
          	document.write('<td><img src="images/workflowImages/color1_bot.jpg" width="29" height="15" border="0" alt="' +graphtext[i]+'" /></td>');
      }
      else
      {
	      if(graphvalue[i]>0)
    	    document.write('<td><a href="'+url[i]+'" ><img src="images/workflowImages/color2_bot.jpg" width="29" height="15" border="0" alt="' +graphtext[i]+'" /></a></td>');
          else
          	document.write('<td><img src="images/workflowImages/color2_bot.jpg" width="29" height="15" border="0" alt="' +graphtext[i]+'" /></td>');
      }
      
      document.write('</tr>');
      document.write('</table></td>');

/*      if('${show}' == '0')
      {*/
	      if(i != 0)
	      {
		      if(type[i]==0 && isGreen==0)//bar is green, previous bar is red
		      {
				  isGreen = 1;
		      }
		      else if(type[i]==1 && isGreen==1)//bar is red, previous bar is green
		      {
		          document.write('<td valign="bottom"> <table width="10%" border="0" cellspacing="0" cellpadding="0">');
		          document.write('<tr>');
		          document.write('<td valign="bottom" width="107"><img src="images/workflowImages/bot_bar.gif" width="50" height="27" ></td>')
		          document.write('</tr></table></td>');
		    	  isGreen = 0;
		      }
	      }
/*      }
      else
      {
	      if(type[i]==0 && isGreen==1)//bar is green, previous bar is green
	      {
	    	  document.write('<td valign="bottom"> <table width="10%" border="0" cellspacing="0" cellpadding="0">');
	          document.write('<tr>');
	          document.write('<td valign="bottom" width="107"><img src="images/workflowImages/bot_bar.gif" width="50" height="27" ></td>')
	          document.write('</tr></table></td>');
			  isGreen = 1;
	      }
	      else if(type[i]==1 && isGreen==0)//bar is red, previous bar is red
	      {
	    	  document.write('<td valign="bottom"> <table width="10%" border="0" cellspacing="0" cellpadding="0">');
	          document.write('<tr>');
	          document.write('<td valign="bottom" width="107"><img src="images/workflowImages/bot_bar.gif" width="50" height="27" ></td>')
	          document.write('</tr></table></td>');
			  isGreen = 0;
	      }
      }*/
      
      document.write('</tr></table></td>');
}
document.write('<td valign="bottom"> <table width="10%" border="0" cellspacing="0" cellpadding="0">');
document.write('<tr>');
document.write('<td valign="bottom" width="107"><img src="images/workflowImages/bot_bar.gif" width="50" height="27" ></td>')
document.write('</tr></table></td>');

for(var l=0; l<graphtext.length; l++)
{
	var tempTxt = graphtext[l].split(' (');
	graphtext[l] = tempTxt[0];
}

var graphName=new Array();
var found;
for(var k=0; k<graphtext.length; k++)
{
	found=0;
	for(var l=0; l<graphName.length; l++)
	{
		if(graphName[l]==graphtext[k])
		{
			found=1;
		}
	}
	if(found==0)
		graphName.push(graphtext[k]);
}

document.write('</tr><tr><td></td>');
/*if('${show}' == '0')
{*/
	for(var j=0; j<graphName.length; j++)
	{
		document.write('<td colspan=2 align="left"><b>'+graphName[j]+'</b></td>');	
	}
/*}
else
{
	for(var j=0; j<graphName.length; j++)
	{
		document.write('<td colspan=1 align="left"><b>'+graphName[j]+'</b></td>');	
	}
}*/
	document.write('</tr></table></td></tr></table>');
	document.write('</td></tr><tr><td></td><td align=left><font class=FldHierarchy><b>---> Status </b></font></td></tr></table>');
   /*  code started by abhi */ 
	document.write('</td></tr><tr><td><table border="1" width="100%" align="left" bgcolor="#F0FFFF"><tr><td></td><td bgcolor="#00EE00" width="12"  ></td><td></td><td bordercolor="#F0FFFF"><b> ${label1}</b></td><td></td><td bgcolor="#FF0000" width="12"></td><td></td><td bordercolor="#F0FFFF"><b>${label2}</b></td></tr>');
	
	document.write('<tr><td></td><td></td><td></td><td bordercolor="#F0FFFF"><b>${label5}</b></td><td></td><td></td><td></td><td bordercolor="#F0FFFF"><b>${label6}</b></td></tr></table></td></tr></table>');
   /*   code ended by abhi */ 
}
function getSearchData()
{
	if(document.getElementById('locationDetails').style.display!='none')
	{
		var comboDept = document.getElementById('comboDept');
		for(var l=1; l<comboDept.options.length; l++)
		{
			if(l!=1)
				document.getElementById('department').value+=";;";
			document.getElementById('department').value += comboDept.options[l].text;
		}
		
		var comboLoc = document.getElementById('comboLoc');
		for(var l=1; l<comboLoc.options.length; l++)
		{
			if(l!=1)
				document.getElementById('location').value+=";;";
			document.getElementById('location').value += comboLoc.options[l].text;
		}
	
		/*var comboBrnch = document.getElementById('comboBrnch');
		for(var l=1; l<comboBrnch.options.length; l++)
		{
			if(l!=1)
				document.getElementById('branch').value+=";;";
			document.getElementById('branch').value += comboBrnch.options[l].text;
			document.getElementById('branch').value += ";;"+comboBrnch.options[l].value;
		}*/
	
		document.getElementById('deptSel').value = document.getElementById('comboDept')[document.getElementById('comboDept').selectedIndex].text;
		document.getElementById('locSel').value = document.getElementById('comboLoc')[document.getElementById('comboLoc').selectedIndex].text;
		/*document.getElementById('brnchSel').value = document.getElementById('comboBrnch')[document.getElementById('comboBrnch').selectedIndex].text;
		document.getElementById('brnch').value = document.getElementById('comboBrnch')[document.getElementById('comboBrnch').selectedIndex].value;*/
	}

	var locVal = document.getElementById('comboLoc')[document.getElementById('comboLoc').selectedIndex].value;
	var url = "hdiits.htm?actionFlag=inboxForDashBoard&moduleName=DashBoard&menuName=forDashBoard&locName="+locVal;
//	var url = "hdiits.htm?actionFlag=inboxForDashBoard";//&moduleName=DashBoard&menuName=forDashBoard";

	if(radioSelect=='0')
	{
		url += "&docType=3";
	}
	else if(radioSelect=='1')
	{
		url += "&docType=2";
	}
	if(document.getElementById('comboPriority').selectedIndex!=0)
	{
		var priority = document.getElementById('comboPriority')[document.getElementById('comboPriority').selectedIndex].value;
		document.getElementById('prioritySel').value = priority;
	}
//	alert(url);
	document.getElementById('dashBoardForm').action = url;
	document.getElementById('dashBoardForm').submit();
}

var deptNameLst = '${resultMap.department1}';
if(deptNameLst!='')
{
	var tempDeptNameLst = deptNameLst.split(';;');
	var comboid1 = document.getElementById('comboDept');
	if(comboid1.options.length>'0')
	{
		for(var i=comboid1.options.length; i>0; i--)
		{
			comboid1.remove(i);
		}
	}
	for (var i=0 ; i < tempDeptNameLst.length ; i++)
	{
		var element = document.createElement('option');
		element.text = tempDeptNameLst[i];
		element.value = tempDeptNameLst[i];
		
		try
	    {
	    	comboid1.add(element,null); // standards compliant
	    }
	    catch(ex)
	    {
		    comboid1.add(element); // IE only
	    }
		if('${resultMap.deptSel1}'==tempDeptNameLst[i])
		{
			comboid1.selectedIndex = i+1;
		}
	}
	
	var locNameLst = '${resultMap.location1}';
	if(locNameLst!='')
	{
		var tempLocNameLst = locNameLst.split(';;');
		var comboid2 = document.getElementById('comboLoc');
		if(comboid2.options.length>'0')
		{
			for(var i=comboid2.options.length; i>0; i--)
			{
				comboid2.remove(i);
			}
		}
		for (var i=0 ; i < tempLocNameLst.length ; i++)
		{
			var element = document.createElement('option');
			element.text = tempLocNameLst[i];
			element.value = tempLocNameLst[i];

			try
		    {
		    	comboid2.add(element,null); // standards compliant
		    }
		    catch(ex)
		    {
			    comboid2.add(element); // IE only
		    }
			if('${resultMap.locSel1}'==tempLocNameLst[i])
			{
				comboid2.selectedIndex = i+1;
			}
		}
		/*var brnchNameLst = '${resultMap.branch1}';
		if(brnchNameLst!='')
		{
			var tempBrnchNameLst = brnchNameLst.split(';;');
			var comboid3 = document.getElementById('comboBrnch');
			if(comboid3.options.length>'0')
			{
				for(var i=comboid3.options.length; i>0; i--)
				{
					comboid3.remove(i);
				}
			}
			for (var i=0; i < tempBrnchNameLst.length ; i++)
			{
				var element = document.createElement('option');
				element.text = tempBrnchNameLst[i++];
				element.value = tempBrnchNameLst[i];

				try
			    {
			    	comboid3.add(element,null); // standards compliant
			    }
			    catch(ex)
			    {
				    comboid3.add(element); // IE only
			    }
				if('${resultMap.brnchSel1}'==tempBrnchNameLst[(i-1)/2])
				{
					comboid3.selectedIndex = ((i-1)/2)+1;
				}
			}
		}*/
	}
}

if('${deptNameList}'=='[]' && '${resultMap.department1}'=='')
{
	document.getElementById('locationDetails').style.display='none';
}

document.getElementById('agerow').style.display='none';
document.getElementById('jobSelect').style.display='';
</script>
</hdiits:form>
<%
}
catch(Exception e)
{e.printStackTrace();}
%>