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
<c:set var="hrTravelMstList" value="${resultValue.hrTravelMstList}">
</c:set>
<c:set var="hrTravelJourneyDtlsList" value="${resultValue.hrTravelJourneyDtlses}">
</c:set>
<c:set var="advAmount" value="${resultValue.advAmount}">
</c:set>
<c:set var="remReqId" value="${resultValue.remReqId}">
</c:set>
<c:set var="tourId" value="${resultValue.tourId}">
</c:set>


<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />
<fmt:setBundle basename="resources.ess.travel.caption" var="commonLables"
	scope="request" />

<script type="text/javascript">
//Global Variable
var amtVar=0;
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
	var urlstyle="hdiits.htm?actionFlag=getAccomPage&RequestId="+reqId;
	
	window.open(urlstyle,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
	
}
function showTACalculation(form)
{
	
	var urlstyle="hdiits.htm?actionFlag=getTACalculation&tourId="+form.title;
	window.open(urlstyle,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		
}
function forwardAdvReq()
{
	var urlstyle="hdiits.htm?actionFlag=forwardRmbrmsReq";
	document.TravelAdv.action=urlstyle;
	document.TravelAdv.submit();
}
function approveAdvReq()
{
	var urlstyle="hdiits.htm?actionFlag=approveRmbrmsReq";
	document.TravelAdv.action=urlstyle;
	document.TravelAdv.submit();
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
function resetForm()
			  {
			  	if(confirm('<fmt:message  bundle="${alertLables}" key="alertLables"/>') == true)
			  	{
			  		document.forms[0].reset();
			  	}
			  				  	
			  }
function addTourData(form)
{
	
	var tripdtls=document.getElementById('tripdtls');
	if(form.value!="-1")
	{

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
		
		
		document.getElementById('totalAmt').innerHTML=amtVar;
		

	}else
	{
		clearRows();
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
			  	
			  	var xmlDom=getDOMFromXML(decXML);
			  	var len=getChildNodeLengnthOfGivenSet(xmlDom,'hrTravelJourneyDtlses');	
			  	document.getElementById('advAmt').value=getXPathValueFromDOM(xmlDom,'totalAmount');
			  	document.getElementById('advAmt').readonly="true";
			  	var tripdtls=document.getElementById('tripdtls');
			  	document.getElementById('totalAmt').title=getXPathValueFromDOM(xmlDom,'tourId');
			  	var str="";
			  	if(len==2)
			  	{
			  		for(var j=0;j<len;j++)
			  		{
			  			var row=tripdtls.insertRow();
				  		var parentNode='hrTravelJourneyDtlses['+j+']';
						row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelFromPlace/cityName');
						
						str="<input type='text' mandatory='false' maxlength=10 size=10 name='arr_dt"+j+"' tabindex='8' id='arr_dt"+j+"' onkeypress='return checkDateNumber()' class='dateTimetag' onblur='departurechkdate(this)' value=''  />";
						str=str+"<img src='images/CalendarImages/ico-calendar.gif'	width=20 onClick=window_open('arr_dt"+j+"',375,570)  alt='Click to View Calendar'	align='absmiddle'>";
						row.insertCell(1).innerHTML=str;
					
						row.insertCell(2).innerHTML="<input type='text' mandatory='false' size='1'  name='arr_tm"+j+"' tabindex='8' id='arr_tm"+j+"' />";;		
			
						row.insertCell(3).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelToPlace/cityName');
						
						str="";
						str=str+"<input type='text' mandatory='false' maxlength=10 size=10 name='depart_dt"+j+"' tabindex='8' id='depart_dt"+j+"' onkeypress='return checkDateNumber()' class='dateTimetag' onblur='checkdate(this)' value='' />";
						str=str+"<img src='images/CalendarImages/ico-calendar.gif'	width=20 onClick=window_open('depart_dt"+j+"',375,570)  alt='Click to View Calendar'	align='absmiddle'>";
						row.insertCell(4).innerHTML=str;

						row.insertCell(5).innerHTML="<input type='text' mandatory='false' size='1'  name='depart_tm"+j+"'  tabindex='8' id='depart_tm"+j+"'/>";
						
						row.insertCell(6).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/cmnLookupMstByTravelMode/lookupDesc');
						
						var mod=getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByTravelMode/lookupId');
						if(mod=="500002" ||mod=="500022")
						{
							row.insertCell(7).innerHTML=getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByAirType/lookupDesc');
						}else if(mod=='500003'||mod=="500033")
						{
							str="";
							str=getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByTrainName/lookupDesc')+"-"+getXPathValueFromDOM(xmlDom, parentNode+'/cmnLookupMstByTravelClass/lookupDesc');
							row.insertCell(7).innerHTML=str;
						}else if (mod=='500004'||mod=="500044")
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
						str=str+"<select name='accomChk"+rowId+"' size='1' caption='drop_down' id='accomChk"+rowId+"'  mandatory='true' onchange='changeAccomodation(this)'> <option value='-1'>---Select--</option>";
						str=str+"<option value='Y'>Yes</option>";
						str=str+"<option value='N'>No</option>";
						str=str+"</select>";
						str=str+"<img id='Acomimg"+rowId+"' style='display:none' src='images/CalendarImages/paste.gif' width=20 onClick='viewAccomodationPage(this)'  alt='Click to Add Accomodaion' align='absmiddle'>";
						row.insertCell(10).innerHTML=str;
						
						
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
	    				row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelFromPlace/cityName');
						var arr_dt=new Date();
						arr_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelFromDate');
						row.insertCell(1).innerHTML=arr_dt;
						row.insertCell(2).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelToPlace/cityName');
						var depart_dt=new Date();
						depart_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelToDate');
						row.insertCell(3).innerHTML=depart_dt;
						
						for(var i=2;i<len;i++)
						{
							row=tripdtls.insertRow();
					  		var parentNode='hrTravelJourneyDtlses['+i+']';
	    					row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelFromPlace/cityName');
							var arr_dt=new Date();
							arr_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelFromDate');
							row.insertCell(1).innerHTML=arr_dt;
							row.insertCell(2).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelToPlace/cityName');
							var depart_dt=new Date();
							depart_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelToDate');
							row.insertCell(3).innerHTML=depart_dt;
							
						}
						row=tripdtls.insertRow();
					  	outerparentNode='hrTravelJourneyDtlses['+1+']';
	    				row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelFromPlace/cityName');
						var arr_dt=new Date();
						arr_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelFromDate');
						row.insertCell(1).innerHTML=arr_dt;
						row.insertCell(2).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelToPlace/cityName');
						var depart_dt=new Date();
						depart_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelToDate');
						row.insertCell(3).innerHTML=depart_dt;
						
				 }
				
				
				
				
				//document.getElementById('totalAmt').innerHTML=getXPathValueFromDOM(xmlDom,'totalAmount');
				
			  	amtVar=amtVar+getXPathValueFromDOM(xmlDom,'totalAmount')*1;
		 }	  	
	}
</script>
<br>
<br>

<hdiits:form name="TravelAdv" validate="true" method="POST"
			encType="multipart/form-data">
		
	<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
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
		<td align="left"><hdiits:select name="RequestId" size="1" caption="RequestId" id="RequestId"  tabindex="2" style="" readonly="true">
					<c:forEach var="MstList" items="${hrTravelMstList}">
								<hdiits:option value="${MstList.tourId}">
													${MstList.tourName}
								</hdiits:option>
   					</c:forEach>
			</hdiits:select>
		</td>
	</tr>
	
<tr>
	<td align="right"><b> Advance Amount Taken</b></td>
	<td align="left"><hdiits:text name="advAmt" id="advAmt" default="${advAmount}"	 /> </td>
</tr>
<tr align="center">
<td colspan="2">
<table id='tripdtls' border="1" cellpadding="0" width="68%"
							cellspacing="0" BGCOLOR="WHITE"
							style="background: #eeeeee;padding: 2px;">
							<tr class="datatableheader">
								<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Departure_Place" bundle="${commonLables}" /></td>
								<td style="width: 15%" colspan="1">Departure Date And Time</td>
								<td style="width: 25%" colspan="1">Arrival Place</td>
								<td style="width: 25%" colspan="1">Arrival Date And Time</td>
								<td colspan="1">Conveyance Mode </td>
								<td colspan="1">Purpose of Stay</td>
								<td colspan="1">Remark</td>
							</tr>
							<c:forEach var="dtlsList" items="${hrTravelJourneyDtlsList}">
							<tr>
							<td>${dtlsList.travelFromPlace.cityName} &nbsp;</td>
							<td>
								<fmt:parseDate var="rday" pattern="yyyy-MM-dd" value="${dtlsList.travelFromDate} "/>
								<fmt:formatDate value="${rday}"  pattern="dd-MM-yyyy"/>
							</td>
							<td>${dtlsList.travelFromPlace.cityName} &nbsp;</td>
							<td>
								<fmt:parseDate var="rday" pattern="yyyy-MM-dd" value="${dtlsList.travelToDate}"/>
								<fmt:formatDate value="${rday}"  pattern="dd-MM-yyyy"/>
							</td>
							<td>${dtlsList.cmnLookupMstByTravelMode.lookupDesc} &nbsp;</td>		
							<td>${dtlsList.cmnLookupMstByPurposeOfStay.lookupDesc} &nbsp;</td>	
							<td>${dtlsList.remark} &nbsp;</td>	
							</tr>
							</c:forEach>		
</table>
</td>
</tr>
<tr>
	<td align="right"><b> Total Receivable Amount(in Rs.)</b></td>
	<td align="left"><font color="RED"> <label id="totalAmt"  onclick="showTACalculation(this)"></label> </font> </td>
</tr>

<tr style="display: none">
	<td align="right"><b> Mode Of Payment</b></td>
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

<tr>
<td colspan="3">

	<table border="1" id="attachtb">
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
</td>

</tr>
</table>
<center>
	<b> Remark</b>
	<hdiits:textarea name="remark" id="remark" rows="5" />
</center>
<br>
<br>

<center>
	<input type="button" name="approveBt" value="Approve" onclick="approveAdvReq()" style="display:none">
	<input type="button" name="forwardBt" value="Forward" onclick="forwardAdvReq()" style="display:none"><input type="button" name="Close" value="close" onclick="closewindow()" style="display:none"> 
</center>


		<hdiits:hidden name="xmlKey"  />
		<hdiits:hidden name="perAmt"  />
		
		<hdiits:hidden name="remReqIdHid"  />
		
</hdiits:form>
<script>
//alert();
	document.getElementById('totalAmt').title="${tourId}";
	document.getElementById('totalAmt').innerHTML="${advAmount}";
	document.getElementById('remReqIdHid').value="${remReqId}"
</script>
<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>






