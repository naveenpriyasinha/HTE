<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="../core/include.jsp" %>

<fmt:setBundle basename="resources.common.CommonLables" var="locLables" scope="request"/>
 
<c:set var="name" 				  value="${param.SearchLocation}" />
<c:set var="searchOn" 			  value="${param.searchOn}" />
<c:set var="disabledLocId" 		  value="${param.disabledLocId}" />
<c:set var="mandatory" 			  value="${param.mandatory}" />
<c:set var="condition" 			  value="${param.condition}" />
<c:set var="title" 				  value="${param.searchLocationTitle}" />
<c:set var="hideFields" 		  value="${param.hideFields}" />
<c:set var="searchOnLoggedInLoc"  value="${param.searchOnLoggedInLocation}" />
<c:set var="searchOnHdnLoc" 	  value="${param.searchOnHiddenLocation}" />
<c:set var="reportMode" 		  value="${param.reportMode}" />
<c:set var="collapseOnLoad"       value="${param.collapseOnLoad}" />
<c:set var="keepLocInSession"     value="${param.keepLocInSession}" />
<c:set var="deptCodes"            value="${param.deptCodes}" />


<c:set var="resultObj" 		value="${result}" /> 
<c:set var="resultMap" 		value="${resultObj.resultValue}" /> 
<c:set var="locationVo" 	value="${resultMap.locationVo}" />
<c:set var="viewModeLoc" 	value="viewMode_${name}" />
<c:set var="readOnly" 		value="${resultMap[viewModeLoc]}" />
<c:set var="voName"  		value="locVo${name}" /> 
<c:set var="locationVo" 	value="${resultMap[voName]}" />

<script language="javascript">
var locSearchWindowObj = null; 
function GoToNewPage${name}() 
{
    var urlstyle = 'height=550,width=680,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes,top=50,left=200';
	var requeswtURL = '${rootUrl}';
	//window.open(requeswtURL+"?viewName=common-findlocation&SearchLocation=${name}&searchOn=${searchOn}&disabledLocId=${disabledLocId}","Show",urlstyle); 
	var url = requeswtURL+"?viewName=common-findlocation&SearchLocation=${name}&searchOn=${searchOn}&disabledLocId=${disabledLocId}&reportMode=${reportMode}&searchOnLoggedInLoc=${searchOnLoggedInLoc}&searchOnHiddenLoc=${searchOnHdnLoc}&keepLocInSession=${keepLocInSession}&deptCodes=${deptCodes}"; 
	
	if(locSearchWindowObj == null)
   	{
   		locSearchWindowObj = displayModalDialog(url, "Show", urlstyle); 
   	}
   	else
   	{
   		locSearchWindowObj.close();
   		locSearchWindowObj = displayModalDialog(url, "Show", urlstyle); 
   	} 

}
function resetSearch${name}()
{
	document.getElementById('LOCATION_NAME_${name}').value 	= '';
	document.getElementById('ADDR1_${name}').value 			= '';
	document.getElementById('ADDR2_${name}').value 			= '';
	document.getElementById('CITY_NAME_${name}').value 		= '';
	document.getElementById('DIST_NAME_${name}').value 		= '';
	document.getElementById('STATE_NAME_${name}').value 	= '';
	document.getElementById('PIN_${name}').value 			= '';
	
	document.getElementById('LOCATION_ID_${name}').value 	= '';
	document.getElementById('LOCATION_CODE_${name}').value 	= '';
	document.getElementById('DIST_CODE_${name}').value 	    = '';
	document.getElementById('DEPT_ID_${name}').value 	    = '';
	
	
	//Added to reset case search location - start
	if(document.forms[0].setLocationDetailsForCase_${param.SearchLocation} != undefined && document.forms[0].setLocationDetailsForCase_${param.SearchLocation} != null)
	{
		if(document.forms[0].setLocationDetailsForCase_${param.SearchLocation}.value == 'Y')
		{
			resetLocationDetails_${param.SearchLocation}();
		}
	}
	//Added to reset case search location - end
	
	// remove location from session
	if(document.getElementById('KEEP_LOC_SESS_${name}').value == "Yes")
	{
		removeLocFromSession();
	}
	
	
}
function removeLocFromSession()
{
	var url ='${pageContext.request.contextPath}/hdiits.htm';
    var pars = "actionFlag=CMN_setLocationInSession&locSearchName=${param.SearchLocation}&removeFrmSession=true";
    
    var myajax = new Ajax.Request(url,{
		method: 'get',
		parameters: pars,
		onComplete: function(transport){
		
			if (200 == transport.status) 
			{
				processResponseRemLocFrmSession(transport);
			}
		}
	});
}
function processResponseRemLocFrmSession(transport)
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

