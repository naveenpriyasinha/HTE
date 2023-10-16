<%try {
%>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="<c:url value="script/common/tabcontent.js"/>"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="deptName" value="${resValue.depName}"></c:set>
<c:set var="contextPath" scope="request">${pageContext.request.contextPath}</c:set>
<c:set  var="loginLocId" value="${param.loginLocId}"></c:set>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<hdiits:form name="LocationBranchForm" method="POST" action="./hdiits.htm" encType="multipart/form-data" validate="true"  >
<c:set var="deptId" value="${param.deptId}"></c:set>
<hdiits:hidden name="SendAsCorr" default="${param.SendAsCorr}" />
<hdiits:hidden name="fileId" default="${param.fileId}" />
<hdiits:hidden name="mehodFlag" id="mehodFlag" default="${param.methodFlag}" />

<hdiits:hidden name="otherSender" default="${param.otherSender}" />
<hdiits:hidden name="postIdArray" id="id_postArray"/>
<hdiits:hidden name="departmentId" default="${deptId}"/>
<hdiits:hidden name="showTable" id="showTable" default="${param.urlupd}"/>
<hdiits:hidden name="grpId" default="${param.groupId}"/>
<hdiits:hidden name="postIdLst" id="pidLst"/>
<hdiits:hidden name="txt_srchDept"/>
<hdiits:hidden name="crt_sel"/>
<center><h5><a onmouseover="style.cursor='hand'" href="#"  onclick="javascript:openDeptCrtSrchPage()"><fmt:message bundle="${fmsLables}" key="WF.SELOTDEPT"></fmt:message> </a></h5></center>
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
		    <li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="WF.LOCBRN" bundle="${fmsLables}"/></a></li>
		</ul>
	</div>
	<div class="tabcontentstyle">
		<div id="tcontent1" class="tabcontent" tabno="0">
		<table width="100%">
				<tr id="exact_id">
					<td><nobr><fmt:message bundle="${fmsLables}" key="WF.Name"></fmt:message>&nbsp;<input type="text" id="empF_id" name="empFName"><fmt:message bundle="${fmsLables}" key="WF.MiddleName"></fmt:message>&nbsp;<input type="text" id="empM_id" name="empMName"><fmt:message bundle="${fmsLables}" key="WF.LastName"></fmt:message>&nbsp;<input type="text" id="empL_id" name="empLName"></nobr></td>
				</tr>
				<tr id="pattern_id" style="display:none">
					<td><fmt:message bundle="${fmsLables}" key="WF.Name"></fmt:message>&nbsp;<input type="text"  id="emp_id" name="empName"></td>
				</tr>
				<tr>
				<td><input type="radio" value="0" name="rbutton" id="exactname_id" checked="checked" onclick="checkForEmpSearch('Exact')"><fmt:message bundle="${fmsLables}" key="WF.ExactSearch"></fmt:message> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" value="1"  id="anywhere_id" name="rbutton" onclick="checkForEmpSearch('Pattern')"><fmt:message bundle="${fmsLables}" key="WF.PatternSearch"></fmt:message></td>
				</tr>
				<tr>
				<td><input type="radio" value="0" name="rbutton_loc" id="defaultloc_id" checked="checked"><fmt:message bundle="${fmsLables}" key="WF.ExtLoc"></fmt:message> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="radio" value="1"  id="subsequantLoc_id" name="rbutton_loc"><fmt:message bundle="${fmsLables}" key="WF.SubSeqLoc"></fmt:message></td>
				</tr>
				<tr>
				<td align="center">
					<hdiits:button name="button" type="button" captionid="WF.Search" bundle="${fmsLables}" onclick="SearchEmpl()"/>
				</td>
				</tr>
			</table>
			<c:if test="${otherSender eq 'yes'}">
				<c:set var="frameDisplay" value="display:none"></c:set>
			</c:if>
			<c:if test="${otherSender ne 'yes'}">
				<c:set var="frameDisplay" value=""></c:set>
			</c:if>
			<iframe  name="locationList" id="location_id"  src="" width="100%" height=""  frameborder="1" scrolling="auto"> 
			</iframe>
			
			<c:if test="${param.urlupd eq 'true'}">
				<iframe name="displayEmpFrame" id="empFrame_id"  src="" width="100%" height="" frameborder="1" style="${frameDisplay}" scrolling="yes">
				</iframe>
			</c:if>
			<c:if test="${param.urlupd ne 'true'}">
				<iframe name="displayEmpFrame" id="empFrame_id"  src="" width="100%" height="" frameborder="1" style="${frameDisplay}" scrolling="no">
				</iframe>
			</c:if>
			 
		</div>
	</div>
	<script type="text/javascript"> 
		document.getElementById('location_id').src = "${contextPath}/hdiits.htm?actionFlag=loadLocFromDepts&departmentId="+'${deptId}';
		
		if(parent.document.getElementById('showTable').value == 'true')
		{
			document.getElementById('empFrame_id').src = "hdiits.htm?actionFlag=populatePrfdLst&updflag=true&groupId=${param.groupId}";
		}
		else if('${otherSender}' != 'yes')
		{
			document.getElementById('empFrame_id').src = "${contextPath}/hdiits.htm?viewName=wf-MarkedEmpList";
		}
	</script>
	<script type="text/javascript"> 
	
	var postIdArray = new Array();
	var empArray = new Array();
	var desgnArray = new Array();
	var locArray = new Array();
	var addedEmpArray_value = new Array();
	var addedEmpArray_text = new Array();
	function addEmps(){
		window.opener.document.getElementById('postIdArray').value=postIdArray;
		window.opener.document.getElementById('empArray').value=empArray;
		window.opener.document.getElementById('locArray').value=locArray;
		window.opener.document.getElementById('desgnArray').value=desgnArray;
		
		window.opener.useSelectedEmployees();
		window.close();
	
	}
	function forwardDoc()
	{
		if(window.opener.document.getElementById('fromPreferredList') != null)
		{
			if(window.opener.document.getElementById('fromPreferredList').value)
			{		
				addEmps();
				return;
			}
		}
		
		var SendAsCorr=document.getElementById('SendAsCorr').value
		var fileId=document.getElementById('fileId').value
		
		if(SendAsCorr=="yes")
		{
			var comboright=document.frames['displayEmpFrame'].document.getElementById('dd_choice2_id');
			if(comboright.length>1)
			{
				alert("Select Only One Employee");
			}
			else
			{
				document.getElementById("LocationBranchForm").method="post";
				document.getElementById("LocationBranchForm").action='${contextPath}/hdiits.htm?actionFlag=SendFileAsCorrespondece&fileId='+fileId+'&postId='+postIdArray[0];	
				document.getElementById("LocationBranchForm").submit();
			}
		}
		else
		{	
			if(window.opener.document.getElementById("markedList").value==''){
				alert('<fmt:message key="WF.CNTFWDMSG"  bundle="${fmsLables}"/>')
				return 
			}
					
			//alert(document.getElementById('mehodFlag').value);
			if(document.getElementById('mehodFlag').value=='forward')
				eval(window.opener.forward());
			else
				eval(window.opener.sendToMany());
			window.close();	
		}
		
		if(childWindow != null)
			childWindow.close();
	}

	function closeWindow()
	{
		if(confirm('<fmt:message key="WF.EXMSG"  bundle="${fmsLables}"/>'))
		{
			window.close();			
		}
	}
	
	function backtoSearchEmp()
	{
		var cntr;
		
		if(locArray.length > 0)
		{
			locArray = new Array();
		}
		if(empArray.length > 0)
		{
			empArray = new Array();
		}
		if(desgnArray.length > 0)
		{
			desgnArray = new Array();
		}
		if(postIdArray.length > 0)
		{
			postIdArray = new Array();
		}
		document.getElementById('location_id').src = "${contextPath}/hdiits.htm?actionFlag=loadLocFromDepts&departmentId="+'${deptId}';
		
	} 
	
		var cnt=0;
	function addEmpinTable(checkstatus,splicedpost)
	{
		if(checkstatus=="true")
		{
			if(confirm("Are You Sure want to add?"))

			{
//				var tab_ref = document.forms['EmpLocForm'].document.getElementById("row");
				
				// rows[0].cells[0].value);
				var flag=false;
				var data = document.frames['locationList'].document.getElementById(splicedpost).value;
				var temp1 = data.split(","); 
				var row=document.frames['displayEmpFrame'].document.getElementById("tab_new").insertRow();
//				row.setAttribute("id",cnt++);
				addPrfdUsr(splicedpost, document.getElementById('grpId').value);
				var cell1=row.insertCell(0);
				var cell2=row.insertCell(1);
				var cell3=row.insertCell(2);
				var cell4=row.insertCell(3);
				var cell5=row.insertCell(4);
				var cell6=row.insertCell(5);
				cell1.innerHTML=temp1[0];
				cell2.innerHTML=temp1[1];
				cell3.innerHTML=temp1[3];
				cell4.innerHTML=temp1[2];
				cell5.innerHTML=temp1[4];
			    cell6.innerHTML='<a href="javaScript:delete_me('+ splicedpost +', ' + document.getElementById('grpId').value + ')">Delete</a>';
			}
			else
			{
				document.frames['locationList'].document.getElementById(splicedpost).checked=false;
			}
		}
		else if(checkstatus=="false")
		{
			if(confirm("Are You Sure want to delete?"))
			{
			var tab_ref = document.frames['displayEmpFrame'].document.getElementById("tab_new");
			for(var i=1;i<tab_ref.rows.length;i++)
			{
				if(tab_ref.rows[i].cells[0].innerHTML == splicedpost)
				{
					tab_ref.deleteRow(i);
				}
			}
			document.frames['displayEmpFrame'].ajaxDelete(splicedpost, document.getElementById('grpId').value);
			}
			else
			{
				document.frames['locationList'].document.getElementById(splicedpost).checked=true;
			}
		}
	}
	function addPrfdUsr(splicedpost, groupId)
	{
		try
    	{   
    	// Firefox, Opera 8.0+, Safari    
    		xmlHttp=new XMLHttpRequest();    
    	}
		catch (e)
		{    // Internet Explorer    
			try
      		{
      			
      		    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");   
      		   
      		}
		    catch (e)
		    {
		          try
        		  {
                	      //alert("here2");
        		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
    	}	
	
		//alert("deparmentId"+deptId);
		//alert("locVal"+locVal);
		
		var url = "hdiits.htm?actionFlag=updatePrfdLst&postIdLst="+splicedpost+"&groupId="+groupId;  
			
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{
				}
			}
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));	
	}
	
	function addEmpinCombo(checkstatus,splicedpost)
	{
		if('${otherSender}' != 'yes')
		{
			var combo_left=document.frames['displayEmpFrame'].document.getElementById('dd_choice1_id');
			if(checkstatus=="true")
			{
				if(empArray.length>0)
				{
						var element=document.createElement('option');
						element.text=empArray[empArray.length-1]+", "+desgnArray[empArray.length-1];
						element.value=postIdArray[postIdArray.length-1];
						combo_left.add(element);
						combo_left.removeAttribute("style");
						if(parseInt(combo_left.size) > 3)
						combo_left.size=parseInt(combo_left.size)+1;
				}
			}
			
			else if(checkstatus=="false")
			{
				for(var cnt1=0;cnt1<combo_left.length;cnt1++)
					{
						
						if(combo_left.options[cnt1].value == splicedpost)
						{
							combo_left.removeChild(combo_left.options[cnt1]);
							if(parseInt(combo_left.size)>3)
								combo_left.size=parseInt(combo_left.size)-1;
						}
					}
					
			}
		}
		//document.getElementById('empFrame_id').src = "hdiits.htm?viewName=wf-MarkedEmpList&empArray="+empArray+"&postIdArray="+postIdArray+"&desgnArray="+desgnArray;
	}
	</script>
	<script type="text/javascript">
	function SearchEmpl()
	{
		var radButtonId,radButId_locsrch;
		radButtonId = document.getElementById('exactname_id').value;
		if(document.getElementById('exactname_id').checked)
		{
			  radButtonId = document.getElementById('exactname_id').value;
			  if(document.getElementById('defaultloc_id').checked)
			  {
			  	radButId_locsrch = document.getElementById('defaultloc_id').value;
			  }
			  else if(document.getElementById('subsequantLoc_id').checked)
			  {
			  	radButId_locsrch = document.getElementById('subsequantLoc_id').value;
			  }
			  var empFName = document.getElementById('empF_id').value;
			  var empMName = document.getElementById('empM_id').value;
			  var empLName = document.getElementById('empL_id').value;
			  if(locArray.length == 0)
			  {
			  	alert('<fmt:message key="WF.SELLOCMSG"  bundle="${fmsLables}"/>');
			  	return false;
			  }
			  else
			  {
			  	eval(ExactEmpSearch(empFName,empMName,empLName,radButtonId,locArray,radButId_locsrch));
			  }
		}
		else if(document.getElementById('anywhere_id').checked)
		{
			 radButtonId = document.getElementById('anywhere_id').value;
			 var empName = document.getElementById('emp_id').value;
			  if(locArray.length == 0)
			  {
			  	alert("Please select any one location");
			  	return false;
			  }
			  else
			  {
			   	  if(document.getElementById('defaultloc_id').checked)
				  {
				  	radButId_locsrch = document.getElementById('defaultloc_id').value;
				  }
				  else if(document.getElementById('subsequantLoc_id').checked)
				  {
				  	radButId_locsrch = document.getElementById('subsequantLoc_id').value;
				  }
			 	eval(PatternEmpSearch(empName,radButtonId,locArray,radButId_locsrch));
			  }
		}
		//alert(radButtonId);
		//var locations = document.getElementsByName('check').value;
		//alert(locArray);
		
	}
	
	
	function ExactEmpSearch(empFName,empMName,empLName,radButtonId,locArray,radButId_locsrch)
	{
	
		document.getElementById("location_id").src = "${contextPath}/hdiits.htm?actionFlag=empSearch&EmpDecFlag=Y&empFName="+empFName+"&empMName="+empMName+"&empLName="+empLName+"&rbutton="+radButtonId+"&check="+locArray+"&radButId_locsrch="+radButId_locsrch;
		/*alert(postIdArray.length);
		alert(empArray.length);*/
	}
	function PatternEmpSearch(empName,radButtonId,locArray,radButId_locsrch)
	{
		document.getElementById("location_id").src = "${contextPath}/hdiits.htm?actionFlag=empSearch&EmpDecFlag=Y&empName="+empName+"&rbutton="+radButtonId+"&check="+locArray+"&radButId_locsrch="+radButId_locsrch;
		/*alert(postIdArray.length);
		alert(empArray.length);*/
	}
	
	function GetBranchsFromLoc(radButIdForBranchEmpDecision)
	{
		document.getElementById('location_id').src = "${contextPath}/hdiits.htm?actionFlag=empSearch&EmpDecFlag=N&check="+locArray+"&radButIdForBranchEmpDecision="+radButIdForBranchEmpDecision;
	}
	
	function checkForEmpSearch(searchParam)
	{
		if(searchParam == 'Exact')
		{
			document.getElementById('exact_id').style.display = '';
			document.getElementById('pattern_id').style.display = 'none';
		}
		else if(searchParam == 'Pattern')
		{
			document.getElementById('pattern_id').style.display = '';
			document.getElementById('exact_id').style.display = 'none';
		}
	}
