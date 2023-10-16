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
<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables"scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="empList" value="${resValue.empList}"></c:set>
<c:set var="UserList" value="${resValue.UserList}"/>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<c:if test="${resValue.EditForm != null && resValue.EditForm == 'N'}">
	<c:set var="varDisabled" scope="page" value="disabled='disabled'"></c:set>
	<c:set var="varImageDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>
<c:if test="${resValue.EditForm == null && resValue.EditForm != 'N'}">
	<c:set var="varRemarksDisabled" scope="page" value="style='display:none'"></c:set>
</c:if>
<script>
var User="";
var Use="";
function rejectContributionData(EmpId)
{
	xmlHttp=GetXmlHttpObject();
	
	if(xmlHttp==null)
	   {
	      return;
	   }  
	   
	Use = document.getElementById("Use").value ;
	User = document.getElementById("User").value;
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var testCounter=1;
	var totalselectedEmployees=0;
	var dcpsContributionIds="" ;
	//alert('totalselectedEmployeesInitially-->'+totalselectedEmployees);
	
	for(var k=1;k<=totalEmployees;k++)
	{
		//alert('inside for loop of checkbox');
		if(document.getElementById("checkbox"+k).checked)
		{  	
			//alert('checkbox'+k+'th checked');
			totalselectedEmployees++ ;	}
	}
	//alert('totalselectedEmployeesAfter-->'+totalselectedEmployees);
	for(var i=1;i<=totalEmployees;i++)
	{
		//alert('inside For');
		if(document.getElementById("checkbox"+i).checked)
		{
			//alert('inside outer If');
			if(testCounter==totalselectedEmployees)
			{
				//alert('inside first inner if');				
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
				testCounter++ ;
			}
			else
			{
				//alert('inside second inner if');	
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
				testCounter++ ;
			}
		}
	}	
	   
	   var url = "ifms.htm?actionFlag=dcpsRjtForContriReq&dcpsContributionIds="+dcpsContributionIds;
	   // alert(url);
	   xmlHttp.onreadystatechange = function()
		{
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					XMLDoc = xmlHttp.responseXML.documentElement;
					// alert(XMLDoc);
					var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
						var success_flag = XmlHiddenValues[0].childNodes[0].text;
						// alert(success_flag);						
						if (success_flag) {
								alert('Contributions are successfully sent back to the assistant.');
								self.location.href = 'ifms.htm?actionFlag=loadOfflineDCPSForm&User='+User+'&Use='+Use;
						}
					}
				}
		};
	   xmlHttp.open("POST",url,false);
	   xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	   xmlHttp.send(url);
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

    var urlstyle = 'height=200,width=270,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=no,top='+Y+',left='+X;

	newWindow = displayModalDialog(urlstring,'Calendar',urlstyle);
}
function AddNewRowContri(counter)
{
		var color1 = "#F0F8FF";
		var color2 = "#7B68EE";
	
		var selectedRow=counter ;
		var table=document.getElementById("contributionTable");
		var cnt=table.rows.length;
		var nextRow=Number(document.getElementById("hdnCounter").value) + 1 ;
		
		var newRow = table.insertRow(-1);
		
		newRow.style.backgroundColor = color1;
		newRow.style.borderColor=color2;
		
		var Cell1 = newRow.insertCell(-1);
   		var Cell2 = newRow.insertCell(-1);
   		var Cell3 = newRow.insertCell(-1);
   		var Cell4 = newRow.insertCell(-1);
   		var Cell5 = newRow.insertCell(-1); 
   		var Cell6 = newRow.insertCell(-1); 
   		var Cell7 = newRow.insertCell(-1); 
   		var Cell8 = newRow.insertCell(-1); 
   		var Cell9 = newRow.insertCell(-1); 
   		var Cell10 = newRow.insertCell(-1); 
   		var Cell11 = newRow.insertCell(-1); 
   		var Cell12 = newRow.insertCell(-1); 
   		
	   	Cell1.align="center";
	   	Cell2.align="center";
	   	Cell3.align="center";
	   	Cell4.align="center";
		Cell5.align="center";
	   	Cell6.align="center";
		Cell7.align="center";
	   	Cell8.align="center";
		Cell9.align="center";
		Cell10.align="center";
	   	Cell11.align="center";
	   	Cell12.align="center";
	   	
	   	Cell1.innerHTML = '<label id="name"'+nextRow+'><b>'+document.getElementById("name"+selectedRow).innerHTML+'</b></label>' ;
	//	Cell1.innerHTML = '<input type="text" name="name" id="name'+nextRow+'" value="'+document.getElementById("name"+selectedRow).value+'" />';
	    Cell2.innerHTML = '<label id="dcpsId"'+nextRow+'><b>'+document.getElementById("dcpsId"+selectedRow).innerHTML+'</b></label>' ;
	//	Cell2.innerHTML = '<input type="text" name="dcpsId" id="name'+nextRow+'" value="'+document.getElementById("dcpsId"+selectedRow).value+'" />';
		Cell3.innerHTML = ' <input type="text" size="10" name="txtStartDate'+nextRow+'" id="txtStartDate'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
						+ '	onBlur="validateDate(this);" value="'+document.getElementById("txtStartDate"+selectedRow).value +'" width="10" /> ' 
						+ ' &nbsp;&nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtStartDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');"	style="cursor: pointer;"/>'  ;
		Cell4.innerHTML = ' <input type="text" size="10" name="txtEndDate'+nextRow+'" id="txtEndDate'+nextRow+'"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"'
						+ '	onBlur="validateDate(this);" value="'+document.getElementById("txtEndDate"+selectedRow).value +'" width="10" /> ' 
						+ ' &nbsp;&nbsp;<img src="images/CalendarImages/ico-calendar.gif"  onClick="window_open(\'txtEndDate'+nextRow+'\', 375, 570, \'\', \'\', '+nextRow+');" style="cursor: pointer;"/>'  ;
		Cell5.innerHTML = ' <select name="cmbPayCommission" id="cmbPayCommission'+nextRow+'"  style="width: 157">' 
										+ ' <option value="-1">--Select--</option>' +LISTPAYCOMMISSIONS +'</select>';
		Cell6.innerHTML = ' <select name="cmbTypeOfPayment" id="cmbTypeOfPayment'+nextRow+'"  style="width: 107">'
										+ ' <option value="-1">--Select--</option>' +LISTTYPESOFPAYMENT +'</select>';
		Cell7.innerHTML = ' <input type="text" size="10"  id="basic'+nextRow+'" name="basic" value="'+document.getElementById("basic"+selectedRow).value+'" onchange="changeDpDaAndContri('+nextRow+')" ; />' ;
		Cell8.innerHTML = ' <input type="text" size="10"  id="DP'+nextRow+'" name="DP"  value="'+document.getElementById("DP"+selectedRow).value+'"  readonly="readonly" />' ;
		Cell9.innerHTML = ' <input type="text" size="10"  id="DA'+nextRow+'" name="DA"  value="'+document.getElementById("DA"+selectedRow).value+'"  readonly="readonly" />' ;
		Cell10.innerHTML = ' <input type="text" size="10"  id="contribution'+nextRow+'" name="contribution"  value="'+document.getElementById("contribution"+selectedRow).value+'"  readonly="readonly" />' ;
		Cell11.innerHTML= ' <input type="checkbox" id="checkbox'+nextRow+'" name="checkbox" value="'+0+'" />' 
										+ ' <input type="hidden" name="dcpsempId" id="dcpsempId'+ nextRow+'" value="'+  document.getElementById("dcpsempId"+selectedRow).value+'" />' ;
		Cell12.innerHTML= ' <a href=# onclick="AddNewRowContri('+nextRow+');"> <label id="AddNewRowContri">Add</label></a> ';
		document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value)+1;

		var lObjectcmbPayCommission = document.getElementById("cmbPayCommission"+nextRow);
		var lValuecmbPayCommission = document.getElementById("cmbPayCommission"+selectedRow).value;
		setSelectedIndex(lObjectcmbPayCommission,lValuecmbPayCommission) ;
}

