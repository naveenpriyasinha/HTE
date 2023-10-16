<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>


<c:set var="resultObj" value="${result}"/>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="totalRecords" value="${resValue.totalRecords}"></c:set>
<c:set var="SchemeList" value="${resValue.schemelist}"></c:set>
<c:set var="schemeCode" value="${resValue.schemeCode}"></c:set>
<c:set var="DDOlist" value="${resValue.DDOlist}"></c:set>
<c:set var="isLvl2" value="${resValue.isLvl2}"></c:set>
<c:set var="counter" value="1" />
<!-- started By roshan kumar-->
<c:set var="displayAddNewEntry" value="${resValue.displayAddNewEntry}"></c:set>
<c:set var="talukaList" value="${resValue.talukaList}" ></c:set>
<c:set var="talukaId" value="${resValue.talukaId}" ></c:set>
<c:set var="ddoSelected" value="${resValue.ddoSelected}" ></c:set>
<!-- started By roshan kumar-->
<c:set var="lLstSchemeNamesForAutoComp" value="${resValue.lLstSchemeNamesForAutoComp}"></c:set>
<script type="text/javascript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/dcps/ddoScheme.js"></script>
<style>
input[type="button" i]:disabled {
    pointer-events: none;
    opacity: 0.6;
}
</style>
<script>
function addRow()
{
	var table=document.getElementById("searchTable");
	var nextRow= Number (document.getElementById("hidTotalRows").value) +1;
	var newRow = table.insertRow(-1);
	color2 = "rgb(255, 218, 178)";
	newRow.style.borderColor = color2;	


	var Cell4 = newRow.insertCell(-1);
	Cell4.width="190px";
	Cell4.innerHTML = '<input type="text"   style="width:61%;" size="20" name="txtSubSchemeCode" maxlength="10" id="txtSubSchemeCode'+nextRow+'"  value=""  onblur="CheckSubSchemeExist('+nextRow+'); popUpSubSchemeName('+nextRow+');" />';

	var Cell1 = newRow.insertCell(-1); 
	Cell1.width="190px";
	Cell1.innerHTML = '<select name="cmbSubSchemeName"  style="width: 100%;"  id="cmbSubSchemeName'+nextRow+'"  onchange="setSubSchemeCode('+nextRow+');" >'
				 	+ '<option value="-1">-------------Select--------------</option>' + +'</select>'; 
	
	var Cell5 = newRow.insertCell(-1);
	Cell5.width="190px";
	Cell5.innerHTML = '<a href=# onclick="ADD('+nextRow+');"> <label id="ADD">ADD</label></a>' +
	 '<a href=# onclick="DeleteRowBrokenPrd('+nextRow+');"> <label id="DeleteRowBrokenPrd">Delete</label></a>';
	document.getElementById("hidTotalRows").value = Number (document.getElementById("hidTotalRows").value) + 1;
	
}

function DeleteRowBrokenPrd(counter)
{

	if(counter == 1)
	{
		alert('You cannot delete the first row.');
		return false;
	}
	var current = window.event.srcElement;
    while ( (current = current.parentElement)  && current.tagName !="TR");
         current.parentElement.removeChild(current);
    
    document.getElementById("hidTotalRows").value = Number(document.getElementById("hidTotalRows").value) - 1 ;
}

function ADD(counter)
{
	addRow();
}

function popUpSubSchemeName(counter)
{

	var txtSubSchemeCode = document.getElementById("txtSubSchemeCode"+counter).value;
	if(txtSubSchemeCode.length >= 4)
	{
		var uri="ifms.htm?actionFlag=displaySubSchemeNameForCode&txtSubSchemeCode="+txtSubSchemeCode;
		var url="txtSubSchemeCode="+txtSubSchemeCode;
		alert("HI"+uri);
		var myAjax = new Ajax.Request(uri,
			       {
			        method: 'post',
			        asynchronous: false,
			        parameters:url,
			        onSuccess: function(myAjax) {
						getDataStateChangedForPopUpSubSchemes(myAjax,counter);
					},
			        onFailure: function(){ alert('Something went wrong...')} 
			          } );
	}
	else
	{
		var cmbSubSchemeName = document.getElementById('cmbSubSchemeName');
		cmbSubSchemeName.options.length = 0;
		var optn = document.createElement("OPTION");
		optn.text = "-- Select --";
		optn.value = "-1";
		cmbSubSchemeName.options.add(optn);
	}
}

