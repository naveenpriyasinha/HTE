<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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

<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="arrDt" value="${resultValue.arrDt}">
</c:set>
<c:set var="departDt" value="${resultValue.departDt}">
</c:set>
<c:set var="arrTm" value="${resultValue.arrTm}">
</c:set>
<c:set var="cityMst" value="${resultValue.cityMst}">
</c:set>
<c:set var="journeyId" value="${resultValue.journeyId}">
</c:set>

<c:set var="departTm" value="${resultValue.departTm}">
</c:set>
<c:set var="Accomodation" value="${resultValue.Accomodation}">
</c:set>
<c:set var="dtls" value="${resultValue.tourDtls}">
</c:set>
<c:set var="readOnly" value="${resultValue.readOnly}">
</c:set>

 

<c:set var="xmlFilePathNameForMulAdd"
	value="${resultValue.xmlFilePathNameForMulAdd}" />

<c:set var="acomDtlsSetKey" value="${resultValue.acomDtlsSetKey}" />


<fmt:setBundle basename="resources.ess.travel.caption"
	var="commonLables1" scope="request" />
<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
<script type="text/javascript">
//Global Variable
var amtVar=0;
function submitDtls()
{
	//alert("In a Submit Details method!!!");
	//alert(document.getElementById('journeyId').value);
	if(document.getElementById('encXML')==null){
		alert('No Record Available');
		return;
	}else{
		 
			var index=document.getElementById('journeyId').value;
			var encXml=document.getElementsByName('encXML');
			var len=encXml.length;
			var xmlArray=new Array();
			for(var i=0;i<len;i++){
				xmlArray[i]=encXml[i].value;
			}
			window.opener.document.getElementById('accomRowXML'+index).value=xmlArray;
	
		
	}
	
	
	window.close();
}
function saveData(form)
{
 	form.style.display="none";
	var fieldArray=new Array();
	fieldArray[0]='fromDate';
	fieldArray[1]='toDate';
	fieldArray[2]='frmTime';
	fieldArray[3]='toTime';
	fieldArray[4]='acomType';
	fieldArray[5]='GovbordingBt';
	fieldArray[6]='GovlodgingRt';
	fieldArray[7]='cktHouse';
	fieldArray[8]='cktChrage';
	fieldArray[9]='hotelBordingBt';
	fieldArray[10]='hotelCharge';
	fieldArray[11]='description';
	addOrUpdateRecord('updateRecord','addDataInAcomDtlsTable',fieldArray);

	
}
	

function updateRecord()
{
	if (xmlHttp.readyState == 4) 
	{
		document.getElementById('srNo').value=document.getElementById('acomDtls').rows.length;
		var fieldArray=new Array();
		
		fieldArray[0]='fromDate';
		fieldArray[1]='frmTime';
		fieldArray[2]='toDate';
		fieldArray[3]='toTime';
		fieldArray[4]='acomType';
		fieldArray[5]='hotelBordingBt';
		fieldArray[6]='hotelCharge';
		fieldArray[7]='GovbordingBt';
		fieldArray[8]='GovlodgingRt';
		fieldArray[9]='cktHouse';
		fieldArray[10]='cktChrage';
		fieldArray[11]='description';
		updateDataInTable('encXML',fieldArray);
	}
}
function formValidating()
{

	var frmDt=document.getElementById('fromDate').value;
	var toDt=document.getElementById('toDate').value;	
	var accomodation = document.TravelAdv.acomType.options.selectedIndex;
	var desc=document.getElementById('description').value;
	var arrivalDt;
	var deptDt;
	var arrTime=document.getElementById('arrTm').value;
	var deptTime=document.getElementById('departTm').value;
	var frmTm=document.getElementById('frmTime').value;
	var toTm=document.getElementById('toTime').value;
	if(frmDt=="")
	{
		alert("<fmt:message key="HRMS_fromdate_req" bundle="${alertLables}" />");
		return false;
	}
	
	if(toDt=="")
	{
	     	alert("<fmt:message key="HRMS_todate_req" bundle="${alertLables}" />");
	     	return false;
	}
	
	if(frmTm=="")
	{
		alert("<fmt:message key="HRMS_fromtime_req" bundle="${alertLables}" />");
		return false;
	
	}
		
	if(toTm=="")
	{
	alert("<fmt:message key="HRMS_totime_req" bundle="${alertLables}" />");
	return false;
	}
	if(frmDt == toDt)
	{
	  	var frmTimeArry = new Array();
	  	frmTimeArry=frmTm.split(':');
	  	fHH=frmTimeArry[0];
	  	fMM=frmTimeArry[1];
	  	
		var toTimeArry = new Array();
		toTimeArry=toTm.split(':');
		tHH=toTimeArry[0];
		tMM=toTimeArry[1];
		
			
		if(fHH == tHH && fMM == tMM)
		{
		      alert("<fmt:message key="HRMS_frmandtotimesame" bundle="${alertLables}" />"); 
		      return false;
		}
		
		if(fHH > tHH)
		{
		
		 alert("<fmt:message key="HRMS_frmandtotimegreater" bundle="${alertLables}" />");
		 return false;
		}
		if(fHH == tHH && fMM > tMM)
		{
		     alert("<fmt:message key="HRMS_frmandtotimegreater" bundle="${alertLables}" />");
		     return false;
		}
	}
	
	
		
	if(accomodation == 0)
	{
	alert("<fmt:message key="HRMS_accomodation_type_req" bundle="${alertLables}" />");
	return false;
	}
	
	if(accomodation == 1)
	{
		var status1=document.TravelAdv.cktHouse[0].checked;		
		var circuitChg=document.TravelAdv.cktChrage.value;
		if(status1=="true" && circuitChg=="")
		{
			alert("<fmt:message key="HRMS_chargepaid" bundle="${alertLables}" />");
			return false;
		}		
	}
	
	if(accomodation == 3)
	{
		var status2=document.TravelAdv.hotelBordingBt[0].checked;		
		var hotelChg=document.getElementById('hotelCharge').value;
		if(status1=="true" && hotelChg=="")
		{
		alert("<fmt:message key="HRMS_lodgingnbordingcharge" bundle="${alertLables}" />");
		return false;
		}		
	}
			
	if(desc=="")
	{
        alert("<fmt:message key="HRMS_description_req" bundle="${alertLables}" />");
        return false;

	}
	return true;
}
function addDataInAcomTable()
{			
	if(formValidating()){
	var fieldArray=new Array();
	fieldArray[0]='fromDate';
	fieldArray[1]='toDate';
	fieldArray[2]='frmTime';
	fieldArray[3]='toTime';
	fieldArray[4]='acomType';
	fieldArray[5]='GovbordingBt';
	fieldArray[6]='GovlodgingRt';
	fieldArray[7]='cktHouse';
	fieldArray[8]='cktChrage';
	fieldArray[9]='hotelBordingBt';
	fieldArray[10]='hotelCharge';
	fieldArray[11]='description';
	document.getElementById('submitBt').disabled=false;
	addOrUpdateRecord('addRecord','addDataInAcomDtlsTable',fieldArray);
	}
}

