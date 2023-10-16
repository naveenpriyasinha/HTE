  
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp"%>
<%@ include file="/WEB-INF/jsp/common/cmnMsg.jsp"%>
 
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/hrms/training/trngAttended.js"/>"></script>

<fmt:setBundle basename="resources.trng.TrngAttendedLables" var="trngAttendedLables" scope="request" /> 
<fmt:setBundle basename="resources.trng.trschLables" var="trschLables"	scope="request" />
<fmt:setBundle basename="resources.trng.EmployeeDetails" var="EmployeeDetails"	scope="request" />
<fmt:message key="TR.MSG" bundle="${trngAttendedLables}" var="msg" />
<script type="text/javascript">
function verifyDuplicate(tableName,elementNameArray,hiddenField,duplicateAlert)
	{//alert("Inside verify duplicate");
		var value="";
		var	rowId ;
		for(var y=0;y<elementNameArray.length;y++)
		{
			value=value+trim(document.getElementsByName(elementNameArray[y])[0].value);
			if(y<elementNameArray.length-1)
			{
				value=value+",";
			}
		}
		//alert("value   "+value);
		//alert(arrMulti[tableName]+"empty");
		if(arrMulti[tableName])
		{
			
			var isPresent = false;
			for(x in arrMulti[tableName])
			{
				//alert("arrMulti[tableName][x]::"+arrMulti[tableName][x]);
				if(value==arrMulti[tableName][x])
				{
					
					
					if(duplicateAlert!=null)
					{
						alert(duplicateAlert);
					}
					else
					{
						var duplicateRecord=cmnLblArray[6];
						if(duplicateRecord)
						{
							alert(duplicateRecord);
						}
						else
						{
							alert('Similar records already exist.Please verify your data');
						}
					}
					isPresent=true;
					return false;
				}
			}
			if(isPresent==false)
			{
				if(rowToUpdate!=null)
				{
					
					arrMulti[tableName][rowToUpdate]=value;
					rowToUpdate = null;
					return true;
				}
				else
				{
					rowId = 'row' + hiddenField + counter;
					arrMulti[tableName][rowId]=value;
					return true;
				}
			}
		}
		else
		{
			rowId = 'row' + hiddenField + 1;
			arrMulti[tableName]=new Array();
			arrMulti[tableName][rowId]=value;
			return true;
		}
	}
function getTrainingNames(val)
{
	//alert('get');	  
 	var actionFlag = "getTrainingNames4TrngAttended";
	xmlHttp=GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert('Your browser does not support AJAX!');
	} 
    //alert('${contextPath}');
  	var url= '${pageContext.request.contextPath}' + '/hdiits.htm?actionFlag=' + actionFlag + '&trngType=' + val.value;
    showProgressbar("Please Wait...");
    xmlHttp.onreadystatechange=trainingNameResponse;
    //xmlHttp.onreadystatechange=trainingNameResponse;
		//alert(url);			
		//alert( xmlHttp.onreadystatechange);
	//xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.open("POST",encodeURI(url),false);
	xmlHttp.send(encodeURIComponent(null));
}