function setSelectedIndex(s, v) {
    for ( var i = 0; i < s.options.length; i++ ) {
        if ( s.options[i].value == v ) {
            s.options[i].selected = true;
            return;
        }
    }
}


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
	Use = document.getElementById("Use").value ;
	User = document.getElementById("User").value;
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var cmbDDOCode = document.getElementById("cmbDDOCode").value;
	var cmbBillGroup = document.getElementById("cmbBillGroup").value;
	var schemeName = document.getElementById("schemeName").value;
	var schemeCode =  document.getElementById("schemeCode").value;
	var cmbMonth = document.getElementById("cmbMonth").value;
	var cmbYear = document.getElementById("cmbYear").value;
	self.location.href = "ifms.htm?actionFlag=loadOfflineDCPSForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup
			+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+schemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use ;
	//alert("ifms.htm?actionFlag=loadOfflineDCPSForm&cmbDDOCode="+cmbDDOCode+"&cmbBillGroup="+cmbBillGroup+"&cmbMonth="+cmbMonth+"&cmbYear="+cmbYear+"&schemeName="+schemeName+"&treasuryCode="+treasuryCode+"&schemeCode="+schemeCode+"&User="+User+"&Use="+Use );
}

function changeDpDaAndContri(counter)
{
	var nthElement = counter ; 
	var basic = document.getElementById("basic"+nthElement).value ;
	var DP = 0;
	var DA = (Number(basic * 0.27)).toFixed(2);
	var tempcontribution = Number(basic) + Number(DA) ;
	var contribution = (Number( tempcontribution * 0.10)).toFixed(2);
	document.getElementById("DP" + nthElement).value = DP ;
	document.getElementById("DA" + nthElement).value = DA ;
	document.getElementById("contribution" + nthElement).value = contribution ;

}
function saveContributionData(flag)
{
	Use = document.getElementById("Use").value ;
	User = document.getElementById("User").value;
	
	var saveOrForwardFlag=flag;
	var totalEmployees = document.getElementById("hdnCounter").value ;
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
	
   try
   {
	var finalSelectedEmployee=0;
	//alert('totalselectedEmployeesInitially-->'+totalselectedEmployees);
	
	for(var k=1;k<=totalEmployees;k++)
	{
		//alert('inside for loop of checkbox');
		if(document.getElementById("checkbox"+k).checked)
		{  	
			//alert('checkbox'+k+'th checked');
			finalSelectedEmployee = k ;	}
	}
	//alert('totalselectedEmployeesAfter-->'+totalselectedEmployees);
	

	for(var i=1;i<=totalEmployees;i++)
	{
		//alert('inside For');
		if(document.getElementById("checkbox"+i).checked)
		{
			//alert('inside outer If');
			if(i==finalSelectedEmployee)
			{
				//alert('inside first inner if');				
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempId"+i).value;
				cmbPayCommission= cmbPayCommission + document.getElementById("cmbPayCommission"+i).value;
				cmbTypeOfPayment= cmbTypeOfPayment + document.getElementById("cmbTypeOfPayment"+i).value;
				basic = basic + document.getElementById("basic"+i).value;
				DP = DP + document.getElementById("DP"+i).value;
				DA = DA + document.getElementById("DA"+i).value;
				contribution = contribution + document.getElementById("contribution"+i).value;
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
				txtStartDate = txtStartDate +  document.getElementById("txtStartDate"+i).value ;
				txtEndDate = txtEndDate +  document.getElementById("txtEndDate"+i).value ;
			}
			else
			{
				//alert('inside second inner if');	
				dcpsEmpIds = dcpsEmpIds + document.getElementById("dcpsempId"+i).value + "~";
				cmbPayCommission= cmbPayCommission + document.getElementById("cmbPayCommission"+i).value + "~";
				cmbTypeOfPayment= cmbTypeOfPayment + document.getElementById("cmbTypeOfPayment"+i).value + "~";
				basic = basic + document.getElementById("basic"+i).value + "~";
				DP = DP + document.getElementById("DP"+i).value + "~";
				DA = DA + document.getElementById("DA"+i).value + "~";
				contribution = contribution + document.getElementById("contribution"+i).value + "~";
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
				txtStartDate = txtStartDate +  document.getElementById("txtStartDate"+i).value + "~" ;
				txtEndDate = txtEndDate +  document.getElementById("txtEndDate"+i).value + "~" ;
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

	url = 'ifms.htm?actionFlag=saveContributions&dcpsEmpIds='+dcpsEmpIds+'&dcpsContributionIds='+dcpsContributionIds+'&txtStartDate='+txtStartDate+'&txtEndDate='+txtEndDate
	+'&cmbPayCommission='+cmbPayCommission+'&cmbTypeOfPayment='+cmbTypeOfPayment+
	'&basic='+basic+'&DP='+DP+'&DA='+DA+'&contribution='+contribution+'&monthId='+monthId+'&yearId='+yearId+'&cmbTreasuryCode='+cmbTreasuryCode+
	'&cmbDDOCode='+cmbDDOCode+'&cmbBillGroup='+cmbBillGroup+'&schemeCode='+schemeCode;

	//alert('urlSave-->'+url);
	
	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
						if(saveOrForwardFlag==1)
						{
							alert('Amounts Deposited successfully');
							self.location.href = 'ifms.htm?actionFlag=loadOfflineDCPSForm&User='+User+'&Use='+Use;
						}
						if(saveOrForwardFlag==2)
						{
							var totalContributionsForFwd = XmlHiddenValues[0].childNodes[1].text ;
							var contriIdsForFwd = "";

							for(var lInt=0;lInt<totalContributionsForFwd;lInt++)
							{
								if(lInt==totalContributionsForFwd-1)
								{
									contriIdsForFwd = contriIdsForFwd + XmlHiddenValues[0].childNodes[lInt+2].text ;
								}
								else
								{
									contriIdsForFwd = contriIdsForFwd + XmlHiddenValues[0].childNodes[lInt+2].text + "~" ;
								}
							}
							
							var ForwardToPost =  document.getElementById("ForwardToPost").value;
							var uriForward = "ifms.htm?actionFlag=dcpsFwdForContriReq&dcpsContributionIds="+contriIdsForFwd+"&ForwardToPost="+ForwardToPost;
							 alert('uriForward-->'+uriForward);
							xmlHttpNew=GetXmlHttpObject();
							
							if (xmlHttpNew==null)
							{
								alert ("Your browser does not support AJAX!");
								return;
							} 
							xmlHttpNew.onreadystatechange= function()
							{
								if (xmlHttpNew.readyState == 4) {
									if (xmlHttpNew.status == 200) {
										
										XMLDocNew = xmlHttpNew.responseXML.documentElement;
										// alert('XMLDocNew-->'+XMLDocNew);
										
										var XmlHiddenValuesNew = XMLDocNew.getElementsByTagName('XMLDOC');
										// alert('XmlHiddenValuesNew-->'+XmlHiddenValuesNew);
		
										// alert(XmlHiddenValuesNew[0].childNodes[0].text) ;
										
										var success_flag = XmlHiddenValuesNew[0].childNodes[0].text;
										// alert('success_flag-->'+success_flag);
										
										if(success_flag=='true')
										{
										alert("Contributions are forwarded successfully");
										self.location.href = 'ifms.htm?actionFlag=loadOfflineDCPSForm&User='+User+'&Use='+Use;
										}
									  }
									}
							};
							xmlHttpNew.open("POST",uriForward,false);
							xmlHttpNew.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
							xmlHttpNew.send(uriForward);
						}
					}
				}
			}
	} ;
xmlHttp.open("POST",url,true);
xmlHttp.send(url);
}

