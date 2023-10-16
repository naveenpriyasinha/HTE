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
<c:set var="reqIdList" value="${resultValue.reqIdList}">
</c:set>
<c:set var="payModList" value="${resultValue.payMod}">
</c:set>
<c:set var="travelMstList" value="${resultValue.travelMstList}">
</c:set>


<fmt:setBundle basename="resources.ess.travel.caption"
	var="commonLables" scope="request" />
<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
<script type="text/javascript">
//Global Variable
var amtVar=0;
var reqIdArray=new Array();
var accomIdArray=new Array();
function changeAccomodation(form)
{
	
	var acomid=form.id;
	var subId=acomid.substring(8,acomid.length);
	var name="Acomimg"+subId;
	
	if(form.value=='Y')
	{
		document.getElementById(name).style.display="";
	}else
	{
		document.getElementById(name).style.display="none";
	}
}
function viewAccomodationPage(form)
{
	
	
	var subId=form.id;
	var reqId=subId.substring(7,subId.length);
	var arrDt=document.getElementById('depart_dt'+reqId).value;
	var arrTm=document.getElementById('depart_tm'+reqId).value;
	var arrivalDate=arrDt+"~"+arrTm;
	var Id=reqIdArray[reqId];
	reqId=reqId*1+1;

	var departDt=document.getElementById('arr_dt'+reqId).value;
	var departTm=document.getElementById('arr_tm'+reqId).value;
	var departDate=departDt+"~"+departTm;
	
	
	var urlstyle="hdiits.htm?actionFlag=getAccomPage&AD="+arrivalDate+"&DD="+departDate+"&RequestId="+Id;
	

	window.open(urlstyle,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}
function showTACalculation(form)
{
	
	var urlstyle="hdiits.htm?actionFlag=getTACalculation&tourId="+form.title;
	window.open(urlstyle,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		
}
function closewindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.TravelAdv.action=urlstyle;
		document.TravelAdv.submit();
	}

function submitDtls()
{	
	
	
	var urlstyle="hrms.htm?actionFlag=reimbursementReq";
	document.TravelAdv.action=urlstyle;
	document.TravelAdv.submit();
}

function addTourData(form)
{
	document.getElementById('tripdtls').style.display='';
	var tripdtls=document.getElementById('tripdtls');
	if(form.value!="-1")
	{
		document.getElementById('tripdtls').style.display='';
		clearRows();
		addOrUpdateRecordforTravel('addRecord','getAjaxReimbursmentData',new Array('RequestId'));
		var xmlKey=document.getElementById('xmlKey');
		var response=xmlKey.value;
		amtVar=0;
		tripdtls.style.display="";
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) 
		{
		  alert("<fmt:message key="HRMS.browsernotsupportajax" bundle="${alertLables}" />");    
		  return;
		} 
		var url='hdiits.htm?actionFlag=getXmlContent&xmlFileName=' + response;
		
		xmlHttp.onreadystatechange = function() 
							{
								eval('populateForm()'); 
							}
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);	
		
		var lengthId=accomIdArray.length;
		lengthId=lengthId-1;
		var lastId=accomIdArray[lengthId];
		document.getElementById('accomChk'+lastId).disabled='true';

		document.getElementById('totalAmt').innerHTML=amtVar;
		

	}else
	{
		clearRows();
		document.getElementById('tripdtls').style.display='none';
		tripdtls.style.display="none";
	}
}
function clearRows()
{
		
		var tripdtls=document.getElementById('tripdtls');
		if(tripdtls.rows.length>1)
		{
				for(var i=1;i<=tripdtls.rows.length+1;i++)
				{
					tripdtls.deleteRow(1);
				}
		}
		
}
function addOrUpdateRecordforTravel(methodName, actionFlag, fieldArray) 
{	
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
		 alert("<fmt:message key="HRMS.browsernotsupportajax" bundle="${alertLables}" />");    
		  return;
		} 
		var reqBody = getRequestBody(fieldArray);
	
		var url='hdiits.htm?actionFlag=' + actionFlag + '&' + reqBody;
		methodName = methodName + "()";
		xmlHttp.onreadystatechange=function() { eval(methodName); }	
	
		xmlHttp.open("POST",encodeURI(url),false);
		xmlHttp.send(null);
}	

