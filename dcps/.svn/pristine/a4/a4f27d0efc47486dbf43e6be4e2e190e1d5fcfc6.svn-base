<%try{ %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>


<style type="text/css">
#popupcontent
{   position: absolute;   
    visibility: hidden;   
    overflow: hidden;   
    border:1px solid #CCC;   
   
    padding:5px;
}
#popupcontent2
{   position: absolute;   
    visibility: hidden;   
    overflow: hidden;   
    border:1px solid #CCC;   
   
    padding:5px;
}
#popupcontent3
{   position: absolute;   
    visibility: hidden;   
    overflow: hidden;   
    border:1px solid #CCC;   
   
    padding:5px;
}

#popupcontent4
{   position: absolute;   
    visibility: hidden;   
    overflow: hidden;   
    border:1px solid #CCC;   
   
    padding:5px;
}
#popupcontent5
{   position: absolute;   
    visibility: hidden;   
    overflow: hidden;   
    border:1px solid #CCC;   
   
    padding:5px;
}
div.transOFF {background-color: white; position: absolute;   }
div.transON  {background-color: gray;opacity:.70;filter: alpha(opacity=50); -moz-opacity: 0.5; position: absolute;   }

</style>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="Message" value="${resValue.Message}"></c:set>
<c:set var="LocationList" value="${resValue.cmnLocationMstList}"></c:set>
<c:set var="WfModuleList" value="${resValue.WfModuleList}"></c:set>
<c:set var="WfModuleList2" value="${resValue.WfModuleList}"></c:set>
<c:set var="Wfrunenvironment" value="${resValue.Wfrunenvironment}"></c:set>
<c:set var="srNo_og" value="${1}" />



<script language="javascript">
if(('${Message}'!='')&&('${Message}'!=null))
{
	alert('${Message}');
	
}
</script>
<hdiits:form name="subjectForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">

<br />
<br />
<hdiits:hidden name="Wfrunenvironment" default="${resValue.Wfrunenvironment}"/>

	<table class="tabtable">
	
			<tr>
				<td class="datatableheader" colspan="4" >
					Get Hierarchy Details
				</td>
				
			</tr>
			<c:if test="${Wfrunenvironment eq 'IFMS'}">
			<tr>
				<td class="fieldLabel1">
					Select Module:
				</td>
				<td class="fieldLabel1">
					<hdiits:select  id="module1"  name="module1" onchange="loadDocCombo(this,'Doc')">
					<hdiits:option value="Select"> Select </hdiits:option>
					<c:forEach items="${WfModuleList}" var="WfModuleList">
					<hdiits:option value="${WfModuleList.moduleId}"> ${WfModuleList.moduleName}</hdiits:option>
					</c:forEach>
					</hdiits:select>
				</td>
				</tr>
				</c:if>
				
			<tr>
			<c:if test="${Wfrunenvironment eq 'HOME'}">
				<td class="fieldLabel">
					Select Location:
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="Location"  name="Location" onchange="loadDocCombo(this,'Doc')">
					<hdiits:option value="Select"> Select </hdiits:option>
					<c:forEach items="${LocationList}" var="LocationList">
					<hdiits:option value="${LocationList.locationCode}"> ${LocationList.locName}</hdiits:option>
					</c:forEach>
					</hdiits:select>
				</td>
			</c:if>
			<c:if test="${Wfrunenvironment eq 'IFMS'}">
				<td class="fieldLabel">
					Select Location:
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="Location"  name="Location" >
					<hdiits:option value="Select"> Select </hdiits:option>
					<c:forEach items="${LocationList}" var="LocationList">
					<hdiits:option value="${LocationList.locationCode}"> ${LocationList.locName}</hdiits:option>
					</c:forEach>
					</hdiits:select>
				</td>
			</c:if>
				<td class="fieldLabel">
					Select Doc:
				</td>
				<td class="fieldLabel">
					<select  id="Doc"  name="Doc" onchange="getHierarchy()">
					<option value="Select"> Select </option>
					</select>
				</td>
			</tr>
		
			<tr id="refId" style="display: none">
							<td class="fieldLabel" >
					Select Hierarchy name:
				</td>
				<td class="fieldLabel">
					<select  id="refName"  name="refName" onchange="getHierarchyDtls()">
					<option value="Select"> Select </option>
					</select>
				</td>
				</tr>	
				
			
			<tr id="brow" style="display: none">
			<td class="fieldLabel">		
					Select Branch:
			</td>
			<td class="fieldLabel">		
					<select  id="Branch"  name="Branch" onchange="getHierarchyForBranch()" >
					<option value="Select"> Select </option>
					</select>
			</td>
			<td class="fieldLabel">		
					
			</td>
			<td class="fieldLabel">		
					
			</td>
			</tr>
			
			
		</table>
		<br />
		
		<table id="hrow" style="display: none" class="tabtable">
		<tr>
				<td class="datatableheader" colspan="4" >
					Map to existing hierarchy
				</td>
				
			</tr>
		<tr>
			<td class="fieldLabel">		
					Select Hierarchy:
			</td>
			<td class="fieldLabel">		
					<select  id="HierarchyC"  name="HierarchyC" onchange="if(this.selectedIndex!=0){document.forms[0].MapHieName.value=this[this.selectedIndex].text;} else{document.forms[0].MapHieName.value='';}">
					<option value="Select"> Select </option>
					</select>
			</td>
			<td class="fieldLabel">Hierarchy Name:</td>
			<td class="fieldLabel"><hdiits:text name="MapHieName" id="MapHieName" />	</td>
		</tr>
		<tr  >
			
			<td class="fieldLabel" colspan="4" align="center">		
					<hdiits:button name="SubmitHie" id="SubmitHie" type="button" onclick="submitHierarchy()" value="Submit"></hdiits:button>
			</td>
		
			</tr>	
		</table>
	
	<br />
			
				<div id="employeeSearchId" style="display:none">
					<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp" >
						<jsp:param name="SearchEmployee" value="EMPLOYEESEARCH"/>
						<jsp:param name="formName" value="testing"/>
						<jsp:param name="mandatory" value="No"/>
						<jsp:param name="multiEmployeeSel" value="Yes"/>
						<jsp:param name="functionName" value="callShowPopup"/>
						<jsp:param name="searchEmployeeTitle" value="Select Employees to add in hierarchy"/>
					</jsp:include>
					<br>
					<table class="tabtable">
						
						<tr id="NewHname" style="display: none;">
							<td class="fieldLabel">		
									Enter Hierarchy Name:
							</td>
							<td class="fieldLabel">		
									<hdiits:text name="HieName" id="HieName" />					
							</td>
						</tr>
						<tr id="NewHDesc" style="display: none;">
							<td class="fieldLabel">		
									Enter Hierarchy Description:
							</td>
							<td class="fieldLabel">									
									<hdiits:select name="HieDesc" id="HieDesc">
										<hdiits:option value="-1">--Select--</hdiits:option>
										<hdiits:option value="Bill Processing">Bill Processing</hdiits:option>
										<hdiits:option value="TC Bill Processing">TC Bill Processing</hdiits:option>
										<hdiits:option value="Challan Processing">Challan Processing</hdiits:option>
										<hdiits:option value="Online Bill Creation">Online Bill Creation</hdiits:option>
										<hdiits:option value="PensionBill Processing">PensionBill Processing</hdiits:option>
										<hdiits:option value="PensionCase Processing">PensionCase Processing</hdiits:option>
										<hdiits:option value="Nill PensionBill Processing">Nill PensionBill Processing</hdiits:option>
										<hdiits:option value="MRCaseWFlow">MRCaseWFlow</hdiits:option>
										<hdiits:option value="GPF">GPF</hdiits:option>
									</hdiits:select>				
							</td>
						</tr>
						<tr>
							<td class="fieldLabel" style="display:none;">		
									Enter due days for all employees:
							</td>
							<td class="fieldLabel" style="display:none;">		
									<input id="CopyAll" type="text" onkeyup="copyDueDays(this)" />					
							</td>
							
						</tr>
						<c:if test="${Wfrunenvironment eq 'IFMS'}">
						<tr>
							<td class="fieldLabel1">
								<a href="#" onmouseover="" onclick="showPopup(500,130,2);">Select Parent Hierarchy</a>
							</td>
							</tr>
						</c:if>
					</table>
					<br>
					
								
					<table id="newEmpTable" class="datatable" style="display: none;">
							
							<tr>
								<td class="datatableheader" style="display:none;"></td>
								<td class="datatableheader" colspan="5"></td>
							</tr>
							<tr>
								<td class="datatableheader" style="display:none;">
								</td>
								<td class="datatableheader" >		
								Employee
								</td>
								<td class="datatableheader" >		
								Designation
								</td>
								<td class="datatableheader" >		
								Delete
								</td>
								<td class="datatableheader" style="display:none;">		
								Due Days
								</td>
								<td class="datatableheader" >		
								Level
								</td>	
							</tr>
								
							
							
					</table>
					<br>
					<table class="tabtable" id="bTab" >
					<tr>
							
							<td class="fieldLabel" colspan="1" align="center">		
								<hdiits:button name="SubmitEmp" id="SubmitEmp" type="button" onclick="submitEmployees()" value="Submit"></hdiits:button>		
							</td>
							
						</tr>
					</table>
					</div>
				
	<div id="mainDiv" >
	</div>

	<div id="popupcontent"  align="center">
	<hdiits:fieldGroup id="fldgrp" titleCaption="Hierarchy is not configured">
	<br><br>
	<input type="radio" name="hieSel" value="1" checked="checked"/>Configure New Hierarchy
	<br><br>
	<input type="radio" name="hieSel" value="2" />Map to existing Hierarchy
	<br><br>
	<a href="#" onmouseover="" onclick="hidePopup(1);">Close</a>
	</hdiits:fieldGroup>
	</div>
	<div id="popupcontent5"  align="center">
	<hdiits:fieldGroup id="fldgrp" titleCaption="Hierarchy is not configured">
	<br><br>
	&nbsp;&nbsp;<input type="radio" name="hieSel1" value="1" checked="checked"/>Configure New Hierarchy
	<br><br>
	<input type="radio" name="hieSel1" value="2" />&nbsp;&nbsp;&nbsp;Edit existing Hierarchy
	<br><br>
	<a href="#" onmouseover="" onclick="hidePopup1(1);">Close</a>
	</hdiits:fieldGroup>
	</div>
	<div id="popupcontent4"  align="center">
	<hdiits:fieldGroup id="fldgrp" titleCaption="Hierarchy is not configured">
	<br><br>
	<input type="radio" name="hieSel1" value="1" checked="checked"/>Configure New Hierarchy
	<br><br>
	<a href="#" onmouseover="" onclick="hidePopup2(1);">Close</a>
	</hdiits:fieldGroup>
	</div>
	<div id="popupcontent2"  align="center">
	<hdiits:fieldGroup id="fldgrp2" titleCaption="Select Parent Hierarchy">
	
	<c:if test="${Wfrunenvironment eq 'IFMS'}">
	
	<table  width="100%" align="center">		
	<tr>
		<td width="30%" align="left">
			Select Module:
		</td>
		<td width="70%" align="left">
			<hdiits:select  id="module2"  name="module2" onchange="loadDocCombo(this,'Doc2')">
			<hdiits:option value="Select"> Select </hdiits:option>
			<c:forEach items="${WfModuleList2}" var="WfModuleList2">
			<hdiits:option value="${WfModuleList2.moduleId}"> ${WfModuleList2.moduleName}</hdiits:option>
			</c:forEach>
			</hdiits:select>
		</td>
	</tr>
	<tr>
		<td width="30%" align="left">
		Select Doc:
		</td>
		<td  width="70%" align="left">
			<select  id="Doc2"  name="Doc2" onchange="getParentHierarchy()">
			<option value="Select"> Select </option>
			</select>
		</td>
	</tr>
		<tr>
			<td width="30%" align="left">Select Hierarchy:
			</td>
			<td width="70%" align="left">
			<hdiits:select  id="pHie"  name="pHie" >
			<hdiits:option value="Select"> Select </hdiits:option>
			</hdiits:select>
			</td>	
		</tr>
	</table>
	</c:if>
	<a href="#" onmouseover="" onclick="hidePopup(2);">Close</a>
	<!--  <a href="#" onmouseover="" onclick="hidePopup1(2);">Close</a>
	<a href="#" onmouseover="" onclick="hidePopup2(2);">Close</a> -->
	</hdiits:fieldGroup>
	</div>
				<div id="popupcontent3"  align="center">
	<hdiits:fieldGroup id="fldgrp" titleCaption="Please enter Followin data">
	Level:<input type="text" name="lvlNo" id="lvlNo"/>
	<br><br>
	<input type="radio" name="lvlsel" value="1" checked="checked"/>Apply to all
	<br><br>
	<input type="radio" name="lvlsel" value="2" />Use as start level
	<br><br>
	<a href="#" onmouseover="" onclick="hidePopup(3);">Close</a>
	</hdiits:fieldGroup>
	</div>			
</hdiits:form>	
<script type="text/javascript">
	
		
	var empIdLst = '';
	var empIdArry='';
	var updateFlg=0;
	var finalIdArr = new Array();
	var finalPostArr = new Array();
	var finalDueDayArr = new Array();
	var finalLevelArr = new Array();
	function createNewHierarchy()
	{
		showPopup1(200,142,1);	
	}
	function callShowPopup()
	{
		showPopup(300,200,3);	
		return true;
	}
	function loadDocCombo(Obj,id)
	{
		
		var comboid = document.getElementById(id);
		var moduleIdCombo= document.getElementById('module1');
		comboid.selectedIndex='0';
		var Wfrunenvironment='${Wfrunenvironment}';
		
		if(id!='Doc2')
		{
			document.getElementById('hrow').style.display='none';
			document.getElementById("Branch").selectedIndex='0';
			document.getElementById('brow').style.display='none';
			document.getElementById('employeeSearchId').style.display='none';
			resetNewEmpTable();
			finalIdArr = new Array();
			updateFlg=0;
			document.getElementById('NewHname').style.display='none';
			document.getElementById('NewHDesc').style.display='none';
			document.getElementById('HieName').value='';
			document.getElementById('HieDesc').selectedIndex=0;
			document.getElementById('CopyAll').value='';
		}
		if(comboid.options.length>'0')
		{
			for(var i=comboid.options.length;i>0;i--)
			{comboid.remove(i);}
		}

			
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
			if(Wfrunenvironment == 'HOME')
				url = "hdiits.htm?actionFlag=getDocsForLocation&locCode="+Code+"&flag=0";
			else
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
	
	function getHierarchy()
	{
		

		var locObj=document.getElementById('Location');
		var docObj=document.getElementById('Doc');

		

		finalIdArr = new Array();
		updateFlg=0;
		resetNewEmpTable();
		document.getElementById('hrow').style.display='none';
		document.getElementById('brow').style.display='none';
		document.getElementById("Branch").selectedIndex='0';
		document.getElementById('employeeSearchId').style.display='none';
		document.getElementById('NewHname').style.display='none';
		document.getElementById('NewHDesc').style.display='none';

		document.getElementById('HieName').value='';
		document.getElementById('HieDesc').selectedIndex=0;
		document.getElementById('CopyAll').value='';
		
		if(docObj.selectedIndex!='0')
		{
			
			
			locCode = locObj[locObj.selectedIndex].value;
			docId = docObj[docObj.selectedIndex].value;
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
		
			var url = "hdiits.htm?actionFlag=getHierarchyByLocationAndDocId&locCode="+locCode+"&docId="+docId+"&subFlg="+0;
		
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						var XMLDoc=xmlHttp.responseXML.documentElement;
						var Flag = XMLDoc.getElementsByTagName('Flag');
						
						if(Flag.length==0)
						{
							document.getElementById('refId').style.display='none';
							
							alert("Error...");
							return;
						}
						else if(Flag[0].text==1)
						{
							document.getElementById('refId').style.display='none';
							
							prepareTable(XMLDoc);
						}
						else if(Flag[0].text==2)
						{
							document.getElementById('refId').style.display='none';
							
							var err = XMLDoc.getElementsByTagName('Error');
							alert(err[0].text);
							return;
						}
						else if(Flag[0].text==3)
						{
							document.getElementById('refId').style.display='none';
							
							
							var err = XMLDoc.getElementsByTagName('Error');
							showPopup(200,142,1);						
						}
						else if(Flag[0].text==5)
						{
									
							document.getElementById('refId').style.display='';
							
							
							var hierachyObj = XMLDoc.getElementsByTagName('Hierachy');
							
							var refObj = document.getElementById('refName');
							//showPopup1(200,142,1);	
							if(refObj.options.length>'0')
							{
								for(var i=refObj.options.length;i>0;i--)
								{refObj.remove(i);}
							}
							if( hierachyObj.length ==0)
							{
								document.getElementById('refId').style.display='none';
								
								//showPopup1(200,142,1);
								//alert('no hierarchy found');
								//showPopup(200,142,1);
								showPopup2(200,100,1);
							}
							else
							{showPopup1(200,142,1);	
									//showPopup2(200,142,1);
								document.getElementById('refId').style.display='none';
							for (var i=0 ; i < hierachyObj.length ; i++ )
							{
								
								var refId = hierachyObj[i].childNodes[0].text;	
							
			  					var refName = hierachyObj[i].childNodes[1].text;	   						
			  					var element = document.createElement('option');   				
			     				element.text = refName;
			     				element.value = refId;	
			     				try
							    {
			     					refObj.add(element,null); // standards compliant
							    }
							    catch(ex)
							    {
							    	refObj.add(element); // IE only
							    }
							}
							//refObj.focus();
							}  	
						}
						else
						{
							document.getElementById('refId').style.display='none';
							
							var err = XMLDoc.getElementsByTagName('Error');
							alert(err[0].text);
							var brnchObj = document.getElementById('Branch');
							document.getElementById('brow').style.display='';
							var Brnch = XMLDoc.getElementsByTagName('Brnch');
							if(Brnch.length==0)
							{
							
								alert("No Branches found...");
								return;
							}
							
							if(brnchObj.options.length>'0')
							{
								for(var i=brnchObj.options.length;i>0;i--)
								{brnchObj.remove(i);}
							}
							
							for (var i=0 ; i < Brnch.length ; i++ )
							{
								BrnchCode = Brnch[i].childNodes[0].text;							
			  					BrnchName = Brnch[i].childNodes[1].text;	   						
			  					var element = document.createElement('option');   				
			     				element.text = BrnchCode;
			     				element.value = BrnchName;	
			     				try
							    {
							    	brnchObj.add(element,null); // standards compliant
							    }
							    catch(ex)
							    {
								    brnchObj.add(element); // IE only
							    }
							}
							brnchObj.focus();  
						}
						
						  
					}
				}
		    }
			xmlHttp.open("POST", encodeURI(url) , false);    
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}
	}
	function showPopup1(w,h,f)
	{   
		hideContent();
		if(f==1)
			id="popupcontent5";
		if(f==2){
			id="popupcontent5";
			document.forms[0].hieSel1[2].style.display='none';
		}
		var popUp = document.getElementById(id);    
		popUp.style.top = "300px";   
		popUp.style.left = "400px";   
		popUp.style.width = w + "px";   
		popUp.style.height = h + "px";    
		popUp.style.visibility = "visible";
		
	}
	function showPopup2(w,h,f)
	{   
		hideContent();
		if(f==1)
			id="popupcontent4";
		if(f==2){
			id="popupcontent5";
			document.forms[0].hieSel1[2].style.display='none';
		}
		var popUp = document.getElementById(id);    
		popUp.style.top = "300px";   
		popUp.style.left = "400px";   
		popUp.style.width = w + "px";   
		popUp.style.height = h + "px";    
		popUp.style.visibility = "visible";
		
	}
	function hidePopup1(f)
	{   
		if(f==1)
			id="popupcontent5";
		if(f==2)
			id="popupcontent2";
		var popUp = document.getElementById(id);   
		popUp.style.visibility = "hidden";
		if(f==1)
			afterPopUpClose1();
		
		showContent();
	}
	function hidePopup2(f)
	{   
		if(f==1)
			id="popupcontent4";
		if(f==2)
			id="popupcontent2";
		var popUp = document.getElementById(id);   
		popUp.style.visibility = "hidden";
		if(f==1)
			afterPopUpClose1();
		
		showContent();
	}
	function afterPopUpClose1()
	{
		var no = 0;
		for(var i = 0;i<document.forms[0].hieSel1.length;i++)
		{
			if(document.forms[0].hieSel1[i].checked)
				no = document.forms[0].hieSel1[i].value;
		}	
		if(no==1)
		{
		 document.getElementById('employeeSearchId').style.display='';
		 document.getElementById('NewHname').style.display='';
		 document.getElementById('NewHDesc').style.display='';
		 return;
		}
		else if (no==2)
		{
			
			document.getElementById('refId').style.display='';
			
		}
		else
		{					
		document.getElementById('employeeSearchId').style.display='none';	
		return;
		}
	}
	
	function getHierarchyDtls()
	{

		
		var refObj = document.getElementById('refName');
		
		if(refObj.selectedIndex!='0')
		{
			
			var hierarchyId = refObj[refObj.selectedIndex].value;
			
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
			
			var	url = "hdiits.htm?actionFlag=getHierarchyDtlsForRefId&hierarchyId="+hierarchyId;	
				xmlHttp.onreadystatechange = function()
				{
					if(xmlHttp.readyState == 4) 
					{     
						if(xmlHttp.status == 200) 
						{
							var XMLDoc=xmlHttp.responseXML.documentElement;
							
							prepareTable(XMLDoc);
						}
						
					}
			    }
				xmlHttp.open("POST", encodeURI(url) , false);    
				xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
				xmlHttp.send(encodeURIComponent(null));
			}
		
	}

	function validkn(ev)
	 { 
		
		var keyNum = ev.keyCode;
		var keyChar = String.fromCharCode(keyNum);
	  	numcheck = /\d/;
		var isNum = numcheck.test(keyChar);
		var newVal= 0;
		var isDel = false;
		if((keyNum==8)||(keyNum==46))
			isDel=true;
		
		if(isNum)
			return true;
		else if(isDel)
			return true;
		else
			return false;
			
	 }

	function getHierarchyForBranch()
	{
		var locObj=document.getElementById('Location');
		var docObj=document.getElementById('Doc');
		var brnchObj = document.getElementById('Branch');
		var empTable = document.getElementById('newEmpTable');
		document.getElementById('employeeSearchId').style.display='none';
		document.getElementById('NewHname').style.display='none';
			document.getElementById('NewHDesc').style.display='none';
		resetNewEmpTable();
		finalIdArr = new Array();
		updateFlg=0;
		document.getElementById('hrow').style.display='none';
		document.getElementById('HieName').value='';
		document.getElementById('HieDesc').selectedIndex=0;
		document.getElementById('CopyAll').value='';
		
		if(brnchObj.selectedIndex!='0')
		{
			
			
			locCode = locObj[locObj.selectedIndex].value;
			docId = docObj[docObj.selectedIndex].value;
			brnchCode = brnchObj[brnchObj.selectedIndex].value;
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
		
			var url = "hdiits.htm?actionFlag=getHierarchyByLocationAndDocId&locCode="+locCode+"&docId="+docId+"&brnchCode="+brnchCode+"&subFlg="+3;
		
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						var XMLDoc=xmlHttp.responseXML.documentElement;
						var Flag = XMLDoc.getElementsByTagName('Flag');
						
						if(Flag.length==0)
						{
						
							alert("Error...");
							return;
						}
						else if(Flag[0].text==1)
						{
							prepareTable(XMLDoc);
						}
						else if(Flag[0].text==2)
						{
						
							var err = XMLDoc.getElementsByTagName('Error');
							alert(err[0].text);
							return;
						}
						else if(Flag[0].text==3)
						{
							var err = XMLDoc.getElementsByTagName('Error');
							showPopup(200,142,1);		
						}
						  
					}
				}
		    }
			xmlHttp.open("POST", encodeURI(url) , false);    
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}
	}

	function prepareTable(XMLDoc)
	{
		resetNewEmpTable();
		var empTable = document.getElementById('newEmpTable');
		document.getElementById('employeeSearchId').style.display='';

		var hName = XMLDoc.getElementsByTagName('HierarchyName');
		var hId = XMLDoc.getElementsByTagName('HierarchyRefId');
		var emp = XMLDoc.getElementsByTagName('Emp');
		var hqId = XMLDoc.getElementsByTagName('HierarchySeqId');
		
		
		empTable.style.display='';
		empTable.rows[0].cells[0].innerHTML = hId[0].text;
		empTable.rows[0].cells[1].innerHTML="Hierarchy Name : "+hName[0].text;
		empTable.rows[1].cells[0].innerHTML = hqId[0].text;
		var rowNo = empTable.rows.length;
		if(emp.length>0)
		{
			for (var i=0 ; i < emp.length ; i++ )
			{
				Post = emp[i].childNodes[0].text;
				Ename = emp[i].childNodes[1].text;							
				Edsgn = emp[i].childNodes[2].text;
				DueDy = emp[i].childNodes[3].text;
				
				if(DueDy == null || DueDy == 'null')
					DueDy = "0";
				Level = emp[i].childNodes[4].text;

				//id change st	
				var cnt = 0;
				for(var b=0;b<finalIdArr.length;b++)
				{
 					var pst = finalIdArr[b].split('-');
				if(pst[0]==Post)
					cnt++;
  				}
  				var chkId =  Post+"-"+cnt;
				ddid = "dd"+"-"+chkId;
				lvlid = "lvl"+"-"+chkId;
				rid = "r"+"-"+chkId;
				//id change end
  				
				var row = empTable.insertRow();	
				row.setAttribute("id", rid);
				
				var cell1=row.insertCell(0);
				var cell2=row.insertCell(1);
				var cell3=row.insertCell(2);
				var cell4=row.insertCell(3);
				var cell5=row.insertCell(4);	
				
				cell1.style.display = 'none';
				cell1.innerHTML=Post;
				cell2.innerHTML=Ename;
				cell3.innerHTML=Edsgn;	
				cell4.innerHTML='<a href=javascript:deleteTempEmp("'+chkId+'") >Delete</a>';
				cell5.innerHTML='<input  type=text onkeypress= "return validkn(event)" id='+lvlid+' value='+ Level +'>';

				//id change st	
				finalIdArr.push(chkId);

				}
			updateFlg=1;
			colorTable();
			}
			else
			{
				alert("Error...");
				return;
			}
	}
	function getId(post)
	{
		var cnt = 0;
		for(var j=0;j<finalIdArr.length;j++)
		{
			var temppst = finalIdArr[j].split('-');
			if(post == temppst[0])
			{
				cnt++;
			}
		}	
		var tid = post+"-"+cnt;
		return tid;
	}
	
	function getEmployees()
	{
		var idName = "Employee_Name_" + "EMPLOYEESEARCH";
		var idPost = "Employee_Post_ID_" + "EMPLOYEESEARCH";
		var idDsgn = "Dsgn_NM_" + "EMPLOYEESEARCH";
		var empNameArry = new Array();
		var empDsgnArry = new Array();
		var empIdArry = new Array();
		var typ = 1;

		if(document.getElementById('lvlNo').value == '')
		{
			var lvlUsr = 10;
		}
		else			

			var lvlUsr = document.getElementById('lvlNo').value; 
		//lvlsel lvlNo
		rad = document.getElementsByName('lvlsel');
		for(i=0;i<rad.length;i++)
		{
			if(rad[i].checked)
			{
				typ = rad[i].value;
				break;
				
			}
		}
		var empTable = document.getElementById('newEmpTable');
		empTable.style.display='';
		
		if(document.getElementById(idPost).value=='')
		{
			var empIdLst = document.forms[0].Sel_Employee_POST_Array_EMPLOYEESEARCH.value;
			var empNameLst = document.forms[0].Sel_Employee_NAME_Array_EMPLOYEESEARCH.value;
			var empDsgnLst = document.forms[0].Sel_Employee_DESIG_Array_EMPLOYEESEARCH.value;
			
			empNameArry = empNameLst.split(',');
			empDsgnArry = empDsgnLst.split(',');
			empIdArry = empIdLst.split(',');
		}
		else
		{
			empIdArry.push(document.getElementById(idPost).value);
			empDsgnArry.push(document.getElementById(idDsgn).value);
			empNameArry.push(document.getElementById(idName).value);
		}

		if(updateFlg==0)
		{
			document.getElementById('NewHname').style.display='';
			document.getElementById('NewHDesc').style.display='';
			empTable.rows[0].cells[1].innerHTML="New Hierarchy";
		}
		for(i=0;i<empNameArry.length;i++)
		{
			
		    chk = getId(empIdArry[i]);
			var row = empTable.insertRow();	
				
			var rid = "r"+"-"+chk;
			var ddid = "dd"+"-"+chk;
			var lvlid = "lvl"+"-"+chk;
			row.setAttribute("id", rid);
			
			var  cell1=row.insertCell(0);
			var  cell2=row.insertCell(1);
			var  cell3=row.insertCell(2);
			var  cell4=row.insertCell(3);
			var  cell5=row.insertCell(4);
			//var  cell6=row.insertCell(5);	
			cell1.style.display = 'none';
			cell1.innerHTML=empIdArry[i];
			cell2.innerHTML=empNameArry[i];
			cell3.innerHTML=empDsgnArry[i];
			cell4.innerHTML='<a href=javascript:deleteTempEmp("'+chk+'") >Delete</a>';
			//cell5.innerHTML='<input  type=text  onblur=valDueDay(this) id='+ddid+' value=0>';
			if(typ==1)
				cell5.innerHTML='<input  type=text onkeypress= "return validkn(event)" id='+lvlid+' value='+ lvlUsr +'>';
				if(typ==2)
				{
					flvl = Number(lvlUsr)+Number(i*10);
				cell5.innerHTML='<input  type=text onkeypress= "return validkn(event)" id='+lvlid+' value='+ flvl +'>';
				}
			
			finalIdArr.push(chk);
		}	
			
		sortTable();	
		colorTable();
	}

	function sortTable()
	{
		
		var empTable = document.getElementById('newEmpTable');
		var seqArr = new Array();
		var cidArr = new Array();
		var c0 = new Array();
		var c1 = new Array();
		var c2 = new Array();
		var c3 = new Array();
		var c4 = new Array();
		//var c5 = new Array();
		for(var i=2;i<empTable.rows.length;i++)
	    {
	      var rowIdC = empTable.rows[i].id;
	      var t1 = rowIdC.split('-');
	      var lvlIdC = "lvl-"+t1[1]+"-"+t1[2];
	      var l1 = document.getElementById(lvlIdC).value;
	      var pval = l1+"::"+rowIdC;
	      seqArr.push(pval);
	      c0.push(empTable.rows[i].cells[0].innerHTML);
		  c1.push(empTable.rows[i].cells[1].innerHTML);
		  c2.push(empTable.rows[i].cells[2].innerHTML);
		  c3.push(empTable.rows[i].cells[3].innerHTML);
		  c4.push(empTable.rows[i].cells[4].innerHTML);
		  //c5.push(empTable.rows[i].cells[5].innerHTML);
		  cidArr.push(rowIdC);
		}
		var rowNo = empTable.rows.length;
		for(var i=2;i<rowNo;i++)
		{
			empTable.deleteRow(2);
		}
		seqArr.sort();
		for(var j=0;j<seqArr.length;j++)
	    {
	      var rowId0 = seqArr[j].split('::')[1];
	      for(var k=0;k<cidArr.length;k++)
		  {
			  var rowId1 = cidArr[k];
			  if((rowId0==rowId1))
			  {
				  var row = empTable.insertRow();	
				  row.setAttribute("id", rowId0);
					
				  var  cell1=row.insertCell(0);
				  var  cell2=row.insertCell(1);
				  var  cell3=row.insertCell(2);
				  var  cell4=row.insertCell(3);
				  var  cell5=row.insertCell(4);
				  //var  cell6=row.insertCell(5);

				  cell1.innerHTML = c0[k];
				  cell2.innerHTML = c1[k];
				  cell3.innerHTML = c2[k];
				  cell4.innerHTML = c3[k];
				  cell5.innerHTML = c4[k];
				  //cell6.innerHTML = c5[k];

				  cell1.style.display = 'none';
			  }
		  }
	    
		}
		
	}

	function colorTable()
	{
		var prevlvl = 0;
		var prevCol = '';
		var empTable = document.getElementById('newEmpTable');
		for(var i=2;i<empTable.rows.length;i++)
	    {
	      var rowIdC = empTable.rows[i].id;
	      var t1 = rowIdC.split('-');
	      var lvlIdC = "lvl-"+t1[1]+"-"+t1[2];
	      var l1 = document.getElementById(lvlIdC).value;
	      if(i==2)
	    	    bColor = 'white';
	      else if((l1!=prevlvl)&&(prevCol=='white'))
				bColor = '#E9E9E9';
	      else if((l1!=prevlvl)&&(prevCol=='#E9E9E9'))
				bColor = 'white';
		  else if(l1==prevlvl)
				bColor = prevCol;
			
	      empTable.rows[i].style.background = bColor;
			
	      prevlvl = l1;
	      prevCol = bColor;
	    }
	}
	function deleteTempEmp(rid)
	{
		
		var empTable = document.getElementById('newEmpTable');
		var tabRId = "r"+"-"+rid;
		var rindex = document.getElementById(tabRId).rowIndex;
		
		empTable.deleteRow(rindex);
		
		for(i=0;i<finalIdArr.length;i++)
		{
			if(finalIdArr[i]==rid)
			{
				finalIdArr.splice(i,1);
			}
		}
		colorTable();
	}
	function valDueDay(obj)
	{
			
		if((obj.value<100)&&(obj.value!='')&&(obj.value.length<3))
		{
			return true;
		}
		else
		{
			obj.value ='';
			alert("Due days should be a number less then 100");
			obj.focus();
			return false;
			
		}
	
	}
	function copyDueDays(obj)
	{
		
			if(valDueDay(obj))
			{
				for(i = 0; i<finalIdArr.length ;i++)
				{
					var Tempid = "dd-"+finalIdArr[i];
					document.getElementById(Tempid).value=obj.value;
				}
			}
		
	}
	function resetNewEmpTable()
	{
		document.getElementById('CopyAll').value='';
		document.getElementById('HieName').value='';
		var empTable = document.getElementById('newEmpTable');
		var rowNo = empTable.rows.length;
		for(var i=2;i<rowNo;i++)
		{
			empTable.deleteRow(2);
		}
		finalIdArr = new Array();
		empTable.style.display='none';
		document.forms[0].Employee_ID_Array_EMPLOYEESEARCH.value = '';
	}

	function submitEmployees()
	{
	
		finalPostArr = new Array();
		finalDueDayArr = new Array();
		finalLevelArr = new Array();
		var empTable = document.getElementById('newEmpTable');
		var rowNo = empTable.rows.length;
		var hieDelFlg = 0;

		//st chk dup lvl
		for(var j=0;j<finalIdArr.length;j++)
		{
			var currentItem = finalIdArr[j];
			
			var tempIdArr = new Array();
			var tempLvlArr = new Array();

			lvId = "lvl-"+currentItem;
			tempIdArr.push(currentItem);
			tempLvlArr.push(document.getElementById(lvId).value);
			
			for(var k=(j+1);k<finalIdArr.length;k++)
			{
				if(finalIdArr[k].split('-')[0]==finalIdArr[j].split('-')[0])
				{
					lvId = "lvl-"+finalIdArr[k];
					tempIdArr.push(finalIdArr[k]);
					tempLvlArr.push(document.getElementById(lvId).value);
				}
			}
			if(tempIdArr.length>1)
			{
				for(var m=0;m<tempLvlArr.length;m++)
				{
					for(var n=(m+1);n<tempLvlArr.length;n++)
					{
						if(tempLvlArr[n]==tempLvlArr[m])
						{
							alert("Please enter a different level");
							var id1 = "lvl-"+tempIdArr[n];
							document.getElementById(id1).focus();
							return;
						}
					}
				}
			}
			
		}
		//end
		
		if(rowNo==2)
		{
			hieDelFlg = 1;
		}
		var refId = empTable.rows[0].cells[0].innerHTML;
		var seqId = empTable.rows[1].cells[0].innerHTML;
		for(var i=0;i<finalIdArr.length;i++)
		{
			dId = "dd-"+finalIdArr[i];
			lId = "lvl-"+finalIdArr[i];
			pst = finalIdArr[i].split('-')[0];
			lvl = document.getElementById(lId).value;
			//due = document.getElementById(dId).value;
			due = 0;
			finalPostArr.push(pst);
			finalDueDayArr.push(due);
			finalLevelArr.push(lvl);
		}
		//alert(finalPostArr+"::"+finalDueDayArr+"::"+finalLevelArr);
		
		var url='';
		if(updateFlg==0)
		{
			if(document.getElementById('HieName').value=='')
			{
				alert('Please enter hierarchy name');
				document.getElementById('HieName').focus();
				return;
			}
			if(document.getElementById('HieDesc').value=='-1')
			{
				alert('Please enter hierarchy description');
				document.getElementById('HieDesc').focus();
				return;
			}
			if(hieDelFlg==1)
			{
				alert('Please select some employees');
				return;
			}
			url = "hdiits.htm?actionFlag=enterHierarchyDetails&dueDaysLst="+finalDueDayArr+"&postIdLst="+finalPostArr+"&levelLst="+finalLevelArr;
		}
		else
		{
			if(hieDelFlg==1)
			{
				if(confirm("You want to delete hierarchy?")==true)
				{
					url = "hdiits.htm?actionFlag=updateHierarchyDetails&dueDaysLst="+finalDueDayArr+"&postIdLst="+finalPostArr+"&levelLst="+finalLevelArr+"&hierarchyDelFlg="+hieDelFlg+"&hierarchyRefId="+refId+"&hierarchySeqId="+seqId;
				}
				else					
				return;
			}
			else
				url = "hdiits.htm?actionFlag=updateHierarchyDetails&dueDaysLst="+finalDueDayArr+"&postIdLst="+finalPostArr+"&levelLst="+finalLevelArr+"&hierarchyDelFlg="+hieDelFlg+"&hierarchyRefId="+refId+"&hierarchySeqId="+seqId;
		} 
		document.forms[0].action=url;
		document.forms[0].submit();
	}
	
	function loadHierarchyCombo()
	{
		
		var comboid = document.getElementById('HierarchyC');
		comboid.selectedIndex='0';
		if(comboid.options.length>'0')
		{
			for(var i=comboid.options.length;i>0;i--)
			{comboid.remove(i);}
		}
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
		var url = "hdiits.htm?actionFlag=getAllHierarchies";
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					var Branch = XMLDoc.getElementsByTagName('Hierarchy');
					//alert(Category)
					if(Branch.length==0)
					{
					
						alert("No Hierarchies found...");
						return;
					}
				
					for (var i=0 ; i < Branch.length ; i++ )
					{
						bCode = Branch[i].childNodes[0].text;							
	  					bName = Branch[i].childNodes[1].text;	   						
	  					var element = document.createElement('option');   				
	     				element.text = bName;
	     				element.value = bCode;	
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

	function submitHierarchy()
	{
		if(document.getElementById('HierarchyC').selectedIndex=='0')
		{
			alert('Please select a hierarchy');
			document.getElementById('HierarchyC').focus();
			return;
		}
		//alert(document.getElementById('HierarchyC').value);
		url = "hdiits.htm?actionFlag=mapHierarchy";
		document.forms[0].action=url;
		document.forms[0].submit();
	}

	var baseText = null; 
	var maind = document.getElementById('mainDiv');
	function showPopup(w,h,f)
	{   
		hideContent();
		if(f==1)
			id="popupcontent";
		if(f==2)
			id="popupcontent2";
		if(f==3)
			id="popupcontent3";
		var popUp = document.getElementById(id);    
		popUp.style.top = "300px";   
		popUp.style.left = "400px";   
		popUp.style.width = w + "px";   
		popUp.style.height = h + "px";    
		popUp.style.visibility = "visible";
		
	}
	function hideContent()
	{
		maind.style.top = "1px";   
		maind.style.left = "1px";   
		maind.style.width = document.body.clientWidth;   
		maind.style.height = document.body.clientHeight;  
		maind.className='transON';  
	}
	function showContent()
	{
		maind.style.top = "0px";   
		maind.style.left = "0px";   
		maind.style.width = "0px";   
		maind.style.height = "0px";  
		maind.className='transOFF';  
	}
	function hidePopup(f)
	{   
		if(f==1)
			id="popupcontent";
		if(f==2)
			id="popupcontent2";
		if(f==3)
			id="popupcontent3";
		
		var popUp = document.getElementById(id);   
		popUp.style.visibility = "hidden";
		if(f==1)
			afterPopUpClose();
		else if(f==3)
			getEmployees();
		showContent();
	}

	function afterPopUpClose()
	{
		var no = 0;
		for(var i = 0;i<document.forms[0].hieSel.length;i++)
		{
			if(document.forms[0].hieSel[i].checked)
				no = document.forms[0].hieSel[i].value;
		}	
		if(no==1)
		{
		 document.getElementById('employeeSearchId').style.display='';
		 document.getElementById('NewHname').style.display='';
		 document.getElementById('NewHDesc').style.display='';
		 return;
		}
		else if (no==2)
		{
			document.getElementById('hrow').style.display='';
			loadHierarchyCombo();
			document.getElementById('HierarchyC').focus();
		}
		else
		{					
		document.getElementById('employeeSearchId').style.display='none';	
		return;
		}
	}

	function getParentHierarchy()
	{
		var locObj=document.getElementById('Location');
		var docObj=document.getElementById('Doc2');
		var combo = document.getElementById('pHie');

		if(combo.options.length>'0')
		{
			for(var i=combo.options.length;i>0;i--)
			{combo.remove(i);}
		}
		
		if(docObj.selectedIndex!='0')
		{
			locCode = locObj[locObj.selectedIndex].value;
			docId = docObj[docObj.selectedIndex].value;
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
		
			var url = "hdiits.htm?actionFlag=getHierarchyByLocationAndDocId&locCode="+locCode+"&docId="+docId+"&subFlg="+0;
		
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						var XMLDoc=xmlHttp.responseXML.documentElement;
						var Flag = XMLDoc.getElementsByTagName('Flag');
						
						if(Flag.length==0)
						{
						
							alert("Error...");
							return;
						}
						else if(Flag[0].text==1)
						{
							var hName = XMLDoc.getElementsByTagName('HierarchyName');
							var hId = XMLDoc.getElementsByTagName('HierarchyRefId');
							var element = document.createElement('option');   			
							element.text = hName[0].text;
		     				element.value = hId[0].text;	
		     				try
						    {
						    	combo.add(element,null); // standards compliant
						    }
						    catch(ex)
						    {
							    combo.add(element); // IE only
						    }
						}
						else if(Flag[0].text==2)
						{
						
							var err = XMLDoc.getElementsByTagName('Error');
							alert(err[0].text);
							return;
						}
						else if(Flag[0].text==3)
						{
							var err = XMLDoc.getElementsByTagName('Error');
							alert(err[0].text);				
						}
						else if(Flag[0].text==5)
						{
									
							//document.getElementById('refId').style.display='';
							
							
							var hierachyObj = XMLDoc.getElementsByTagName('Hierachy');
							
							var refObj = document.getElementById('refName');
							//showPopup1(200,142,1);	
							if(refObj.options.length>'0')
							{
								for(var i=refObj.options.length;i>0;i--)
								{refObj.remove(i);}
							}
							if( hierachyObj.length ==0)
							{
								
							}
							else
							{
							for (var i=0 ; i < hierachyObj.length ; i++ )
							{
								
								var refId = hierachyObj[i].childNodes[0].text;	
							
			  					var refName = hierachyObj[i].childNodes[1].text;	   						
			  					var element = document.createElement('option');   				
			     				element.text = refName;
			     				element.value = refId;	
			     				try
							    {
			     					combo.add(element,null); // standards compliant
							    }
							    catch(ex)
							    {
							    	combo.add(element); // IE only
							    }
							}
							//refObj.focus();
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


	
	/*function chkAllDueDays()
	{
		for(var i = 0; i < finalPostArr.length ; i++)
		{
			valDueDay(document.getElementById(finalPostArr[i]));
				
		}
	}*/
	
	/*function deleteEmp(rid, hieId)
	{
		
		var hieDelFlg = 0;
		var empTable = document.getElementById('newEmpTable');
		var postId = empTable.rows[rid.rowIndex].cells[0];
		if(empTable.rows.length==3)
		{
		hieDelFlg = 1;
		}
		
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

		var url = "hdiits.htm?actionFlag=deleteEmpFromHierarchy&postId="+postId+"&hieId="+hieId+"&hieDelFlg="+hieDelFlg;
		xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{ //alert(xmlHttp.responseXML);
				    var XMLDoc=xmlHttp.responseXML.documentElement;
				   alert(XMLDoc);
				    if(XMLDoc!=null)
				    {
						
						empTable.deleteRow(rid.rowIndex);
						if(hieDelFlg==1)
						{
							document.getElementById('Doc').selectedIndex='0';
							empTable.style.display='none';
							document.getElementById("Branch").selectedIndex='0';
							document.getElementById('brow').style.display='none';
							document.getElementById('employeeSearchId').style.display='none';
							
						}
						for(i=0;i<finalPostArr.length;i++)
						{
							if(finalPostArr[i]==pst)
								finalPostArr.splice(i,1);
						}
					}
					else
					{alert("Error");}
				}
			}
	    }
        xmlHttp.open("POST", encodeURI(url) , false);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
	
		
		
	}*/
	/*function shiftEmpUp(rowId)
	{
		
		var empTable = document.getElementById('newEmpTable');
		
		var rInd = rowId.rowIndex;
		
		if(rInd==2)
			alert('Can not move up');

		else
		{
			var tempCell1=empTable.rows[rInd].cells[0].innerHTML;
			var tempCell2=empTable.rows[rInd].cells[1].innerHTML;
			var tempCell3=empTable.rows[rInd].cells[2].innerHTML;
			
			var tempCell5=empTable.rows[rInd].cells[4].innerHTML;
			

			empTable.rows[rInd].cells[0].innerHTML = empTable.rows[(rInd-1)].cells[0].innerHTML;
			empTable.rows[rInd].cells[1].innerHTML = empTable.rows[(rInd-1)].cells[1].innerHTML;
			empTable.rows[rInd].cells[2].innerHTML = empTable.rows[(rInd-1)].cells[2].innerHTML;
			
			empTable.rows[rInd].cells[4].innerHTML = empTable.rows[(rInd-1)].cells[4].innerHTML;
			

			empTable.rows[(rInd-1)].cells[0].innerHTML = tempCell1;
			empTable.rows[(rInd-1)].cells[1].innerHTML = tempCell2;
			empTable.rows[(rInd-1)].cells[2].innerHTML = tempCell3;
			
			empTable.rows[(rInd-1)].cells[4].innerHTML = tempCell5;
			
			
		}
		
	}

	function shiftEmpDn(rowId)
	{
		
		var empTable = document.getElementById('newEmpTable');
		var rInd = rowId.rowIndex;
		
		if(rInd==(empTable.rows.length-1))
			alert('Can not move down');

		else
		{
			var tempCell1=empTable.rows[rInd].cells[0].innerHTML;
			var tempCell2=empTable.rows[rInd].cells[1].innerHTML;
			var tempCell3=empTable.rows[rInd].cells[2].innerHTML;
			
			var tempCell5=empTable.rows[rInd].cells[4].innerHTML;
			

			empTable.rows[rInd].cells[0].innerHTML = empTable.rows[(rInd+1)].cells[0].innerHTML;
			empTable.rows[rInd].cells[1].innerHTML = empTable.rows[(rInd+1)].cells[1].innerHTML;
			empTable.rows[rInd].cells[2].innerHTML = empTable.rows[(rInd+1)].cells[2].innerHTML;
			
			empTable.rows[rInd].cells[4].innerHTML = empTable.rows[(rInd+1)].cells[4].innerHTML;
			

			empTable.rows[(rInd+1)].cells[0].innerHTML = tempCell1;
			empTable.rows[(rInd+1)].cells[1].innerHTML = tempCell2;
			empTable.rows[(rInd+1)].cells[2].innerHTML = tempCell3;
			
			empTable.rows[(rInd+1)].cells[4].innerHTML = tempCell5;
			
			
		}
		
	}
	*/
</script>
<%}catch(Exception e)
{e.printStackTrace();} %>