function addRecord()
{
	if (xmlHttp.readyState == 4) 
	{
		var encXML=xmlHttp.responseText;
		
		document.getElementById('srNo').value=document.getElementById('acomDtls').rows.length;
		var fieldArray=new Array();
		fieldArray[0]='srNo';
		fieldArray[1]='fromDate';
		fieldArray[2]='frmTime';
		fieldArray[3]='toDate';
		fieldArray[4]='toTime';
		fieldArray[5]='acomType';
		fieldArray[6]='hotelBordingBt';
		fieldArray[7]='hotelCharge';
		fieldArray[8]='GovbordingBt';
		fieldArray[9]='GovlodgingRt';
		fieldArray[10]='cktHouse';
		fieldArray[11]='cktChrage';
		fieldArray[12]='description';
		
		addDataInTable('acomDtls','encXML',fieldArray,'editRecord','deleteTravel','');
		//addDataInTable('acomDtls','encXML',new Array('srNo','fromDate','frmTime','toDate','toTime','acomType','hotelBordingBt','hotelCharge','GovbordingBt','GovlodgingRt','description'),'');
		cleardtls();
	}	
}
function cleardtls()
{
	document.getElementById('acomType').selectedIndex=0;
	document.getElementById('fromDate').value="";
	document.getElementById('frmTime').value="";
	document.getElementById('toDate').value="";
	document.getElementById('toTime').value="";

	document.getElementById('govPaidTb').style.display="none";
	document.getElementById('cktHouseTb').style.display="none";
	document.getElementById('hotelPaidTb').style.display="none";
	
	document.getElementById('hotelCharge').value="";
	document.getElementById('cktChrage').value="";
	
	var name=document.getElementsByName('GovbordingBt');
	name[1].checked="checked";
	
	name=document.getElementsByName('GovlodgingRt');
	name[1].checked="checked";
	
	name=document.getElementsByName('cktHouse');
	name[1].checked="checked";	

	name=document.getElementsByName('hotelBordingBt');
	name[1].checked="checked";	
	
	document.getElementById('description').value="";
}
function deleteTravel(rowId)
	{
		var ans=deleteRecord(rowId);		
	}
function viewRecord(rowId){
	sendAjaxRequestForEditForTravel(rowId, 'populateForm') ;
	updateRow = null;
	document.getElementById('save').style.display="none";
	 
}	
	function sendAjaxRequestForEditForTravel(rowId, methodName, progressBarFlag) 
	{
		//	alert('sendAjaxRequestForEdit called....');
		var editNtCap = "";
		try
		{
			editNtCap = cmnLblArray[5];	
		}
		catch(e)
		{
			editNtCap = "You can not edit this record, Because you have open one record for update."
		}
		if(updateRow != null)
		{
			alert (editNtCap);
			return ;
		}
		if(progressBarFlag != false)
		{
			showProgressbar("Please Wait...");
		}
		
		updateRow = rowId;

		var hField = rowId.substring(3, rowId.length);
		//	alert(hField);
		
		var xmlFileName = document.getElementById(hField).value;
		//	alert('xmlFileName'+xmlFileName);
		if(isAllreadyAddedVOFileName(xmlFileName))
		{
			flagForUpdatedVO = true;		
		}
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) 
		{
			if(progressBarFlag != false)
			{
			  hideProgressbar();
			}
		  alert ("Your browser does not support AJAX!");
		  return;
		} 		
		
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + xmlFileName;
		methodName = methodName + "()";
		
			
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4) {
				eval(methodName);
				if(progressBarFlag != false)
				{
					hideProgressbar();
				}
			 }
		 }
		
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);	
	}