function addRecord()
{
		if (xmlHttp.readyState == 4) 
		{
			
			var encXML=xmlHttp.responseText;
			var xmlKey=document.getElementById('xmlKey');
			xmlKey.value=encXML;
		}
}

function populateForm()
	{
		 if (xmlHttp.readyState == 4) 
		 { 				  
			  	var decXML = xmlHttp.responseText;
			  	reqIdArray=new Array();
			  	var xmlDom=getDOMFromXML(decXML);
			  	var len=getChildNodeLengnthOfGivenSet(xmlDom,'hrTravelJourneyDtlses');	
			  	document.getElementById('advAmt').value=getXPathValueFromDOM(xmlDom,'totalAmount');
			  	document.getElementById('advAmt').readonly="true";
			  	var tripdtls=document.getElementById('tripdtls');
			  	document.getElementById('totalAmt').title=getXPathValueFromDOM(xmlDom,'tourId');
			  	var str="";
				document.getElementById('totalAmt').title=getXPathValueFromDOM(xmlDom,'tourId');
			  	if(len==2)
			  	{
			  		for(var j=0;j<len;j++)
			  		{
			  			var row=tripdtls.insertRow();
				  		var parentNode='hrTravelJourneyDtlses['+j+']';
						row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelFromPlace/cityName');
						
						str="<input type='text' name='arr_dt"+j+"'  onkeypress='return checkDateNumber()' id='arr_dt"+j+"' class='texttag'  onBlur='departurechkdate(this)' value=''  maxlength=10 size=10/>";
						
						
						row.insertCell(1).innerHTML=str;
					
						row.insertCell(2).innerHTML="<input type='text' mandatory='false' size='1'  name='arr_tm"+j+"' tabindex='8' id='arr_tm"+j+"' />";;		
			
						row.insertCell(3).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelToPlace/cityName');
						
						str="";
						
						str="<input type='text' name='depart_dt"+j+"'  onkeypress='return checkDateNumber()' id='depart_dt"+j+"' class='texttag'  onBlur='checkdate(this)' value=''  maxlength=10 size=10/>";
						
						
						row.insertCell(4).innerHTML=str;

						row.insertCell(5).innerHTML="<input type='text' mandatory='false' size='1'  name='depart_tm"+j+"'  tabindex='8' id='depart_tm"+j+"'/>";
						
						row.insertCell(6).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/cmnLookupMstByTravelMode/lookupDesc');
						
						var mod=getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByTravelMode/lookupId');
						if(mod=="321594" ||mod=="321598")
						{
							row.insertCell(7).innerHTML=getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByAirType/lookupDesc');
						}else if(mod=='321595'||mod=="321599")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByTrainName/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByTravelClass/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}else if (mod=='321596'||mod=="321600")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByGovtOrGovtpaid/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByVehicleType/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}
						
						row.insertCell(8).innerHTML=getXPathValueFromDOM(xmlDom, parentNode+'/distance');
						
						if(getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByPurposeOfStay/lookupDesc')!=null)
						{
							row.insertCell(9).innerHTML=getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByPurposeOfStay/lookupDesc');
						}else
						{
							row.insertCell(9).innerHTML="-";
						}
						
						str="";
						
						var rowId=getXPathValueFromDOM(xmlDom, parentNode+'/tourRowId');
						
						str=str+"<select name='accomChk"+j+"' size='1' caption='drop_down' id='accomChk"+j+"'  mandatory='true' onchange='changeAccomodation(this)'> <option value='-1'>---Select--</option>";
						str=str+"<option value='Y'>Yes</option>";
						str=str+"<option value='N'>No</option>";
						str=str+"</select>";
						str=str+"<img id='Acomimg"+j+"' style='display:none' src='images/CalendarImages/paste.gif' width=20 onClick='viewAccomodationPage(this)'  alt='Click to Add Accomodaion' align='absmiddle'>";
						row.insertCell(10).innerHTML=str;
						reqIdArray[j]=rowId;
						accomIdArray[j]=j;
						
						row.insertCell(11).innerHTML=getXPathValueFromDOM(xmlDom, parentNode+'/costIncurred');
						
						
						
						row.insertCell(12).innerHTML="<textarea rows='3' name='remark"+j+"' id='remark"+j+"' cols='21'/>";
						
						//Date And Time
						var depart_dt=new Date();
						depart_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelToDate');
						
						var arr_dt=new Date();
						arr_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelFromDate');
										
						var arrayDate=new Array();
						arrayDate=getDateAndTimeFromDateObj(arr_dt);
						var name="arr_dt"+j;
						document.getElementById(name).value=arrayDate[0];
						name="arr_tm"+j;
						document.getElementById(name).value=arrayDate[1];
						name="depart_dt"+j;
						arrayDate=getDateAndTimeFromDateObj(depart_dt);
						document.getElementById(name).value=arrayDate[0];
						name="depart_tm"+j;
						document.getElementById(name).value=arrayDate[1];
						name="remark"+j;
						document.getElementById(name).value=getXPathValueFromDOM(xmlDom, parentNode+'/remark');
						
						
						
				  	}
				 }
				 else
				 {
				 		
					 	var row=tripdtls.insertRow();
					  	var outerparentNode='hrTravelJourneyDtlses['+0+']';
					  	
	    				row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,outerparentNode+'/travelFromPlace/cityName');
						
						str="<input type='text' name='arr_dt"+j+"'  onkeypress='return checkDateNumber()' id='arr_dt"+j+"' class='texttag'  onBlur='departurechkdate(this)' value=''  maxlength=10 size=10/>";
						
						
						row.insertCell(1).innerHTML=str;
					
						row.insertCell(2).innerHTML="<input type='text' mandatory='false' size='1'  name='arr_tm"+j+"' tabindex='8' id='arr_tm"+j+"' />";;		
			
						row.insertCell(3).innerHTML=getXPathValueFromDOM(xmlDom,outerparentNode+'/travelToPlace/cityName');
						
						str="";
						
						str="<input type='text' name='depart_dt"+j+"'  onkeypress='return checkDateNumber()' id='depart_dt"+j+"' class='texttag'  onBlur='checkdate(this)' value=''  maxlength=10 size=10/>";
						
						
						row.insertCell(4).innerHTML=str;

						row.insertCell(5).innerHTML="<input type='text' mandatory='false' size='1'  name='depart_tm"+j+"'  tabindex='8' id='depart_tm"+j+"'/>";
						
						row.insertCell(6).innerHTML=getXPathValueFromDOM(xmlDom,outerparentNode+'/cmnLookupMstByTravelMode/lookupDesc');
						
						var mod=getXPathValueFromDOM(xmlDom, outerparentNode+'/cmnLookupMstByTravelMode/lookupId');
						if(mod=="321594" ||mod=="321598")
						{
							row.insertCell(7).innerHTML=getXPathValueFromDOM(xmlDom, outerparentNode+'/cmnLookupMstByAirType/lookupDesc');
						}else if(mod=='321595'||mod=="321599")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, outerparentNode+'/cmnLookupMstByTrainName/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, outerparentNode+'/cmnLookupMstByTravelClass/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}else if (mod=='321596'||mod=="321600")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, outerparentNode+'/cmnLookupMstByGovtOrGovtpaid/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, outerparentNode+'/cmnLookupMstByVehicleType/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}
						
						row.insertCell(8).innerHTML=getXPathValueFromDOM(xmlDom, outerparentNode+'/distance');
						
						if(getXPathValueFromDOM(xmlDom, outerparentNode+'/cmnLookupMstByPurposeOfStay/lookupDesc')!=null)
						{
							row.insertCell(9).innerHTML=getXPathValueFromDOM(xmlDom, outerparentNode+'/cmnLookupMstByPurposeOfStay/lookupDesc');
						}else
						{
							row.insertCell(9).innerHTML="-";
						}
						
						str="";
						
						var rowId=getXPathValueFromDOM(xmlDom, outerparentNode+'/tourRowId');
						str=str+"<select name='accomChk"+j+"' size='1' caption='drop_down' id='accomChk"+j+"'  mandatory='true' onchange='changeAccomodation(this)'> <option value='-1'>---Select--</option>";
						str=str+"<option value='Y'>Yes</option>";
						str=str+"<option value='N'>No</option>";
						str=str+"</select>";
						str=str+"<img id='Acomimg"+rowId+"' style='display:none' src='images/CalendarImages/paste.gif' width=20 onClick='viewAccomodationPage(this)'  alt='Click to Add Accomodaion' align='absmiddle'>";
						row.insertCell(10).innerHTML=str;
						
						accomIdArray[j]=rowId;
						
						row.insertCell(11).innerHTML=getXPathValueFromDOM(xmlDom, outerparentNode+'/costIncurred');
						
						
						
						row.insertCell(12).innerHTML="<textarea rows='3' name='remark"+j+"' id='remark"+j+"' cols='21'/>";
						
						//Date And Time
						var depart_dt=new Date();
						depart_dt=getXPathValueFromDOM(xmlDom, outerparentNode+'/travelToDate');
						
						var arr_dt=new Date();
						arr_dt=getXPathValueFromDOM(xmlDom, outerparentNode+'/travelFromDate');
										
						var arrayDate=new Array();
						arrayDate=getDateAndTimeFromDateObj(arr_dt);
						var name="arr_dt"+j;
						document.getElementById(name).value=arrayDate[0];
						name="arr_tm"+j;
						document.getElementById(name).value=arrayDate[1];
						name="depart_dt"+j;
						arrayDate=getDateAndTimeFromDateObj(depart_dt);
						document.getElementById(name).value=arrayDate[0];
						name="depart_tm"+j;
						document.getElementById(name).value=arrayDate[1];
						name="remark"+j;
						document.getElementById(name).value=getXPathValueFromDOM(xmlDom, outerparentNode+'/remark');
						
						
						for(var i=2;i<len;i++)
						{
							row=tripdtls.insertRow();
					  		var outerparentNode='hrTravelJourneyDtlses['+i+']';
	    					row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelFromPlace/cityName');
						
						str="<input type='text' name='arr_dt"+j+"'  onkeypress='return checkDateNumber()' id='arr_dt"+j+"' class='texttag'  onBlur='departurechkdate(this)' value=''  maxlength=10 size=10/>";
						
						
						row.insertCell(1).innerHTML=str;
					
						row.insertCell(2).innerHTML="<input type='text' mandatory='false' size='1'  name='arr_tm"+j+"' tabindex='8' id='arr_tm"+j+"' />";;		
			
						row.insertCell(3).innerHTML=getXPathValueFromDOM(xmlDom,outerouterparentNode+'/travelToPlace/cityName');
						
						str="";
						
						str="<input type='text' name='depart_dt"+j+"'  onkeypress='return checkDateNumber()' id='depart_dt"+j+"' class='texttag'  onBlur='checkdate(this)' value=''  maxlength=10 size=10/>";
						
						
						row.insertCell(4).innerHTML=str;

						row.insertCell(5).innerHTML="<input type='text' mandatory='false' size='1'  name='depart_tm"+j+"'  tabindex='8' id='depart_tm"+j+"'/>";
						
						row.insertCell(6).innerHTML=getXPathValueFromDOM(xmlDom,outerouterparentNode+'/cmnLookupMstByTravelMode/lookupDesc');
						
						var mod=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByTravelMode/lookupId');
						if(mod=="321594" ||mod=="321598")
						{
							row.insertCell(7).innerHTML=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByAirType/lookupDesc');
						}else if(mod=='321595'||mod=="321599")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByTrainName/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByTravelClass/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}else if (mod=='321596'||mod=="321600")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByGovtOrGovtpaid/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByVehicleType/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}
						
						row.insertCell(8).innerHTML=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/distance');
						
						if(getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByPurposeOfStay/lookupDesc')!=null)
						{
							row.insertCell(9).innerHTML=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByPurposeOfStay/lookupDesc');
						}else
						{
							row.insertCell(9).innerHTML="-";
						}
						
						str="";
						
						var rowId=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/tourRowId');
						str=str+"<select name='accomChk"+j+"' size='1' caption='drop_down' id='accomChk"+j+"'  mandatory='true' onchange='changeAccomodation(this)'> <option value='-1'>---Select--</option>";
						str=str+"<option value='Y'>Yes</option>";
						str=str+"<option value='N'>No</option>";
						str=str+"</select>";
						str=str+"<img id='Acomimg"+rowId+"' style='display:none' src='images/CalendarImages/paste.gif' width=20 onClick='viewAccomodationPage(this)'  alt='Click to Add Accomodaion' align='absmiddle'>";
						row.insertCell(10).innerHTML=str;
						
						accomIdArray[j]=rowId;
						
						row.insertCell(11).innerHTML=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/costIncurred');
						
						
						
						row.insertCell(12).innerHTML="<textarea rows='3' name='remark"+j+"' id='remark"+j+"' cols='21'/>";
						
						//Date And Time
						var depart_dt=new Date();
						depart_dt=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/travelToDate');
						
						var arr_dt=new Date();
						arr_dt=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/travelFromDate');
										
						var arrayDate=new Array();
						arrayDate=getDateAndTimeFromDateObj(arr_dt);
						var name="arr_dt"+j;
						document.getElementById(name).value=arrayDate[0];
						name="arr_tm"+j;
						document.getElementById(name).value=arrayDate[1];
						name="depart_dt"+j;
						arrayDate=getDateAndTimeFromDateObj(depart_dt);
						document.getElementById(name).value=arrayDate[0];
						name="depart_tm"+j;
						document.getElementById(name).value=arrayDate[1];
						name="remark"+j;
						document.getElementById(name).value=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/remark');
						
							
						}
						row=tripdtls.insertRow();
					  	outerouterouterouterparentNode='hrTravelJourneyDtlses['+1+']';
					  	
	    				row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,outerouterparentNode+'/travelFromPlace/cityName');
						
						str="<input type='text' name='arr_dt"+j+"'  onkeypress='return checkDateNumber()' id='arr_dt"+j+"' class='texttag'  onBlur='departurechkdate(this)' value=''  maxlength=10 size=10/>";
						
						
						row.insertCell(1).innerHTML=str;
					
						row.insertCell(2).innerHTML="<input type='text' mandatory='false' size='1'  name='arr_tm"+j+"' tabindex='8' id='arr_tm"+j+"' />";;		
			
						row.insertCell(3).innerHTML=getXPathValueFromDOM(xmlDom,outerouterparentNode+'/travelToPlace/cityName');
						
						str="";
						
						str="<input type='text' name='depart_dt"+j+"'  onkeypress='return checkDateNumber()' id='depart_dt"+j+"' class='texttag'  onBlur='checkdate(this)' value=''  maxlength=10 size=10/>";
						
						
						row.insertCell(4).innerHTML=str;

						row.insertCell(5).innerHTML="<input type='text' mandatory='false' size='1'  name='depart_tm"+j+"'  tabindex='8' id='depart_tm"+j+"'/>";
						
						row.insertCell(6).innerHTML=getXPathValueFromDOM(xmlDom,outerouterparentNode+'/cmnLookupMstByTravelMode/lookupDesc');
						
						var mod=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByTravelMode/lookupId');
						if(mod=="321594" ||mod=="321598")
						{
							row.insertCell(7).innerHTML=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByAirType/lookupDesc');
						}else if(mod=='321595'||mod=="321599")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByTrainName/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByTravelClass/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}else if (mod=='321596'||mod=="321600")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByGovtOrGovtpaid/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByVehicleType/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}
						
						row.insertCell(8).innerHTML=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/distance');
						
						if(getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByPurposeOfStay/lookupDesc')!=null)
						{
							row.insertCell(9).innerHTML=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/cmnLookupMstByPurposeOfStay/lookupDesc');
						}else
						{
							row.insertCell(9).innerHTML="-";
						}
						
						str="";
						
						var rowId=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/tourRowId');
						str=str+"<select name='accomChk"+j+"' size='1' caption='drop_down' id='accomChk"+j+"'  mandatory='true' onchange='changeAccomodation(this)'> <option value='-1'>---Select--</option>";
						str=str+"<option value='Y'>Yes</option>";
						str=str+"<option value='N'>No</option>";
						str=str+"</select>";
						str=str+"<img id='Acomimg"+rowId+"' style='display:none' src='images/CalendarImages/paste.gif' width=20 onClick='viewAccomodationPage(this)'  alt='Click to Add Accomodaion' align='absmiddle'>";
						row.insertCell(10).innerHTML=str;
						
						accomIdArray[j]=rowId;
						
						row.insertCell(11).innerHTML=getXPathValueFromDOM(xmlDom, parentNode+'/costIncurred');
						
						
						
						row.insertCell(12).innerHTML="<textarea rows='3' name='remark"+j+"' id='remark"+j+"' cols='21'/>";
						
						//Date And Time
						var depart_dt=new Date();
						depart_dt=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/travelToDate');
						
						var arr_dt=new Date();
						arr_dt=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/travelFromDate');
										
						var arrayDate=new Array();
						arrayDate=getDateAndTimeFromDateObj(arr_dt);
						var name="arr_dt"+j;
						document.getElementById(name).value=arrayDate[0];
						name="arr_tm"+j;
						document.getElementById(name).value=arrayDate[1];
						name="depart_dt"+j;
						arrayDate=getDateAndTimeFromDateObj(depart_dt);
						document.getElementById(name).value=arrayDate[0];
						name="depart_tm"+j;
						document.getElementById(name).value=arrayDate[1];
						name="remark"+j;
						document.getElementById(name).value=getXPathValueFromDOM(xmlDom, outerouterparentNode+'/remark');
						
						
				 }
				
				//document.getElementById('totalAmt').innerHTML=getXPathValueFromDOM(xmlDom,'totalAmount');
			  	amtVar=amtVar+getXPathValueFromDOM(xmlDom,'totalAmount')*1;
		 }	  	
	}
