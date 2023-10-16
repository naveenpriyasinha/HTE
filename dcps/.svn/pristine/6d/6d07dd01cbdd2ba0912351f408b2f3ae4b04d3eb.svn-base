
<%
try {
%>
<%@ include file="../../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<script type="text/javascript" src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>

<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
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
	var="commonLables1" scope="request" />
<fmt:setBundle basename="resources.ess.travel.AlertMessages"
	var="alertLables" scope="request" />

<script type="text/javascript">
//Global Variable
var amtVar=0;
function showTACalculation(form)
{

	var urlstyle="hdiits.htm?actionFlag=getTACalculation&tourId="+form.title;
	window.open(urlstyle,'chield', 'width=940,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		
}
function closewindow()
	{
		var urlstyle="hdiits.htm?actionFlag=getHomePage";
		document.TravelAdv.action=urlstyle;
		document.TravelAdv.submit();
	}

function submitDtls()
{
	var amt=document.getElementById('totalAmt').innerHTML;
	//alert('Amount :::'+amt); 
	document.getElementById('perAmt').value=amt;
 	roundNumber(); 
	var advAmt=document.getElementById('advAmt').value;
	
	if(document.getElementById('RequestId').value=="-1")
	{
		
		alert("<fmt:message key="HRMS.AMOUNT" bundle="${alertLables}" />");
		return;
	}
	if(advAmt=="")
	{
		alert("<fmt:message key="HRMS.enteramount" bundle="${alertLables}" />");    
		return;
	}else if(isNaN(advAmt))
	{
		alert("<fmt:message key="HRMS.enteramount" bundle="${alertLables}" />");    
		return;
	}else 
	{
		var finalAmt=amt*0.80;
		if(finalAmt<advAmt)
		{
			alert("<fmt:message key="HRMS.amount60recieveamt" bundle="${alertLables}" />");    
			return;
		}
	}
	if(document.getElementById('paymod').value=="-1")
	{
		alert("<fmt:message key="HRMS.selectpaymentmode" bundle="${alertLables}" />");    		
	}
 
	var urlstyle="hrms.htm?actionFlag=submitAdvReq";
	document.TravelAdv.action=urlstyle;
	document.TravelAdv.submit();
}
function resetForm()
			  {
			  	if(confirm('<fmt:message  bundle="${alertLables}" key="ConfirmReset"/>') == true)
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
		addOrUpdateRecordforTravel('addRecord','getAjaxTourData',new Array('RequestId'));
		var xmlKey=document.getElementById('xmlKey');
		var response=xmlKey.value;
		amtVar=0;
		tripdtls.style.display="";
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) 
		{
		  alert("<fmt:message key="HRMS.browsernotsupportajax" bundle="${commonLables1}" />");    
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
		document.getElementById('totalAmt').innerHTML="";
		document.getElementById('advAmt').value="";
		
}
function addOrUpdateRecordforTravel(methodName, actionFlag, fieldArray) 
{	
		xmlHttp=GetXmlHttpObject();
		if (xmlHttp==null) {
 		  alert("<fmt:message key="HRMS.browsernotsupportajax" bundle="${commonLables1}" />");    
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
function roundNumber() {

	var numberField = document.getElementById('advAmt'); // Field where the number appears
	var tmpNum=numberField.value*1;
	numberField.value = tmpNum.toFixed(2);
	 

}

function populateForm()
	{
		 if (xmlHttp.readyState == 4) 
		 { 				  
			  	var decXML = xmlHttp.responseText;
			  	
			  	var xmlDom=getDOMFromXML(decXML);
			  	var len=getChildNodeLengnthOfGivenSet(xmlDom,'hrTravelJourneyDtlses');	
			  	document.getElementById('advAmt').value=getXPathValueFromDOM(xmlDom,'totalAmount')*0.80;
			  	roundNumber();
			  	var tripdtls=document.getElementById('tripdtls');
			  	document.getElementById('totalAmt').title=getXPathValueFromDOM(xmlDom,'tourId');
			  	if(len==2)
			  	{
			  		for(var j=0;j<len;j++)
			  		{
			  			var row=tripdtls.insertRow();
				  		var parentNode='hrTravelJourneyDtlses['+j+']';
						row.insertCell(0).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelFromPlace/cityName');
						var arr_dt=new Date();
						arr_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelFromDate');
						var arrivalDate=getDateAndTimeFromDateObj(arr_dt);
						row.insertCell(1).innerHTML=arrivalDate[0];
						row.insertCell(2).innerHTML=getXPathValueFromDOM(xmlDom,parentNode+'/travelToPlace/cityName');
						var depart_dt=new Date();
						depart_dt=getXPathValueFromDOM(xmlDom, parentNode+'/travelToDate');
						var departureDate=getDateAndTimeFromDateObj(depart_dt);
						row.insertCell(3).innerHTML=departureDate[0];
						
						
						
					
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
				
				
				
				
			
			  	amtVar=amtVar+getXPathValueFromDOM(xmlDom,'totalAmount')*1;
		 }	  	
	}
</script>
<br>
<br>

<hdiits:form name="TravelAdv" validate="true" method="POST"
	encType="multipart/form-data">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
			captionid="TRAVEL.ADVANCE" bundle="${commonLables1}" /> </a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">
	<div id="tcontent1" class="tabcontent" tabno="0"><c:if
		test="${empty travelMstList}">
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<center><font color="RED"><hdiits:caption
			captionid="HRMS.NoRecord" bundle="${commonLables1}" />  </font></center>
	</c:if>
	<tr>

		<c:if test="${not empty travelMstList}">

			<%@ include file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%>
			<table class="tabhdiits:table" bordercolor="#386CB7" width="100%">
				<tr bgcolor="#386CB7" height="20" align="center">
					<td class="fieldLabel" colspan="4"><font color="#ffffff">
					<u><fmt:message key="TRAVEL.ADVANCE" bundle="${commonLables1}" /></u>
					</font></td>

				</tr>

				<tr>
					<td align="right"><b><fmt:message key="TOUR.ID"
						bundle="${commonLables1}" /></b></td>
					<td align="left"><hdiits:select name="RequestId" size="1"
						caption="RequestId" id="RequestId" onchange="addTourData(this)"
						tabindex="2" style="">
						<hdiits:option value="-1">------Select -----</hdiits:option>
						<c:forEach var="MstList" items="${travelMstList}">
							<hdiits:option value="${MstList.tourId}">
													${MstList.tourName}
								</hdiits:option>
						</c:forEach>
					</hdiits:select></td>
				</tr>


				<tr align="center">
					<td colspan="2">
					<table id='tripdtls' border="1" cellpadding="0" width="68%"
						cellspacing="0" BGCOLOR="WHITE"
						style="background: #eeeeee;padding: 2px;display: none">
						<tr class="datatableheader">
							<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Departure_Place" bundle="${commonLables1}" /> </td>
							<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Departure_Date" bundle="${commonLables1}" /></td>
							<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Arrival_Place" bundle="${commonLables1}" /></td>
							<td style="width: 25%" colspan="1"><hdiits:caption captionid="HRMS.Arrival_Date" bundle="${commonLables1}" /></td>
						</tr>
					</table>
					</td>
				</tr>

				<tr>
					<td align="right"><b> <hdiits:caption captionid="RECEIVABLE.AALOW" bundle="${commonLables1}" /></b></td>
					<td align="left"><font color="RED"> <label
						id="totalAmt" onclick="showTACalculation(this)"></label> </font></td>
				</tr>
				<tr>
					<td align="right"><b>  <hdiits:caption captionid="ADVANCE.AMT" bundle="${commonLables1}" /></b></td>
					<td align="left"><hdiits:text name="advAmt" id="advAmt" /></td>
				</tr>
				<tr>
					<td align="right"><b> <hdiits:caption captionid="MODE.PAYMENT" bundle="${commonLables1}" /></b></td>
					<td align="left"><hdiits:select name="paymod" id="paymod">
						<hdiits:option value="-1">------Select---------</hdiits:option>
						<c:forEach var="payMod" items="${payModList}">
							<hdiits:option value="${payMod.lookupId}">
							${payMod.lookupDesc}
			</hdiits:option>
						</c:forEach>
					</hdiits:select></td>
				</tr>

				<tr>
					<td align="right"><b><hdiits:caption captionid="HRMS.Remark" bundle="${commonLables1}" /></b></td>
					<td align="left"><hdiits:textarea name="remark" id="remark"
						rows="5" /></td>
				</tr>
				<tr>
					<td align="right"><hdiits:button type="button" name="submitBt"
						value="Submit" onclick="submitDtls()" /></td>
					<td align="left"><hdiits:button type="button" name="Close"
						value="close" onclick="closewindow()" /></td>
				</tr>

			</table>
		</c:if>
	</div>
	</div>

	<script type="text/javascript">		
		initializetabcontent("maintab");
	
		</script>


	<hdiits:hidden name="xmlKey" />
	<hdiits:hidden name="perAmt" />

</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>