function trainingNameResponse()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{ 
  				
					var XMLDoc=xmlHttp.responseXML;
					if(XMLDoc!= null)
				    	{
								    	var entries = XMLDoc.getElementsByTagName('training');	
								    	
								    	//alert(entries.length);
								    	
								    	var targetCombo = document.getElementById('TrainingId');
				     			    	if(targetCombo.length > 1)
								    		  {
								    		     clearListSch(targetCombo);
								    		  }
								    	for ( var i = 0 ; i < entries.length ; i++ )
					     				{
					     					text=entries[i].childNodes[0].text;   
					     				    value=entries[i].childNodes[1].text;
					     				   
					     					var y=document.createElement('option');
						 					y.text=value;
											y.value=text;
											
											try
					   						{
					    						targetCombo.add(y,null);     							    			
					   						}
					 						catch(ex)
					   						{
					   			 				targetCombo.add(y); 
					   						}     					
					           			}
								    	 
								    	 
										 
				    	     //alert(xmlHttp.responseText);
				    	    // alert(names[0].childNodes[0].text);
				    	}

				}
				else 
				{  
 					alert("ERROR");
					//alert(xmlHttp.status);
					alert(xmlHttp.responseText);
					//document.getElementById("res").innerHTML=xmlHttp.responseText;
					//alert("after Div");
				}
			}
	hideProgressbar();
}
function getTrainingCenterNames(val)
{
	//alert('get');
	var actionFlag = "getTrainingCenterNames";
	xmlHttp=GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert('Your browser does not support AJAX!');
	} 
    //alert('${contextPath}');
  	var url= '${pageContext.request.contextPath}' + '/hdiits.htm?actionFlag=' + actionFlag + '&trngName=' + val.value;
    showProgressbar("Please Wait...");
    xmlHttp.onreadystatechange=trainingCentreNameResponse;
    //xmlHttp.onreadystatechange=trainingNameResponse;
		//alert(url);			
		//alert( xmlHttp.onreadystatechange);
	//xmlHttp.setRequestHeader("Content-Type", "text/html; charset=iso-8859-1");
	xmlHttp.open("POST",encodeURI(url),true);
	xmlHttp.send(encodeURIComponent(null));
}

function trainingCentreNameResponse()
{
	if (xmlHttp.readyState == 4) 
			{     
				if (xmlHttp.status == 200) 
				{ 
  				
					var XMLDoc=xmlHttp.responseXML;
					if(XMLDoc!= null)
				    	{
								    	var entries = XMLDoc.getElementsByTagName('traininglocation');									    	
								    	//alert("No of elements in the combo:"+entries.length);

								   if(entries.length)
								   { 	
								    	var targetCombo = document.getElementById('place');
				     			    	if(targetCombo.length > 1)
								    		  {
								    		     clearListSch(targetCombo);
								    		  }
								    	for ( var i = 0 ; i < entries.length ; i++ )
					     				{
					     					text=entries[i].childNodes[0].text;   
					     				    value=entries[i].childNodes[1].text;
					     				   
					     					var y=document.createElement('option');
						 					y.text=value;
											y.value=text;
											
											try
					   						{
					    						targetCombo.add(y,null);     							    			
					   						}
					 						catch(ex)
					   						{
					   			 				targetCombo.add(y); 
					   						}     					
					           			}
					           			//alert("b4");
					           		 	document.getElementById("address").style.display="none";
					           		 	document.getElementById("internaltrngCentreNames").style.display="";
					           		 	//alert("after");
					           		}
					           		else
					           		{
					           			//alert("Else block.....");
					           			document.getElementById("address").style.display="";
					           		 	document.getElementById("internaltrngCentreNames").style.display="none";
					           		 	//alert("Else block enD.....");
					           		}
						 			 
				    	    //alert(xmlHttp.responseText);
				    	    // alert(names[0].childNodes[0].text);
				    	}

				}
				else 
				{  
 					alert("ERROR");
					//alert(xmlHttp.status);
					//alert(xmlHttp.responseText);
					//document.getElementById("res").innerHTML=xmlHttp.responseText;
					//alert("after Div");
				}
			}
	hideProgressbar();
}

function validateSpecificFormFields(controlNames)
{
	//alert("in the function");
	var returnValue = true;
	for (var i=0; i<controlNames.length; i++)
	{
		var strValidation = document.getElementsByName('formField_'+controlNames[i]);
		for (var j=0; j<strValidation.length; j++)
		{
			//alert(strValidation[j].value);
			var validation = strValidation[j].value.split(' && ');

			if(validation.length > 1)
			{
				returnValue = eval(validation[0]);
				if(returnValue)
				{
					returnValue = eval(validation[1]);
					if(!returnValue)
					{
						return false;
					}
				}
			}
			else
			{			
				returnValue = eval(strValidation[j].value);
				if(!returnValue)
				{
					return false;
				}
			}
		}
	}
	
	return returnValue;
}

