

<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="travelModeList" value="${resValue.travelModeList}"></c:set>

<script>
var total=0;
function startupAjaxFormValidation(buttonName)
{
	
	if((document.getElementById('departurePlace').value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.departurePlaceReq"/>');
		document.forms[0].departurePlace.focus();
		return;
	}
	else if ((document.forms[0].departureDate.value)=='')
	{
	 	alert('<fmt:message  bundle="${commonLables}" key="HRMS.departureDateReq"/>');
	 	document.forms[0].departureDate.focus();
		return;
	}
	
	else if((document.forms[0].arrivedPlace.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.arrivedPlaceReq"/>');
		document.forms[0].arrivedPlace.focus();
		return;
	}
	else if((document.forms[0].arrivedDate.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.arrivedDateReq"/>');
		document.forms[0].arrivedDate.focus();
		return;
	}
	else if((document.forms[0].distanceInKm.value)=='' || (document.forms[0].distanceInKm.value)=='0')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.distanceInKmReq"/>');
		document.forms[0].distanceInKm.focus();
		return;
	}
	
	else if(isNaN(document.getElementById('distanceInKm').value) )
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.distanceInKmReqFormat"/>');
		document.forms[0].distanceInKm.value='';
		document.forms[0].distanceInKm.focus();
		return;
	}
	
	else if((document.forms[0].travelMode10.value)=='0')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.travelMode10Req"/>');
		document.forms[0].travelMode10.focus();
		return;
	}
	
	else if((document.forms[0].class0.value)=='Select')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.class0Req"/>');
		document.forms[0].class0.focus();
		return;
	}
	
				
	else if((document.forms[0].noOfPersonTravelling.value)<='0')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.noOfPersonTravellingReq"/>');
		document.forms[0].noOfPersonTravelling.focus();
		return;
	}
	 
	else if(isNaN(document.getElementById('noOfPersonTravelling').value))
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.noOfPersonTravellingReqFormat"/>');
		document.forms[0].noOfPersonTravelling.value='';
		document.forms[0].noOfPersonTravelling.focus();
		return;
	}
	 
	else if((document.forms[0].faresPaid.value)=='' || (document.forms[0].faresPaid.value)=='0')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.faresPaidReq"/>');
		document.forms[0].faresPaid.focus();
		return;
	}
	
	else if(isNaN(document.getElementById('faresPaid').value))
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.faresPaidReqFormat"/>');
		document.forms[0].faresPaid.value='';
		document.forms[0].faresPaid.focus();
		return;
	} 
	
	else if((document.forms[0].remarks.value)=='')
	{
		alert('<fmt:message  bundle="${commonLables}" key="HRMS.remarksReq"/>');
		document.forms[0].remarks.focus();
		return;
	}
	
	else
	{	
	
	
	var finalTotal=0.0;
	var finalTotalG=0.0;
	var amountDue=0.0;
	
	var totalTravel=document.getElementById('totalTravel').value;
	var totalNew=document.getElementById('total').value;
	var grandTotal=document.getElementById('grandTotal').value;
	var sanctionedAmt=document.getElementById('sanctionedAmt').value;
	
	document.getElementById('arrivedDate1').value=document.forms[0].arrivedDate.value;
	document.getElementById('departureDate1').value=document.forms[0].departureDate.value;
	
	
	var addArray=new Array('departurePlace','departureDate1','arrivedPlace','arrivedDate1','distanceInKm','travelMode10',
	'class0','noOfPersonTravelling','faresPaid','total','remarks');
	var statusValidation = validateSpecificFormFields(addArray);
	if(statusValidation == true)
	{								
		if(buttonName == 'Add')
		{		
			var num=0;			
			addOrUpdateRecord('addRecord', 'RtaDetails&flag=AddRtaDtls&travelReqId='+num,new Array('departurePlace',
			'departureDate1','departureDate','arrivedPlace','arrivedDate1','arrivedDate','distanceInKm',
			'travelMode10','class0','noOfPersonTravelling','faresPaid','total','remarks'));	
			finalTotal=eval(eval(totalTravel)+eval(totalNew));
			finalTotalG=eval(eval(grandTotal)+eval(totalNew));
			amountDue=eval(eval(finalTotalG)-eval(sanctionedAmt));
			document.getElementById("subButton").disabled=false;
		}
		else
		{
			var	num = document.getElementById('travelReqqId').value;
			addOrUpdateRecord('updateRecord','RtaDetails&flag=AddRtaDtls&travelReqId='+num,new Array('departurePlace',
			'departureDate1','departureDate','arrivedPlace','arrivedDate1','arrivedDate','distanceInKm',
			'travelMode10','class0','noOfPersonTravelling','faresPaid','total','remarks'));
			finalTotal=eval(eval(totalTravel)+eval(totalNew)-eval(total));
			finalTotalG=eval(eval(grandTotal)+eval(totalNew)-eval(total));
			amountDue=eval(eval(finalTotalG)-eval(sanctionedAmt));
		
		}
	}
	document.getElementById('totalTravel').value=finalTotal;
	document.getElementById('grandTotal').value=finalTotalG;
	document.getElementById('dueAmt').value=amountDue;
	}
	total=0;
}    
//End of Function startupAjaxFormValidation()

