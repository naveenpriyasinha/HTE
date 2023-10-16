<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<fmt:setBundle basename="resources.common.CommonLables" var="locLables" scope="request"/>

<c:set var="resultObj" 				value="${result}" /> 
<c:set var="resultMap" 				value="${resultObj.resultValue}" />						 
<c:set var="locList" 				value="${resultMap.LocationList}" />
<c:set var="locSearchCrt"   		value="${resultMap.locSearchCrt}" />
<c:set var="locSearchbaseLoginMap" 	value="${resultMap.baseLoginMap}" /> 

<c:set var="locSearchDefState"    		value="${locSearchbaseLoginMap.loggedInUserStateName}" /> 
<c:set var="locSearchDefStateCode"     	value="${locSearchbaseLoginMap.loggedInUserStateCode}" /> 	
<c:set var="locSearchDefCountry" 		value="${locSearchbaseLoginMap.loggedInUserCountryName}" />	
<c:set var="locSearchDefCountryCode" 	value="${locSearchbaseLoginMap.loggedInUserCountryCode}" />

<c:set var="locNameVal"     value="%" />

<c:if test="${locSearchCrt ne null}" >
	<c:set var="locNameVal" value="${locSearchCrt}" />
</c:if>

<fmt:message var="confirmMsg" key="LOC.ALERTMSG"            bundle="${locLables}" />
<fmt:message var="alertMsg"   key="LOC.SEARCHCRITERIAALERT" bundle="${locLables}" />
<fmt:message var="select"     key="LOC.SELECT"              bundle="${locLables}" />

<script language="javascript">
var searchFieldName = '${param.searchFieldName}';
if (searchFieldName == null)
{
	searchFieldName == '${resultMap.searchFieldName}';
}

function clearCombo(listId)
  {
  	//var listId = document.getElementById("lstPoint");
  	var listLength = listId.length;
	for(var i=listLength;i>=0;i--)
			{
				    		        
			listId.remove(i);
				    		        
	       }   
	var anOption = document.createElement("OPTION") 
	listId.options.add(anOption) 
	anOption.innerText = "-----${select}-----" 
	anOption.value = "select"      
  }
 
function populateState()
{
	var url_ps ='${pageContext.request.contextPath}/hdiits.htm';
    var pars_ps = "actionFlag=getAllStates&countryName=india";  
    var myAjax_ps = new Ajax.Request(url_ps,{
		method: 'get',
		parameters: pars_ps,
		onComplete: function(transport){
			
			if (200 == transport.status) 
			{
				processResponsePopulateState(transport);
			}
		}
	});
}
function processResponsePopulateState(transport)
{		
				if (transport.status == 200) 
				{    
					    var text;						
		            	var z = document.getElementById('lstState');   
				    	var XMLDoc=transport.responseXML.documentElement;
				    	if(XMLDoc!= null)
				    	{
				    		 if(z.length > 1)
				    		  {
				    		     clearCombo(z);
				    		  }
				    		
				    	var entries = XMLDoc.getElementsByTagName('element');					    		
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;							
							try
	   						{	    						
	    						z.add(y,null); 	    			
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y); 
	   						}   					
	     				}
	     				//document.getElementById('lstState').options[1].selected = 'true';
						 var lstSteLen = document.getElementById('lstState').options.length;
						 //alert(lstSteLen);
						 //alert('${locSearchDefStateCode}');
	     		         for(var dynCounter = 0; dynCounter < lstSteLen; dynCounter++)
	     				 {
	     					if(document.getElementById('lstState').options[dynCounter].value == '${locSearchDefStateCode}')
	     					{
	     						document.getElementById('lstState').options[dynCounter].selected = 'true';
	     						break;
	     					}
	     				 }
	     				populateDistrict();
	           		 }	
	           	}
				else 
				{  			
					alert("ERROR");
				}
}