function deleteRecord(rowId) 
	{
		//alert("DElete record");
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		
		if(updateRow != null)
		{
			//alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{	
			var delRow = document.getElementById(rowId);
			//alert(delRow.rowIndex);
			delRow.parentNode.deleteRow(delRow.rowIndex);		
		}

	//For hiding the headers
	var Rowlength = document.getElementById('txnAddSch').rows.length;
	flag=false;
	noDeletedRow=1;
	//alert("Rowlength:::"+Rowlength);		
	for(var i = 1; i < Rowlength; i++)
	{
		if(document.getElementById('txnAddSch').rows[i].style.display=='none')
			noDeletedRow++;
	}
	if(noDeletedRow==Rowlength)		flag=true;
	//alert("noDeletedRow:::"+noDeletedRow);		 
	if(flag == true){
		//alert("0 rows");
		document.getElementById('txnAddSch').style.display='none';
	}
		
	//alert("ANSWER :"+answer);
	deleteFromDuplicateArray('txnAddSch',rowId);
	return answer;
	}
	
function deleteDBRecord(rowId)
	{
		var delCap = "";
		var delNtAlllowed = "";
		try
		{
			delCap = cmnLblArray[2];
			delNtAlllowed = cmnLblArray[3];
		}
		catch (e)
		{
			delNtAlllowed = "You can't delete record,\n Because you have open one record for update";
			delCap = "Are you sure you want to delete this record ?";
		}
		if(updateRow != null)
		{
			//alert (delNtAlllowed);
			return false;
		}
		var answer = confirm(delCap);
		if(answer)
		{
			var delRow 	= document.getElementById(rowId);
			var content = delRow.cells[0].innerHTML;
			showAlert(content);
			content = content.replace(".xml_N",".xml_D");
			content = content.replace(".xml_U",".xml_D");
			delRow.cells[0].innerHTML = content;					
			delRow.style.display='none';
		}
		
	//For hiding the headers
	var Rowlength = document.getElementById('txnAddSch').rows.length;
	flag=false;
	noDeletedRow=1;
	//alert("Rowlength:::"+Rowlength);		
	for(var i = 1; i < Rowlength; i++)
	{
		if(document.getElementById('txnAddSch').rows[i].style.display=='none')
			noDeletedRow++;
	}
	if(noDeletedRow==Rowlength)		flag=true;
	//alert("noDeletedRow:::"+noDeletedRow);		 
	if(flag == true){
		//alert("0 rows");
		document.getElementById('txnAddSch').style.display='none';
	}
	
		deleteFromDuplicateArray('txnAddSch',rowId);
	return answer;	
	}
	
	function noRecords()
	{
	
	//alert("lstSchtyp:::"+document.getElementById("lstSchtyp").value);
	//alert("TrainingId:::"+document.getElementById("TrainingId").value);
	//alert("txtSchstdate:::"+document.getElementById("txtSchstdate").value);
	//alert("txtScheddate:::"+document.getElementById("txtScheddate").value);
	//alert("length:::"+document.getElementById('txnAddSch').rows.length)
	var Rowlength = document.getElementById('txnAddSch').rows.length;

	if(Rowlength <= 1)
	{
			//alert("If block");
			if(document.getElementById("lstSchtyp").value == -1)
			 	{
			 		alert("Please select the Training Type");
			 		return false;
			 	}
	 		else if(document.getElementById("TrainingId").value == -1)
	 			{
	 			alert("Please select the TrainingName");
	 			return false;
	 			}
	 		else if(document.getElementById("txtSchstdate").value == " ")
	 			{
	 			alert("Please select the Training Startdate");
	 			return false;
	 			}
	 		else if(document.getElementById("txtScheddate").value == " ")
	 			{
	 			alert("Please select the Training enddate");
	 			return false;
	 			}
			else	
				{
				return true;
				}
	}
	else
	{
		//alert("Else block");
		return true;
	}
	
	}
</script>