function getDataStateChangedForPopUpSubSchemes(myAjax,counter)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var cmbSubSchemeName = document.getElementById('cmbSubSchemeName'+counter);
	cmbSubSchemeName.options.length = 0;
	var optn = document.createElement("OPTION");
	optn.text = "-- Select --";
	optn.value = "-1";
	cmbSubSchemeName.options.add(optn);

	var totalSchemes = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;
	var optnScheme ;
		var count=1;
		while(count<=(2*totalSchemes))
		{
			optnScheme = document.createElement("OPTION");
			optnScheme.text = XmlHiddenValues[0].childNodes[count].firstChild.nodeValue;
			optnScheme.title= XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
			optnScheme.value = XmlHiddenValues[0].childNodes[count+1].firstChild.nodeValue;
			
			document.getElementById("cmbSubSchemeName"+counter).options.add(optnScheme);
			count=count+2;
		}
}
function setSubSchemeCode(counter)
{
	document.getElementById("txtSchemeCode").value=document.getElementById("cmbSchemeName"+counter).value;
}

function CheckSubSchemeExistForTable()
{
	var schemeCode=document.getElementById("txtSchemeCode").value;
	var url = run();
		var uri = "ifms.htm?actionFlag=CheckSubSchemeExist&schemeCode="+schemeCode;
		url = uri + url;   
		xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return false;
		  }        
		xmlHttp.onreadystatechange=checkSubSchemeExistOrNot;
		xmlHttp.open("POST",uri,true);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(url);

}

/* function checkSubSchemeExistOrNot() commented by saurabh
{
	if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200)
			{
				var XMLDoc=xmlHttp.responseXML.documentElement;	
				var paybillMapping = XMLDoc.getElementsByTagName('paybillMapping');				
				var status=paybillMapping[0].childNodes[0].firstChild.nodeValue;       			
				if(status ==0)
   				{
   					//alert(" Inside the disabling the table");
   					
   					document.getElementById("searchTable").disabled = true;
   					//added by poonam on 21-04-2017
   					document.getElementById("txtSubSchemeCode").disabled = true;
   					document.getElementById("cmbSubSchemeName").disabled = true;
   					
   				}
   				else
   				{
   				//alert(" Inside the enabling the table");
   					document.getElementById("searchTable").disabled = false;
   					document.getElementById("txtSubSchemeCode").disabled = false;
   					document.getElementById("cmbSubSchemeName").disabled = false;
   				}
			}
		}
} */

function checkSubSchemeExistOrNot()
{
if (xmlHttp.readyState == 4) 
{     
if (xmlHttp.status == 200)
{
var XMLDoc=xmlHttp.responseXML.documentElement;
var paybillMapping = XMLDoc.getElementsByTagName('paybillMapping');
var status=paybillMapping[0].childNodes[0].firstChild.nodeValue;
//alert("status"+status);
if(status ==0)
    {
    //alert(" Inside the disabling the table");
   
    document.getElementById("searchTable").disabled = true;
    //added by poonam on 21-04-2017
    document.getElementById("txtSubSchemeCode").disabled = true;
    document.getElementById("cmbSubSchemeName").disabled = true;
    document.getElementById("isSubScheme").value="1";
    }
    else
    {
    //alert(" Inside the enabling the table");
    document.getElementById("searchTable").disabled = false;
    document.getElementById("txtSubSchemeCode").disabled = false;
    document.getElementById("cmbSubSchemeName").disabled = false;
    
    }
}
}
}








function CheckSubSchemeExist(counter)
{
	var schemeCode=document.getElementById("txtSchemeCode").value;
	var subSchemeCode=document.getElementById("txtSubSchemeCode"+counter).value;
	var url = run();
		var uri = "ifms.htm?actionFlag=CheckSubSchemeExistOrNot&schemeCode="+schemeCode+"&subSchemeCode="+subSchemeCode;
		url = uri + url;   
		xmlHttp=GetXmlHttpObject();
		  if (xmlHttp==null)
		  {
			  alert ("Your browser does not support AJAX!");
			  return false;
		  }        
		xmlHttp.onreadystatechange=checkPaybillStatus;
		xmlHttp.open("POST",uri,true);
		xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		xmlHttp.send(url);
}



