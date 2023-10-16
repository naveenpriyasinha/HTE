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
<c:set var="lListUnMatchedVouchers" value="${resValue.lListUnMatchedVouchers}"></c:set>

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

function getAllVoucherEntriesForTreasuryAndYear()
{
	var yearId = document.getElementById("cmbFinYear").value;
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var fromDate =  document.getElementById("txtFromDate").value ;
	var toDate = document.getElementById("txtToDate").value;

	if(yearId == -1)
	{
		alert('Please select year');
		return;
	}
	if(treasuryCode == -1)
	{
		alert('Please select treasury');
		return;
	}
	if(fromDate=="")
	{
		alert('Please enter from date');
		return;
	}
	if(toDate=="")
	{
		alert('Please enter to date');
		return;
	}

	self.location.href = "ifms.htm?actionFlag=loadManualMatch&elementId=700108&requestForMatching=Yes&yearId="+yearId+"&treasuryCode="+treasuryCode+"&fromDate="+fromDate+"&toDate="+toDate;
}

function matchVouchersWithTreasuryNet()
{
	showProgressbar();
	var totalVouchers = document.getElementById("hdnCounter").value ;
	var finalSelectedVoucher=0;
	var contriVoucherIdPks = "";
	
	for(var k=1;k<=totalVouchers;k++)
	{
		if(document.getElementById("checkbox"+k).checked)
		{ 
			finalSelectedVoucher = k ;
		}
	}

	if(finalSelectedVoucher == 0)
	{
		alert('Please select at least one voucher');
		hideProgressbar();
		return false; 
	}

	for(var i=1;i<=totalVouchers;i++)
	{
		if(document.getElementById("checkbox"+i).checked)
		{
			if(i==finalSelectedVoucher)
			{
				contriVoucherIdPks = contriVoucherIdPks + document.getElementById("checkbox"+i).value  ;
			}
			else
			{
				contriVoucherIdPks = contriVoucherIdPks + document.getElementById("checkbox"+i).value + "~" ;
			}
		}
	}	

	var uri="ifms.htm?actionFlag=matchVouchersWithTreasuryNet";
	var url="contriVoucherIdPks="+contriVoucherIdPks;

	var myAjax = new Ajax.Request(uri,
		       {
		        method: 'post',
		        asynchronous: false,
		        parameters:url,
		        onSuccess: function(myAjax) {
					getDataStateChangedForMatchVouchers(myAjax);
				},
		        onFailure: function(){ alert('Something went wrong...');} 
		          } );
}

function getDataStateChangedForMatchVouchers(myAjax)
{
	XMLDoc = myAjax.responseXML.documentElement;
	var XmlHiddenValues = XMLDoc.getElementsByTagName('XMLDOC');
	var lBlFlag = XmlHiddenValues[0].childNodes[0].firstChild.nodeValue;

	var yearId = document.getElementById("cmbFinYear").value;
	var treasuryCode = document.getElementById("cmbTreasuryCode").value;
	var fromDate =  document.getElementById("txtFromDate").value ;
	var toDate = document.getElementById("txtToDate").value;

	if(lBlFlag=='true')
	{
		alert('The selected vouchers are matched.');
		hideProgressbar();
		self.location.href = "ifms.htm?actionFlag=loadManualMatch&requestForMatching=Yes&yearId="+yearId+"&treasuryCode="+treasuryCode+"&fromDate="+fromDate+"&toDate="+toDate;
	}
}
</script>

<hdiits:form name="formMatchContriManually" encType="multipart/form-data"
	validate="true" method="post">