function editRecord(rowId)
{
	
	sendAjaxRequestForEdit(rowId, 'populateForm')
}
function populateForm()
{
	if (xmlHttp.readyState == 4) 
	{

		var decXML=xmlHttp.responseText;
		var xmlDOM=getDOMFromXML(decXML);	
		
		document.getElementById('acomType').value=getXPathValueFromDOM(xmlDOM,'cmnLookupMst/lookupId');
		var fromDate=new Date();
		fromDate=getXPathValueFromDOM(xmlDOM,'accomFromDate');
		//alert('fromDate'+fromDate);
		var dateArray=getDateAndTimeFromDateObj(fromDate);
		document.getElementById('fromDate').value=dateArray[0];
		document.getElementById('frmTime').value=dateArray[1];
		
		var toDate=new Date();
		toDate=getXPathValueFromDOM(xmlDOM,'accomToDate');
		dateArray=getDateAndTimeFromDateObj(toDate);
		document.getElementById('toDate').value=dateArray[0];
		document.getElementById('toTime').value=dateArray[1];
		
		var accomType=getXPathValueFromDOM(xmlDOM,'cmnLookupMst/lookupId');
		 
		if(accomType=='321547'||accomType=='321553')
		{
			document.getElementById('govPaidTb').style.display="";

			var name=document.getElementsByName('GovbordingBt');
			if(getXPathValueFromDOM(xmlDOM,'govBording')=='Y')
			{
				name[0].checked="checked";
			}else
			{
				name[1].checked="checked";
			}
			
			
			name=document.getElementsByName('GovlodgingRt');

			if(getXPathValueFromDOM(xmlDOM,'govLodging')=='Y')
			{
				name[0].checked="checked";
			}else
			{
				name[1].checked="checked";
			}
	
			document.getElementById('cktHouseTb').style.display="none";
			document.getElementById('hotelPaidTb').style.display="none";
		}else if(accomType=='321556'||accomType=='321550')
		{
			document.getElementById('govPaidTb').style.display="none";
			document.getElementById('cktHouseTb').style.display="";
			var name=document.getElementsByName('cktHouse');
			if(getXPathValueFromDOM(xmlDOM,'ckthouseClaimed')=='Y')
			{
				name[0].checked="checked";
			}else
			{
				name[1].checked="checked";
			}
			document.getElementById('cktChrage').value=getXPathValueFromDOM(xmlDOM,'ckthouseCharge');
			document.getElementById('hotelPaidTb').style.display="none"	
		}else if(accomType=='321548'||accomType=='321554')
		{
			document.getElementById('govPaidTb').style.display="none";
			document.getElementById('cktHouseTb').style.display="none";
			document.getElementById('hotelPaidTb').style.display=""
			var name=document.getElementsByName('hotelBordingBt');
			if(getXPathValueFromDOM(xmlDOM,'hotelClaimed')=='Y')
			{
				name[0].checked="checked";
			}else
			{
				name[1].checked="checked";
			}
			document.getElementById('hotelCharge').value=getXPathValueFromDOM(xmlDOM,'hotelCharge');
		}else
		{
			document.getElementById('govPaidTb').style.display="none";
			document.getElementById('cktHouseTb').style.display="none";
			document.getElementById('hotelPaidTb').style.display="none"
		}
		if(getXPathValueFromDOM(xmlDOM,'description')==null){
			document.getElementById('description').value="";
		}else
		{
			document.getElementById('description').value=getXPathValueFromDOM(xmlDOM,'description');	
		}
		
		document.getElementById('save').style.display="";
	}
}
function closewindow()
{
		window.close();
}
function changeAcomType(form)
{
	if(form.value=='321553'||form.value=='321547')
	{
		document.getElementById('govPaidTb').style.display="";
		document.getElementById('cktHouseTb').style.display="none";
		document.getElementById('hotelPaidTb').style.display="none";
	}else if(form.value=='321556'||form.value=='321550')
	{
		document.getElementById('govPaidTb').style.display="none";
		document.getElementById('cktHouseTb').style.display="";
		document.getElementById('hotelPaidTb').style.display="none"	
	}else if(form.value=='321554'||form.value=='321548')
	{
		document.getElementById('govPaidTb').style.display="none";
		document.getElementById('cktHouseTb').style.display="none";
		document.getElementById('hotelPaidTb').style.display=""
	}else
	{
		document.getElementById('govPaidTb').style.display="none";
		document.getElementById('cktHouseTb').style.display="none";
		document.getElementById('hotelPaidTb').style.display="none"
	}
}

function formValidate(){
 if(document.getElementById('fromDate').value==''){
 	return false;
}if(document.getElementById('toDate').value==''){
 return false;
}if(document.getElementById('frmTime').value==''){
return false;
}if(document.getElementById('toTime').value==''){
return false;
}
return true;
}

