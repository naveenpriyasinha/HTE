<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date, java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax" %>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>

<script>
function SaveInterestRateData()
{
	SaveDataUsingAjax();
	
	return true;

}
function SaveDataUsingAjax()
{
	//saveOrUpdateFlag=1;
	var uri = "ifms.htm?actionFlag=saveInterestRate";
	var url = runForm(0);

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					dataStateChangedForSaveInterest(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}
function dataStateChangedForSaveInterest(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XMLEntries  = XMLDoc.getElementsByTagName('XMLDOC');
	var successFlag = XMLEntries[0].childNodes[0].firstChild.nodeValue;
	if(successFlag)
	{
		alert("DCPS Interest Rate Saved Successfully");
		self.location.reload(true);
	}
}
</script>


<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmDCPSSanctionedBudget" encType="multipart/form-data"
	validate="true" method="post">
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="interestRateList" value="${resValue.interestRateList}" />

<fieldset class="tabstyle">
<legend>DCPS Interest Rate Entry</legend>
<table id="table1" width="40%" border = "0" align="center" cellpadding = 2 cellspacing = 2>	
	
	
	<tr>
		<td width="30%" align="left" ><fmt:message key="DCPS.INTERESTENTRY" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="70%" align="left">
			<input type="text"  size="20%" name='txtInterestEntry' id="txtInterestEntry" maxlength="5" onkeypress="digitFormat(this);" style="text-align: left" />
		</td>
		
	</tr>
	<tr>
		<td width="30%" align="left" ><fmt:message key="DCPS.EFD" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="70%" align="left">
			<input type="text" name="txtFromDate" id="txtFromDate"  maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" /> 
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtFromDate",375,570)' style="cursor: pointer;"/>
		</td>
	</tr>
	<tr>
		<td width="30%" align="left" >	
		</td>
		<td width="70%" align="left">
		<hdiits:button type="button" captionid="BTN.SAVE" bundle="${DCPSLables}" id="save" name="save" onclick="SaveInterestRateData();"></hdiits:button>
		</td>
	</tr>
</table>

</fieldset><br/>

<fieldset class="tabstyle">

	<display:table list="${interestRateList}"  id="vo"   requestURI="" export="" style="width:90%"  pagesize="5">	

		<display:setProperty name="paging.banner.placement" value="bottom" />
		<display:setProperty name="basic.empty.showtable" value="true" />
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.SRNO">
			<c:out value="${vo_rowNum}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre" style="text-align:center" sortable="true"  titleKey="CMN.RATE" >    
    			<c:out value="${vo[1]}%"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.EFFECTFROM">
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[2]}" var="fromDate"/>
			<c:out value="${fromDate}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.APPLICABLETO">
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}" var="toDate"/>
			<c:out value="${toDate}"></c:out>
		</display:column>
		<display:column headerClass="datatableheader" class="oddcentre"	style="text-align:center" sortable="true" titleKey="CMN.STATUS">
			<c:choose>
				<c:when test="${vo[4]==1}">
					<c:out value="Active"/>
				</c:when>
				<c:when test="${vo[4]==0}">
					<c:out value="Inactive"/>
				</c:when>
			</c:choose>
		</display:column>
	</display:table>
	
</fieldset>
<input type = "hidden" id = "totalEntries" name = "totalEntries" value = "${fn:length(interestRateList)}"  />
</hdiits:form>