</script>


<hdiits:form name="TravelAdv" validate="true" method="POST"
			encType="multipart/form-data">
	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="HRMS.Reimbursment" bundle="${commonLables}" /> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0"><hdiits:hidden
		name="wffileId_hidden" id="wffileId_hidden" /> 
	<c:if test="${empty travelMstList}">
	<br><br><br><br><br><br><br><br><br><br><br><br>
		<center><font color="RED"><hdiits:caption
			captionid="HRMS.NoRecord" bundle="${commonLables}" /></font></center>
	</c:if>	
	<tr>

	<c:if test="${not empty travelMstList}">	

	
		
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
	<div style="width: 100%;height: 100%;overflow:scroll">
	<table class="tabhdiits:table" bordercolor="#386CB7" width="100%" height="100%" align="center">
	<tr bgcolor="#386CB7" height="20" align="center">
		<td class="fieldLabel" colspan="4"><font color="#ffffff">
			<u><fmt:message key="HRMS.Reimbursment" bundle="${commonLables}" /></u>
			</font>
		</td>
	
	</tr>

	<tr>
		<td align="right"><b><fmt:message key="TOUR.ID"
								bundle="${commonLables}" /></b>
		</td>
		<td align="left"><hdiits:select name="RequestId" size="1" caption="RequestId" id="RequestId" onchange="addTourData(this)" tabindex="2" style="">
					<hdiits:option value="-1">------Select -----</hdiits:option>
					<c:forEach var="MstList" items="${travelMstList}">
								<hdiits:option value="${MstList.tourId}">
													${MstList.tourName}
								</hdiits:option>
   					</c:forEach>
			</hdiits:select>
		</td>
	</tr>
	

