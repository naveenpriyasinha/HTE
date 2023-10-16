<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript" src="script/common/ajax_saveData.js"> </script>
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script>
var totalEmployees=0 ;
var nextRow=0;
var employeesAdded = 0;
function checkUncheckAll(theElement)
{
	 var theForm = theElement.form, z = 0;	
	 for(z=0; z<theForm.length;z++)
	 {		 
	      if(theForm[z].type == 'checkbox' && theForm[z].name != 'SelectAll')
		  {
			  theForm[z].checked = theElement.checked;
		  }
     }   
}
function getSchemeforBillGroup()
{
	xmlHttp=GetXmlHttpObject();
	
	if (xmlHttp==null)
	   {
	      return;
	   }  

	 var billGroupId ="";
	 
	 billGroupId = document.getElementById("cmbBillGroup").value;
	 
	 var uri = 'ifms.htm?actionFlag=getSchemeforBillGroup&billGroupId='+ billGroupId ;
	 xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var schemeName = XmlHiddenValues[0].childNodes[0].text;
						var schemeCode = XmlHiddenValues[0].childNodes[1].text;
						document.getElementById("schemeName").value=schemeName;
						document.getElementById("schemeCode").value=schemeCode;
					}
				}
		};
		
	   xmlHttp.open("POST",uri,false);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(uri);
	
}
function getEmpListforContri()
{

	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var schemeName = document.getElementById("schemeName").value;
	var schemeCode =  document.getElementById("schemeCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	self.location.href = "ifms.htm?actionFlag=loadOfflineCorrectionForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
			+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+schemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode ;
}
function changeDpDaAndContri(counter)
{
	var nthElement = counter ; 
	var basic = document.getElementById("basicNew"+nthElement).value ;
	var DP = 0;
	var DA = ( Number(basic * 0.27) ).toFixed(2) ;
	var tempcontribution = Number(basic) + Number(DA) ;
	var contribution = ( Number( tempcontribution * 0.10 )).toFixed(2);
	document.getElementById("DPNew" + nthElement).value = DP ;
	document.getElementById("DANew" + nthElement).value = DA ;
	document.getElementById("contributionNew" + nthElement).value = contribution ;

}
function window_open(val,x,y,afterDateSelect,dateInputParams){
    var newWindow;

    if(afterDateSelect == undefined) {
		afterDateSelect = '';
	}
	glbAfterDateSelect = afterDateSelect;

    var urlstring = "common/calendarDppf.jsp?requestSent=" +val;

    dateChkInputs = dateInputParams;
    var X = window.event.screenX  - window.event.offsetX;
    var Y = window.event.screenY  + (20 - window.event.offsetY);	    

    var urlstyle = 'height=230,width=315,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+Y+',left='+X;

	newWindow = displayModalDialog(urlstring,'Calendar',urlstyle);
}
function AddNewRowContri(dcpsContributionIdValue)
{
	employeesAdded = employeesAdded + 1;

	if(employeesAdded <= 1)
	{
			var dcpsContributionId = dcpsContributionIdValue ;
			xmlHttp=GetXmlHttpObject();
			if (xmlHttp==null)
			{
				alert ("Your browser does not support AJAX!");
				return;
			} 
			uri= 'ifms.htm?actionFlag=getContributionDetails&dcpsContributionId='+dcpsContributionId  ;
			xmlHttp.onreadystatechange=addNewRowOnReadyStateChanged;
			xmlHttp.open("POST",uri,false);
			xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xmlHttp.send(uri);
	}
	else
	{
			alert('You have already added one employee');
			return false;
	}
}
function addNewRowOnReadyStateChanged()
{
	if (xmlHttp.readyState == 4) {
		if (xmlHttp.status == 200) {

			XMLDoc = xmlHttp.responseXML.documentElement;
			var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
			
			var empName =  XmlHiddenValues[0].childNodes[0].text;
			var dcpsId = XmlHiddenValues[0].childNodes[1].text;
			var startDate = XmlHiddenValues[0].childNodes[2].text;
			var endDate = XmlHiddenValues[0].childNodes[3].text;
			var payCommission = XmlHiddenValues[0].childNodes[4].text;
			var typeOfPayment = XmlHiddenValues[0].childNodes[5].text;
			var basic = XmlHiddenValues[0].childNodes[6].text;
			var DP = XmlHiddenValues[0].childNodes[7].text;
			var DA = XmlHiddenValues[0].childNodes[8].text;
			var contribution = XmlHiddenValues[0].childNodes[9].text;
			var dcpsEmpId =  XmlHiddenValues[0].childNodes[10].text;
			var dcpsContributionId = XmlHiddenValues[0].childNodes[11].text;
			
	
						document.getElementById("saveAndDelete").style.display='';
						document.getElementById("contributionAddRowTableDiv").style.display='';
						totalEmployees = totalEmployees+1;
						var table=document.getElementById("contributionAddRowTable");
						nextRow = nextRow + 1 ;
						//alert('Next Row-->'+nextRow);
						
							var newRow = table.insertRow();	
							var Cell1 = newRow.insertCell();
							var Cell2 = newRow.insertCell();
							var Cell3 = newRow.insertCell();
							var Cell4 = newRow.insertCell();
							var Cell5 = newRow.insertCell(); 
							var Cell6 = newRow.insertCell(); 
							var Cell7 = newRow.insertCell(); 
							var Cell8 = newRow.insertCell(); 
							var Cell9 = newRow.insertCell(); 
							var Cell10 = newRow.insertCell(); 
							var Cell11 = newRow.insertCell();
							Cell11.className = "tds";
					 	  	Cell11.align="center";
					  
						
						Cell1.innerHTML = '<input type="text" name="name" id="nameNew'+nextRow+'" value="'+empName+'" />';
						Cell2.innerHTML = '<input type="text" name="dcpsIdNew" id="dcpsIdNew'+nextRow+'" value="'+dcpsId+'" />';
						Cell3.innerHTML = ' <input type="text" size="10" name="txtStartDateNew'+nextRow+'" id="txtStartDateNew'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
										+ '	onBlur="validateDate(this);" value="'+startDate+'" width="10" /> ' 
										+ ' &nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtStartDateNew'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>'  ;
						Cell4.innerHTML = ' <input type="text" size="10" name="txtEndDateNew'+nextRow+'" id="txtEndDateNew'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
										+ '	onBlur="validateDate(this);" value="'+endDate+'" width="10" /> ' 
										+ ' &nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtEndDateNew'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');" style="cursor: pointer;"/>'  ;
						Cell5.innerHTML = ' <select name="cmbPayCommissionNew" id="cmbPayCommissionNew'+nextRow+'" >' 
														+ ' <option value="-1">--Select--</option>' +LISTPAYCOMMISSIONS +'</select>';
						Cell6.innerHTML = ' <select name="cmbTypeOfPaymentNew" id="cmbTypeOfPaymentNew'+nextRow+'" >'
														+ ' <option value="-1">--Select--</option>' +LISTTYPESOFPAYMENT +'</select>';
						Cell7.innerHTML = ' <input type="text" size="10" id="basicNew'+nextRow+'" name="basic" value="'+basic+'" onchange="changeDpDaAndContri('+nextRow+')" ; />' ;
						Cell8.innerHTML = ' <input type="text" size="10" id="DPNew'+nextRow+'" name="DP"  value="'+DP+'"  readonly="readonly" />' ;
						Cell9.innerHTML = ' <input type="text" size="10" id="DANew'+nextRow+'" name="DA"  value="'+DA+'"  readonly="readonly" />' ;
						Cell10.innerHTML = ' <input type="text" size="10" id="contributionNew'+nextRow+'" name="contribution"  value="'+contribution+'"  readonly="readonly" />' ;
						Cell11.innerHTML= ' <input type="checkbox" id="checkboxNew'+nextRow+'" name="checkboxNew" value="'+dcpsContributionId+'" />' 
														+ ' <input type="hidden" name="dcpsempIdNew" id="dcpsempIdNew'+ nextRow+'" value="'+dcpsEmpId+'" />' ;
					
						var lObjectcmbPayCommissionNew = document.getElementById("cmbPayCommissionNew"+nextRow);
						var lValuecmbPayCommission = payCommission;
						setSelectedIndex(lObjectcmbPayCommissionNew,lValuecmbPayCommission) ;
					
						var lObjectcmbTypeOfPaymentNew = document.getElementById("cmbTypeOfPaymentNew"+nextRow);
						var lValuecmbTypeOfPayment = typeOfPayment;
						setSelectedIndex(lObjectcmbTypeOfPaymentNew,lValuecmbTypeOfPayment) ;

		}
	}
}
function setSelectedIndex(s, v) {
    for ( var i = 0; i < s.options.length; i++ ) {
        if ( s.options[i].value == v ) {
            s.options[i].selected = true;
            return;
        }
    }
}

function saveContributionData()
{
	//alert('totalEmployees-->'+totalEmployees);
	var dcpsEmpIds = "" ;
	var cmbPayCommission = "" ;
	var cmbTypeOfPayment = "" ;
	var basic = "" ;
	var DP = "" ;
	var DA = "" ;
	var contribution = "" ;
	var monthId = document.getElementById("cmbMonth").value;
	var yearId = document.getElementById("cmbYear").value;
	var cmbTreasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var schemeCode=document.getElementById("schemeCode").value;
	var dcpsContributionIds="";
	var txtStartDate="";
	var txtEndDate="";
	
	var voucherOrChallan="";
	var txtVCNo="";
	var txtVCDate="";
	var txtBillNo="";
	
   try
   {
	var testCounter=1;
	var totalSelectedEmployees = 0;
	

	for (var i=0; i < document.DCPSOfflineEntryCorrectionForm.voucherOrChallan.length; i++)
	   {
	   if (document.DCPSOfflineEntryCorrectionForm.voucherOrChallan[i].checked)
	      {
		    voucherOrChallan = document.DCPSOfflineEntryCorrectionForm.voucherOrChallan[i].value;
	      }
	   }
		
	txtVCNo =  document.getElementById("txtVCNO").value ;
	txtVCDate = document.getElementById("txtVCDate").value ;
	txtBillNo = document.getElementById("txtBILLNO").value ;
	
	//alert('totalselectedEmployeesInitially-->'+totalselectedEmployees);
	//alert('totalselectedEmployeesAfter-->'+totalselectedEmployees);
	
	for(var k=1;k<=totalEmployees;k++)
	{
		//alert('inside for loop of checkbox');
		if(document.getElementById("checkboxNew"+k).checked)
		{  	
			//alert('checkbox'+k+'th checked');
			totalSelectedEmployees++ ;	}
	}

	for(var i=1;i<=totalSelectedEmployees;i++)
	{
		//alert('inside For');
		if(document.getElementById("checkboxNew"+i).checked)
		{
			//alert('inside outer If');
			if(testCounter==totalSelectedEmployees)
			{
				//alert('inside first inner if');				
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempIdNew"+i).value;
				cmbPayCommission= cmbPayCommission + document.getElementById("cmbPayCommissionNew"+i).value;
				cmbTypeOfPayment= cmbTypeOfPayment + document.getElementById("cmbTypeOfPaymentNew"+i).value;
				basic = basic + document.getElementById("basicNew"+i).value;
				DP = DP + document.getElementById("DPNew"+i).value;
				DA = DA + document.getElementById("DANew"+i).value;
				contribution = contribution + document.getElementById("contributionNew"+i).value;
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkboxNew"+i).value  ;
				txtStartDate = txtStartDate +  document.getElementById("txtStartDateNew"+i).value ;
				txtEndDate = txtEndDate +  document.getElementById("txtEndDateNew"+i).value ;
				testCounter++ ;
			}
			else
			{
				//alert('inside second inner if');	
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempIdNew"+i).value + "~";
				cmbPayCommission= cmbPayCommission + document.getElementById("cmbPayCommissionNew"+i).value + "~";
				cmbTypeOfPayment= cmbTypeOfPayment + document.getElementById("cmbTypeOfPaymentNew"+i).value + "~";
				basic = basic + document.getElementById("basicNew"+i).value + "~";
				DP = DP + document.getElementById("DPNew"+i).value + "~";
				DA = DA + document.getElementById("DANew"+i).value + "~";
				contribution = contribution + document.getElementById("contributionNew"+i).value + "~";
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkboxNew"+i).value + "~" ;
				txtStartDate = txtStartDate +  document.getElementById("txtStartDateNew"+i).value + "~" ;
				txtEndDate = txtEndDate +  document.getElementById("txtEndDateNew"+i).value + "~" ;
				testCounter++ ;
			}
		}
	}	
   }
   catch(e)
   {
   }
	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert(url);
	return;
	} 

	url = 'ifms.htm?actionFlag=saveContributionsCorrections&dcpsEmpIds='+dcpsEmpIds+'&dcpsContributionIds='+dcpsContributionIds+'&txtStartDate='+txtStartDate+'&txtEndDate='+txtEndDate
	+'&cmbPayCommission='+cmbPayCommission+'&cmbTypeOfPayment='+cmbTypeOfPayment+
	'&basic='+basic+'&DP='+DP+'&DA='+DA+'&contribution='+contribution+'&monthId='+monthId+'&yearId='+yearId+'&cmbTreasuryCode='+cmbTreasuryCode+
	'&cmbDDOCode='+cmbDDOCode+'&cmbBillGroup='+cmbBillGroup+'&schemeCode='+schemeCode+ 
	'&voucherOrChallan='+voucherOrChallan+'&txtVCNo='+txtVCNo+'&txtVCDate='+txtVCDate+'&txtBillNo='+txtBillNo ;

	alert('url-->'+url);
	
	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
							alert('Amounts Changed successfully');
							self.location.href = 'ifms.htm?actionFlag=loadOfflineCorrectionForm';
					}
				}
			}
	} ;
