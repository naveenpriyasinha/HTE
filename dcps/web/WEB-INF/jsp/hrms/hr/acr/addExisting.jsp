
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>

function getSelectedValues (select) 
{
	var r = new Array();
  	for (var i = 0; i < select.options.length; i++)
    	if (select.options[i].selected)
      		r[r.length] = select.options[i].value;
  	return r;
}


function searchPoints()
{		
	var desgSelArray=getSelectedValues(document.getElementById('selectDesgnE'));
	var authSelArray=getSelectedValues(document.getElementById('selectAuthorityE'));


	if( authSelArray.length == 0 && desgSelArray.length == 0 )
	{
		alert('<fmt:message  bundle="${commonLables}" key="HR.ACR.selectAnyOne"/>');	
	}
	else
	{
		var desgSel='';
	  	var authSel='';
		for(i=0;i<desgSelArray.length;i++)
		{
			desgSel=desgSelArray[i]+"~"+desgSel;
		}
		for(i=0;i<authSelArray.length;i++)
		{
			authSel=authSelArray[i]+"~"+authSel;
		}
		top.frames['addExistingPointsDetails'].submitSearchData(desgSel,authSel);
	}
}


</script>


<table width="100%" class="tabtable">
	<tr align="center" colspan="4">
		<td  align="left"  colspan="1">
			<b><fmt:message	key="HR.ACR.Designation" bundle="${commonLables}" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
		</td>
		<td  align="left"  colspan="1">
			<hdiits:select captionid="HR.ACR.Designation" bundle="${commonLables}" name="selectDesgnE" multiple="true" id="selectDesgnE" mandatory="false" sort="true" > 
				<c:forEach var="desgnListE" items="${desgnListE}" >
					<option value="<c:out value="${desgnListE.dsgnCode}" />">
					<c:out value="${desgnListE.dsgnName}"/>
					</option>
				</c:forEach>
			</hdiits:select>
		</td>
		<td  align="left"  colspan="1">
			<b><fmt:message	key="HR.ACR.Authority" bundle="${commonLables}"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</b>
		</td>
		<td  align="left"  colspan="1">
			<hdiits:select captionid="HR.ACR.Authority" bundle="${commonLables}" name="selectAuthorityE" id="selectAuthorityE"  mandatory="false" sort="true" multiple="true" > 
				<c:forEach var="reviewingOfficerListE" items="${reviewingOfficerListE}" >
					<option value="<c:out value="${reviewingOfficerListE.lookupName}" />">
					<c:out value="${reviewingOfficerListE.lookupDesc}"/>
					</option>
				</c:forEach>
			</hdiits:select>
		</td>
	</tr>
	<tr align="center" colspan="4">
		
	</tr>
	
	<tr align="center" colspan="4">
		
	</tr>
	
	<tr align="center">
		<td class="fieldLabel" align="center" colspan="4" width="100%" >
			<hdiits:button name="pointSearchButton" id="pointSearchButton" type="button" captionid="HR.ACR.Search" bundle="${commonLables}"
			 onclick="searchPoints();"  />
		</td>
	</tr>
	
</table>
<br>

<Div id="addExistingDetailsDiv"  width="100%" style="" >

<iframe name="addExistingPointsDetails"  id="addExistingPointsDetails" src="hdiits.htm?viewName=displayPointSearch" width="100%" height="30%" frameborder="1" scrolling="yes">
</iframe>


</DIV>


	