<tr align="center">
<td colspan="2">


	<table id='tripdtls' border="1" cellpadding="0"
								cellspacing="0" BGCOLOR="WHITE" style="display: none">
								
								<tr class="datatableheader">
									<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Departure_Place" bundle="${commonLables}" /></td>
									<td style="width: 15%" colspan="1"><hdiits:caption captionid="HRMS.Departure_Date" bundle="${commonLables}" /></td>
									<td style="width: 15%" colspan="1"><hdiits:caption captionid="HRMS.DepartureTime" bundle="${commonLables}" /></td>
									<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Arrival_Place" bundle="${commonLables}" /></td>
									<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Arrival_Date" bundle="${commonLables}" /></td>
									<td style="width: 15%" colspan="1"><hdiits:caption captionid="HRMS.ArrivalTime" bundle="${commonLables}" /></td>
									<td colspan="1"><hdiits:caption captionid="HRMS.Conveyance_Mode" bundle="${commonLables}" /> </td>
									<td colspan="1"><hdiits:caption captionid="HRMS.Sub_Items" bundle="${commonLables}" /></td>
									<td colspan="1"><hdiits:caption captionid="HRMS.Distance" bundle="${commonLables}" /></td>
									<td colspan="1"><hdiits:caption captionid="HRMS.Purose_Of_Stay" bundle="${commonLables}" /></td>
									<td colspan="1"><hdiits:caption captionid="HRMS.Accomodation" bundle="${commonLables}" /></td>
									<td colspan="1"><hdiits:caption captionid="HRMS.Total_Cost" bundle="${commonLables}" /></td>
									<td colspan="1"><hdiits:caption captionid="HRMS.Remark" bundle="${commonLables}" /></td>
									
								</tr>
	</table>
	