<script type="text/javascript" src="script/common/address.js"></script>
			<c:set var="resultObj"	value="${result}"/>
			<c:set var="resultMap" value="${resultObj.resultValue}"/>
			<c:set var="defaults" value="${resultMap.defaults}" />
 			<c:out value='${resultMap.result}' />  			
			
			<c:set var="trnglist" value="${resultMap.trainingList}"/>
			<c:set var="trngTypelist" value="${resultMap.trngTypelist}"/>
		    <c:set var="trngAttendlist" value="${resultMap.trngAttendlist}" > </c:set>
		    <c:set var="trngCenterNames" value="${resultMap.locationList}" > </c:set> 	    
			<c:set var="xmlKeyList" value="${resultMap.xmlKeyList1}" > </c:set>
			<c:set var="flagExternal"  value="${resultMap.isExternalFlag}"/> 
		
		  
<hdiits:form name="frmtrngAttended" validate="true" method="post" action="./hdiits.htm?" encType="multipart/form-data">
 
<hdiits:hidden name="srNo" />
<hdiits:hidden name="empIdHidden" default="${resultMap.empId}" /> 
<hdiits:hidden name="trngAddressId" default="" /> 
<hdiits:hidden name="actionFlag" default="attendedTrainings" />

 
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li  class="selected">
		<a href="#"  rel="tcontent1">
			<hdiits:caption	captionid="TR.TrngAttended" bundle="${trngAttendedLables}" /> 
		</a>
	</li>
	</ul>
</div>
	<div class="tabcontentstyle"> 
		<div id="tcontent1" class="tabcontent" tabno="fmkghcf" >   
		<hdiits:jsField name="chkrecords" jsFunction="noRecords()"/>
 		
 		<table class="tabtable">
			<tr>					 
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.Name" bundle="${EmployeeDetails}" /></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${resultMap.empName}"></c:out></td>			 			 
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.PLACE" bundle="${EmployeeDetails}"/></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${resultMap.empLoc}"></c:out></td>				 
			</tr>
	 				<tr>
					 	<TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGTYPE" bundle="${trschLables}"/></TD>
					    <TD>
					    	<hdiits:select name="lstSchtyp"  captionid="TR.TRAININGTYPE" bundle="${trschLables}"  
					    				     mandatory="true" sort="true" onchange="getTrainingNames(this)">
			 		    		<hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${trschLables}"/></hdiits:option>
				       	 			<c:forEach var="trngType" items="${trngTypelist}">
										<option value='<c:out value="${trngType.lookupId}"/>'><c:out value="${trngType.lookupDesc}" /></option>
									</c:forEach>		  
			 			   </hdiits:select>    
					   </TD>
					   <TD class="fieldLabel" width="25%"><hdiits:caption captionid="TR.TRAININGNAME" bundle="${trschLables}"/></TD>
					   <TD>
					   	   <hdiits:select name="TrainingId"  captionid="TR.TRAININGNAME" bundle="${trschLables}"  validation="sel.isrequired" 
					   	   				  mandatory="true" sort="true"  onchange="getTrainingCenterNames(this)">
							  	<hdiits:option value="-1" selected="true"><fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${trschLables}"/>
					       			<c:forEach var="trngName" items="${trnglist}">
											<option value="${trngName.trainingMstId}" ><c:out value="${trngName.trainingName}"></c:out></option>
									</c:forEach>	
									
								</hdiits:option>
			 			   </hdiits:select>    
					  </TD>
			 		<tr>
  				
  				<tr>
  					<TD class="fieldLabel" width="25%">
						<hdiits:caption captionid="TR.STARTDATE" bundle="${trschLables}"/>&nbsp;
						<hdiits:caption captionid="TR.DateFormat" bundle="${trschLables}"/>
					</TD>
					<TD class=fieldLabel width="25%">
			 			<hdiits:dateTime name="txtSchstdate" captionid="TR.STARTDATE"  bundle="${trschLables}" 
			 							validation="txt.isrequired" mandatory="true"   default=" " maxvalue="01/01/9999"/>  </TD>
			 							
			 		<td class="fieldLabel" width="25%">
			 			<hdiits:caption captionid="TR.ENDDATE" bundle="${trschLables}"/>&nbsp;
						<hdiits:caption captionid="TR.DateFormat" bundle="${trschLables}"/></td>
					<td class="fieldLabel width="25%">
						<hdiits:dateTime name="txtScheddate" captionid="TR.DateFormat"  bundle="${trschLables}" 
										 validation="txt.isrequired" mandatory="true" default=" " 
										 onblur="checkEndDate('${msg}');calDuration();" maxvalue="01/01/9999"/> </td>			
			
                    </TR>
				 
					<tr>
			    		<td class="fieldLabel" width="25%">
			    			<hdiits:caption captionid="TR.duration" bundle="${trngAttendedLables}"/>
			    		</td>
		 		
						<td class="fieldLabel" width="25%">
						 <hdiits:number name="txtduration" 
			                             captionid="TR.duration"  maxlength="3"  bundle="${trngAttendedLables}" validation="txt.isrequired" mandatory="true"/>  
			 			</td>
	 			
					</tr>
					<tr>
						 
				    		<td class="fieldLabel" width="25%">
				    				<hdiits:caption captionid="TR.ispassed" bundle="${trngAttendedLables}" />
				    		</td>
			
							<td class="fieldLabel" width="25%">
									<hdiits:radio name="istrngpassed" id="istrngpassed" value='P' bundle="${trngAttendedLables}" captionid="TR.pass"  validation="txt.isrequired" mandatory="true" />
									<hdiits:radio name="istrngpassed" value='F'  bundle="${trngAttendedLables}" captionid="TR.fail"  validation="txt.isrequired"/>
							</td>
					</tr>
					<tr>
				      		<td class="fieldLabel" width="25%">
			      					<hdiits:caption captionid="TR.remarks" bundle="${trngAttendedLables}"/>
				      		</td>
					
				    		<td class="fieldLabel" width="25%">
						    		 <hdiits:textarea name="txtremarks" captionid="TR.remarks" rows="5" cols="50" />
						    </td>
					</tr>
					
