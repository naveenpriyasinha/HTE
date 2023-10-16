<%@ include file="../../../core/include.jsp"%>
<%@page buffer="256kb" autoFlush="true"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/DateDifference.js"></script>
<script type="text/javascript" src="script/leave/DateVal.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript" src="script/leave/ajax_saveData.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="script/leave/leavevalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>
<script type="text/javascript" src="script/hrms/ess/travel/travelCommonScripts.js"></script>

<%
try {
%>
<!-- For Xml File List Return By VoGen -->

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>




<c:set var="flagType" value="${resultValue.flagType}">
</c:set>


<c:set var="requestType" value="${resultValue.RequestType}">
</c:set>

<c:set var="hrTravelReq" value="${resultValue.hrTravelReqVo}">
</c:set>

<c:set var="cityList" value="${resultValue.cityList}">
</c:set>
<!--Convyance Mode-->
<c:set var="ConvyanceModeList" value="${resultValue.ConvyanceMode}">
</c:set>
<!--Train-->
<c:set var="TrainList" value="${resultValue.Train}">
</c:set>
<!--Class-->
<c:set var="ClassList" value="${resultValue.Class}">
</c:set>
<!--Accomodation-->
<c:set var="AccomodationList" value="${resultValue.Accomodation}">
</c:set>

<!--Purpose_of_Stay-->
<c:set var="Purpose_of_StayList" value="${resultValue.Purpose_of_Stay}">
</c:set>
<!--owned_by-->
<c:set var="owned_byList" value="${resultValue.owned_by}">
</c:set>
<!--veh_type-->
<c:set var="veh_typeList" value="${resultValue.veh_type}">
</c:set>
<!-- Air Mode -->
<c:set var="Air_modList" value="${resultValue.Air_mod}">
</c:set>


<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.travel.caption" var="caption"
	scope="request" />
	
<style type="text/css">
  .prog-border {
  height: 8px;
  width: 200px;
  background: white;
  border: 1px solid #000;
  border-color: black;
  margin: 1;
  padding: 0;
  }

  .prog-bar {
  height: 6px;
  margin: 2px;
  padding: 0px;
  background: #3299CC;
  }
</style>

<script type="text/javascript">
//Global Variable
	var finalError="";
	var tourCnt=1;
	var tourRowIdArray=new Array();
	var jCnt=0;
//End
	 
	var cityList="";
	var conmodList="";
	var TrainList="";
	var ClassList="";
	var AccomodationList="";
	var Purpose_of_StayList="";
	var owned_byList="";
	var veh_typeList="";
	var air_modVal="";
//Value Array	

	var cityListVal="";
	var conmodListVal="";
	var TrainListVal="";
	var ClassListVal="";
	var AccomodationListVal="";
	var Purpose_of_StayListVal="";
	var owned_byListVal="";
	var veh_typeListVal="";
</script>



<c:forEach var="Air_modList" items="${Air_modList}">
	<script>
		air_modVal+="${Air_modList.lookupId}";
		air_modVal+=",";	
	</script>
</c:forEach>

<c:forEach var="leavetypes" items="${cityList}">
	<script>
		cityList+="${leavetypes.cityName}";
		cityList+=",";	
		cityListVal+="${leavetypes.cityId}";
		cityListVal+=",";	
		
	</script>
</c:forEach>
<c:forEach var="ConvyanceModevar" items="${ConvyanceModeList}">
	<script>
	conmodList+="${ConvyanceModevar.lookupDesc}";
	conmodList+=",";
	conmodListVal+="${ConvyanceModevar.lookupId}";
	conmodListVal+=",";
	
</script>
</c:forEach>
<c:forEach var="TrainListvar" items="${TrainList}">
	<script>
		TrainList+="${TrainListvar.lookupDesc}";
		TrainList+=",";	
		TrainListVal+="${TrainListvar.lookupId}";
		TrainListVal+=",";	
	</script>
</c:forEach>
<c:forEach var="ClassListvar" items="${ClassList}">
	<script>
		ClassList+="${ClassListvar.lookupDesc}";
		ClassList+=",";	
		ClassListVal+="${ClassListvar.lookupId}";
		ClassListVal+=",";	
		
	</script>

</c:forEach>
<c:forEach var="AccomodationListvar" items="${AccomodationList}">
	<script>
		AccomodationList+="${AccomodationListvar.lookupDesc}";
		AccomodationList+=",";	
		AccomodationListVal+="${AccomodationListvar.lookupId}";
		AccomodationListVal+=",";	
		
	</script>

</c:forEach>
<c:forEach var="Purpose_of_StayListvar" items="${Purpose_of_StayList}">
	<script>
		Purpose_of_StayList+="${Purpose_of_StayListvar.lookupDesc}";
		Purpose_of_StayList+=",";	
		Purpose_of_StayListVal+="${Purpose_of_StayListvar.lookupId}";
		Purpose_of_StayListVal+=",";	
		
	</script>
</c:forEach>
<c:forEach var="owned_byvar" items="${owned_byList}">
	<script>
		owned_byList+="${owned_byvar.lookupDesc}";
		owned_byList+=",";	
		owned_byListVal+="${owned_byvar.lookupId}";
		owned_byListVal+=",";	
	</script>
</c:forEach>

<c:forEach var="veh_typevar" items="${veh_typeList}">
	<script>
		veh_typeList+="${veh_typevar.lookupDesc}";
		veh_typeList+=",";	
		veh_typeListVal+="${veh_typevar.lookupId}";
		veh_typeListVal+=",";	
	</script>
</c:forEach>


<script type="text/javascript">
	
	var cityCmb=new Array();
	var conmodCmb=new Array();
	var trainCmb=new Array();
	var classCmb=new Array();
	var accomCmb=new Array();
	var purCmb=new Array();
	var veh_typeCmb=new Array();
	var own_byCmb=new Array();
	
	var cityCmbVal=new Array();
	var conmodCmbVal=new Array();
	var trainCmbVal=new Array();
	var classCmbVal=new Array();
	var accomCmbVal=new Array();
	var purCmbVal=new Array();
	var veh_typeCmbVal=new Array();
	var own_byCmbVal=new Array();
	
	var air_modCmb=new Array();
	air_modCmb=air_modVal.split(",");
	
	
	conmodCmb=conmodList.split(",");
	cityCmb=cityList.split(",");
	trainCmb=TrainList.split(",");
	classCmb=ClassList.split(",");
	accomCmb=AccomodationList.split(",");
	purCmb=Purpose_of_StayList.split(",");
	veh_typeCmb=veh_typeList.split(",");
	own_byCmb=owned_byList.split(",");
	
	cityCmbVal=cityListVal.split(",");
	conmodCmbVal=conmodListVal.split(",");
	trainCmbVal=TrainListVal.split(",");
	classCmbVal=ClassListVal.split(",");
	accomCmbVal=AccomodationListVal.split(",");
	purCmbVal=Purpose_of_StayListVal.split(",");
	veh_typeCmbVal=veh_typeListVal.split(",");
	own_byCmbVal=owned_byListVal.split(",");
	function cancleRequest(form)
	{
		
		lCnt=0;
		
		for (var lCntr = 0; lCntr < document.frm1.elements.length; lCntr++)
		{ 
			var reqId=document.frm1.elements[lCntr].id;

		     if((document.frm1.elements[lCntr].type == "checkbox") && (document.frm1.elements[lCntr].checked == true )&&(reqId.substring(0,9)=="reqChkBox") ) 
		     {
		        id=document.frm1.elements[lCntr].id;
		        lCnt++;
		      }
		}
		if(lCnt>1)
		{
			alert("<fmt:message key="HRMS.selactonerow" bundle="${caption}" />");    				
			 form.selected="";
	  		 return;
			 
		}

		
	}
	
	function deleteReq()
	{
		lCnt=0;
		var id=0;
		for (var lCntr = 0; lCntr < document.frm1.elements.length; lCntr++)
		{ 
			var reqId=document.frm1.elements[lCntr].id;

		     if((document.frm1.elements[lCntr].type == "checkbox") && (document.frm1.elements[lCntr].checked == true )&&(reqId.substring(0,9)=="reqChkBox") ) 
		     {
		        id=document.frm1.elements[lCntr].id;
		        lCnt++;
		      }
		}
		if(lCnt>1)
		{
			alert("<fmt:message key="HRMS.selactonerow" bundle="${caption}" />");  
	  		 return;
			 
		}
		if(lCnt==0)
		{
			alert("<fmt:message key="HRMS.selactonerow" bundle="${caption}" />");  
	  		 return;
		}
		var subid=id.split("reqChkBox");
		var RequestId=document.getElementById('RequestId');
		RequestId.value=subid[1];
		var urlstyle="hdiits.htm?actionFlag=requestCancle&cancleflag=true";
		document.frm1.action=urlstyle;
		document.frm1.submit(); 
		
	}
	function viewDetails(form)
	{	
	
	//	alert("In a view Details method =="+form.id);
		resetTourDetails();
		var ParentReqId=document.getElementById('ParentReqId');
		ParentReqId.value=form.id;
		var req_id=document.getElementById('RequestId');
		req_id.value=form.id;
		var parmArray=new Array();
		parmArray[0]='RequestId';
		addOrUpdateRecord('adddata','getDraftData',parmArray);

		//Buttons
	 	var addButton=document.getElementById('addButton');
	   	var SaveButton=document.getElementById('SaveButton');
	   	var addtripbt=document.getElementById('addtripbt'); 
	   	var saveasButton=document.getElementById('saveasButton');  
	   	addButton.style.display="";
	   	SaveButton.style.display="";
	   	saveasButton.style.display="";
		addtripbt.style.display="";
		
		
		//Tables
		var detailsTable=document.getElementById('detailsTable');
		detailsTable.style.display="";
		var attachtb=document.getElementById('attachtb');
	   	attachtb.style.display="";
	   	var mainTable=document.getElementById('mainTable');
		mainTable.style.display="";
		   	
	
	}
	function resetTourDetails()
	{
		var tb=document.getElementById('tripdtls');
		var len=tb.rows.length;
		for(var i=1;i<len;i++)
		{
			
			tb.deleteRow(1);
			
		}
		
	}
	function adddata()
	{
		//alert('In a add data tabele');
		if (xmlHttp.readyState == 4) 
		{
		//	alert(xmlHttp.responseText);
			var res=xmlHttp.responseText;
		//	alert(res);
			
			var respArray=new Array();
			respArray=res.split("##");
		//	alert("No of Tours="+respArray.length);
			
			for(var i=0;i<respArray.length;i++)
			{
			//	alert("TourName And Path==="+respArray[i]);
				var xmlPath=respArray[i].split("$$");
				var Trip_Name=document.getElementById("Trip_Name");
				var IdArray=new Array();
				IdArray=xmlPath[1].split(",");
				var tmpArray=IdArray[0].split("@@");
				var status=new Array();
				status=tmpArray[0].split("^^");
				//alert('Tour Id::'+tmpArray[1]);
				Trip_Name.value=status[0];
				
				var s="";
				if(status[1]=="P")
				{
					s="Pending";
				}else if(status[1]=="Y")
				{
					s="Approve";
				}else if(status[1]=="R")
				{
					s="Rejected";
				}
				
				if(IdArray[1]!="1")
				{
				//	alert("Attachment id is not null and it is :::"+IdArray[1]);
					var rowNum = addDBDataInTableAttachmentforTravel('tripdtls','encXML',new Array(status[0]),xmlPath[0],IdArray[1],'','cancleRequest','editRecordForDraft',tmpArray[1]);
				}else
				{
				//	alert("Attachment id is null ");
	//				var rowNum=addDBDataInTable('tripdtls','encXML',new Array(IdArray[0]),xmlPath[0],'editRecordForDraft','deleteTravel','');
					var rowNum = addDBDataInTableAttachmentforTravel('tripdtls','encXML',new Array(status[0]),xmlPath[0],'','','cancleRequest','editRecordForDraft',tmpArray[1]);
				}
		//		alert("TriName="+xmlPath[1]);
//				var rowNum = addDBDataInTableAttachment('tripdtls','encXML',new Array('Trip_Name'),xmlPath[0],'editRecordForDraft','deleteTravel','');
		//		var rowNum = addDataInTableForDraft('tripdtls','encXML',new Array('Trip_Name'),'editRecordForDraft','deleteTravel','',xmlPath[0]);
		//		alert("Row Nomber=="+rowNum);
			}
		}
	}
	function cancleRequest(tourId)
	{
		alert('In a cancle Requst method:::>>>'+tourId);
		var urlstyle="hdiits.htm?actionFlag=requestCancle&cancleflag=true&RequestId="+tourId;
		document.frm1.action=urlstyle;
		document.frm1.submit(); 
	
	}
	function addDBDataInTableAttachmentforTravel(tableId, hiddenField, displayFieldArray, xmlFilePath, attachmentIds,
			editMethodName, deleteMethodName, viewMethodName,tourId)
	{
	
		if(deleteMethodName == undefined) {
			deleteMethodName = '';
		}
		if(editMethodName == undefined) {
			editMethodName = '';
		}
		if(viewMethodName == undefined) {
			viewMethodName = '';
		}
		
//		alert('attachmentIds : ' + attachmentIds);
		document.getElementById(tableId).style.display='';
		var trow=document.getElementById(tableId).insertRow();

		trow.id = 'row' + hiddenField + counter;
		trow.insertCell(0).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "' id='" + hiddenField + counter + "' value='" +xmlFilePath  + "_N' />";
		trow.cells[0].style.display = 'none';		
		
		var len = displayFieldArray.length;
		for(var i = 0; i < len; i++) 
		{
			trow.insertCell(i+1).innerHTML = displayFieldArray[i];	
		}	
		if(document.getElementById("Incremented_rowNumber")==null)
		{
			var table = document.getElementById(tableId);
			table.rows[0].insertCell(len+1).innerHTML = "<INPUT type='hidden' name='Incremented_rowNumber' id='Incremented_rowNumber' value='1'/>";
			table.rows[0].cells[len+1].style.display = 'none';
		}
		var rowNumberForRow = document.getElementById("Incremented_rowNumber").value;
		var editCap = "";
		var delCap  = "";
		var viewCap = "";
		try 
		{
			editCap = cmnLblArray[0];
			delCap  = cmnLblArray[1];
			viewCap = cmnLblArray[4];
		}
		catch (e)
		{
//			alert ("EXCEPTION THROWN ");
			editCap = "Edit";
			delCap  = "Cancel";
			viewCap = "View";
		}
		
		
		if(editMethodName!='' && deleteMethodName!='' && viewMethodName!='') 		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+delCap+"</a>";			
		
		else if(editMethodName!='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>"+editCap+"</a> / <a href=javascript:void('delete') onclick=javascript:" +deleteMethodName + "('" + tourId + "')>"+delCap+"</a>";										
												 
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName!='')											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow +
												 "')>View</a> / <a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>"+editCap+"</a>";
												 
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName!='')											 											 
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id + "','" + rowNumberForRow + 
												 "')>View</a> / <a href=javascript:void('delete') onclick=javascript:" + deleteMethodName + "('" + tourId + "')>"+delCap+"</a>";			
		
		else if(editMethodName=='' && deleteMethodName!='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('delete') onclick=javascript:" +deleteMethodName + "('" + tourId + "')>"+delCap+"</a>";
		
		else if(editMethodName!='' && deleteMethodName=='' && viewMethodName=='')
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('edit') onclick=javascript:" + editMethodName + "('" + trow.id + "','" + rowNumberForRow + "')>"+editCap+"</a>";		
			
		else if(editMethodName=='' && deleteMethodName=='' && viewMethodName!='')		
			trow.insertCell(len + 1).innerHTML = "<a href=javascript:void('view') onclick=javascript:" + viewMethodName + "('" + trow.id +  "','" + rowNumberForRow + "')>View</a>";
	
		trow.insertCell(len + 2).innerHTML = "<INPUT type='hidden' name='attachment" + hiddenField + "' id='attachment" + hiddenField + counter + "' value='" + attachmentIds + "' />";		
		trow.cells[len + 2].style.display = 'none';					
	
		trow.insertCell(len + 3).innerHTML = "<INPUT type='hidden' name='" + hiddenField + "_rowNumber' value='" + rowNumberForRow + "'/>";
		trow.cells[len + 3].style.display = 'none';

		rowNumberForRow = parseInt(rowNumberForRow) + 1;
		document.getElementById("Incremented_rowNumber").value = rowNumberForRow;
	
		counter++;
	return rowNumberForRow - 1;
	}


	
	function deleteRecord(rowId)
	{

		var urlstyle="hdiits.htm?actionFlag=removeTour&cancleflag=true&reqTourId="+rowId;
		document.frm1.action=urlstyle;
		document.frm1.submit(); 
	}
	function editRecordForDraft(rowId,rowNum)
	{
			updateRow=null;
			
			resettripdtls();
			deleteallrows(); 
	
		//		alert("In Edit Record Method for Draft Method");
		//		alert('rowId='+rowId+'And rowNum='+rowNum);
			var detailsTable=document.getElementById('detailsTable');
			detailsTable.style.display="";
			//Buttons to hide
			
			
			
		  	var addButton=document.getElementById('addButton');
		  	var savetrip=document.getElementById('savetrip');
		   	var SaveButton=document.getElementById('SaveButton');
		//   	var addtripbt=document.getElementById('addtripbt'); 
		   	var saveasButton=document.getElementById('saveasButton');  
		   	addButton.style.display="";
		   	savetrip.style.display="";
		   //	SaveButton.disabled="none";
		   	saveasButton.disabled="none";
		   	
		 
		   	var attachtb=document.getElementById('attachtb');
		   	attachtb.style.display="";
		   	
			sendAjaxRequestForEditAttachment(rowId, 'populateForm','TravelAttachment',rowNum);
	}
   function changeReqType(form)
   {
	   	 	
   		if(form.value=='500007'||form.value=='500057')
  	 	{
	  	 	var urlstyle="hdiits.htm?actionFlag=getTravelView&flag=draft"
			document.frm1.action=urlstyle;
			document.frm1.submit(); 
   		}
   		if(form.value=='500008'||form.value=='500058')
   		{
   			var urlstyle="hdiits.htm?actionFlag=getTravelView&flag=pending"
			document.frm1.action=urlstyle;
			document.frm1.submit(); 
   		}
   		if(form.value=='500006'||form.value=='500056')
   		{
   			var urlstyle="hdiits.htm?actionFlag=getTravelView&flag=approve"
			document.frm1.action=urlstyle;
			document.frm1.submit(); 
   		}
   }
function saveasdraft()
	{
		var tb = document.getElementById('detailsTable');
		var len=tb.rows.length;
 		var row=(len*1-2)/2;
 		var NullCnt=0;
 		for(var i=0;i<row;i++)
 		{
			var flag=checkforEmptyDetails(i);
			
			if(flag!=0)
			{
				NullCnt=NullCnt*1+1;
			}
 		}
 		if(NullCnt!=0)
 		{
	 			var trippurpose=document.getElementById('purpose');
				var ref_file_no=document.getElementById('ref_file_no'); 
				
				if(trippurpose.value=="")
				{
					alert("<fmt:message key="HRMS.trippurpose" bundle="${alertLables}" />");      
					return false;
				}
				if(ref_file_no.value=="")
				{
					alert("<fmt:message key="HRMS.reffileno" bundle="${alertLables}" />");    				
					return false;
				}
				var arr_plc0=document.getElementById('arr_plc0');
				if(arr_plc0.value=='321611')
				{
					alert("<fmt:message key="HRMS.startplace" bundle="${alertLables}" />");    				
					
					return false;
				}
				var arr_dt0=document.getElementById('arr_dt0');
				if(arr_dt0.value==" ")
				{
					alert("<fmt:message key="HRMS.arrivaldate" bundle="${alertLables}" />");    	
					
					return false;
				}
				var arr_plc1=document.getElementById('arr_plc1');
				if(arr_plc1.value=='321611')
				{
					alert("<fmt:message key="HRMS.lastplace" bundle="${alertLables}" />");    	
					return false;
				}
				addtrip('addRecord');
				
				var valpresent=document.getElementById('valpresent');
   	 	     	valpresent.value="false";
   	 	     	var draftReq=document.getElementById('draftReq');
   	 	     	draftReq.value="true";
				var urlstyle="hrms.htm?actionFlag=addTrip&reqtype=submit";
				
				finalSubmit(urlstyle);
	 			
	 	}else
 		{
 				
 			var tripdtls=document.getElementById("tripdtls");
 	
 			if(tripdtls.rows.length>1)
 			{
 				var draftReq=document.getElementById('draftReq');
   	 	     	draftReq.value="true";
   	 	     	var valpresent=document.getElementById('valpresent');
   	 	     	valpresent.value="false";
 				var urlstyle="hrms.htm?actionFlag=addTrip&reqtype=submit";
				finalSubmit(urlstyle);
 			}else
 			{
 				alert("<fmt:message key="HRMS.touralongwithrequest" bundle="${alertLables}" />");  
 				return;
 			}
 		}
 		
	}
	
	var methodName="checkForDraft";
	var attachmentName="TravelAttachment"; 
	var pollTime=50;
	function test()
	{	
		finalError="";
		for (var lCntr = 0; lCntr < document.frm1.elements.length; lCntr++)
		{ 
		     if(document.frm1.elements[lCntr].type == "hidden")  
		     {
		        id=document.frm1.elements[lCntr].id;
		   		var subid=id.substring(0,6);
		   		var i=id.substring(6,7);
		   		if(subid=="encXML")
		   		{
		   		
			   		var row_nm="rowencXML"+i;
					sendAjaxRequest(row_nm, methodName, attachmentName, i);
					//sendAjaxRequest(row_nm, 'checkForDraft','TravelAttachment',i);
				//	setTimeout(dummy,"50000000");			   		
		   		}
		   		
		      }
		     
		}

		var error=document.getElementById('errorId');		
//		var errorCode=error.innerHTML;
		if(finalError.length!=0)
		{
			error.innerHTML=finalError;
			return false;
		}else
		{
			error.innerHTML="";
			return true;
		}
		
	}
	function sendAjaxRequest(rowId, methodName, attachementName, rowNumber) 
	{
		var hField = rowId.substring(3, rowId.length);	
		var xmlFileName = document.getElementById(hField).value;	
	//	alert("xmlFileName IN sendAjaxRequestForEditAttachment:" +xmlFileName);
		
		var attachHField = 'attachment' + hField;
	//	alert('attachHField : ' + attachHField);
		var attachHFieldObj = document.getElementById(attachHField);
	//	alert('attachHFieldObj : ' + attachHFieldObj);	
		
		if(isAllreadyAddedVOFileName(xmlFileName))
		{
			flagForUpdatedVO = true;		
		}
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		 	alert("<fmt:message key="HRMS.browsernotsupportajax" bundle="${caption}" />"); 
		  return;
		} 
	
		var attachmentString = '&attachmentName=';
		var attachementNames = attachementName.split(',');
		for (var i = 0; i < attachementNames.length; i++)  
		{
			var currRowNum = rowNumber-1;
			var removeFun = 'removeRowFromTable'+attachementNames[i] + '('+currRowNum+',"EditAttachmentIsTrue")';
		
			eval(removeFun);
		
			attachmentString = attachmentString + attachementNames[i];
			if(i<(attachementNames.length-1))
			{
				attachmentString = attachmentString + '&attachmentName=';
			}
		}

		var attachmentIdString = '';
		if(attachHFieldObj != null) {	
			var attachmentIds = attachHFieldObj.value;
	//		alert('attachmentIds : ' + attachmentIds);	
		
			var attachementIdsArr = attachmentIds.split(',');	
	//		alert('attachementIdsArr.length : ' + attachementIdsArr.length);
			for (var i = 0; i < attachementIdsArr.length; i++)  
			{
				attachmentIdString = attachmentIdString + '&attachmentId=' + attachementIdsArr[i];
			}
		}
	
	//var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName+'&attachmentName=' + attachementName +'&rowNumber='+rowNumber;
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName+ attachmentString + attachmentIdString + '&rowNumber='+rowNumber;
	//	alert(url);
		
		//alert('sendAjaxRequestForEditAttachment:url '+url);
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() { eval(methodName); }
	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);	
	}
	
	function checkForDraft()
	{
		
		if (xmlHttp.readyState == 4) 
		 { 				  
			  	var decXML = xmlHttp.responseText;
				var xmlDOM=populateAttachment(decXML,'frm1');
				var len=getChildNodeLengnthOfGivenSet(xmlDOM, 'hrTravelJourneyDtlses');	
				var tripName=getXPathValueFromDOM(xmlDOM,'tourName');
				var errorStr="<font color='BLUE'><br>";
				errorStr=errorStr+tripName+"</font><font color='BLACK'>";
				var indiError="";
				var checkStr="";
			for(var j=0;j<len;j++)
			{
						
						indiError=indiError+"<Br>For Journey"+j+"::</font><font color='RED'>";					
						var parentNode='hrTravelJourneyDtlses['+j+']';
						var i=getXPathValueFromDOM(xmlDOM, parentNode+'/tourRowId');
					
						var arr_plc;
						var arr_dt;
						var arr_tm;
						var depart_plc;
						var depart_dt;
						var depart_tm;
						var con_mod;
						var dis;
						var purpose;
						var accom;
						var cost;
						var remark;
						var sub_train;
						var sub_class;
						var sub_own;
						var sub_veh;
						var airmod;
						
						
						arr_plc=getXPathValueFromDOM(xmlDOM, parentNode+'/travelFromPlace/cityId');
				
						if(arr_plc=="321611")
						{
							indiError=indiError+"<fmt:message key="HRMS.Arrival_Place" bundle="${alertLables}" />";
							alert("<fmt:message key="HRMS.arrivalplaceinjourney" bundle="${alertLables}" />" + j);  
							
							checkStr=checkStr+"a";
						}
						depart_plc=getXPathValueFromDOM(xmlDOM, parentNode+'/travelToPlace/cityId');
					
						if(depart_plc=="321611")
						{
							alert("<fmt:message key="HRMS.departureplaceinjourney" bundle="${alertLables}" />" + j);  
							indiError=indiError+","+"<fmt:message key="HRMS.Departure_Place" bundle="${alertLables}" />";
							checkStr=checkStr+"a";
						}
						

						con_mod=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTravelMode/lookupId');
			
						if(con_mod=="321611")
						{
						alert("<fmt:message key="HRMS.conveyancemodejourney" bundle="${alertLables}" />" + j);  
						
							indiError=indiError+","+"<fmt:message key="HRMS.Conveyance_Mode" bundle="${alertLables}" />";
							checkStr=checkStr+"a";
						}else if(con_mod=="500002" ||con_mod=="500022")
						{
						
						}else if(con_mod=='321595'||con_mod=="321599")
						{
								
								sub_train=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTrainName/lookupId')
								if(sub_train=="321611")
								{
									alert("<fmt:message key="HRMS.trainnamejourney" bundle="${alertLables}" />" + j);  
									
									indiError=indiError+","+"<fmt:message key="HRMS.Train_Name" bundle="${alertLables}" />";
									checkStr=checkStr+"a";
								}
								
								sub_class=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTravelClass/lookupId')
								if(sub_class=="321611")
								{
									alert("<fmt:message key="HRMS.classnamejourney" bundle="${alertLables}" />" + j);
									
									indiError=indiError+","+"<fmt:message key="HRMS.Purpose" bundle="${alertLables}" />";
									checkStr=checkStr+"a";
									
								}
								
						}else if (con_mod=='321600' ||con_mod=="321596")
						{
								
								sub_own=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByGovtOrGovtpaid/lookupId')
								if(sub_own=="321611")
								{
									alert("<fmt:message key="HRMS.ownedbyjourney" bundle="${alertLables}" />" + j);  
								
									indiError=indiError+","+"<fmt:message key="HRMS.Owned" bundle="${alertLables}" />";
									checkStr=checkStr+"a";
								}
								if(sub_own=="321571"||sub_own=="321572"||sub_own=="321577"||sub_own=="321578")
								{
									sub_veh=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByVehicleType/lookupId')
									
									if(sub_veh=="321611")
									{
										alert("<fmt:message key="HRMS.vehicletypejourney" bundle="${alertLables}" />" + j);  
										indiError=indiError+","+"<fmt:message key="HRMS.Vehicle_Type" bundle="${alertLables}" />";
										checkStr=checkStr+"a";
									}
								}
								
								cost=getXPathValueFromDOM(xmlDOM, parentNode+'/costIncurred');
								if(cost==" ")
								{
									alert("<fmt:message key="HRMS.entercost" bundle="${alertLables}" />");  
										indiError=indiError+","+"<fmt:message key="HRMS.Total_Cost" bundle="${alertLables}" />";
									checkStr=checkStr+"a";
								}
								
						}
						
						purpose=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByPurposeOfStay/lookupId');

						if(purpose=="321611")
						{	
								if(j!=1)
								{
									alert("<fmt:message key="HRMS.selectpurposestayjourney" bundle="${alertLables}" />");  
									indiError=indiError+","+"<fmt:message key="HRMS.Purose_Of_Stay" bundle="${alertLables}" />";
									checkStr=checkStr+"a";
								}
						}
								
						
						var tmp_dt=new Date();
						tmp_dt=getXPathValueFromDOM(xmlDOM, parentNode+'/travelFromDate');
				
						
						var arrayDate=new Array();
						arrayDate=getDateAndTimeFromDateObj(tmp_dt);
						
						if(arrayDate[0]=="11/11/1111")
						{
							alert("<fmt:message key="HRMS.arrivaldataintrip" bundle="${alertLables}" />" + tripName);  
							indiError=indiError+","+"<fmt:message key="HRMS.Arrival_Date" bundle="${alertLables}" />";
							checkStr=checkStr+"a";
							
							
						}
						if(arrayDate[1]=="00:00")
						{
							alert("<fmt:message key="HRMS.selectarrivaltime" bundle="${alertLables}" />" + tripName);  
								indiError=indiError+","+"<fmt:message key="HRMS.Time" bundle="${alertLables}" />";
							checkStr=checkStr+"a";
						}
						
						tmp_dt=getXPathValueFromDOM(xmlDOM, parentNode+'/travelToDate');
						arrayDate=getDateAndTimeFromDateObj(tmp_dt);
						
				
						if(arrayDate[0]=="11/11/1111")
						{
							alert("<fmt:message key="HRMS.departuredataintrip" bundle="${alertLables}" />" + tripName);
						
							indiError=indiError+","+"<fmt:message key="HRMS.Departure_Date" bundle="${alertLables}" />";
							checkStr=checkStr+"a";
						}
						if(arrayDate[1]=="00:00")
						{
							alert("<fmt:message key="HRMS.departuretime" bundle="${alertLables}" />");
						
							indiError=indiError+","+"<fmt:message key="HRMS.Time" bundle="${alertLables}" />";					
							checkStr=checkStr+"a";						}
						
						accom=getXPathValueFromDOM(xmlDOM, parentNode+'/hrTravelAcomDtlses[0]/accomType');
						if(accom=="321611")
						 {
							 if(j!=1)
							{
							 	alert("<fmt:message key="HRMS.selectaccomodationtype" bundle="${alertLables}" />");
							 	indiError=indiError+","+"<fmt:message key="HRMS.Accomodation" bundle="${alertLables}" />";
							 	checkStr=checkStr+"a";
							 }
						}
				}
				if(checkStr.length!=0)
				{
					errorStr=errorStr+indiError;
					finalError=finalError+errorStr;
				}  
				
		}
	}
	function generateDtlsArray()
	{
		var tb=document.getElementById('tripdtls');
		var trip_nm="";
		var trippurpose=document.getElementById('purpose');
		var ref_file_no=document.getElementById('ref_file_no'); 
		
	
		
		
		if(trippurpose.value=="")
		{
			alert("<fmt:message key="HRMS.trippurpose" bundle="${alertLables}" />");      
			return false;
		}
		if(ref_file_no.value=="")
		{
			alert("<fmt:message key="HRMS.reffileno" bundle="${alertLables}" />");    
			return false;
		}
		var arr_plc0=document.getElementById('arr_plc0');
		if(arr_plc0.value=='321611')
		{
			alert("<fmt:message key="HRMS.startplace" bundle="${alertLables}" />");
			return false;
		}
		var arr_dt0=document.getElementById('arr_dt0');
		if(arr_dt0.value==" ")
		{
			alert("<fmt:message key="HRMS.arrivaldate" bundle="${alertLables}" />");  
			return false;
		}
		var arr_plc1=document.getElementById('arr_plc1');
		if(arr_plc1.value=='321611')
		{
			alert("<fmt:message key="HRMS.lastplace" bundle="${alertLables}" />");   
			return false;
		}
		trip_nm=arr_plc0.value+"<font colore='BLUE'> To </font> "+arr_plc1.value+"<font colore='BLUE'> - <font>"+arr_dt0.value;
		
		var len = tb.rows.length;
		var dtlstb = document.getElementById('detailsTable');
		var dtlslen = dtlstb.rows.length;
		var dtlsrow=(dtlslen*1-2)/2;
		
		var trip=new Array();
		var journey=new Array();
		var attachment=new Array();
		var cnt=0;
	 

		for(var i=0;i<dtlsrow;i++)
		{
			var arr_plc_nm="arr_plc"+i;
			var arr_dt_nm="arr_dt"+i;
			var arr_tm_nm="arr_tm"+i;
			var depart_plc_nm="depart_plc"+i;
			var depart_dt_nm="depart_dt"+i;
			var depart_tm_nm="depart_tm"+i;
			var con_mod_nm="con_mod"+i;
			var dis_nm="dis"+i;
			var purpose_nm="purpose"+i;
			var accom_nm="accom"+i;
			var cost_nm="cost"+i;
			var remark_nm="remark"+i;
			var sub_train_nm="sub_train"+i;
			var sub_class_nm="sub_class"+i;
			var sub_own_nm="sub_own"+i;
			var sub_veh_nm="sub_veh"+i;
			var airmod_nm="airmod"+i;

			
			
			var arr_plc=document.getElementById(arr_plc_nm);
			var arr_dt=document.getElementById(arr_dt_nm);
			var arr_tm=document.getElementById(arr_tm_nm);
			var depart_plc=document.getElementById(depart_plc_nm);
			var depart_dt=document.getElementById(depart_dt_nm);
			var depart_tm=document.getElementById(depart_tm_nm);
			var con_mod=document.getElementById(con_mod_nm);
			var dis=document.getElementById(dis_nm);
			var purpose=document.getElementById(purpose_nm);
			var accom=document.getElementById(accom_nm);
			var cost=document.getElementById(cost_nm);
			var remark=document.getElementById(remark_nm);
			var sub_train=document.getElementById(sub_train_nm);
			var sub_class=document.getElementById(sub_class_nm);
			var sub_own=document.getElementById(sub_own_nm);
			var sub_veh=document.getElementById(sub_veh_nm);
			var airmod=document.getElementById(airmod_nm);
			
			
			journey[cnt]=arr_plc.value;
			cnt=cnt*1+1;
			journey[cnt]=arr_dt.value;
			cnt=cnt*1+1;
			journey[cnt]=arr_tm.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_plc.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_dt.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_tm.value;
			cnt=cnt*1+1;
			journey[cnt]=con_mod.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_train.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_class.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_own.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_veh.value;									
			cnt=cnt*1+1;
			journey[cnt]=airmod.value;	
			
			cnt=cnt*1+1;
			if(!isNaN(dis.value))
			{
				journey[cnt]=dis.value;
			}
			else
			{
				alert("<fmt:message key="HRMS.distanceintegervalkuse" bundle="${alertLables}" />");  
				return ;
			}
			cnt=cnt*1+1;
			journey[cnt]=purpose.value;
			cnt=cnt*1+1;
			journey[cnt]=accom.value;
			cnt=cnt*1+1;
			if(!isNaN(cost.value))
			{
				journey[cnt]=cost.value;			
			}else
			{ 
				alert("<fmt:message key="HRMS.costintegervalue" bundle="${alertLables}" />");
					return ;
			}
			cnt=cnt*1+1;
			
			if(remark.value!="")
			{
				journey[cnt]=remark.value;
			}else
			{
				journey[cnt]="$";		
			}
			cnt=cnt*1+1;
			
		}
 				
				var arr_plc0=document.getElementById("arr_plc0");
 				var arr_plc1=document.getElementById("arr_plc1");
 				var arr_dt0=document.getElementById("arr_dt0");
 				var tmp ="<fmt:message key="HRMS.To" bundle="${caption}" />";
 				var name=arr_plc0.options[arr_plc0.selectedIndex].text +" "+tmp+" "+arr_plc1.options[arr_plc1.selectedIndex].text+" - "+arr_dt0.value;		0
 				var trippurpose=document.getElementById("purpose");
				var refFile=document.getElementById("ref_file_no");
   	 	     	var tourDtlslist=new Array();
   	 	     	tourDtlslist[0]=name;
   	 	     	tourDtlslist[1]=tourCnt;
   	 	     	tourCnt++;
   	 	     	tourDtlslist[2]=trippurpose.value;
   	 	     	tourDtlslist[3]=refFile.value;
   	 	     	
   	 	     	     	   	 	     
   	 	       var journeyCnt=document.getElementById('journeyCnt');
   	 	     	
   	 	       var journeyDtls=document.getElementById('journeyDtls');
   	 	     	
   	  		   var tourDtls=document.getElementById('tourDtls');
   	  	
   	  		   journeyCnt.value=dtlsrow;
   	  		 
   	  		   journeyDtls.value=journey;
   	  		  
   	 	  	   tourDtls.value=tourDtlslist;
   	 	  	  
   	 	  	  return true; 
   	 	  	   
	}
	
	function addtrip(flag)
	{
		
	/*	if(!ckeckplace())
		{
			return;
		}
		if(!validdate())
		{
			return;
		}*/
			
			if(!generateDtlsArray())
			{
				return;
				
			}
   	 	   	  var totArray=new Array(3);
   	 	     	totArray[0]='journeyCnt';
   	 	      	totArray[1]='journeyDtls';
   	 	     	totArray[2]='tourDtls';
   	 	     /*	var valpresent=document.getElementById('valpresent');
   	 	     	valpresent.value="true"; */
				var t=document.getElementById("journeyCnt");
			
			  addOrUpdateRecord(flag,'addTrip&reqtype=ajax&valpresent=true',totArray);
		
//		addOrUpdateRecord('updateRecord','setTravelDetailsVoGen',sendArr);
		
	/*	var tb=document.getElementById('tripdtls');
		tb.style.display="";
		addtriprow();
		resettripdtls();
		deleteallrows();*/
	}
	function updateRecord()
	{
		
		if (xmlHttp.readyState == 4) 
		{
			var encXML=xmlHttp.responseText;
			var triptb=document.getElementById('tripdtls');						
			document.getElementById("Sr_no").value=triptb.rows.length;
			var encXML=xmlHttp.responseText;
			
			var arr_plc0=document.getElementById('arr_plc0');
			var arr_dt0=document.getElementById('arr_dt0');
			var arr_plc1=document.getElementById('arr_plc1');
			
			var tmp ="<fmt:message key="HRMS.To" bundle="${caption}" />";
			
			var trip_nm=arr_plc0.options[arr_plc0.selectedIndex].text +" "+tmp+" "+arr_plc1.options[arr_plc1.selectedIndex].text+" - "+arr_dt0.value;		
			
			document.getElementById("Trip_Name").value=trip_nm;
	//		updateDataInTable('encXML',new Array('Sr_no','Trip_Name'));
			
			var rowNum=updateDataInTableAttachment('tripdtls','encXML',new Array('Trip_Name'));
			
			removeRowFromTableTravelAttachment(rowNum,'frm1');		
					
			var savetrip=document.getElementById('savetrip');
			savetrip.style.display="none";		
			
			var addtripbt=document.getElementById('addtripbt');
			addtripbt.disabled="";			
			
			resettripdtls();
			deleteallrows();
	/*		var tabid=document.getElementById('tourDtlstb');
		   	var maintb=document.getElementById('maintb');
		   	var tb = document.getElementById('detailsTable');
		   
		   	var addButton=document.getElementById('addButton');
			var savetrip=document.getElementById('savetrip'); */
			var SaveButton=document.getElementById('SaveButton');
			//   	var addtripbt=document.getElementById('addtripbt'); 
			var saveasButton=document.getElementById('saveasButton');  
			
	//		addButton.style.display="none";
	//	   	savetrip.style.display="none";
		   //	SaveButton.disabled="";
		   	saveasButton.disabled="";
		   	
		   	
		   	var attachtb=document.getElementById('attachtb');
		   	attachtb.style.display="";
   	/*		maintb.style.display="none";
   			tb.style.display="none";   
   			//triptb.style.display="none";  */

		}
		
	}
	function addRecord()
	{
		if (xmlHttp.readyState == 4) 
		{
			var triptb=document.getElementById('tripdtls');						
			document.getElementById("Sr_no").value=triptb.rows.length;
			var encXML=xmlHttp.responseText;
			
		//	var appDesXML = encXML.split('$$$ATTACHMENT_XML$$$');	
		
			
			var arr_plc0=document.getElementById('arr_plc0');
			var arr_dt0=document.getElementById('arr_dt0');
			var arr_plc1=document.getElementById('arr_plc1');
			var tmp ="<fmt:message key="HRMS.To" bundle="${caption}" />";
			trip_nm=arr_plc0.options[arr_plc0.selectedIndex].text +" "+tmp+" "+arr_plc1.options[arr_plc1.selectedIndex].text+" - "+arr_dt0.value;		
			document.getElementById("Trip_Name").value=trip_nm;
			//addDataInTable('tripdtls','encXML',new Array('Sr_no','Trip_Name'),'editRecord','deleteTravel','');
			var rowNum = addDataInTableAttachment('tripdtls','encXML',new Array('Trip_Name'),'editRecord','deleteTravel','');
			
			removeRowFromTableTravelAttachment(rowNum,'frm1');
			
			resettripdtls();
			deleteallrows();
		}
	}
	function deleteTravel(rowId)
	{
		var ans=deleteRecord(rowId);		
	}
	function editRecord(rowId,rowNum)
	{
			var attachtb=document.getElementById('attachtb');
		   	attachtb.style.display="";
			sendAjaxRequestForEditAttachment(rowId, 'populateForm','TravelAttachment',rowNum);

	}
	function populateForm()
	{
		//alert("In a populate form method");
		 if (xmlHttp.readyState == 4) 
		 { 				  
			  	var decXML = xmlHttp.responseText;
			  //	alert(decXML);
			//	var xmlDOM = getDOMFromXML(decXML);																				
//				var xmlDOM = populateAttachment(decXML,'frm1');
				var xmlDOM=populateAttachment(decXML,'frm1');
				
				var len=getChildNodeLengnthOfGivenSet(xmlDOM, 'hrTravelJourneyDtlses');	
			//	alert("No of journey=="+len);
				for(var i=2;i<len;i++)
				{
					addrow();
				}
				
				for(var j=0;j<len;j++)
				{
						
						var parentNode='hrTravelJourneyDtlses['+j+']';
						var i=getXPathValueFromDOM(xmlDOM, parentNode+'/tourRowId');
					//	alert(i);
					
						var arr_plc_nm="arr_plc"+j;
						var arr_dt_nm="arr_dt"+j;
						
						var arr_tm_nm="arr_tm"+j;
						var depart_plc_nm="depart_plc"+j;
						var depart_dt_nm="depart_dt"+j;
						var depart_tm_nm="depart_tm"+j;
						var con_mod_nm="con_mod"+j;
						var dis_nm="dis"+j;
						var purpose_nm="purpose"+j;
						var accom_nm="accom"+j;
						var cost_nm="cost"+j;
						var remark_nm="remark"+j;
						var sub_train_nm="sub_train"+j;
						var sub_class_nm="sub_class"+j;
						var sub_own_nm="sub_own"+j;
						var sub_veh_nm="sub_veh"+j;
						var airmod_nm="airmod"+j;
						
						
						var arr_plc=document.getElementById(arr_plc_nm);
						var arr_dt=document.getElementById(arr_dt_nm);
						var arr_tm=document.getElementById(arr_tm_nm);
						var depart_plc=document.getElementById(depart_plc_nm);
						var depart_dt=document.getElementById(depart_dt_nm);
						var depart_tm=document.getElementById(depart_tm_nm);
						var con_mod=document.getElementById(con_mod_nm);
						var dis=document.getElementById(dis_nm);
						var purpose=document.getElementById(purpose_nm);
						var accom=document.getElementById(accom_nm);
						var cost=document.getElementById(cost_nm);
						var remark=document.getElementById(remark_nm);
						var sub_train=document.getElementById(sub_train_nm);
						var sub_class=document.getElementById(sub_class_nm);
						var sub_own=document.getElementById(sub_own_nm);
						var sub_veh=document.getElementById(sub_veh_nm);
						var airmod=document.getElementsByName(airmod_nm);
					//	alert("arr_plc==="+getXPathValueFromDOM(xmlDOM, parentNode+'/travelFromPlace'));
						
						arr_plc.value=getXPathValueFromDOM(xmlDOM, parentNode+'/travelFromPlace/cityId');
						arr_plc.selected="true";
						
						
						depart_plc.value=getXPathValueFromDOM(xmlDOM, parentNode+'/travelToPlace/cityId');
						depart_plc.selected="true";
						
					
						var mod=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTravelMode/lookupId')
						con_mod.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTravelMode/lookupId');
						con_mod.selected="true";
						
						//Hide Coluem According To Selected TravelMode
							var own_nm="own"+j;
							var bus_nm="bus"+j;
							var eco_nm="eco"+j;
							var veh_nm="veh"+j;
							var trn_nm="trn"+j;
							var class_nm="class"+j;
							var trncol_nm="trncol"+j;
							var clscol_nm="clscol"+j;
							var fir_nm="fir_dm"+j;
							var sec_nm="sec_dm"+j;
							var dislab_nm="dislab"+j;
							var discol_nm="discol"+j;
					
							
							var own=document.getElementById(own_nm);
							var bus=document.getElementById(bus_nm);
							var eco=document.getElementById(eco_nm);
							var veh=document.getElementById(veh_nm);
							var trn=document.getElementById(trn_nm);
							var cls = document.getElementById(class_nm);
							var trncol=document.getElementById(trncol_nm);
							var clscol=document.getElementById(clscol_nm);
							var fir=document.getElementById(fir_nm);
							var sec=document.getElementById(sec_nm);
							var dislab=document.getElementById(dislab_nm);
							var discol=document.getElementById(discol_nm);
				
							
							if(mod=='321611')
							{
								
								own.style.display="none";
								bus.style.display="none";
								eco.style.display="none";
								veh.style.display="none";
								trn.style.display="none";
								cls.style.display="none";
							//	alert(mod);
								trncol.style.display="";
								clscol.style.display="";
								fir.style.display="";
								sec.style.display="";
								discol.style.display="none";
								dislab.style.display="";
								sub_train.style.display="";
								sub_train.disabled="true";
								sub_train.value="321611";
								sub_class.style.display="";
								sub_class.disabled="true";
								sub_class.value="321611";
								sub_own.style.display="none";
								sub_veh.style.display="none";
								
							}else if(mod=="321594" || mod=="321598")
							{
								
								own.style.display="none";
								bus.style.display="";
								var type=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByAirType/lookupId');
								if(type=="350501")
								{
									airmod[0].checked='true';
								}else
								{
									airmod[1].checked='true';
								}
								eco.style.display="";
								veh.style.display="none";
								trn.style.display="none";
								cls.style.display="none";
								trncol.style.display="none";
								clscol.style.display="none";
								fir.style.display="none";
								sec.style.display="none";
								discol.style.display="none";
								dislab.style.display="";
							}else if(mod=='321595'|| mod=="321599")
							{
								sub_train.disabled="";
								sub_train.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTrainName/lookupId')
								sub_train.selected="true";
								
								sub_class.disabled="";
								sub_class.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByTravelClass/lookupId')
								sub_class.selected="true";
								
								own.style.display="none";
								bus.style.display="none";
								eco.style.display="none";
								veh.style.display="none";
								trn.style.display="";
								cls.style.display="";
								trncol.style.display="";
								clscol.style.display="";
								fir.style.display="none";
								sec.style.display="none";
								discol.style.display="none";
								dislab.style.display="";
								sub_train.style.display="";
								sub_class.style.display="";
								sub_own.style.display="none";
								sub_veh.style.display="none";
							}else if (mod=='321600'|| mod=="321596")
							{
								
								own.style.display="";
								bus.style.display="none";
								eco.style.display="none";
								veh.style.display="";
								trn.style.display="none";
								cls.style.display="none";
								trncol.style.display="";
								clscol.style.display="";
								fir.style.display="none";
								sec.style.display="none";
								discol.style.display="";
								dislab.style.display="none";
								sub_train.style.display="none";
								sub_class.style.display="none";
								sub_own.style.display="";
								sub_own.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByGovtOrGovtpaid/lookupId')
								sub_own.selected="true";
								
								sub_veh.style.display="";
								sub_veh.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByVehicleType/lookupId')
								sub_veh.selected="true";
							}
						//End
						
						if(getXPathValueFromDOM(xmlDOM, parentNode+'/remark')=="$")
						{
							remark.value="";
						}else
						{
							remark.value=getXPathValueFromDOM(xmlDOM, parentNode+'/remark');
		//					remark.selected="true";	
						}
					//	alert(getXPathValueFromDOM(xmlDOM, parentNode+'/costIncurred'));
						if(getXPathValueFromDOM(xmlDOM, parentNode+'/costIncurred')==null)
						{
							cost.value="0.0";
					//		alert('CostValue:::::'+cost.value);
						}else
						{
							cost.value=getXPathValueFromDOM(xmlDOM, parentNode+'/costIncurred');
						}
						//cost.selected="true";
						
						purpose.value=getXPathValueFromDOM(xmlDOM, parentNode+'/cmnLookupMstByPurposeOfStay/lookupId');
						
						if(purpose.value=='')
						{
							
							purpose.selectedIndex=0;
						}
						purpose.selected="true";
						
						var tmp_dt=new Date();
						tmp_dt=getXPathValueFromDOM(xmlDOM, parentNode+'/travelFromDate');
			
						
						var arrayDate=new Array();
						arrayDate=getDateAndTimeFromDateObj(tmp_dt);
						
						if(arrayDate[0]!="11/11/1111")
						{
							arr_dt.value=arrayDate[0];
						}
						if(arrayDate[1]!="00:00")
						{
							arr_tm.value=arrayDate[1];
						}
						
						
						tmp_dt=getXPathValueFromDOM(xmlDOM, parentNode+'/travelToDate');
						arrayDate=getDateAndTimeFromDateObj(tmp_dt);
						
						if(arrayDate[0]!="11/11/1111")
						{
							depart_dt.value=arrayDate[0];
						}
						if(arrayDate[1]!="00:00")
						{
							depart_tm.value=arrayDate[1];						
						}
						
					//	alert("Tour Row Id:::::"+getXPathValueFromDOM(xmlDOM, parentNode+'/tourRowId'));
						
						tourRowIdArray[jCnt]=getXPathValueFromDOM(xmlDOM, parentNode+'/tourRowId');
						jCnt=jCnt*1+1;
						
						
						
						accom.value=getXPathValueFromDOM(xmlDOM, parentNode+'/hrTravelAcomDtlses[0]/accomType');
						if(accom.value=='')
						{
							accom.selectedIndex=0;
						}
						accom.selected="true";
				}
				str=getXPathValueFromDOM(xmlDOM, 'travel_From_Date');
				document.getElementById('purpose').value=getXPathValueFromDOM(xmlDOM, 'purpose');
				document.getElementById("ref_file_no").value=getXPathValueFromDOM(xmlDOM, 'refFileNo');
				
				
				var savetrip=document.getElementById('savetrip');
				savetrip.style.display="";				
				
				var addtripbt=document.getElementById('addtripbt');
				addtripbt.disabled="true";		
				
				var tourId=document.getElementById('tourId');
				tourId.value=getXPathValueFromDOM(xmlDOM, 'tourId');		
			//	alert("Tour Id in Populate:::"+tourId.value);

		}
	}	

	
	function checkvalue(i)
	{
		 
			var arr_plc_nm="arr_plc"+i;
			var arr_dt_nm="arr_dt"+i;
			var arr_tm_nm="arr_tm"+i;
			var depart_plc_nm="depart_plc"+i;
			var depart_dt_nm="depart_dt"+i;
			var depart_tm_nm="depart_tm"+i;
			var con_mod_nm="con_mod"+i;
			var dis_nm="dis"+i;
			var purpose_nm="purpose"+i;
			var accom_nm="accom"+i;
			var cost_nm="cost"+i;
			var remark_nm="remark"+i;
			var sub_train_nm="sub_train"+i;
			var sub_class_nm="sub_class"+i;
			var sub_own_nm="sub_own"+i;
			var sub_veh_nm="sub_veh"+i;
			var airmod_nm="airmod"+i;
			
			
			var arr_plc=document.getElementById(arr_plc_nm);
			var arr_dt=document.getElementById(arr_dt_nm);
			var arr_tm=document.getElementById(arr_tm_nm);
			var depart_plc=document.getElementById(depart_plc_nm);
			var depart_dt=document.getElementById(depart_dt_nm);
			var depart_tm=document.getElementById(depart_tm_nm);
			var con_mod=document.getElementById(con_mod_nm);
			var dis=document.getElementById(dis_nm);
			var purpose=document.getElementById(purpose_nm);
			var accom=document.getElementById(accom_nm);
			var cost=document.getElementById(cost_nm);
			var remark=document.getElementById(remark_nm);
			var sub_train=document.getElementById(sub_train_nm);
			var sub_class=document.getElementById(sub_class_nm);
			var sub_own=document.getElementById(sub_own_nm);
			var sub_veh=document.getElementById(sub_veh_nm);
			var airmod=document.getElementById(airmod_nm);
			var trippurpose=document.getElementById("purpose");
			
		
			if(trippurpose.value=="")
			{
				alert("<fmt:message key="HRMS.trippurpose" bundle="${alertLables}" />");
				return false;
			}
			var ref_file_no=document.getElementById('ref_file_no'); 
			if(ref_file_no.value=="")
			{
				alert("<fmt:message key="HRMS.reffileno" bundle="${alertLables}" />");  
				return false;
			}
			if(arr_plc.value=='321611')
			{
				alert("<fmt:message key="HRMS.departureplaceinjourney" bundle="${alertLables}" />");  
			
				return false;
			}
			if(arr_dt.value=="")
			{
				alert("<fmt:message key="HRMS.selectdeparturedate" bundle="${alertLables}" />"); 
				
				return false;
			} 
			if(arr_tm.value=="")
			{
				alert("<fmt:message key="HRMS.departuretime" bundle="${alertLables}" />"); 
		
				return false;
			}
			if(depart_plc.value=="321611")
			{
				alert("<fmt:message key="HRMS.selectarrivalplace" bundle="${alertLables}" />"); 
			
				return false;
			}
			if( depart_dt.value=="")
			{
				alert("<fmt:message key="HRMS.selectarrivaldate" bundle="${alertLables}" />"); 
			
				return false;
			}
			if( depart_tm.value=="")
			{
				alert("<fmt:message key="HRMS.selectarrivaltime" bundle="${alertLables}" />"); 
			
				return false;
			}
			if(con_mod.value=="321611")
			{
				alert("<fmt:message key="HRMS.conveyancemodejourney" bundle="${alertLables}" />"); 
		
				return false;
			}
			alert('con_mod.value'+con_mod.value);
			if(con_mod.value!="321611")
			{
				  
				if(con_mod.value=="Train")
				{
					if(sub_train.value=='321611')
					{
						alert("<fmt:message key="HRMS.selecttrainmode" bundle="${alertLables}" />"); 
					return false;
					}
					if(sub_class.value=="321611")
					{
						alert("<fmt:message key="HRMS.selectclass" bundle="${alertLables}" />"); 
					
						return false;
					}
					
				}
				if(con_mod.value=="Bus")
				{
					if( sub_own.value=='321611')
					{
						alert("<fmt:message key="HRMS.selectownedbymode" bundle="${alertLables}" />"); 
					
						return false;
					}else if(sub_own.selectedIndex=="4"||sub_own.selectedIndex=="5")
					{
						if(sub_veh.value=="321611")
						{
							alert("<fmt:message key="HRMS.vehicletypejourney" bundle="${alertLables}" />"); 
					
							return false;
						}
					}
					if(dis.value=="") 
					{
						alert("<fmt:message key="HRMS.enterapproxdistance" bundle="${alertLables}" />"); 
					
						return false;
					}
			 			
					
				}
				if(con_mod.value=="Air")
				{
					if(airmod.value=="")
					{
						alert("<fmt:message key="HRMS.economicorbusiness" bundle="${alertLables}" />");
					
						return false;
					}
				
				}
			}
			if(purpose.disabled=="")
			{					
				if(purpose.value=='321611')
				{
						alert("<fmt:message key="HRMS.selectpurpose" bundle="${alertLables}" />");
					
						return false;
				}
			}
			if(accom.disabled=="")
			{
				if(accom.value=="321611") 
				{
					alert("<fmt:message key="HRMS.selectaccomodation" bundle="${alertLables}" />");
				
						return false;
				}
			}
			if (cost.value=="")
			{
				alert("<fmt:message key="HRMS.entercost" bundle="${alertLables}" />");
			
					return false;
			}
			
			return true;
		
		
	}
		function checkforEmptyDetails(i)
	{
			var flag=0;
			var arr_plc_nm="arr_plc"+i;
			var arr_dt_nm="arr_dt"+i;
			var arr_tm_nm="arr_tm"+i;
			var depart_plc_nm="depart_plc"+i;
			var depart_dt_nm="depart_dt"+i;
			var depart_tm_nm="depart_tm"+i;
			var con_mod_nm="con_mod"+i;
			var dis_nm="dis"+i;
			var purpose_nm="purpose"+i;
			var accom_nm="accom"+i;
			var cost_nm="cost"+i;
			var remark_nm="remark"+i;
			var sub_train_nm="sub_train"+i;
			var sub_class_nm="sub_class"+i;
			var sub_own_nm="sub_own"+i;
			var sub_veh_nm="sub_veh"+i;
			var airmod_nm="airmod"+i;
			var remark_nm="remark"+i;
			
			var arr_plc=document.getElementById(arr_plc_nm);
			var arr_dt=document.getElementById(arr_dt_nm);
			var arr_tm=document.getElementById(arr_tm_nm);
			var depart_plc=document.getElementById(depart_plc_nm);
			var depart_dt=document.getElementById(depart_dt_nm);
			var depart_tm=document.getElementById(depart_tm_nm);
			var con_mod=document.getElementById(con_mod_nm);
			var dis=document.getElementById(dis_nm);
			var purpose=document.getElementById(purpose_nm);
			var accom=document.getElementById(accom_nm);
			var cost=document.getElementById(cost_nm);
			var remark=document.getElementById(remark_nm);
			var sub_train=document.getElementById(sub_train_nm);
			var sub_class=document.getElementById(sub_class_nm);
			var sub_own=document.getElementById(sub_own_nm);
			var sub_veh=document.getElementById(sub_veh_nm);
			var airmod=document.getElementById(airmod_nm);
			var trippurpose=document.getElementById("purpose");
			var remark=document.getElementById(remark_nm);
			
			if(trippurpose.value.length!=0)
			{
				flag=1;
			//	alert('trippurpose');
				return flag;
			}
			
			if(arr_plc.value!='321611')
			{
				flag=1;
				
				return flag;
			}
			if(arr_dt.value.length!=0)
			{
				flag=1;
		//		alert('arr_dt==='+arr_dt.value.length);
				return flag;
			} 
			if(arr_tm.value.length!=0)
			{
				flag=1;
		//		alert('arr_tm'+arr_tm.value);
				return flag;
			}
			if(depart_plc.value!='321611')
			{
				flag=1;
			//	alert('depart_plc');
				return flag;
			}
			if( depart_dt.value.length!=0)
			{
				flag=1;
		//	alert('depart_dt');
				return flag;
			}
			if( depart_tm.value.length!=0)
			{
				flag=1;
		//	alert('depart_tm');
				return flag;
			}
			if(con_mod.value!='321611')
			{
				flag=1;
		//	alert('con_mod');
				return flag;
			}
			if(sub_train.value!='321611')
			{
				flag=1;
		//	alert('subtrain');
				return flag;
			}
			if(sub_class.value!='321611')
			{
				flag=1;
		//	alert('subclass');
				return flag;
			}
			if( sub_own.value!='321611')
			{
			
				flag=1;
		//	alert('subown');
				return flag;
			}
			if(sub_veh.value!='321611')
			{
					flag=1;
	//		alert('sub veh');
					return flag;
			}
			 			
			if(airmod.value!='321611')
			{
					flag=1;
	//			alert('air mod==='+airmod.value);
					return flag;
			}
	
			if(purpose.value!='321611')
			{
					flag=1;
	//		alert('purpose');
					return flag;
			}
			if(accom.value!='321611') 
			{
					flag=1;
					
	//				alert('accomodation'+accom.value.length	);
					return flag;
			}
			if(remark.value.length!=0)
			{
				flag=1;
	//			alert('Remark');
				return flag;
			}
			
			return flag;
		
		
	
	}
	function submitRequest()
	{
		
		var tb = document.getElementById('detailsTable');
		var len=tb.rows.length;
 		var row=(len*1-2)/2
 		var NullCnt=0;
 		for(var i=0;i<row;i++)
 		{
			var flag=checkforEmptyDetails(i);
			
			if(flag!=0)
			{
				NullCnt=NullCnt*1+1;
			}
 		}
 		if(NullCnt!=0)
 		{
	 		//	alert('Value is present ');
	 			if(row==2)
	 			{ 
	 				for(var i=0;i<row;i++)
	 				{
	 					var flag=checkvalue(i);
	 					if(flag==false)
	 					{
	 						return;
	 					}
	 					
	 				}
	 			}else
	 			{
	 			//	alert("For more than tow row");
	 				var flag=checkvalue(0);
	 				if(flag==false)
	 				{
	 					return;
	 				}
	 				for(var i=2;i<row;i++)
	 				{
	 					var flag=checkvalue(i);
		 				if(flag==false)
		 				{
		 					return;
		 				}
		 			}
		 			flag=checkvalue(1);
		 			if(flag==false)
		 			{
		 				return;
		 			}
		 			
		 		}
		 		var valpresent=document.getElementById('valpresent');
   	 	     	valpresent.value="true";
   	 	     	var draftReq=document.getElementById('draftReq');
   	 	     	draftReq.value="false";
				var urlstyle="hrms.htm?actionFlag=addTrip&reqtype=submit";
				if(test())
   	 	     	{
   	 	     		finalSubmit(urlstyle);
   	 	     	}else
   	 	     	{
   	 	     		alert("<fmt:message key="HRMS.neccessaryfields" bundle="${caption}" />");    
   	 	     	}
 		}
 		else
 		{
 		//	alert('All rows are empty !!!!!! '); 			
 			var tripdtls=document.getElementById("tripdtls");
 			//alert(tripdtls.rows.length);
 			if(tripdtls.rows.length>1)
 			{
   	 	     	var valpresent=document.getElementById('valpresent');
   	 	     	valpresent.value="false";
   	 	     	var draftReq=document.getElementById('draftReq');
   	 	     	draftReq.value="false";
 				var urlstyle="hrms.htm?actionFlag=addTrip&reqtype=submit";
				if(test())
   	 	     	{
   	 	     		finalSubmit(urlstyle);
   	 	     	}else
   	 	     	{
   	 	     		alert("<fmt:message key="HRMS.neccessaryfields" bundle="${caption}" />");    
   	 	     		return;
   	 	     	}
 			}else
 			{
 				alert("<fmt:message key="HRMS.onetouralongwithrequest" bundle="${caption}" />");    
 				return;
 			}
 		}
 		
 		
	}
	function finalSubmit(urlstyle)
	{
		var tb = document.getElementById('detailsTable');
		var len=tb.rows.length;
 		var row=(len*1-2)/2
		var hid=document.getElementById('journeyCnt');
		hid.value=row;
	
		var arr_plc0=document.getElementById("arr_plc0");
 		var arr_plc1=document.getElementById("arr_plc1");
 		var arr_dt0=document.getElementById("arr_dt0");
 		var name=arr_plc0.value+"To"+arr_plc1.value+"/"+arr_dt0.value;
 		
 		var tourName=document.getElementById("tourName");
		
 		var ParentReqId=document.getElementById('ParentReqId');		 	
 		//alert("ParentReqId"+ParentReqId.value);
 		return;
 		 var agree=confirm("Do you want to Submit Details?");
     	 if (agree)
     	 {
 			//	generateDtlsArray();
			document.frm1.action=urlstyle;
			document.frm1.submit();
		}		
	}
	function setAirmodeval(form)
	{
		if(form.title=="Business")
		{
			form.value=air_modCmb[0];
			//alert(form.value);
		}
		else
		{
			form.value=air_modCmb[1];
			//alert(form.value);
		}
		
	}
	function closewindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage"
		document.frm1.action=urlstyle;
		document.frm1.submit();
	}
	
	function window_open(val,x,y)
	{
	
		//parameter val contains the name of the textbox
		var newWindow;
		var urlstring = 'common/calendar.jsp?requestSent='+val;
		var urlstyle = 'height=200,width=280,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+x+',left='+y;
		newWindow = window.open(urlstring,'Calendar',urlstyle);

	}
	function donotchange(form)
	{
		var arr_plc0=document.getElementById("arr_plc0");
		form.value=arr_plc0.value;
	}
	function departureTimeCheck(form)
	{
		
		var month;
		var year;
		var day;
		
		var dmonth;
		var dyear;
		var dday; 
		
		var hour;
		var min;
		
		idx=form.name.charAt(form.name.length-1);
		 
		var arr_nm="arr_tm"+idx;
		var depart_tm_nm="depart_tm"+idx;
		var arr_tm=document.getElementById(arr_nm);
		var depart_time=document.getElementById(depart_tm_nm);
		var depart_tm=depart_time.value;
		var arrival_time=arr_tm.value;
		
		if(arrival_time.length==0)
		{
			alert("<fmt:message key="HRMS.arrivaltime" bundle="${caption}" />");    
			form.value="";
			return;
		}
	
		
		var arr_dt_nm="arr_dt"+idx;
		var depart_dt_nm="depart_dt"+idx;
		var arr_dt=document.getElementById(arr_dt_nm);
		var depart_dt=document.getElementById(depart_dt_nm);
		var departure_date=depart_dt.value;
 		var arrival_date=arr_dt.value;
 		if(departure_date=="")
 		{
 			alert("<fmt:message key="HRMS.selectdeparturedate" bundle="${caption}" />");    
 			depart_dt.focus();
 			return;
 		}
		var arr_yr=arrival_date.substring(6,10);
	 	var depart_yr=departure_date.substring(6,10);
	 		  		
		var arr_mon=arrival_date.substring(3,5);
		var depart_mon=departure_date.substring(3,5);
		
		var arr_day=arrival_date.substring(0,2);
		var depart_day=departure_date.substring(0,2);
		
		if(depart_mon.charAt(0)=='0' && depart_day.charAt(0)=='0')
		{
			 
			dmonth=parseInt(depart_mon.charAt(1));
			dday=parseInt(depart_day.charAt(1));
		}else if(depart_mon.charAt(0)=='0' && depart_day.charAt(0)!='0')
		{	
			dmonth=parseInt(depart_mon.charAt(1));
			dday=parseInt(depart_day);
		}else if (depart_mon.charAt(0)!='0' && depart_day.charAt(0)=='0')
		{
			 
			dmonth=parseInt(depart_mon);
			dday=parseInt(depart_day.charAt(1));
		}else
		{
			dmonth=parseInt(depart_mon);
			dday=parseInt(depart_day);
		}
		dyear=parseInt(depart_yr);
		
		
		if(arr_mon.charAt(0)=='0' && arr_day.charAt(0)=='0')
		{
			 
			month=parseInt(arr_mon.charAt(1));
			day=parseInt(arr_day.charAt(1));
		}else if(arr_mon.charAt(0)=='0' && arr_day.charAt(0)!='0')
		{	
			month=parseInt(arr_mon.charAt(1));
			day=parseInt(arr_day);
		}else if (arr_mon.charAt(0)!='0' && arr_day.charAt(0)=='0')
		{
			 
			month=parseInt(arr_mon);
			day=parseInt(arr_day.charAt(1));
		}else
		{
			month=parseInt(arr_mon);
			day=parseInt(arr_day);
		}
		year=parseInt(arr_yr);
		
		
		//Start checking of Time
		
	
		if(depart_tm.value=="")
		{
			alert("<fmt:message key="HRMS.inserttime" bundle="${caption}" />");    
		}else if(depart_tm.length==5 && depart_tm.charAt(2)!=':')
		{
			alert("<fmt:message key="HRMS.selecttime" bundle="${caption}" />");  
			form.value="";
			return;
		}else if(depart_tm.length==3)
		{
			if(isNaN(depart_tm.charAt(0))||isNaN(depart_tm.charAt(1))||isNaN(depart_tm.charAt(2)))
			{
				alert("<fmt:message key="HRMS.specialcharsnotallowed" bundle="${caption}" />");  
				form.value="";
				return;
			}else
			{
				hour=depart_tm.substring(0,1);
				min=depart_tm.substring(1,3);
				var imin=parseInt(min);
				if(imin>59)
				{
					alert("<fmt:message key="HRMS.minutenotgreater" bundle="${caption}" />");  
					form.value="";
					return;
					
				}
				var nhour="0"+hour;
				var newtime=nhour+":"+min;
				form.value=newtime
				
			}
			
		}else if(depart_tm.length==4)
		{
			 
				if(isNaN(depart_tm.charAt(0))||isNaN(depart_tm.charAt(1))||isNaN(depart_tm.charAt(2))||isNaN(depart_tm.charAt(3)))
				{
				 
					form.value="";
					return;
				}
				else
				{
					hour=depart_tm.substring(0,2);
					min=depart_tm.substring(2,4);
					var ihour=parseInt(hour);
					var imin=parseInt(min);
					 

					if(ihour>25)
					{
						alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${caption}" />");    
						form.value="";
						return;
					}else if(ihour==24)
					{
						if(imin!=0)
						{
							alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${caption}" />");    
							form.value="";
							return;
	
						}
					}
					if(imin>60)
					{
						alert("<fmt:message key="HRMS.minutenotgreater" bundle="${caption}" />");    
						form.value="";
						return;
					
					}
					var newtime=hour+":"+min;
					form.value=newtime
				
				}
		}else if(depart_tm.length==5 && depart_tm.charAt(2)==':')
		{
					if(isNaN(depart_tm.charAt(0))||isNaN(depart_tm.charAt(1))||isNaN(depart_tm.charAt(3))||isNaN(depart_tm.charAt(4)))
					{
						alert("<fmt:message key="HRMS.selecttime" bundle="${caption}" />");    
						form.value="";
						return;
					}
					hour=depart_tm.substring(0,2);
					min=depart_tm.substring(3,5);
					
					
					var ihour=parseInt(hour);
					var imin=parseInt(min);
					if(ihour>25)
					{
						alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${caption}" />");    
						form.value="";
						return;
					}else if(ihour==24)
					{
						if(imin!=0)
						{
							alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${caption}" />");    
							form.value="";
							return;
	
						}
					}
					if(imin>60)
					{
						alert("<fmt:message key="HRMS.minutenotgreater" bundle="${caption}" />");    
						form.value="";
						return;
					
					}
		}else
		{
			alert("<fmt:message key="HRMS.selecttime" bundle="${caption}" />");    
			form.value="";
			return;
		}
		//End
		if(month==dmonth&&year==dyear&&day==dday)
		{
			
			var arr_hour=arrival_time.substring(0,2);
			var arr_min=arrival_time.substring(3,5);
			
			var idepart_hour=parseInt(hour);
			var idepart_min=parseInt(min);
			var iarr_hour=parseInt(arr_hour);
			var iarr_min=parseInt(arr_min);
			
			
			
			if(idepart_hour<iarr_hour)
			{
				alert("<fmt:message key="HRMS.departutetimegtrrival" bundle="${caption}" />");    
				form.value="";
				return;
				 
			}
			else if(idepart_hour==iarr_hour)
			{

				if(iarr_min>=idepart_min)
				{
					alert("<fmt:message key="HRMS.departutetimegtrrival" bundle="${caption}" />");    
					form.value="";
					return;
				}
			}						
		}
		
	}
	function arrivalTimeCheck(form)
	{
		
		var arr_time =form.value;
		idx=form.name.charAt(form.name.length-1);
		var arr_dt_nm="arr_dt"+idx;
		var arr_dt=document.getElementById(arr_dt_nm);
		
		if(arr_dt.value=="")
		{
			alert("<fmt:message key="HRMS.selectarrivaldate" bundle="${caption}" />");    
			form.value="";
			arr_dt.focus();
			return;
		}
		if(arr_time.value=="")
		{
			alert("<fmt:message key="HRMS.inserttime" bundle="${caption}" />");    
		}else if(arr_time.length==5 && arr_time.charAt(2)!=':')
		{
			alert("<fmt:message key="HRMS.selecttime" bundle="${caption}" />");    
			form.value="";
			return;
		}else if(arr_time.length==3)
		{
			if(isNaN(arr_time.charAt(0))||isNaN(arr_time.charAt(1))||isNaN(arr_time.charAt(2)))
			{
				alert("<fmt:message key="HRMS.specialcharsnotallowed" bundle="${caption}" />");    
				form.value="";
				return;
			}else
			{
				hour=arr_time.substring(0,1);
				min=arr_time.substring(1,3);
				var imin=parseInt(min);
				if(imin>59)
				{
					alert("<fmt:message key="HRMS.minutenotgreater" bundle="${caption}" />");    
					form.value="";
					return;
					
				}
				var nhour="0"+hour;
				var newtime=nhour+":"+min;
				form.value=newtime
				return;
			}
			
		}else if(arr_time.length==4)
		{
				if(isNaN(arr_time.charAt(0))||isNaN(arr_time.charAt(1))||isNaN(arr_time.charAt(2))||isNaN(arr_time.charAt(3)))
				{
					alert("<fmt:message key="HRMS.specialcharsnotallowed" bundle="${caption}" />");    
					form.value="";
					return;
				}
				else
				{
					hour=arr_time.substring(0,2);
					min=arr_time.substring(2,4);
					var ihour=parseInt(hour);
					var imin=parseInt(min);
					if(ihour>25)
					{
						alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${caption}" />");    
						form.value="";
						return;
					}else if(ihour==24)
					{
						if(imin!=0)
						{
							alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${caption}" />");    
							form.value="";
							return;
	
						}
					}
					if(imin>60)
					{
						alert("<fmt:message key="HRMS.minutenotgreater" bundle="${caption}" />");    
						form.value="";
						return;
					
					}
					var newtime=hour+":"+min;
					form.value=newtime
					return;
				}
		}else if(arr_time.length==5 && arr_time.charAt(2)==':')
		{
					if(isNaN(arr_time.charAt(0))||isNaN(arr_time.charAt(1))||isNaN(arr_time.charAt(3))||isNaN(arr_time.charAt(4)))
					{
						alert("<fmt:message key="HRMS.selecttime" bundle="${caption}" />");    
						form.value="";
						return;
					}
					hour=arr_time.substring(0,2);
					min=arr_time.substring(3,5);
					
					
					var ihour=parseInt(hour);
					var imin=parseInt(min);
					if(ihour>25)
					{
						alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${caption}" />");    
						form.value="";
						return;
					}else if(ihour==24)
					{
						if(imin!=0)
						{
							alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${caption}" />");    
							form.value="";
							return;
	
						}
					}
					if(imin>60)
					{
						alert("<fmt:message key="HRMS.minutenotgreater" bundle="${caption}" />");    
						form.value="";
						return;
					
					}
		}else
		{
			alert("<fmt:message key="HRMS.selecttime" bundle="${caption}" />");    
			form.value="";
			return;
		}
		 
	}
	function enabletype(form)
	{
		var idx=form.name.charAt(form.name.length-1);
		var sub_veh_nm="sub_veh"+idx;
		if(form.selectedIndex==4 ||form.selectedIndex==5)
		{
			var sub_veh=document.getElementById(sub_veh_nm);
			sub_veh.disabled="";
		}
		else
		{
			var sub_veh=document.getElementById(sub_veh_nm);
			sub_veh.disabled="true";
		}
			
	}
	function departurechkdate(form)
	{

		var amonth;
		var ayear;
		var aday;
		
		var dmonth;
		var dyear;
		var dday; 
		
		var arrival_date=form.value;
		var departure_date;
		var tb = document.getElementById('detailsTable');
		var len=tb.rows.length;

 		var row=(len*1-2)/2
 		
		idx=form.name.charAt(form.name.length-1);
		
		
		if(idx==2)
		{

			departure_date=document.getElementById('depart_dt0');
		}else if(idx==1)
		{
	

			if(row==2)
			{
				departure_date=document.getElementById('depart_dt0');
			}
			else
			{
				var app=row*1-1;
				departure_date_nm="depart_dt"+app;
				departure_date=document.getElementById(departure_date_nm);
			}
		}
		else
		{
			var app=idx*1-1;
			departure_date_nm="depart_dt"+app;
			departure_date=document.getElementById(departure_date_nm);
		}
		if(departure_date.value=="")
		{
			alert("<fmt:message key="HRMS.selctprevdepartureplace" bundle="${caption}" />");    
			form.value="";
			return;
		}
		
		var departure_dt=departure_date.value;
		var aday=arrival_date.substring(0,2);
		var amon=arrival_date.substring(3,5);
		var ayear=arrival_date.substring(6,10);
		
		var dday=departure_dt.substring(0,2);
		var dmon=departure_dt.substring(3,5);
		var dyear=departure_dt.substring(6,10);
		
		 var idmonth;
		 var idday;
		 var idyear;
		 
		 var iamonth;
		 var iaday;
		 var iayear;
//To eliminate zero form the date
		if(dmon.charAt(0)=='0' && dday.charAt(0)=='0')
		{
			 
			idmonth=parseInt(dmon.charAt(1));
			idday=parseInt(dday.charAt(1));
		}else if(dmon.charAt(0)=='0' && dday.charAt(0)!='0')
		{	
			idmonth=parseInt(dmon.charAt(1));
			idday=parseInt(dday);
		}else if (dmon.charAt(0)!='0' && dday.charAt(0)=='0')
		{
			 
			idmonth=parseInt(dmon);
			idday=parseInt(dday.charAt(1));
		}else
		{
			idmonth=parseInt(dmon);
			idday=parseInt(dday);
		}
		idyear=parseInt(dyear);
		
		if(amon.charAt(0)=='0' && aday.charAt(0)=='0')
		{
			 
			iamonth=parseInt(amon.charAt(1));
			iaday=parseInt(aday.charAt(1));
		}else if(amon.charAt(0)=='0' && aday.charAt(0)!='0')
		{	
			iamonth=parseInt(amon.charAt(1));
			iaday=parseInt(aday);
		}else if (amon.charAt(0)!='0' && aday.charAt(0)=='0')
		{
			 
			iamonth=parseInt(amon);
			iaday=parseInt(aday.charAt(1));
		}else
		{
			iamonth=parseInt(amon);
			iaday=parseInt(aday);
		}
		iayear=parseInt(ayear);
		
		 
//End 	
		if(idyear<iayear)
		{
			return;		
		}else if(idyear==iayear)
		{
			if(idmonth<iamonth)
			{
				return;
			}else if(idmonth==iamonth)
			{
				if(idday<=iaday)
				{
					return;		
				}
				else{
					alert("<fmt:message key="HRMS.arivaldategtdeparturedate" bundle="${caption}" />");    
					form.value="";
					return;		
				}	
			}else
			{
				alert("<fmt:message key="HRMS.arivaldategtdeparturedate" bundle="${caption}" />");    
				form.value="";
				return;		
			}
				
		}else
		{
			alert("<fmt:message key="HRMS.arivaldategtdeparturedate" bundle="${caption}" />");    
			form.value="";
			return;		
		}
		
			
		
		
	/*	if(idday>iaday||idmonth>iamonth||idyear>iayear)
		{
		    alert("Arrival Date should not be greater than Departure Date");
			form.value="";
			return;
		} */
		  
	}
	function checkdate(form)
	{
		var month;
		var year;
		var day;
		
		var dmonth;
		var dyear;
		var dday; 
		
		idx=form.name.charAt(form.name.length-1);
		var arr_dt_nm="arr_dt"+idx;
		var arr_dt=document.getElementById(arr_dt_nm);
		var departure_date=form.value;
 		var arrival_date=arr_dt.value;
		var arr_yr=arrival_date.substring(6,10);
	 	var depart_yr=departure_date.substring(6,10);
	 		  		
		var arr_mon=arrival_date.substring(3,5);
		var depart_mon=departure_date.substring(3,5);
		
		var arr_day=arrival_date.substring(0,2);
		var depart_day=departure_date.substring(0,2);
		
		if(arr_yr==""&&arr_mon==""&&arr_day=="")
		{
			alert("<fmt:message key="HRMS.selectarrivaldate" bundle="${caption}" />");    
			form.value="";
			return;
		}
		
		if(depart_mon.charAt(0)=='0' && depart_day.charAt(0)=='0')
		{
			 
			dmonth=parseInt(depart_mon.charAt(1));
			dday=parseInt(depart_day.charAt(1));
		}else if(depart_mon.charAt(0)=='0' && depart_day.charAt(0)!='0')
		{	
			dmonth=parseInt(depart_mon.charAt(1));
			dday=parseInt(depart_day);
		}else if (depart_mon.charAt(0)!='0' && depart_day.charAt(0)=='0')
		{
			 
			dmonth=parseInt(depart_mon);
			dday=parseInt(depart_day.charAt(1));
		}else
		{
			dmonth=parseInt(depart_mon);
			dday=parseInt(depart_day);
		}
		dyear=parseInt(depart_yr);
		
		
		if(arr_mon.charAt(0)=='0' && arr_day.charAt(0)=='0')
		{
			 
			month=parseInt(arr_mon.charAt(1));
			day=parseInt(arr_day.charAt(1));
		}else if(arr_mon.charAt(0)=='0' && arr_day.charAt(0)!='0')
		{	
			month=parseInt(arr_mon.charAt(1));
			day=parseInt(arr_day);
		}else if (arr_mon.charAt(0)!='0' && arr_day.charAt(0)=='0')
		{
			 
			month=parseInt(arr_mon);
			day=parseInt(arr_day.charAt(1));
		}else
		{
			month=parseInt(arr_mon);
			day=parseInt(arr_day);
		}
		year=parseInt(arr_yr);
		
/*		if(month>dmonth||year>dyear||day>dday)
		{
			alert("Departure Date Should be greater than Arrival Date");
			form.value="";
			return;
		} */
		
		if(year<dyear)
		{
			return;		
		}else if(year==dyear)
		{
			if(month<dmonth)
			{
				return;
			}else if(month==dmonth)
			{
				if(day<=dday)
				{
					return;		
				}
				else{
					alert("<fmt:message key="HRMS.arivaldategtdeparturedate" bundle="${caption}" />");    
					form.value="";
					return;		
				}	
			}else
			{
				alert("<fmt:message key="HRMS.arivaldategtdeparturedate" bundle="${caption}" />");    
				form.value="";
				return;		
			}
				
		}else
		{
			alert("<fmt:message key="HRMS.arivaldategtdeparturedate" bundle="${caption}" />");    
			form.value="";
			return;		
		}
		
		
		
		
		
		
		var depart_time_nm="depart_tm"+idx;
		var depart_time=document.getElementById(depart_time_nm);
	//	depart_time.focus();
	
	}
	
	function confirmDelete(form)
	{	  
          var agree=confirm("Are you sure you wish to delete this entry?");
     	  if (agree)
     	  {
     	  		
     	  		var idx=form.id.charAt(form.id.length-1);
     	  		var row=parseInt(idx);
     	  		var tb=document.getElementById('tripdtls');
     	  		var oldlen=tb.rows.length;
     	  		tb.deleteRow(row);
     	  		var len=tb.rows.length;
     	  		if(len==1)
     	  		{
     	  			tb.style.display="none";
     	  		}
     	  		idx=idx*1+1;
     	  		
     	  		for(var i=idx;i<=len;i++)
     	  		{
					var old_lab="lab"+i;     	  		
     	  			var old_id="row"+i;

     	  			var app=i*1-1;
     	  			var new_id="row"+app;
     	  			var rowid=document.getElementById(old_id);
     	  			var lab=document.getElementById(old_lab);  
     	  			rowid.id=new_id;
     	  			lab.id="lab"+app;
     	  			lab.innerHTML=app;

     	  			 
     	  		}
     	  		
           }
 		  else
    		    return false;
	} 
	

	function deleteallrows()
	{
		
		var tb=document.getElementById('detailsTable');
		var len=tb.rows.length;
		var row=(len-2)/2;
		
		for(var i=2;i<row;i++)
		{
			var index=2*2;
			tb.deleteRow(index);
			tb.deleteRow(index);
			
		}
		var xLeaveAttachment=document.getElementById('myTableTravelAttachment');
		var tmp=xLeaveAttachment.rows.length;
		for(var i=1;i<tmp;i++)
		{
	//		removeRowFromTableTravelAttachment(i);
				
		}  
		var deleterow=document.getElementById('deleterow');
		deleterow.style.display="none";
		
	}
	function validdate()
	{
		var arr_dt0=document.getElementById('arr_dt0');
		var arr_tm0=document.getElementById('arr_tm0');
		var depart_dt1=document.getElementById('depart_dt1');
		var depart_tm1=document.getElementById('depart_tm1');
		if(arr_dt0.value==0)
		{
			alert("<fmt:message key="HRMS.selectarrivaldate" bundle="${caption}" />");    
			
			return false;
		}else if(arr_tm0.value==0)
		{
			alert("<fmt:message key="HRMS.arrivaltime" bundle="${caption}" />");    
			return false;
		}else if(depart_dt1.value==0)
		{
			alert("<fmt:message key="HRMS.selectdeparturedate" bundle="${caption}" />");    
			return false;
		
		}else if(depart_tm1.value==0)
		{
			alert("<fmt:message key="HRMS.departuretime" bundle="${caption}" />");    
			return false;
		}
		
		return true;
	}
	function resettripdtls()
	{

		var tb = document.getElementById('detailsTable');
		var len = tb.rows.length;
		var row=(len*1-2)/2;
		var trippurpose=document.getElementById("purpose");
		trippurpose.value="";
		var ref_file_no=document.getElementById("ref_file_no");
		ref_file_no.value="";
		for(var i=0;i<row;i++)
		{
		
				//Hide column
				var own_nm="own"+i;
				var bus_nm="bus"+i;
				var eco_nm="eco"+i;
				var veh_nm="veh"+i;
				var trn_nm="trn"+i;
				var class_nm="class"+i;
				var trncol_nm="trncol"+i;
				var clscol_nm="clscol"+i;
				var fir_nm="fir_dm"+i;
				var sec_nm="sec_dm"+i;
				var dislab_nm="dislab"+i;
				var discol_nm="discol"+i;
				var airmod_nm="airmod"+i;
				
				var own=document.getElementById(own_nm);
				var bus=document.getElementById(bus_nm);
				var eco=document.getElementById(eco_nm);
				var veh=document.getElementById(veh_nm);
				var trn=document.getElementById(trn_nm);
				var cls = document.getElementById(class_nm);
				var trncol=document.getElementById(trncol_nm);
				var clscol=document.getElementById(clscol_nm);
				var fir=document.getElementById(fir_nm);
				var sec=document.getElementById(sec_nm);
				var dislab=document.getElementById(dislab_nm);
				var discol=document.getElementById(discol_nm);
				var airmod=document.getElementsByName(airmod_nm);
				var airmodValue=document.getElementById(airmod_nm);
				
				own.style.display="none";
				bus.style.display="none";
				eco.style.display="none";
				veh.style.display="none";
					
				trn.style.display="none";
				cls.style.display="none";
				trncol.style.display="";
				clscol.style.display="";
				fir.style.display="";
				sec.style.display="";
				discol.style.display="none";
				dislab.style.display="";
							
				var arr_plc_nm="arr_plc"+i;
				var arr_dt_nm="arr_dt"+i;
				var arr_tm_nm="arr_tm"+i;
				
				var depart_plc_nm="depart_plc"+i;
				var depart_dt_nm="depart_dt"+i;
				var depart_tm_nm="depart_tm"+i;
				
				var con_mod_nm="con_mod"+i;
				
				var sub_train_nm="sub_train"+i;
				var sub_class_nm="sub_class"+i;
				var sub_own_nm="sub_own"+i;
				var sub_veh_nm="sub_veh"+i;
				
				var purpose_nm="purpose"+i;
				
				var accom_nm="accom"+i;
				var cost_nm="cost"+i;
				var remark_nm="remark"+i;
				
				var arr_plc=document.getElementById(arr_plc_nm);
				var arr_dt=document.getElementById(arr_dt_nm);
				var arr_tm=document.getElementById(arr_tm_nm);
				
				var depart_plc=document.getElementById(depart_plc_nm);
				var depart_dt=document.getElementById(depart_dt_nm);
				var depart_tm=document.getElementById(depart_tm_nm);
			
				var con_mod=document.getElementById(con_mod_nm);
				
				var sub_train=document.getElementById(sub_train_nm);
				var sub_class=document.getElementById(sub_class_nm);
				
				var purpose=document.getElementById(purpose_nm);
				
				var accom=document.getElementById(accom_nm);
				var cost=document.getElementById(cost_nm);
				var remark=document.getElementById(remark_nm);
			
				var sub_own=document.getElementById(sub_own_nm);
				var sub_veh=document.getElementById(sub_veh_nm);
				
				sub_own.selectedIndex=0;
				sub_veh.selectedIndex=0;
				sub_own.style.display="none";
				sub_veh.style.display="none";
				sub_train.style.display="";
				sub_class.style.display="";
				
				arr_plc.selectedIndex=0;
				arr_tm.value="";	
				arr_dt.value="";
				
				
				
				depart_plc.selectedIndex=0;		
				depart_tm.value="";
				depart_dt.value="";
			
				con_mod.selectedIndex=0;
				
				sub_train.selectedIndex=0;
				sub_class.selectedIndex=0;
				
				purpose.selectedIndex=0;
				
				accom.selectedIndex=0;
				
				cost.value="0.00";
				
				remark.value="";
				
				airmodValue.value="321611";
				airmod[0].checked="";
				airmod[1].checked="";
				

		}
	}	
	
	function ckeckplace()
	{
	
		var arr_plc0=document.getElementById('arr_plc0');
		var arr_plc1=document.getElementById('arr_plc1');
		if(arr_plc0.value=='321611')
		{
			alert("<fmt:message key="HRMS_arrival_name_req" bundle="${alertLables}" />");
			//alert("Please select arrival name");
			return false;
		}else if(arr_plc1.value=='321611')
		{
			alert("<fmt:message key="HRMS_Depature_name_req" bundle="${alertLables}" />");
			//alert("Please select Departure name");
			return false;
		}
		
		return true;
		
		
	}
	function addtriprow()
	{
		
		var tb=document.getElementById('tripdtls');
		var trip_nm="";
		var arr_plc0=document.getElementById('arr_plc0');
		var arr_dt0=document.getElementById('arr_dt0');
		var arr_plc1=document.getElementById('arr_plc1');
		trip_nm=arr_plc0.value+"<font colore='BLUE'> To </font> "+arr_plc1.value+"<font colore='BLUE'> - <font>"+arr_dt0.value;
		
		var len = tb.rows.length;
		var dtlstb = document.getElementById('detailsTable');
		var dtlslen = dtlstb.rows.length;
		var dtlsrow=(dtlslen*1-2)/2;
		
		var trip=new Array();
		var journey=new Array();
		var attachment=new Array();
		var cnt=0;
		
//Store the  journey details as  hidden field
		for(var i=0;i<dtlsrow;i++)
		{
			var arr_plc_nm="arr_plc"+i;
			var arr_dt_nm="arr_dt"+i;
			var arr_tm_nm="arr_tm"+i;
			var depart_plc_nm="depart_plc"+i;
			var depart_dt_nm="depart_dt"+i;
			var depart_tm_nm="depart_tm"+i;
			var con_mod_nm="con_mod"+i;
			var dis_nm="dis"+i;
			var purpose_nm="purpose"+i;
			var accom_nm="accom"+i;
			var cost_nm="cost"+i;
			var remark_nm="remark"+i;
			var sub_train_nm="sub_train"+i;
			var sub_class_nm="sub_class"+i;
			var sub_own_nm="sub_own"+i;
			var sub_veh_nm="sub_veh"+i;
			var airmod_nm="airmod"+i;
			
			
			var arr_plc=document.getElementById(arr_plc_nm);
			var arr_dt=document.getElementById(arr_dt_nm);
			var arr_tm=document.getElementById(arr_tm_nm);
			var depart_plc=document.getElementById(depart_plc_nm);
			var depart_dt=document.getElementById(depart_dt_nm);
			var depart_tm=document.getElementById(depart_tm_nm);
			var con_mod=document.getElementById(con_mod_nm);
			var dis=document.getElementById(dis_nm);
			var purpose=document.getElementById(purpose_nm);
			var accom=document.getElementById(accom_nm);
			var cost=document.getElementById(cost_nm);
			var remark=document.getElementById(remark_nm);
			var sub_train=document.getElementById(sub_train_nm);
			var sub_class=document.getElementById(sub_class_nm);
			var sub_own=document.getElementById(sub_own_nm);
			var sub_veh=document.getElementById(sub_veh_nm);
			var airmod=document.getElementById(airmod_nm);
			var trippurpose=document.getElementById("purpose");
			
			journey[cnt]=arr_plc.value;
			cnt=cnt*1+1;
			journey[cnt]=arr_dt.value;
			cnt=cnt*1+1;
			journey[cnt]=arr_tm.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_plc.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_dt.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_tm.value;
			cnt=cnt*1+1;
			journey[cnt]=con_mod.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_train.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_class.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_own.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_veh.value;									
			cnt=cnt*1+1;
			journey[cnt]=airmod.value;									
			cnt=cnt*1+1;
			journey[cnt]=dis.value;
			cnt=cnt*1+1;
			journey[cnt]=purpose.value;
			cnt=cnt*1+1;
			journey[cnt]=accom.value;
			cnt=cnt*1+1;
			journey[cnt]=cost.value;
			cnt=cnt*1+1;
			journey[cnt]=remark.value;
			cnt=cnt*1+1;
			journey[cnt]=trippurpose.value;
			cnt=cnt*1+1;
			journey[cnt]='$';
			cnt=cnt*1+1;
			
			
		}
	//	alert(journey);
//Store Attachment Details.
		var atrow=0;
		var xLeaveAttachment=document.getElementById('myTableTravelAttachment');
		for(var i=1;i<xLeaveAttachment.rows.length;i++)
		{
	//		alert(i);
			var row=xLeaveAttachment.rows(i);
			
			for(var j=0;j<row.cells.length;j++)
			{
				var col=row.cells[j];
//				alert(col.innerHTML);
				attachment[atrow]=col.innerHTML;
//				alert(attachment[atrow]);
				++atrow;
			}
			
		}
	//	alert("Attachment!!!!!!!!!!!!!!!!!!!!!!!"+attachment);
	

	

//Insert new Row to Trip table	
		var row=tb.insertRow(len);
		var str='';
		var cell1 = row.insertCell(0);
		cell1.innerHTML="<label id='lab"+len+"'>"+len+"</label>";

		var cell2 = row.insertCell(1);
		str="<center><label id='tripnm"+len+"'>"+trip_nm+"</label></center>";
		str+="<input type='hidden' value='"+journey+"'  id='trip"+len+"' name='trip"+len+"' >";
		str+="<input type='hidden' value='"+attachment+"'  id='attach"+len+"' name='attach"+len+"' >";
		cell2.innerHTML=str;


		var cell3 = row.insertCell(2);
		cell3.innerHTML="<center><a href='#' id='edittrip"+len+"' onclick='edittrip(this)'>Edit</a>/<a href='#' id='row"+len+"' onclick='confirmDelete(this)'>Remove</a></center>";
		
		
//		
		
		
	}
	function updateDetails()
	{
	//	alert("Upadate mehtod is called");
//		alert("tourRowIdArray:::::::"+tourRowIdArray);
		if(!generateDtlsArray())
		{
			return;
			
		}
   	 	var totArray=new Array(3);
   	 	totArray[0]='journeyCnt';
   	 	totArray[1]='journeyDtls';
   	 	totArray[2]='tourDtls';		
	//	addtrip('updateRecord');	
		var savetrip=document.getElementById('savetrip');
		savetrip.style.display="none";		
		var tourDtls=document.getElementById('tourDtls');
	//	alert(tourDtls.value);
		 addOrUpdateRecord('updateRecord','addTrip&reqtype=ajax&valpresent=true',totArray);	
		
	}
	
