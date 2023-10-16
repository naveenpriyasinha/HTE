<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="travelModeListT" value="${resValue.travelModeListT}"></c:set>


<script>
var totalT=0;
function startupAjaxFormValidationT(buttonName)
{

	if((document.forms[0].departurePlaceT.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.departurePlaceTReq"/>');	
		document.forms[0].departurePlaceT.focus();
		return;
	}
	else if ((document.forms[0].departureDateT.value)=='')
	{
	 	alert('<fmt:message  bundle="${commonLables}" key="HRMS.departureDateTReq"/>');
	 	document.forms[0].departureDateT.focus();
		return;
	}
	
	else if((document.forms[0].arrivalPlaceT.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.arrivedPlaceTReq"/>');
		document.forms[0].arrivalPlaceT.focus();
		return;
	}
	
	else if((document.forms[0].arrivalDateT.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.arrivedDateTReq"/>');
		document.forms[0].arrivalDateT.focus();
		return;
	}
	else if((document.forms[0].distanceInKmT.value)=='' || (document.forms[0].distanceInKmT.value)=='0' )
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.distanceInKmTReq"/>');
		document.forms[0].distanceInKmT.focus();
		return;
	}
	else if(isNaN(document.getElementById('distanceInKmT').value))
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.distanceInKmTReqFormat"/>');
		document.forms[0].distanceInKmT.value='';
		document.forms[0].distanceInKmT.focus();
		return;
	}
	else if((document.forms[0].travelModeT10.value)=='0')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.travelModeT10Req"/>');
		document.forms[0].travelModeT10.focus();
		return;
	}
	
	else if((document.forms[0].classT0.value)=='Select')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.classT0Req"/>');
		document.forms[0].classT0.focus();
		return;
	}
	else if((document.forms[0].totalT.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.totalTReq"/>');
		document.forms[0].totalT.focus();
		return;
	}
	else if(isNaN(document.getElementById('totalT').value))
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.totalTReqFormat"/>');
		document.forms[0].totalT.value='';
		document.forms[0].totalT.focus();
		return;
	}
	else
	{
	
	
	
	var finalTotal=0.0;
	var finalTotalG=0.0;
	var amountDue=0.0;
	
	var totalTransport=document.getElementById('totalTransport').value;
	var totalNew=document.getElementById('totalT').value;
	var grandTotal=document.getElementById('grandTotal').value;
	var sanctionedAmt=document.getElementById('sanctionedAmt').value;
	
	
	
	
	document.getElementById('arrivalDateT1').value=document.forms[0].arrivalDateT.value;
	document.getElementById('departureDateT1').value=document.forms[0].departureDateT.value;
	
	var addArray=new Array('departurePlaceT','departureDate1T','arrivalPlaceT','arrivalDateT1','distanceInKmT','travelModeT10',
	'classT0','totalT');
	var statusValidation = validateSpecificFormFields(addArray);
	if(statusValidation == true)
	{								
		if(buttonName == 'AddT')
		{		
			var num=0;			
			addOrUpdateRecord('addRecordT', 'RtaTransportDetails&flag=AddRtaTransportDtls&transportDetailId='+num,new Array('departurePlaceT',
			'departureDateT1','departureDateT','arrivalPlaceT','arrivalDateT1','arrivalDateT','distanceInKmT',
			'travelModeT10','classT0','totalT'));	
			finalTotal=eval(eval(totalTransport)+eval(totalNew));
			finalTotalG=eval(eval(grandTotal)+eval(totalNew));
			amountDue=eval(eval(finalTotalG)-eval(sanctionedAmt));
			document.getElementById("subButton").disabled=false;
			
		}
		else
		{

			var num = document.getElementById('transportDetailId').value;
			addOrUpdateRecord('updateRecordT','RtaTransportDetails&flag=AddRtaTransportDtls&transportDetailId='+num,new Array('departurePlaceT',
			'departureDateT1','departureDateT','arrivalPlaceT','arrivalDateT1','arrivalDateT','distanceInKmT',
			'travelModeT10','classT0','totalT'));
			finalTotal=eval(eval(totalTransport)+eval(totalNew)-eval(totalT));
			finalTotalG=eval(eval(grandTotal)+eval(totalNew)-eval(totalT));
			amountDue=eval(eval(finalTotalG)-eval(sanctionedAmt));
		}
	}
	document.getElementById('totalTransport').value=finalTotal;
	document.getElementById('grandTotal').value=finalTotalG;
	document.getElementById('dueAmt').value=amountDue;
	}
