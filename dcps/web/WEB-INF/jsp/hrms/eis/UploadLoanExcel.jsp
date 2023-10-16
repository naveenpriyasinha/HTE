<%
try {
%>

<%@ include file="../../core/include.jsp"%>
<%@ taglib prefix="hdiits" uri="http://hdiits.tcs.com"%>

<html>
<head>
<title>Upload loan excel sheet</title>

<script type="text/javascript" src="script/common/tagLibValidation.js"></script>
<script type="text/javascript" src="script/common/commonfunctions.js"></script>
<script type="text/javascript" src="script/common/address.js"></script>
<script type="text/javascript" src="script/common/calendar.js"></script>
<script type="text/javascript" src="script/common/CalendarPopup.js"></script>
<script type="text/javascript" src="script/hod/ps/common.js"></script>
<script type="text/javascript" src="script/common/person.js"></script>
<script type="text/javascript" src="script/common/addRecord.js"></script>
<script type="text/javascript" src="script/common/attachment.js"></script>
<script type="text/javascript" src="script/hrms/eis/commonUtils.js"></script>
<script>
	function uploadFile(){
		alert("File Upload");
	}
	
	function chekc(){
		var r=confirm("Are you sure to update records");
		if (r==true)
		 return true;
		else
		return false;
	}
	
	function closeWindow()
	{
		window.close();
	}
</script>
</head>

<c:set var="resultObj" value="${result}" > </c:set>
<c:set var="resValue" value="${resultObj.resultValue}" > </c:set>
<c:set var="msg" value="${resValue.msg}" ></c:set>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="resources.eis.eis_common_lables" var="enLables" scope="page"/>

<body>
<hdiits:form name="uploadLoan" validate="true" method="POST"
	action="hrms.htm?actionFlag=updateLoanDataByExcel" encType="multipart/form-data">

<div id="tabmenu">
	<ul id="maintab" class="shadetabs">
		<li class="selected"><a href="#" rel="tcontent1"><b>
			<hdiits:caption captionid="EL.UploadLoanExcel" bundle="${enLables}"></hdiits:caption></b></a></li></ul>
</div>

<div class="halftabcontentstyle">
<div id="tcontent1" class="halftabcontent" tabno="0">
<br>
   <TABLE width="60%" align="center">
	    <tr> <td></td><td></td></tr>
 		<TR><TD>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</TD>
		<td colspan="10"><b>Upload and Update Loan Data</b>
		   <jsp:include page="/WEB-INF/jsp/common/attachmentPage.jsp">
     	    	<jsp:param name="attachmentName" value="uploadLoan" />
         	    <jsp:param name="formName" value="uploadLoan" />
          	    <jsp:param name="attachmentType" value="Excel" />
	    		<jsp:param name="multiple" value="N" />
	    		<jsp:param name="mandatory" value="Y"/>
			</jsp:include>
		</td>
		</TR>
	</table>
 </div>
 
	
<table align="center">
	<tr align="center" >
		<td><hdiits:formSubmitButton name="formSubmitButton" value="Save" type="button"	captionid="eis.save" bundle="${enLables}" /> </td>
		<td><hdiits:button name="Close" value="Close" type="button" captionid="eis.close" bundle="${enLables}" onclick="closeWindow();" /></td>
	</tr>
</table>

	
</div>
	<hdiits:jsField  name="validate" jsFunction="chekc()" /> 
	
	<script type="text/javascript">
		//Start Tab Content script for UL with id="maintab" Separate multiple ids each with a comma.
		initializetabcontent("maintab")
		
		if("${msg}"!=null&&"${msg}"!='')
		{
					alert("${msg}");
					//window.location="./hrms.htm?viewName=UpdateLoanrExcel";
		}
		</script>
		
	<hdiits:validate controlNames="text" locale='<%=(String)session.getAttribute("locale")%>' />
</hdiits:form>

<%
		} catch (Exception e) {
		e.printStackTrace();
	}
%>
</body>
</html>