function checkPaybillStatus()
{
	if (xmlHttp.readyState == 4) 
		{     
			if (xmlHttp.status == 200)
			{
				var XMLDoc=xmlHttp.responseXML.documentElement;	
				var paybillMapping = XMLDoc.getElementsByTagName('paybillMapping');				
				var status=paybillMapping[0].childNodes[0].firstChild.nodeValue;       			
   				if(status==0)
   				{
   					alert(" Subscheme is not Mapped to the scheme");
   					//document.getElementById("txtSchemeCode").value="";
   					document.getElementById("txtSubSchemeCode").value="";
   					//document.getElementById("cmbSchemeName").value="";
   					
   				}
   				
			}
		}
}
</script>




<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<hdiits:form name="frmScheme" encType="multipart/form-data" validate="true" method="post" >
<fieldset class="tabstyle" ><legend> <b><fmt:message key="CMN.SCHEMESOPERATED"
				bundle="${dcpsLables}"/></b> </legend>


<div align = "center">
<display:table list="${SchemeList}" size="${totalRecords}" pagesize="5"  id="vo"  requestURI="" 
	cellpadding="4" style="width:50%">
 	<display:setProperty name="paging.banner.placement" value="bottom" />
	<display:column style="text-align: left;" 
		titleKey="CMN.SCHEMECODE" headerClass="datatableheader"
		sortable="true" value="${vo[0]}"> 
	</display:column>
	<display:column style="text-align: left;"
		titleKey="CMN.SCHEMENAME" headerClass="datatableheader"
		sortable="true" value="${vo[1]}"> 
	</display:column>
	
	<display:column style="text-align: left;" title="Sub Scheme Code"
			headerClass="datatableheader" sortable="true" value="${vo[2]}">
		</display:column>
	<display:column style="text-align: left;"
		titleKey="CMN.DDOCODE" headerClass="datatableheader"
		sortable="true" value="${vo[3]}"> 
	</display:column>
</display:table>
</div>
</fieldset>
<%-- Added by roshan for filter by ddo --%>


	<fieldset class="tabstyle"><legend> <b>Filter Institute</b> </legend>
<table align="center">

<tr>

<td><c:out value="Taluka"></c:out></td>

<td><select name="cmbTaluka"
			id="cmbTaluka" style="width: 85%,display: inline;">
			<option title="Select" value="-1"><c:out value="Select"></c:out></option>

			<c:forEach var="talukaList" items="${talukaList}">
			<c:choose> 
			<c:when test="${talukaId==talukaList[0]}">
							<option value="${talukaList[0]}" title="${talukaList[1]}" selected="selected">
						<c:out value="${talukaList[1]}"/></option>
			</c:when>
			<c:otherwise>
						<option value="<c:out value="${talukaList[0]}"/>" title="${talukaList[1]}">
						<c:out value="${talukaList[1]}"/></option>
						</c:otherwise>
						</c:choose>
					</c:forEach>
					
					
		</select></td>


<td><c:out value=" DDO Code/Office Name"></c:out></td>
<td>
<c:choose>
<c:when test="${ddoSelected!=null}">
<!--<hdiits:text id = "ddoCode" name="ddoCode" captionid="${ddoSelected}" validation="" maxlength="200" size="30"
			 default=""/>
-->
<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" value="${ddoSelected}" size="30"/>
</c:when>
<c:otherwise>
<input type="text" id = "ddoCode" name="ddoCode" maxlength="200" size="30"/>
</c:otherwise>
</c:choose>			 
</td>


		</tr>
	<tr>	
<td colspan="4" align="center"><input id="btnFilter" class="buttontag" type="button" size="5" maxlength="5"
		value="Filter" onclick="filterInstitute()"
		name="btnFilter" style="width: 120px;" /></td>
</tr>
</table>
</fieldset>
<%--Added by roshan --%>


<c:if test="${isLvl2  eq 'yes'}">
<fieldset class="tabstyle"><legend> <b><fmt:message key="CMN.ADDSCHEMESTODDO"
				bundle="${dcpsLables}"/></b> </legend>