function populateDistrict()
{
		var stateCode = "";
        var lstLength = document.getElementById('lstState').options.length;
        //alert(lstLength);
		for(var dynCounter = 0; dynCounter < lstLength; dynCounter++)
			{
				if(document.getElementById('lstState').options[dynCounter].selected)
				{
				 	stateCode = document.getElementById('lstState').options[dynCounter].value;
				 	//alert(stateCode);
					break;
				}
			}
			
	var url_pd ='${pageContext.request.contextPath}/hdiits.htm';
    var pars_pd = "actionFlag=getAllDistrict&stateCode="+stateCode;  
    var myAjax_pd = new Ajax.Request(url_pd,{
		method: 'get',
		parameters: pars_pd,
		onComplete: function(transport){
		
			if (200 == transport.status) 
			{
				processResponsePopulateDist(transport)
			}
		}
		});
}
function processResponsePopulateDist(transport)
{		
				if (transport.status == 200) 
				{    
					    var text;						
		            	var z = document.getElementById('lstDist');  
		            	var city = document.getElementById('lstCity');  
		            	clearCombo(z);
		            	clearCombo(city);
				    	var XMLDoc=transport.responseXML.documentElement;
				    	if(XMLDoc!= null)
				    	{
				    	var entries = XMLDoc.getElementsByTagName('element');					    		
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;							
							try
	   						{	    						
	    						z.add(y,null); 	    			
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y); 
	   						}   					
	     				}
	     				document.getElementById('lstDist').focus();
	           		 }	
	           	}
	           	else 
				{  			
					alert("ERROR");
				}
}

function populateCity()
{
		var districtCode = "";
        var lstLength = document.getElementById('lstDist').options.length;
        //alert(lstLength);
		for(var dynCounter = 0; dynCounter < lstLength; dynCounter++)
			{
				if(document.getElementById('lstDist').options[dynCounter].selected)
				{
				 	districtCode = document.getElementById('lstDist').options[dynCounter].value;
				 	//alert(districtCode);
					break;
				}
			}
			
	var url_pc ='${pageContext.request.contextPath}/hdiits.htm';
    var pars_pc = "actionFlag=getAllCity&districtCode="+districtCode;  
    var myAjax_pc = new Ajax.Request(url_pc,{
		method: 'get',
		parameters: pars_pc,
		onComplete: function(transport){
		
			if (200 == transport.status) 
			{
				processResponsePopulateCity(transport);
			}
		}
		});
}
function processResponsePopulateCity(transport)
{		
				if (transport.status == 200) 
				{    
					    var text;						
		            	var z = document.getElementById('lstCity');   
				    	var XMLDoc=transport.responseXML.documentElement;
				    	clearCombo(z);
				    	if(XMLDoc!= null)
				    	{
				    	var entries = XMLDoc.getElementsByTagName('element');					    		
						for ( var i = 0 ; i < entries.length ; i++ )
	     				{
	     					text=entries[i].childNodes[0].text;   
	     				    value=entries[i].childNodes[1].text;	     				   
	     					var y=document.createElement('option');
		 					y.text=value;
							y.value=text;							
							try
	   						{	    						
	    						z.add(y,null); 	    			
	   						}
	 						catch(ex)
	   						{	   			 
	   			 				z.add(y); 
	   						}   					
	     				}
	     				document.getElementById('lstCity').focus();
	           		 }	
	           	}
	           	else 
				{  			
					alert("ERROR");
				}
}
 
function submitForm()
	{	
		var selectedVal;
		var statusFlag = false;
		var radioLength = document.findLocation.rdoLoc.length;
		if(radioLength == undefined)
		{
			if(document.findLocation.rdoLoc.checked)
			{
				var content = document.findLocation.rdoLoc.value;
				fillInParentWindow(content);								
			}
		}
		else
		{
			for (var rdoIndex = 0; rdoIndex < document.findLocation.rdoLoc.length; rdoIndex++)
			{ 
				if (document.findLocation.rdoLoc[rdoIndex].checked)
				{
					selectedVal = document.findLocation.rdoLoc[rdoIndex].value
					statusFlag = true;
				} 
			} 
			if(statusFlag)
			{	
				fillInParentWindow(selectedVal);
			}	
			else
			{
				alert('${confirmMsg}');
			}
		}
		
} 
if (window.dialogArguments) 
{
    window.opener = window.dialogArguments;
}   