function editLocation(locId, locSearchName)
{
	if(locId != "" && locSearchName != "")
		populateLocationSearch(locId, locSearchName);
	
}
function populateLocationSearch(locId, locSearchName)
{
	var url ='${pageContext.request.contextPath}/hdiits.htm';
    var pars = "actionFlag=CMN_getLocationByLocId&locId="+locId+"&locSearchName="+locSearchName;
    
    var myajax = new Ajax.Request(url,{
		method: 'get',
		parameters: pars,
		onComplete: function(transport){
		
			if (200 == transport.status) 
			{
				processResponsePopulateLocation(transport);
			}
		}
	});
}

function processResponsePopulateLocation(transport)
{		
				if (transport.status == 200) 
				{  
					    var XMLDoc = transport.responseXML.documentElement;
				    	if(XMLDoc != null)
				    	{
				    		var locArr = XMLDoc.getElementsByTagName('element');				    		
							
							locSearchName 	= locArr[0].childNodes[0].text;   
			     			locId        	= locArr[0].childNodes[1].text;	
			     			locCode         = locArr[0].childNodes[2].text;     				   
			     			locName         = locArr[0].childNodes[3].text;
							locAddr1        = locArr[0].childNodes[4].text;
							locAddr2        = locArr[0].childNodes[5].text;
							cityName        = locArr[0].childNodes[6].text;
							stateName       = locArr[0].childNodes[7].text;
							districtName    = locArr[0].childNodes[8].text;
							districtCode    = locArr[0].childNodes[9].text;
							pinCode         = locArr[0].childNodes[10].text;
							deptId          = locArr[0].childNodes[11].text;
							
							
							var eleLocation = 'LOCATION_NAME_'+locSearchName;
							var eleAddr1    = 'ADDR1_'+locSearchName;
							var eleAddr2    = 'ADDR2_'+locSearchName;
							var eleCity     = 'CITY_NAME_'+locSearchName;
							var eleDist     = 'DIST_NAME_'+locSearchName;
							var eleState    = 'STATE_NAME_'+locSearchName;
							var elePin      = 'PIN_'+locSearchName;
							var eleLocId    = 'LOCATION_ID_'+locSearchName;
							var eleLocCode  = 'LOCATION_CODE_'+locSearchName;
							var eleDistCode = 'DIST_CODE_'+locSearchName;
							var eleDeptId   = 'DEPT_ID_'+locSearchName;
							
							document.getElementById(eleLocation).value 	= locName;
							document.getElementById(eleAddr1).value 	= locAddr1;
							document.getElementById(eleAddr2).value 	= locAddr2;
							document.getElementById(eleCity).value 		= cityName;
							document.getElementById(eleDist).value 		= stateName;
							document.getElementById(eleState).value 	= districtName;
							document.getElementById(elePin).value 		= pinCode;
							
							document.getElementById(eleLocId).value 	= locId;
							document.getElementById(eleLocCode).value 	= locCode;
							document.getElementById(eleDistCode).value 	= districtCode;
							document.getElementById(eleDeptId).value 	= deptId;
							
						}
	     			 }	
}	

function hideFields${name}()
{
	var hideFields = "${hideFields}";
	var hideFldsArr	= hideFields.split(",");
	if(hideFldsArr.length > 0)
	{
		for(var arrIndex = 0; arrIndex < hideFldsArr.length; arrIndex++)
		{
			if(hideFldsArr[arrIndex] == 'Addr')
			{
				document.getElementById('addrRow_${name}').style.display     = 'none';
			}
			if(hideFldsArr[arrIndex] == 'City-Dist')
			{
				document.getElementById('cityDistRow_${name}').style.display = 'none';
			}
			if(hideFldsArr[arrIndex] == 'State-Pin')
			{
				document.getElementById('statePinRow_${name}').style.display = 'none';
			}
		}
	}
	
}
</script>
<c:choose>
		<c:when test="${mandatory eq 'Yes'}">
			<c:set var="mandatoryFlag" value="true" />
		</c:when>
		<c:otherwise>
			<c:set var="mandatoryFlag" value="false" />
		</c:otherwise>
</c:choose>
<hdiits:fieldGroup titleCaption="${title}" mandatory="${mandatoryFlag}" id="locationSearchId" collapseOnLoad="${collapseOnLoad}" >
	<c:if test="${readOnly !='true'}">
		<table width="100%">
			<tr>
			    <td class="fieldLabel" width="75%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td class="fieldLabel" width="25%" align="right">
					<hdiits:a href="#" onclick="javascript:resetSearch${name}();" captionid="LOC.RESET" bundle="${locLables}" ></hdiits:a>
				</td>
			</tr>
		</table>
	</c:if>
