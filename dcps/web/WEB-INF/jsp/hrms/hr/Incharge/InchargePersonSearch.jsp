<%
try {
%>
<%@ include file="../../../core/include.jsp"%>

<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"  src="<c:url value="/script/common/base64.js"/>">
</script>

<fmt:setBundle basename="resources.hr.incharge.InchargeAlertMessage" var="alertLables"
	scope="request" />
<fmt:setBundle basename="resources.hr.incharge.InchargeLabels" var="CapLabels"
	scope="request" />
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="applicationName" value="${resultValue.applicationName}">
</c:set>

<c:set var="searchDetail" value="${resultValue.searchList}" />
<c:set var="postList" value="${resultValue.postDetails}" />

<SCRIPT LANGUAGE="JavaScript">
function SearchEmp(){
var href='<%=request.getRequestURL() %>'+'?actionFlag=allData';
		window.open(href,'chield', 'width=840,height=630,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		
		showSearch();
}
function search(){

document.getElementById("1").style.display="none";

}

function showSearch(){

document.getElementById("1").style.display="";

}
var empArray = new Array();

function empSearch(from){

for(var i=0; i<from.length; i++){
alert(from[i]);
empArray[i] = from[i].split("~"); 
alert(empArray[i]);
}
var single = empArray[0];
alert(single[0]);
var userID = single[2];
alert(document.getElementById('userID').value =userID );
alert(document.getElementById('empName').value = single[1]);
alert(document.getElementById('empDesg').value = single[7]);
alert(single[1]);
}




</script>

<hdiits:form name="Incharge" validate="true" method="POST"  action="" encType="text/form-data"> 

 	
<div id="tabmenu" >
			<ul id="maintab" class="shadetabs">
				<li class="selected"><a href="#" rel="tcontent1">
				<hdiits:caption captionid="IC.Incharge" bundle="${CapLabels}" captionLang="single"/></a></li>
			</ul>
		</div>
		<div class="tabcontentstyle" style="height: 100%">
	
		
		<div id="tcontent1" class="tabcontent" tabno="0" >
	<br>

<table id="empInfo" width="100%" align="center">
<tr>
<td width="25%"></td>
<td width="25%">First Name </td>
<td width="25%"> Middle Name </td>
<td width="25%"> Last Name </td>
</tr>

<tr>
<td width="25%">Employee Name</td>
<td width="25%"><hdiits:text name="fName" id="fName" default="${searchDetail.userFName}" /></td>
<td width="25%"><hdiits:text name="mName" id="mName" default="${searchDetail.userMName}" /></td>
<td width="25%"><hdiits:text name="lName" id="lName" default="${searchDetail.userLName}" /></td>
</tr>

<tr>
 <td width="25%">Department </td>
 <td width="25%"><hdiits:text name="department" id="department" default="${searchDetail.designation}" /> </td>
 <td width="25%">Location </td>
 <td width="25%"><hdiits:text name="location" id="location" /> </td>
</tr>

</table>


<table width="100%" border="1" id="1">
<tr bgcolor="#386CB7"  >
			<td align="left" colspan="4">
			<font color="#ffffff"> <strong><u> 
			<hdiits:caption captionid="IC.searchInchargePerson" bundle="${CapLabels}"></hdiits:caption></u></strong> </font>
			</td>
<td></td>			
</tr>
</table>

<table>
<tr align="center">
<input type="hidden" name="userID" id="userID">
<td>Employee Name </td>
<td><hdiits:text name="empName" id="empName" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" /> </td>
<td>Designation</td>
<td><hdiits:text name="empDesg" id="empDesg" readonly="true" style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;"/>
</td>
<td><hdiits:button name="Search" type="button" onclick="SearchEmp();" value="Employee Search"/></td>
</tr>

</table>
		

  <table id="searchResult" border="1" width="100%">
  <tr>
  <td>Sr. No
  </td>
  <td>Post Name</td>
  <td>Type of Post</td>
  <td>Incharge Name</td>
  <td>From Date</td>
  <td>To Date</td>
  </tr>
 <c:set var="i" value="1"></c:set>
 <c:set var="fromDate" value="fromdate"></c:set> 
	<c:set var="toDate" value="toDate"></c:set> 
 	
 <c:forEach var="sList" items="${postList}" >
 
 	
 
 	
 <tr>
 <td><hdiits:radio name="rdBtn" value="${sList.postId}" /> </td>
 <td><c:out value="${sList.postName}" /> </td>
  <td><c:out value="${sList.postLookupDesc}" /> </td>
    
        
 </tr> 
 </c:forEach>
 </table>


	
		

	</div>

	</div>
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />
	<hdiits:hidden name="encPath"/>
</hdiits:form>



</html>






<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