/*	function savedtls(form)
	{
		
		
		var trip_nm="";
		var arr_plc0=document.getElementById('arr_plc0');
		var arr_dt0=document.getElementById('arr_dt0');
		var arr_plc1=document.getElementById('arr_plc1');
		if(arr_plc1.value==0)
		{
			alert("Please Select Arrival Place");
			return;
		}
		
		trip_nm=arr_plc0.value+"<font colore='BLUE'> To </font> "+arr_plc1.value+"<font colore='BLUE'> - <font>"+arr_dt0.value;
//Saving Attachment value		
		var atrow=0;
		var attachment=new Array();
		var xLeaveAttachment=document.getElementById('myTableTravelAttachment');
		
		
		for(var i=1;i<xLeaveAttachment.rows.length;i++)
		{
	//		alert(i);
			var row=xLeaveAttachment.rows(i);
			
			for(var j=0;j<row.cells.length;j++)
			{
				var col=row.cells[j];
				attachment[atrow]=col.innerHTML;
				++atrow;
			}
			
		}
		
		
		
		
//Saving modifying Details
		var dtlstb = document.getElementById('detailsTable');
		var dtlslen = dtlstb.rows.length;
		var dtlsrow=(dtlslen*1-2)/2;
		
		var trip=new Array();
		var journey=new Array();
		var cnt=0;
		
		//Store the  journey details as  hidden field
		for(var i=0;i<dtlsrow;i++)
		{
			var arr_plc_nm="arr_plc"+i;
			var arr_dt_nm="arr_dt"+i;
			var arr_tm_nm="arr_tm"+i;
			var depart_plc_nm="depart_plc"+i;
			var depart_dt_nm="depart_dt"+i;
			var depart_tm_nm="depart_tm"+i;
			var con_mod_nm="con_mod"+i;
			var dis_nm="dis"+i;
			var purpose_nm="purpose"+i;
			var accom_nm="accom"+i;
			var cost_nm="cost"+i;
			var remark_nm="remark"+i;
			var sub_train_nm="sub_train"+i;
			var sub_class_nm="sub_class"+i;
			var sub_own_nm="sub_own"+i;
			var sub_veh_nm="sub_veh"+i;
			var airmod_nm="airmod"+i;
			
			
			var arr_plc=document.getElementById(arr_plc_nm);
			var arr_dt=document.getElementById(arr_dt_nm);
			var arr_tm=document.getElementById(arr_tm_nm);
			var depart_plc=document.getElementById(depart_plc_nm);
			var depart_dt=document.getElementById(depart_dt_nm);
			var depart_tm=document.getElementById(depart_tm_nm);
			var con_mod=document.getElementById(con_mod_nm);
			var dis=document.getElementById(dis_nm);
			var purpose=document.getElementById(purpose_nm);
			var accom=document.getElementById(accom_nm);
			var cost=document.getElementById(cost_nm);
			var remark=document.getElementById(remark_nm);
			var sub_train=document.getElementById(sub_train_nm);
			var sub_class=document.getElementById(sub_class_nm);
			var sub_own=document.getElementById(sub_own_nm);
			var sub_veh=document.getElementById(sub_veh_nm);
			var airmod=document.getElementById(airmod_nm);
			var trippurpose=document.getElementById("purpose");
			
			journey[cnt]=arr_plc.value;
			cnt=cnt*1+1;
			journey[cnt]=arr_dt.value;
			cnt=cnt*1+1;
			journey[cnt]=arr_tm.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_plc.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_dt.value;
			cnt=cnt*1+1;
			journey[cnt]=depart_tm.value;
			cnt=cnt*1+1;
			journey[cnt]=con_mod.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_train.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_class.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_own.value;
			cnt=cnt*1+1;
			journey[cnt]=sub_veh.value;									
			cnt=cnt*1+1;
			journey[cnt]=airmod.value;									
			cnt=cnt*1+1;
			journey[cnt]=dis.value;
			cnt=cnt*1+1;
			journey[cnt]=purpose.value;
			cnt=cnt*1+1;
			journey[cnt]=accom.value;
			cnt=cnt*1+1;
			journey[cnt]=cost.value;
			cnt=cnt*1+1;
			journey[cnt]=remark.value;
			cnt=cnt*1+1;
			journey[cnt]=trippurpose.value;
			cnt=cnt*1+1;
			journey[cnt]='$';
			cnt=cnt*1+1;
			
			
		}
//End	    
 	  	var idx=form.id.charAt(form.id.length-1);	
//Save new value of attachment 	  	
 	  	var attach_nm="attach"+idx;
 	  	var attach=document.getElementById(attach_nm);
  	 	attach.value=attachment;
//Change Trip Name 	  	
 	  	var name="tripnm"+idx;
 	  	var tripname=document.getElementById(name);
 	  	tripname.innerHTML=trip_nm;
 	  	var trip_nm="trip"+idx;
 	  	var trip=document.getElementById(trip_nm);
 	  	trip.value=journey;
		form.id="savetrip";
		form.style.display="none";
		var addtripbt=document.getElementById("addtripbt");
		addtripbt.disabled="";
		resettripdtls();
		deleteallrows();
		
	} */
	function edittrip(form)
	{
		
		var idx=form.id.charAt(form.id.length-1);
		var addtripbt=document.getElementById("addtripbt");
		addtripbt.disabled="true";
		
		var bt=document.getElementById("savetrip");
		bt.style.display="";
		bt.id="savetrip"+idx;	
		var trip_nm="trip"+idx;
		var trip=document.getElementById(trip_nm);
		var result=new Array();
		result=trip.value;
		
//Getting value of Attachment;
		var attach_nm="attach"+idx;		
		var attach=document.getElementById(attach_nm);		
		var attachmentstr=attach.value;
		var attachment=attachmentstr.split(",");
		
		var xLeaveAttachment=document.getElementById('myTableTravelAttachment');
		var tmp=xLeaveAttachment.rows.length;
		for(var i=1;i<tmp;i++)
		{
			xLeaveAttachment.deleteRow(1);
			
		}
		var atrow=0;
		var stop=parseInt(attachment.length/3);
		
		for(var i=0;i<stop;i++)
		{
			
			var xLeaveAttachment=document.getElementById('myTableTravelAttachment').insertRow();

			var col1LeaveAttachment=xLeaveAttachment.insertCell(0)
		    var col2LeaveAttachment=xLeaveAttachment.insertCell(1)
	    	var col3LeaveAttachment=xLeaveAttachment.insertCell(2)
	    	
	    	col1LeaveAttachment.innerHTML=attachment[atrow++];
	    	col2LeaveAttachment.innerHTML=attachment[atrow++];
	    	col3LeaveAttachment.innerHTML=attachment[atrow++];    	    	
				
			
		}
	
//		var xLeaveAttachment=document.getElementById('myTableTravelAttachment').insertRow();

//		var col1LeaveAttachment=xLeaveAttachment.insertCell(0)
//	    var col2LeaveAttachment=xLeaveAttachment.insertCell(1)
 //   	var col3LeaveAttachment=xLeaveAttachment.insertCell(2)
    	
 //   	col1LeaveAttachment.innerHTML=attachment[0];
 //   	col2LeaveAttachment.innerHTML=attachment[1];
 //   	col3LeaveAttachment.innerHTML=attachment[2];    	    	
		
//		
		var arrresult=result.split(",");
	//	alert(arrresult);
		var end=arrresult.length/19;
		for(var j=2;j<end;j++)
		{
			addrow();
		}
		var cnt=0;
		for(var i=0;i<end;i++)
		{
			var arr_plc_nm="arr_plc"+i;
			var arr_dt_nm="arr_dt"+i;
			var arr_tm_nm="arr_tm"+i;
			var depart_plc_nm="depart_plc"+i;
			var depart_dt_nm="depart_dt"+i;
			var depart_tm_nm="depart_tm"+i;
			var con_mod_nm="con_mod"+i;
			var dis_nm="dis"+i;
			var purpose_nm="purpose"+i;
			var accom_nm="accom"+i;
			var cost_nm="cost"+i;
			var remark_nm="remark"+i;
			var sub_train_nm="sub_train"+i;
			var sub_class_nm="sub_class"+i;
			var sub_own_nm="sub_own"+i;
			var sub_veh_nm="sub_veh"+i;
			var airmod_nm="airmod"+i;
			
			
			var arr_plc=document.getElementById(arr_plc_nm);
			var arr_dt=document.getElementById(arr_dt_nm);
			var arr_tm=document.getElementById(arr_tm_nm);
			var depart_plc=document.getElementById(depart_plc_nm);
			var depart_dt=document.getElementById(depart_dt_nm);
			var depart_tm=document.getElementById(depart_tm_nm);
			var con_mod=document.getElementById(con_mod_nm);
			var dis=document.getElementById(dis_nm);
			var purpose=document.getElementById(purpose_nm);
			var accom=document.getElementById(accom_nm);
			var cost=document.getElementById(cost_nm);
			var remark=document.getElementById(remark_nm);
			var sub_train=document.getElementById(sub_train_nm);
			var sub_class=document.getElementById(sub_class_nm);
			var sub_own=document.getElementById(sub_own_nm);
			var sub_veh=document.getElementById(sub_veh_nm);
			var airmod=document.getElementById(airmod_nm);
			var trippurpose=document.getElementById("purpose");
			
		
		var own_nm="own"+i;
		var bus_nm="bus"+i;
		var eco_nm="eco"+i;
		var veh_nm="veh"+i;
		var trn_nm="trn"+i;
		var class_nm="class"+i;
		var trncol_nm="trncol"+i;
		var clscol_nm="clscol"+i;
		var fir_nm="fir_dm"+i;
		var sec_nm="sec_dm"+i;
		var dislab_nm="dislab"+i;
		var discol_nm="discol"+i;
		
		
		var own=document.getElementById(own_nm);
		var bus=document.getElementById(bus_nm);
		var eco=document.getElementById(eco_nm);
		var veh=document.getElementById(veh_nm);
		var trn=document.getElementById(trn_nm);
		var cls = document.getElementById(class_nm);
		var trncol=document.getElementById(trncol_nm);
		var clscol=document.getElementById(clscol_nm);
		var fir=document.getElementById(fir_nm);
		var sec=document.getElementById(sec_nm);
		var dislab=document.getElementById(dislab_nm);
		var discol=document.getElementById(discol_nm);
		
		
			
			arr_plc.value=arrresult[cnt];
			cnt=cnt*1+1;
			arr_dt.value=arrresult[cnt];
			cnt=cnt*1+1;
			arr_tm.value=arrresult[cnt];
			cnt=cnt*1+1;
			depart_plc.value=arrresult[cnt];
			cnt=cnt*1+1;
			depart_dt.value=arrresult[cnt];
			cnt=cnt*1+1;
			depart_tm.value=arrresult[cnt];
			cnt=cnt*1+1;
			con_mod.value=arrresult[cnt];
			
			var mod=arrresult[cnt];
			cnt=cnt*1+1;
			sub_train.value=arrresult[cnt];
			cnt=cnt*1+1;
			sub_class.value=arrresult[cnt];
			cnt=cnt*1+1;
			sub_own.value=arrresult[cnt];
			cnt=cnt*1+1;
			sub_veh.value=arrresult[cnt];									
			cnt=cnt*1+1;
			airmod.value=arrresult[cnt];									
			cnt=cnt*1+1;
			dis.value=arrresult[cnt];
			cnt=cnt*1+1;
			purpose.value=arrresult[cnt];
			cnt=cnt*1+1;
			accom.value=arrresult[cnt];
			cnt=cnt*1+1;
			cost.value=arrresult[cnt];
			cnt=cnt*1+1;
			remark.value=arrresult[cnt];
			cnt=cnt*1+1;
			trippurpose.value=arrresult[cnt];
			cnt=cnt*1+1;
			cnt=cnt*1+1;
			if(mod=='-1')
			{
				own.style.display="none";
				bus.style.display="none";
				eco.style.display="none";
				veh.style.display="none";
				trn.style.display="none";
				cls.style.display="none";
				trncol.style.display="";
				clscol.style.display="";
				fir.style.display="";
				sec.style.display="";
				discol.style.display="none";
				dislab.style.display="";
				sub_train.style.display="";
				sub_class.style.display="";
				sub_own.style.display="none";
				sub_veh.style.display="none";
			}else if(mod=='Air')
			{
				
				own.style.display="none";
				bus.style.display="";
				eco.style.display="";
				veh.style.display="none";
				trn.style.display="none";
				cls.style.display="none";
				trncol.style.display="none";
				clscol.style.display="none";
				fir.style.display="none";
				sec.style.display="none";
				discol.style.display="none";
				dislab.style.display="";
				
			}else if(mod=='Rail')
			{
				
				own.style.display="none";
				bus.style.display="none";
				eco.style.display="none";
				veh.style.display="none";
				trn.style.display="";
				cls.style.display="";
				trncol.style.display="";
				clscol.style.display="";
				fir.style.display="none";
				sec.style.display="none";
				discol.style.display="none";
				dislab.style.display="";
				sub_train.style.display="";
				sub_class.style.display="";
				sub_own.style.display="none";
				sub_veh.style.display="none";
				
			}else if (mod=='Bus')
			{
				
				own.style.display="";
				bus.style.display="none";
				eco.style.display="none";
				veh.style.display="";
				trn.style.display="none";
				cls.style.display="none";
				trncol.style.display="";
				clscol.style.display="";
				fir.style.display="none";
				sec.style.display="none";
				discol.style.display="";
				dislab.style.display="none";
				sub_train.style.display="none";
				sub_class.style.display="none";
				sub_own.style.display="";
				sub_veh.style.display="";
				
			}
			
			
		}
		//confirmDelete(form);
		
	}
	function setDeparture(form)
	{
			//var depart_plc=document.getElementById('depart_plc1');
		var depart_plc0=document.getElementById('depart_plc0');
		var depart_plc1=document.getElementById('depart_plc1');
		if(form.value=='-1')
		{
			depart_plc1.value=form.value;
			return;
		}
		
		if(depart_plc0.value==form.value)
		{
			alert("<fmt:message key="HRMS.departureArivalplacesame" bundle="${caption}" />");    
			form.value=depart_plc1.value;
			return;
		}
		depart_plc1.value=form.value;
	}
	function setArrival(form)
	{
		var preid=form.value;
		var tb = document.getElementById('detailsTable');
		var arr_plc1 = document.getElementById('arr_plc1');
		var len = tb.rows.length;
		var row=(len-2)/2;
		var idx=form.id.charAt(form.id.length-1);
		var arr="arr_plc"+idx;
		var depart="depart_plc"+idx;
		var arrival=document.getElementById(arr);
		var departure=document.getElementById(depart);	
		if(departure.value=='321611')
		{
		
			if(row==2)
			{
				var arr=document.getElementById("arr_plc1");
				arr.value='321611';
				form.value='321611';
				return;
			}
			else if(idx==0)
			{
				var arr=document.getElementById("arr_plc2")
				arr.value='321611';
				form.value='321611';
				return;
			}else if(idx==(row-1))
			{
				
				var arr=document.getElementById("arr_plc1");
				arr.value='321611';
				form.value='321611';
				return;
			}else
			{
				
				var app=parseInt(idx)+1;
				
				var arr_nm="arr_plc"+app;
				var arr=document.getElementById(arr_nm);
				arr.value='321611';
				form.value='321611';
				return;
			}
			
			return;	
		}
		if(arrival.value==departure.value)
		{
			alert("<fmt:message key="HRMS.arivalplacedepartureplacediff" bundle="${alertLables}" />");
			if(row==2)
			{
				var arr=document.getElementById("arr_plc1");
				form.value=arr.value;
				return;
			}
			else if(idx==0)
			{
				var arr=document.getElementById("arr_plc2")
				
				form.value=arr.value;
				return;
			}else if(idx==(row-1))
			{
				
				var arr=document.getElementById("arr_plc1");
				form.value=arr.value;
				return;
			}else
			{
				
				var app=parseInt(idx)+1;
				
				var arr_nm="arr_plc"+app;
				var arr=document.getElementById(arr_nm);
				form.value=arr.value;
				return;
			}
			
			
			return;
		}
		if(idx==(row-1))
		{
			
			var departure=document.getElementById("depart_plc1");	
			if(form.value==departure.value)
			{
				alert("<fmt:message key="HRMS.notselectdepartureplace" bundle="${alertLables}" />"); 
				var arr=document.getElementById("arr_plc1");	
				form.value=arr.value;
				return;
			}
		}	
		if(row==2)
		{
		
			arr_plc1.value=form.value;
			return;
		}
		if(idx==(row-1))
		{
			
			arr_plc1.value=form.value;
			return;
		}
		if(row!=2&&idx==0)
		{
		
			var temp=parseInt(idx)+2;
			var arr_plc="arr_plc"+temp;
			var next_plc=document.getElementById(arr_plc);
			next_plc.value=form.value;
			
			return;
		}
	
		var temp=parseInt(idx)+1;
		var arr_plc="arr_plc"+temp;
		
		var next_plc=document.getElementById(arr_plc);
		next_plc.value=form.value;
			
		
	}
	function setpredepart(form)
	{
		
		var idx=form.id.charAt(form.id.length-1);
		var tb = document.getElementById('detailsTable');
		var arr_plc1 = document.getElementById('arr_plc1');
		var len = tb.rows.length;
		var row=(len-2)/2;
		var depart_plc;
		if(row==2)
		{
			
			depart_plc=document.getElementById('depart_plc0');
		
		}else if(idx==1)
		{
			row=row-1;
			var depart_plc_nm="depart_plc"+row;
			depart_plc=document.getElementById(depart_plc_nm);
		}else if(idx==2)
		{
			
			depart_plc=document.getElementById('depart_plc0');
			
		}
		else
		{
			idx=idx-1;
			
			var depart_plc_nm="depart_plc"+idx;
			
			depart_plc=document.getElementById(depart_plc_nm);
		}
		if(depart_plc.value=='321611')
		{

			alert("<fmt:message key="HRMS.departureplaceinjourney" bundle="${alertLables}" />");  
			form.value='321611';
			return;
		}
		else
		{
			alert("<fmt:message key="HRMS.departureArivalplacesame" bundle="${alertLables}" />");
			form.value=depart_plc.value;
			return;
		}
		
		
	}
	
	
	function deleteRows() {
		
		
		var tb = document.getElementById('detailsTable');
		
		
		
		var id='';
		var lCnt=0;
		for (var lCntr = 0; lCntr < document.frm1.elements.length; lCntr++)
		{ 
		     if((document.frm1.elements[lCntr].type == "checkbox") && (document.frm1.elements[lCntr].checked == true ) ) 
		     {
		        id=document.frm1.elements[lCntr].id;
		   
		        lCnt++;
		      }
		}
		if(lCnt<=0)
		{
		     alert("<fmt:message key="HRMS.selactonerow" bundle="${caption}" />");    
		      return;
		}
		if(lCnt>1)
		{
			 alert("<fmt:message key="HRMS.selactonerow" bundle="${caption}" />");    
			 return;
		}

		var idx=id.charAt(id.length-1);

		var del=idx*1+idx*1;
		tb.deleteRow(del);	
		tb.deleteRow(del);
		var newrow=(tb.rows.length-2)/2;

		var start=idx*1+1;
		for(var i=start;i<=newrow;i++)
		{
			var app=i*1-1;
			var oldid="srno"+i;
			var newid="srno"+app;

			for (var lCntr = 0; lCntr < document.frm1.elements.length; lCntr++)
			{ 
			     if(document.frm1.elements[lCntr].id==oldid) 
			     {

		    	    document.frm1.elements[lCntr].id=newid;
		    	    document.frm1.elements[lCntr].name=newid;

		      	}
			}
		
		}
		for(var cnt=start;cnt<=newrow;cnt++)
		{
			var app=cnt*1-1;
			//var cnt=parseInt(tmp);
			
			var arr_plc_nm="arr_plc"+cnt;
			var arr_dt_nm="arr_dt"+cnt;
			var arr_tm_nm="arr_tm"+cnt;

			var depart_plc_nm="depart_plc"+cnt;
			var depart_dt_nm="depart_dt"+cnt;
			var depart_tm_nm="depart_tm"+cnt;
			
			var con_mod_nm="con_mod"+cnt;
			
			var purpose_nm="purpose"+cnt;
			
			var cost_nm="cost"+cnt;
			 
			var remark_nm="remark"+cnt;
			
			var eco_nm="eco"+cnt;
			var bus_nm="bus"+cnt;
			var own_nm="own"+cnt;
			var veh_nm="veh"+cnt;
			
			var trn_nm="trn"+cnt;
			var class_nm="class"+cnt;
			
			var fir_dm_nm="fir_dm"+cnt;
			var sec_dm_nm="sec_dm"+cnt;
			
			var discol_nm="discol"+cnt;
			var dislab_nm="dislab"+cnt;
			var dis_nm="dis"+cnt;
			
			var trncol_nm="trncol"+cnt;
			var clscol_nm="clscol"+cnt;
			var sub_train_nm="sub_train"+cnt;
			var sub_class_nm="sub_class"+cnt;
			
			var trncol=document.getElementById(trncol_nm);
			var clscol=document.getElementById(clscol_nm);
			var sub_train=document.getElementById(sub_train_nm);
			var sub_class=document.getElementById(sub_class_nm);
			
			var discol=document.getElementById(discol_nm);
			var dislab=document.getElementById(dislab_nm);
			var dis=document.getElementById(dis_nm);
			
			var fir_dm=document.getElementById(fir_dm_nm);
			var sec_dm=document.getElementById(sec_dm_nm);			
			
			var trn=document.getElementById(trn_nm);
			var class1=document.getElementById(class_nm);
			
			
			var eco=document.getElementById(eco_nm);
		 	var bus=document.getElementById(bus_nm);
		 	var own=document.getElementById(own_nm);
		 	var veh=document.getElementById(veh_nm);
			
			var arr_plc=document.getElementById(arr_plc_nm);
			var arr_dt=document.getElementById(arr_dt_nm);
			var arr_tm=document.getElementById(arr_tm_nm);
			
			var depart_plc=document.getElementById(depart_plc_nm);
			var depart_dt=document.getElementById(depart_dt_nm);
			var depart_tm=document.getElementById(depart_tm_nm);
			
			var con_mod=document.getElementById(con_mod_nm);
			
			var purpose=document.getElementById(purpose_nm);
			
			var cost=document.getElementById(cost_nm)
			
			var remark=document.getElementById(remark_nm);
			
			trncol.id="trncol"+app;
			trncol.name="trncol"+app;
			
			clscol.id="clscol"+app;
			clscol.name="clscol"+app;
			
			sub_train.id="sub_train"+app;
			sub_train.name="sub_train"+app;			

			sub_class.id="sub_class"+app;
			sub_class.name="sub_class"+app;			
			
			trn.id="trn"+app;
			trn.name="trn"+app;
			
			discol.id="discol"+app;
			discol.name="discol"+app;
			
			dislab.id="dislab"+app;
			dislab.name="dislab"+app;
			
			dis.id="dis"+app;
			dis.name="dis"+app;
			
			class1.id="class"+app;
			class1.name="class"+app;
			
			fir_dm.id="fir_dm"+app;
			fir_dm.name="fir_dm"+app;
			
			sec_dm.id="sec_dm"+app;
			sec_dm.name="sec_dm"+app;	
					
			arr_plc.id="arr_plc"+app;	
			arr_dt.id="arr_dt"+app;
			arr_tm.id="arr_tm"+app;
			
			eco.id="eco"+app;
			eco.name="eco"+app;
			s
			bus.id="bus"+app;
			bus.name="bus"+app;
			
			own.id="own"+app;
			own.name="own"+app;
			
			veh.id="veh"+app;
			veh.name="veh"+app;		
			
			arr_plc.name="arr_plc"+app;
			arr_dt.name="arr_dt"+app;
			arr_tm.name="arr_tm"+app;
			
			depart_plc.id="depart_plc"+app;
			depart_dt.id="depart_dt"+app;
			depart_tm.id="depart_tm"+app;
			
			depart_plc.name="depart_plc"+app;
			depart_dt.name="depart_dt"+app;
			depart_tm.name="depart_tm"+app;
			
			con_mod.id="con_mod"+app;
			con_mod.name="con_mod"+app;
			
			
			purpose.id="purpose"+app;
			purpose.name="purpose"+app;
			
			cost.id="cost"+app;
			cost.name="cost"+app;
			
			remark.id="remark"+app;
			remark.name="remark"+app;	
			
		
		
		}
		var row=(tb.rows.length-2)/2;

		
		if(row<=2)
		{
			var bt = document.getElementById('deleterow');
			bt.style.display="none";
			
		}
		if(row<=2)
		{
			var bt = document.getElementById('deleterow');
			bt.style.display="none";
		}
		if(idx==2)
		{
			if(row==2)
			{
			
				var depart=document.getElementById("depart_plc0");
				var arrival=document.getElementById("arr_plc1");
				arrival.value=depart.value;
				return;
			}else
			{
				var depart=document.getElementById("depart_plc0");
				var arrival=document.getElementById("arr_plc2");
				arrival.value=depart.value;
				return;
			}
		}else if(parseInt(idx)==parseInt(row))
		{
				var cnt=parseInt(idx)-1;
//				alert("You are in second last row");
				var depart_nm="depart_plc"+cnt;
//		        alert("Departure place===="+depart_nm);
				var depart=document.getElementById(depart_nm);
				var arrival=document.getElementById("arr_plc1");
				arrival.value=depart.value;
				return;
		}
		else
		{
//			alert("You have selected middle row of table");
			var cnt=parseInt(idx)-1;
			var cnt1=parseInt(idx);
			
			var depart_nm="depart_plc"+cnt;
			var arr_nm="arr_plc"+cnt1;
//			alert("Name of departure is "+depart_nm);
//			alert("Name of arrival is "+arr_nm);
			var depart=document.getElementById(depart_plc_nm);
			var arrival=document.getElementById(arr_nm);
			arrival.value=depart.value;
			return;
			
		}
		
	}
	
	function chkrow()
	{
//		alert("Ina chkrow function");
		var tb = document.getElementById('detailsTable');
		var bt = document.getElementById('deleterow');
		var len = tb.rows.length;
		var row=(len-2)/2;
		if(row>=2)
		{
			
			bt.style.display="";
		}
		else 
		{
			
			bt.style.display="none";
		}
		
	}
	
	function changePage(form)
	{
		if(form.value=="newreq")
		{
			var urlstyle="hdiits.htm?actionFlag=getTravelView"
			document.frm1.action=urlstyle;
			document.frm1.submit();
		}
		else
		{
			var urlstyle="hdiits.htm?actionFlag=getTravelView&flag=draft"
			document.frm1.action=urlstyle;
			document.frm1.submit();
		}
	}