<fieldset  class="tabstyle">
<legend> <b><fmt:message
		key="CMN.MATCHCONTRISMANUALLY" bundle="${dcpsLables}"></fmt:message></b></legend>
	<table width="75%" align="center" cellpadding="4" cellspacing="4" >	
			<tr>
				<td width="10%">
				</td>
				<td width="10%" align="left" ><fmt:message key="DCPS.FINYEAR" bundle="${dcpsLables}"></fmt:message>	
				</td>
				<td align="left">
					<select name="cmbFinYear" id="cmbFinYear" style="width:60%" onchange="getStartAndEndDateForYear();">
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
				<td align="left"><fmt:message key="CMN.TREASURY"
				bundle="${dcpsLables}"></fmt:message></td>
				<td><select name="cmbTreasuryCode" id="cmbTreasuryCode">
						<option value="-1"><fmt:message key="COMMON.DROPDOWN.SELECT" /></option>
						         				<c:forEach var="Treasury" items="${resValue.TreasuryList}" >
						         					<c:choose>
						         						<c:when test="${resValue.selectedTreasury==Treasury.id}">
						         							<option value="${Treasury.id}" selected="selected"><c:out value="${Treasury.desc}"></c:out></option>
						         						</c:when>
							         					<c:otherwise>
							         						<option value="${Treasury.id}"><c:out value="${Treasury.desc}"></c:out></option>
							         					</c:otherwise>
						         					</c:choose>
						         				</c:forEach>
					</select>
				</td>
			</tr>
	
			<tr>
				<td width="10%">
				</td>
				<td width="10%" align="left" ><fmt:message key="CMN.FROMDATE" bundle="${dcpsLables}"></fmt:message>	
				</td>
				<td align="left">
					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.fromDate}" var="fromDateVar"/>
					<input type="text" name="txtFromDate" id="txtFromDate" value="${fromDateVar}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" /> 
					<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtFromDate",375,570)' style="cursor: pointer;"/>
				</td>
				<td width="10%" align="left" ><fmt:message key="CMN.TODATE" bundle="${dcpsLables}"></fmt:message>	
				</td>
				<td width="40%" align="left">
					<fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${resValue.toDate}" var="toDateVar"/>
					<input type="text" name="txtToDate" id="txtToDate" value="${toDateVar}" maxlength="10" onkeypress="digitFormat(this);dateFormat(this);" onBlur="validateDate(this);" /> 
					<img src='images/CalendarImages/ico-calendar.gif' onClick='window_open(event,"txtToDate",375,570)' style="cursor: pointer;"/>
				</td>
			</tr>
	</table>
	
	<center>
			<hdiits:button type="button" captionid="BTN.SUBMIT" bundle="${dcpsLables}" id="submit" name="submit" onclick="getAllVoucherEntriesForTreasuryAndYear();"></hdiits:button>
	</center>
</fieldset>

<br/>

<c:if test="${resValue.lBlFirstTimeLoadFlag == 'false'}">
		<c:choose>
			<c:when test="${resValue.totalRecords != 0}">
					<fieldset  class="tabstyle">
					<legend> <b><fmt:message
							key="CMN.UNMATCHEDVOUCHERS" bundle="${dcpsLables}"></fmt:message></b></legend>
						<c:set var="counter" value="1"></c:set>
						<div align="center">
						<div class="scrollablediv" style="width:70%; overflow:auto;height: 250px; " >
						
						<input type="hidden" name="hdnCounter" id="hdnCounter" value="0"/>
						
						<display:table list="${lListUnMatchedVouchers}" id="vo" cellpadding="4" export="false" partialList="true"  offset="1" excludedParams="ajax" sort="external" size="${resValue.totalRecords}" defaultsort="3" defaultorder="descending" >
						    			      			        			      			      			  		         		         				
								              <display:setProperty name="paging.banner.placement" value="bottom"  />
								              
								              <display:column titleKey="CMN.CHKBXEMPSELECT" title="<input name='chkSelect'  type='checkbox' onclick='checkUncheckAll(this)'/>" headerClass="datatableheader" style="text-align:center;" class="oddcentre" sortable="true">
												<input type="checkbox" align="left" name="checkbox" id="checkbox${vo_rowNum}" value="${vo[0]}" />
												 	<script>
														document.getElementById("hdnCounter").value=Number(document.getElementById("hdnCounter").value) + 1;
													</script> 
											  </display:column>	 
								              
								              <display:column style="text-align: center;"  titleKey="CMN.SRNO" headerClass="datatableheader" class="oddcentre"
									            value="${vo_rowNum}" > 	
								              </display:column>
								              
								              <display:column  style="text-align:left" titleKey="CMN.VOUCHERNO" headerClass="datatableheader" class="oddcentre"
								              	 value="${vo[1]}" >	
									          </display:column>
									          
									          <fmt:formatDate dateStyle="full" pattern="dd/MM/yyyy" value="${vo[3]}" var="voucherDate"/>
									          <display:column style="text-align: left;"  titleKey="CMN.VOUCHERDATE" headerClass="datatableheader" class="oddcentre" >
									         		<c:out value="${voucherDate}"/>
									          </display:column>
								              	
								              <display:column  style="text-align:left" titleKey="CMN.ACTUALVOUCHERAMOUNT" headerClass="datatableheader" class="oddcentre"
								              	 value="${vo[2]}">
									          </display:column>	
									          
									          <!-- 
									          <display:column  style="text-align:left" titleKey="CMN.TREASURYNETVOUCHERAMOUNT" headerClass="datatableheader" class="oddcentre"
								              	 value="${vo[4]}">
									          </display:column>
									           -->	
									                		         
					     </display:table>
					     </div>
					     </br>
					     <hdiits:button type="button" captionid="BTN.MATCHVOUCHERS" classcss="bigbutton" bundle="${dcpsLables}" id="matchVouchers" name="matchVouchers" onclick="matchVouchersWithTreasuryNet();"></hdiits:button>
					     </div>
					  </fieldset>  
			</c:when>
			<c:otherwise>
				<c:out value="No Records found"></c:out>
			</c:otherwise>
		</c:choose>
</c:if>
</hdiits:form>