totalT=0;
}    //End of Function startupAjaxFormValidation()

//START OF CHECK DATE FUNCTION
function checkDateT()
{
	if( (document.forms[0].arrivalDateT.value!='') || (document.forms[0].departureDateT.value!='') )
	{
	var depDate=document.forms[0].departureDateT.value;
	var retDate=document.forms[0].arrivalDateT.value;
	
	
	//currDate=new Date('${currentDate}');
	
	//currMonth=currDate.getMonth()+1;

/*	if(currMonth < 10)
	{
		var sysDate= currDate.getDate().toString() + "/0" + currMonth.toString() + "/" + currDate.getYear().toString

();
	}
	else
		var sysDate= currDate.getDate().toString() + "/" + currMonth.toString() + "/" + currDate.getYear().toString

();
		
	if( (document.forms[0].dateOfDep.value!='') || (document.forms[0].dateOfDep.value=='') )
	{
		if( compareDate(depDate,sysDate) > 0 )
		{
			alert('<fmt:message  bundle="${commonLables}" key="HRMS.detDateGtSysDate"/>');
			document.getElementById('dateOfDep').value='';
			if( compareDate(retDate,sysDate) > 0 )
			{
				document.getElementById('retDate').value='';
			}
			return;
		}*/

		
		if( retDate!='' )
		{
			if( compareDate(retDate,depDate) > 0 )
			{
				alert('<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessDepDateT"/>');
				document.forms[0].arrivalDateT.value='';
				document.forms[0].arrivalDateT.focus();
				return;
			}
		}
				
	}
	/*if ( document.forms[0].retDate.value!='' )
	{
		if( depDate!='' )
		{
			if( compareDate(retDate,depDate) > 0 )
			{
				alert('<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessDepDate"/>');
				document.getElementById('arrivedDate').value='';
				return;
			}
		}
	}
	
	if( (document.forms[0].retDate.value!='') && (document.forms[0].dateOfDep.value!='') )
	{
	var Date1=depDate.split("/");
	var Date2=retDate.split("/");
	

	Date1=Date1[1]+"/"+Date1[0]+"/"+Date1[2];
	Date2=Date2[1]+"/"+Date2[0]+"/"+Date2[2];
	
	
	var dateDiff=dateDifferenceWOAlert(new Date(Date1),new Date(Date2));
	var dispDAString='${dAForCountry}'.toString()+"*"+dateDiff.toString();
	var dA='${dAForCountry}'*dateDiff;
	
	document.forms[0].stayDur.value=dateDiff;
	document.forms[0].disTotalDailyAll.value=dispDAString;
	document.forms[0].totalDailyAll.value=dA;
	document.forms[0].totalDailyAllRs.value=(dA)*(document.forms[0].rateOfDollar.value);
	}
	}*/

	else
	{
		return;
	}	
}

//END OF CHECK DATE FUNCTION

function addRecordT() 
	{   		  	    
	  	if (xmlHttp.readyState == 4)
	  	{ 	
	  		if (xmlHttp.status == 200) 
			{		
				
				var displayFieldArray=new Array('departurePlaceT','departureDateT1','arrivalPlaceT','arrivalDateT1'
				,'distanceInKmT','travelModeT10','classT0','totalT');
				var rowNum=addDataInTableAttachment('rtaTransportTable','encXMLT',displayFieldArray,'editRecordT','deleteRecordTransport','');														
				resetDataT(rowNum);				   	    	  															   	    	
	  	    }
	   	}			   	
	}			