function deleteContributionData()
{
	var totalEmployees = document.getElementById("hdnCounter").value ;
	var dcpsEmpIds = "" ;
	var finalSelectedEmployee=0;
	var dcpsContributionIds="";
	
	for(var k=1;k<=totalEmployees;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{ 
			finalSelectedEmployee = k ;
		}
	}

	alert('totalEmployees-->'+totalEmployees);
	alert('finalSelectedEmployee-->'+finalSelectedEmployee);
	
	for(var i=1;i<=totalEmployees;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedEmployee)
			{
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value  ;
			}
			else
			{
				dcpsContributionIds = dcpsContributionIds + document.getElementById("checkbox"+i).value + "~" ;
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
	alert('url-->'+url);

	xmlHttp.onreadystatechange = function()
	{
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				XMLDoc = xmlHttp.responseXML.documentElement;
				var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');

					var successFlag = XmlHiddenValues[0].childNodes[0].text;
											
					if (successFlag=='true') {
							alert('Employees Deleted Successfully');
							self.location.href = 'ifms.htm?actionFlag=loadOfflineDCPSForm';
					}
				}
			}
	} ;

	xmlHttp.open("POST",url,true);
	xmlHttp.send(url);
}

function forwardContributionData()
{
	saveContributionData(2);
}
</script>