</table>

<div id="internaltrngCentreNames" style="display:none">
	<table class="tabtable">
		<tr>
			 <td class="fieldLabel" width="25%">
			 		<hdiits:caption captionid="TR.place" bundle="${trngAttendedLables}"/>
			 </td>		
			 <td class="fieldLabel" width="25%">		
		 			<hdiits:select captionid="TR.place" bundle="${trngAttendedLables}"  name="place" sort="false" >	
						 <hdiits:option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" bundle="${trschLables}"/></hdiits:option>	     	
						 	<c:forEach var="trngCenters" items="${trngCenterNames}">
											<option value='<c:out value="${trngCenters.locId}"/>'><c:out value="${trngCenters.locName}" /></option>
							</c:forEach>
				 	</hdiits:select>
			</td>
			 <td class="fieldLabel" width="25%"></td>
			 <td class="fieldLabel" width="25%"></td>
		</tr>
		
	</table>
</div>

<div id="address" style="display:none">
			<jsp:include page="/WEB-INF/jsp/common/address.jsp">
				<jsp:param name="addrName" value="trngatt_address"/>
		        <jsp:param name="addressTitle" value="Training Centre Address"/>
		        <jsp:param name="addrLookupName" value="Training Address"/>		         
		        <jsp:param name="mandatory" value="No"/>		        
	        </jsp:include>
</div>		
	 	

<table id="btnAdd" style="display" align="center">
	<tr><TD class="fieldLabel" align="center" colspan="4"><hdiits:button  name="add"  type="button" captionid="TR.add" bundle="${trngAttendedLables}" onclick="addTrngAttnd('addRecord')"></hdiits:button></td></tr>
</table>

<table id="btnUpdate" style="display:none" align="center">
	<tr><TD class="fieldLabel" align="center" colspan="4"><hdiits:button  name="update"  type="button" captionid="TR.updt" bundle="${trngAttendedLables}" onclick="addTrngAttnd('updateRecord')"></hdiits:button></td></tr>
</table>