function deleteRecordTransport(rowId,rowNum)
{
	
	
var trow = document.getElementById(rowId);

	var cells = trow.getElementsByTagName('td');
	

	var finalTotal=0.0;
	var sanctionedAmt=document.getElementById('sanctionedAmt').value;
	
	var totalTransport=eval(eval(document.getElementById('totalTransport').value)-eval(cells[8].innerText));
	var grandTotal=eval(eval(document.getElementById('grandTotal').value)-eval(cells[8].innerText));
	var amtDue=eval(eval(grandTotal)-eval(sanctionedAmt));
	var answer = deleteRecord(rowId);
	if(answer){
	
		document.getElementById("totalTransport").value=totalTransport;
		document.getElementById("grandTotal").value=grandTotal;
		document.getElementById("dueAmt").value=amtDue;
	}
	if(grandTotal=='0.0')
	{
		document.getElementById("subButton").disabled=true;
	}

}


function updateRecordT() 
{		
	 if (xmlHttp.readyState == 4) 
	 { 				
	 	if (xmlHttp.status == 200) 
		{
		

			var displayFieldArray=new Array('departurePlaceT','departureDateT1','arrivalPlaceT','arrivalDateT1'
			,'distanceInKmT','travelModeT10','classT0','totalT');
		
			//updateDataInTable('encXMLT', displayFieldArray);
			
			var rowNum;							
			//if(dbRecordForEdit==false)														
			{
				rowNum = updateDataInTableAttachment('rtaTransportTable','encXMLT', displayFieldArray);
			}
			//else
			{
			//	rowNum = updateDataInTableAttachment('rtaTransportTable','addedencXMLT', displayFieldArray);
			}
			resetDataT(rowNum);	
			document.getElementById('UpdateT').disabled=true;
			document.getElementById('AddT').disabled=false;
		}
	}
}

function resetDataT(rowNum) 
			{	
				
		        document.getElementById('departurePlaceT').value='';
				document.forms[0].departureDateT.value='';
				document.getElementById('arrivalPlaceT').value='';
				document.forms[0].arrivalDateT.value='';
				document.getElementById('distanceInKmT').value='';
				document.forms[0].travelModeT10.value='0';
				document.forms[0].classT0.value='Select';
				document.getElementById('totalT').value='';
										
				removeRowFromTableattachmentBiometricT(rowNum,'rtaSubmit'); 	
				  					
			}  // end of function resetData

function editRecordT(rowId,rowNum) 
{						
			
			document.getElementById('UpdateT').disabled=false;
			document.getElementById('AddT').disabled=true;
			sendAjaxRequestForEditAttachment(rowId, 'populateFormT','attachmentBiometricT',rowNum);					
}	

function populateFormT() 
{				
	 if (xmlHttp.readyState == 4) 
	 {	 	
	 	if (xmlHttp.status == 200) 
		{
		  	var decXML = xmlHttp.responseText;				  	
			var xmlDOM = populateAttachment(decXML,'rtaSubmit');
			
			document.getElementById('transportDetailId').value = getXPathValueFromDOM (xmlDOM, 'transportDetailId');
					
			document.getElementById('departurePlaceT').value = getXPathValueFromDOM (xmlDOM, 'departurePlace');
			
			document.forms[0].departureDateT.value=getDateInProperFormatT(getXPathValueFromDOM (xmlDOM, 'departureDate'));
			document.getElementById('arrivalPlaceT').value = getXPathValueFromDOM (xmlDOM, 'arrivalPlace');
			document.forms[0].arrivalDateT.value = getDateInProperFormatT(getXPathValueFromDOM (xmlDOM, 'arrivalDate'));
			
			document.getElementById('distanceInKmT').value = getXPathValueFromDOM (xmlDOM, 'distanceInKm');
			document.forms[0].travelModeT10.value = getXPathValueFromDOM (xmlDOM, 'cmnLookupMstByModeOfTrans/lookupName');
			document.forms[0].classT0.value = getXPathValueFromDOM (xmlDOM, 'cmnLookupMstByClassOfAccomod/lookupName');
			document.getElementById('totalT').value = getXPathValueFromDOM (xmlDOM, 'total');
			
			var finalTotal=0.0;
			var totalTransport=document.getElementById('totalTransport').value;
			var grandTotal=document.getElementById('grandTotal').value;
			totalT=getXPathValueFromDOM (xmlDOM, 'total');
			
			
			
		}
	}
}