</td>
</tr>
<tr >
	<td align="right"><b><hdiits:caption captionid="RECEIVABLE.AALOW" bundle="${commonLables}" /></b></td>
	<td align="left"><font color="RED"> <label id="totalAmt"  onclick="showTACalculation(this)"></label> </font> </td>
</tr>
<tr>
	<td align="right"><b><hdiits:caption captionid="HRMS.AdvanceTaken" bundle="${commonLables}" /></b></td>
	<td align="left"><hdiits:text name="advAmt" id="advAmt" /> </td>
</tr>



<tr>
	<td align="right"><b><hdiits:caption captionid="HRMS.Remark" bundle="${commonLables}" /></b></td>
	<td align="left"><hdiits:textarea name="remark" id="remark" rows="5" /> </td>
	
</tr>
<tr style="display: none">
	<td align="right"><b><hdiits:caption captionid="MODE.PAYMENT" bundle="${commonLables}" /></b></td>
	<td align="left">
		<hdiits:select name="paymod" id="paymod" > 
			<hdiits:option value="-1">------Select---------</hdiits:option>
			<c:forEach var="payMod" items="${payModList}">
			<hdiits:option value="${payMod.lookupId}">
							${payMod.lookupDesc}
			</hdiits:option>
		</c:forEach>
		</hdiits:select> 
	</td>
</tr>

<tr align="center">
		<table border="1" id="attachtb" align="center">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
				<jsp:param name="attachmentName" value="TravelAttachment" />
				<jsp:param name="formName" value="TravelAdv" />
				<jsp:param name="attachmentType" value="Document" />
				<jsp:param name="attachmentTitle" value="Attachment" />
				<jsp:param name="multiple" value="Y" />
				<jsp:param name="rowNumber" value="1" />
				<jsp:param name="removeAttachmentFromDB" value="Y" />
			</jsp:include></td>
		</tr>				
		</table>
	
</tr>

</table>
	
<center> 
	<hdiits:button name="submitBt" value="Submit" type="button"  onclick="submitDtls()" />
	<hdiits:button name="Close" type="button" value="Close"  onclick="closewindow()"  />
</center>

</div>
</c:if>	
		<hdiits:hidden name="xmlKey"  />
		<hdiits:hidden name="perAmt"  />
		<hdiits:hidden name="journeyId"  />
</div>
</div>

<script type="text/javascript">		
		initializetabcontent("maintab");
	
		</script>
	
</hdiits:form>
		


<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>






