<%

try {
%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="request"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>

<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"  
    src="script/eis/hrms/commonUtils.js">
</script>

<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" 
	src="script/hod/ps/common.js">
</script>
<script type="text/javascript" 
	src="script/common/person.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>">
</script>

<script>
function checkDatefrom()
{
    if(document.getElementById("toDate").value!='' && document.getElementById("fromDate").value!='')
    {    	
    var diff = compareDate(document.getElementById("fromDate").value,document.getElementById("toDate").value);
    if(diff<=0)
    {
    	alert("End Date must be Greater then Start Date");
    	document.getElementById("toDate").value='';    
    	document.getElementById("toDate").focus();	
    	return false;
    }
    }
    return;
}
	function clearBoxes(chk)
	{
		if(chk.checked)
		{
			document.getElementById("vehiAvail").value="TRUE";
			document.getElementById("vehiCaption").style.display="";
			document.getElementById("vehiText").style.display="";
			document.getElementById("dateRow").style.display="";
		}
		else
		{
			document.getElementById("vehiAvail").value="FALSE";
			document.getElementById("vehiNo").value="";
			document.getElementById("fromDate").value="";
			document.getElementById("toDate").value="";
			document.getElementById("vehiCaption").style.display="none";
			document.getElementById("vehiText").style.display="none";
			document.getElementById("dateRow").style.display="none";
		}
	}
</script>

<c:set var="distance" value='<%=request.getParameter("distance")%>'/>
<c:set var="vehicalAvailed" value='<%=request.getParameter("vehicalAvailed")%>'/>
<c:set var="vehiNo" value='<%=request.getParameter("vehiNo")%>'/>
<c:set var="vehicalAvailDate" value='<%=request.getParameter("vehicalAvailDate")%>'/>
<c:set var="vehicalLeaveDate" value='<%=request.getParameter("vehicalLeaveDate")%>'/>
<table align="center" width="100%">
			
			<tr>    
			    
				<td><font size="2"><b><hdiits:caption captionid="Tra.vehi_Avail" bundle="${commonLables}"></hdiits:caption> </b></font> 
				<c:choose>
					<c:when test="${vehicalAvailed=='TRUE'}" >
					<c:set value="display:visible" var="visible"/>
					
						<input type="checkbox" name="chkVehicalAvailed" id="chkVehicalAvailed" checked  onclick="clearBoxes(this)">
						
					</c:when> 
					<c:otherwise>
					<c:set value="display:none" var="visible"/>
						<input type="checkbox" name="chkVehicalAvailed" id="chkVehicalAvailed" onclick="clearBoxes(this)">
						</c:otherwise>
			    </c:choose>
			    <hdiits:hidden name="vehiAvail" default="${vehicalAvailed }"></hdiits:hidden></td>
				<td></td>
				<td></td>
				<td style="<c:out value="${visible}"/>" id="vehiCaption"><font size="2"><b><hdiits:caption captionid="Tra.vehi_No" bundle="${commonLables}"></hdiits:caption> </b></font> </td>
				<td style="<c:out value="${visible}"/>" id="vehiText"><hdiits:text name="vehiNo" size="25" mandatory="true"
			    captionid="Tra.vehi_No" bundle="${commonLables}"
			   	condition="vehiAvailed()"   validation="txt.isrequired"  maxlength="15" onblur="checkSplCharExceptArg(this,'/-.')" default="${vehiNo}" /></td>
			</tr>
			
			<tr id="dateRow" style="<c:out value="${visible}"/>">    
			     
			    <td>
			    <font size="2"><b><hdiits:caption captionid="Tra.vehi_Avail_From_Date" bundle="${commonLables}"></hdiits:caption> </b></font> </td>
				<td>
				<hdiits:dateTime captionid="Tra.vehi_Avail_From_Date" condition="vehiAvailed()"
			    validation="txt.isrequired,txt.isdt" mandatory="true" default="${vehicalAvailDate}" bundle="${commonLables}" name="fromDate" onblur="checkDatefrom()"/></td>
				<td></td>
				<td>
					<font size="2"><b><hdiits:caption captionid="Tra.vehi_Avail_To_Date" bundle="${commonLables}"></hdiits:caption> </b></font>
				</td>
				<td><hdiits:dateTime captionid="Tra.vehi_Avail_To_Date" default="${vehicalLeaveDate}" bundle="${commonLables}"  name="toDate" onblur="checkDatefrom()"/></td>
			</tr>
		</table>
 	<%
	} catch (Exception e) {
		e.printStackTrace();
	}
%>