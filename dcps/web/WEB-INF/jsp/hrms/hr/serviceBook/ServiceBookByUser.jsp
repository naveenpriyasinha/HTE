

<%
try {
%>


<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<%@ include file="/WEB-INF/jsp/core/include.jsp"%>
<%@ taglib uri="http://ajaxtags.org/tags/ajax" prefix="ajax"%>

<html>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/base64.js"/>">
</script>


<fmt:setBundle basename="resources.hr.ServiceBook.ServiceBook"
	var="Caption" scope="request" />
<c:set var="resultObj" value="${result}">
</c:set>
<c:set var="resultValue" value="${resultObj.resultValue}">
</c:set>
<c:set var="Order" value="${resultValue.Order}">
</c:set>
<c:set var="date" value="${resultValue.date}">
</c:set>
<script type="text/javascript">

var empArray = new Array();

function SearchEmp(){
		
		var href='hdiits.htm?actionFlag=getEmpSearchSelData&multiple=false';
		window.open(href,'chield', 'width=840,height=550,toolbar=yes,minimize=yes,status=yes,memubar=yes,location=no,scrollbars=yes,top=50,left=200');
		return;
}
	
	
function empSearch(from){

	for(var i=0; i<from.length; i++){
	empArray[i] = from[i].split("~"); 
	}
	var single = empArray[0];
	var userID = single[2];
	document.getElementById('userID').value =userID;
	document.getElementById('empName').value = single[1];
	document.getElementById("srv").style.display="";
	return;
}
function closePage()
{
	document.forms[0].action = "./hrms.htm?actionFlag=getMenuOnApplicationPage&elementCodeForRoot=300022&viewJspName=hrmsHome";
	document.forms[0].submit();
}

</script>
<body>

<input type="hidden" name="sysDt" id="sysDt" value="${date}">


<hdiits:form name="compSearch" validate="true" method="POST"
	encType="text/form-data"
	action="./hrms.htm?actionFlag=getServiceBookData">


	<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"> <hdiits:caption
				captionid="SrvcBook.AppSrvcBook" bundle="${Caption}" captionLang="single"></hdiits:caption></a></li>
	</ul>
	</div>
	<div class="tabcontentstyle" style="height: 100%">


	<div id="tcontent1" class="tabcontent" tabno="0"><br>

	<table  align="center" >
		<tr align="center">
			<input type="hidden" name="userID" id="userID">
			<td align="center" ><b><hdiits:caption
				captionid="ServiceBook.EmpName" bundle="${Caption}"></hdiits:caption></b>
			</td>
			<td align="center" ><hdiits:text name="empName"
				id="empName" readonly="true"
				style="color: black; font-family: Verdana; font-weight: bold; font-size: 12px; background-color: lightblue;" />
			<img src="images/search_icon.gif" onclick="SearchEmp();">
			</td>
		</tr>
</table>

<hdiits:fieldGroup >
<table id="srv" align="center" style="display:none" width="100" >

		<tr align="center">
		<td>
		</td>
		<td>
		<hdiits:caption captionid="ServiceBook.alert.selectOrder" bundle="${Caption}"/>
		</td>
			<td align="center"><hdiits:select sort="false"
				name="Order" size="1" caption="drop_down" mandatory="true" captionid="ServiceBook.alert.selectOrder" bundle="${Caption}"
				validation="sel.isrequired">
				<hdiits:option value="0">
					<fmt:message key="ServiceBook.Select" bundle="${Caption}" />
				</hdiits:option>

				<c:forEach var="Order" items="${Order}">
					<hdiits:option value="${Order.lookupName}">
				${Order.lookupDesc}
				</hdiits:option>
				</c:forEach>

			</hdiits:select></td>
			
			<td>
		</td>
		</tr>
		<tr td align="center">
			<td align="center"><hdiits:caption captionid="srv.FromDate" bundle="${Caption}" />

			</td>
			<td><hdiits:dateTime name="fromdate" caption="From Date"
				mandatory="true" captionid="srv.FromDate" bundle="${Caption}"
				maxvalue="01/01/2099" validation="txt.isrequired"></hdiits:dateTime>
			</td>

			<td><hdiits:caption captionid="srv.ToDate" bundle="${Caption}" />
			</td>
			<td><hdiits:dateTime name="todate"  caption="From Date"
				mandatory="true" captionid="srv.ToDate" bundle="${Caption}"
				maxvalue="01/01/2099" validation="txt.isrequired" ></hdiits:dateTime>
			</td>
		</tr>
		<tr align="center">
		<td colspan="4" align="center">
			<hdiits:formSubmitButton name="sub" type="button" captionid="srv.getSrvcBook" bundle="${Caption}" />
			&nbsp;&nbsp;<hdiits:button name="btnClose" type="button" captionid="srv.btnClose" bundle="${Caption}" onclick="closePage()"></hdiits:button>
		</td>
		</tr>
	</table>

</hdiits:fieldGroup>
	</div>

	</div>
	
	<script type="text/javascript">		
		initializetabcontent("maintab")
	</script>
	
<fmt:formatDate var="todayDate" pattern="dd/MM/yyyy" value="${date}" type="date"/>
<script>
	document.compSearch.todate.value='${todayDate}';
</script>
	<hdiits:validate locale='<%=(String)session.getAttribute("locale")%>' />

</hdiits:form>

</body>

</html>

<%
	}

	catch (Exception e) {
		e.printStackTrace();
	}
%>
