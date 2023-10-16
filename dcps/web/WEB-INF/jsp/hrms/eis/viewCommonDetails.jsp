
<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@page import="java.util.List"%>
<script type="text/javascript" src="common/script/tabcontent.js"></script>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<fmt:setBundle basename="resources.eis.eis_common_lables"	var="CommonDetailsLabel" scope="request" />

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="curmonth" value="${resValue.curmonth}" ></c:set>
<c:set var="curyear" value="${resValue.curyear}" ></c:set>
<c:set var="billNosList" value="${resValue.BillList}" ></c:set>
<c:set var="curbill" value="${resValue.curbill}" ></c:set>


<script type="text/javascript">
	function ViewCommonDetails()
	{
		var Month=document.CommonDetails.selMonth.value; 
		var mahina = document.CommonDetails.selMonth.options[document.CommonDetails.selMonth.selectedIndex].text;// this will give you the selected month 
		var Year   = document.CommonDetails.selYear.value;
		var bill   = document.CommonDetails.billNo.value;// this wil give the selected bill ID
		var BillNo = document.CommonDetails.billNo.options[document.CommonDetails.billNo.selectedIndex].text;// this wil give the selected bill number
		if(!Month)
		{
				alert("Please Select The Month");
				return false;
		}
		if(!Year)
		{
			alert("Please Select The Year");
			return false;
		}	
		if(!bill)
		{
			alert("Please Select The Bill Number");
			return false;
		}	
		alert
		document.CommonDetails.action = "./hdiits.htm?actionFlag=showCommonDetails&exportflag=y&Month=" + Month + "&mahina=" + mahina + "&Year=" + Year + "&billNo="+bill+"&BillNo="+BillNo;
		document.CommonDetails.submit();
	}
</script>

<hdiits:form name="CommonDetails" validate="true" method="POST">

	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b> <hdiits:caption
			captionid="ViewCommonDetails" bundle="${CommonDetailsLabel}"></hdiits:caption>
		</b></a></li>
	</ul>
	</div>
	
	<br><br>
	<br><br><br><br><br><br>
<table width="85%" border="3" bordercolor=#ccccff bgcolor="white" align="center" id="searchTable">
	<tr>
		<td  align = "center"> MONTH </td><td  align = "center"> YEAR </td><td  align = "center"> BILL NUMBER </td>
	</tr>
	<tr>
		<td align = "center">
			<hdiits:select name="selMonth" size="1" sort="false" caption="Month" id="selMonth" validation="sel.isrequired"  > 
					<hdiits:option value="">-------Selected--------</hdiits:option>
					<c:forEach items="${monthList}" var="month">
						<c:choose>
							<c:when test="${month.lookupShortName==curmonth}">
								<hdiits:option value="${month.lookupShortName}" selected="true" > ${month.lookupDesc} </hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>
				</c:forEach>
		   </hdiits:select>
      </td>
      <td align = "center">
      		<hdiits:select name="selYear" size="1" sort="false" caption="Year" id="selYear" validation="sel.isrequired"  > 
				<hdiits:option value="">-------Selected--------</hdiits:option>
					<c:forEach items="${yearList}" var="year">
						<c:choose>
							<c:when test="${year.lookupShortName == curyear}">
								<hdiits:option value="${year.lookupShortName}" selected="true" > ${year.lookupDesc} </hdiits:option>
							</c:when>
							<c:otherwise>
								<hdiits:option value="${year.lookupShortName}"> ${year.lookupDesc} </hdiits:option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
		   	</hdiits:select>
      </td>
      <td align = "center">
      		<hdiits:select name="billNo"  id="billNo" size="1" caption="Bill No" sort="false"> 
				<hdiits:option value="">-------Selected--------</hdiits:option>
			<c:forEach items="${billNosList}" var="billNosList">
			<c:choose>
				<c:when test="${billNosList.billHeadId == curbill}">
						<hdiits:option value="${billNosList.billHeadId}" selected="true" > ${billNosList.billId}</hdiits:option>
				</c:when>
					<c:otherwise>
						<hdiits:option value="${billNosList.billHeadId}"> ${billNosList.billId}</hdiits:option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			</hdiits:select>		       
     </TD>
    </tr>
</table>
	<br><br><br><br><br><br>
	<center>
	<hdiits:button name="btn" value="Export Details" caption="Export Details" 
	id="ExportDetails" captionid="Export Details" onclick="ViewCommonDetails()" type="button" />
	</center>
	
	<br><br>

<hdiits:validate locale="${locale}" controlNames="" />
<script type="text/javascript">
	initializetabcontent("maintab")
	
</script>
</hdiits:form>

<%
}catch(Exception e) {e.printStackTrace();}
%>
	