function fillInParentWindow(content)
{	
	var locArr 		= content.split("*");
	var locName 	= locArr[0];
	var addr1 		= locArr[1];
	var addr2 		= locArr[2];
	var cityName 	= locArr[3];
	var distName	= locArr[4];
	var stateName	= locArr[5];
	var pin		 	= locArr[6];
		
	var locId	 	= locArr[7];
	var locCode	 	= locArr[8];
	var distCode	= locArr[9];
	var deptId      = locArr[10];
	
	if(document.findLocation.reportMode.value == "Y")
	{
		setSelectedItem(searchFieldName, locName, locCode);
	}
	else
	{
		if(window.opener.parent.document.forms[0].LOCATION_NAME_${param.SearchLocation} != null)
		{			
			window.opener.parent.document.forms[0].LOCATION_NAME_${param.SearchLocation}.value 	= locName;
			window.opener.parent.document.forms[0].ADDR1_${param.SearchLocation}.value 			= addr1;
			window.opener.parent.document.forms[0].ADDR2_${param.SearchLocation}.value 			= addr2;
			window.opener.parent.document.forms[0].CITY_NAME_${param.SearchLocation}.value 		= cityName;
			window.opener.parent.document.forms[0].DIST_NAME_${param.SearchLocation}.value 		= distName;
			window.opener.parent.document.forms[0].STATE_NAME_${param.SearchLocation}.value		= stateName;
			window.opener.parent.document.forms[0].PIN_${param.SearchLocation}.value 		    = pin;
				
			window.opener.parent.document.forms[0].LOCATION_ID_${param.SearchLocation}.value 	= locId;
			window.opener.parent.document.forms[0].LOCATION_CODE_${param.SearchLocation}.value 	= locCode;
			window.opener.parent.document.forms[0].DIST_CODE_${param.SearchLocation}.value 	    = distCode;
		    window.opener.parent.document.forms[0].DEPT_ID_${param.SearchLocation}.value        = deptId;
		
			if(window.opener.parent.document.forms[0].setLocationDetailsForCase_${param.SearchLocation} != undefined && window.opener.parent.document.forms[0].setLocationDetailsForCase_${param.SearchLocation} != null)
			{
				if(window.opener.parent.document.forms[0].setLocationDetailsForCase_${param.SearchLocation}.value == 'Y')
				{
					window.opener.setLocationDetails_${param.SearchLocation}();
				}
			}
		}
		// Below code is added by maitrak and samiksha to work with this jsp in IFRAME.....
		else
		{
			parent.window.opener.document.getElementById("LOCATION_NAME_${param.SearchLocation}").value	 = locName; 
			parent.window.opener.document.getElementById("ADDR1_${param.SearchLocation}").value	         = addr1; 
			parent.window.opener.document.getElementById("ADDR2_${param.SearchLocation}").value	         = addr2; 
			parent.window.opener.document.getElementById("CITY_NAME_${param.SearchLocation}").value	     = cityName; 
			parent.window.opener.document.getElementById("DIST_NAME_${param.SearchLocation}").value	     = distName; 
			parent.window.opener.document.getElementById("STATE_NAME_${param.SearchLocation}").value     = stateName; 
			parent.window.opener.document.getElementById("PIN_${param.SearchLocation}").value            = pin; 
			parent.window.opener.document.getElementById("LOCATION_ID_${param.SearchLocation}").value    = locId;
			parent.window.opener.document.getElementById("LOCATION_CODE_${param.SearchLocation}").value  = locCode;
			parent.window.opener.document.getElementById("DIST_CODE_${param.SearchLocation}").value      = distCode;
			parent.window.opener.document.getElementById("DEPT_ID_${param.SearchLocation}").value        = deptId
			if(parent.window.opener.document.forms[0].setLocationDetailsForCase_${param.SearchLocation} != undefined && parent.window.opener.document.forms[0].setLocationDetailsForCase_${param.SearchLocation} != null)
			{
				if(parent.window.opener.document.forms[0].setLocationDetailsForCase_${param.SearchLocation}.value == 'Y')
				{
					parent.window.opener.setLocationDetails_${param.SearchLocation}();
				}
			}
		}
	}	
	if(document.findLocation.keepLocInSession.value == "Yes")
	{
	
		setLocInSession(locName,locId,locCode);
	}
	window.close();
	
}	          