<table width="100%">
<c:choose>    
		<c:when test="${mandatory eq 'Yes'}">               
    		<c:choose>
		        <c:when test="${empty condition}">   		      
		        	<tr>
			        	<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.LOCATION" bundle="${locLables}"/></td>
			        	<td class="fieldLabel" width="25%"><hdiits:text name="LOCATION_NAME_${name}" readonly="true" mandatory="true" validation="txt.isrequired" captionid="LOC.LOCATION" bundle="${locLables}" default="${locationVo.locName}"></hdiits:text></td>
			        	<hdiits:hidden name="LOCATION_ID_${name}" default="${locationVo.locId}" />
			        	<hdiits:hidden name="LOCATION_CODE_${name}" default="${locationVo.locCode}" />
			        	<hdiits:hidden name="DEPT_ID_${name}" default="${locationVo.deptId}" />
			        	<td class="fieldLabel" width="25%">
							<hdiits:button type="button" captionid="LOC.SEARCHLOCATION" bundle="${locLables}" name="btnSearchLocation_${name}" onclick="GoToNewPage${name}()" readonly="${readOnly}"/>
						</td>
					</tr>
					<tr id="addrRow_${name}" >
						<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.ADDR1" bundle="${locLables}"/></td>
				        <td class="fieldLabel" width="25%"><hdiits:text name="ADDR1_${name}" readonly="true" default="${locationVo.locAddr1}"></hdiits:text></td>
				     	
				     	<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.ADDR2" bundle="${locLables}"/></td>
				        <td class="fieldLabel" width="25%"><hdiits:text name="ADDR2_${name}" readonly="true" default="${locationVo.locAddr2}"></hdiits:text></td>
		    		</tr>
		     		<tr id="cityDistRow_${name}" >
						<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.CITY" bundle="${locLables}"/></td>
		        	    <td class="fieldLabel" width="25%"><hdiits:text name="CITY_NAME_${name}" readonly="true" default="${locationVo.cityName}"></hdiits:text></td>
		     		
						<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.DIST" bundle="${locLables}"/></td>
		        	    <hdiits:hidden name="DIST_CODE_${name}" default="${locationVo.districtCode}" />
		        	    <td class="fieldLabel" width="25%"><hdiits:text name="DIST_NAME_${name}" readonly="true" default="${locationVo.districtName}"></hdiits:text></td>
		     		</tr>
		     		<tr id="statePinRow_${name}" >
						<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.STATE" bundle="${locLables}"/></td>
				        <td class="fieldLabel" width="25%"><hdiits:text name="STATE_NAME_${name}" readonly="true" default="${locationVo.stateName}"></hdiits:text></td>
				     		
						<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.PIN" bundle="${locLables}"/></td>
				        <td class="fieldLabel" width="25%"><hdiits:text name="PIN_${name}" readonly="true" default="${locationVo.locPin}"></hdiits:text></td>
		   			</tr>
				</c:when>		        	        	        						
				<c:otherwise>
					<tr>				
						<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.LOCATION" bundle="${locLables}"/></td>
						<td class="fieldLabel" width="25%"><hdiits:text name="LOCATION_NAME_${name}" readonly="true" condition="${condition}" mandatory="true" validation="txt.isrequired" captionid="LOC.LOCATION" bundle="${locLables}" default="${locationVo.locName}"></hdiits:text></td>	 						
						<hdiits:hidden name="LOCATION_ID_${name}" default="${locationVo.locId}" />
		        		<hdiits:hidden name="LOCATION_CODE_${name}" default="${locationVo.locCode}" />
		        		<hdiits:hidden name="DEPT_ID_${name}" default="${locationVo.deptId}" />
		        		<td class="fieldLabel" width="25%">
							<hdiits:button type="button" captionid="LOC.SEARCHLOCATION" bundle="${locLables}" name="btnSearchLocation_${name}" onclick="GoToNewPage${name}()" readonly="${readOnly}"/>
						</td>
					</tr>
					<tr id="addrRow_${name}" >
						<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.ADDR1" bundle="${locLables}"/></td>
				        <td class="fieldLabel" width="25%"><hdiits:text name="ADDR1_${name}" readonly="true" default="${locationVo.locAddr1}"></hdiits:text></td>
				     	
				     	<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.ADDR2" bundle="${locLables}"/></td>
				        <td class="fieldLabel" width="25%"><hdiits:text name="ADDR2_${name}" readonly="true" default="${locationVo.locAddr2}"></hdiits:text></td>
		    		</tr>
					<tr id="cityDistRow_${name}" >
						<td class="fieldLabel" width="25%" ><hdiits:caption captionid="LOC.CITY" bundle="${locLables}"/></td>
		        	    <td class="fieldLabel" width="25%" ><hdiits:text name="CITY_NAME_${name}" readonly="true" default="${locationVo.cityName}"></hdiits:text></td>
		     		
						<td class="fieldLabel" width="25%" ><hdiits:caption captionid="LOC.DIST" bundle="${locLables}"/></td>
		        	    <hdiits:hidden name="DIST_CODE_${name}" default="${locationVo.districtCode}" />
		        	    <td class="fieldLabel" width="25%" ><hdiits:text name="DIST_NAME_${name}" readonly="true" default="${locationVo.districtName}"></hdiits:text></td>
		     		</tr>
		     		<tr id="statePinRow_${name}" >
						<td class="fieldLabel" width="25%" ><hdiits:caption captionid="LOC.STATE" bundle="${locLables}"/></td>
				        <td class="fieldLabel" width="25%" ><hdiits:text name="STATE_NAME_${name}" readonly="true" default="${locationVo.stateName}"></hdiits:text></td>
				     		
						<td class="fieldLabel" width="25%" ><hdiits:caption captionid="LOC.PIN" bundle="${locLables}"/></td>
				        <td class="fieldLabel" width="25%" ><hdiits:text name="PIN_${name}" readonly="true" default="${locationVo.locPin}"></hdiits:text></td>
		   			</tr>
				</c:otherwise>
 			</c:choose>
    	</c:when>  
   		<c:otherwise>
    		<tr>
	    		<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.LOCATION" bundle="${locLables}"/></td>
	   			<td class="fieldLabel" width="25%"><hdiits:text name="LOCATION_NAME_${name}" readonly="true" captionid="LOC.LOCATION" bundle="${locLables}" default="${locationVo.locName}"></hdiits:text></td>
	   			<hdiits:hidden name="LOCATION_ID_${name}" default="${locationVo.locId}" />
		    	<hdiits:hidden name="LOCATION_CODE_${name}" default="${locationVo.locCode}" />
		    	<hdiits:hidden name="DEPT_ID_${name}" default="${locationVo.deptId}" />
		    	<td class="fieldLabel" width="25%">
					<hdiits:button type="button" captionid="LOC.SEARCHLOCATION" bundle="${locLables}" name="btnSearchLocation_${name}" onclick="GoToNewPage${name}()" readonly="${readOnly}" />
				</td>
			</tr>
			<tr id="addrRow_${name}" >
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.ADDR1" bundle="${locLables}"/></td>
		        <td class="fieldLabel" width="25%"><hdiits:text name="ADDR1_${name}" readonly="true" default="${locationVo.locAddr1}"></hdiits:text></td>
		     	
		     	<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.ADDR2" bundle="${locLables}"/></td>
		        <td class="fieldLabel" width="25%"><hdiits:text name="ADDR2_${name}" readonly="true" default="${locationVo.locAddr2}"></hdiits:text></td>
		    </tr>
			<tr id="cityDistRow_${name}" >
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.CITY" bundle="${locLables}"/></td>
		        <td class="fieldLabel" width="25%"><hdiits:text name="CITY_NAME_${name}" readonly="true" default="${locationVo.cityName}"></hdiits:text></td>
		     		
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.DIST" bundle="${locLables}"/></td>
		        <hdiits:hidden name="DIST_CODE_${name}" default="${locationVo.districtCode}" />
		        <td class="fieldLabel" width="25%"><hdiits:text name="DIST_NAME_${name}" readonly="true" default="${locationVo.districtName}"></hdiits:text></td>
		   </tr>
		   <tr id="statePinRow_${name}" >
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.STATE" bundle="${locLables}"/></td>
		        <td class="fieldLabel" width="25%"><hdiits:text name="STATE_NAME_${name}" readonly="true" default="${locationVo.stateName}"></hdiits:text></td>
		     		
				<td class="fieldLabel" width="25%"><hdiits:caption captionid="LOC.PIN" bundle="${locLables}"/></td>
		        <td class="fieldLabel" width="25%"><hdiits:text name="PIN_${name}" readonly="true" default="${locationVo.locPin}"></hdiits:text></td>
		   </tr>
	 </c:otherwise>
</c:choose>
<hdiits:hidden name="KEEP_LOC_SESS_${name}" default="${keepLocInSession}" />
		    	 
</table>   
</hdiits:fieldGroup>
<script type="text/javascript">
	hideFields${name}();
</script>