function fromdatecheck(form)
{ 
	var from_date=form.value;
	var arrival_date=document.getElementById('arrivalDt').innerHTML;
	var departure_date=document.getElementById('departDt').innerHTML;
	if(from_date.length==0)
	{
		return;
	}
	else
	{
		var from_yr=from_date.substring(6,10);
	 	var arrival_yr=arrival_date.substring(6,10);	 		  		
	 	var departure_yr=departure_date.substring(6,10);	
		  		
		var from_mon=from_date.substring(3,5);
		var arrival_mon=arrival_date.substring(3,5);
		var departure_mon=departure_date.substring(3,5);
		
		var from_day=from_date.substring(0,2);
		var arrival_day=arrival_date.substring(0,2);
		var departure_day=departure_date.substring(0,2);
		
		ifrom_yr=parseInt(from_yr);
		iarrival_yr=parseInt(arrival_yr);
		ideparture_yr=parseInt(departure_yr);
		
		ifrom_mon=parseInt(from_mon);
		iarrival_mon=parseInt(arrival_mon);
		ideparture_mon=parseInt(departure_mon);
		
		ifrom_day=parseInt(from_day);
		iarrival_day=parseInt(arrival_day);
		ideparture_day=parseInt(departure_day);
		
		if(ifrom_yr<iarrival_yr)
		{
			alert("<fmt:message key="HRMS.fromdate_arrrivaldate_validation" bundle="${alertLables}" />"); 
			form.value="";
			return;
		}
		else if(ifrom_yr==iarrival_yr)
		{
			if(ifrom_mon<iarrival_mon)
			{
				alert("<fmt:message key="HRMS.fromdate_arrrivaldate_validation" bundle="${alertLables}" />");  
				form.value="";
				return;
			}
			else if(ifrom_mon==iarrival_mon)
			{
				if(ifrom_day<iarrival_day)
				{
					alert("<fmt:message key="HRMS.fromdate_arrrivaldate_validation" bundle="${alertLables}" />");  
					form.value="";
					return;
				}
			}
		}
		
		if(ifrom_yr>ideparture_yr)
		{
			alert("<fmt:message key="HRMS.fromdate_departuedate_validation" bundle="${alertLables}" />");  
			form.value="";
			return;
		}
		else if(ifrom_yr==ideparture_yr)
		{
			if(ifrom_mon>ideparture_mon)
			{
				alert("<fmt:message key="HRMS.fromdate_departuedate_validation" bundle="${alertLables}" />");  
				form.value="";
				return;
			}
			else if(ifrom_mon==ideparture_mon)
			{
				if(ifrom_day>ideparture_day)
				{
					alert("<fmt:message key="HRMS.fromdate_departuedate_validation" bundle="${alertLables}" />");  
					form.value="";
					return;
				}
			}
		}
		
	}
}

function tocheckdate(form)
	{
		var to_date=form.value;
		var from_date=document.getElementById('fromDate').value;
		var departure_date=document.getElementById('departDt').innerHTML;
		
		var departure_yr=departure_date.substring(6,10);	
		var departure_mon=departure_date.substring(3,5);
		var departure_day=departure_date.substring(0,2);
		
 		var from_yr=from_date.substring(6,10);
	 	var to_yr=to_date.substring(6,10);
	 		  		
		var from_mon=from_date.substring(3,5);
		var to_mon=to_date.substring(3,5);
		
		var from_day=from_date.substring(0,2);
		var to_day=to_date.substring(0,2);
		
		ifrom_yr=parseInt(from_yr);
		ito_yr=parseInt(to_yr);
		ideparture_yr=parseInt(departure_yr);
		
		ifrom_mon=parseInt(from_mon);
		ito_mon=parseInt(to_mon);
		ideparture_mon=parseInt(departure_mon);
		
		ifrom_day=parseInt(from_day);
		ito_day=parseInt(to_day);
		ideparture_day=parseInt(departure_day);
		
	if(to_date.length==0)
	{
		return;
	}
	else if(from_date.length==0)
	{
		alert("<fmt:message key="HRMS.fromdate_entry" bundle="${alertLables}" />");  
		form.value="";
	
		return;
	}
	else
	{
		if(ifrom_yr>ito_yr)
		{
			alert("<fmt:message key="HRMS.fromdate_todate_validation" bundle="${alertLables}" />");  
			form.value="";
			return;
		}
		else if(ifrom_yr==ito_yr)
		{
			if(ifrom_mon>ito_mon)
			{
				alert("<fmt:message key="HRMS.fromdate_todate_validation" bundle="${alertLables}" />");  
				form.value="";
				return;
			}
			else if(ifrom_mon==ito_mon)
			{
				if(ifrom_day>ito_day)
				{
					alert("<fmt:message key="HRMS.fromdate_todate_validation" bundle="${alertLables}" />");  
					form.value="";
					return;
				}
			}
		}
		
		if(ito_yr>ideparture_yr)
		{
			alert("<fmt:message key="HRMS.todate_departuedate_validation" bundle="${alertLables}" />");  
			form.value="";
			return;
		}
		else if(ito_yr==ideparture_yr)
		{
			
			if(ifrom_mon>ideparture_mon)
			{
				alert("<fmt:message key="HRMS.todate_departuedate_validation" bundle="${alertLables}" />");  
				form.value="";
				return;
			}
			else if(ito_mon==ideparture_mon)
			{
				if(ito_day>ideparture_day)
				{
					alert("<fmt:message key="HRMS.todate_departuedate_validation" bundle="${alertLables}" />");  
					form.value="";
					return;
				}
			}
		}
	}
}
	