function getDateInProperFormatT(v)
{
	if(v.indexOf("-")!=-1)
	{
		v=v.substring(0,10);				
		var splitDate=v.split("-");				
		var byr=splitDate[0];
		var bmo=splitDate[1];
		var bday=splitDate[2];				
		v= bday+'/'+bmo+'/'+byr;	
	}
	return v;
}
		


function popupClassT(index)
{
		var travelModName = document.getElementById("travelModeT1"+index).value;
        var travelMode="";
        var userId=document.getElementById('userId').value;
        if (travelModName=="Rail") {travelMode="RailLimits";}
        else if(travelModName=="Bus") {travelMode="Bus";}
        else if(travelModName=="Air") {travelMode="Air";}
        else if(travelModName=="Cab" ) {travelMode="Cab";}        
		else {return;}
			try{   
    			xmlHttp=new XMLHttpRequest();    
	    	}
			catch (e)
			{    // Internet Explorer    
					try{
      					xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");      
      				}
		    		catch (e){
		          		try
        		  		{
                	           	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");        
        		  		}
				      	catch (e)
				      	{
			           	   	  alert('<fmt:message  bundle="${commonLables}" key="HR.AJAX_BROWSER"/>');        
			            	  return false;        
			     		}
			 		}			 
        	}	
			var url = "./hrms.htm?actionFlag=getRailLimits&travelMode="+travelMode+"&index="+index+"&userId="+userId;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponseforClassT;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));	

}

function processResponseforClassT()
		{
			if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{        					  
				    	var xmlStr = xmlHttp.responseText;				    				    	
				    	var XMLDoc=getDOMFromXML(xmlStr);   // xmlHttp.responseXML.documentElement;						    			    					    					    						    					    	
				    	var classId = XMLDoc.getElementsByTagName('classListId');
				    	var className = XMLDoc.getElementsByTagName('classListName');
				    	var index = XMLDoc.getElementsByTagName('index');
				    	
				    	var indexId = "";			    	

						for ( var i = 0 ; i < 1 ; i++ )
				     	{
			    	 	  indexId=index[i].childNodes[0].text;
			    	 	}  
				    	//Remove existing elements
						var z=document.getElementById("classT"+indexId);				
					   	for (var i=z.length;i>=0;i--)
	     				{	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
				    	
				      var z=document.getElementById("classT"+indexId);	
			
					  var y=document.createElement('option');
		   				y.text='<fmt:message key="HRMS.select" />';
						y.value='Select';
		   				try
						{
							z.add(y,null); 	    						
						}
						catch(ex)
						{	   			 
			 				z.add(y);	   			 				 
						}
				    	
				    	
					for ( var i = 0 ; i < classId.length ; i++ )
			     	{
			     	  var id=classId[i].childNodes[0].text;
  			     	  var val=className[i].childNodes[0].text;
			     	  var z=document.getElementById("classT"+indexId);				
					  var y=document.createElement('option');
		   				y.text=val;
						y.value=id;
		   				try
						{
							z.add(y,null); 	    						
						}
						catch(ex)
						{	   			 
			 				z.add(y);	   			 				 
						}
			     	}			
	     						
				}				
			}    		
		}





</script>


