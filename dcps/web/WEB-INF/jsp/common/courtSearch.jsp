<%try
{
%>

<%@ include file="../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.hod.ps.SummonsLables" var="courtLables"
	scope="request" />
<fmt:setBundle basename="resources.hod.ps.PSCaseSearchLables" var="searchLabels" scope="request"/>	
<script language="javascript">

	function searchCourt_<%=request.getParameter("append")%>()
	{
		var requeswtURL= '${rootUrl}';
		var urlstyle = 'height=550,width=680,toolbar=no,minimize=no,status=yes,memubar=no,location=no,scrollbars=no,top=50,left=200';
		//var url = requeswtURL+"?viewName=ps-showAndSearchCourt&fromSearch=Y&append="+"<%=request.getParameter("append")%>"; 
		var url = requeswtURL+"?actionFlag=showAndSearchCourtLoad&fromSearch=Y&append="+"<%=request.getParameter("append")%>";
		window.open(url,"Show",urlstyle); 
	}
	
	function checkValidation_<%=request.getParameter("append")%>()
	{
		if ('${param.mandatory}' == 'Y')
		{
			return true;
		}
		return false;
	}
	
</script>
<c:set var="resultObj"
	value="${result}"></c:set>
<c:set var="resultMap" value="${resultObj.resultValue}"></c:set>
<c:set var="append" value="${resultMap.append}"></c:set>
<c:set var="cmnCourtMst" value="${resultMap.courtMst}"></c:set>
<hdiits:hidden name="append"   id="append"  default="${resultMap.append}"/>

<tr>
	<TD class="fieldLabel" width="25%">
		<hdiits:caption captionid="SMNS.COURTNAME" bundle="${courtLables}" />
	</TD>
	<td class="fieldLabel" width="25%">		
		<c:choose>
			<c:when test="${param.mandatory eq 'Y'}">
				<hdiits:text id="courtName_${param.append}" name="courtName_${param.append}" condition="checkValidation_${param.append}()" validation="txt.isrequired"
				captionid="SMNS.COURTNAME" bundle="${courtLables}"  readonly="true" mandatory="true" default="${cmnCourtMst.courtName}"/>
			</c:when>
			<c:otherwise>
				<hdiits:text id="courtName_${param.append}" name="courtName_${param.append}" condition="checkValidation_${param.append}()" validation="txt.isrequired"
				captionid="SMNS.COURTNAME" bundle="${courtLables}"  readonly="true" default="${cmnCourtMst.courtName}"/>					 	
			</c:otherwise>
		 </c:choose>
	</td>
	<td class="fieldLabel" width="25%">
		<hdiits:button type="button" captionid="SEARCH_BTN"  bundle="${searchLabels}" name="searchButton_${param.append}" onclick="searchCourt_${param.append}()" />
	</td>
	<td  class="fieldLabel"width="25%">&nbsp;</td>
</tr>
	<hdiits:hidden id="courtId_${param.append}" name="courtId_${param.append}" default="${cmnCourtMst.courtId}"/>
<%
}catch(Exception e)
{
    
    e.printStackTrace();
}

%>