<table id="txnAddSch" style="display:none" border="1" align="center" width="80%" class="datatable"> 
 		    <tr>
 		     <td class="datatableheader"><b><hdiits:caption captionid="TR.TRAININGTYPE" bundle="${trschLables}"/></b></TD>					   
     		 <td class="datatableheader"><b><hdiits:caption captionid="TR.name" bundle="${trngAttendedLables}"/></b></td>
			 <td class="datatableheader"><b><hdiits:caption captionid="TR.STARTDATE" bundle="${trschLables}"/></b></td>
          	 <td class="datatableheader"><b><hdiits:caption captionid="TR.ENDDATE" bundle="${trschLables}"/></b></td>
             <td class="datatableheader"><b><hdiits:caption captionid="TR.duration" bundle="${trngAttendedLables}"/></b></td>
             <td class="datatableheader"><b><hdiits:caption captionid="TR.ispassed" bundle="${trngAttendedLables}"/></b></td>         
             <td class="datatableheader"><b><hdiits:caption captionid="TR.remarks" bundle="${trngAttendedLables}"/></b></td>
             <!-- <td class="datatableheader"><b><hdiits:caption captionid="TR.place" bundle="${trngAttendedLables}"/></b></td> 			 -->
             <td class="datatableheader"><b>Edit / Delete</b></td>  
    	    </tr>
</table>  

        <c:set var="serialNumber1" value="0" />
        <c:if test="${trngAttendlist != null}">
				<script type="text/javascript">
					arrMulti['txnAddSch'] = new Array();
					
					//alert("new array created...");
				</script>
		</c:if>
	
	<c:forEach items="${trngAttendlist}" var="Lst">
		<c:set var="curXMLFileName1" value="${xmlKeyList[serialNumber1]}" ></c:set>
		<c:set var="type" 	value="${Lst.hrTrTrainingMst.cmnLookupMstTrainingTypeLookupId.lookupName}" />
		<c:set var="tn" 	value="${Lst.hrTrTrainingMst.trainingName}" />
		<c:set var="typeId" 	value="${Lst.hrTrTrainingMst.cmnLookupMstTrainingTypeLookupId.lookupId}" />
		<c:set var="tnId" 	value="${Lst.hrTrTrainingMst.trainingMstId}" />
		<c:set var="trst" 	value="${Lst.startDt}" />
		<c:set var="tret" value="${Lst.endDt}" />
		<c:set var="td" 	value="${Lst.trngDuration}" />
		<c:set var="tr"		value="${Lst.trngResult}" ></c:set> 		 
		<c:set var="trmk"		value="${Lst.trngRemarks}" ></c:set>		 
		
		<script type="text/javascript">
	  		var xmlFileName1 = '${curXMLFileName1}';
	 		
	 		var datetimearray3 = new Array();
			datetimearray3= getDateAndTimeFromDateObj('${trst}');
			
			var datetimearray4 = new Array();
			datetimearray4= getDateAndTimeFromDateObj('${tret}');			
			 
  		  	var tempA1 = new Array('${type}','${tn}',datetimearray3[0],datetimearray4[0],'${td}', '${tr}','${trmk}');			 
  		  	addDBDataInTable('txnAddSch','EditXmlKey',tempA1,xmlFileName1,'editRecord','deleteDBRecord','');
			var rowId = 'rowEditXmlKey${serialNumber1 + 1}';
			arrMulti['txnAddSch'][rowId] = '${typeId},${tnId}'+','+datetimearray3[0]+','+datetimearray4[0];					 			 
			//alert(rowId);						 				
			//alert(arrMulti['txnAddSch'][rowId]);
			 
		</script>
		<c:set var="serialNumber1" value="${serialNumber1 +1}" />	
	</c:forEach> 	 			
	</div>    
</div>
<script type="text/javascript">
document.frmtrngAttended.istrngpassed[0].checked = true;
var navDisplay = false;
</script>
         
  <jsp:include page="../../core/tabnavigation.jsp"/> 
	<script type="text/javascript">
		initializetabcontent("maintab")
				setBackUrl('${pageContext.request.contextPath}'+"/hdiits.htm?viewName=trngAttendedFrnt");
	</script>    
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="lstSchtyp,TrainingId,txtSchstdate,txtScheddate,txtduration,istrngpassed,txtremarks"/>
</hdiits:form>

 			
					
            