function fromtimecheck(form)
{
		to_date=document.getElementById('toDate').value;
		var from_time =form.value;
		if(from_time.length==0)
		{
			return;
		}
		if(from_time.length==5 && from_time.charAt(2)!=':')
		{
			alert("<fmt:message key="HRMS.fromtime_lesslength" bundle="${alertLables}" />"); 		
			form.value="";
			document.TravelAdv.frmTime.focus();
			return;
		}else if(from_time.length==3)
		{
			if(isNaN(from_time.charAt(0))||isNaN(from_time.charAt(1))||isNaN(from_time.charAt(2)))
			{
				alert("<fmt:message key="HRMS.specialcharsnotallowed" bundle="${alertLables}" />"); 	
			
				form.value="";
				document.TravelAdv.frmTime.focus();
				return;
			}else
			{
				hour=from_time.substring(0,1);
				min=from_time.substring(1,3);
				var imin=parseInt(min);
				if(imin>59)
				{
					alert("<fmt:message key="HRMS.minutenotgreater" bundle="${alertLables}" />");
				
					form.value="";
					document.TravelAdv.frmTime.focus();
					return;
					
				}
				var nhour="0"+hour;
				var newtime=nhour+":"+min;
				form.value=newtime
				return;
			}
			
		}else if(from_time.length==4)
		{
				if(isNaN(from_time.charAt(0))||isNaN(from_time.charAt(1))||isNaN(from_time.charAt(2))||isNaN(from_time.charAt(3)))
				{
					alert("<fmt:message key="HRMS.specialcharsnotallowed" bundle="${alertLables}" />");
					
					form.value="";
					document.TravelAdv.frmTime.focus();
					return;
				}
				else
				{
					hour=from_time.substring(0,2);
					min=from_time.substring(2,4);
					var ihour=parseInt(hour);
					var imin=parseInt(min);
					if(ihour>24)
					{
					alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${alertLables}" />");
						
						form.value="";
						document.TravelAdv.frmTime.focus();
						return;
					}else if(ihour==24)
					{
						if(imin!=0)
						{
							alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${alertLables}" />");
							form.value="";
							document.TravelAdv.frmTime.focus();
							return;
	
						}
					}
					if(imin>60)
					{
						alert("<fmt:message key="HRMS.minutenotgreater" bundle="${alertLables}" />");
						form.value="";
						document.TravelAdv.frmTime.focus();
						return;
					
					}
					var newtime=hour+":"+min;
					form.value=newtime
					return;
				}
		}else if(from_time.length==5 && from_time.charAt(2)==':')
		{
					if(isNaN(from_time.charAt(0))||isNaN(from_time.charAt(1))||isNaN(from_time.charAt(3))||isNaN(from_time.charAt(4)))
					{
						alert("<fmt:message key="HRMS.selectpropertime" bundle="${alertLables}" />");
						form.value="";
						document.TravelAdv.frmTime.focus();
						return;
					}
					hour=from_time.substring(0,2);
					min=from_time.substring(3,5);
					
					
					var ihour=parseInt(hour);
					var imin=parseInt(min);
					if(ihour>24)
					{
						alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${alertLables}" />");
						form.value="";
						return;
					}else if(ihour==24)
					{
						if(imin!=0)
						{
							alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${alertLables}" />");
							form.value="";
							document.TravelAdv.frmTime.focus();
							return;
	
						}
					}
					if(imin>60)
					{
						alert("<fmt:message key="HRMS.minutenotgreater" bundle="${alertLables}" />");
						form.value="";
						document.TravelAdv.frmTime.focus();
						return;
					
					}
		}
		else if(from_time.length==2)
		{
			if(isNaN(from_time.charAt(0))||isNaN(from_time.charAt(1)))
			{
				alert("<fmt:message key="HRMS.specialcharsnotallowed" bundle="${alertLables}" />"); 	
				form.value="";
				document.TravelAdv.frmTime.focus();
				return;
			}else
			{
				var ifrom_time=parseInt(from_time);
				if(ifrom_time>59)
				{
					alert("<fmt:message key="HRMS.minutenotgreater" bundle="${alertLables}" />");
				
					form.value="";
					document.TravelAdv.frmTime.focus();
					return;
					
				}
				var nhour="00";
				var newtime=nhour+":"+from_time;
				form.value=newtime;
				return;
			}
		}
		else
		{
			alert("<fmt:message key="HRMS.selectpropertime" bundle="${alertLables}" />");
			form.value="";
			document.TravelAdv.frmTime.focus();
			return;
		}
		 
}