//START OF CHECK DATE FUNCTION
function checkDate()
{
	if( (document.forms[0].arrivedDate.value!='') || (document.forms[0].departureDate.value!='') )
	{
	var depDate=document.forms[0].departureDate.value;
	var retDate=document.forms[0].arrivedDate.value;
	
	
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
				alert('<fmt:message  bundle="${commonLables}" key="HRMS.retDateLessDepDate"/>');
				document.forms[0].arrivedDate.value='';
				document.forms[0].arrivedDate.focus();
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
function addRecord() 
	{   		  	    
	  	if (xmlHttp.readyState == 4)
	  	{ 	
	  		if (xmlHttp.status == 200) 
			{		
				
				var displayFieldArray=new Array('departurePlace','departureDate1','arrivedPlace','arrivedDate1'
				,'distanceInKm','travelMode10','class0','noOfPersonTravelling','faresPaid','total','remarks');
				var rowNum=addDataInTableAttachment('rtaTable','encXML',displayFieldArray,'editRecord','deleteRecordTravel','');														
				resetData(rowNum);				   	    	  															   	    	
	  	    }
	   	}			   	
	}	
	
	
function deleteRecordTravel(rowId,rowNum)
{
	
	
	var trow = document.getElementById(rowId);

	var cells = trow.getElementsByTagName('td');
	

	var finalTotal=0.0;
	var sanctionedAmt=document.getElementById('sanctionedAmt').value;
	
	var totalTravel=eval(eval(document.getElementById('totalTravel').value)-eval(cells[10].innerText));
	var grandTotal=eval(eval(document.getElementById('grandTotal').value)-eval(cells[10].innerText));
	var amtDue=eval(eval(grandTotal)-eval(sanctionedAmt));
	var answer = deleteRecord(rowId);
	if(answer){
	
		document.getElementById("totalTravel").value=totalTravel;
		document.getElementById("grandTotal").value=grandTotal;
		document.getElementById("dueAmt").value=amtDue;
	}
	if(grandTotal=='0.0')
	{
		document.getElementById("subButton").disabled=true;
	}

}

	
	
			
function updateRecord() 
{		
	 if (xmlHttp.readyState == 4) 
	 { 				
	 	if (xmlHttp.status == 200) 
		{
		

			var displayFieldArray=new Array('departurePlace','departureDate1','arrivedPlace','arrivedDate1'
			,'distanceInKm','travelMode10','class0','noOfPersonTravelling','faresPaid','total','remarks');
		
			var rowNum;							
							
			rowNum = updateDataInTableAttachment('rtaTable','encXML', displayFieldArray);
			
			resetData(rowNum);	
			document.getElementById('Update').disabled=true;
			document.getElementById('Add').disabled=false;
		}
	}
}

function resetData(rowNum) 
			{	
				
		        document.getElementById('departurePlace').value='';
				document.forms[0].departureDate.value='';
				document.getElementById('arrivedPlace').value='';
				document.forms[0].arrivedDate.value='';
				document.getElementById('distanceInKm').value='';
				document.forms[0].travelMode10.value='0';
				document.forms[0].class0.value='Select';
				document.getElementById('noOfPersonTravelling').value='';
				document.getElementById('faresPaid').value='';
				document.getElementById('total').value='';
				document.getElementById('remarks').value='';
							
				removeRowFromTableattachmentBiometric(rowNum,'rtaSubmit'); 	
				  					
			}  // end of function resetData



function editRecord(rowId,rowNum) 
{			
				
			document.getElementById('Update').disabled=false;
			document.getElementById('Add').disabled=true;		
			sendAjaxRequestForEditAttachment(rowId, 'populateForm','attachmentBiometric',rowNum);			
}				
		
		
	
function populateForm() 
{				
	 if (xmlHttp.readyState == 4) 
	 {	 	
	 	if (xmlHttp.status == 200) 
		{
		  	var decXML = xmlHttp.responseText;				  	
			var xmlDOM = populateAttachment(decXML,'rtaSubmit');
			
			document.getElementById('travelReqqId').value = getXPathValueFromDOM (xmlDOM, 'travelReqqId');
			document.getElementById('rtaId').value = getXPathValueFromDOM (xmlDOM, 'HrRtaReqDtl');		
			document.getElementById('departurePlace').value = getXPathValueFromDOM (xmlDOM, 'departurePlace');
			document.getElementById('departureDate1').value = getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'departureDate'));
			document.forms[0].departureDate.value=document.getElementById('departureDate1').value;
			document.getElementById('arrivedPlace').value = getXPathValueFromDOM (xmlDOM, 'arrivedPlace');
			document.forms[0].arrivedDate.value = getDateInProperFormat(getXPathValueFromDOM (xmlDOM, 'arrivedDate'));
			document.getElementById('distanceInKm').value = getXPathValueFromDOM (xmlDOM, 'distanceInKm');
			document.forms[0].travelMode10.value = getXPathValueFromDOM (xmlDOM, 'cmnLookupMstByModeOfTrans/lookupName');
			document.forms[0].class0.value = getXPathValueFromDOM (xmlDOM, 'cmnLookupMstByClassOfAccomod/lookupName');
			document.getElementById('noOfPersonTravelling').value = getXPathValueFromDOM (xmlDOM, 'noOfPersonTravelling');
			document.getElementById('faresPaid').value = getXPathValueFromDOM (xmlDOM, 'faresPaid');
			document.getElementById('total').value = getXPathValueFromDOM (xmlDOM, 'total');
			document.getElementById('remarks').value = getXPathValueFromDOM (xmlDOM, 'remarks');
			
			var finalTotal=0.0;
			var totalTravel=document.getElementById('totalTravel').value;
			var grandTotal=document.getElementById('grandTotal').value;
			total=getXPathValueFromDOM (xmlDOM, 'total');
			
			
			
		}
	}
}
	