</script>





<hdiits:form name="frm1" validate="true" method="POST"
	encType="multipart/form-data">
	<br>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0" >
	
	<table style="display: none">
	<tr><td>
	<input type="radio" name="newreq" value="newreq"
		onclick="changePage(this)" > New Travel
	Request <input type="radio" name="newreq" value="drafts"
		onclick="changePage(this)" checked="checked"> Previous Request
	</td>
	</tr>
	<tr>
	<td>
	<hdiits:select name="req_type" size="1"
		caption="RequestType" id="req_type" onchange="changeReqType(this)"
		tabindex="2">
		<hdiits:option value="-1">------Select -----</hdiits:option>
		<c:forEach var="reqType" items="${requestType}">
			<hdiits:option value="${reqType.lookupId}">
							${reqType.lookupDesc}
						</hdiits:option>
		</c:forEach>
	</hdiits:select>
	</td>
	</tr>
	</table>
	<br>
<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	<br>
	<table bgcolor="#386CB7" align="center" width="100%">
			<tr align="center">
				<td><font class="Label3" align="center"><u><font
				class="Label3" align="center" color="white"><b></b></u><span class="UserText"
				lang="en-us"></span></font>
					</td>
			</tr>
		</table>
	<br>
	<div id="tabmenu" style="display: none">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1" >
		<hdiits:caption captionid="HRMS.Trip_Details" bundle="${caption}" /></a></li>
	</ul>
	</div>
	<span id="errorId"> </span>
	<!--  Starting main Table -->
	<table id="mainTable" style="display: none">
		<tr>
			<td><fmt:message key="HRMS.Purpose" bundle="${caption}" /></td>
			<td><hdiits:textarea mandatory="false" rows="4" cols="35"
				name="purpose" tabindex="1" id="purpose" validation="txt.isrequired"
				caption="purpose" maxlength="99" /></td>
			<td><fmt:message key="HRMS.Ref_file_no" bundle="${caption}" /></td>
			<td><hdiits:text mandatory="false" name="ref_file_no"
				tabindex="2" id="ref_file_no" validation="txt.isrequired"
				caption="ref. file no." /></td>
			<td width="25%"></td>
		</tr>
		<!--<tr bgcolor="#386CB7">
			<td class="fieldLabel" width="15%" align="left"><font
				color="#0000"> <strong><u><fmt:message
				key="HRMS.Journey_Details" bundle="${caption}" /></u></strong> </font></td>
		</tr>
	--></table>
		
	
	<br>
	
	<table bgcolor="#386CB7" align="center" width="100%" id="journeyLable" style="display: none">
	<tr align="center">
		<td><font class="Label3" align="center"><u><font
			class="Label3" align="center" color="white"><b><hdiits:caption
			captionid="HRMS.Journey_Details" bundle="${caption}" /></b></u><span class="UserText"
			lang="en-us"></span></font></td>


	</tr>
	</table>
	<br>

	<div style="width: 100%;height: 100%;overflow:scroll">
	<!--  End main Table --> <!-- Start : Detailed table -->
	<table id='detailsTable' border="1" cellpadding="0" cellspacing="0"	style="display: none">
		<!-- Start: Heading -->
		<thead title="Heading">
			
			<tr height="25%" class="datatableheader">
				<td rowspan="2" colspan="1" width="1%"></td>
				<td rowspan="1" colspan="3" height="25%" width="25%" align="center"><fmt:message
					key="HRMS.Departure" bundle="${caption}" /></td>
				<td rowspan="1" colspan="3" height="25%" width="25%" align="center"><fmt:message
					key="HRMS.Arrival" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Conveyance_Mode" bundle="${caption}" /></td>
				<td rowspan="2" colspan="2" height="25%" align="center"><fmt:message
					key="HRMS.Sub_Items" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="1%"><fmt:message
					key="HRMS.Distance" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Purose_Of_Stay" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><fmt:message
					key="HRMS.Accomodation" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><fmt:message
					key="HRMS.Total_Cost" bundle="${caption}" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><fmt:message
					key="HRMS.Remark" bundle="${caption}" /></td>
			</tr>
			<tr class="datatableheader">
				<td colspan="1" width="10%"><fmt:message key="HRMS.Place" bundle="${caption}" /></td>
				<td colspan="1" width="5%"><fmt:message	key="HRMS.Departure_Date" bundle="${caption}" /></td>
				<td colspan="1" width="1%"><fmt:message key="HRMS.Time" bundle="${caption}" /></td>
				<td colspan="1" width="10%"><fmt:message key="HRMS.Place" bundle="${caption}" /></td>
				<td colspan="1" width="5%"><fmt:message key="HRMS.Arrival_Date" bundle="${caption}" /></td>
				<td colspan="1" width="1%"><fmt:message key="HRMS.Time" bundle="${caption}" /></td>
			</tr>
		</thead>
		<!-- End: Heading -->
		<!-- Start: First Row -->
		<tbody>
			<tr height="25%">
				<td rowspan="2" colspan="1" width="1%"><hdiits:checkbox
					name="srno0" id="srno0" value="chk" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="arr_plc0" size="1" caption="arrival_place"
					validation="sel.isrequired" id="arr_plc0" mandatory="true"
					onchange="setDeparture(this)" tabindex="2">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="leavetypes" items="${cityList}">
						<hdiits:option value="${leavetypes.cityId}">
							${leavetypes.cityName}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="arr_dt0" caption="arr_dt0" mandatory="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					name="arr_tm0" mandatory="true" id="arr_tm0" size="1" maxlength="5"
					onblur="arrivalTimeCheck(this)" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="depart_plc0" size="1" caption="departure_place"
					validation="sel.isrequired" id="depart_plc0" mandatory="true"
					onchange="setArrival(this)">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="leavetypes" items="${cityList}">
						<hdiits:option value="${leavetypes.cityId}">
							${leavetypes.cityName}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="depart_dt0" caption="depart_dt0" onblur=" checkdate(this)"
					mandatory="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="depart_tm0" id="depart_tm0"
					onblur="departureTimeCheck(this)" mandatory="true" maxlength="5" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="con_mod0" id="con_mod0" caption="con_mode0" mandatory="true"
					validation="sel.isrequired" onchange="hide(this)">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="ConvyanceModevar" items="${ConvyanceModeList}">
						<hdiits:option value="${ConvyanceModevar.lookupId}">${ConvyanceModevar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display:none" id="bus0">Business <hdiits:radio
					name="airmod0" id="airmod0" value="321611" title="Business"
					onclick="setAirmodeval(this)" /></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="eco0">Economic <hdiits:radio
					name="airmod0" id="airmod0" value="321611" title="Economic"
					onclick="setAirmodeval(this)" /></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="own0"><label>Own by</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="veh0"><label>Vehicle Type</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="trn0"
					style="display: none"><label style="border-spacing:em;">Train</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="class0"
					style="display: none"><label>Class</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="fir_dm0"><label></label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="sec_dm0"><label></label></td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="dislab0">
				<center><label>0</label></center>
				</td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="discol0"
					style="display: none;"><hdiits:text size="3" name="dis0"
					default="0" id="dis0" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="purpose0" id="purpose0" caption="purposeofstay0"
					mandatory="true" validation="sel.isrequired">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="Purpose_of_StayListvar"
						items="${Purpose_of_StayList}">
						<hdiits:option value="${Purpose_of_StayListvar.lookupId}">
						${Purpose_of_StayListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="accom0" id="accom0" caption="accomodation0" mandatory="true"
					validation="sel.isrequired" sort="false">
					<hdiits:option value="321611">---------Select---------</hdiits:option>
					<c:forEach var="AccomodationListvar" items="${AccomodationList}">
						<hdiits:option value="${AccomodationListvar.lookupId}">
						${AccomodationListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><hdiits:text
					size="2" name="cost0" id="cost0" default="0.00" maxlength="6" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><hdiits:textarea
					name="remark0" id="remark0" rows="3" cols="28"></hdiits:textarea></td>
			</tr>
			<tr height="25%">
				<td rowspan="1" colspan="1" height="25%" align="center" id="trncol0">
				<hdiits:select name="sub_train0" id="sub_train0" caption="train"
					mandatory="false" validation="sel.isrequired" readonly="true"
					sort="false">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="TrainListvar" items="${TrainList}">
						<hdiits:option value="${TrainListvar.lookupId}">
						${TrainListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> <hdiits:select name="sub_own0" id="sub_onw0" caption="train"
					mandatory="false" validation="sel.isrequired" style="display: none"
					onchange="enabletype(this)">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="owned_byvar" items="${owned_byList}">
						<hdiits:option value="${owned_byvar.lookupId}">
							${owned_byvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="clscol0">
				<hdiits:select name="sub_class0" id="sub_class0" caption="class"
					mandatory="false" validation="sel.isrequired" readonly="true"
					sort="false">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="ClassListvar" items="${ClassList}">
						<hdiits:option value="${ClassListvar.lookupId}"> 
						${ClassListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> <hdiits:select name="sub_veh0" id="sub_veh0" caption="class"
					mandatory="false" validation="sel.isrequired" style="display: none"
					readonly="true">
					<hdiits:option value='321611'> ------Select -----</hdiits:option>
					<c:forEach var="veh_typevar" items="${veh_typeList}">
						<hdiits:option value="${veh_typevar.lookupId}">
							${veh_typevar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>

			</tr>
		</tbody>
		<!-- End: First Row -->
		<!-- Start: Second Row -->
		<tbody>
			<tr height="25%">
				<td rowspan="2" colspan="1" width="1%"><hdiits:checkbox
					name="srno1" id="srno1" value="chk" readonly="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="arr_plc1" size="1" caption="arrival_place1"
					validation="sel.isrequired" id="arr_plc1" mandatory="true"
					onchange="setpredepart(this)">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="leavetypes" items="${cityList}">
						<hdiits:option value="${leavetypes.cityId}">
							${leavetypes.cityName}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="arr_dt1" caption="arr_dt1" onblur="departurechkdate(this)"
					mandatory="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="arr_tm1" onblur="arrivalTimeCheck(this)"
					mandatory="true" maxlength="5" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center">
				<hdiits:select name="depart_plc1" size="1" caption="depart_place1"
					validation="sel.isrequired" id="depart_plc1" mandatory="true"
					onchange="donotchange(this)">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="leavetypes" items="${cityList}">
						<hdiits:option value="${leavetypes.cityId}">
							${leavetypes.cityName}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:dateTime
					name="depart_dt1" caption="depart_dt1" onblur="checkdate(this)"
					mandatory="true" /></td>
				<td rowspan="2" colspan="1" height="25%" width="25%" align="center"><hdiits:text
					size="1" name="depart_tm1" onblur="departureTimeCheck(this)"
					mandatory="true" maxlength="5" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="con_mod1" caption="con_mode1" mandatory="true"
					validation="sel.isrequired" onchange="hide(this)" id="con_mod1">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="ConvyanceModevar" items="${ConvyanceModeList}">
						<hdiits:option value="${ConvyanceModevar.lookupId}">${ConvyanceModevar.lookupDesc}</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="bus1">Business <hdiits:radio
					name="airmod1" id="airmod1" title="Business" value="321611"
					onclick="setAirmodeval(this)" /></td>
				<td rowspan="2" colspan="1" height="25%" align="center"
					style="display: none" id="eco1">Economic <hdiits:radio
					name="airmod1" id="airmod1" title="Economic" value="321611"
					onclick="setAirmodeval(this)" /></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="own1"><label>Own by</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center"
					style="display: none" id="veh1"><label>Vehicle Type</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="trn1"
					style="display: none"><label>Train</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="class1"
					style="display: none"><label>Class</label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="fir_dm1"><label></label></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="sec_dm1"><label></label></td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="dislab1">
				<center><label>0</label></center>
				</td>
				<td rowspan="2" colspan="1" height="25%" width="1%" id="discol1"
					style="display: none;"><hdiits:text size="3" name="dis1"
					id="dis1" default="0" /></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="purpose1" caption="purposeofstay1" mandatory="true"
					validation="sel.isrequired" readonly="true">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="Purpose_of_StayListvar"
						items="${Purpose_of_StayList}">
						<hdiits:option value="${Purpose_of_StayListvar.lookupId}">
							${Purpose_of_StayListvar.lookupDesc}
						</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="5%"><hdiits:select
					name="accom1" caption="accomodation1" mandatory="true"
					validation="sel.isrequired" readonly="true" sort="false">
					<hdiits:option value="321611">------Select -----</hdiits:option>
					<c:forEach var="AccomodationListvar" items="${AccomodationList}">
						<hdiits:option value="${AccomodationListvar.lookupId}">
						${AccomodationListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="2" colspan="1" height="25%" width="2%"><hdiits:text
					size="2" name="cost1" default="0.00" maxlength="6" /></td>
				<td rowspan="2" colspan="1" height="25%" width="10%"><hdiits:textarea
					name="remark1" rows="3" cols="28"></hdiits:textarea></td>
			</tr>
			<tr height="25%">
				<td rowspan="1" colspan="1" height="25%" align="center" id="trncol1">
				<hdiits:select name="sub_train1" id="sub_train1" caption="train1"
					mandatory="false" validation="sel.isrequired" readonly="true"
					sort="false">
					<hdiits:option value='321611'>---------Select--------</hdiits:option>
					<c:forEach var="TrainListvar" items="${TrainList}">
						<hdiits:option value="${TrainListvar.lookupId}">
						${TrainListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> <hdiits:select name="sub_own1" id="sub_own1" caption="train1"
					mandatory="false" validation="sel.isrequired" style="display:none"
					onchange="enabletype(this)">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="owned_byvar" items="${owned_byList}">
						<hdiits:option value="${owned_byvar.lookupId}">
							${owned_byvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>
				<td rowspan="1" colspan="1" height="25%" align="center" id="clscol1">
				<hdiits:select name="sub_class1" id="sub_class1" caption="class1 "
					mandatory="false" validation="sel.isrequired" readonly="true"
					sort="false">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="ClassListvar" items="${ClassList}">
						<hdiits:option value="${ClassListvar.lookupId}"> 
						${ClassListvar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select> <hdiits:select name="sub_veh1" id="sub_veh1" caption="class1 "
					mandatory="false" validation="sel.isrequired" style="display:none"
					readonly="true">
					<hdiits:option value='321611'>------Select -----</hdiits:option>
					<c:forEach var="veh_typevar" items="${veh_typeList}">
						<hdiits:option value="${veh_typevar.lookupId}">
							${veh_typevar.lookupDesc}
					</hdiits:option>
					</c:forEach>
				</hdiits:select></td>


			</tr>
		</tbody>
		<!-- End: Second Row -->

	</table>
	<!-- End : Detailed table --> <br>
	<br>
	
	<center>
	<table style="display: none" >
	<hdiits:button type="button" name="addButton"
		value="Add Journey" id="addButton" captionid="ADD_Journey"
		onclick="addrow()" style="display:none" /><hdiits:button
		type="button" name="savetrip" value="Save Trip" id="savetrip"
		captionid="Save_Trip" onclick="updateDetails()" style="display:none" />
	<hdiits:button type="button" name="deleteButton" value="Delete Journey"
		id="deleterow" captionid="Delete_journey" onclick="deleteRows()"
		style="display: none" />
	</table>
	<br>
	<br>
	</center>
	<center>
	<table border="1" style="display: none" id="attachtb">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="TravelAttachment" />
				<jsp:param name="formName" value="frm1" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="multiple" value="Y" />
				<jsp:param name="rowNumber" value="1" />
				<jsp:param name="removeAttachmentFromDB" value="Y" />
			</jsp:include></td>
		</tr>
	</table>
	</center>
	<br>

	<center>
	<table id='tripdtls' border="1" cellpadding="0" width="68%"
		cellspacing="0" BGCOLOR="WHITE"
		style="background: #eeeeee;padding: 2px;display: none">
		<tr class="datatableheader">
			<td style="width: 45%">Trip Name</td>
			<td style="width: 15%">Status</td>
		</tr>

	</table>
	</center>

	<br>
	<br>
	<center>
	<table style="display:none">
	 <hdiits:button
		type="button" name="saveasButton" value="Save as Draft"
		id="saveasButton" captionid="save_trip" onclick="saveasdraft()"
		style="display:none" /> <hdiits:button type="button"
		name="addtripButton" value="Add Trip" id="addtripbt"
		captionid="add_trip" onclick="addtrip('addRecord')"
		style="display:none" /> 
	</table>
	<br>
	<br>
	</center>

	<c:if test="${empty hrTravelReq}">
	<center><font color="RED"><c:out value="No Travel Request Found!!!!!" ></font> </c:out></center>
	</c:if>	
	<br>
	<c:if test="${not empty hrTravelReq}">
	<center>
		<table id='tourDtlstb' border="1" cellpadding="0" width="68%"
			cellspacing="0" BGCOLOR="WHITE"
			style="background: #eeeeee;padding: 2px">
			<tr class="datatableheader">
				<td style="width: 45%">Request Id</td>
				<td style="width: 15%">Status</td>
				<td style="width: 15%">Actions</td>
			</tr>
			<c:forEach var="hrdraftreq" items="${hrTravelReq}">
				<tr>
					<td style="width: 25%">${hrdraftreq.reqId}</td>
					<td style="width: 15%"><c:if test="${hrdraftreq.approve eq 'N'}"><c:out value="Pending"></c:out></c:if>
					<c:if test="${hrdraftreq.approve ne 'N'}"><c:out value="Approve"></c:out></c:if>
					</td>
					<td style="width: 15%"><a href="#" id="${hrdraftreq.reqId}"
						name="${hrdraftreq.reqId}" onclick="viewDetails(this)">View</a>
					</td>
					<!--<td style="width: 15%"><hdiits:checkbox name="reqChkBox${hrdraftreq.reqId}" id="reqChkBox${hrdraftreq.reqId}" value="true" /> </td>
				--></tr>
			</c:forEach>
		</table>
	</center>
	</c:if>	
	<br>
	<br>
	<center>
	<hdiits:button type="button" name="saveButton"
		value="Submit Request" id="SaveButton" captionid="save_trip"
		onclick="deleteReq()" />
	<hdiits:button type="button" name="closeButton"
		value="Close" id="closeButton" captionid="Close"
		onclick="closewindow()" /></center>
	</div>
	</div>
	</div>
	<!-- Hidden Fields -->
	<hdiits:hidden name="Sr_no" id="Sr_no"></hdiits:hidden>
	<hdiits:hidden name="journeyCnt" id="journeyCnt" />
	<hdiits:hidden name="tourDtls" id="tourDtls" />
	<hdiits:hidden name="journeyDtls" id="journeyDtls" />
	<hdiits:hidden name="draftReq" id="draftReq" />
	<hdiits:hidden name="valpresent" id="valpresent" />
	<hdiits:hidden name="tourName" id="tourName" />
	<hdiits:hidden name="valpresent" id="valpresent" />
	<hdiits:hidden name="Trip_Name" id="Trip_Name" />
	<hdiits:hidden name="RequestId" id="RequestId" />
	<hdiits:hidden name="ParentReqId" id="ParentReqId" />
	<hdiits:hidden name="tourId" id="tourId" />
	<hdiits:hidden name="tourRowIdList" id="tourRowIdList" />
f	<script type="text/javascript">		
		initializetabcontent("maintab");
	</script>
</hdiits:form>


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