function setLocInSession(locName,locId,locCode)
{
	var url ='${pageContext.request.contextPath}/hdiits.htm';
    var pars = "actionFlag=CMN_setLocationInSession&locSearchName=${param.SearchLocation}&locName="+locName+"&locId="+locId+"&locCode="+locCode;
    
    var myajax = new Ajax.Request(url,{
		method: 'get',
		parameters: pars,
		onComplete: function(transport){
		
			if (200 == transport.status) 
			{
				processResponseSetLocInSession(transport);
			}
		}
	});
}
function processResponseSetLocInSession(transport)
{
	if (transport.status == 200) 
	{  
		var XMLDoc = transport.responseXML.documentElement;
		if(XMLDoc != null)
		{
			var arr = XMLDoc.getElementsByTagName('element');				    		
			var isKeepInSess = arr[0].childNodes[0].text;   
			
		}
	}	
}

function checkForm()
{
	var trimedStr = trimText(document.findLocation.locName.value);
	if(trimedStr == '')
	{
		alert('${alertMsg}');
		document.findLocation.locName.focus();
		return false;
	}
	else
	{
		return true;
	}
}

function init()
{
	self.focus();
	populateState();
	document.findLocation.locName.focus();
}
</script>
<hdiits:form name="findLocation" validate="true" method="post" action="./hdiits.htm?actionFlag=findLocation">
<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
	<li class="selected"><a href="#" rel="tcontent1"><hdiits:caption captionid="LOC.SEARCHLOCATION" bundle="${locLables}" captionLang="single" /></a></li>
	</ul>
</div>

<div class="tabcontentstyleForPopup">
<!-------------------------------------------------------- tb1 --------------------------------------------------->
<div id="tcontent1" class="tabcontentForPopup" tabno="0" title='<fmt:message bundle="${locLables}" key="LOC.SEARCHLOCATION"/>'>

<table class="tabtable" headerClass="datatableheader" border="0" width="100%" height="30%" style="tabtable">
	<tr>
		<td class="datatableheader" colspan="4" align="center"><b><hdiits:caption captionid="LOC.SEARCHCRITERIA" bundle="${locLables}"/></b></td>
	</tr>
	<tr>
	    <td class="fieldLabel" width="25%" ><hdiits:caption captionid="LOC.ENTERLOCATION" bundle="${locLables}" /></td>
		<td class="fieldLabel" width="25%" ><hdiits:text name="locName" id="locName" default="${locNameVal}"/></td>	
		<td class="fieldLabel" width="25%" ><hdiits:caption captionid="LOC.STATE" bundle="${locLables}" /></td>
		<td class="fieldLabel" width="25%" >
			<hdiits:select sort="false" name="lstState" id="lstState" bundle="${locLables}" style="width: 80%" onchange="populateDistrict()">
				<hdiits:option value="select">-----${select}-----</hdiits:option>
			</hdiits:select>
		</td>
	</tr>
	<tr>
		<td class="fieldLabel" width="25%" ></td>
		<td class="fieldLabel" width="25%" >(<hdiits:caption captionid="LOC.VIEWALL" bundle="${locLables}" />)</td>
		<td class="fieldLabel" width="25%" ></td>
		<td class="fieldLabel" width="25%" ></td>
	</tr>
	<tr>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.DIST" bundle="${locLables}" /></td>
		<td class="fieldLabel" width="25%">
			<hdiits:select sort="false" name="lstDist" id="lstDist" bundle="${locLables}" style="width: 80%" onchange="populateCity()">
				<hdiits:option value="select">-----${select}-----</hdiits:option>
			</hdiits:select>
		</td>
		<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.CITY" bundle="${locLables}" /></td>
		<td class="fieldLabel" width="25%">
			<hdiits:select sort="false" name="lstCity" id="lstCity" bundle="${locLables}" style="width: 80%">
				<hdiits:option value="select">-----${select}-----</hdiits:option>
			</hdiits:select>
		</td>
	</tr>
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><hr><hdiits:submitbutton type="button" captionid="LOC.SEARCH" bundle="${locLables}" name="searchButton" onclick="return checkForm()" style="width :5em" /></td>
	</tr>	