function totimecheck(form)
{
		var from_date=document.getElementById('fromDate').value;
		var to_date=document.getElementById('toDate').value;

		var to_time =form.value;
		var from_time =document.getElementById('frmTime').value;
		
		var hour=to_time.substring(0,2);
		var min=to_time.substring(3,5);
					
		var ihour=parseInt(hour);
		var imin=parseInt(min);
					
		if(to_time.length==0)
		{
			return;
		}
		else if(from_time.length==0)
		{
			alert("<fmt:message key="HRMS.fromtime_entry" bundle="${alertLables}" />");  
			form.value="";
		    document.TravelAdv.frmTime.focus();
			return;
		}
		else if(to_date.length==0)
		{
			alert("<fmt:message key="HRMS.todate_entry" bundle="${alertLables}" />");  
			form.value="";
			document.TravelAdv.toDate.focus();
			return;
		}
		else if(to_time.length==5 && to_time.charAt(2)!=':')
		{
			alert("<fmt:message key="HRMS.selecttime" bundle="${alertLables}" />");  
			form.value="";
			document.TravelAdv.toTime.focus();
			return;
		}else if(to_time.length==3)
		{
			if(isNaN(to_time.charAt(0))||isNaN(to_time.charAt(1))||isNaN(to_time.charAt(2)))
			{
				alert("<fmt:message key="HRMS.specialcharsnotallowed" bundle="${alertLables}" />");  
				form.value="";
				document.TravelAdv.toTime.focus();
				return;
			}else
			{
				hour=to_time.substring(0,1);
				min=to_time.substring(1,3);
				var imin=parseInt(min);
				if(imin>59)
				{
					alert("<fmt:message key="HRMS.minutenotgreater" bundle="${alertLables}" />");  
				
					form.value="";
					document.TravelAdv.toTime.focus();
					return;
					
				}
				var nhour="0"+hour;
				var newtime=nhour+":"+min;
				form.value=newtime;
				
			}
			
		}else if(to_time.length==4)
		{
			 
				if(isNaN(to_time.charAt(0))||isNaN(to_time.charAt(1))||isNaN(to_time.charAt(2))||isNaN(to_time.charAt(3)))
				{
				 
					form.value="";
					return;
				}
				else
				{
					hour=to_time.substring(0,2);
					min=to_time.substring(2,4);
					var ihour=parseInt(hour);
					var imin=parseInt(min);
					 

					if(ihour>24)
					{
						alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${alertLables}" />");  
					
						form.value="";
						document.TravelAdv.toTime.focus();
						return;
					}else if(ihour==24)
					{
						if(imin!=0)
						{
							alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${alertLables}" />");  
							form.value="";
							document.TravelAdv.toTime.focus();
							return;
	
						}
					}
					if(imin>60)
					{
						alert("<fmt:message key="HRMS.minutenotgreater" bundle="${alertLables}" />");  
						form.value="";
						document.TravelAdv.toTime.focus();
						return;
					
					}
					var newtime=hour+":"+min;
					form.value=newtime
				
				}
		}else if(to_time.length==5 && to_time.charAt(2)==':')
		{
					if(isNaN(to_time.charAt(0))||isNaN(to_time.charAt(1))||isNaN(to_time.charAt(3))||isNaN(to_time.charAt(4)))
					{
							alert("<fmt:message key="HRMS.selecttime" bundle="${alertLables}" />");  
						form.value="";
						document.TravelAdv.toTime.focus();
						return;
					}
					
					if(ihour>25)
					{
						alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${alertLables}" />");  
						form.value="";
						document.TravelAdv.toTime.focus();
						return;
					}else if(ihour==24)
					{
						if(imin!=0)
						{
							alert("<fmt:message key="HRMS.hoursnotgreater" bundle="${alertLables}" />");  
							form.value="";
							document.TravelAdv.toTime.focus();
							return;
	
						}
					}
					if(imin>60)
					{
							alert("<fmt:message key="HRMS.minutenotgreater" bundle="${alertLables}" />");  
						form.value="";
						document.TravelAdv.toTime.focus();
						return;
					
					}
		}
		else if(to_time.length==2)
		{
			if(isNaN(to_time.charAt(0))||isNaN(to_time.charAt(1)))
			{
				alert("<fmt:message key="HRMS.specialcharsnotallowed" bundle="${alertLables}" />");  
			
				form.value="";
				document.TravelAdv.toTime.focus();
				return;
			}else
			{
				
				var ito_time=parseInt(to_time);
				if(ito_time>59)
				{
					alert("<fmt:message key="HRMS.minutenotgreater" bundle="${alertLables}" />");  
					form.value="";
					document.TravelAdv.toTime.focus();
					return;	
				}
				var nhour="00";
				var newtime=nhour+":"+to_time;
				form.value=newtime;
				
			}
		}
		else
		{
			alert("<fmt:message key="HRMS.selecttime" bundle="${alertLables}" />");  
			form.value="";
			document.TravelAdv.toTime.focus();
			return;
		}
		
		if(from_date==to_date)
		{
			to_time=form.value;
			
			hour=to_time.substring(0,2);
		    min=to_time.substring(3,5);
			var from_hour=from_time.substring(0,2);
			var from_min=from_time.substring(3,5);
			
			ihour=parseInt(hour);
		    imin=parseInt(min);
			var ifrom_hour=parseInt(from_hour);
			var ifrom_min=parseInt(from_min);
			
			if(ihour<ifrom_hour)
			{
				alert("<fmt:message key="HRMS.fromtime_less" bundle="${alertLables}" />"); 			
				form.value="";
				document.TravelAdv.toTime.focus();
				return;
				 
			}
			else if(ihour==ifrom_hour)
			{

				if(ifrom_min>=imin)
				{
					alert("<fmt:message key="HRMS.fromtime_less" bundle="${alertLables}" />"); 
					form.value="";
					document.TravelAdv.toTime.focus();
					return;
				}
			}	
			else
			{
				return;
			}			
		}
}