xmlHttp.open("POST",url,true);
xmlHttp.send(url);
}


function deleteContributionData()
{
	var testCounter=1;
	var dcpsEmpIds = "" ;
	var dcpsContributionIds="";
	var totalSelectedEmployees =0;

	for(var k=1;k<=totalEmployees;k++)
	{
		//alert('inside for loop of checkbox');
		if(document.getElementById("checkboxNew"+k).checked)
		{  	
			//alert('checkbox'+k+'th checked');
			totalSelectedEmployees++ ;	}
	}
	
	
	for(var i=1;i<=totalSelectedEmployees;i++)
	{
		if(document.getElementById("checkboxNew"+i).checked)
		{
			if(testCounter==totalSelectedEmployees)
			{
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkboxNew"+i).value  ;
				testCounter++ ;
			}
			else
			{
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkboxNew"+i).value + "~" ;
				testCounter++ ;
			}
		}
	}	

	xmlHttp = GetXmlHttpObject();
	if(xmlHttp == null)
	{
	alert(url);
	return;
	} 

	url = 'ifms.htm?actionFlag=deleteContributions&dcpsContributionIds='+dcpsContributionIds ;
	alert(url);

	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
							alert('Employees Deleted Successfully');
							self.location.href = 'ifms.htm?actionFlag=loadOfflineCorrectionForm';
					}
				}
			}
	} ;

	xmlHttp.open("POST",url,true);
	xmlHttp.send(url);
}

