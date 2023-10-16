<%
try {
	

%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="actionnamelst" value="${resValue.actionnamelst}"></c:set>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<c:set var="WfModuleList" value="${resValue.WfModuleList}"></c:set>
<c:set var="Wfrunenvironment" value="${resValue.Wfrunenvironment}"></c:set>

<c:set var="LocationList" value="${resValue.cmnLocationMstList}"></c:set>

<fmt:setBundle basename="resources.workflow.FMSLables" var="fmsLables" scope="request"/>
<hdiits:form name="HierachyAlternateflowDetailsfrm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">
<hdiits:hidden name="actionFlag"  default="fms_insertalternateflowDetails"  />

<hdiits:hidden name="toNodeUpdate"  />
<hdiits:hidden name="fromNodeUpdate"  />
<hdiits:hidden name="hierachySeqId"  />
<hdiits:hidden name="hierachyRefId"  />
<hdiits:hidden name="allempoflevel"  />

<script>
function setAction()
{

	if(currentTab==0)
	{
		//isert
	
	}
	else if(currentTab==1)
	{
			//update
	}
	
	
}

/*function setActionConditioninst()
{
	//insert	
	if(currentTab==0)
	{
		return true;
	
	}
	else if(currentTab==1)
	{
		return false;
	}
	
	
}
function setActionConditioninst()
{
	//update	
	if(currentTab==0)
	{
		return false;
	
	}
	else if(currentTab==1)
	{
		return true;
	}
	
	
}	*/
</script>
<div id="tabmenu">
	<ul id="maintab" class="shadetab">
			<li class="selected"><a href="#" rel="tab1" ><hdiits:caption caption="Create" captionid=""/></a></li>
			<li ><a href="#" rel="tab2"  ><hdiits:caption caption="Update" captionid=""/></a></li>
	</ul>
			
</div>

<div class="tabcontentstyle">

<div id="tab1" class="tabcontent" tabno="0">
	

		
		<table  border="1" bordercolor="black" align="center" width="100%">
		
	
		<tr>
		<td  style="border: none" colspan="3">
		
			<b><fmt:message key="WF.AltFrom" bundle="${fmsLables}"></fmt:message></b>
			&nbsp;&nbsp;&nbsp;
			<fmt:message key="WF.FromEmp" bundle="${fmsLables}"></fmt:message>
			<hdiits:radio name="fromemprd" value="1" onclick="fromemprd_onclick()" default="1" />
			&nbsp;&nbsp;
			&nbsp;
			<fmt:message key="WF.FromLevel" bundle="${fmsLables}"></fmt:message>
			<hdiits:radio name="fromemprd" value="2"  onclick="fromlevelrd_onclick()"/>
		</td>
		
		</tr>
		<tr id="fromemptr">
		<td  style="border: none" colspan="3">
		
		
			<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
							<jsp:param name="SearchEmployee" value="AltFlowEmpSearchFromNode"/>
							<jsp:param name="formName" value="HierachyAlternateflowDetailsfrm"/>
							<jsp:param name="searchEmployeeTitle" value="Select From Employee"/>
							<jsp:param name="mandatory" value="Yes"/>
							<jsp:param name="condition" value="chkEmpValidation()"/>
							
			</jsp:include>
		</td>
		
		</tr>
		<tr>
		<c:if test="${Wfrunenvironment ne 'IFMS'}">
		<td  style="border: none" colspan="3">
		
		
			<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
							<jsp:param name="SearchEmployee" value="AltFlowEmpSearchToNode"/>
							<jsp:param name="formName" value="HierachyAlternateflowDetailsfrm"/>
							<jsp:param name="searchEmployeeTitle" value="Select To Employee"/>
			</jsp:include>
		</td>
		</c:if>
		
		</tr>
	
		<tr>
			<td colspan="3">
				<table width="100%" border="1">
				<tr>
					<td style="border: none" width="30%" >
						<fmt:message key="WF.ActionName" bundle="${fmsLables}"></fmt:message>
					</td>
					<td  style="border: none" align="left">
							<hdiits:select name="actionName" id="actionName" captionid="WF.ActionName" bundle="${fmsLables}" mandatory="true" validation="sel.isrequired"  >
							<hdiits:option value="0" selected="true">
							<hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
							<c:forEach items="${actionnamelst}" var="ActionNameLst">
								<option value='<c:out value="${ActionNameLst.altrntActnId}"/>' >
								<c:out value="${ActionNameLst.altrntActnName}" /></option>
							</c:forEach>
							</hdiits:select>
							
							<hdiits:button name="btncrtnewact" type="button" caption="Create New Action" onclick="creatnewaction()"/> 
					</td>		
		</tr>
		
		
		<tr>
					
					
						<td  style="border: none"  width="30%">
						Select Module:
						</td>
						<td  style="border: none" >
							<hdiits:select  id="module1"  name="module1" onchange="loadDocCombo(this,'Doc')">
							<hdiits:option value="Select"> Select </hdiits:option>
							<c:forEach items="${WfModuleList}" var="subWfModuleList">
							<hdiits:option value="${subWfModuleList.moduleId}"> ${subWfModuleList.moduleName}</hdiits:option>
							</c:forEach>
							</hdiits:select>
						</td>		
					
		</tr>
		<tr>
					
					
						<td  style="border: none"  width="30%">
						Select Doc:
						</td>
						<td  style="border: none" >
							<select  id="Doc"  name="Doc" onchange="getHierarchy()">
									<option value="Select"> Select </option>
							</select>
						</td>		
					
		</tr>
		
		<tr>
					
					
						<td  style="border: none"  width="30%">
							<fmt:message key="WF.SelHierachy" bundle="${fmsLables}"></fmt:message>
						</td>
						<td  style="border: none" >
							<hdiits:select  id="Hierarchy"  name="Hierarchy"  onchange="getToLevelDtl()" captionid="WF.SelHierachy" bundle="${fmsLables}" mandatory="true" validation="sel.isrequired" >
									<option value="0"> Select </option>
							</hdiits:select>
						
						</td>		
					
		</tr>
		<tr id="fromlevelid" style="display: none">
					<td  style="border: none" width="30%">
								<fmt:message key="WF.LevelId" bundle="${fmsLables}"></fmt:message>
							
					</td>
					<td  style="border: none" >
					
								<hdiits:select  id="txtlevel"  name="txtlevel"  captionid="WF.LevelId" bundle="${fmsLables}" mandatory="true" validation="sel.isrequired"  condition="validateVal()">
									<option value="0"> Select </option>
								</hdiits:select>
								
								
								
					</td>

			
		</tr>
		<tr>
					
					
						<td  style="border: none"  width="30%">
						<fmt:message key="WF.ToLevel" bundle="${fmsLables}"></fmt:message>
						</td>
						<td  style="border: none" >
							<hdiits:select  id="txttolevel"  name="txttolevel"  captionid="WF.ToLevel" bundle="${fmsLables}" mandatory="true" validation="sel.isrequired" >
									<option value="0"> Select </option>
							</hdiits:select>
							
							
						</td>		
					
		</tr>
				</table>
			</td>
		</tr>
		
		
			
	</table>
	
	
	
	
	
	
</div>

<div id="tab2" class="tabcontent" tabno="1">

<table width="100%">
<tr>	
					<td class="fieldLabel">
								Select Location:
					</td>
					<td class="fieldLabel">
							<hdiits:select  id="Location"  name="Location" onchange="loadUpdateHierarchy()">
							<hdiits:option value="Select"> Select </hdiits:option>
							<c:forEach items="${LocationList}" var="LocationList">
							<hdiits:option value="${LocationList.locationCode}"> ${LocationList.locName}</hdiits:option>
							</c:forEach>
							</hdiits:select>
					</td>
</tr>
<tr>					
					<td  style="border: none"  width="30%">
							<fmt:message key="WF.SelHierachy" bundle="${fmsLables}"></fmt:message>
					</td>
					<td  style="border: none" >
							<hdiits:select  id="updateHierachycmb"  name="updateHierachycmb"  onchange="resetActionCombo()"  >
							<option value="0"> Select </option>
							</hdiits:select>
						
					</td>	
</tr>
<tr>
					<td style="border: none" width="30%" >
						<fmt:message key="WF.ActionName" bundle="${fmsLables}"></fmt:message>
					</td>
					<td  style="border: none" align="left">
							<hdiits:select name="actionNameUpdate" id="actionNameUpdate" captionid="WF.ActionName" bundle="${fmsLables}"   onchange="getActionDetails()">
							<hdiits:option value="0" selected="true">
							<hdiits:caption captionid="WF.Select" bundle="${fmsLables}"/></hdiits:option>
							<c:forEach items="${actionnamelst}" var="ActionNameLst">
								<option value='<c:out value="${ActionNameLst.altrntActnId}"/>' >
								<c:out value="${ActionNameLst.altrntActnName}" /></option>
							</c:forEach>
							</hdiits:select>
							
							 
					</td>
</tr>
</table>		
<table id="HieAltMpgDtlTable" border="1"  width="100%" style="display: none">

<tr class="datatableheader">
	<td >
	From Employee
	</td>
	<td>
	To Level
	</td>	
	<td>
	Hierachy Name
	</td>
	<td>
	Edit
	</td>
	<td>
	Edit
	</td>
</tr>

</table>

<table  border="1" bordercolor="black" align="center" width="100%" style="display:none" id="updateTable">
		
	
		<tr>
		<td  style="border: none" colspan="3">
		
		
			<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
							<jsp:param name="SearchEmployee" value="AltFlowEmpSearchFromNodeUpdate"/>
							<jsp:param name="formName" value="HierachyAlternateflowDetailsfrm"/>
							<jsp:param name="searchEmployeeTitle" value="Select From Employee"/>
			</jsp:include>
		</td>
		
		</tr>
		<tr>
		<c:if test="${Wfrunenvironment ne 'IFMS'}">
		<td  style="border: none" colspan="3">
		
		
			<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
							<jsp:param name="SearchEmployee" value="AltFlowEmpSearchToNodeUpdate"/>
							<jsp:param name="formName" value="HierachyAlternateflowDetailsfrm"/>
							<jsp:param name="searchEmployeeTitle" value="Select To Employee"/>
			</jsp:include>
		</td>
		</c:if>
		
		</tr>
	
		<tr>
					<td  style="border: none" width="30%">
								<fmt:message key="WF.ToLevel" bundle="${fmsLables}"></fmt:message>
							
					</td>
					<td  style="border: none" >
								<hdiits:text name="txtlevelupdate" id="txtlevelupdate"  captionid="WF.LevelId" bundle="${fmsLables}" />
					</td>

			
		</tr>
		
		<tr>
					
					
						<td  style="border: none"  width="30%">
						Select Module:
						</td>
						<td  style="border: none" >
							<hdiits:select  id="module1update"  name="module1update" onchange="loadDocCombo(this,'Docupdate')">
							<hdiits:option value="Select"> Select </hdiits:option>
							<c:forEach items="${WfModuleList}" var="WfModuleList">
							<hdiits:option value="${WfModuleList.moduleId}"> ${WfModuleList.moduleName}</hdiits:option>
							</c:forEach>
							</hdiits:select>
						</td>		
					
		</tr>
		<tr>
					
					
						<td  style="border: none"  width="30%">
						Select Doc:
						</td>
						<td  style="border: none" >
							<select  id="Docupdate"  name="Docupdate" onchange="getHierarchy('2')">
									<option value="Select"> Select </option>
							</select>
						</td>		
					
		</tr>
		<tr>
					
					
						<td  style="border: none"  width="30%">
						Select Hierarchy:
						</td>
						<td  style="border: none" >
							<select  id="Hierarchyupdate"  name="Hierarchyupdate" >
									<option value="0"> Select </option>
							</select>
							
							
						</td>		
					
		</tr>
	</table>
	<center>
	<hdiits:button name="btnupdate" type="button" onclick="updateData()" caption="Update " style="display:none"/>	
	</center>
	

</div>



<jsp:include page="../core/tabnavigation.jsp" />
</div>
<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
		<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>'/>	
		
</hdiits:form>
<script>
function resetActionCombo()
{
	document.getElementById('actionNameUpdate').value=0;
}
function loadUpdateHierarchy()
{

	
	locCode =document.getElementById('Location').value;
	hie1=document.getElementById('updateHierachycmb');
	
		 var url = "hdiits.htm?actionFlag=FMS_getHiearchyForDocForAltFlow&locCode="+locCode;

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
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc!=null)
					{
						var tableentries = XMLDoc.getElementsByTagName('HerachyDetail');	
						
						for ( var i = 0 ; i < tableentries.length ; i++ )
	     				{
	     					
							HiearchyId=tableentries[i].childNodes[0].text;
							HierachyName=tableentries[i].childNodes[1].text;
	     					var element=document.createElement('option');
		     				
		     			
		     				element.text=HierachyName
		     				element.value=HiearchyId
		     				try
						    {
		     					hie1.add(element,null); // standards compliant
						    }
						    catch(ex)
						    {
						    	hie1.add(element); // IE only
						    }
	     				}
					}		
					
					
					  
				}
					
					  
			}
		}
	    xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	
}