<hdiits:form name="DCPSOfflineEntryForm" id="DCPSOfflineEntryForm" encType="multipart/form-data"
	validate="true" method="post" >
	
		<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.INPUTDETAILSFORCONTRI" bundle="${dcpsLables}"></fmt:message></b> </legend>
		
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
	   </table>
	   <table align="center">
					<tr>
								<td id="go" align="center" style="display:block;"><hdiits:button
															name="btnGo" id="btnGo" type="button"
															captionid="BTN.GO" bundle="${dcpsLables}"
															onclick="getEmpListforContri();" />
								<input type="hidden" id="User" value="${resValue.lStrUser}"/>
								<input type="hidden" id="Use" value="${resValue.lStrUse}"/>
								</td>	
					</tr>
	    </table>		
		</fieldset>
		
		
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
		
		<c:if test="${empList!=null}">
		
		<fieldset class="tabstyle"><legend> <b><fmt:message
		key="CMN.OUTPUTDETAILSFORCONTRI" bundle="${dcpsLables}"></fmt:message></b> </legend>
		
		<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
		
		<div style="width:100%;overflow:auto;height:100%;" >
		<display:table list="${empList}"  id="contributionTable"    requestURI="" export="" style="width:100%,border: thin;"  pagesize="5" >
		<display:setProperty name="paging.banner.placement" value="bottom" />		
		
	
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"   sortable="true" titleKey="CMN.NAME" >
						 <label id="name${contributionTable_rowNum}" ><b>${contributionTable[2]}</b></label>
				</display:column>
				
				<display:column headerClass="datatableheader" class="tablecelltext" style="text-align:center;"   sortable="true" titleKey="CMN.DCPSID" >
						<label id="dcpsId${contributionTable_rowNum}" ><b>${contributionTable[1]}</b></label>
				</display:column>
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.CONTRISTARTDATE"  >
				
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.FirstDate}" var="empStartDate"/>
						<input type="text" size="10" name="txtStartDate${contributionTable_rowNum}" id="txtStartDate${contributionTable_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
						onBlur="validateDate(this);" value="${empStartDate}" ${varDisabled} /> &nbsp;
						<img src='images/CalendarImages/ico-calendar.gif' 
						onClick='window_open("txtStartDate${contributionTable_rowNum}",375,570)'
						style="cursor: pointer;" ${varImageDisabled}/>
				</display:column>
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;" sortable="true"  titleKey="CMN.CONTRIENDDATE" >
				
						<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.LastDate}" var="empEndDate"/>
						<input type="text" size="10" name="txtEndDate${contributionTable_rowNum}" id="txtEndDate${contributionTable_rowNum}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);"
						onBlur="validateDate(this);" value="${empEndDate}" ${varDisabled}/>&nbsp;
					
						<img src='images/CalendarImages/ico-calendar.gif' 
						onClick='window_open("txtEndDate${contributionTable_rowNum}",375,570)'
						style="cursor: pointer;" ${varImageDisabled}/>
				</display:column>
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.PAYCOMIMISSION" >   
						   			<select name="cmbPayCommission" id="cmbPayCommission${contributionTable_rowNum}" style="width: 10">
										<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
										      <c:forEach var="PayCommission" items="${resValue.listPayCommission}">
				           		     						<c:choose>
																<c:when test="${contributionTable[3] == PayCommission.lookupId}">
																<option value="${PayCommission.lookupId}" ${varDisabled} selected="selected"><c:out value="${PayCommission.lookupDesc}" ></c:out></option>
																</c:when>
																<c:otherwise>
																<option value="${PayCommission.lookupId}" ${varDisabled}><c:out value="${PayCommission.lookupDesc}" ></c:out></option>
																</c:otherwise>
															</c:choose>
										      </c:forEach>
								     </select>
				</display:column>		
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.PAYMENTTYPE" >  
						  
									  <select name="cmbTypeOfPayment" id="cmbTypeOfPayment${contributionTable_rowNum}" style="width: 10">
											<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
											      <c:forEach var="typeOfPayment" items="${resValue.listTypeOfPayment}">
											      			<c:choose>
											      				<c:when test="${contributionTable[6]==typeOfPayment.lookupId}">
																	<option value="${typeOfPayment.lookupId}" ${varDisabled} selected="selected"><c:out value="${typeOfPayment.lookupDesc}"></c:out></option>
																</c:when>
																<c:otherwise>
																	<option value="${typeOfPayment.lookupId}"><c:out value="${typeOfPayment.lookupDesc}"></c:out></option>
																</c:otherwise>
															</c:choose>
											      </c:forEach>
									  </select>
				</display:column>		 
						  
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.BASIC" > 
						      		  <input type="text" size="10"  id="basic${contributionTable_rowNum}" name="basic" value="${contributionTable[4]}" ${varDisabled}  onchange="changeDpDaAndContri('${contributionTable_rowNum}');" style="size: 5"  />
				</display:column>
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.DP" >  
						  			  <input type="text" size="10" id="DP${contributionTable_rowNum}" name="DP" value="${contributionTable[9]}" ${varDisabled} readonly="readonly" width="10"/>
				</display:column>
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.DA" > 
						              <input type="text" size="10" id="DA${contributionTable_rowNum}" name="DA" value="${contributionTable[10]}" ${varDisabled} readonly="readonly" width="10"/>
			    </display:column>
			    
			    <display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.CONTRIBUTION" >
				  		
				  		  			  <input type="text" size="10" id="contribution${contributionTable_rowNum}" name="contribution" value="${contributionTable[11]}" ${varDisabled}  readonly="readonly"/>
				</display:column>  		
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  title="<input type='checkbox' name='SelectAll' onclick='checkUncheckAll(this)'/>" >
				  		
				  		  			  <input type="checkbox" id="checkbox${contributionTable_rowNum}" name="checkbox" value="${contributionTable[5]}"/>
								  
								      <input type="hidden" name="dcpsempId" id="dcpsempId${contributionTable_rowNum}" value="${contributionTable[0]}"/>
				</display:column> 		
				
				<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center;"  sortable="true"  titleKey="CMN.ADDROW" >
				
						<c:if test="${resValue.EditForm != null && resValue.EditForm == 'Y'}">    
				  			 <a href=# onclick="AddNewRowContri('${contributionTable_rowNum}');"> <label id="AddNewRowContri">Add</label></a>
				  		</c:if>
				  			 <script>
								document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
							 </script>
				</display:column>
		  	      
		</display:table>
		</div>
		  
		<input type="hidden" id="previousStartDate" value="${resValue.DelFirstDate}" />
		<input type="hidden" id="previousEndDate" value="${resValue.DelLastDate}" />     
		
		<br/>
		<br/>
		<br/>

		 
        <c:if test="${resValue.lStrUser == 'ATO'}">
       
        <table width="100%" >
        <tr>
		        <td width="33%" id="saveBtn" align="center" style="display:block;"><hdiits:button
																	name="btnSaveData" id="btnSaveData" type="button"
																	captionid="BTN.SAVE" bundle="${dcpsLables}"
																	onclick="saveContributionData(1)" />
				</td>
				<td width="33%" id="deleteEmpBtn" align="center" style="display:block;">
																<hdiits:button
																	name="btnDeleteEmp" id="btnDeleteEmp" type="button"  style="width:30%"  
																	captionid="BTN.DELETEEMP" bundle="${dcpsLables}"
																	onclick="deleteContributionData()" />
				</td>
				<td width="34%" id="forwardBtn" align="center" style="display:block;">
				<input type="hidden" id="ForwardToPost" name="ForwardToPost" value="${UserList[0]}"/>
																<hdiits:button
																	name="btnForward" id="btnForward" type="button"  style="width:30%"  
																	captionid="BTN.FORWARD" bundle="${dcpsLables}"
																	onclick="forwardContributionData()" />
		        </td>
        </tr>
        </table>
		</c:if>
		
		<c:if test="${resValue.lStrUser == 'TO'}">
		<table width="100%">
        <tr>
		        <td width="33%" >&nbsp;</td>
				<td width="33%" id="approveBtn" align="center" style="display:block;">
																<hdiits:button
																	name="btnApprove" id="btnApprove" type="button"  style="width:30%"  
																	captionid="BTN.APPROVE" bundle="${dcpsLables}"
																	onclick="" />
				</td>
				<td width="34%" id="rejectBtn" align="center" style="display:block;">
				<input type="hidden" id="rejectToPost" name="rejectToPost" value="${UserList[0]}"/>
																<hdiits:button
																	name="btnReject" id="btnReject" type="button"  style="width:30%"  
																	captionid="BTN.REJECT" bundle="${dcpsLables}"
																	onclick="rejectContributionData()" />
		        </td>
        </tr>
        </table>
		</c:if>
		</c:if>
		</fieldset>
</hdiits:form>






