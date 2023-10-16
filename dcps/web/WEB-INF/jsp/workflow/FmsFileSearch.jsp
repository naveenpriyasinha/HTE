
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<c:set var="length" value="1"></c:set>
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>

<c:set var="size" value="${resValue.size}"></c:set>
<c:set var="expandFlag" value="${resValue.expandFlag}"></c:set>
<c:set var="fmsFileMstLst" value="${resValue.fmsFileMstLst}"></c:set>
<c:set var="fileUrl" value="${resValue.fileUrl}"></c:set>
<c:set var="pPost" value="${resValue.pPost}"></c:set>
<script type="text/javascript"  src="script/common/xp_progress.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" >
function chkDept()
{
	if(document.getElementById('DeptId').value=='')
	{
		alert('Please select a department first');
		document.getElementById('txtDeptName').focus();
		document.getElementById('LocCode').value='';
		document.getElementById('txtLocName').value='';
		return false;
	}
	else
		return true;
}

function loadBrnchCombo()
{
	var comboid = document.getElementById('brnch');
	resetCombo(comboid);
	var comboid2 = document.getElementById('sub');
	resetCombo(comboid2);
	locObj = document.getElementById('LocCode');
	if(locObj.value!='')
	{
		locCode = locObj.value;
		//alert(locCode);
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
		var url = "hdiits.htm?actionFlag=getBrnchesForLocForSrch&locCode="+locCode;
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc=xmlHttp.responseXML.documentElement;
					
					var Branch = XMLDoc.getElementsByTagName('Branch');
					//alert(Category)
					if(Branch.length==0)
					{
					
						alert("No Branches found...");
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
}
</script>
<fmt:setBundle basename="resources.workflow.workFlowLables" var="resourceLables" scope="request"/>
<hdiits:form name="fileSearchForm" validate="true" method="POST" action="./hdiits.htm" encType="multipart/form-data">


<table id="PageTab" width="100%"><tr><td>
<hdiits:fieldGroup id="fldgrp" titleCaption="Complete File/Correspondence Search" expandable="true" collapseOnLoad="${expandFlag}">
	<table class="tabtable">
		
			
			<tr>
				<td class="fieldLabel" colspan="2" >
				<hdiits:radio name="srchTypSel" onclick="showRow(this.value)" id="Generic" value="0" captionid="WFS.GEN" bundle="${resourceLables}"/>
				</td>
				<td class="fieldLabel" colspan="2" >
				<hdiits:radio name="srchTypSel" onclick="showRow(this.value)" id="Advanced" value="1" captionid="WFS.ADV" bundle="${resourceLables}"/>
				</td>
				<td class="fieldLabel" colspan="1" >
				<hdiits:caption captionid="WFS.DT" bundle="${resourceLables}" />
				</td>
				<td class="fieldLabel" colspan="1" >
					<hdiits:select  id="docTypSel"  name="docTypSel"  onchange="">
					<hdiits:option value="3"> File </hdiits:option>
					<hdiits:option value="2"> Correspondence </hdiits:option>
					</hdiits:select>
				</td>
			</tr>
			
			<tr id="advRow1" style="display: none;">
				<td class="fieldLabel">
					<hdiits:caption captionid="WFS.DEPT" bundle="${resourceLables}" />
				</td>
				<td class="fieldLabel">
					<hdiits:text  id="txtDeptName" name="txtDeptName"  />
					<input type="hidden" name="DeptId" id="DeptId"/>
					<span id="indicatorRegion" style="display:none;">
					<img src="./images/busy-indicator.gif"/>
					</span>
					<ajax:autocomplete
				    source="txtDeptName"
				    target="DeptId"
				    baseUrl="hdiits.htm?actionFlag=getAllDepartments"
				    parameters="deptName={txtDeptName}"
				    className="autocomplete"
				    minimumCharacters="2"
				   
				    indicator="indicatorRegion"
				    />
				</td>
				
				<td class="fieldLabel">
					<hdiits:caption captionid="WFS.LOC" bundle="${resourceLables}" />
				</td>
				<td class="fieldLabel">
					<hdiits:text  id="txtLocName" name="txtLocName"  />
					<input type="hidden" name="LocCode" id="LocCode"/>
					<span id="indicatorRegion1" style="display:none;">
					<img src="./images/busy-indicator.gif"/>
					</span>
					<ajax:autocomplete
				    source="txtLocName"
				    target="LocCode"
				    baseUrl="hdiits.htm?actionFlag=getAllLocationsForDepartment"
				    parameters="locName={txtLocName},deptId={DeptId}"
				    className="autocomplete"
				    minimumCharacters="2"
				    indicator="indicatorRegion1"
				    postFunction="loadBrnchCombo"
				    preFunction="chkDept"
				    />
				</td>
				<td class="fieldLabel">
					<hdiits:caption captionid="WFS.BRANCH" bundle="${resourceLables}" />
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="brnch"  name="brnch"  onchange="loadDocCombo(this)">
					<hdiits:option value="Select"> Select </hdiits:option>
					</hdiits:select>
				</td>	
			</tr>
			
			<tr id="advRow2" style="display: none;">
				<td class="fieldLabel">
					<hdiits:caption captionid="WFS.SUB" bundle="${resourceLables}" />
				</td>
				<td class="fieldLabel">
					<hdiits:select  id="sub"  name="sub" >
					<hdiits:option value="Select"> Select </hdiits:option>
					</hdiits:select>
				</td>
				<td class="fieldLabel">
					<hdiits:caption captionid="WFS.NUM" bundle="${resourceLables}" />
				</td>
				<td class="fieldLabel">
					<hdiits:text name="fcNum" id="fcNum" />
				</td>
				<td class="fieldLabel">
					<hdiits:caption captionid="WFS.FCDESC" bundle="${resourceLables}" />
				</td>
				<td class="fieldLabel">
					<hdiits:text name="fcDesc" id="fcDesc" />
				</td>
			</tr>
			<tr>
			<tr id="genRow" style="display: none;">
				<td colspan="1" class="fieldLabel">
					<hdiits:caption captionid="WFS.GENL" bundle="${resourceLables}" />
				</td>
				<td colspan="1" class="fieldLabel">
					<hdiits:text name="genTxt" id="genTxt" />
				</td>
				<td colspan="4" class="fieldLabel"></td>
			</tr>
			<td colspan="6" style="text-align: center">
			
				<hdiits:button name="reset1" type="button" onclick="SrchReset()" captionid="WFS.RSET" bundle="${resourceLables}"/>
				<hdiits:button name="submit1" type="button" onclick="AjaxSubmit(1, 0)" captionid="WFS.SRCH" bundle="${resourceLables}"/>
				
			</td>
			</tr>
	</table>
</hdiits:fieldGroup>		
	<br />
	

	<table id="dataTable" style="display: none;" width="100%" >
	<tr class="datatableheader" >
				<td  >
				<hdiits:caption captionid="WFS.SRNO" bundle="${resourceLables}" />
				</td>
				<td  >
				<hdiits:caption captionid="WFS.SUB" bundle="${resourceLables}" />
				</td>
				<td  >
				<hdiits:caption captionid="WFS.DESC" bundle="${resourceLables}" />
				</td>
				<td  >
				<hdiits:caption captionid="WFS.NUM" bundle="${resourceLables}" />
				</td>
				<td  >
				<hdiits:caption captionid="WFS.CREATOR" bundle="${resourceLables}" />
				</td>
				<td   >
				<hdiits:caption captionid="WFS.OWNER" bundle="${resourceLables}" />
				</td>
				<td   >
				<hdiits:caption captionid="WFS.DUEDATE" bundle="${resourceLables}" />
				</td >
				<td  >
				<hdiits:caption captionid="WFS.PRIORITY" bundle="${resourceLables}" />
				</td>
				<td  >
				<hdiits:caption captionid="WFS.STATUS" bundle="${resourceLables}" />
				</td>
				<td  >
				<hdiits:caption captionid="WFS.STAGE" bundle="${resourceLables}" />
				</td>
				<td  >
				<hdiits:caption captionid="WFS.FOUND" bundle="${resourceLables}" />
				</td>	
			</tr>
	</table>
	<br />
	
	<table id="pageTable" style="background-color: rgb(223,223,223); border-width: 1px; border-style: dashed;border-color:black; width: 100%;display:none;">
		<tr><td style="text-align: center" ></td></tr>
		<tr><td style="text-align: center" ></td></tr>
	</table>
	
	<div class="shadow" id="shadow" style="visibility: hidden">
		<div class="output" id="output" onclick="mouseClick()"></div>
	</div>
	
	</td></tr></table>
	<span id="indicatorRegion2" style="position: fixed;top: 40%;left: 40%;display: none;">Loading...
	<script type="text/javascript">
		var bar1= createBar(250,15,'white',1,'black','blue',85,7,3,"");
	</script>
	</span>
	
</hdiits:form>	
<script type="text/javascript">
	//alert('${size}'+' records found');
	var urlArr = new Array();//Url Array
	var tPg = '';//total Pages
	var depId = '';
	var locd = '';
	var brncd = '';
	var docId = '';
	var desc = '';
	var num = '';
	var rad = new Array();
	var srchTyp = '';
	var srchTxt = '';
	var docTy = '';
	var depName = '';
	var locName = '';
	var brnName = '';
	var docName = '';
	var typName = '';
	document.getElementsByName('srchTypSel')[1].checked='true';
	showRow('1'); 
	function showRow(srchTyp)
	{
		if(srchTyp=='1')
		{
			document.getElementById('advRow1').style.display='';
			document.getElementById('advRow2').style.display='';
			document.getElementById('genRow').style.display='none';
		}
		else
		{
			document.getElementById('genRow').style.display='';
			document.getElementById('advRow1').style.display='none';
			document.getElementById('advRow2').style.display='none';
		}
	}
	function SrchReset()
	{
		document.getElementById('DeptId').value = ''; 
		document.getElementById('LocCode').value = '';
		document.getElementById('brnch').selectedIndex = '0';
		document.getElementById('sub').selectedIndex = '0'; 
		document.getElementById('fcDesc').value = '';
		document.getElementById('fcNum').value = '';
		document.getElementById('txtDeptName').value = ''; 
		document.getElementById('txtLocName').value = '';
		document.getElementById('genTxt').value = '';
		resetCombo(document.getElementById('brnch'));
		resetCombo(document.getElementById('sub'));
	}
	function resetTable(tabObj, firstRow)
	{
		var rowNo = tabObj.rows.length;
		for(var i=firstRow;i<rowNo;i++)
		{
			tabObj.deleteRow(firstRow);
		}
		
	}
	function clearTableContent(tabObj, firstRow, firstCol)
	{
		var rowNo = tabObj.rows.length;
		var colNo = '';
		for(var i=firstRow;i<rowNo;i++)
		{
			colNo = tabObj.rows[i].cells.length;
			for(var j=firstCol;j<colNo;j++)
			{
				tabObj.rows[i].cells[j].innerHTML = '';
			}
		}
		
	}
	function initParameters()
	{
		depId = document.getElementById('DeptId').value;
		locd = document.getElementById('LocCode').value;
		brncd = document.getElementById('brnch')[document.getElementById('brnch').selectedIndex].value;
		docId = document.getElementById('sub')[document.getElementById('sub').selectedIndex].value;
		desc = document.getElementById('fcDesc').value;
		num = document.getElementById('fcNum').value;
		srchTxt = document.getElementById('genTxt').value;
		rad = new Array();
		docTy = document.getElementById('docTypSel').value;
		
		rad = document.getElementsByName('srchTypSel');
		for(i=0;i<rad.length;i++)
		{
			if(rad[i].checked)
			{
				srchTyp = rad[i].value;
				break;
			}
		}

		depName = document.getElementById('txtDeptName').value;
		locName = document.getElementById('txtLocName').value;
		brnName = document.getElementById('brnch')[document.getElementById('brnch').selectedIndex].text;
		docName = document.getElementById('sub')[document.getElementById('sub').selectedIndex].text;
		//alert(depId+" "+depName);
	}
	function AjaxSubmit(firstRec, init)
	{
		if(init==0)
			initParameters();
		
		var pgTab = document.getElementById('pageTable');
		var tabObj = document.getElementById('dataTable');
		var indObj = document.getElementById('indicatorRegion2');
		var pageTabObj= document.getElementById('PageTab');
		urlArr = new Array();
		tabObj.style.display = '';
		indObj.style.display = '';
		
		pgTab.style.display = '';
		resetTable(tabObj, 1);
		clearTableContent(pgTab, 0, 0);
		
		//resetTable(pgTab, 0;
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
	
		var url = "hdiits.htm?actionFlag=searchJob&docTypSel="+docTy+"&DeptId="+depId+"&LocCode="+locd+"&brnch="+brncd+"&sub="+docId+"&fcNum="+num+"&fcDesc="+desc+"&firstRec="+firstRec+"&srchTyp="+srchTyp+"&srchTxt="+srchTxt;
		//alert(url);
		xmlHttp.onreadystatechange = function()
		{
			if(xmlHttp.readyState == 4) 
			{     
				if(xmlHttp.status == 200) 
				{
					var XMLDoc = xmlHttp.responseXML.documentElement;
					
					indObj.style.display = 'none';
					if(XMLDoc!=null)
					{
						var pageData = XMLDoc.getElementsByTagName('com.tcs.sgv.fms.valueobject.PaginationCustomVO');
						var displayData = XMLDoc.getElementsByTagName('com.tcs.sgv.fms.valueobject.FmsFileSearchResultsCustomVO');
						
						var rSize = pageData[0].childNodes[1].text;
						var fr = pageData[0].childNodes[4].text;
						var lr = pageData[0].childNodes[5].text;
						var pgNo = pageData[0].childNodes[2].text;
						var pgSz = pageData[0].childNodes[3].text;
						tPg = pageData[0].childNodes[0].text;
						//alert(tPg+" "+rSize+" "+fr+" "+lr+" "+pgNo+" "+pgSz);
						var bColor = "";
						pageTabObj.style.display = '';
						if((rSize==0)||(displayData.length==0))
						{
							alert("No Records found to display...");
							return;
						}	
						for (var i=0 ; i < displayData.length ; i++ )
						{
							

							if((i%2)==0)
								bColor = "rgb(255,255,255)";
							else
								bColor = "rgb(233,233,233)";
							RSrNo = displayData[i].childNodes[0].text;	
							RSubject = displayData[i].childNodes[1].text;						
		  					RDesc = displayData[i].childNodes[2].text;	
		  					RNum = displayData[i].childNodes[3].text;		
		  					RUrl = displayData[i].childNodes[4].text;	
		  					RCrt = displayData[i].childNodes[5].text;	
		  					ROwn = displayData[i].childNodes[6].text;	
		  					RDueDt = displayData[i].childNodes[7].text;	
		  					RPrior = displayData[i].childNodes[8].text;	
		  					RStat = displayData[i].childNodes[9].text;	
		  					RStag = displayData[i].childNodes[10].text;	
		  					RFound = displayData[i].childNodes[11].text;	
		  					urlArr[i] = RUrl;
		  					
		  					var row = tabObj.insertRow();	
		  					row.style.backgroundColor = bColor;
		  					var cell1=row.insertCell(0);
							var cell2=row.insertCell(1);
							var cell3=row.insertCell(2);
							var cell4=row.insertCell(3);
							var cell5=row.insertCell(4);
							var cell6=row.insertCell(5);
							var cell7=row.insertCell(6);
							var cell8=row.insertCell(7);
							var cell9=row.insertCell(8);
							var cell10=row.insertCell(9);	
							var cell11=row.insertCell(10);	
							
							cell1.innerHTML=RSrNo;
							cell2.innerHTML=RSubject;
							cell3.innerHTML=RDesc;
							if(RUrl=='#')	
								cell4.innerHTML=RNum;
							else
								cell4.innerHTML='<a href=javascript:openDocument('+i+')>'+RNum+'</a>';
							cell5.innerHTML=RCrt;
							cell6.innerHTML=ROwn;
							cell7.innerHTML=RDueDt;
							cell8.innerHTML=RPrior;
							cell9.innerHTML=RStat;
							cell10.innerHTML=RStag;
							cell11.innerHTML=RFound;
						}
							
	  					var Pcell1=pgTab.rows[0].cells[0];
	  					var Pcell2=pgTab.rows[1].cells[0];
	  					
	  					
	  					Pcell1.innerHTML = (rSize+" records found. Displaying "+fr+" to "+lr+" records.").bold();
						var st = 0;//Start Page range
						var ed = 0;//End PAge range
						var lpgNo = Number(pgNo);//current page Number
						var ltPg = Number(tPg);//total number of pages
						var lpgSz = Number(pgSz);//no. of records on current page
						var prev = (lpgNo-1);//setting previous record
						var nxt = (lpgNo+1);//setting next record
						//settin first record	
						if((lpgNo-4)>0)
							st = (lpgNo-4);
						else
							st = 1;
						if((ltPg>8)&&((st+8)>ltPg))
							st = (ltPg-8);
						//settin last record
						if((ltPg>8))
						ed = (st+8);
						else
						ed = ltPg;
						//alert(st+" "+ed);
						if(lpgNo==1)
						{
							Pcell2.innerHTML+='&nbsp;First&nbsp;';
							Pcell2.innerHTML+='&nbsp;Prev&nbsp;';
						}
						else
						{	
							Pcell2.innerHTML+='&nbsp;<a href=javascript:AjaxSubmit(1,1)>First</a>&nbsp;';
							Pcell2.innerHTML+='&nbsp;<a href=javascript:AjaxSubmit('+prev+',1)>Prev</a>&nbsp;';
						}
						for(var j = st; j<=ed ;j++)
						{ 
							if(j==lpgNo)
								Pcell2.innerHTML+='&nbsp;'+j+'&nbsp;';
							else
								Pcell2.innerHTML+='&nbsp;<a href=javascript:AjaxSubmit('+j+',1)>'+j+'</a>&nbsp;';
						}
						if(lpgNo==ltPg)
						{
							Pcell2.innerHTML+='&nbsp;Next&nbsp;';
							Pcell2.innerHTML+='&nbsp;Last&nbsp;';
						}
						else
						{
						Pcell2.innerHTML+='&nbsp;<a href=javascript:AjaxSubmit('+nxt+',1)>Next</a>&nbsp;';
						Pcell2.innerHTML+='&nbsp;<a href=javascript:AjaxSubmit('+ltPg+',1)>Last</a>&nbsp;';
						}
						//pgTab.rows[1].cells[0].innerHTML = '<a href=javascript:AjaxSubmit(1)>1</a>&nbsp;&nbsp;<a href=javascript:AjaxSubmit(2)>2</a>&nbsp;&nbsp;<a href=javascript:AjaxSubmit(3)>3</a>&nbsp;&nbsp;<a href=javascript:AjaxSubmit(4)>4</a>&nbsp;&nbsp;<a href=javascript:AjaxSubmit(5)>5</a>';
						Pcell2.innerHTML+='&nbsp;&nbsp;&nbsp;Enter Page Number:<input type=text id=pgVal style="width: 40px; height: 12px;" onkeypress= "return validkn(event)" />&nbsp;<input type=button onclick=getPgVal() value="Go">';
					}
					else
					{
						alert("Error Encountered");
					}  
				}
				
			}
	    }
		xmlHttp.open("POST", encodeURI(url) , true);    
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));
		
	}	
	function getPgVal()
	{
		var dd = Number(document.getElementById('pgVal').value);
		AjaxSubmit(dd, 1);
	}
	function validkn(ev)
	 { 
		var val = String(document.getElementById('pgVal').value);
		var keyNum = ev.keyCode;
		var keyChar = String.fromCharCode(keyNum);
	  	numcheck = /\d/;
	  	//alert();
		var isNum = numcheck.test(keyChar);
		var newVal= 0;
		var isDel = false;
		if((keyNum==8)||(keyNum==46))
			isDel=true;
		
		if(isNum)
		{
			newVal = Number(val.concat(keyChar));
			if((newVal>0)&&(newVal<=Number(tPg)))
			{
				return true;
			}
			else
				return false;
		}
		else if(isDel)
			return true;
		else
			return false;
			
	 }
		
	var original;
	function changeToBlue(obj)
	{
		original = obj.style.backgroundColor;
		obj.style.backgroundColor='rgb(200,200,255)';
	}
	
	function changeToOriginal(obj)
	{
		obj.style.backgroundColor=original;
	}
	
	function openDocument(i)
	{
			var url = urlArr[i];
			var urlstyle="location=0,status=0,scrollbars=1,width=screen.availWidth,height=screen.availHeight";
			docWindow = window.open (url,"Document",urlstyle); 
			docWindow.resizeTo(screen.availWidth,screen.availHeight)
			docWindow.moveTo(0,0);
	}
	function submitSubject()
	{	
		var url = "hdiits.htm?actionFlag=searchJob"; 
		document.forms[0].action=url;
		document.forms[0].submit();
	}
	function resetCombo(comboid)
	{
		if(comboid.options.length>'0')
		{
			for(var i=comboid.options.length;i>0;i--)
			{comboid.remove(i);}
		}
		comboid.selectedIndex='0';
	}

	function loadDocCombo(brnchObj)
	{
		
		var comboid = document.getElementById('sub');
		resetCombo(comboid);
		if(brnchObj.selectedIndex!='0')
		{
			
			brnchCode = brnchObj[brnchObj.selectedIndex].value;
			//alert(brnchCode);
			//locCode="qwerty";
			//brnchCode="LIB";
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
		
			var url = "hdiits.htm?actionFlag=getDocsForBranch&brnchCode="+brnchCode;
		
			xmlHttp.onreadystatechange = function()
			{
				if(xmlHttp.readyState == 4) 
				{     
					if(xmlHttp.status == 200) 
					{
						var XMLDoc=xmlHttp.responseXML.documentElement;
						
						var Category = XMLDoc.getElementsByTagName('Doc');
						//alert(Category)
						if(Category.length==0)
						{
							alert("No Docs found...");
							return;
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
	

</script>

