<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<META content="MSHTML 6.00.6000.16608" name=GENERATOR>
<fmt:setBundle basename="resources.hr.acr.acr" var="commonLables" scope="request" />
<fmt:setBundle basename="resources.hr.acr.acr_AlertMessages" var="commonLables1" scope="request" />
<script type="text/javascript" src="common/script/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="<c:url value="script/common/base64.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/hr/acr/acrHierarchyGrp.js"/>"></script>
<style> 
 .dsgnCSS {background-color: #F0FFFF;} 
 .branchCSS {background-color: #E3E4FA;}
 .empCSS {background-color: #FFF8DC;} 
</style>
<script type="text/javascript">
var cacheKey = "";
var rowColor=0;
var cachedData = new JSOC();
var mstPk=0;
var currentYear = '0000';
xmlHttp=GetXmlHttpObject();
var xmlUserData,xmlUserData1,xmlResponseData="";  // Used In Child Window
var updateDBRowIdNow='',updateDBRowIdHost2='';
var userId=new Number();
var finalSubmitArr = new  Array();
var finalSubmitArrCounter =0,offUserId=0;
var totalBranchInCurrTable = new Array();
var totalBranchInCurrTableCount =0;
var totalOffCount =-1;
var totalOffArr = new Array(); 
var empArray = new Array();
var allRowCount=0,ReviewingOffCount=0;
var allRow = new Array();
var ReviewingOffArr = new Array();
function resetHiePage()
{
	offUserId=0;
	document.getElementById('officerName').value='';
	document.getElementById('officerDesi').value='';
	document.getElementById('selectUserId').value='0';
	document.getElementById('userName').value='';
	document.HierarchyGroupMst.startDate.value='';
	document.HierarchyGroupMst.endDate.value='';
	document.getElementById('roleCmb').value='Select';
}
function populateNextLevel(branchCmb) //On Change Event Call this function
{	
	var nextLevelId = document.getElementById('selectDesgn');
	var checkCachedData = isCachedData(branchCmb,nextLevelId);	
	if(checkCachedData)
	{
		populateDropDownWithCachedData(cacheKey,"selectDesgn")
	}
	else
	{
		getAllDesignation(branchCmb)
	}
}
function getAllDesignation(branchCmb)
{
	var radio=branchCmb;	
	var displaystring = "";
	for(var j = 0; j < branchCmb.options.length; j++)
	{
		if(branchCmb.options[j].selected && branchCmb.options[j].value != 'Select' )
		{
			if(displaystring == "")
			{
				displaystring=branchCmb.options[j].value;
			}
			else
			{
				displaystring+= " / " + branchCmb.options[j].value;
			}
		}
	}
	for(var j = 0; j < branchCmb.options.length; j++)
	{
		if(branchCmb.options[j].selected && branchCmb.options[j].value == 'Select' && displaystring=="")
		{
			var comboSubType=document.getElementById('selectDesgn').length;	
			for(i=1;i<comboSubType;i++)
			{
				lgth = document.getElementById('selectDesgn').options.length -1;
				document.getElementById("selectDesgn").options[lgth] = null;
			}
			var comboSubType = document.getElementById('selectDesgn');
			for (var i=comboSubType.length;i>0;i--)  // removing a previous value of combo
			{	     				     					
				comboSubType.remove(comboSubType.value);
			}
			value="Select"
			textStr='<fmt:message key="HR.ACR.Select" bundle="${commonLables}" />'
			
			var y=document.createElement('option');
			y.text=textStr;
			y.value=value;				 		 					
			try
			{
				comboSubType.add(y,null); 	    						
			}
			catch(ex)
			{	   			 
				comboSubType.add(y);	   			 				 
			} 			 
		}
	}
	if(displaystring!="")
	{
		var comboSubType=document.getElementById('selectDesgn').length;	
		for(i=1;i<comboSubType;i++)
		{
			lgth = document.getElementById('selectDesgn').options.length -1;
			document.getElementById("selectDesgn").options[lgth] = null;
		}
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) 
		{
		  hideProgressbar();
		  alert ("Your browser does not support AJAX!");
		  return;
		}		
		
		var url='hdiits.htm?actionFlag=ACRGetDesignationOn&branchArr='+displaystring;
		xmlHttp.open("POST",encodeURI(url),false);
		
		xmlHttp.onreadystatechange = function() 
		{
			if(xmlHttp.readyState == 4) {
				fillDesignationCmb();
				hideProgressbar();
			}
		}
		xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
		xmlHttp.send(encodeURIComponent(null));						
	}
}
function fillDesignationCmb()
{
	if (xmlHttp.readyState == 4) 
	{    
		if (xmlHttp.status == 200) 
		{
			dscXML=xmlHttp.responseText;
			var selectDesgn = document.getElementById('selectDesgn');
			var XMLDoc = getDOMFromXML(dscXML);	
			var tempArray = new Array();
	
			for (var i=selectDesgn.length;i>0;i--)  // removing a previous value of combo
			{	     				     					
				selectDesgn.remove(selectDesgn.value);
			}
			value="Select"
			textStr='<fmt:message key="HR.ACR.Select" bundle="${commonLables}" />'
			
			var y=document.createElement('option');
			y.text=textStr;
			y.value=value;	
			tempArray[0] = y ; 		 					
			try
			{
				selectDesgn.add(y,null); 	    						
			}
			catch(ex)
			{	   			 
				selectDesgn.add(y);	   			 				 
			}   
			try
			{
				var dsgnCodeArr = XMLDoc.getElementsByTagName('dsgnCode');     
				var dsgnNameArr = XMLDoc.getElementsByTagName('dsgnName'); 
				for ( var i = 0 ; i < dsgnCodeArr.length ; i++ ) 
				{	     		     								
					value=dsgnCodeArr[i].childNodes[0].text;	     				    
					textStr=dsgnNameArr[i].childNodes[0].text;
					
					var y=document.createElement('option');
					y.text=textStr;
					y.value=value;	
					tempArray[i+1] = y ; 		 					
					try
					{
						selectDesgn.add(y,null); 	    						
					}
					catch(ex)
					{	   			 
						selectDesgn.add(y);	   			 				 
					}     						     					
				}
			}catch(ex){}
			if( (cacheKey != "") && (cachedData.get(cacheKey) == undefined) )
			{
				cachedData.add(cacheKey,tempArray);
				cacheKey = "";
			} 
		}			
	}
}
function isCachedData(srcLevelId,nextLevelId)
{
	cacheKey = "";
	cacheKey = "Designation_" + srcLevelId.value;	
	if(cachedData.get(cacheKey) != undefined )
	{
		return true;
	}
	else
	{	
		return false;
	}
}
function populateDropDownWithCachedData(key,dropDownId)
{
	var dropDown = document.getElementById(dropDownId);

	for (var i=dropDown.length;i>0;i--)  // removing a previous value of combo
	{	     				     					
		dropDown.remove(dropDown.value);
	}
	
	var cacheObj = cachedData.get(key);
	var cachedArray = cacheObj[key];	
	for ( var i = 0 ; i < cachedArray.length ; i++ )
	{
		var temp = cachedArray[i]
		var myOpt = new Option(temp.text,temp.value)
		dropDown.options[(i)] = myOpt;	
	}
}

function empSearch(from)
{
	for(var i=0; i<from.length; i++)
	{
		empArray[i] = from[i].split("~"); 		
	}	
	if(from.length>0)
	{
		var single = empArray[0];
		userId=single[2];
		if(EmpFlag==false)
		{
			offUserId=eval(single[2]);
			document.getElementById('officerName').value=single[1];
			document.getElementById('officerDesi').value=single[7];			
		}
		else {
			document.getElementById('selectUserId').value=single[2];
			document.getElementById('userName').value=single[1];
		}		
	}
}
function AddUser()
{
	if(offUserId!=0)
	{
		var str = document.getElementById('groupCmb').value;	
		var validateArrName = new Array('roleCmb','startDate','endDate','groupCmb','year');
		if(str=='BRANCH'){
			validateArrName.push('branch');
		}
		if(str=='DSGN'){
			validateArrName.push('branch');
			validateArrName.push('selectDesgn');
		}		
		if(str=='EMP')
		{
			if(document.getElementById('selectUserId').value==0 && EmpFlag!=true)
			{	
				alert("<fmt:message  bundle="${commonLables1}" key="HR.ACR.SetUserHie"/>");			
				return;
			}
		}	
		var ans = validateSpecificFormFields(validateArrName);	
		if(ans==true)
		{	
			if(EmpFlag==false)
			{
				var sDate = document.HierarchyGroupMst.startDate.value;
				var eDate= document.HierarchyGroupMst.endDate.value;
				ans = acrDateDiff(sDate,eDate);
				if(ans==false)
				{
					alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.properDate"/>');	
					document.HierarchyGroupMst.startDate.value='';
					document.HierarchyGroupMst.endDate.value='';
					return;
				}
				else
				{
					var sCheckDate = "01/"+"04/"+currentYear;
					var eCheckDate = "31/"+"03/"+parseInt(currentYear+1);
					ans = checkDateInBetween(sDate,eDate,sCheckDate,eCheckDate);
					if(ans==false)
					{
						alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.betweenDate"/>'+sCheckDate + " - " + eCheckDate);
						document.HierarchyGroupMst.startDate.value='';
						document.HierarchyGroupMst.endDate.value='';
						return;
					}
					else
					{
						var totaldays = getTotalDays(sDate,eDate);
						if(totaldays>=90 || totaldays>='90'){}
						else {
							ans=false;
							alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.totalNoDays"/>' + " : " + totaldays);
							document.HierarchyGroupMst.startDate.focus();
							return;
						}					
					}
				}
			}
			if(str!='EMP')
			{
				if(document.getElementById('branch').value=='')
				{
					alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectBranch"/>');
					return;
				}
				else if(document.getElementById('selectDesgn').value=='' && str=='DSGN')
				{
					alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectDesgn"/>');
					return;
				}			
			}		
			getUId123(offUserId);	
		}			
	}
	else
	{
		alert("<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserName"/>");	
		searchForEmp(0);
	}
}
function searchForEmp(state)
{
	if(state==1){EmpFlag=true;}
	else {EmpFlag=false;}
	var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
	window.open(href,'cheild', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');	
}
function getUId123(uid)
{
		offUserId=0;
		document.getElementById('officerName').value='';
		document.getElementById('officerDesi').value='';
	    host1=uid;
	    document.getElementById('selectedOff').value=uid;
	    document.getElementById('finalXmlData').value=finalSubmitArr;
	    var groupTypCmbValue = document.getElementById('groupCmb').value;
	    if(groupTypCmbValue=='BRANCH')
	    {
	    	var fieldArr= new Array('groupCmb','selectedOff','branch','year','roleCmb','startDate','endDate','R1','finalXmlData');
	    }
	    else if(groupTypCmbValue=='DSGN')
	    {
	    	var fieldArr= new Array('groupCmb','selectedOff','branch','year','roleCmb','startDate','endDate','R1','selectDesgn','finalXmlData');
	    }
	    else if(groupTypCmbValue=='EMP')
	    {
	    	var fieldArr= new Array('groupCmb','selectedOff','branch','year','roleCmb','startDate','endDate','R1','finalXmlData','selectUserId');	
	    }
	    addOrUpdateRecord('processResponse12','ACRInitiateChangeAjax',fieldArr,true);		
}
function processResponse(xmlStr)
{	
				var XMLDoc=getDOMFromXML(xmlStr); 	
					    					
		    	var PSOName = XMLDoc.getElementsByTagName("PSOName");
		    	var district = XMLDoc.getElementsByTagName("district");	
    	        var PSName = XMLDoc.getElementsByTagName("PSName");
    	        var GPFNo = XMLDoc.getElementsByTagName('GPFNo');
    	        var desgn=	XMLDoc.getElementsByTagName('Desgn');
    	        var xml = XMLDoc.getElementsByTagName('XML');
    	        var finalXml = XMLDoc.getElementsByTagName('SUBMITXML');
    	        var noFound = XMLDoc.getElementsByTagName('NOFOUND');    	        
    	        finalSubmitArrCounter=0;
    	        finalSubmitArr= new Array();
    	        for (var i=0;i<finalXml.length;i++)
    	        {    	        	    	        	
    	        	finalSubmitArr[finalSubmitArrCounter]=finalXml[i].childNodes[0].text;    	        	
    	        	finalSubmitArrCounter++;
    	        }  
    	        if(noFound[0]!=null)
    	        {
    	        	alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.HieUserNotGound"/>');
    	        }
			    var PSONameValue='',distValue='',PSNameValue='',GPFNoValue='',desgnValue='';	           					    	      	    	 				    	     		     							
				for ( var i = 0 ; i < PSOName.length ; i++ )
   				{	     		     								
   				    value=PSOName[i].childNodes[0].text;		     				       				    
   					PSONameValue=value;
   				}
         		for ( var i = 0 ; i < district.length ; i++ )
   				{	     		     								
   				    value=district[i].childNodes[0].text;		     				           				    
   					distValue=value;
   				}
         		for ( var i = 0 ; i < PSName.length ; i++ )
   				{	     		     								
   				    value=PSName[i].childNodes[0].text;	 	     				   
   					PSNameValue=value;							     					
         		}
         		for ( var i = 0 ; i < GPFNo.length ; i++ )
   				{	     		     								
   				    value=GPFNo[i].childNodes[0].text;		     				          				    
   					GPFNoValue=value;
   				}
         		for ( var i = 0 ; i < desgn.length ; i++ )
   				{	     		     								
   				    value=desgn[i].childNodes[0].text;	     				   
   					desgnValue=value;	     											     					
         		}
         		var oldId='';
         		var indexBranch=1;         		
         		showProgressbar("Please Wait... ");	
         		
         		for ( var i = 0 ; i < xml.length ; i++ )
   				{	     		     	   											
   				    xmlFileName=xml[i].childNodes[0].text;   				     				      					   				       					   				  
   					xmlHttp=GetXmlHttpObject();
					if (xmlHttp==null) 
					{
					  hideProgressbar();
					  alert ("Your browser does not support AJAX!");
					  return;
					}		
					
					var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
					xmlHttp.open("POST",encodeURI(url),false);
					
					xmlHttp.onreadystatechange = function() 
					{
						if(xmlHttp.readyState == 4) 
						{
							dscXML=xmlHttp.responseText;
		    	        	var flag =false;		    	        	
							if(flag==true)
							{
								alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.UserAlreadySelected"/>');
							}
							else
							{						
								var tableId='currentTable';
								var hiddenField = 'encXML';						
								
								var xmlDOM = getDOMFromXML(dscXML,'HierachyGrpEntryVO');
										
								var branch=xmlDOM.getElementsByTagName('branch');
								branchValue=branch[0].childNodes[0].text;	
								hideShowRow(branchValue,2);
								
								var branchName=xmlDOM.getElementsByTagName('branchName');
								branchNameValue=branchName[0].childNodes[0].text;		
																
								var newDesgn=xmlDOM.getElementsByTagName('designation');
								newDesgnValue=newDesgn[0].childNodes[0].text;
								
								var userId=xmlDOM.getElementsByTagName('userId');
								userIdValue=userId[0].childNodes[0].text;

								var empName=xmlDOM.getElementsByTagName('empName');
								empNameValue=empName[0].childNodes[0].text;
								
								var GpfNo=xmlDOM.getElementsByTagName('gpfNo');
								GpfNoValue=GpfNo[0].childNodes[0].text;
								
								var selfApp = xmlDOM.getElementsByTagName('selfApp');
								selfAppValue=selfApp[0].childNodes[0].text;
								
								var year = xmlDOM.getElementsByTagName('year');
								yearValue=year[0].childNodes[0].text;
																
								var pkData = xmlDOM.getElementsByTagName('pk');
								var pk=pkData[0].childNodes[0].text; 
								
								try {
									var pkData = xmlDOM.getElementsByTagName('hieType');
									rowColor=pkData[0].childNodes[0].text; 									
								}catch (rowEX){rowColor=0;}
								
								rowId = branchValue+"/"+ newDesgnValue+"/"+userIdValue;		
								
								var flag = false;
								var insertRowIndex =-1;
								for(var x=0;x<allRow.length;x++)
								{
									if(allRow[x]==rowId)
									{
										var delRow = document.getElementById(rowId);
										insertRowIndex=delRow.rowIndex;
										delRow.parentNode.deleteRow(delRow.rowIndex);												
										allRow.splice(x,1);
										allRowCount--;
										for(var x1=0;x1<totalBranchInCurrTable.length;x1++)
										{
											var reportArrSplit = totalBranchInCurrTable[x1].split('^');												
											if(reportArrSplit[1]==rowId)
											{										
												totalBranchInCurrTable.splice(x1,1);
												totalBranchInCurrTableCount--;
											}
										}										
									}
								}					
								var branchFlag = false;
								for(var x=0;x<totalBranchInCurrTable.length;x++)
								{
									var reportArrSplit = totalBranchInCurrTable[x].split('^');												
									if(reportArrSplit[0]==branchValue)
									{	
										ele = document.getElementById(reportArrSplit[1]);
										if(ele!=null && ele!='' && ele!='null')
										{
											insertRowIndex=	document.getElementById(reportArrSplit[1]).rowIndex;										
											insertRowIndex++;											
										}
										oldId=reportArrSplit[1];										
										branchFlag = true;
									}
								}
								if(branchFlag==false)
								{
									oldId=rowId;
									totalBranchInCurrTable[totalBranchInCurrTableCount]=branchValue+"^"+rowId;
									totalBranchInCurrTableCount++;								
								}
								if(flag==false)
								{
									allRow[allRowCount]=rowId;
									allRowCount++;
									
									if(insertRowIndex==-1)
										var trow=document.getElementById(tableId).insertRow();									
									else
										{var trow=document.getElementById(tableId).insertRow(insertRowIndex);}									
									trow.id = rowId;
									if(rowColor==1)
									{
										trow.className = "branchCSS"; 
									}
									else if(rowColor==3)
									{
										trow.className = "empCSS"; 
									}
									else if (rowColor==2)
									{
										trow.className = "dsgnCSS"; 
									}
									rowColor=0;
									trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" +rowId+ "' value='" + xmlFileName + "'/>";				
									trow.cells[0].style.display = 'none';	
															
									if(branchFlag==true)
									{
										//var tdB=document.createElement("td");	
										//tdB.innerHTML=branchNameValue;
										//var trowB = document.getElementById(oldId);																												
										//tdB.innerHTML = trowB.innerHTML;
										//indexBranch++;									
										//tdB.rowSpan=indexBranch;									
										//tdB.setAttribute("rowSpan", indexBranch);										
										//trowB.cells[1].innerHTML="<a href=javascript:void('view') title='"+branchNameValue+"' onclick=javascript:hideShowRow('"+ branchValue +"','1',this)>"+tdB.innerHTML+"</a>"; 
										trow.insertCell(1).innerHTML="";
										trow.cells[1].width="10%";								
									}
									else
									{																				
										//trow.insertCell(1).innerHTML=branchNameValue;
										trow.insertCell(1).innerHTML="<a href=javascript:void('view') title='"+branchNameValue+"' onclick=javascript:hideShowRow('"+ branchValue +"','1',this)>"+branchNameValue+"</a>"; 
										trow.cells[1].width="10%";
									}							
									trow.insertCell(2).innerHTML=newDesgnValue;
									trow.cells[2].width="10%";									
									var td1=document.createElement("td");
									td1.cellpadding="0";
									td1.colSpan="2";								
									td1.width="20%";
									td1.cellspacing="0";
									
									var y=document.createElement("table");
									y.id="user"+userIdValue;
								    y.width="100%";
								    							    
								    y.border="0";
								    y.cellpadding="0";
								    y.cellspacing="0";
								   								   									
								   	var tr=document.createElement("tr");
								   										
								   	var td=document.createElement("td");							    
								   	td.width="50%";
								   	td.innerHTML=empNameValue;
								   										
								    var td2=document.createElement("td");							   	
								    td2.width="50%";
								   	td2.innerHTML=GpfNoValue;
									
									tr.appendChild(td);
								   	tr.appendChild(td2);
								   	y.appendChild(tr);
								   	td1.appendChild(y);
								   	trow.insertCell(3).innerHTML =td1.innerHTML;								   									   								   									   									   
									
								   	var len=getChildNodeLengnthOfGivenSet(xmlDOM, 'hierachyGepDtlVO');								   	
								   	var roleTypeValue='';
							   		var totalOffRowCount=4;									
								   	for(totalOffCountI=0;totalOffCountI<=totalOffCount;totalOffCountI++)
									{
										trow.insertCell(totalOffRowCount).innerHTML="";
										totalOffRowCount++;
									}
									for(var j=0;j<len;j++)
									{		
										var parentNode='hierachyGepDtlVO['+j+']';
										var disValue=getXPathValueFromDOM(xmlDOM, parentNode+'/designation');
										var nameValue=getXPathValueFromDOM(xmlDOM, parentNode+'/empName');
										var startDValue=getXPathValueFromDOM(xmlDOM, parentNode+'/startDate');
										var endDValue=getXPathValueFromDOM(xmlDOM, parentNode+'/endDate');
										roleTypeValue =getXPathValueFromDOM(xmlDOM, parentNode+'/roleType');
												
										var mainTD=document.createElement("td");
										mainTD.cellpadding="0";
										mainTD.colSpan="4";											
										mainTD.width="20%";								
										mainTD.cellspacing="0";
										
										var roleTable = document.getElementById(userIdValue+'_'+roleTypeValue);										
										if(roleTable==undefined || roleTable=='null' || roleTable==null || roleTable=='' )
										{																																										
											var y=document.createElement("table");
											y.id=userIdValue+'_'+roleTypeValue;
										    y.width="100%";
										    y.border="0";
										    y.cellpadding="0";
										    y.cellspacing="0";
										    											
											
										   	var trR=document.createElement("tr");
										   										
										   	var tdR=document.createElement("td");
										   	tdR.width="25%";
										   	tdR.innerHTML=nameValue;
										   										
										    var td2R=document.createElement("td");							   	
										    td2R.width="25%";
										   	td2R.innerHTML=disValue;
											
											var td3R=document.createElement("td");							    
											td3R.width="25%";
										   	td3R.innerHTML=startDValue;
										   										
										    var td4R=document.createElement("td");							   	
										    td4R.width="25%";
										   	td4R.innerHTML=endDValue;
										   	
											trR.appendChild(tdR);
										   	trR.appendChild(td2R);
									   		trR.appendChild(td3R);
									   		trR.appendChild(td4R);
										   	y.appendChild(trR);									
										   	mainTD.appendChild(y);
										   	
										}
										else
										{
											var roleTableRow=document.createElement("tr");
											var tdR=document.createElement("td");tdR.width="25%";
											var td2R=document.createElement("td");td2R.width="25%";
											var td3R=document.createElement("td");td3R.width="25%";
											var td4R=document.createElement("td");td4R.width="25%";
																								
										   	tdR.innerHTML =nameValue;
										   	td2R.innerHTML =disValue;
										   	td3R.innerHTML =startDValue;
										   	td4R.innerHTML =endDValue;
										   	
										   	roleTableRow.appendChild(tdR);
										   	roleTableRow.appendChild(td2R);
									   		roleTableRow.appendChild(td3R);
									   		roleTableRow.appendChild(td4R);
										   	roleTable.appendChild(roleTableRow);
										   	mainTD.appendChild(roleTable);
										}
										var totalOffRowCount=4;
										if(totalOffCount==-1)
										{
											totalOffRowCount=4;
										}
										else									
										{
											for(totalOffCountI=0;totalOffCountI<=totalOffCount;totalOffCountI++)
											{
												var reportArrSplit = totalOffArr[totalOffCountI].split('/');												
												if(reportArrSplit[0]==roleTypeValue)
													{trow.cells[reportArrSplit[1]].innerHTML=mainTD.innerHTML;}												
												totalOffRowCount++;
											}
										}										
									}									
									trow.insertCell(totalOffRowCount).innerHTML="<INPUT type=radio  name='rad' id='rad"+j+"_"+userIdValue+"' value='"+dscXML+"' onclick='actionOnRadioFoc(this)' />"
																				+"<INPUT type=hidden  name='rad1' id='rad"+j+"_"+userIdValue+"rowId' value='"+rowId+"' />"
																				+"<INPUT type=hidden  name='pk1' id='rad"+j+"_"+userIdValue+"pk' value='"+pk+"' />"
																				+"<INPUT type=hidden  name='year1' id='rad"+j+"_"+userIdValue+"year' value='"+yearValue+"' />"
																				+"<INPUT type=hidden  name='self1' id='rad"+j+"_"+userIdValue+"self' value='"+selfAppValue+"' />";	
									trow.cells[totalOffRowCount].align="center";
									trow.cells[totalOffRowCount].width="10%";
									totalOffRowCount++;
									trow.insertCell(totalOffRowCount).innerHTML=selfAppValue;
									trow.cells[totalOffRowCount].width="10%";
								}
							}							
						}
					}
					xmlHttp.send(null);										
				}
				
				/*var table = document.getElementById("currentTable");   
			    var rows = table.getElementsByTagName("tr");   
			    for(i = 2; i < rows.length; i++)
			    {           			    	
			    	if(i % 2 == 0){ 
				       	rows[i].className = "even"; 
			    	}else{ 
				       rows[i].className = "odd"; 
			    	}       
			    }   */ 
				hideProgressbar();
}
function hideShowRow(branchCode,hideShow,obj)
{		
	var branchNameValue='a';
	if(obj!=undefined)
	{
		branchNameValue = obj.title;
	}
	var insertRowIndex=0;
	var tableId = 'currentTable';
	var tab = document.getElementById(tableId);
	var allTr = tab.getElementsByTagName('tr');
	for (var lCntr = 0; lCntr < allTr.length; lCntr++)
	{
		if(allTr[lCntr].id.search(branchCode)!=-1) 
		{						
			if(hideShow==1)
			{				
				if(insertRowIndex==0){insertRowIndex=allTr[lCntr].rowIndex }
				allTr[lCntr].style.display='none';
			}
			else
			{
				allTr[lCntr].style.display='';			
			}
		}		
	}
	if(hideShow==1)
	{
		var trow=document.getElementById(tableId).insertRow(insertRowIndex);
		trow.id = "B_"+branchCode;
		trow.insertCell(0).innerHTML="<a href=javascript:void('view') onclick=javascript:hideShowRow('" + branchCode +"','2')>"+branchNameValue+"</a>";
		trow.cells[0].colSpan = "10";
		//trow.cells[0].align="center";
	}
	else
	{
		try {	
			var delRow = document.getElementById("B_"+branchCode);
			delRow.parentNode.deleteRow(delRow.rowIndex);
		}catch (e){}
	}
}
function processResponse12()
{
		if (xmlHttp.readyState == 4) 
		{    
			if (xmlHttp.status == 200) 
			{       
				var xmlStr;			
				if(xmlResponseData=="")		
				{	
					xmlStr = xmlHttp.responseText;				    						
				}
				else
				{
					xmlStr =xmlResponseData;					
					xmlResponseData="";
				}
				processResponse(xmlStr);		    		
			}												
			else 
			{  			
				//alert("ERROR");					
			}
		}			
}
function actionOnRadioFoc(obj)
{
	var rowId =document.getElementById(obj.id+"rowId").value;
	var year =document.getElementById(obj.id+"year").value;
	var selfAppFlag =document.getElementById(obj.id+"self").value;
	document.getElementById('finalXmlData').value=finalSubmitArr;
	var rowIdObj = document.getElementById(rowId);
	//document.getElementById('xmlUserData').value=obj.value;		
	xmlUserData1=obj.value;	
	obj.status = true;
	var splitArr =obj.id.split('_');		
	mstPk = document.getElementById(obj.id+"pk").value;	
	xmlUserData=obj.value;
	win=window.open("hrms.htm?actionFlag=editSetHierachyInAcr&selfAppFlag="+selfAppFlag+"&year="+year+"&userId="+splitArr[1]+"&XmlData="+finalSubmitArr,"editHierachyDetails","width=800,height=600,toolbar=no,status=yes,menubar=no,minimize=no,resizable=no,top=100,left=100,dependent=yes");
	if (win.opener == null) {win.opener = self;	}	
	win.focus();
}
function deleteReviewOff(rowId)
{
	var  updateRowValue =updateRow;
	updateRow = null;
	var ans = deleteRecord(rowId);
	updateRow=updateRowValue;
	if(ans==true)
	{
		for(var x=0;x<ReviewingOffArr.length;x++)
		{
			var reportArrSplit = ReviewingOffArr[x].split('/');
			if(reportArrSplit[1]==rowId)
			{
				ReviewingOffArr.splice(x,1);				
				if(ReviewingOffCount>0)
					ReviewingOffCount--;
				else
					ReviewingOffCount=0;
			}
		}
	}
}
function deleteReportOff(rowId)
{
	var  updateRowValue =updateRow;
	updateRow = null;
	var ans = deleteRecord(rowId);
	updateRow=updateRowValue;
	if(ans==true)
	{
		for(var x=0;x<ReportingOffArr.length;x++)
		{
			var reportArrSplit = ReportingOffArr[x].split('/');
			if(reportArrSplit[1]==rowId)
			{
				ReportingOffArr.splice(x,1);				
				if(ReportingOffCount>0)
					ReportingOffCount--;
				else
					ReportingOffCount=0;
			}
		}
	}
}		   		
function checkRecordCount()
{	
	if(finalSubmitArrCounter!=0)
	{	
		if(confirm('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SubmitForm"/>')==true)		
		{
			showProgressbar("Please Wait... ");	
			submit1();
		}
	}
	else
	{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.AddGroup"/>');
			return;
	}
}
function submit1()
{
	//alert(finalSubmitArr);
	//if(confirm('Do You want to submit form?')==true)
	{
		var rdo = document.getElementsByName("rad");
		for(var j = 0; j < rdo.length; j++) 
		{
			rdo[j].value="";			
		}
		document.HierarchyGroupMst.method="POST";		
		document.HierarchyGroupMst.action="./hrms.htm?actionFlag=ACRHiGroupSubmit&finalData="+finalSubmitArr;	
		document.HierarchyGroupMst.submit();
	}
}
function setAppraisalFlag(button)
{
	document.getElementById("selfAppraisalFlag").value=button.value;		
}
function refreshPage()
{
	document.HierarchyGroupMst.method="POST";
	document.HierarchyGroupMst.action="./hrms.htm?actionFlag=ACRHiGroupMstPage";	
	document.HierarchyGroupMst.submit();	
}
function closeButtonHandler()
{
	document.HierarchyGroupMst.method="POST";
	document.HierarchyGroupMst.action="./hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";	
	document.HierarchyGroupMst.submit();
	
}
function getAllOficers(branch)
{

	var a=document.getElementById("selectDesgn");
	var b=document.getElementById("year");
	var c=document.getElementById("branch");
	var d=document.getElementById("locationCode");
	deleteAllPrevData();
	if(c.selectedIndex!=0)
	{
		if(a.selectedIndex==0||b.selectedIndex==0)
		{
			alert('<fmt:message  bundle="${commonLables1}" key="HR.ACR.SelectDesgnYearFirst"/>');
			return;
		}
		else
		{
				
				var locCode=d.value;
				var desgnCode=a.value;
				var year=b.value;
				var branchCode=c.value;
				try{   
    				xmlHttp=new XMLHttpRequest();    
	    		}
				catch (e)
				{    // Internet Explorer    
						try{
      						xmlHttp=new 
                       	 ActiveXObject("Msxml2.XMLHTTP");      
      					}
		    			catch (e){
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
        	
			var url = "hrms.htm?actionFlag=ACRGetAllOfficers&locCode="+locCode+"&desgnCode="+desgnCode+"&branchCode="+branchCode+"&year="+year;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processAllOfficers;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));
		}
	}
	
}
function processAllOfficers()
{						
			var name=new Array();
			var userId=new Array();
			var branchCode=new Array();
			if (xmlHttp.readyState == 4) 
			{
				if (xmlHttp.status == 200) 
				{						
						var textId;												
		            	var table=document.getElementById('officerTable');	
				    	var xmlStr = xmlHttp.responseText;
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    	
				    	var NameStr = XMLDoc.getElementsByTagName('Name');				    	
				    	var UserStr = XMLDoc.getElementsByTagName('UserId'); 
				    	 var Br = XMLDoc.getElementsByTagName('BranchCode'); 
				    	    		    						
						for ( var i = 0 ; i < NameStr.length ; i++ )
	     				{	     		     								
	     				    value=NameStr[i].childNodes[0].text;	     				    	     				
	     					name[i]=value;		     					
	           			}
	           			for ( var i = 0 ; i < UserStr.length ; i++ )
	     				{	     		     								
	     				    value=UserStr[i].childNodes[0].text;	     				    
	     				    
	     					userId[i]=value;		     					
	           			}
	           			for ( var i = 0 ; i < UserStr.length ; i++ )
	     				{	     		     								
	     				    value=Br[i].childNodes[0].text;	     				    
	     				    
	     					branchCode[i]=value;		     					
	           			}
	           			//inserting row into table dynamically
	           			
	           			for(var i = 0; i < userId.length ; i++)
	           			{
	           				var trow=table.insertRow();
	           				trow.id = 'userrow'+i;
	           				trow.insertCell(0).innerHTML="<INPUT type=checkbox  name='chk" + i + "' id='checkBox" +i+ "' value='"+userId[i]+"'/>";
	           				trow.insertCell(1).innerHTML=i+1;
	           				trow.insertCell(2).innerHTML=name[i];
	           				trow.insertCell(3).innerHTML=userId[i];
	           				trow.insertCell(4).innerHTML="<INPUT type='hidden' name='chkHidden" + i + "' id='chkHidden"+i+"' value='" + name[i] + "'/>";
	           				trow.insertCell(5).innerHTML="<INPUT type='hidden' name='chkHiddenBr" + i + "' id='chkHiddenBr"+i+"' value='" + branchCode[i] + "'/>";	           				
	           				trow.cells[4].style.display = 'none';
	           				trow.cells[5].style.display = 'none';
	           			}
	           			document.getElementById('allOfficerCount').value=userId.length;
				}
				else 
				{  			

				}
			}
}

function showOfficerTable()
{		
	var table=document.getElementById("officerTable");
	if (table.style.display == '')  
	{
	
		table.style.display = 'none';
		document.getElementById("img_see").src="./images/greenDown.gif";
	}
	else
	{

		 table.style.display = '';
		 document.getElementById("img_see").src="./images/redUp1.gif";
		 
 		
	}
}
function deleteAllPrevData()
{	
		var count=document.getElementById('allOfficerCount').value;
		for(var i=0;i<count;i++)
		{
			var delRow = document.getElementById('userrow'+i);
			delRow.parentNode.deleteRow(delRow.rowIndex);
		}
		document.getElementById('checkAllBox0').status=false;
		document.getElementById('officerTable').style.display='none';
		document.getElementById('allOfficerCount').value=0;
		document.getElementById("img_see").src="./images/greenDown.gif";
}

function openHierachy()
{
	win=window.open("hrms.htm?actionFlag=viewSetHierachyInAcr","AcrHierachyDetails","width=800,height=600,toolbar=no,status=yes,menubar=no,top=100,left=100,dependent=yes,scrollbars=yes,minimize=no,resizable=no");
	if (win.opener == null) {win.opener = self;	}	
	win.focus();
}
</script>


<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="acrRole" value="${resValue.acrRole}"></c:set>
<c:set var="year" value="${resValue.year}"></c:set>
<c:set var="lstOfAllBranch" value="${resValue.listOfAllBranch}"></c:set>
<c:set var="allPrvData" value="${resValue.allPrvData}"></c:set>
<c:set var="finalXMLData" value="${resValue.finalXMLData}"></c:set>
<body>
<hdiits:form name="HierarchyGroupMst" method="POST" validate="true" encType="multipart/form-data">
	<div id="tabmenu">
		<ul id="maintab" class="shadetabs">
			<li class="selected"><a href="#" rel="tcontent1"> <b> <fmt:message key="HR.ACR.ACR" bundle="${commonLables}" /> </b> </a></li>
		</ul>
	</div>
<div class="tabcontentstyle">
	<div id="tcontent1" class="tabcontent" tabno="0">
	<hdiits:fieldGroup collapseOnLoad="false" id="acr_hie_field1" titleCaptionId="HR.ACR.GroupDetails" bundle="${commonLables}">				
		<table id="default" width="100%">			
			<tr id="asdfkjasldf">
				<td width="25%" align="left"><b><hdiits:caption
					captionid="HR.ACR.Location" bundle="${commonLables}"/></b>
				</td>
				<td width="25%" align="left"><c:out
					value="${resValue.getLocation}" /></td>
				<td width="25%" align="left"><b><hdiits:caption
					captionid="HR.ACR.Year" bundle="${commonLables}" />:</b></td>
				<td width="25%" align="left">
					<c:out value="${year} - ${year+1}"></c:out>
					<hdiits:hidden name="year"  id="year" default="${year}"/>
					<script>
						currentYear = ${year};						
					</script>
				</td>
			</tr>						
		</table>	
		<BR>
		<table class="tabtable">
		<tr><td width="25%">			
			<hdiits:caption captionid="HR.ACR.HieType" bundle="${commonLables}"></hdiits:caption>
		</td>
		<td width="25%">
			<hdiits:select name="groupCmb" id="groupCmb" mandatory="true" onchange="showDataEntryTable(this);" validation="sel.isrequired" captionid="HR.ACR.HieType" bundle="${commonLables}">
				<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
				<option value="BRANCH"><fmt:message key="HR.ACR.BranchType" bundle="${commonLables}" /></option>
				<option value="DSGN"><fmt:message key="HR.ACR.DsgnType" bundle="${commonLables}" /></option>
				<option value="EMP"><fmt:message key="HR.ACR.EmpType" bundle="${commonLables}" /></option>
			</hdiits:select>
			
		</td>
			<td width="25%" id="groupTD1" style="display:none"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}" /></td>
			<td width="25%" id="groupTD2" style="display:none"><hdiits:text name="userName" id="userName" default="" readonly="true" style="background-color:lightblue"></hdiits:text>
					<hdiits:hidden id="selectUserId" name="selectUserId" default="0"></hdiits:hidden>
					&nbsp;<hdiits:image id="imgUser" tooltip="Search User" onmouseover="showCursorAsHand(this)"
						source="./images/search_icon.gif" onclick="searchForEmp(1);" >
					</hdiits:image>
			</td>
			<td width="25%" id="groupTD3"></td>
			<td width="25%" id="groupTD4"></td>
		</tr>
		</table>
		<table class="tabtable">
		<tr>
			<td width="25%" id="branchTD1" align="left" style="display:none"><b><hdiits:caption
					captionid="HR.ACR.Branch" bundle="${commonLables}" /></b></td>
			<td id="branchTD2" width="25%" align="left" style="display:none"><hdiits:select multiple="true"
					captionid="HR.ACR.Branch" bundle="${commonLables}" name="branch" 
					id="branch" mandatory="true" sort="true" validation="sel.isrequired"
					onchange="getAllDesignation(this);" >
					<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
					<c:forEach var="branch" items="${lstOfAllBranch}">
						<option value="<c:out value="${branch.branchCode}" />"><c:out
							value="${branch.branchName}" /></option>
					</c:forEach>
			</hdiits:select></td>
			<td width="25%" id="dsgnTD1" align="left" style="display:none"><b><hdiits:caption
					captionid="HR.ACR.Designation" bundle="${commonLables}" />:</b></td>
			<td width="25%" align="left" id="dsgnTD2" style="display:none"><hdiits:select  multiple="true"
					captionid="HR.ACR.Designation" bundle="${commonLables}" validation="sel.isrequired"
					name="selectDesgn" id="selectDesgn" mandatory="true" sort="true">
					<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>				
			</hdiits:select></td>
			<td width="25%" id="branchTD3" style="display:none"></td>
			<td width="25%" id="branchTD4" style="display:none"></td>			
		</tr>
		</table>
		<table id="dataEntryTable" class="tabtable" style="display:none"><tr><td width="25%">
				<hdiits:caption captionid="HR.ACR.RoleSelect" bundle="${commonLables}"/>
			</td>
			<td width="25%">
				<hdiits:select name="roleCmb" id="roleCmb" mandatory="true" validation="sel.isrequired" captionid="HR.ACR.RoleSelect" bundle="${commonLables}">
					<option value="Select"><fmt:message key="HR.ACR.Select" bundle="${commonLables}" /></option>
					<c:forEach items="${acrRole}" var="role">
						<c:if test="${role.lookupName ne 'acrRoleSelf'}">
							<option value="<c:out value="${role.lookupName}" />"><c:out
								value="${role.lookupDesc}" /></option>
						</c:if>	
					</c:forEach>
				</hdiits:select>
			</td>
			<td width="25%"></td>
			<td width="25%"></td>
		</tr>
		<tr>
			<td width="25%"><hdiits:caption captionid="HR.ACR.StartDate" bundle="${commonLables}"></hdiits:caption></td>
			<td width="25%"><hdiits:dateTime name="startDate"  validation="txt.isdt,txt.isrequired" captionid="HR.ACR.StartDate" mandatory="true" bundle="${commonLables}" afterDateSelect="" maxvalue="01/01/2999"></hdiits:dateTime></td>		
			<td width="25%"><hdiits:caption captionid="HR.ACR.EndDate" bundle="${commonLables}"/></td>
			<td width="25%"><hdiits:dateTime name="endDate" validation="txt.isdt,txt.isrequired" captionid="HR.ACR.EndDate" mandatory="true" bundle="${commonLables}" maxvalue="01/01/2999"></hdiits:dateTime></td>				
		</tr>	
		<tr>													
				<td width="25%" align="left"><hdiits:caption captionid="HR.ACR.OffName" bundle="${commonLables}" /></td>
				<td width="25%" align="left"><hdiits:text name="officerName" id="officerName" default="" readonly="true" style="background-color:lightblue" />
					<hdiits:image id="imgOff" tooltip="Search Officers" onmouseover="showCursorAsHand(this)"
						source="./images/search_icon.gif" onclick="searchForEmp(0);" ></hdiits:image>
				</td>
				<td width="25%" align="left"><hdiits:caption captionid="HR.ACR.OffDesignation" bundle="${commonLables}" /></td>
				<td width="25%" align="left">
					<hdiits:text name="officerDesi" id="officerDesi" default="" readonly="true" style="background-color:lightblue" />
				</td>
		</tr>		
		<tr>
			<td width="25%" align="left"><b><hdiits:caption captionid="HR.ACR.CheckForSelfAppraisal" bundle="${commonLables}" /></b></td>
			<td width="25%" align="left">
			<hdiits:radio name="R1" value="N" id="Rno" captionid="HR.ACR.ButtonNo" bundle="${commonLables}" onfocus="" default="N" />&nbsp;&nbsp;
			<hdiits:radio name="R1" value="Y" id="Ryes" onfocus="" captionid="HR.ACR.ButtonYes" bundle="${commonLables}"/></td>
			<td width="25%" align="left"></td>
			<td width="25%" align="left"></td>
		</tr>
		<tr align="center">
			<td align="center" colspan="10">
				<hdiits:button type="button" name="btnSearch" onclick="AddUser()" captionid="HR.ACR.BtnAdd" bundle="${commonLables}" id="btnSearch"/>
				<hdiits:button type="button" id="btnReset" captionid="HR.ACR.Reset" bundle="${commonLables}" name="btnReset" onclick="resetHiePage();"/>			
			</td>
		</tr>
		</table>
		<BR>
		<table align="center" border="0">		
		<tr align="center"><td colspan="10" align="center">
		<hdiits:button type="button" name="viewSetHierachy" onclick="openHierachy()" captionid="HR.ACR.BtnViewSetHierachy" bundle="${commonLables}" id="viewSetHierachy"/>
		</td></tr></table>
	</hdiits:fieldGroup>
	<BR><BR>
	<hdiits:fieldGroup collapseOnLoad="false" id="acr_hie_field2" titleCaptionId="HR.ACR.CurrTable" bundle="${commonLables}">				
		<table class="tabtable" name="currentTable" id="currentTable" border="1" bgcolor="white" bordercolor="black" cellpadding="0" cellspacing="0">
			<tr bgcolor="B0C4DE">
			<td colspan="2" align="center" width="20%">&nbsp;</td>
			<td colspan="1" align="center" width="20%"><hdiits:caption captionid="HR.ACR.EmpData" bundle="${commonLables}"/></td>
			<c:forEach items="${acrRole}" var="role">
				<c:if test="${role.lookupName ne 'acrRoleSelf'}">							
					<td align="center" colspan="1" width="20%"><c:out value="${role.lookupDesc} " /></td>
				</c:if>
			</c:forEach>
			<td colspan="2" align="center" width="20%">&nbsp;</td>
			</tr>
			<tr bgcolor="B0C4DE" align="center">
				<td width="10%"><hdiits:caption captionid="HR.ACR.Branch" bundle="${commonLables}"/></td>
				<td width="10%"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></td>
				<td width="20%">
				<table border="0" width="100%"><tr align="center">
				<td width="50%"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></td>								
				<td width="50%"><hdiits:caption captionid="HR.ACR.GPF" bundle="${commonLables}"/></td>	
				</tr></table></td>				
				<c:forEach items="${acrRole}" var="role">
					<c:if test="${role.lookupName ne 'acrRoleSelf'}">
					<td width="20%">
					<table border="0"><tr align="center">							
							<td width="25%"><hdiits:caption captionid="HR.ACR.Name" bundle="${commonLables}"/></td>									
							<td width="25%"><hdiits:caption captionid="HR.ACR.Designation" bundle="${commonLables}"/></td>
							<td width="25%"><hdiits:caption captionid="HR.ACR.StartDate" bundle="${commonLables}"/></td>				
							<td width="25%"><hdiits:caption captionid="HR.ACR.EndDate" bundle="${commonLables}"/></td>
					</tr>
					</table>
					<script>
						totalOffCount++;
						var x =totalOffCount+4;
						totalOffArr[totalOffCount]='${role.lookupName}'+"/"+x;						
					</script>
					</td>					
					</c:if>						
				</c:forEach>								
				<td width="10%"><hdiits:caption captionid="HR.ACR.Action" bundle="${commonLables}"/></td>	
				<td width="10%"><hdiits:caption captionid="HR.ACR.SelfAppraisal" bundle="${commonLables}"/></td>					
			</tr>
		</table>
	</hdiits:fieldGroup>	
		<br>
		<table width="100%">
		<tr><td width="33%">
			<hdiits:text size="1" name="img1" style="BACKGROUND-COLOR: #E3E4FA" readonly="true"/> BRANCH Row Colour</td>
		<td width="33%">		
			<hdiits:text size="1" name="img2" style="BACKGROUND-COLOR: #F0FFFF" readonly="true"/> Designation Row Colour</td>
		<td width="34%">
			<hdiits:text size="1" name="img3" style="BACKGROUND-COLOR: #FFF8DC" readonly="true"/> Employee Row Colour</td>
		</tr></table>
		<br>
		<hdiits:hidden name="mstPK" id="mstPK"/>
		<hdiits:hidden name="selectedOff" id="selectedOff"/>
		<hdiits:hidden name="finalXmlData" id="finalXmlData" default=""/>
		<BR>
		<table align="center">
			<tr align="center" colspan="2">
				<td><hdiits:button captionid="HR.ACR.Submit"
					bundle="${commonLables}" name="Submit" type="button"
					onclick="checkRecordCount();" /></td>
				<td><hdiits:button captionid="HR.ACR.Close"
					bundle="${commonLables}" name="Close" type="button"
					onclick="closeButtonHandler();" /></td>
			</tr>
		</table>			
	</div>
	</div>	
<hdiits:hidden id="xmlUserData" name="xmlUserData" default=""></hdiits:hidden>	
</hdiits:form>
<script type="text/javascript">
		initializetabcontent("maintab")
		document.getElementById('imgUser').style.display='none';
</script>
<c:if test="${not empty allPrvData}">
	<c:forEach items="${allPrvData}" var="allData">
		<script type="text/javascript">			
			processResponse('${allData}');
		</script>	
	</c:forEach>
</c:if>													
<c:if test="${not empty finalXMLData}">
	<c:forEach items="${finalXMLData}" var="allData">
		<script type="text/javascript">
   	       finalSubmitArr[finalSubmitArrCounter]='${allData}';    	        	
   	       finalSubmitArrCounter++;	    	       
		</script>	
	</c:forEach>
</c:if>

<hdiits:validate locale="${locale}" controlNames="" />
</body>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>