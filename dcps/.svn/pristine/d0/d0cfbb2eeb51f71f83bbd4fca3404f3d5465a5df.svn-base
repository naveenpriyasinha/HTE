<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>

<fmt:setBundle basename="resources.dcps.dcpsLabels" var="dcpsLables" scope="request" />
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>
<c:set var="treasuriesList" value="${resValue.ListTreasuriesMatchEntries}"></c:set>

<script type="text/javascript" src="script/common/tabcontent.js"></script>
<script language="JavaScript" src="script/dcps/dcpsvalidation.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/common.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/dppf/calendarDppf.js"></script>
<script type="text/javascript">

function getStartAndEndDateForYear()
{
	var year = document.getElementById("cmbFinYear").value ;
	year = document.getElementById("cmbFinYear").options[document.getElementById("cmbFinYear").selectedIndex].text;
	year = year.substring(0,4);
	var nextYear = Number(Number(year)+1);
	var nextYearString = nextYear.toString();
	var partOfYear = "01/04/" ;
	var partOfNextYear =  "31/03/" ;
	var startDate = partOfYear.concat(year);
	var endDate = partOfNextYear.concat(nextYearString);
	document.getElementById("txtFromDate").value = startDate ;
	document.getElementById("txtToDate").value = endDate ;
}
function getMatchedContriEntriesReport(treasuryCodeValue)
{
	var treasuryCode = treasuryCodeValue ;
	var yearDesc = document.getElementById("cmbFinYear").options[document.getElementById("cmbFinYear").selectedIndex].text;
	var yearId = document.getElementById("cmbFinYear").options[document.getElementById("cmbFinYear").selectedIndex].value;
	var fromDate =  document.getElementById("txtFromDate").value ;
	var toDate =  document.getElementById("txtToDate").value ;
	url = "ifms.htm?actionFlag=reportService&reportCode=700030&action=generateReport&treasuryCode="+treasuryCode
		+"&yearDesc="+yearDesc+"&fromDate="+fromDate+"&toDate="+toDate+"&yearId="+yearId+"&MatchedOrUnMatched=Matched";

	self.location.href = url ;
}
function getMatchedEntries()
{
	var yearId = document.getElementById("cmbFinYear").value ;
	var fromDate =  document.getElementById("txtFromDate").value ;
	var toDate =  document.getElementById("txtToDate").value ;
	
	if(yearId== -1)
	{
		alert('Please select financial year to confirm request.');
		return;
	}
	else if(fromDate == "")
	{
		alert('Please select From Date to confirm request.');
		return;
	}
	else if(toDate == "")
	{
		alert('Please select To Date to confirm request.');
		return;
	}
	else
	{
		self.location.href='ifms.htm?actionFlag=loadMatchContriEntryForm&elementId=700072&yearId='+yearId+'&fromDate='+fromDate+'&toDate='+toDate;
	}
}
</script>

<fmt:setBundle	basename="resources.dcps.dcpsLabels" var="DCPSLables" scope="request" />
<hdiits:form name="frmDCPSMatchedEntries" encType="multipart/form-data"
	validate="true" method="post">
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"/>	
<c:set var="entries" value="${resValue.EntryList}" />

<input type="hidden"  name='txtDdoCode' id="txtDdoCode" style="text-align: left" value="${resValue.DDOCODE}" />

<br/>
<fieldset  class="tabstyle">
<legend> <b><fmt:message
		key="CMN.INPUTDETAILSFORCONTRI" bundle="${dcpsLables}"></fmt:message></b></legend>
<table id="table1" width="70%" align="center" cellpadding="4" cellspacing="4">	
	<tr>
		<td width="10%">
		</td>
		<td width="10%" align="left" ><fmt:message key="DCPS.FINYEAR" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td colspan="3" align="left">
			<select name="cmbFinYear" id="cmbFinYear" style="width:25%" onchange="getStartAndEndDateForYear();">
			<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
			<c:forEach var="finYear" items="${resValue.YEARS}" >
				<c:choose>
				<c:when test="${resValue.selectedYear==finYear.id}">
					<option value="${finYear.id}" selected="selected"><c:out value="${finYear.desc}"></c:out></option>
				</c:when>
				<c:otherwise>
					<option value="${finYear.id}" ><c:out value="${finYear.desc}"></c:out></option>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			</select>
		</td>
	</tr>
	
	<tr>
		<td width="10%">
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.FROMDATE" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td align="left">
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.fromDate}" var="fromDateVar"/>
			<input type="text" name="txtFromDate" id="txtFromDate" value="${fromDateVar}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" /> 
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtFromDate",375,570)' style="cursor: pointer;"/>
		</td>
		<td width="10%" align="left" ><fmt:message key="CMN.TODATE" bundle="${DCPSLables}"></fmt:message>	
		</td>
		<td width="40%" align="left">
			<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.toDate}" var="toDateVar"/>
			<input type="text" name="txtToDate" id="txtToDate" value="${toDateVar}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" /> 
			<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtToDate",375,570)' style="cursor: pointer;"/>
		</td>
	</tr>
</table>
</fieldset><br/>

	<center>
			<hdiits:button type="button" captionid="BTN.SUBMIT" bundle="${DCPSLables}" id="submit" name="submit" onclick="getMatchedEntries();"></hdiits:button>
	</center>
<br/>

<c:if test="${treasuriesList != null}">

	<c:set var="counter" value="1"></c:set>
	<div align="center">
	<div style="width:50%; auto;height:400px;" >
	
	<display:table list="${treasuriesList}" id="vo" cellpadding="4" pagesize="10" export="false" partialList="true"  offset="1" excludedParams="ajax" sort="external" size="${resValue.totalRecords}" defaultsort="3" defaultorder="descending" >    			      			        			      			      			  		         		         				
			              <display:setProperty name="paging.banner.placement" value="bottom" />
			              
			              <display:column style="text-align: center;"  titleKey="CMN.SRNO" headerClass="datatableheader" class="oddcentre"
				            value="${vo_rowNum}" > 	
			              </display:column>
			              	
			              <display:column  style="text-align:left" titleKey="CMN.TreasuryName" headerClass="datatableheader" class="oddcentre"
			               >	
			              <a href = # onclick="getMatchedContriEntriesReport('${vo[0]}');"><c:out value="${vo[1]} - ${vo[0]}" /></a>		      
				          </display:column>
			              	
			              <display:column  style="text-align:left" titleKey="CMN.DCPSAMOUNT" headerClass="datatableheader" class="oddcentre"
			               value="${vo[2]}">
				          </display:column>		     
				          
				          <display:column  style="text-align:left" titleKey="CMN.TREASURYNETAMOUNT" headerClass="datatableheader" class="oddcentre"
			               value="${vo[3]}">
				          </display:column>		     		         
			              	              	              
						  <c:set var="counter" value="${counter+1}"></c:set>              	              	              	            
     </display:table>
     
     </div>
     </div>
     
</c:if>

</hdiits:form>