<table name="transportRequest" id="transportRequest" class="tabtable" border="1">

			<tr align="center" >
						<td align="center" colspan="8" bgcolor="#386CB7">
						<strong><u><font color="white"><fmt:message key="HRMS.transportReq"/></font></u></strong>
						</td>
			</tr>
			
				<tr colspan="8">
					<td align="center" ><b><fmt:message
						key="HRMS.Departure" /></b></td>
					<td align="center" ><b><fmt:message
						key="HRMS.Arrival" /></td>
					<td align="center" ><b><fmt:message
						key="HRMS.DistanceInKm" /></td>
					<td align="center" ><b><fmt:message
						key="HRMS.TransportMode" /></td>
					<td align="center" ><b><fmt:message
						key="HRMS.total" /></td>
					
					</tr>
					<hdiits:hidden name="transportDetailId" id="transportDetailId" default="0"></hdiits:hidden>
					
					<tr colspan="8">
		
					<td width="13%" align="center"><hdiits:text mandatory="true"
						name="departurePlaceT" tabindex="15" id="departurePlaceT" caption="Departure" validation="txt.isrequired" maxlength="2000"/><br>
					   <fmt:message key="HRMS.dateFormat" /> 
					
					<hdiits:dateTime name="departureDateT" tabindex="16" captionid="HRMS.Arrival" bundle="${commonLables}" 
						mandatory="true" onblur="checkDateT();" ></hdiits:dateTime></td>
					<hdiits:hidden name="departuretDateT1" id="departureDateT1" default="0" ></hdiits:hidden>
					<td width="13%" align="center"><hdiits:text mandatory="true" name="arrivalPlaceT" validation="txt.isrequired"
						tabindex="17" id="arrivalPlaceT" caption="Arrival" maxlength="2000"/><br>
					   <fmt:message key="HRMS.dateFormat" /> 
					<hdiits:dateTime name="arrivalDateT" captionid="HRMS.Arrival" bundle="${commonLables}" tabindex="18"
						mandatory="true" onblur="checkDateT();" ></hdiits:dateTime></td>
					<hdiits:hidden name="arrivalDateT1" id="arrivalDateT1" default="0" ></hdiits:hidden>
					<td width="5%" align="center"><hdiits:number mandatory="true" floatAllowed="true"
						name="distanceInKmT"  id="distanceInKmT" maxlength="10" caption="Distance" size="5" tabindex="19" /></td>
					<td width="10%" align="center"><hdiits:select name="travelModeT10" id="travelModeT10" tabindex="20"
					onchange="popupClassT('0');">
					<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
						<c:forEach var="travelModeT" items="${travelModeListT}" >
							<hdiits:option value="${travelModeT.lookupName}">
								${travelModeT.lookupDesc}
							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					<hdiits:select sort="false" name="classT0"
						tabindex="21" id='classT0' size="1" caption="drop_down"
						mandatory="false" onchange="" >
						<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
						<c:forEach var="class" items="">
							<hdiits:option value="">

							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					</td>
					
					
					<td width="10%" align="center"><hdiits:number name="totalT" id="totalT" mandatory="true" floatAllowed="true" tabindex="22" maxlength="10"
					/></td>
				</tr>
				<tr>
			<td class="fieldLabel" colspan="8">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="attachmentBiometricT" />
					<jsp:param name="formName" value="rtaSubmit" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="Attachment" />			
					<jsp:param name="multiple" value="Y" />
					<jsp:param name="rowNumber" value="1" />
				</jsp:include>				
			</td>
		</tr>
				<tr>
			<td align="center" id="addButton" colspan="8"><hdiits:button type="button" tabindex="23" name="AddT" id="AddT" captionid="HRMS.add" 
			        bundle="${commonLables}" onclick="startupAjaxFormValidationT('AddT');" />
					&nbsp;<hdiits:button type="button" name="UpdateT" id="UpdateT" tabindex="24" readonly="true" captionid="HRMS.update"
					bundle="${commonLables}" onclick="startupAjaxFormValidationT('UpdateT');" />
					</td>
		</tr>
				
			
		
	
		</table>	
		
		
		<table id="rtaTransportTable" name="rtaTransportTable" class="tabtable" border="1" align="center">
		<tr>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DeparturePlace" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DepartureDate" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ArrivalPlace" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ArrivalDate" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.DistanceInKm" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.TransportMode" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.ClassofAcc" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.total" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.action" /></b></td>
			
						<td>
						</td>
						<td>
						</td>
			
		</tr>
	</table>	
	
	<table id="rtaTransportTotaltable" name="rtaTransportTotaltable" class="tabtable" border="0" align="center">
		
		<tr align="center">
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						<td align="right" ><b><fmt:message key="HRMS.total" /></b></td>
						<td align="left" >
							<hdiits:number name="totalTransport" tabindex="25" floatAllowed="true" style="background-color: lightblue;" readonly="true" id="totalTransport" mandatory="true" default="0.0" />
						</td>
						
		
		
	</table>
	
	
		