function validateVal()
{
		if(document.getElementById('fromlevelid').style.display=='none')
			return false;
		else 
			return true;
}

function getToLevelDtl()
{

	
	var hierachyRefId=document.getElementById('Hierarchy').value;
	var levelcmbid=document.getElementById('txtlevel');
	var tolevelcmbid=document.getElementById('txttolevel');
		
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
	       		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	       		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
	   	}	
		var url;
		url = "hdiits.htm?actionFlag=FMS_getHiearchyLevelDetails&Hierarchy="+hierachyRefId;	
			
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					var Category = XMLDoc.getElementsByTagName('HerachyLevelDetail');
					
					
					if(levelcmbid.options.length>'0')
					{
						for(var i=levelcmbid.options.length;i>0;i--)
						{levelcmbid.remove(i);}
					}
					if(tolevelcmbid.options.length>'0')
					{
						for(var i=tolevelcmbid.options.length;i>0;i--)
						{tolevelcmbid.remove(i);}
					}
					for (var i=0 ; i < Category.length ; i++ )
					{
						levelNo = Category[i].childNodes[0].text;							
	  					var element = document.createElement('option');   	
	  					element.text = levelNo;
	     				element.value = levelNo;
	     				var element1 = document.createElement('option');   	
	  					element1.text = levelNo;
	     				element1.value = levelNo;
	     				try
					    {
						    
	     					levelcmbid.add(element,null); // standards compliant
	     					tolevelcmbid.add(element1,null);
	     					
					    }
					    catch(ex)
					    {
					    	
					    	levelcmbid.add(element);
					    	tolevelcmbid.add(element1);// IE only
					    }
					} 
				

					
				}
				
			}
	    }
		xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	
}
function fromemprd_onclick()
{
	
	document.getElementById('allempoflevel').value="";
	if(document.getElementById('fromemptr').style.display=='none')
	{
		document.getElementById('fromemptr').style.display='';
	}
	if(document.getElementById('fromlevelid'))
	{
		document.getElementById('fromlevelid').style.display='none';
	}
	
}
function fromlevelrd_onclick()
{
	document.getElementById('allempoflevel').value=1;
	if(document.getElementById('fromlevelid').style.display=='none')
	{
		document.getElementById('fromlevelid').style.display='';
	}
	if(document.getElementById('fromemptr').style.display=='')
	{
		document.getElementById('fromemptr').style.display='none';
	}
}
function toggle(x){

	var HieAltMpgDtlTable = document.getElementById('HieAltMpgDtlTable');

	for(i=1;i<HieAltMpgDtlTable.rows.length;i++)
	{
		HieAltMpgDtlTable.rows[i].style.backgroundColor ="white"
	}
		
	x.parentNode.parentNode.style.backgroundColor ="#B6BAD5"
}
function updateData()
{

	
	document.getElementById('actionFlag').value="FMS_updateAltHiearchyMpgDetails";
	document.forms[0].method='post';
	document.forms[0].submit();
}
function creatnewaction()
{
	var url = "${contextPath}/hdiits.htm?viewName=wf-crtNewAlternateAction";
	window.open(url, '', 'status=no,toolbar=no,scrollbars=no,width=800,height=200,top=150,left=200');		
}

