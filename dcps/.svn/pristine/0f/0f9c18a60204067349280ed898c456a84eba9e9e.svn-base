 
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../../core/include.jsp" %>
<%@ include file="/WEB-INF/jsp/common/cmnMsg.jsp"%>
<script type="text/javascript" src="<c:url value="script/common/calendar.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/hrms/training/trainingAttendedForExternalTrngs.js"/>"></script>
<script type="text/javascript" src="<c:url value="script/common/addRecord.js"/>"></script>

			<fmt:setBundle basename="resources.trng.TrngAttendedLables" var="trngAttendedLables" scope="request" /> 
			<fmt:setBundle basename="resources.trng.trschLables" var="trschLables"	scope="request" />
			<fmt:setBundle basename="resources.trng.EmployeeDetails" var="EmployeeDetails"	scope="request" />
			<fmt:message key="TR.MSG1" bundle="${trngAttendedLables}" var="msg"/>

<script type="text/javascript">
function noRecords()
{
	//alert("schid"+document.getElementById("schId").value);
	var Rowlength = document.getElementById('txnAddSch').rows.length;

	if(Rowlength <= 1)
	{
		if(document.getElementById("schId").value == "")
		 	{
				alert("Please select the Schedule");
				return false;
			}
		else	{return true;}
	}
	else	{return true;}
}
function verifyDuplicate(tableName,elementNameArray,hiddenField,duplicateAlert)
	{ //alert("Inside verify duplicate");
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

function validateSpecificFormFields(controlNames)
{
 	
	var returnValue = true;
	for (var i=0; i<controlNames.length; i++)
	{
		var strValidation = document.getElementsByName('formField_'+controlNames[i]);
		for (var j=0; j<strValidation.length; j++)
		{
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

		//for hiding the headers	
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
<hdiits:hidden name="trngId" default="" /> 
<hdiits:hidden name="trainingName" default="" /> 
<hdiits:hidden name="trngTypeId" default="" /> 
<hdiits:hidden name="schId" default="" /> 
<hdiits:hidden name="startDt" default=""/>	
<hdiits:hidden name="endDt" default=""/>	
<hdiits:hidden name="txtduration" default=""/>	
<hdiits:hidden name="OrderNumber" default=""/>	
<hdiits:hidden name="Batch" default=""/>	
<hdiits:hidden name="actionFlag" default="InsertionDataIntoDB" />

 		

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
		<div id="tcontent1" class="tabcontent" tabno="0" >   
		<fmt:message key="TR.SEARCH_TRAINING_SCHEDULE"  bundle="${trschLables}" var="scheduleTitle"></fmt:message>
 		<hdiits:jsField name="chkrecords" jsFunction="noRecords()"/>
 		<table class="tabtable">
 
		<tr>					 
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.Name" bundle="${EmployeeDetails}" /></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${resultMap.empName}"></c:out></td>			 			 
				<td class="fieldLabel" width="25%"><b><hdiits:caption captionid="TR.PLACE" bundle="${EmployeeDetails}"/></b></td>
				<td class="fieldLabel" width="25%"><c:out value="${resultMap.empLoc}"></c:out></td>				 
		</tr>	
		<tr>  
			<td class="fieldLabel" colspan="6">
			<jsp:include page="./trngSchSearchForTrngAttended.jsp">
				<jsp:param name="searchScheduleTitle" value="${scheduleTitle}"/>
				<jsp:param name="mandatory" value="Yes" />
			</jsp:include>
			</td>
		</tr>
		 				
		<tr>					 
    		<td class="fieldLabel" width="25%">
    				<hdiits:caption captionid="TR.ispassed" bundle="${trngAttendedLables}" />
    		</td>

			<td class="fieldLabel" width="25%">
					<hdiits:radio name="istrngpassed" id="istrngpassed" value='P' bundle="${trngAttendedLables}" captionid="TR.pass"  validation="txt.isrequired" mandatory="true"  />
					<hdiits:radio name="istrngpassed" value='F'  bundle="${trngAttendedLables}" captionid="TR.fail"  validation="txt.isrequired"  />
			</td>
		    <td class="fieldLabel" width="25%"></td>			
		    <td class="fieldLabel" width="25%"></td>
		</tr>
		<tr>
		      		<td class="fieldLabel" width="25%">
	      					<hdiits:caption captionid="TR.remarks" bundle="${trngAttendedLables}"/>
		      		</td>			
		    		<td class="fieldLabel" width="25%">
				    		 <hdiits:textarea name="txtremarks" captionid="TR.remarks" rows="5" cols="50"  />
				    </td>
				    <td class="fieldLabel" width="25%"></td>
				    <td class="fieldLabel" width="25%"></td>
		</tr>
					
</table>
 
<table id="btnAdd" style="display" align="center">
	<tr><TD class="fieldLabel" align="center" colspan="4"><hdiits:button  name="add"  type="button" captionid="TR.add" bundle="${trngAttendedLables}" onclick="addTrngAttndExternal('addRecord','${msg}')"></hdiits:button></td></tr>
</table>

<table id="btnUpdate" style="display:none" align="center">
	<tr><TD class="fieldLabel" align="center" colspan="4"><hdiits:button  name="update"  type="button" captionid="TR.updt" bundle="${trngAttendedLables}" onclick="addTrngAttndExternal('updateRecord','${msg}')"></hdiits:button></td></tr>
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
		<c:set var="trst" 	value="${Lst.trngScheduleID.startDt}" />
		<c:set var="tret" value="${Lst.trngScheduleID.endDt}" />
		<c:set var="td" 	value="${Lst.trngDuration}" />
		<c:set var="tr"		value="${Lst.trngResult}" ></c:set> 		 
		<c:set var="trmk"		value="${Lst.trngRemarks}" ></c:set>		 
		<c:set var="trngschid"		value="${Lst.trngScheduleID.trngScheduleId}" ></c:set>		 
		<c:set var="trngempid"		value="${Lst.orgEmpMst.empId}" ></c:set>		 
	
		<script type="text/javascript">
	  		var xmlFileName1 = '${curXMLFileName1}';
	 		
	 		var datetimearray3 = new Array();
			datetimearray3= getDateAndTimeFromDateObj('${trst}');
			
			var datetimearray4 = new Array();
			datetimearray4= getDateAndTimeFromDateObj('${tret}');			
			 
  		  	var tempA1 = new Array('${type}','${tn}',datetimearray3[0],datetimearray4[0],'${td}', '${tr}','${trmk}');			 
  		  	addDBDataInTable('txnAddSch','EditXmlKey',tempA1,xmlFileName1,'editRecord','deleteDBRecord','');
			var rowId = 'rowEditXmlKey${serialNumber1 + 1}';
			//arrMulti['txnAddSch'][rowId] = '${typeId},${trngempid},${trngschid},${tnId}'+','+datetimearray3[0]+','+datetimearray4[0];					 			 
			
			 arrMulti['txnAddSch'][rowId] = '${trngempid},${trngschid}';					 			 
			//alert("RowId"+rowId);						 				
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
                
  <jsp:include page="../../core/tabnavigation.jsp" /> 
	<script type="text/javascript">
		initializetabcontent("maintab")
		setBackUrl('${pageContext.request.contextPath}'+"/hdiits.htm?viewName=trngAttendedFrntExternal");
	</script>    
<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' controlNames="trngTypeId,trngId,startDt,endDt,istrngpassed,txtremarks,txtduration"/>
</hdiits:form>
              