</table>

<c:if test="${locSearchCrt ne null and locList eq null}" >
<table class="tabtable" >
	<tr>
		<td class="fieldLabel" colspan="4" align="center"><b><fmt:message bundle="${locLables}" key="LOC.LOCNOTFOUND" /></b></td>
	</tr>
</table>
</c:if>
<c:if test="${locList ne null}">
<display:table pagesize="10" name="${locList}" id="row" requestURI="" style="width:100%" >
			<c:set var="readOnlyFlag" value="false" />
			<c:forEach items="${resultMap.disabledLocIds}" var="locIds" varStatus="status" >
				<c:if test="${row.locId eq locIds }">							
					<c:set var="readOnlyFlag" value="true" />
				</c:if>
			</c:forEach>
			<display:column class="tablecelltext" titleKey="LOC.SELECT" headerClass="datatableheader" >
				<hdiits:radio readonly="${readOnlyFlag}" name="rdoLoc" id="rdoLoc" value="${row.locName}*${row.locAddr1}*${row.locAddr2}*${row.cityName}*${row.districtName}*${row.stateName}*${row.locPin}*${row.locId}*${row.locCode}*${row.districtCode}*${row.deptId}"></hdiits:radio>
			</display:column>
	   		<display:column class="tablecelltext" value="${locList}" property="locName" sortable="true" titleKey="LOC.LOCATION" headerClass="datatableheader" ></display:column>
			<display:column class="tablecelltext" value="${locList}" property="locAddr1" sortable="true" titleKey="LOC.ADDR1" headerClass="datatableheader"  ></display:column>
			<display:column class="tablecelltext" value="${locList}" property="locAddr2" sortable="true" titleKey="LOC.ADDR2" headerClass="datatableheader"  ></display:column>
			<display:column class="tablecelltext" value="${locList}" property="cityName" sortable="true" titleKey="LOC.CITY" headerClass="datatableheader"  ></display:column>
			<display:column class="tablecelltext" value="${locList}" property="districtName" sortable="true" titleKey="LOC.DIST" headerClass="datatableheader"  ></display:column>
			<display:column class="tablecelltext" value="${locList}" property="stateName" sortable="true" titleKey="LOC.STATE" headerClass="datatableheader"  ></display:column>
			<display:column class="tablecelltext" value="${locList}" property="locPin" sortable="true" titleKey="LOC.PIN" headerClass="datatableheader"  ></display:column>

</display:table>
<table align="center"  border="0">
	<tr>
		<td>
			<hdiits:button type="button" name="submitButton" captionid="LOC.CLOSE" bundle="${locLables}" onclick="submitForm()" style="width :5em" />
		</td>
		
	</tr>
</table>
</c:if>
</div>
</div>
<script type="text/javascript">
	initializetabcontent("maintab")
	init();
	setWindowName(window, document.findLocation);
</script>  
<hdiits:hidden name="SearchLocation" 		default="${param.SearchLocation}" />
<hdiits:hidden name="searchOn" 		 		default="${param.searchOn}" />
<hdiits:hidden name="disabledLocId"         default="${param.disabledLocId}" />
<hdiits:hidden name="searchOnLoggedInLoc"   default="${param.searchOnLoggedInLoc}" />
<hdiits:hidden name="searchOnHiddenLoc"     default="${param.searchOnHiddenLoc}" />
<hdiits:hidden name="reportMode"     		default="${param.reportMode}" />
<hdiits:hidden name="keepLocInSession"     	default="${param.keepLocInSession}" />
<hdiits:hidden name="deptCodes"     	    default="${param.deptCodes}" />

<hdiits:hidden name="searchFieldName"     	    default="${param.searchFieldName}" />
</hdiits:form>