</script>
<br>
<br>

<hdiits:form name="TravelAdv" validate="true" method="POST"
	encType="multipart/form-data">

	<center>
	<table id='tripdtls' border="1" cellpadding="0" width="100%"
		cellspacing="0" BGCOLOR="WHITE"
		style="background: #eeeeee;padding: 2px">
		<tr class="datatableheader">
			<td style="width: 25%" colspan="1">City</td>
			<td style="width: 15%" colspan="1">Arrival Date</td>
			<td style="width: 15%" colspan="1">Arrival Time</td>
			<td style="width: 25%" colspan="1">Departure Date</td>
			<td style="width: 25%" colspan="1">Departure Time</td>
		</tr>
		<tr>
			<td><label id="city">${cityMst.cityName}</label></td>
			<td><label id="arrivalDt">${arrDt}</label></td>
			<td><hdiits:text name="arrTm" size="5" readonly="true"
				default="${arrTm}" /></td>
			<td><label id="departDt">${departDt}</label></td>
			<td><hdiits:text name="departTm" size="5" readonly="true"
				default="${departTm}" /></td>
		</tr>
	</table>
	</center>
	<br>
	<br>
	<br>
	<br>
	<hdiits:fieldGroup titleCaptionId="HRMS.AccomDetais" bundle="${commonLables1}" expandable="false"/>
	
	<table width="100%" align="center">
		<tr>
			<td><hdiits:caption captionid="HRMS.FromDate" bundle="${commonLables1}" /></td>
			<td><hdiits:dateTime name="fromDate" captionid="HRMS.DummyArrDt"
				bundle="${commonLables1}" maxvalue="31/12/2099"  mandatory="true" onblur="" /></td>
			<td><hdiits:caption captionid="HRMS.ToDate" bundle="${commonLables1}" /></td>
			<td><hdiits:dateTime name="toDate" captionid="HRMS.DummyDeprDt"
				bundle="${commonLables1}" maxvalue="31/12/2099"  mandatory="true" onblur="" /></td>
		</tr>
		<script>