function getDateInProperFormat(v)
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
		
			
			
function popupClass(index)
{
		var travelModName = document.getElementById("travelMode1"+index).value;
        var travelMode="";
        if(travelModName=="Air" ) {travelMode="Air";}
        else if(travelModName=="Bus") {travelMode="Bus";}
        else if(travelModName=="Rail") {travelMode="Rail";}
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
			var url = "hrms.htm?actionFlag=classOfAccomodationDtls&travelMode="+travelMode+"&index="+index;    
			xmlHttp.open("POST", encodeURI(url) , true);			
			xmlHttp.onreadystatechange = processResponseforClass;
			xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
			xmlHttp.send(encodeURIComponent(null));	

}

function processResponseforClass()
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
						var z=document.getElementById("class"+indexId);				
					   	for (var i=z.length;i>=0;i--)
	     				{	     				     					
							z.remove(z.value);
							z.remove(i);
	     				}	     		     							
				    	
				      var z=document.getElementById("class"+indexId);	
			
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
			     	  var z=document.getElementById("class"+indexId);				
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


function calTravelTotal()
{
		var total=0.0;
	
	
		var noOfPersonTravelling=document.getElementById("noOfPersonTravelling").value;
		var faresPaid=document.getElementById("faresPaid").value;
		total=noOfPersonTravelling*faresPaid;
		document.getElementById("total").value=total;
		
}
</script>

	<table name="travelRequest" id="travelRequest" class="tabtable" border="1" width="100%">
			
		
			
			<tr align="center" >
						<td align="center" colspan="8" bgcolor="#386CB7">
						<strong><u><font color="white"><fmt:message key="HRMS.travelreq"/></font></u></strong>
						</td>
			</tr>
			<tr>
					<td align="center" width="13%"><b><fmt:message
						key="HRMS.Departure" /></b></td>
					<td align="center" width="13%"><b><fmt:message
						key="HRMS.Arrival" /></b></td>
					<td align="center" width="10%"><b><fmt:message
						key="HRMS.DistanceInKm" /></b></td>
					<td align="center" width="10%"><b><fmt:message
						key="HRMS.TransportMode" /></b></td>
					<td align="center" width="10%"><b><fmt:message
						key="HRMS.ClassofAcc" /></b></td>
					<td align="center" width="10%"><b><fmt:message
						key="HRMS.NoOfFares" /></b></td>
					<td align="center" width="10%"><b><fmt:message
						key="HRMS.FaresPaid" /></b></td>
					
			</tr>

					<!-- Declaration of travelReqId  -->					
					<hdiits:hidden name="travelReqqId" id="travelReqqId" default="0"></hdiits:hidden>
					<hdiits:hidden name="rtaId" id="rtaId" default="0" ></hdiits:hidden>
			
				<tr colspan="8">
		
					<td width="13%" align="center"><hdiits:text mandatory="true" validation="txt.isrequired" 
						name="departurePlace" tabindex="1" id="departurePlace"  caption="Departure" /><br>
					<hdiits:dateTime name="departureDate" captionid="HRMS.Departure" bundle="${commonLables}" tabindex="2"
						mandatory="true" onblur="checkDate();" ></hdiits:dateTime>
						<b> <fmt:message key="HRMS.dateFormat" /> </b>
						</td>
					
					<hdiits:hidden name="departureDate1" id="departureDate1" default="0" ></hdiits:hidden>
					<td width="13%" align="center"><hdiits:text  mandatory="true" name="arrivedPlace" validation="txt.isrequired"
						tabindex="3" id="arrivedPlace" caption="Arrival" /><br>
					<hdiits:dateTime name="arrivedDate"  captionid="HRMS.Arrival" bundle="${commonLables}" tabindex="4"
						mandatory="true"  onblur="checkDate();" ></hdiits:dateTime>
											  <b><fmt:message key="HRMS.dateFormat" /> </b>
						</td>

					<hdiits:hidden name="arrivedDate1" id="arrivedDate1" default="0" ></hdiits:hidden>
					<td width="5%" align="center"><hdiits:number  mandatory="true"  maxlength="6"  floatAllowed="true"
						name="distanceInKm" id="distanceInKm" caption="Distance" size="5" tabindex="5" /></td>
					<td width="10%" align="center"><hdiits:select name="travelMode10" id="travelMode10" tabindex="6"
					 onchange="popupClass('0');">
					<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
						<c:forEach var="travelMode" items="${travelModeList}">
							<hdiits:option value="${travelMode.lookupName}">
								${travelMode.lookupDesc}
							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					</td>
					<td width="10%" align="center"><hdiits:select sort="false" name="class0"
						tabindex="7" id='class0' size="1" caption="drop_down" 
						mandatory="true" onchange="">
						<hdiits:option value="0"><fmt:message key="HRMS.select" /></hdiits:option>
						<c:forEach var="class" items="">
							<hdiits:option value="">

							</hdiits:option>
						</c:forEach>
					</hdiits:select>
					</td>
					<td width="10%" align="center">
					<hdiits:number name="noOfPersonTravelling"  id="noOfPersonTravelling"  maxlength="4" mandatory="true" 
					 size="5" onblur="calTravelTotal();" floatAllowed="false" tabindex="8"/></td>
					<td width="10%" align="center"><hdiits:number name="faresPaid" id="faresPaid" tabindex="9" maxlength="10" mandatory="true" 
					 size="5" onblur="calTravelTotal();" floatAllowed="true" /></td>			
					
					
				</tr>	
				<tr>
					
					<td align="center" colspan="5"><b><fmt:message
						key="HRMS.remarks" /></b></td>
						
					<td align="center" width="10%"><b><fmt:message
						key="HRMS.total" /></b></td>	
					<td align="center"><hdiits:number floatAllowed="true" name="total" id="total" mandatory="true" style="background-color: lightblue;" readonly="true"
					tabindex="10" size="6" /></td>
						
				</tr>
				<tr>
					<td colspan="5" align="center"><hdiits:textarea name="remarks" id="remarks" mandatory="true" cols="60"
					tabindex="11"  /></td>
					<td colspan="2">
					&nbsp;
					</td>
				</tr>
		
		<tr>
			<td class="fieldLabel" colspan="8">
				<jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
					<jsp:param name="attachmentName" value="attachmentBiometric" />
					<jsp:param name="formName" value="rtaSubmit" />
					<jsp:param name="attachmentType" value="Document" />
					<jsp:param name="attachmentTitle" value="Attachment" />			
					<jsp:param name="multiple" value="Y" />
					<jsp:param name="rowNumber" value="1" />
				</jsp:include>				
			</td>
		</tr>
		<tr>
			<td align="center" id="addButton" colspan="8"><hdiits:button 
					type="button" name="Add" id="Add" captionid="HRMS.add" tabindex="12"  bundle="${commonLables}" onclick="startupAjaxFormValidation('Add');" />
					&nbsp;<hdiits:button	type="button" name="Update" tabindex="13" id="Update" readonly="true" captionid="HRMS.update" 
			        bundle="${commonLables}" onclick="startupAjaxFormValidation('Update');" />
					</td>
					
		</tr>
		
	</table>
		
		<table id="rtaTable" name="rtaTable" class="tabtable" border="1" align="center">
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
						key="HRMS.NoOfFares" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.FaresPaid" /></b></td>	
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.total" /></b></td>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.remarks" /></b>
			<td align="center" bgcolor="lightblue"><b><fmt:message
						key="HRMS.action" /></b></td>
						<td>
						</td>
						<td>
						</td>
			
		</tr>
	</table>	
	
	<table id="rtaTravelTotaltable" name="rtaTravelTotaltable" class="tabtable" border="0" align="center">
		
		<tr align="center">
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						<td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
						<td align="right" ><b><fmt:message key="HRMS.total" /></b></td>
						<td align="left" >
							<hdiits:number name="totalTravel" tabindex="14" floatAllowed="true" style="background-color: lightblue;" id="totalTravel" readonly="true" mandatory="true" default="0.0" />
							
						</td>
						
		
		
	</table>
	
	
	
	