</script>
	<table align="center">
		<tr>
	    	<td>
	    		<a href='javascript:openPreferredList()'>Select Employees From Preferred List</a>
	    	</td>
	    </tr>
	    <tr>
	    	<td>
	    		<hdiits:button type="button" name="btnEight" id="btn8" onclick="closeWindow()" captionid="WF.Cancel" bundle="${fmsLables}" style="width: 75px"/>
	    	</td>
	    </tr> 
    </table>
	<script type="text/javascript"> 
				//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
			initializetabcontent("maintab")
	</script> 
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>		
			
</hdiits:form>

 <SCRIPT LANGUAGE="JavaSCRIPT">
 var childWindow;
 function openPreferredList()
	{
		var urlstyle="location=0,status=0,scrollbars=1,width=600,height=600";
		var url = "hdiits.htm?actionFlag=populatePrfdLst&sendToPreferredList=false";
		childWindow = window.open (url,'',urlstyle); 
	}
   function msieversion()
   {
   	  try
   	  {
	      var ua = window.navigator.userAgent
	      var msie = ua.indexOf ( "MSIE " )
	  
	      if ( msie > 0 )      // If Internet Explorer, return version number
	         return parseInt (ua.substring (msie+5, ua.indexOf (".", msie )))
	      else                 // If another browser, return 0
	         return 0
	  }
	  catch(e)
	  {
	  }
	}
    var whichVer = 	msieversion();
	if(parseInt(whichVer) > 6)
	{
		document.getElementById("location_id").height = 470; 
		document.getElementById("empFrame_id").height = 200;
	}
	else
	{
		document.getElementById("location_id").style.height = "70%";
		document.getElementById("empFrame_id").style.height = "30%";
	}


	if('${loginLocId}'!=''){
		locArray.push('${loginLocId}');
		ExactEmpSearch('','','','0',locArray,'1');
	}


	function openDeptCrtSrchPage(){
		var url='hdiits.htm?actionFlag=fms_showDeptCrtPage&otherSender=${otherSender}&upd=${param.urlupd}&groupId=${param.groupId}&urlupd=${param.urlupd}';
		var urlStyle = 'width=500,height=300,toolbar=no,status=yes,menubar=no,location=no,scrollbars=no,top=150,left=200';
		window.open(url,'',urlStyle);
		
	}
</SCRIPT>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