document.TravelAdv.fromDate.readOnly=true;
document.TravelAdv.toDate.readOnly=true;
</script>
		<tr>
			<td><hdiits:caption captionid="HRMS.FromTime" bundle="${commonLables1}" /></td>
			<td><hdiits:text name="frmTime" id="frmTime" mandatory="true"
				maxlength="5" onblur="fromtimecheck(this)" size="5" /></td>
			<td><hdiits:caption captionid="HRMS.ToTime" bundle="${commonLables1}" />  </td>
			<td><hdiits:text name="toTime" id="toTime" mandatory="true"
				maxlength="5" onblur="totimecheck(this)" size="5" /></td>
		</tr>
		<tr>
			<td><fmt:message key="HRMS.Accomodation" bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:select name="acomType" size="1"
				caption="acomType" id="acomType" onchange="changeAcomType(this)"
				tabindex="2" style="">
				<hdiits:option value="-1">------Select -----</hdiits:option>
				<c:forEach var="acomTypeList" items="${Accomodation}">
					<hdiits:option value="${acomTypeList.lookupId}">
													${acomTypeList.lookupDesc}
								</hdiits:option>
				</c:forEach>
			</hdiits:select></td>
		</tr>
	</table>
	<table>
		<tr>
			<td>
			<table id="govPaidTb" style="display: none">
				<tr>
					<td><hdiits:caption captionid="HRMS.BOARDREASON" bundle="${commonLables1}" /></td>
					<td>Yes<input name="GovbordingBt" type="radio"
						id="GovbordingBt" value="Y" /> No<input name="GovbordingBt"
						type="radio" checked="checked" id="GovbordingBt" value="N" /></td>
				</tr>
				<tr>
					<td><hdiits:caption captionid="HRMS.LODGINGREASON" bundle="${commonLables1}" /></td>
					<td>Yes<input name="GovlodgingRt" type="radio"
						id="GovlodgingRt" value="Y" /> No<input name="GovlodgingRt"
						type="radio" checked="checked" id="GovlodgingRt" value="N" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table id="cktHouseTb" style="display: none">
				<tr>
					<td><hdiits:caption captionid="HRMS.CIRCUITSTAY" bundle="${commonLables1}" /></td>
					<td>Yes <input type="radio" name="cktHouse" id="cktHouse"
						value="Y" /> No <input type="radio" name="cktHouse" id="cktHouse"
						value="N" checked="checked" /></td>
				</tr>
				<tr>
					<td><hdiits:caption captionid="HRMS.CHARGE" bundle="${commonLables1}" /></td>
					<td><hdiits:number name="cktChrage" id="cktChrage" /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table id="hotelPaidTb" style="display: none">
				<tr>
					<td><hdiits:caption captionid="HRMS.LODBODCLM" bundle="${commonLables1}" /></td>
					<td>Yes <input type="radio" name="hotelBordingBt"
						id="hotelBordingBt" value="Y" /> No <input type="radio"
						name="hotelBordingBt" id="hotelBordingBt" value="N"
						checked="checked" /></td>
				</tr>
				<tr>
					<td><hdiits:caption captionid="HRMS.LODBODCHRG" bundle="${commonLables1}" /></td>
					<td><hdiits:number name="hotelCharge" id="hotelCharge" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<td><hdiits:caption captionid="DESCRIPTION" bundle="${commonLables1}" /></td>
			<td><hdiits:text name="description" id="description" /></td>
		</tr>
		<tr>
	</table>
	<center><hdiits:button name="addData" type="button"
		value="Add" id="addData" onclick="addDataInAcomTable()" /> <hdiits:button
		name="save" id="save" type="button" value="Save"
		onclick="saveData(this)" style="display:none" /></center>

	<table id="acomDtls" border="1" cellpadding="0" width="100%"
		cellspacing="0" BGCOLOR="WHITE"
		style="background: #eeeeee;padding: 2px">
		<tr class="datatableheader">
			<td colspan="1"><hdiits:caption captionid="HRMS.SRNO"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.FromDate"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.FromTime"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.ToDate"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.ToTime"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.Accomodation"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.LodingClaim"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.LodingCharge"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.FreeBording"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.FreeLodging"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.CktHouseStay"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.CktHouseStay"
				bundle="${commonLables1}" /></td>
			<td colspan="1"><hdiits:caption captionid="HRMS.Description"
				bundle="${commonLables1}" /></td>
			<td colspan="1">Action</td>
		</tr>
	</table>
	<c:forEach items="${acomDtlsSetKey}" var="acomDtlsSetKeyVar"
		varStatus="x">

		<c:set var="curXMLFileName"
			value="${xmlFilePathNameForMulAdd[x.index]}"></c:set>

		<c:set var="frmDate" value="${acomDtlsSetKeyVar.accomFromDate}" />

		<c:set var="toDate" value="${acomDtlsSetKeyVar.accomToDate}" />

		<c:set var="cmnLookupMst"
			value="${acomDtlsSetKeyVar.cmnLookupMst.lookupDesc}" />

		<c:set var="cktHouseClaimed"
			value="${acomDtlsSetKeyVar.ckthouseClaimed}" />

		<c:set var="cktHousetCharge"
			value="${acomDtlsSetKeyVar.ckthouseCharge}" />

		<c:set var="govBording" value="${acomDtlsSetKeyVar.govBording}" />

		<c:set var="govLodging" value="${acomDtlsSetKeyVar.govLodging}" />

		<c:set var="hotelClaimed" value="${acomDtlsSetKeyVar.hotelClaimed}" />

		<c:set var="hotelCharge" value="${acomDtlsSetKeyVar.hotelCharge}" />

		<c:set var="description" value="${acomDtlsSetKeyVar.description}" />


		<script type="text/javascript">
				var xmlFileName = '${curXMLFileName}';
				var dt=new Date();
				var adtArray=new Array();
				var ddtArray=new Array();
				dt="${frmDate}";
				dtArray=getDateAndTimeFromDateObj(dt);
				dt='${toDate}';
				ddtArray=getDateAndTimeFromDateObj(dt);
				var id='${x.index}'*1+1;
				var displayFieldA  = new Array(id+'&nbsp;',dtArray[0]+'&nbsp;',dtArray[1],ddtArray[0],ddtArray[1],'${cmnLookupMst}'+'&nbsp;','${hotelClaimed}'+'&nbsp;','${hotelCharge}'+'&nbsp;','${govBording}'+'&nbsp;','${govLodging}'+'&nbsp;','${cktHouseClaimed}'+'&nbsp;','${cktHousetCharge}'+'&nbsp;','${description}'+'&nbsp;');
				addDBDataInTable('acomDtls','encXML',displayFieldA,xmlFileName,'','','viewRecord');
		</script>
	</c:forEach>


	<CENTER><hdiits:button name="submitBt" id="submitBt"
		type="button" value="Submit" onclick="submitDtls()" /> <hdiits:button
		name="Close" id="Close" type="button" value="Close"
		onclick="closewindow()" /></CENTER>


	<hdiits:hidden name="xmlKey" />
	<hdiits:hidden name="perAmt" />
	<hdiits:hidden name="arrivalDate" />
	<hdiits:hidden name="departDate" />

	<hdiits:hidden name="journeyId" id="journeyId" default="${journeyId}" />
	<script>
		 
		if('${readOnly}'=='Y'){
			document.getElementById('addData').style.display="none";
			document.getElementById('submitBt').style.display="none";
			document.getElementById('acomType').disabled="true";
			document.getElementById('fromDate').disabled="true";
			document.getElementById('toDate').disabled="true";
			document.getElementById('frmTime').disabled="true";
			document.getElementById('hotelCharge').disabled="true";
			document.getElementById('toTime').disabled="true";
			document.getElementById('description').disabled="true";
			
		}
	</script>
	<hdiits:hidden name="srNo" />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