function loadDocCombo(Obj,id)
{
	var comboid = document.getElementById(id);
	var moduleIdCombo= document.getElementById('module1');
	
	
	
	
		
	if(Obj.selectedIndex!='0')
	{
		
		Code = Obj[Obj.selectedIndex].value;
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
	       		          xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
	       		  }
			      catch (e)
			      {
			              alert("Your browser does not support AJAX!");        
			              return false;        
			      }
			 }
	   	}	
		var url;
		url = "hdiits.htm?actionFlag=getDocsForLocation&moduleId="+Code+"&flag=1";	
			
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					var Category = XMLDoc.getElementsByTagName('Doc');
					if(Category.length==0)
					{
					
						alert("No Docs found...");
						return;
					}
					if(comboid.options.length>'0')
					{
						for(var i=comboid.options.length;i>0;i--)
						{comboid.remove(i);}
					}
					
					for (var i=0 ; i < Category.length ; i++ )
					{
						DocId = Category[i].childNodes[0].text;							
	  					DocName = Category[i].childNodes[1].text;	   						
	  					var element = document.createElement('option');   				
	     				element.text = DocName;
	     				element.value = DocId;	
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
}
function getHierarchy(checkflag)
{
	var hie1;
	var docObj;

	
	if(checkflag!='2')
	{
		
		hie1=document.getElementById('Hierarchy');
		docObj=document.getElementById('Doc');
	}
	else
	{
		
		hie1=document.getElementById("Hierarchyupdate");
		docObj=document.getElementById('Docupdate');
	}	
	
	for(cnt=1;cnt<hie1.length;cnt++)
	{
		hie1.removeChild(hie1.options[cnt]);
	}
	
	if(docObj.selectedIndex!='0')
	{
		docId = docObj[docObj.selectedIndex].value;
		 var url = "hdiits.htm?actionFlag=FMS_getHiearchyForDocForAltFlow&docId="+docId;

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
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc!=null)
					{
						var tableentries = XMLDoc.getElementsByTagName('HerachyDetail');	
						
						for ( var i = 0 ; i < tableentries.length ; i++ )
	     				{
	     					
							HiearchyId=tableentries[i].childNodes[0].text;
							HierachyName=tableentries[i].childNodes[1].text;
	     					var element=document.createElement('option');
		     				
		     			
		     				element.text=HierachyName
		     				element.value=HiearchyId
		     				try
						    {
		     					hie1.add(element,null); // standards compliant
						    }
						    catch(ex)
						    {
						    	hie1.add(element); // IE only
						    }
						    
	     				}
						
	     				
					}		
				}
			}
		}
	    xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	}

	
}
function viewDtl(HierSeqId,FromNode,LevelId,HierachyRefId,trow)
{

	
	toggle(trow);
	document.getElementById('updateTable').style.display='';
	document.getElementById('btnupdate').style.display='';	
	document.getElementById('fromNodeUpdate').value=FromNode
	document.getElementById('hierachySeqId').value=HierSeqId
	document.getElementById('hierachyRefId').value=HierachyRefId
	document.getElementById('txtlevelupdate').value=LevelId
	
}
function getActionDetails()
{

	
	var altactionId= document.getElementById('actionNameUpdate').value;

	var hierachyrefid=document.getElementById('updateHierachycmb').value;
	
	document.getElementById('HieAltMpgDtlTable').style.display='';
	var HieAltMpgDtlTable = document.getElementById('HieAltMpgDtlTable');

	while (HieAltMpgDtlTable.rows.length > 1 ) {
		HieAltMpgDtlTable.deleteRow(HieAltMpgDtlTable.rows.length-1);
	}

	 var url = "hdiits.htm?actionFlag=FMS_getAltHiearchyMpgDetails&altactionId="+altactionId+"&hierachyrefid="+hierachyrefid;
	  var trow;
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
		xmlHttp.onreadystatechange = function()
		{
		
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					
					var XMLDoc=xmlHttp.responseXML.documentElement;
					if(XMLDoc!=null)
					{
						var tableentries = XMLDoc.getElementsByTagName('HerachyMpgDetail');	
						for ( var i = 0 ; i < tableentries.length ; i++ )
	     				{
	     					
							HierSeqId=tableentries[i].childNodes[0].text;
							FromNode=tableentries[i].childNodes[1].text;
							FromEmp=tableentries[i].childNodes[2].text;
							ToNode=tableentries[i].childNodes[3].text;
							ToEmp='&nbsp'+tableentries[i].childNodes[4].text;
							LevelId=tableentries[i].childNodes[5].text;
							ClassName='&nbsp'+tableentries[i].childNodes[6].text;
							MethodName='&nbsp'+tableentries[i].childNodes[7].text;
							HierachyName=tableentries[i].childNodes[8].text;

							HierachyRefId=tableentries[i].childNodes[9].text;
	     							     				
							trow=HieAltMpgDtlTable.insertRow();
							trow.insertCell(0).innerHTML = 	FromEmp;	
							//trow.insertCell(1).innerHTML = 	ToEmp;
							trow.insertCell(1).innerHTML = 	LevelId;

							
							//trow.insertCell(3).innerHTML = 	ClassName;
							//trow.insertCell(4).innerHTML = 	MethodName;
							trow.insertCell(2).innerHTML = 	HierachyName;
								
							var a=trow.insertCell(3);
							a.innerHTML = 	"<a href=# onclick=\"viewDtl('"+HierSeqId+"','"+FromNode+"','"+LevelId+"','"+HierachyRefId+"',this)\">Update</a>";
							 
							
							var b=trow.insertCell(4);
							b.innerHTML = 	"<a href=# onclick=\"deleteRow('"+HierSeqId+"')\">Delete</a>";
	     				}
					}		
				}
			}
		}
	    xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	
}

function add(txt,val)
{
    
    var newOpt = document.getElementById('actionName').appendChild(document.createElement('option'));
    newOpt.text = txt;
    newOpt.value = val;
}
function deleteRow(hierseqid)
{
	var urldelete = "${contextPath}/hdiits.htm?actionFlag=FMS_deleteAltFlowDetails&hierachySeqId="+hierseqid;
	if(confirm("Do You want to delete this details"))
	{
		window.open(urldelete, '', 'status=no,toolbar=no,scrollbars=no,width=800,height=200,top=150,left=200');
	}
	
}	

function chkEmpValidation()
{
	
			
			if(document.getElementById('fromemptr').style.display=='none')
				return false;
			else 
				return true;
	
		
}


</script>
<%
}
catch(Exception e)
{
	e.printStackTrace();
}
%> 