<table id="tblMaster" width="70%" align="center" border = "0">	

	
	
	<tr>
		
		<td width="10%" align="left"><fmt:message key="CMN.DDOOFCNAME"
				bundle="${dcpsLables}"></fmt:message>
				</td>
		<td width="100%" align="left">
		<select name="cmbsubDdos"
				id="cmbsubDdos" style="width:120%" >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" />
				</option>
				
			<c:forEach items="${DDOlist}" var="ddo">
					
					<option value=${ddo[0]}>${ddo[3]}(${ddo[0]})
					</option>
					
				</c:forEach>
				
		</select>
		
		<label class="mandatoryindicator">*</label>
		</td>
		
		</tr>
		<tr>
		<td width="10%" align="left"><fmt:message key="CMN.SCHEMECODE"
				bundle="${dcpsLables}"></fmt:message>
				</td>
		<td width="100%" align="left">
		<input type="text" name='txtSchemeCode' id="txtSchemeCode" maxlength="10" style="text-align: left" onblur="CheckSubSchemeExistForTable();popUpSchemeName();"/>
		<label class="mandatoryindicator">*</label>
		</td>
	</tr>
	
	<tr>
		<td width="10%" align="left"><fmt:message key="CMN.SCHEMENAME"
				bundle="${dcpsLables}"></fmt:message></td>
		<td width="100%" align="left">
		
		<select name="cmbSchemeName"
				id="cmbSchemeName" style="width:100%" onchange="setSchemeCode();" >
				<option value="-1"><fmt:message
					key="COMMON.DROPDOWN.SELECT" /></option>
				
		</select>
		<label class="mandatoryindicator">*</label>
	</td>
	</tr>
	<tr></tr>
	<tr></tr>
	
		<table width="90%" id="Added2" align="center">
				<tr>
					<td></td>
				</tr>
				<tr bgcolor="#BD6C03">
					<td class="fieldLabel" width="100%" colspan="2" align="center">

					<font color="#ffffff" size="2"> <strong>Add sub
					scheme code details </strong></font></td>
				</tr>
			</table>
	
	
</table>


<table id="searchTable" align="center" width="90%"  bordercolor="black">
	
		<tr>
			<td align="center" style="background-color: #F7E7D7;  color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
			Sub Scheme Code:</td>
			<td align="center" style="background-color: #F7E7D7;    color: rgb(202, 97, 12); font-size: small; font-style: normal; font-weight: bold">
			Discription:</td>

</tr>
	<tr>
	
	<!--  <td></td>
	<td width="20%" align="left"><fmt:message key="CMN.SUBSCHEMECODE" bundle="${dcpsLables}"></fmt:message></td>-->
	<td >
	<input type="text" name='txtSubSchemeCode' size="30" id="txtSubSchemeCode${counter}" maxlength="10" style="text-align: left;" onblur="CheckSubSchemeExist(${counter});popUpSubSchemeName(${counter});"/>
	</td>
	
	
	
	<!-- <td width="15%" align="left"><fmt:message key="CMN.SUBSCHEMENAME"
				bundle="${dcpsLables}"></fmt:message></td>-->
	
		<td >
	<select name="cmbSubSchemeName"
				id="cmbSubSchemeName${counter}" style="width:100%" onchange="setSubSchemeCode(${counter});" >
				<option value="-1"><!--<fmt:message
					key="COMMON.DROPDOWN.SELECT" />--> -------------Select--------------</option>
				
		</select></td>
	
	
	
	
	<td><a href=# onclick="addRow()">ADD</a>
	<a href=# onclick="DeleteRowBrokenPrd('${counter}')">Delete</a></td>
	</tr>
	
	
	    <c:set var="counter" value="${counter+1}" ></c:set>
	</table>


<input type="hidden" id="hidTotalRows" value="${counter-1}" />
<input type="hidden" name="isSubScheme" id="isSubScheme" value="1"/> 
		<div align="center"><hdiits:button type="button"
			captionid="BTN.ADDSCHEME" id="btnSave"
			onclick="SaveDataAfterValidation();" bundle="${dcpsLables}"
			name="btnSave" /> <hdiits:button name="btnBack" id="btnBack"
			type="button" captionid="BTN.BACK" bundle="${dcpsLables}"
			onclick="ReturnLoginPage();" /></div>

</fieldset>
</c:if>

</hdiits:form>

