<%
try {
%>
<%@ include file="../../core/include.jsp"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page session="true"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>
<fmt:setBundle basename="resources.eis.eisLables_en_US" var="commonLables" scope="request"/>
<script type="text/javascript" src="/script/common/commonfunctions.js"></script>
<script type="text/javascript" src="<c:url value="/script/hrms/eis/Address.js"/>">
</script>
<fmt:setBundle basename="resources.eis.eis_common_lables"var="commonLables" scope="request" />
<script type="text/javascript"
	src="<c:url value="/script/common/address.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/calendar.js"/>"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/addRecord.js"/>"></script>
<script type="text/javascript"
	src="<c:url value="/script/common/attachment.js"/>"></script>
	
<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>	
<c:set var="monthList" value="${resValue.monthList}" ></c:set>
<c:set var="yearList" value="${resValue.yearList}" ></c:set>
<c:set var="empList" value="${resValue.empList}" ></c:set>
<script>

<%

SimpleDateFormat sdfObj = new SimpleDateFormat("yyyy");
Date dt = new Date();
int curYear=Integer.parseInt(sdfObj.format(dt));
sdfObj = new SimpleDateFormat("MM");
int curMonth=Integer.parseInt(sdfObj.format(dt));

pageContext.setAttribute("curMonth",curMonth);
pageContext.setAttribute("curYear",curYear);


%>
function generatePayCertificate()
{
if(document.getElementById("Employee_id_EmpSearch").value=="")
{
alert('Please select Employee First.');
document.frmPayCertificate.txtEmpId.focus();
return false;
}
else
{
	document.getElementById("txtEmpId").value=document.getElementById("Employee_Id_EmpSearch").value;
	
}
if(document.frmPayCertificate.month.value=="-1")
{
alert('Please select month');
document.frmPayCertificate.month.focus();
return false;
}
if(document.frmPayCertificate.year.value=="-1")
{
alert('Please select Year');
document.frmPayCertificate.year.focus();
return false;
}	
	
	var empId=document.getElementById("txtEmpId").value;
	var month = document.frmPayCertificate.month.value;
	var year = document.frmPayCertificate.year.value;
	var url = "./hrms.htm?actionFlag=generatePayCertificate&txtEmpId="+empId+"&month="+month+"&year="+year;
	window.open(url,"PayCertificate","status=yes, toolbar=yes,resizable=1, width = 1000, height = 600, top=100,left=90,scrollbars=1");
 //document.frmPayCertificate.submit();
	
}
var  point2 = document.getElementById("point2").value;
var  point3 = document.getElementById("point3").value;
var  no =  document.getElementById("no").value;
var  of =  document.getElementById("no").value;
var  vide =  document.getElementById("vide").value;
var transfer= document.getElementById("transfer").value;
</script>


<body>
<hdiits:form name="frmPayCertificate" validate="true" method="POST"	action="#" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b><hdiits:caption captionid="PayCertificate" caption="PayCertificate"/> </b></a></li>
	</ul>
	</div>
	
	<div class="halftabcontentstyle">
	<div id="tcontent1" class="halftabcontent" tabno="0">
	<div align="center">
 
 <table border="0" cellpadding="5" cellspacing="5">
 	<tr> <td colspan="5"><jsp:include page="/WEB-INF/jsp/ess/SearchEmployee.jsp">
						<jsp:param name="searchEmployeeTitle" value="Search Employee"/>
						<jsp:param name="SearchEmployee" value="EmpSearch"/>
						<jsp:param name="formName" value="frmPayCertificate"/>
					</jsp:include> </td></tr>
 	
 	<tr> <td width="35%" align="right"><!--<b><hdiits:caption captionid="OT.empName" bundle="${commonLables}"></hdiits:caption></b></td>-->
 	<td colspan="4" align="left">
 	<hdiits:hidden name="txtEmpId" default=""/>
	<!--<hdiits:select name="txtEmpId1" size="1" sort="false">
	<hdiits:option value="-1"> --Select-- </hdiits:option>
	<c:forEach items="${empList}" var="empList">
	<hdiits:option value="${empList.hrEisEmpMst.empId}"> ${empList.hrEisEmpMst.orgEmpMst.empPrefix} ${empList.hrEisEmpMst.orgEmpMst.empFname} ${empList.hrEisEmpMst.orgEmpMst.empMname} ${empList.hrEisEmpMst.orgEmpMst.empLname}</hdiits:option>
	</c:forEach>
	</hdiits:select>
	--></td>
 	</tr>
	
 
   	 <tr>	
	<TD align="right"> <b><hdiits:caption captionid="PR.MONTH" bundle="${commonLables}"/></b></TD>
	<td align="left">
	<hdiits:select name="month" size="1" sort="false">
	<hdiits:option value="-1"> --Select-- </hdiits:option>
	<c:forEach items="${monthList}" var="month">
	<c:choose>
            <c:when test="${curMonth == month.lookupShortName}">
 			 <hdiits:option selected="true"  value="${month.lookupShortName}"> ${month.lookupDesc}</hdiits:option>
			</c:when>
			<c:otherwise>
 			 <hdiits:option  value="${month.lookupShortName}"> ${month.lookupDesc} </hdiits:option>
			</c:otherwise>
	</c:choose>		
	</c:forEach>
	</hdiits:select>
	</td>
   <td> </td>
	<TD > <b><hdiits:caption captionid="PR.YEAR" bundle="${commonLables}"/></b></TD>
	<td>
		<hdiits:select name="year" size="1" sort="false">
	<hdiits:option value="-1"> --Select-- </hdiits:option>
	<c:forEach items="${yearList}" var="yearList">
	
	<c:choose>
            <c:when test="${curYear == yearList.lookupShortName}">
 			 <hdiits:option selected="true"  value="${yearList.lookupShortName}"> ${yearList.lookupDesc}</hdiits:option>
			</c:when>
			<c:otherwise>
 			 <hdiits:option  value="${yearList.lookupShortName}"> ${yearList.lookupDesc} </hdiits:option>
			</c:otherwise>
	</c:choose>		
	</c:forEach>
	</hdiits:select>
	</td>
	</tr>

	<tr><DIV style="color:red; font-style:italic">
	<tr>
	<TD align="right" ><b><hdiits:caption captionid="no" bundle="${commonLables}"/></b><b>
	<hdiits:text name="No" id="no" size="10"></hdiits:text></b></TD>
	<TD align="right"><b><hdiits:caption captionid="of" bundle="${commonLables}"/></b>
	<hdiits:text name="Of" id="of" size="10"></hdiits:text></TD>
	<TD ><b><hdiits:caption captionid="vide" bundle="${commonLables}"/></b>
	<hdiits:text name="Vide" id="vide" size="10"></hdiits:text></TD>
	</tr></DIV>
	<tr>
	<TD align="right" ><b><hdiits:caption captionid="point2" bundle="${commonLables}"/></b>
	<hdiits:dateTime captionid="point2" bundle="${commonLables}" name="point2" default=""/></TD>
	<TD align="right"><b><hdiits:caption captionid="point3" bundle="${commonLables}"/></b>
	<hdiits:dateTime captionid="point3" bundle="${commonLables}" name="point3" default=""/></TD>
	<TD align="right"><b><hdiits:caption captionid="transfer" bundle="${commonLables}"/></b>
	<hdiits:text name="Transfer" id="transfer" size="10"></hdiits:text></TD></tr>
	<tr><td></td></tr>
	
	 </div>
	</table>
   
   </div>
   <br> 
   <table align="center">
   
	
	<tr> <td> </td></tr>
	<tr> <td> </td></tr>
	<tr>
	<td align="center" colspan="3">
	<hdiits:button name="btn1" value="Generate Pay Certificate" caption="Generate Pay Certificate" 
	id="btnSubmit1" captionid="Generate Pay Certificate" onclick="generatePayCertificate()" 
	   type="button" />
	  </td>
	  </tr>	
	</table> 
    <br>
 </div>
 
	
	</div>
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		</script>
	<hdiits:validate controlNames="tesxt"
		locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>
</body>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>