</script>

<hdiits:form name="DCPSOfflineEntryCorrectionForm" id="DCPSOfflineEntryCorrectionForm" encType="multipart/form-data"
	validate="true" method="post" >
	
	<fieldset class="tabstyle">
		
		<table align="center" width="100%">
					<tr>
								<td width="20%"><fmt:message
										key="CMN.TreasuryName" bundle="${dcpsLables}"></fmt:message></td>
								<td width="30%">
								   <select name="cmbTreasuryCode" id="cmbTreasuryCode" style="width:240px"  onChange="" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
																					<option value="1001" selected="selected"><c:out 
																							value="TreasuryName"></c:out></option>
										
									    </select>
								  
								</td>
								<td width="20%"><fmt:message
										key="CMN.DDOName" bundle="${dcpsLables}"></fmt:message></td>
								<td width="30%">
										<select name="cmbDDOCode" id="cmbDDOCode" style="width:240px"  onChange="" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
																					<option value="DDO1234567" selected="selected" ><c:out 
																							value="DDOName"></c:out></option>
										
									    </select>
								</td>			
					</tr>
					
					<tr>
								<td width="20%"><fmt:message
										key="CMN.BillGroup" bundle="${dcpsLables}"></fmt:message></td>
								<td width="30%">
										<select name="cmbBillGroup" id="cmbBillGroup" style="width:240px"  onChange="getSchemeforBillGroup();" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
											<c:forEach var="billGroup" items="${resValue.BILLGROUPS}" >
															<c:choose>
																<c:when test="${resValue.lLongbillGroupId == billGroup.id}">
																					<option value="${billGroup.id}" selected="selected"><c:out 
																							value="${billGroup.desc}"></c:out></option>
																</c:when>
																<c:otherwise>
																					<option value="${billGroup.id}"><c:out 
																							value="${billGroup.desc}"></c:out></option>
																</c:otherwise>						
														    </c:choose>
											</c:forEach>
									    </select>
								</td>	
								
								<td width="20%"><fmt:message
										key="CMN.Scheme" bundle="${dcpsLables}"></fmt:message></td>
								<td width="30%"><input type="text" name="schemeName"  id="schemeName" value="${resValue.schemename}" />
								<input type="hidden" name="schemeCode"  id="schemeCode" value="${resValue.schemeCode}" />
								</td>
				   </tr>
					
					<tr>
								<td width="20%"><fmt:message
										key="CMN.Month" bundle="${dcpsLables}"></fmt:message></td>
								
								<td width="30%">
										<select name="cmbMonth" id="cmbMonth" style="width:240px"  onChange="" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
											<c:forEach var="month" items="${resValue.MONTHS}" >
															<c:choose>
																<c:when test="${resValue.monthId == month.id}">
																					<option value="${month.id}" selected="selected"><c:out 
																							value="${month.desc}"></c:out></option>
																</c:when>
																<c:otherwise>
																					<option value="${month.id}"><c:out 
																							value="${month.desc}"></c:out></option>
																</c:otherwise>						
														    </c:choose>
											</c:forEach>
									    </select>
								</td>	
								<td width="20%"><fmt:message
										key="CMN.Year" bundle="${dcpsLables}"></fmt:message></td>
							
								<td width="30%">
										<select name="cmbYear" id="cmbYear" style="width:240px"  onChange="" >
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT"/></option>
											<c:forEach var="year" items="${resValue.YEARS}" >
															<c:choose>
																				<c:when test="${resValue.yearId == year.id}">
																					<option value="${year.id}" selected="selected"><c:out 
																							value="${year.desc}"></c:out></option>
																</c:when>
																<c:otherwise>
																					<option value="${year.id}"><c:out 
																							value="${year.desc}"></c:out></option>
																</c:otherwise>						
														    </c:choose>
											</c:forEach>
									    </select>
								</td>
					</tr>
					
					<tr>
								<td width="20%" id="go" align="center" style="display:block;" colspan="4" ><hdiits:button
															name="btnGo" id="btnGo" type="button"
															captionid="BTN.GO" bundle="${dcpsLables}"
															onclick="getEmpListforContri();" />
								<input type="hidden" id="ddoCode" value="${resValue.DDOCODE}"/>
								</td>
								<td width="20%"></td>
								<td width="30%"></td>
								<td width="30%"></td>
					</tr>
	    </table>		
		</fieldset>
		
		<br/>
		<br/>
		
	<c:if test="${empList!=null}">
	
	<fieldset class="tabstyle">
	<br/>
	<script type="text/javascript">
		LISTPAYCOMMISSIONS='';
		LISTTYPESOFPAYMENT='';
		</script>
		
		<c:forEach var="EventList" items="${resValue.listPayCommission}" >
		<script> LISTPAYCOMMISSIONS += "<option value='${EventList.lookupId}'> ${EventList.lookupDesc}</option>";</script>
		</c:forEach>
		
		<c:forEach var="EventList" items="${resValue.listTypeOfPayment}" >
		<script> LISTTYPESOFPAYMENT += "<option value='${EventList.lookupId}'> ${EventList.lookupDesc}</option>";</script>
		</c:forEach>
		
		
	<div style="width:100%;overflow:auto;height:100%;"  align="center">
		<display:table list="${empList}"  id="contributionTable"   requestURI="" export="" style="width:20%"  pagesize="5">
		<display:setProperty name="paging.banner.placement" value="none" />		
		
				<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;border: none;"   sortable="true" titleKey="CMN.NAME" >
				
						<a href=# onclick="AddNewRowContri('${contributionTable[3]}');"> <label id="AddNewRowContri"><c:out value="${contributionTable[2]}"></c:out></label></a>
						<input type="hidden" name="dcpsempId" id="dcpsempId${contributionTable_rowNum}" value="${contributionTable[0]}"/>
						<input type="hidden" name="dcpsContributionId" id="dcpsContributionId${contributionTable_rowNum}" value="${contributionTable[3]}"/>
						
				</display:column>
				
				
				<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;border: none;"   sortable="true" titleKey="CMN.DCPSID" >
				<label><c:out value="${contributionTable[1]}"></c:out></label>
				</display:column>
				
		</display:table>
		
	</div>
	</fieldset>
	
	<br/>
	<br/>
	
	</c:if>
	
	<div id="contributionAddRowTableDiv" style="display:none">
	
	<fieldset class="tabstyle">
	
	<div style="width:100%;overflow:auto;height:100%;" >
	<display:table list="" id="contributionAddRowTable" requestURI="" export="" style="width:100%,border: thin;" pagesize="5">
	<display:setProperty name="paging.banner.placement" value="bottom" />		
	
			<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;border: none;"   sortable="true" titleKey="CMN.NAME" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;border: none;"   sortable="true" titleKey="CMN.DCPSID" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.CONTRISTARTDATE" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;" sortable="true"  titleKey="CMN.CONTRIENDDATE" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.PAYCOMIMISSION" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.PAYMENTTYPE" >
			</display:column>
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.BASIC" >
			</display:column>
						
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.DP" >  
		    </display:column>
						
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.DA" > 
			</display:column>
					    
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  titleKey="CMN.CONTRIBUTION" >
		    </display:column> 
			
			<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;border: none;"  sortable="true"  title="<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
			</display:column>
	
	</display:table>
	</div>
	</fieldset>
	
	</div>
	
	<br/>
	<br/>
	
	<div id="saveAndDelete" style="display:none">
	
	<fieldset class="tabstyle">
	<table>
			<tr>
			<td width="30%">
			<input type="radio" id="voucherOrChallan" name="voucherOrChallan" value="Voucher" />
			<fmt:message key="CMN.VOUCHER"
						bundle="${dcpsLables}"></fmt:message> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" id="voucherOrChallan" name="voucherOrChallan" value="Challan" />
			<fmt:message key="CMN.CHALLAN"
						bundle="${dcpsLables}"></fmt:message> 
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="40%">&nbsp;</td>
			<td width="30%">&nbsp;</td>
			</tr>
			
			<tr>
			<td width="30%">
			<fmt:message key="CMN.VCNO"
						bundle="${dcpsLables}"></fmt:message> 
			<input type="text" id="txtVCNO"
				size="30" name="txtVCNO" value="" />
			</td>
			<td width="40%" align="right">
			<fmt:message key="CMN.VCDATE"
						bundle="${dcpsLables}"></fmt:message> 
			<input type="text" name="txtVCDate" id="txtVCDate" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
						onBlur="validateDate(this);" value="" /> 
						<img src='images/CalendarImages/ico-calendar.gif' 
						onClick='window_open("txtVCDate",375,570)'
						style="cursor: pointer;" />
			</td>
			
			<td width="30%" align="right">
			<fmt:message key="CMN.BILLNO"
						bundle="${dcpsLables}"></fmt:message> 
			<input type="text" id="txtBILLNO"
				size="30" name="txtBILLNO" value="" />
			</td>
			</tr>
	</table>
	
	</fieldset>
	<br/>
	<br/>
	
	<fieldset class="tabstyle">
	
	 	 <table width="100%">
         	<tr>
		        <td width="10%">
		        </td>
		        <td width="20%">
		        </td>
		        <td width="30%" id="saveBtn" align="center" style="display:block;"><hdiits:button
																	name="btnSaveData" id="btnSaveData" type="button"
																	captionid="BTN.SAVE" bundle="${dcpsLables}"
																	onclick="saveContributionData()" />
										</td>
				<td width="40%" id="deleteEmpBtn" align="center" style="display:block;">
																<hdiits:button
																	name="btnDeleteEmp" id="btnDeleteEmp" type="button"  style="width:30%"  
																	captionid="BTN.DELETEEMP" bundle="${dcpsLables}"
																	onclick="deleteContributionData()" />
				</td>
        	</tr>
        </table>
   </fieldset>
   
   </div>
	
	

	
	
</hdiits:form>