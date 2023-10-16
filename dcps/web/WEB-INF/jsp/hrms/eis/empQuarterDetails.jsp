<%
try {
	//System.out.println("in empQuarter Details...");

%>
<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="commonLables" scope="page"/>

<script type="text/javascript"   
	src="common/script/tagLibValidation.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
	<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript"  
    src="common/script/commonfunctions.js">
</script>
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>">
</script>
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





<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	

<fmt:setBundle basename="resources.Payroll" var="commonLable" scope="request"/>
<fmt:setBundle basename="resources.eis.eis_Constants" var="enConstants" scope="request"/>


<script type="text/javascript" language="JavaScript">


</script>
<body>
<hdiits:form name="empQuarterDetails"  validate="true" method="POST" action=""  onload="">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><fmt:message key="Quarter.Head" bundle="${commonLables}"/></b></a></li>
    </ul>
</div>

<div class="tabcontentstyle">
	
	<div id="tcontent1" class=tabcontent tabno="0">
	
	<table  width="85%" align="center">
		<tr>
			<td >
				<jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpOtherSearch"/>
						<jsp:param name="formName" value="empQuarterDetails"/>
					</jsp:include>
			</td>
		</tr>
		
		
			
	</table>
	<table  width="85%" align="center">
	  <tr>
			<TD width="20%" align="left"  class="fieldLabel" >
			<b><hdiits:caption captionid="Quarter.Type" bundle="${commonLables}"></hdiits:caption></b>
			</TD>
			<td width="30%" align="left" >
				<hdiits:radio name="rdotypeOfAccom" id="rdoRentFree" value="true" default="true" captionid="Quarter.rentfree" bundle="${commonLables}" />
				<hdiits:radio name="rdotypeOfAccom" id="rdoRented" value="false" captionid="Quarter.rented"  bundle="${commonLables}" />
			</td>
			<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
			<TD  width="16%" align="left"  class="fieldLabel" >
			<b><hdiits:caption captionid="Quarter.Allowto" bundle="${commonLables}"></hdiits:caption></b> </td>
			<td width="30%" align="left">
				<hdiits:radio name="rdoQuarterAlloted" id="rdoSelf" value="true" default="true" captionid="Quarter.self" bundle="${commonLables}" />
				<hdiits:radio name="rdoQuarterAlloted" id="rdoOther" value="false" captionid="Quarter.Others"  bundle="${commonLables}" />
			</td>
		</tr>
	</table>
	
	<table align="center" width="100%">
	<tr> </tr> <tr> </tr>
	
	<tr bgcolor="#A15700">
			<td class="fieldLabel" colspan="10">
 			<font color="#ffffff">
			<strong>Rent Details</strong></font></td>
	</tr>
	
	<tr>
		<TD width="12%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="Quarter.Rentrs" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" ><hdiits:text id="rent" name="rent" caption="rent" size="25"/>
		</td>
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="Quarter.ServiceChg" bundle="${commonLables}"></hdiits:caption></b> </td>
		<td width="30%" align="left"><hdiits:text id="serviceCharge" name="serviceCharge" caption="serviceCharge" size="25"/></td>
	</tr>
	<tr>
		<TD  width="12%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="Quarter.Garage"  bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left" ><hdiits:text id="garageCharges" name="garageCharges" caption="garageCharges" size="25"/>
		</td>
	</tr>
	
	<tr bgcolor="#A15700">
			<td class="fieldLabel" colspan="10">
 			<font color="#ffffff">
			<strong>Vacate Quarter Details</strong></font></td>
	</tr>
			
			
	<tr>
		<TD width="16%" align="left"  class="fieldLabel" > 
		<b><hdiits:caption captionid="Quarter.vacateletter" bundle="${commonLables}"></hdiits:caption></b></td>
		<td width="30%" align="left"><hdiits:text id="vacatingLetter" name = "vacatingLetter"  caption = "vacatingLetter" size="25"></hdiits:text></td>
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
		<TD  width="16%" align="left"  class="fieldLabel" >
		<b><hdiits:caption captionid="Quarter.ldate" bundle="${commonLables}"></hdiits:caption></b></td>
		<td width="30%" align="left" >
		<hdiits:dateTime name="LetterDate" captionid="Quarter.ldate"  bundle="${commonLables}" mandatory="true"
			validation="txt.isdt,txt.isrequired"  minvalue=""></hdiits:dateTime></td>
	</tr>
 	<tr></tr>	
	<tr></tr>
	<tr>
		<TD width="16%" align="left"   class="fieldLabel" >
		<b><hdiits:caption captionid="Quarter.Date" bundle="${commonLables}"></hdiits:caption></b>
		</TD>
		<td width="30%" align="left">
		<hdiits:dateTime name="DateVacated" captionid="Quarter.ldate"  bundle="${commonLables}" mandatory="true"
			validation="txt.isdt,txt.isrequired"  minvalue=""></hdiits:dateTime>
		</td>
		<td width="8%" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	
	</table>
	</div>
</div>
<script type="text/javascript">
initializetabcontent("maintab");
</script>
<hdiits:validate controlNames="tesxt"locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>
<%
		} catch (Exception e) {
			e.printStackTrace();

	}
%>