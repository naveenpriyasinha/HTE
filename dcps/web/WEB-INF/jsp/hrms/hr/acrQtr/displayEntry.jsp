<%
try {
%>
<%@ include file="//WEB-INF/jsp/core/include.jsp" %>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="resources.hr.qtr.qtr_AlertMessages" var="commonLables1" scope="request"/>

<script type="text/javascript"   
src="common/script/tagLibValidation.js">
</script>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<script type="text/javascript"  
    src="script/common/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
	
<c:set var="resultObj" value="${result}"></c:set>
<c:set var="resValue" value="${resultObj.resultValue}"></c:set>	
<c:set var="uId" value="${resValue.entryId}" ></c:set>
<c:set var="fieldList" value="${resValue.fieldList}"></c:set>
<c:set var="viewList" value="${resValue.viewList}" ></c:set>
<c:set var="qtr" value="${resValue.qtr}" ></c:set>
<c:set var="yr" value="${resValue.yr}" ></c:set>
<c:set var="counter" value="${resValue.counter}" ></c:set>
<c:set var="EmpDetVO" value="${resValue.EmpDet}" />
<c:set var="reportFlag" value="${resValue.reportFlag}" ></c:set>

<script type="text/javascript">
var reportFlag=${reportFlag};



function submit1(){
	
	document.qtrDisplay.method="POST";
	document.qtrDisplay.action="./hrms.htm?actionFlag=getQtrSearchEntry";
	showProgressbar('Submitting Request...<br>Please wait...');
	document.qtrDisplay.submit();
}

function submit4()
{
	if(reportFlag==1)
	window.close();
	document.qtrDisplay.method="POST";
	document.qtrDisplay.action="hdiits.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome&elementId=302322";
	showProgressbar('Opening Page...<br>Please wait...');
	document.qtrDisplay.submit();
}
</script>

<fmt:setBundle basename="resources.hr.qtr.qtr" var="commonLables" scope="request"/>

<div id="tabmenu">
	 <ul id="maintab" class="shadetabs">
	 <li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="HR.QTR.QTR" bundle="${commonLables}" /></b></a></li>
	 </ul>
</div>
<hdiits:form name="qtrDisplay" validate="true"  encType="multipart/form-data" >
<div class="halftabcontentstyle">
	 <div id="tcontent1" class="tabcontent" tabno="0">
<table width="100%">
			<tr>
			<td class="fieldLabel" colspan="4"><%@ include
				file="/WEB-INF/jsp/hrms/eis/empInfo/EmployeeInfo.jsp"%></td>
		</tr>
</table>  
    
<c:set var="x" value= "1"/>
<c:forEach var="yearPrv" items="${counter}" varStatus="statusCounter" step="4">
	<c:set var="quarterPrv" value="${counter[statusCounter.index+1]}"/>
	<c:set var="fieldListPrv" value="${counter[statusCounter.index+2]}"/>
	<c:set var="viewListPrv" value="${counter[statusCounter.index+3]}"/>
	<c:set var="n" value="${x}"/>
	
<hdiits:fieldGroup bundle="${commonLables}"  expandable="true" collapseOnLoad="true" id="PreviousDetails" titleCaptionId="HR.QTR.PREVIOUS" >
	<table align="center" class="tabtable" width"80%">
	 <tr colspan="4">
	    <td width="15%" align="center"><hdiits:caption captionid="HR.QTR.year" bundle="${commonLables}" id="year1" />: </td>
	    <td width="35%"><b><label class="captionTag">${yearPrv}</label></b></td>
		<td width="15%" align="center"><hdiits:caption captionid="HR.QTR.quarter" bundle="${commonLables}" id="quarter1" />:</td>
	    <td width="35%"><b><label class="captionTag">${quarterPrv}</label></b></td>
	  </tr> 
	</table> 

	<br>
	
	<table align="center" width="100%">
		<tr>
		<td width="10%"></td>
			<td>
				<table class="tabtable" id="t${n}" align="center" border="1">
					<tr style="color: white; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #386CB7;">
						<td><center><b><fmt:message key="HR.QTR.goals" bundle="${commonLables}"/></b></center></td>
						<td><center><b><fmt:message key="HR.QTR.remarks" bundle="${commonLables}"/></b></center></td>
					</tr>
					<c:forEach var="viewListPrvItems" items="${viewListPrv}" varStatus="statusView">
					   <tr colspan="2">
						    <td width="25%">
						       <label class="captionTag">${fieldListPrv[statusView.index].field}</label>
						    </td>
						    <td width="75%">
						     <label class="captionTag">${viewListPrvItems.fieldView}</label>
						    </td>
					   </tr> 	
					</c:forEach>
					
				</table> 
			</td>
		<td width="10%"></td>
		</tr>
	</table>
</hdiits:fieldGroup>
<c:set var="x" value="${x+1}"/>
</c:forEach>

<c:if test="${x==1}" >
   <tr align="center">
   <td width="25%" align="center"><b><hdiits:caption captionid="HR.QTR.NOPREVIOUS" bundle="${commonLables}" /></b> </td>
   </tr>
   <br><br>
</c:if>

<hdiits:fieldGroup bundle="${commonLables}"  id="PresentDetails" expandable="true" titleCaptionId="HR.QTR.PRESENT">

<table align="center" class="tabtable" width="80%">
	 <tr colspan="4">
	    <td width="15%" align="center"><hdiits:caption captionid="HR.QTR.year" bundle="${commonLables}" id="year1" />:</td>
	    <td width="35%"><b><label class="captionTag">${yr}</label></b></td>
	    <td width="15%" align="center"><hdiits:caption captionid="HR.QTR.quarter" bundle="${commonLables}" id="quarter1" />:</td>
	    <td width="35%"><b><label class="captionTag">${qtr}</label></b></td>
	  </tr> 
</table> 
<br>
<table align="center" width="100%">
		<tr>
		<td width="10%"></td>
			<td>
				<table class="tabtable" id="t0" align="center"border="1">
					<tr style="color: white; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: #386CB7;">
						<td><center><b><fmt:message key="HR.QTR.goals" bundle="${commonLables}"/></b></center></td>
						<td><center><b><fmt:message key="HR.QTR.remarks" bundle="${commonLables}"/></b></center></td>
					</tr>
					<c:forEach var="viewListItems" items="${viewList}" varStatus="statusView">
					   <tr colspan="2">
						    <td width="25%">
						       <label class="captionTag">${fieldList[statusView.index].field}</label>
						    </td>
						    <td width="75%">
						       <label class="captionTag">${viewListItems.fieldView}</label>
						    </td>
					   </tr> 	
					</c:forEach>
					
				</table> 
			</td>
		<td width="10%"></td>
		</tr>
</table>
</hdiits:fieldGroup>

<table class="tabtable">
	<tr>
	  <td colspan ="4" align= "center">	
	  	<c:if test="${reportFlag==0}">	
		  <hdiits:button captionid="HR.QTR.BackToSearch" id="submitBut" bundle="${commonLables}"  name="Submit" type="button" onclick="submit1()" />
		</c:if>
		  <hdiits:button captionid="HR.QTR.Close" bundle="${commonLables}" name="SetDefault" type="button" onclick="submit4()"/>
	  </td>
	</tr>
</table>

</div>
</div>

<hdiits:validate controlNames="" locale='${locale}' />

</hdiits:form>

<script type="text/javascript">
	initializetabcontent("maintab")
</script>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>